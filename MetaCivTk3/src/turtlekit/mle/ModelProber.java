package turtlekit.mle;
import java.util.logging.Level;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Probe;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;
import turtlekit.agr.TKOrganization;


/**
 * Obsolete : not used right now
 * 
 * @author fab
 *
 */
public class ModelProber extends Watcher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3101174765431271425L;
	private String group;
	private PropertyProbe<AbstractAgent, Integer> nrj;
	private int level = 2;
	private int time = 0;

	public ModelProber(String group) {
		this(group,-1);
	}
	
	public ModelProber(String group, int level) {
		setLogLevel(Level.ALL);
		this.group = group;
		this.level = level;
	}
	
	@Override
	protected void activate() {
		for (int i = 0; i < level; i++) {
			final int lvl = i;
			addProbe(new PropertyProbe<AbstractAgent, Integer>(TKOrganization.TK_COMMUNITY, group, ""+lvl, "nrj"){
				@Override
				public String toString() {
					return "LEVEL "+lvl+" -- "+size()+" agents, max nrj = "+getMaxValue();
				}
			});
		}
		setLogLevel(Level.FINEST);
		super.activate();
		requestRole(TKOrganization.TK_COMMUNITY, group, TKOrganization.VIEWER_ROLE);
	}

	public void observe(){
		time ++;
		if (time % 50 == 0) {
			System.err.println("----------\n\n");
			for (Probe<? extends AbstractAgent> p : allProbes()) {
				if (logger != null)
					logger.info(p.toString());
			}
		}
	}

}
