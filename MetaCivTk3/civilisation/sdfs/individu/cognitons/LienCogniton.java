package civilisation.individu.cognitons;

public class LienCogniton {

	TypeCogniton c;
	int poids;
	
	public LienCogniton(TypeCogniton c , int poids){
		this.poids = poids;
		this.c = c;
	}
	
	@Override
	public String toString(){
		return "Lien : " + c.getNom() + " " + poids;
	}

	public TypeCogniton getC() {
		return c;
	}

	public void setC(TypeCogniton c) {
		this.c = c;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}
	
	
}
