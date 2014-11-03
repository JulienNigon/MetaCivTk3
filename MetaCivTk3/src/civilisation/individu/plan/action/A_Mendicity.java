package civilisation.individu.plan.action;

import java.util.ArrayList;
import java.util.List;

import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.individu.cognitons.TypeCogniton;
import civilisation.inventaire.Objet;
import civilisation.world.World;

public class A_Mendicity extends Action{

	Integer turns;
	
	Objet itemToTake;
	Integer nItemToTake;
	
	String myTag;
	
	TypeCogniton targetCogniton;
	TypeCogniton improvedCogniton;
	Double nImprovedCogniton;
	
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
		
		List<Humain> humains = h.humansHereWithCogniton(targetCogniton);
		
		//Do the trade
		if (!humains.isEmpty()) {
						
			Humain target = humains.get((int) Math.floor(humains.size() * Math.random()));
			
			//Change my inventory
			for (int i = 0 ; i < nItemToTake ; i++) {
				h.getInventaire().addObjets(itemToTake,1);
			}
			
			//Change target inventory & mind
			for (int i = 0 ; i < nItemToTake ; i++) {
				target.getInventaire().deleteObjets(itemToTake,1);
			}
			target.getEsprit().addWeightToCogniton(this.improvedCogniton, this.nImprovedCogniton);
			
			//Remove the tags
			h.getEsprit().cleanActionData(this);
			h.getEsprit().removeTag(myTag);
			
			//Result if success
			return listeActions.get(0);
			
		}
		else if (m.getActionData(this).equals(0)) {
			h.getEsprit().cleanActionData(this);
			h.getEsprit().removeTag(myTag);
			return listeActions.get(1);
		}
		else {
			Humain cible = h.getNearestTurtleOf(World.getInstance().getHumansWithCogniton(targetCogniton));
			if (cible != null && cible != h) {
				//h.allerVers(cible);
				h.setHeadingTowards(cible);
				h.fd(1);
			} else {
				h.setHeading(Math.random()*360.);
				h.fd(1);
			}
			return this;		}
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
			}
			 else if (option.getName().equals("nItemToTake")) {
				nItemToTake = (Integer) option.getParametres().get(0);
			}
		}
		else if (option.getParametres().get(0).getClass() == String.class) {
			if (option.getName().equals("myTag")) {
				myTag = (String) option.getParametres().get(0);
			}
		}
		else if (option.getParametres().get(0).getClass().equals(Objet.class)) {
			if (option.getName().equals("itemToTake")) {
				itemToTake = (Objet) option.getParametres().get(0);
			}
		}
		else if (option.getParametres().get(0).getClass().equals(Double.class)) {
			if (option.getName().equals("nImprovedCogniton")) {
				nImprovedCogniton = (Double) option.getParametres().get(0);
			}
		}
		else if (option.getParametres().get(0).getClass().equals(TypeCogniton.class)){
			if (option.getName().equals("targetCogniton")) {
				targetCogniton = (TypeCogniton) option.getParametres().get(0);
			} else if (option.getName().equals("improvedCogniton")) {
				improvedCogniton = (TypeCogniton) option.getParametres().get(0);
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
			
			String[] itemToTake = {"**Objet**" , "itemToTake"};
			String[] nItemToTake = {"**Integer**" , "nItemToTake", "0" , "100" , "1"};

			String[] myTag = {"**String**","myTag"};
			String[] targetCog = {"**Cogniton**" , "targetCogniton"};
			String[] improvedCog = {"**Cogniton**" , "improvedCogniton"};
			String[] nImprovedCogniton = {"**Double**" , "nImprovedCogniton", "-10.0" , "10.0" , "0.1","100"};

			schemaParametres.add(turns);

			schemaParametres.add(itemToTake);
			schemaParametres.add(nItemToTake);
			schemaParametres.add(myTag);
			schemaParametres.add(targetCog);
			schemaParametres.add(improvedCog);
			schemaParametres.add(nImprovedCogniton);

		}
		return schemaParametres;	
	}
	
	public boolean internActionsAreLinked() {
		return false;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " Seek an agent willing to give some specific items.<html>";
	}

}
