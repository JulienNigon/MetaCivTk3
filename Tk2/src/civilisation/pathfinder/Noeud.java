package civilisation.pathfinder;

public class Noeud {
	
	int posX;
	int posY;
	int distanceRacine;
	int parent;
	int id;
	public Noeud(int x,int y, int parent,int id)
	{
		this.posX = x;
		this.posY = y;
		this.parent = parent;
		this.id = id;
	}
	public Noeud(int x,int y,int id)
	{
		this.posX = x;
		this.posY = y;
		this.id = id;
	}
	public Noeud(int x,int y)
	{
		this.posX = x;
		this.posY = y;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getDistanceRacine() {
		return distanceRacine;
	}
	public void setDistanceRacine(int distanceRacine) {
		this.distanceRacine = distanceRacine;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getId() {
		return id;
	}
	
}
