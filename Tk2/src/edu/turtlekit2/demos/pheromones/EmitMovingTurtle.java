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

package edu.turtlekit2.demos.pheromones;

import java.awt.Color;
import java.util.ArrayList;

import edu.turtlekit2.kernel.agents.Turtle;



/**
 * <p>Titre : EmitMovingTurtle</p>
 * <p>Description : A random moving Turtle emitting flavors.</p>
 * <p></p>
 * <p>XML Attributes :</p>
 * <p>FlavorToEmit1, FlavorToEmit2, etc. : the emitted flavors/pheromones. Can't be omitted.</p>
 * <p>Quantity1, Quantity2, etc.: the quantity of emitted flavors</p>
 * <p>Quantity: Override quantity1, etc., all flavors are emitted with this quantity. default is 1000.</p>
 * <p>Frequency1,Frequency2, etc.: Flavor1 is emitted each "Frequency1" steps. default is 1.</p>
 * <p>Frequency: Override Frenquency1, etc. default is 1.</p>
 * <p>Speed: the speed of the turtle. Expressed in distance per step of simulation. default is 1</p> 
 * @author Gregory Beurier
 * @version 1.0 - 04/2007
 */

public class EmitMovingTurtle extends Turtle {
	private static final long serialVersionUID = 1L;
	String patchToEmit ="";
	String quantityToEmit="1000";
	int turns = 0; 
	String frequency = "1";
	double mySpeed = 1;
	double currentSpeed = 0;
	@SuppressWarnings("unchecked")
	ArrayList flavors = new ArrayList();
	@SuppressWarnings("unchecked")
	ArrayList frequencies =  new ArrayList();
	@SuppressWarnings("unchecked")
	ArrayList quantities = new ArrayList();

	public EmitMovingTurtle(){
		super("behavior");
	}

	/** Parse attributes and initialize the behavior of the turtle */
	@SuppressWarnings("unchecked")
	public void setup() {
		if(getAttributes().containsKey("quantity"))	quantityToEmit = getAttributes().getString("quantity");
		if(getAttributes().containsKey("frequency")) frequency = getAttributes().getString("frequency");
		if(getAttributes().containsKey("speed")) mySpeed = getAttributes().getDouble("speed");

		String key = "flavorToEmit";
		String key2 = "frequency";
		String key3 = "quantity";
		int rank = 1;
		while(getAttributes().containsKey(key+rank)){
			flavors.add(getAttributes().getString(key+rank));
			if(getAttributes().containsKey(key2+rank)) frequencies.add(getAttributes().getString(key2+rank));
			else frequencies.add(frequency);
			if(getAttributes().containsKey(key3+rank)) quantities.add(getAttributes().getString(key3+rank));
			else quantities.add(quantityToEmit);
			rank++;
		}
	}

	/** toSring method */
	public String toString(){
		return getName();
	}

	/** The turtle moves according to its speed and emits flavors each frequency steps with an amount equal to quantity */
	public String behavior(){
		currentSpeed += mySpeed;
		if (currentSpeed > 1){
			turnRight(Math.random() * 45);
			turnLeft(Math.random() * 45);
			if(mySpeed < 1){
				fd(1);
				currentSpeed -= 1;
			}else{
				fd((int)currentSpeed);
				currentSpeed = 0;
			}
		}

		setColor(new Color(new Float(Math.random()), new Float(Math.random()), new Float(Math.random())));

		for(int i = 0; i<flavors.size(); i++){
			if(turns % (Integer.parseInt((String)frequencies.get(i))) == 0){
				emit((String)flavors.get(i),Double.parseDouble((String)quantities.get(i)));
			}
		}
		turns++;
		return("behavior");
	}
}
