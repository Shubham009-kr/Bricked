package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BricksDesign {
	public int map[][];
	public int bWidth;
	public int bHeight;
	public BricksDesign(int row, int col) {
		map = new int[row][col];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j]=1; // Here 1 represts the visibility of bricks. If its equals to 1 simply the bricks will be visible and 
			}                // 0 for invisible.
		}
		bWidth = 540/col;
		bHeight = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j]>0) {
					g.setColor(Color.yellow);
					g.fillRect(j*bWidth + 80, i*bHeight+50, bWidth, bHeight);  // Draws bricks.
					// Upto here there will be only one large brick. We have to re-paint the brick by applying stroke to it 
					// so as to differentiate the bricks.
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*bWidth + 80, i*bHeight+50, bWidth, bHeight);  // Draws inner stroke of bricks.
					}
			}
		}
	}
	
	public void brickDisplay(int val, int row, int col) {
		map[row][col]= val; 
	}
	
	


}
