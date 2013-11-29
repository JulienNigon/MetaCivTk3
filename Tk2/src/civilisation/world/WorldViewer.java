package civilisation.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;



import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.ItemPheromone;
import civilisation.amenagement.Amenagement;
import civilisation.inspecteur.FenetreInspecteur;
import civilisation.inspecteur.FenetreInspecteurCommunaute;
import civilisation.individu.Humain;
import civilisation.individu.plan.NPlan;
import civilisation.marks.ExplosionCombat;


import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.agents.Viewer;
import edu.turtlekit2.kernel.environment.Patch;

/** 
 * Gestion de la reprŽsentation du monde et de l'interaction avec lui (via click de souris)
 * A terme, permettra (eventuellement) de sŽlectionner des agents
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public class WorldViewer extends Viewer
{
	
	private static WorldViewer instance;
	private static final long serialVersionUID = -6736823013883615452L;
	FenetreInspecteur inspecteur;
	FenetreInspecteurCommunaute inspecteurCommunaute;
	Boolean GraphismesAmeliores;
	NPlan planVisible;
	Boolean frontieresVisibles = true;
	ItemPheromone pheroToMap;

	

	public WorldViewer()
	{
		super();
		//inspecteur = new FenetreInspecteur("Inspecteur");  /*Inutile avec la version 1.08*/
		
		cellSize = 5;
		instance = this;
	}
	
	@Override
	public void setup()
	{
		super.setup();
		GraphismesAmeliores = this.getBooleanParam("GraphismesAmeliores" , true); //n'est plus utilisŽ pour l'instant

	}
	
	static public WorldViewer getInstance()
	{
		return instance;
	}

	
	/**
	 * Redifination de l'affichage des patchs
	 */
	@Override
	public void paintPatch(Graphics g, Patch p,int x,int y,int cellS){
		
		if (pheroToMap == null) {
			g.setColor(p.color);
			} else {
				double v = p.smell(pheroToMap.getNom());
				if (v > 255) v = 255;
				else if (v < 0) v = 0;
				g.setColor(new Color (255 - (int)v, 255 - (int)v, 255));
			}
			g.fillRect(x,y,cellS,cellS);

			if (this.frontieresVisibles)
			{
				int controleur = getControleurPatch(p);
				if (controleur != -1)
				{
					Color c = Civilisation.getListeCiv().get(controleur).getCouleur();
					g.setColor(c);
					
					if (getControleurPatch(p.getNeighbors()[0])!=controleur)
					{
						g.drawLine(x+cellS-1, y, x+cellS-1, y+cellS-1);
					}
					if (getControleurPatch(p.getNeighbors()[6])!=controleur)
					{
						g.drawLine(x, y+cellS-1, x+cellS-1, y+cellS-1);
					}
					if (getControleurPatch(p.getNeighbors()[4])!=controleur)
					{
						g.drawLine(x, y, x, y+cellS-1);
					}
					if (getControleurPatch(p.getNeighbors()[2])!=controleur)
					{
						g.drawLine(x, y, x+cellS-1, y);
					}			
				}
			}

			if (p.isMarkPresent("Champ"))
			{
				Amenagement mark = (Amenagement) p.getMark("Champ");
				mark.dessiner(g, x, y, cellS);
				p.dropMark("Champ", mark); // On repose la mark, car elle est retirŽe ˆ l'appel du getÉ
			}
			if (p.isMarkPresent("Route"))
			{
				Amenagement mark = (Amenagement) p.getMark("Route");
				mark.dessiner(g, x, y, cellS);
				p.dropMark("Route", mark); // On repose la mark, car elle est retirŽe ˆ l'appel du getÉ
			}
			if (p.isMarkPresent("ExplosionCombat")) //Dessin d'un petit graphisme d'attaque
			{
				ExplosionCombat mark = (ExplosionCombat) p.getMark("ExplosionCombat");
				mark.dessiner(g, x, y, cellS);
			}
	}
	
	@Override
	public void paintTurtle(Graphics g,Turtle t,int x,int y,int cellSize)
    {


		
		// Les dessins sous le carrŽ de couleur
		if(t.isPlayingRole("Humain") && ((Humain) t).getIsSelected()){
			g.setColor(Color.white);
			g.fillRect(x-2,y-2,cellSize+4,cellSize+4);
		}
		
		if (t.isPlayingRole("Humain") && (planVisible == null || planVisible == ((Humain) t).getEsprit().getPlanEnCours().getPlan() ))
		{	
			//Le carrŽ de couleur
			g.setColor(t.getColor());
			g.fillRect(x+1,y+1,cellSize-1,cellSize-1);
		}
		else
		{
			//Le carrŽ de couleur
			g.setColor(t.getColor());
			g.fillRect(x+3,y+3,cellSize-3,cellSize-3);
		}


		// Les dessins sur le carrŽ de couleur
		if(t.isPlayingRole("Communaute")){
			
			//Le carrŽ de couleur
			g.setColor(t.getColor());
			g.fillRect(x+1,y+1,cellSize-1,cellSize-1);
			
			g.setColor(Color.DARK_GRAY);
			g.drawLine(x+cellSize-1, y, x+cellSize-1, y+cellSize-1);
			g.drawLine(x, y+cellSize-1, x+cellSize-1, y+cellSize-1);
			g.drawLine(x, y, x, y+cellSize-1);
			g.drawLine(x, y, x+cellSize-1, y);
		}
    	
		
	}
	
	public int getControleurPatch(Patch p)
	{
		int controlleur = -1;
		double smellControlleur = 0;
		
		for (int i = 0; i <Civilisation.getNombreCiv();i++)
		{
			if (smellControlleur < p.smell("civ" + i))
			{
				controlleur = i;
				smellControlleur = p.smell("civ" + i);
			}
		}
		
		return controlleur;
		
	}
	
	public void setPlanVisible(NPlan p)
	{
		planVisible = p;
	}

	public void setFrontieresVisibles(Boolean frontieresVisibles) {
		this.frontieresVisibles = frontieresVisibles;
	}

	public void setPheroToMap(ItemPheromone itemPheromone) {
		pheroToMap = itemPheromone;
	}

	
}
