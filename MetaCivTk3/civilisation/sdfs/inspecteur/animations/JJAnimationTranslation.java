package civilisation.inspecteur.animations;

public class JJAnimationTranslation extends JJAnimation{

	double dx , dy;
	
	public JJAnimationTranslation(int pas , JJComponent compo , double dx , double dy, boolean vieLimite) {
		super(pas,compo,vieLimite);
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public boolean animer(){

		compo.setXx(compo.getXx() + dx);
		compo.setYy(compo.getYy() + dy);

		return super.animer();
	}	

}
