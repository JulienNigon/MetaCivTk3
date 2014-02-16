package civilisation.inspecteur.animations;

public class JJAnimationRotation extends JJAnimation{

	double angle;
	
	public JJAnimationRotation(int pas , JJComponent compo , double angle, boolean vieLimite) {
		super(pas,compo,vieLimite);
		this.angle = angle;
	}
	
	@Override
	public boolean animer(){

		compo.setTheta(compo.getTheta() + angle);



		return super.animer();
	}	

}
