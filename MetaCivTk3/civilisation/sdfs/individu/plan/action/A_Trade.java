package civilisation.individu.plan.action;

import java.util.ArrayList;
import java.util.List;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;

public class A_Trade extends Action{

	Integer turns;
	
	Objet itemToGive;
	Integer nItemToGive;
	
	Objet itemToTake;
	Integer nItemToTake;
	
	String myTag;
	String compatibleTag;

	
	public Action effectuer(Humain h) {
		Esprit m = h.getEsprit();
		
		//Number of turns
		if (m.getActionData(this) == null) {
			h.getEsprit().addTag(myTag);
			m.setActionData(this, turns - 1);
		} else {
			if (m.ownTag(myTag)) {
				m.setActionData(this, (Integer)m.getActionData(this) - 1);
			} else {
				h.getEsprit().cleanActionData(this);
				return nextAction;
			}
		}
		
		List<Humain> humains = h.humansHereWithTag(compatibleTag);
		
		//Do the trade
		if (!humains.isEmpty()) {
						
			Humain target = humains.get((int) Math.floor(humains.size() * Math.random()));
			
			//Change my inventory
			for (int i = 0 ; i < nItemToGive ; i++) {
				h.getInventaire().deleteObjets(itemToGive,1);
			}
			for (int i = 0 ; i < nItemToTake ; i++) {
				h.getInventaire().addObjets(itemToTake,1);
			}
			
			//Change target inventory
			for (int i = 0 ; i < nItemToGive ; i++) {
				target.getInventaire().addObjets(itemToGive,1);
			}
			for (int i = 0 ; i < nItemToTake ; i++) {
				target.getInventaire().deleteObjets(itemToTake,1);
			}
			
			//Remove the tags
			h.getEsprit().cleanActionData(this);
			h.getEsprit().removeTag(myTag);
			target.getEsprit().removeTag(compatibleTag);
			return listeActions.get(0);
			
		}
		else if (m.getActionData(this).equals(0)) {
			h.getEsprit().cleanActionData(this);
			h.getEsprit().removeTag(myTag);
			return listeActions.get(1);
		}
		else {
			return this;
		}
	}
	
	@Override
	public int getNumberActionSlot(){
		return 2;
	}
	
	@Override
	public void parametrerOption(OptionsActions option){
		super.parametrerOption(option);

		if (option.getParametres().get(0).getClass() == Integer.class) {
			if (option.getName().equals("turns")) {
				turns = (Integer) option.getParametres().get(0);
			} else if (option.getName().equals("nItemToGive")) {
				nItemToGive = (Integer) option.getParametres().get(0);
			} else if (option.getName().equals("nItemToTake")) {
				nItemToTake = (Integer) option.getParametres().get(0);
			}
		}
		else if (option.getParametres().get(0).getClass() == String.class) {
			if (option.getName().equals("myTag")) {
				myTag = (String) option.getParametres().get(0);
			} else if (option.getName().equals("compatibleTag")) {
				compatibleTag = (String) option.getParametres().get(0);
			}
		}
		else if (option.getParametres().get(0).getClass().equals(Objet.class)) {
			if (option.getName().equals("itemToGive")) {
				itemToGive = (Objet) option.getParametres().get(0);
			} else if (option.getName().equals("itemToTake")) {
				itemToTake = (Objet) option.getParametres().get(0);
			}
		}

	}
	
	@Override
	public ArrayList<String[]> getSchemaParametres(){
		/*
		Integer turns;
		
		String itemToGive;
		Integer nItemToGive;
		
		String itemToTake;
		Integer nItemToTake;
		
		String myTag;
		String compatibleTag;
		*/
		
		if (schemaParametres == null){
			schemaParametres = new ArrayList<String[]>();
			
			String[] turns = {"**Integer**" , "turns", "0" , "100" , "1"};
			
			String[] itemToGive = {"**Objet**" , "itemToGive"};
			String[] nItemToGive = {"**Integer**" , "nItemToGive", "0" , "100" , "1"};

			String[] itemToTake = {"**Objet**" , "itemToTake"};
			String[] nItemToTake = {"**Integer**" , "nItemToTake", "0" , "100" , "1"};

			String[] myTag = {"**String**","myTag"};
			String[] compatibleTag = {"**String**","compatibleTag"};

			schemaParametres.add(turns);
			schemaParametres.add(itemToGive);
			schemaParametres.add(nItemToGive);
			schemaParametres.add(itemToTake);
			schemaParametres.add(nItemToTake);
			schemaParametres.add(myTag);
			schemaParametres.add(compatibleTag);


		}
		return schemaParametres;	
	}
	
	public boolean internActionsAreLinked() {
		return false;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Change the current value of an attribute.<html>";
	}

}
