package civilisation.inspecteur.simulation.environnement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.world.Terrain;

public class GCarte extends JJComponent{
	



	PanelEnvironnement panelEnvironnement;
	BufferedImage bufferedImage; /*Image en mémoire pour dessin plus rapide*/
	
	public GCarte(JJPanel parent, int espacementVerticalCarte, int espacementHorizontalCarte,PanelEnvironnement panelEnvironnement) {		
		super(parent, espacementVerticalCarte, espacementHorizontalCarte, panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getLargeur(), panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getHauteur());
		this.panelEnvironnement = panelEnvironnement;
		this.addMouseListener(new MouseGCarteListener(this));
		this.addMouseMotionListener(new MouseGCarteListener(this));

    	int taille = panelEnvironnement.getTaillePseudoPatch();
    	int largeur = panelEnvironnement.getLargeur();
    	int hauteur = panelEnvironnement.getHauteur();
    	
    	this.setBounds(40, 40, taille*largeur, taille*hauteur);
    	dessinerBufferImage();
	}

    public void paintComponent(Graphics g) {

		System.out.println(this.getBounds());

        Graphics2D g2d = genererContexte(g);
    	super.paintComponents(g
    			);

    	
    	int taille = panelEnvironnement.getTaillePseudoPatch();
    	int largeur = panelEnvironnement.getLargeur();
    	int hauteur = panelEnvironnement.getHauteur();
    	//System.out.println(this.getBounds() +" " +this.getLocationOnScreen());

    	//this.setSize(taille*largeur, taille*hauteur);

        
        g2d.drawImage(bufferedImage,0,0,null);
        
    }

	public void changerPatch(MouseEvent e) {
    	int taille = panelEnvironnement.getTaillePseudoPatch();
		Terrain terrain = (Terrain) panelEnvironnement.getPanelPrincipal().getPanelTerrains().getListeTerrains().getSelectedValue();
		if (terrain != null){
			int x = (int)(e.getX()/(double)taille);
			int y = (int)(e.getY()/(double)taille);
			panelEnvironnement.getCarte().get(y).get(x).setTerrain(terrain);
		}
		else{
			System.out.println("Aucun terrain sélectionné!");
		}
		dessinerBufferImage();
		
	}

	public void actualiser(){
    	this.setBounds(40, 40, panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getLargeur(), panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getHauteur());
		//System.out.println(this.getBounds());
    	dessinerBufferImage();
	}
	
    private void dessinerBufferImage(){
    	
    	int taille = panelEnvironnement.getTaillePseudoPatch();
    	int largeur = panelEnvironnement.getLargeur();
    	int hauteur = panelEnvironnement.getHauteur();
    	
    	this.setBounds((int)getXx(), (int)getYy(), taille*largeur, taille*hauteur);
    	
        bufferedImage = new BufferedImage(taille*largeur,taille*hauteur,BufferedImage.TYPE_INT_RGB );
        Graphics2D g = bufferedImage.createGraphics();
        
        for (int i = 0; i < hauteur; i++){
        	for (int j = 0; j < largeur; j++){
        		if (panelEnvironnement.getCarte().get(i).get(j).getTerrain() == null){
            		g.setColor(Color.BLACK);
        		}
        		else{
            		g.setColor(panelEnvironnement.getCarte().get(i).get(j).getTerrain().getCouleur());
        		}
            	g.fill(new Rectangle2D.Double(j*taille,i
            			*taille,taille,taille));
        	}
        }
        
        
    }
	
    
	
	
}
