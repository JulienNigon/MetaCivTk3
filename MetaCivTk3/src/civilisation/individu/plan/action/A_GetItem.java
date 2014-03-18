package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class A_GetItem extends Action{

	Objet obj;
	
	@Override
	public Action effectuer(Humain h) {

		System.out.println(obj.getNom()); //TODO
		return nextAction;
	}

	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);
		
		if (option.getParametres().get(0).getClass().equals(Objet.class)){
			obj = (Objet) option.getParametres().get(0);
		}
		else
		{
			System.out.println("Mauvaise initialisation d'une action!");
		}

	}
	

	/**
	 * Retourne la structure des param_tres.
	 * Permet de d_terminer la pr_sentation de la fen_tre de r_glages.
	 */
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			String[] objet = {"**Objet**" , "ObjetGagne"};
			schemaParametres.add(objet);
		}
		return schemaParametres;	
	}
	
}
