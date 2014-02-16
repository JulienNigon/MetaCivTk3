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
package civilisation;

import java.awt.Color;
import java.util.ArrayList;

import civilisation.individu.Humain;
import civilisation.urbanisme.Batiment;
import civilisation.urbanisme.Batiment_Grenier;
import civilisation.world.World;

import turtlekit.kernel.Turtle;

/** 
 * Une communaut�, point de rassemblement d'un peuple
 * @author DTEAM
 * @version 1.0 - 2/2013
*/



@SuppressWarnings("serial")
public class Communaute extends Turtle 
{

	int visionRadius;
	Civilisation civ;
	ArrayList<Batiment> batiments;
	static int nombreCommunaute = 0;
	int index;
	/*Pour des tests*/
	Communaute ennemie;
	

	public Communaute()
	{
		super("start");
		batiments = new ArrayList<Batiment>();
		//Pour tester :
		batiments.add(new Batiment_Grenier(null,this));
		batiments.get(0).setTermine(true);
		index = nombreCommunaute;
		nombreCommunaute++;
		this.playRole("communaute");
	}

	public Communaute(Civilisation civ)
	{
		super("start2");
		batiments = new ArrayList<Batiment>();
		//Pour tester :
		index = nombreCommunaute;
		nombreCommunaute++;
		this.civ = civ;
		
	}
	
	
	
	@Override
	public void setup()
	{
		int posX = this.xcor();
		int posY = this.ycor();

		super.setup();
		setColor(Color.orange);
		playRole("Communaute");
		
		System.out.println(xcor() + " " + ycor() + " " + posX + " " + posY);
		this.moveTo(posX, posY);


	} 

	/**
	 * Action unique appell�e seulement use seule fois pour initialiser certaines informations
	 * @return Action suivante de la communaute
	 */
	public String start()
	{
		while(this.getPatchColor() == World.getColorOcean())
		{
			int u = (int) (Math.random()*getWorldWidth());
			int v = (int) (Math.random()*getWorldHeight());
			moveTo(u,v);
		}
		
		civ = new Civilisation();

		for (int i = 0; i < 15; i++)
		{
		//	Turtle h = new Humain(civ,this);
		//	createTurtle(h);
		}
		
		//setColor(civ.getCouleur());
		
		return "neRienFaire";
	}
	
	/**
	 * Action unique appell�e seulement use seule fois pour initialiser certaines informations.
	 * Permet de creer les agents li�s � cette communaut�
	 * @return Action suivante de la communaute
	 */
	public String start2()
	{
		System.out.println("AGENTS : " + civ.getAgentsInitiaux());
		for (int i = 0; i < civ.getAgentsInitiaux(); i++)
		{
			Humain h = new Humain(civ,this);
			this.launchAgent(h);
		}
		return "neRienFaire";
	}
	
	public String neRienFaire()
	{
		return "neRienFaire";
	}

	public void construire(Batiment b)
	{
		batiments.add(b);
	}
	
	public void retirerBatiment(Batiment b)
	{
		for (int i = 0; i < batiments.size(); i++){
			if (batiments.get(i).equals(b)){
				batiments.remove(i);
			}
		}
		if(batiments.isEmpty()){
			end();//die();
		}
	}
	
	//----------------GETTERS/SETTERS------------------
		public ArrayList<Batiment> getBatiments() {
		return batiments;
	}
	
		public int getIndex(){
			return index;
		}

		public boolean possedeHutte(Humain h) {

			for (int i = 0; i < batiments.size();i++)
			{
				if (batiments.get(i).getPossesseur() == h)
				{
					return true;
				}
			}

			return false;
		}

		public Civilisation getCiv() {
			return civ;
		}
	
		
		
}










