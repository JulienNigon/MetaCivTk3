package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.ItemPheromone;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class A_Transformer extends Action{
	
	Objet objet;
	ItemPheromone item;

	public Action effectuer(Humain h)
	{
		if(h.smell(item.getNom()) > 0)
		{
			int nombre = (int) h.smell(item.getNom());
			h.getPheromone(item.getNom()).incValue(h.xcor(), h.ycor(),-h.smell(item.getNom()));
			h.getInventaire().addObjets(objet, nombre);
		}
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);

		if (option.getParametres().get(0).getClass() == ItemPheromone.class) {
			item = (ItemPheromone) option.getParametres().get(0);
		} else if (option.getParametres().get(0).getClass() == Objet.class) {
			objet = (Objet) option.getParametres().get(0);
			//System.out.println(objet);
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
			String[] attrName = {"**Pheromone**" , "Pheromone to collect"};
			String[] n = {"**Objet**" , "Created Object"};

			schemaParametres.add(attrName);
			schemaParametres.add(n);

		}
		return schemaParametres;	
	}
}
