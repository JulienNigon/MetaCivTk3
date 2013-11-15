package civilisation.individu.plan;

import civilisation.Communaute;
import civilisation.Configuration;
import civilisation.individu.Esprit;
import civilisation.individu.Humain;
import civilisation.inventaire.Objet_Or;
import edu.turtlekit2.kernel.agents.Turtle;
import edu.turtlekit2.kernel.environment.Patch;

public class PLANGR_FonderVille extends GroupPlan{

	
	public PLANGR_FonderVille(Esprit e, Humain h, Object target, Humain leader) {
		super(e,h,target,leader);
	}

	public String getNom(){
		return "FonderVille";
	}
	
	public int getPoidsInfluence(){
		return 50;
	}
	
	public void Activer()
	{
		
		if(leader == null){
			activerLeader();
		}
		else{		
			activerFollower();
		}
	}
	
	public void activerLeader(){
		if (ordreDonne)
		{

			h.move(1);
			if (h.CommunauteInRadius(Configuration.distanceEntreVilles).size() <= 0 && h.humainsFromCiv(h.HumaininRadius(2) , h.getCiv()).size() >= Configuration.humainsPourFonderVille){
				
				Communaute c = new Communaute(h.getCiv());
				h.createTurtle(c);
				
				e.finProjet();
				h.migrer(c);

			}
		}
		else
		{
			if (h.isHere(h.getCommunaute())){
				ordreDonne = true;
				if (h.emettreInfluence(this)){
					e.setTimer(this.getTempsMax());
					h.randomHeading();
				}
				else
				{
					e.finProjet();
				}
			}
			else{
				h.rentrer();
			}
		}

	}
	
	public void activerFollower(){
		if (leader != null)
		{
			if (leader.CommunauteInRadius(1).size() == 1){
				h.migrer(h.CommunauteInRadius(3).get(0));
				e.finProjet();
			}
			

			if(!leader.isHere(h)){
				h.face(leader);
				h.fd(1);
			}
		}

		else
		{

			e.finProjet();
		}

	}
	

	public int getTempsMax() {
		return 100;
	}
}
