package civilisation.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;

import javax.swing.JPanel;

import turtlekit.kernel.Patch;
import turtlekit.kernel.Turtle;

/**
 * A minimalist world viewer
 * (Inspired by the code from TKDefaultViewer)
 * Require the WorldViewer
 * @author Julien Nigon
 *
 */
public class PanelMicroWorldViewer extends JPanel{
	
	World world;
	int cellSize = 5;
	int radius = 22;
	Turtle tur;
	
	public PanelMicroWorldViewer(Turtle tur) {
		this.tur = tur;
		world = world.getInstance();
		//this.setPreferredSize(new Dimension(50,500));
		this.setPreferredSize(new Dimension(2*radius*cellSize,4*radius*cellSize/*radius*cellSize*/));
	}

	public void paint(Graphics g) {
		try {
			int index = 0;
			int offsetY = 0;
			int offsetX = 0;
			int targetY = tur.ycor();
			int targetX = tur.xcor();
			int height = WorldViewer.getInstance().getHeight();
			//height = 0;
			final Patch[] grid = WorldViewer.getInstance().getPatchGrid();
				final int w = WorldViewer.getInstance().getWidth();
				for (int j = WorldViewer.getInstance().getHeight() - 1; j >= 0; j--) {
					if (targetY - radius <= j && targetY + radius >= j) {
						//System.out.println("Y valide");
						offsetX = 0;
						for (int i = 0; i < w; i++) {
							if (targetX - radius <= i && targetX + radius >= i) {
								//System.out.println("\tX valide +"+(i+offsetX)+" Y:"+(j+offsetY));
								final Patch p = world.getPatchAt(i,j);
								if (p.isEmpty()) {
									paintPatch(g, p, (i+offsetX) * cellSize, (int) ((j+offsetY-height) * cellSize + (1.5 * height)), index);
								} 
								else {
										try {
											paintTurtle(g, p.getTurtles().get(0), (i+offsetX) * cellSize, (j+offsetY-height) * cellSize +height);
										} catch (NullPointerException | IndexOutOfBoundsException e) {//for the asynchronous mode
										}
									}
								index++;
							}
							else
							{
								//System.out.println("X:"+offsetX);
								offsetX--;
								index++;
							}
						}
					}
					else {
						//System.out.println("Y:"+offsetY);
						offsetY++;
						index++;
					}
			}
		} catch (ConcurrentModificationException e) {
		}
		g.setColor(Color.RED);
		g.drawLine(getWidth()*cellSize, getHeight()*cellSize, 0, getHeight()*cellSize);
		g.drawLine(getWidth()*cellSize, getHeight()*cellSize, getWidth()*cellSize, 0);
	}

	public void paintTurtle(final Graphics g, final Turtle t, final int i, final int j) {
		if (t.isPlayingRole("Humain")) {
			g.setColor(t.getColor());
			if (t.getPatch().equals(tur.getPatch())) g.setColor(Color.RED);
			g.fillRect(i , j , cellSize, cellSize);
		}

	}

	public void paintPatch(final Graphics g, final Patch p, final int x, final int y, final int index) {
		final Color c = p.getColor();
		System.out.println(x + " " + y);
		if (c != Color.BLACK) {
			g.setColor(c);
			g.fillRect(x , y , cellSize, cellSize);
		}
	}
	
}
