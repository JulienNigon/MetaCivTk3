package civilisation.inspecteur.animations;

public class JJAnimationTranslationEntreDeuxObjets extends JJAnimation{

	int pasInitial;
	JJComponent a;
	JJComponent b;
	
	public JJAnimationTranslationEntreDeuxObjets(int pas , JJComponent compo , JJComponent a, JJComponent b, boolean vieLimite) {
		super(pas,compo,vieLimite);
		this.a = a;
		this.b = b;
		pasInitial = pas;
	}
	
	@Override
	public boolean animer(){

		compo.setXx(a.getCentreX() + (((pasInitial-pas)/(double)pasInitial)*(b.getCentreX() - a.getCentreX())) - (compo.getW()/2.));
		compo.setYy(a.getCentreY() + (((pasInitial-pas)/(double)pasInitial)*(b.getCentreY() - a.getCentreY())) - (compo.getH()/2.));

		//compo.setXx((a.getXx() + b.getXx())/2);
		//compo.setYy((a.getYy() + b.getYy())/2);

		
		return super.animer();
	}	

	
}
