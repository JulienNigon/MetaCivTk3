/*
 * TurtleKit - An Artificial Life Simulation Platform
 * Copyright (C) 2000-2010 Fabien Michel, Gregory Beurier
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package edu.turtlekit2.demos.mle;

import java.awt.Color;
import java.util.Random;

import edu.turtlekit2.kernel.agents.Turtle;


public class MleAgent extends Turtle
{
	private static final long serialVersionUID = 1L;
	//init Organoid Behavior Values/variables
	final static int levelMax = 2;//max level of emergence
	final static boolean adaptativMode = true;//adaptative repulsion for membran
	final static boolean autoRepuls = false;//repulsion beetween same lvl agents --> reduce emergence mutations

	double pheromoneQuantity = 10000; //arbitrary chosen - emitted quantity
	double sensibility = 3000;//depending on pheromones (to be changed fo need) approx 1/3 pheroQtty
	double minSensibility = 0.1; //not really needed - speed ud simulation
	double mutationFactor = 0.2; //% of chance to mutate in adequate conditions (50% decrease simultaneous mutations so increase simu speed)
	double myAverageEmission; //ad-hoc computation (with agent or Flavor Observer).coz of lack of information on turtlekit flavors
	int level = 0;//init lvl



	boolean goAway = false;
	//init Core Behavior Values/variables
	int speed ;
	int mutationDuration;
	double lastLowerPresAverage;

	//init variables
	double attractMax;
	double repulsMin;
	boolean membran;
	int myHeading = 0;
	Color color = Color.red;
	Random randomizer = new Random();
	//init Pheromones
	//perceived Pheromones Names
	String attractorName ="";
	String upperAttractorName="";
	String repulsorName="";
	String upperRepulsorName="";
	String presenceName="";
	String upperPresenseName="";
	String lowerPresenseName="";
	String myLvlRepulsorName="";
	//init percepts
	double myPresAverage;
	double lowerPresAverage;
	double upperPresAverage;
	int nbTurtles;
	double[] upperAttractors = new double [9];
	double[] upperRepulsors = new double [9];
	double[] myLvlRepulsors = new double [9];
	double[] presence = new double [9];
	double[] upperPresence = new double [9];
	double[] lowerPresence = new double [9];
	double[] turtles = new double [9];
	double presenceQtty, repulsorQtty, attractorQtty;

	public MleAgent() {
		super("basisBehavior");
		//    if(Math.random()>0.9) level = 2;
		membran = false;
		setColor(Color.blue);
		recursiveFunction();
		randomHeading();
	}
	/**
	 * initialisation
	 * color, pheromones, heading
	 */
	public void setup(){}

	public String basisBehavior()
	{
		myHeading = 8;
		initPercepts();
		processPercepts();
		updateDisplay();
		mutate();
		tryToMove();
		emitPheromones();
		presenceQtty = pheromoneQuantity;
		repulsorQtty = 2*pheromoneQuantity;
		attractorQtty = pheromoneQuantity;
		return("basisBehavior");
	}

	/*----------------------------------------------------------------------------*/
	/**
	 * graphic modifications
	 */
	void updateDisplay()
	{
		switch(level){
		case 0:{if(membran){color = Color.white;}
		else{color = Color.red;}    setColor(color);
		break;}
		case 1:{if(membran){color = Color.orange;}
		else{color = Color.green;}    setColor(color);
		break;}
		case 2:{if(membran){color = Color.yellow;}
		else{color = Color.blue;}    setColor(color);
		break;}
		}
	}
	/**
	 * simplified recursive function.
	 * - no tropisms .
	 */
	void recursiveFunction()
	{
		mutationDuration = (int)Math.pow(10,level+1);
		speed = (int)Math.pow(10,level);
		switch(level)
		{
		case 0: {
			myAverageEmission = 3200;
			attractorName = "";


			repulsorName = "";
			upperAttractorName = "ATT1";
			upperRepulsorName = "REP1";
			presenceName = "PRE0";
			upperPresenseName = "PRE1";   
			break;
		}
		case 1: {
			myAverageEmission = 7500;
			attractorName = "ATT1";
			upperAttractorName = "ATT2";
			repulsorName = "REP1";
			myLvlRepulsorName = "MYREP1";
			upperRepulsorName = "REP2";
			presenceName = "PRE1";
			upperPresenseName = "PRE2";
			lowerPresenseName = "PRE0";
			break;
		}
		case 2:{
			myAverageEmission = 12300;
			attractorName = "ATT2";
			repulsorName = "REP2";
			myLvlRepulsorName = "MYREP1";
			presenceName = "PRE2";
			lowerPresenseName = "PRE1";
			break;
		}
		}
		updateDisplay();
	}
	void mutate(){
		if (mutationDuration > 1){
			mutationDuration--;
		}
		else{
			if ( (myPresAverage-myAverageEmission) > sensibility 
					&&   upperPresAverage < minSensibility
					&& 	 level < levelMax ){
				if( Math.random() < mutationFactor){level++;
				recursiveFunction();
				if(adaptativMode) emit(attractorName,Math.pow(10,level)*pheromoneQuantity);
				}
			}
			else if ( lowerPresAverage < (sensibility+level)
					&& 	 Math.random() < mutationFactor
					&& 	 level > 0 ) {
				if( Math.random() < mutationFactor){level--;
				if(adaptativMode) emit(repulsorName,10*pheromoneQuantity);
				}
				recursiveFunction();
			}
		}
	}

	void move(){
		if( membran ) findPlaceInMembran();
		else if ( attractMax == 0 && !goAway){
			//wiggle
			turnRight(Math.random()*45);
			turnLeft(Math.random()*45);
			fd(1);
		}
		else if( myHeading < 8 ) walk();
		else if ( myHeading == 8 && !membran ) findAPlace();
		else fd(0);
	}
	void tryToMove(){
		speed--;
		if( speed < 1 ){
			speed = (int)Math.pow(10,level);
			move();
		}
	}
	void findPlaceInMembran(){
		boolean findAPlace = false;
		int i = 0 ;
		while( ( i < 8 ) && !findAPlace ){
			if( upperRepulsors[i] > upperAttractors[i] ){
				int anticlockWise = i+1;
				if( anticlockWise == 8 ) anticlockWise = 0;
				if( (turtles[anticlockWise] == 0)
						&& (upperAttractors[anticlockWise] > (upperRepulsors[anticlockWise]))){
					myHeading = anticlockWise; findAPlace = true;
				}
			}
			i++;
		}
		if( findAPlace )
		{
			walk();
		}
	}
	void findAPlace(){
		boolean findAPlace = false;
		int i = 0 ;
		while( ( i < 8 ) && !findAPlace ){
			if( turtles[i] > 0 ) {
				int anticlockWise = i+1;
				if( anticlockWise > 7 ) anticlockWise = 0;
				if( (turtles[anticlockWise] == 0)
						&& (upperAttractors[anticlockWise] > upperRepulsors[anticlockWise])){
					myHeading = anticlockWise; findAPlace = true;
				}
			}
			i++;
		}
		if(findAPlace){
			walk();
		}
	}

	void emitPheromones(){
		if(!adaptativMode){
			emit(presenceName, presenceQtty);
			emit(repulsorName,repulsorQtty);
			emit(attractorName,attractorQtty);
		}else{
			if( lastLowerPresAverage > lowerPresAverage ){//if membran increase - repulsor increase a bit
				repulsorQtty+=sensibility;
			}
			if( lowerPresAverage > pheromoneQuantity ){//if membran too near - repulsor increase drastically
				repulsorQtty+=pheromoneQuantity;
			}
			emit(presenceName,presenceQtty);
			emit(repulsorName,repulsorQtty);
			emit(attractorName,attractorQtty);
		}
		if(autoRepuls){
			emit(myLvlRepulsorName,repulsorQtty);
		}

	}


	/**
	 * Initialization of all perceived pheromones around agent
	 * (this method is the ad-hoc one)
	 */
	void initPercepts()
	{
		lastLowerPresAverage = lowerPresAverage;
		myPresAverage = 0;
		lowerPresAverage = 0;
		upperPresAverage = 0;
		nbTurtles = 0;

		//Pheromones Matrix
		if(upperAttractorName.length() != 0)
		{
			upperAttractors[0] = (smellAt(upperAttractorName,1,0));
			upperAttractors[1] = (smellAt(upperAttractorName,1,1));
			upperAttractors[2] = (smellAt(upperAttractorName,0,1));
			upperAttractors[3] = (smellAt(upperAttractorName,-1,1));
			upperAttractors[4] = (smellAt(upperAttractorName,-1,0));
			upperAttractors[5] = (smellAt(upperAttractorName,-1,-1));
			upperAttractors[6] = (smellAt(upperAttractorName,0,-1));
			upperAttractors[7] = (smellAt(upperAttractorName,1,-1));
			upperAttractors[8] = (smell(upperAttractorName));
		}
		else
			for(int i = 0; i<9; i++)
			{ upperAttractors[i] = 0; }

		if(myLvlRepulsorName.length() != 0)
		{
			myLvlRepulsors[0] = (smellAt(myLvlRepulsorName,1,0));
			myLvlRepulsors[1] = (smellAt(myLvlRepulsorName,1,1));
			myLvlRepulsors[2] = (smellAt(myLvlRepulsorName,0,1));
			myLvlRepulsors[3] = (smellAt(myLvlRepulsorName,-1,1));
			myLvlRepulsors[4] = (smellAt(myLvlRepulsorName,-1,0));
			myLvlRepulsors[5] = (smellAt(myLvlRepulsorName,-1,-1));
			myLvlRepulsors[6] = (smellAt(myLvlRepulsorName,0,-1));
			myLvlRepulsors[7] = (smellAt(myLvlRepulsorName,1,-1));
			myLvlRepulsors[8] = (smell(myLvlRepulsorName));
		}
		else
			for(int i = 0; i<9; i++)
			{ upperRepulsors[i] = 0; }


		if(upperRepulsorName.length() != 0)
		{
			upperRepulsors[0] = (smellAt(upperRepulsorName,1,0));
			upperRepulsors[1] = (smellAt(upperRepulsorName,1,1));
			upperRepulsors[2] = (smellAt(upperRepulsorName,0,1));
			upperRepulsors[3] = (smellAt(upperRepulsorName,-1,1));
			upperRepulsors[4] = (smellAt(upperRepulsorName,-1,0));
			upperRepulsors[5] = (smellAt(upperRepulsorName,-1,-1));
			upperRepulsors[6] = (smellAt(upperRepulsorName,0,-1));
			upperRepulsors[7] = (smellAt(upperRepulsorName,1,-1));
			upperRepulsors[8] = (smell(upperRepulsorName));
		}
		else
			for(int i = 0; i<9; i++)
			{ upperRepulsors[i] = 0; }


		if(presenceName.length() != 0)
		{
			presence[0] = (smellAt(presenceName,1,0));
			presence[1] = (smellAt(presenceName,1,1));
			presence[2] = (smellAt(presenceName,0,1));
			presence[3] = (smellAt(presenceName,-1,1));
			presence[4] = (smellAt(presenceName,-1,0));
			presence[5] = (smellAt(presenceName,-1,-1));
			presence[6] = (smellAt(presenceName,0,-1));
			presence[7] = (smellAt(presenceName,1,-1));
			presence[8] = (smell(presenceName));
		}
		else
			for(int i = 0; i<9; i++)
			{ presence[i] = 0; }

		if(upperPresenseName.length() != 0)
		{
			upperPresence[0] = (smellAt(upperPresenseName,1,0));
			upperPresence[1] = (smellAt(upperPresenseName,1,1));
			upperPresence[2] = (smellAt(upperPresenseName,0,1));
			upperPresence[3] = (smellAt(upperPresenseName,-1,1));
			upperPresence[4] = (smellAt(upperPresenseName,-1,0));
			upperPresence[5] = (smellAt(upperPresenseName,-1,-1));
			upperPresence[6] = (smellAt(upperPresenseName,0,-1));
			upperPresence[7] = (smellAt(upperPresenseName,1,-1));
			upperPresence[8] = (smell(upperPresenseName));
		}
		else
			for(int i = 0; i<9; i++)
			{ upperPresence[i] = 0; }

		if(lowerPresenseName.length() != 0)
		{
			lowerPresence[0] = (smellAt(lowerPresenseName,1,0));
			lowerPresence[1] = (smellAt(lowerPresenseName,1,1));
			lowerPresence[2] = (smellAt(lowerPresenseName,0,1));
			lowerPresence[3] = (smellAt(lowerPresenseName,-1,1));
			lowerPresence[4] = (smellAt(lowerPresenseName,-1,0));
			lowerPresence[5] = (smellAt(lowerPresenseName,-1,-1));
			lowerPresence[6] = (smellAt(lowerPresenseName,0,-1));
			lowerPresence[7] = (smellAt(lowerPresenseName,1,-1));
			lowerPresence[8] = (smell(lowerPresenseName));
		}
		else
			for(int i = 0; i<9; i++)
			{ lowerPresence[i] = 0; }


		//neighbours turtles matrix
		turtles[0] = (countTurtlesAt(1,0));
		turtles[1] = (countTurtlesAt(1,1));
		turtles[2] = (countTurtlesAt(0,1));
		turtles[3] = (countTurtlesAt(-1,1));
		turtles[4] = (countTurtlesAt(-1,0));
		turtles[5] = (countTurtlesAt(-1,-1));
		turtles[6] = (countTurtlesAt(0,-1));
		turtles[7] = (countTurtlesAt(1,-1));
		turtles[8] = 0 ;

		for(int i = 0; i < 9 ; i++)
		{      nbTurtles += turtles[i];    }

		//Averages for tropism methods (not in this class)
		for(int i = 0; i < 9 ; i++)
		{      myPresAverage += presence[i];    }
		myPresAverage /= 9;

		for(int i = 0; i < 9 ; i++)
		{      upperPresAverage += upperPresence[i];    }
		upperPresAverage /= 9;


		for(int i = 0; i < 9 ; i++)
		{      lowerPresAverage += lowerPresence[i];    }
		lowerPresAverage /= 9;
	}

	/**
	 * Processing of perceptions for circular emergent structuring
	 * the tropisms methods are not implemented
	 */
	void processPercepts() {
		//objective
		goAway = false;
		attractMax = 0;
		repulsMin = 99999999;
		membran = false;
		boolean attracted = false;
		double attractorQuantity = 0;

		for(int i = 0; i < 9 ; i++) {
			attractorQuantity = upperAttractors[i];
			//Maximal attraction square without turtle inside
			if ( attractorQuantity > attractMax //best attractive square
					&& attractorQuantity >= upperRepulsors[i] //better than repulsive squares
					                                       && turtles[i]==0 ){//without turtles) { //and perceived
				attractMax = attractorQuantity;
				myHeading = i;
				attracted = true;
			}
			//Am i a membran ? --> circular move (not needed !! - just for display)
			if( upperRepulsors[i] > upperAttractors[i]
			                                        && upperAttractors[8] > upperRepulsors[8]) {
				membran = true;
			}
		}

		//Minimal repulsion square
		if (!attracted && attractorQuantity>0) {
			for(int i = 0; i < 8 ; i++) {
				double rate = upperRepulsors[i];
				if ( rate < repulsMin
						&& turtles[i]==0 ) {
					goAway = true;
					repulsMin = rate;
					myHeading = i;
				}
			}
		}
	}



	/**
	 * Heading = square Index * 45
	 *
	 */
	void walk(){
		if(turtles[myHeading] == 0 ){
			if(!autoRepuls){
				setHeading((double)( myHeading * 45 ) );
			}
			else if(myLvlRepulsors[myHeading]<sensibility){
				if(level==1)System.out.println(myLvlRepulsors[myHeading]);
				setHeading((double)( myHeading * 45 ) );
			}else setHeading((double)( myHeading * -45 ) );
			fd(1);
		}
	}
}