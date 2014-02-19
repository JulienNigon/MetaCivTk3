package civilisation.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;







import java.util.List;

import civilisation.Civilisation;
import civilisation.Configuration;
import civilisation.GroupAndRole;
import civilisation.ItemPheromone;
import civilisation.TurtleGenerator;
import civilisation.amenagement.Amenagement;
import civilisation.amenagement.Amenagement_Champ;
import civilisation.inspecteur.FenetreInspecteur;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.plan.NPlan;
import turtlekit.kernel.Turtle;
import turtlekit.viewer.TKDefaultViewer;
import turtlekit.kernel.Patch;
import turtlekit.pheromone.Pheromone;

/** 
 * Viewer of the environment
 * @author DTEAM
 * @version 1.0 - 2/2013
*/


public class WorldViewer extends TKDefaultViewer
{
	
	private static WorldViewer instance;
	private static final long serialVersionUID = -6736823013883615452L;
	FenetreInspecteur inspecteur;
	Boolean GraphismesAmeliores;
	NPlan planVisible;
	Boolean frontieresVisibles = true;
	ItemPheromone pheroToMap;
	Pheromone pheromoneToMap;
	GroupAndRole groupAndRoleToMap;

	

	public WorldViewer()
	{
		super();
		//inspecteur = new FenetreInspecteur("Inspecteur");  /*Inutile avec la version 1.08*/
		
		cellSize = 5;
		instance = this;
	}
	

	static public WorldViewer getInstance()
	{
		return instance;
	}


	//TODO repair compatibility
	/**
	 * Patch drawing.
	 */
	
	@Override
	public void paintPatch(Graphics g, Patch p,int x,int y,int cellS){
		//super.paintPatch(g, p, x, y, cellS);

		
			if (pheroToMap == null) {
				g.setColor(p.getColor());
				} else {
					double v = pheromoneToMap.get(cellS);
					if (v > 255) v = 255;
					else if (v < 0) v = 0;
					g.setColor(new Color (255 - (int)v, 255 - (int)v, 255));
				}
				g.fillRect(x,y,this.getCellSize(),this.getCellSize());

			/*	if (this.frontieresVisibles)
				{
					int controleur = getControleurPatch(p);
					List<Patch> neighbors = p.getNeighbors(1, true);

					if (controleur != -1)
					{
						Color c = Civilisation.getListeCiv().get(controleur).getCouleur();
						g.setColor(c);
						
						if (getControleurPatch(neighbors.get(0))!=controleur)
						{
							g.drawLine(x+cellS-1, y, x+cellS-1, y+cellS-1);
						}
						if (getControleurPatch(neighbors.get(6))!=controleur)
						{
							g.drawLine(x, y+cellS-1, x+cellS-1, y+cellS-1);
						}
						if (getControleurPatch(neighbors.get(4))!=controleur)
						{
							g.drawLine(x, y, x, y+cellS-1);
						}
						if (getControleurPatch(neighbors.get(2))!=controleur)
						{
							g.drawLine(x, y, x+cellS-1, y);
						}			
					}*/
				
			 	
				if (p.isMarkPresent(Amenagement_Champ.class.getName()))
				{
					Amenagement mark = (Amenagement) p.getMark(Amenagement_Champ.class.getName());
					mark.dessiner(g, x, y,  this.getCellSize());
					p.dropMark(Amenagement_Champ.class.getName(), mark);
				}
				if (p.isMarkPresent("Route"))
				{
					Amenagement mark = (Amenagement) p.getMark("Route");
					mark.dessiner(g, x, y, this.getCellSize());
					p.dropMark("Route", mark);
				}
		
				
	}
	
	@Override

	public void paintTurtle(Graphics g,Turtle t,int x,int y) {
		paintOneTurtle( g, t, x, y, true);
	}

	
	/**
	 * paint every turtle on the viewer
	 */
	private void paintOneTurtle(Graphics g,Turtle t,int x,int y, boolean first)
    {
		if(t.isPlayingRole("Humain")){
			
			int size;
			int dx , dy;
			
			
			if (this.getCellSize() > 20) {
				size = 4 + (this.getCellSize() - 20)/10;
				dx = x + (int)(this.getCellSize() * (t.getX()%1));
				dy = y + (int)(this.getCellSize() * (t.getY()%1));
				//System.out.println(size+" "+dx+" "+dy+" "+this.getCellSize()+" "+x+" "+y);
				if (first) {
					paintPatch(g, t.getPatch(),x,y,World.getInstance().get1DIndex(t.xcor(), t.ycor()));
					List<Turtle> turtles = t.getOtherTurtles(0, true);
					for (int i = 0 ; i < turtles.size() ; i++) {
						paintOneTurtle(g,turtles.get(i),x,y,false);
					}
				}
			}
			else {
				size = this.getCellSize();
				dx = x;
				dy = y;
			}
			
			Esprit e = ((Humain) t).getEsprit();
			
			// Les dessins sous le carre de couleur
			if(((Humain) t).getIsSelected()){
				g.setColor(Color.white);
				g.fillRect(dx-2,dy-2,size +4,size +4);
			}
			
			if ((planVisible == null || planVisible == e.getPlanEnCours().getPlan() ))
			{	
				//Color square
				if(getCellSize() > 4) {
					g.setColor(t.getPatch().getColor());
					g.fillRect(dx,dy,size,size);
					g.setColor(t.getColor());
					g.fillRect(dx+1,dy+1,size,size - 2);
				}
				else 
				{
					g.setColor(t.getColor());
					g.fillRect(dx,dy,size,size);
				}

				
				if (e.getHumain().isShowGroup)
				{	
					g.setColor(Color.DARK_GRAY);
					g.drawLine(dx+size -1, dy, dx+size -1, dy+size -1);
					g.drawLine(dx, dy+size -1, dx+size -1, dy+size -1);
					g.drawLine(dx, dy, dx, dy+size -1);
					g.drawLine(dx, dy, dx+size -1, dy);
					
					g.drawLine(dx+size -2, dy, dx+size -2, dy+size -1);
					g.drawLine(dx, dy+size -2, dx+size -1, dy+size -2);
					g.drawLine(dx+1, dy, dx+1, dy+size -1);
					g.drawLine(dx, dy+1, dx+size -1, dy+1);
				}
			}
			else
			{
				paintPatch(g, t.getPatch(),x,y,World.getInstance().get1DIndex(t.xcor(), t.ycor()));
				g.setColor(t.getColor());
				g.fillRect(dx+3,dy+3,size - 6,size - 6);
			}

		}


		// Les dessins sur le carr��� de couleur
		if(t.isPlayingRole("Communaute")){
			
			//Le carr��� de couleur
			g.setColor(t.getColor());
			g.fillRect(x+1,y+1,this.getCellSize() -1,this.getCellSize() -1);
			
			g.setColor(Color.DARK_GRAY);
			g.drawLine(x+this.getCellSize() -1, y, x+this.getCellSize() -1, y+this.getCellSize() -1);
			g.drawLine(x, y+this.getCellSize() -1, x+this.getCellSize() -1, y+this.getCellSize() -1);
			g.drawLine(x, y, x, y+this.getCellSize() -1);
			g.drawLine(x, y, x+this.getCellSize() -1, y);
		}	
	}
	
	
	
	public int getControleurPatch(Patch p)
	{
		int controlleur = -1;
		double smellControlleur = 0;
		Turtle t = TurtleGenerator.getInstance();

		for (int i = 0; i <Civilisation.getNombreCiv();i++)
		{
			if (smellControlleur < t.smellAt("civ" + i , p.x ,p.y))
			{
				controlleur = i;
				smellControlleur = t.smellAt("civ" + i , p.x ,p.y);
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
		pheromoneToMap = World.getInstance().getPheromone(itemPheromone.getNom());
	}

	public GroupAndRole getGroupAndRoleToMap() {
		return groupAndRoleToMap;
	}

	public void setGroupAndRoleToMap(GroupAndRole groupAndRoleToMap) {
		this.groupAndRoleToMap = groupAndRoleToMap;
	}

	
	
}
