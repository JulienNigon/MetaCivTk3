package civilisation.individu.plan.action;

import java.util.ArrayList;

import civilisation.individu.Humain;
import civilisation.inventaire.Objet;

public class A_CreateObject extends Action {
	
	Objet objet;
	
	public Action effectuer(Humain h)
	{
		if(objet.getRecette().size() > 0)
		{
			boolean test = true;
			for(int i = 0; i < objet.getRecette().size(); ++i)
			{
				if(h.getInventaire().possede(objet.getRecette().get(i)) < objet.getNombre().get(i) )
				{
					test = false;
				}
			}
			
			if(!test)
			{
				for(int i = 0; i < objet.getRecette().size(); ++i)
				{
					h.getInventaire().deleteObjets(objet.getRecette().get(i), objet.getNombre().get(i));
				}
			}
		}
		else
		{
			h.getInventaire().addObjets(objet, 1);
		}
		
		return nextAction;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);

		if (option.getParametres().get(0).getClass() == Objet.class) {
			objet = (Objet) option.getParametres().get(0);
		//	System.out.println(objet);
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
			String[] attrName = {"**Objet**" , "Created object"};

			schemaParametres.add(attrName);

		}
		return schemaParametres;	
	}

}
