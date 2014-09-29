package civilisation.amenagement;

import civilisation.individu.Humain;
import turtlekit.kernel.Patch;

public class Mark {

	Patch patch;
	Humain owner;
	
	
	
	
	
	public Mark(Patch patch, Humain owner) {
		super();
		this.patch = patch;
		this.owner = owner;
	}
	
	
	
	public Patch getPatch() {
		return patch;
	}
	public void setPatch(Patch patch) {
		this.patch = patch;
	}
	public Humain getOwner() {
		return owner;
	}
	public void setOwner(Humain owner) {
		this.owner = owner;
	}
	
}
