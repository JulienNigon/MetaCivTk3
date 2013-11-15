package civilisation.inspecteur.animations;

import java.util.ArrayList;

public class JJAnimationForDiagramme extends JJAnimation{

	ArrayList<Double> increments;
	JJStatDiagramme diag;
	
	public JJAnimationForDiagramme(int pas , JJStatDiagramme compo, boolean vieLimite) {
		super(pas,compo,vieLimite);
		diag = compo;
		increments = new ArrayList<Double>();

		//Calcul des increments
		for (int i = 0; i < diag.getListeValeurs().size(); i++){
			/*System.out.println(diag.getValeursPositions().get(i));
			System.out.println(diag.getValeurTotalePosition());
			System.out.println(diag.getListeValeurs().get(i));
			System.out.println(diag.getValeurTotale());*/

			increments.add((-1)*((diag.getValeursPositions().get(i)-diag.getListeValeurs().get(i))*(diag.getValeurTotalePosition()/diag.getValeurTotale()))/pas);
			System.out.println("Incr crŽŽ : " + increments.get(i));

		}
	}
	
	// Pour rendre inutilisable le constructeur par dŽfaut
	private JJAnimationForDiagramme(int pas , JJComponent compo , double dx , double dy, boolean vieLimite) {
		super(pas,compo,vieLimite);
	}
	
	public boolean animer(){

		System.out.println("DIAG");
		for (int i = 0; i < diag.getListeValeurs().size(); i++){
			/*System.out.println(diag.getValeursPositions().get(i));
			System.out.println("Incr : " + increments.get(i));*/

			diag.getValeursPositions().set(i, diag.getValeursPositions().get(i) + increments.get(i));
			System.out.println( diag.getValeursPositions().get(i) + " avec incr : " + increments.get(i) + " pour total : " +increments.get(i)*pas);
		}
		

		return super.animer();
	}	

}
