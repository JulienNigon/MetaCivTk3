package civilisation.inspecteur.simulation.environnement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import civilisation.inspecteur.animations.JJComponent;
import civilisation.inspecteur.animations.JJPanel;
import civilisation.world.Terrain;
import civilisation.*;

/**
 * @author juliennigon
 *
 */
public class GCarte extends JJComponent{
	



	PanelEnvironnement panelEnvironnement;
	BufferedImage bufferedImage; /*Image en mémoire pour dessin plus rapide*/
	int espacementVerticalCarte;
	int espacementHorizontalCarte;
	JPopupMenu popup;
	HashMap<Civilisation,PseudoPatch> startingPositions = new HashMap<Civilisation,PseudoPatch>();
	
	public GCarte(JJPanel parent, int espacementVerticalCarte, int espacementHorizontalCarte,PanelEnvironnement panelEnvironnement) {		
		super(parent, espacementVerticalCarte, espacementHorizontalCarte, panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getLargeur(), panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getHauteur());
		this.panelEnvironnement = panelEnvironnement;
		this.addMouseListener(new MouseGCarteListener(this));
		this.addMouseMotionListener(new MouseGCarteListener(this));

    	int taille = panelEnvironnement.getTaillePseudoPatch();
    	int largeur = panelEnvironnement.getLargeur();
    	int hauteur = panelEnvironnement.getHauteur();
    	this.espacementVerticalCarte = espacementVerticalCarte;
    	this.espacementHorizontalCarte = espacementHorizontalCarte;
    	
    	
    	this.setBounds(espacementHorizontalCarte, espacementVerticalCarte, taille*largeur, taille*hauteur);
    	this.setXx(espacementHorizontalCarte);
    	this.setYy(espacementVerticalCarte);
    	dessinerBufferImage();
	}

    @Override
	public void paintComponent(Graphics g) {
        Graphics2D g2d = genererContexte(g);
    	super.paintComponents(g);
        g2d.drawImage(bufferedImage,0,0,null);
    }

	public void changerPatch(MouseEvent e) {
    	int taille = panelEnvironnement.getTaillePseudoPatch();
		Terrain terrain = (Terrain) panelEnvironnement.getPanelPrincipal().getPanelTerrains().getListeTerrains().getSelectedValue();
		if (terrain != null){ /*On vérifie qu'un terrain est sélectionné*/
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
    	this.setXx(espacementHorizontalCarte);
    	this.setYy(espacementVerticalCarte);
    	this.setW(panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getLargeur());
    	this.setH(panelEnvironnement.getTaillePseudoPatch()*panelEnvironnement.getHauteur());
    	
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
            	g.fill(new Rectangle2D.Double(j*taille,i*taille,taille,taille));
        	}
        }
        
        /*Dessin des civilisations*/
        for (int i = 0; i < Configuration.civilisations.size(); i++){
        	Civilisation civ = Configuration.civilisations.get(i);
        	PseudoPatch pp = startingPositions.get(civ);
        	if (startingPositions.containsKey(civ)){
        		
    			g.setColor(civ.getCouleur());
            	g.fill(new Rectangle2D.Double(pp.getX()*taille,pp.getY()*taille,taille,taille));
    			
    			g.setColor(Color.DARK_GRAY);
    			g.drawLine(pp.getX()*taille, pp.getY()*taille, (pp.getX()+1)*taille, pp.getY()*taille);
    			g.drawLine(pp.getX()*taille, pp.getY()*taille, (pp.getX()+0)*taille, (pp.getY()+1)*taille);
    			g.drawLine((pp.getX()+1)*taille, pp.getY()*taille, (pp.getX()+1)*taille, (pp.getY()+1)*taille);
    			g.drawLine(pp.getX()*taille, (pp.getY()+1)*taille, (pp.getX()+1)*taille, (pp.getY()+1)*taille);
        	}
        }
        
        
    }

	public PanelEnvironnement getPanelEnvironnement() {
		return panelEnvironnement;
	}

	
	//TODO La peinture est completement à revoir : récursivité trop longue...
	public void peindre(MouseEvent e) {
		System.out.println("!!!En cours de développement!!!");

    	int taille = panelEnvironnement.getTaillePseudoPatch();
		Terrain terrain = (Terrain) panelEnvironnement.getPanelPrincipal().getPanelTerrains().getListeTerrains().getSelectedValue();
		if (terrain != null){ /*On vérifie qu'un terrain est sélectionné*/
			int x = (int)(e.getX()/(double)taille);
			int y = (int)(e.getY()/(double)taille);
			peintureDynamique(x , y ,panelEnvironnement.getCarte().get(y).get(x) , terrain, panelEnvironnement.getCarte().get(y).get(x).getTerrain());
		}
		else{
			System.out.println("Aucun terrain sélectionné!");
		}
		dessinerBufferImage();		
	}
	
	private void peintureDynamique(int x , int y, PseudoPatch pp , Terrain t, Terrain reference){
		pp.setTerrain(t);
		for (int i = -1 ; i <= 1 ; i++){
			for (int j = -1 ; j <= 1; j++){
				if (x + i > 0 &&
					y + j > 0 &&
					x + i < panelEnvironnement.getCarte().get(0).size() &&
					y + j < panelEnvironnement.getCarte().size()
					){
					PseudoPatch newPp = panelEnvironnement.getCarte().get(y+i).get(x+j);
					if (newPp.getTerrain() != reference){
						peintureDynamique(x+j , y+i ,newPp , t, reference);
					}
				}

			}
		}
	}

	public void afficherPopup(MouseEvent e) {
		popup = new JPopupMenu("Actions spéciales");
		
		JMenuItem editerPhero = new JMenuItem("Emplacement de départ");
		editerPhero.addActionListener(new ActionsMenuGCarte(this,0,e));
		editerPhero.setIcon(new ImageIcon(this.getClass().getResource("../../icones/pencil.png")));
		popup.add(editerPhero);
		
		popup.show(this, e.getX(), e.getY());			
	}

	public PseudoPatch getTargetPseudoPatch(MouseEvent e) {
    	int taille = panelEnvironnement.getTaillePseudoPatch();
		int x = (int)(e.getX()/(double)taille);
		int y = (int)(e.getY()/(double)taille);
		return panelEnvironnement.getCarte().get(y).get(x);
	}
	
	public void addStartingPosition(Civilisation c, PseudoPatch pp){
		this.startingPositions.put(c, pp);
		actualiser();
	}

	public HashMap<Civilisation, PseudoPatch> getStartingPositions() {
		return startingPositions;
	}

	
    
	
	
}
