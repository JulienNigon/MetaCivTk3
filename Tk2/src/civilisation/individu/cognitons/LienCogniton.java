package civilisation.individu.cognitons;

public class LienCogniton {

	NCogniton c;
	int poids;
	
	public LienCogniton(NCogniton c , int poids){
		this.poids = poids;
		this.c = c;
	}
	
	@Override
	public String toString(){
		return "Lien : " + c.getNom() + " " + poids;
	}

	public NCogniton getC() {
		return c;
	}

	public void setC(NCogniton c) {
		this.c = c;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}
	
	
}
