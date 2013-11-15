package civilisation.inspecteur.animations;

public abstract class JJAnimation {

	
	int pas;
	JJComponent compo;
	boolean vieLimite;
	
	/**
	 * @param pas , compo , vieLimite
	 */
	public JJAnimation(int pas , JJComponent compo, boolean vieLimite){
		this.pas = pas;
		this.compo = compo;
		this.vieLimite = vieLimite;
	}
	
	public boolean animer(){
		pas--;
		if (pas == 0){
			return false;
		}
		else
		{
			return true;
		}
	}

	public int getPas() {
		return pas;
	}

	public void setPas(int pas) {
		this.pas = pas;
	}

	public boolean isVieLimite() {
		return vieLimite;
	}

	public void setVieLimite(boolean vieLimite) {
		this.vieLimite = vieLimite;
	}
	
	public void decalerPas(int offset){
		pas += offset;
	}

	
	
}
