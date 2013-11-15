package civilisation.inspecteur.animations;

public class JJAnimationOpacite extends JJAnimation{

	
	double dOpacite;
	
	public JJAnimationOpacite(int pas , JJComponent compo , double dOpacite, boolean vieLimite) {
		super(pas,compo,vieLimite);
		this.dOpacite = dOpacite;
	}
	
	@Override
	public boolean animer(){

		compo.setOpacite(compo.getOpacite()+dOpacite);

		return super.animer();
	}	
	
}
