package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Design extends JPanel implements ActionListener, KeyListener {
	
	private boolean play = false; // our game should not start automatically just after opening.
	private int score=0;
	
	private int TBricks=21; // total no. of bricks.
	
	private int sliderX=310; // starting pos. of slider w.r.t. x-axis.
	
	private int ballX=120; // starting pos. of ball w.r.t. x-axis. 
	private int ballY=350; // starting pos. of ball w.r.t. x-axis.
	private int ballDirectionX=-1;
	private int ballDirectionY=-2;
	
	private Timer timer; // for setting the speed of ball. 
	private int delay= 6;
	
	private BricksDesign map;
	
	public Design() {
		map=new BricksDesign(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		// BG
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// Drawing Bricks
		map.draw((Graphics2D)g);	
		
		// Scores
		g.setColor(Color.red);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);
		
		
		// Outer Border
		g.setColor(Color.red);
		g.fillRect(0, 0, 3, 592); // Left Border
		g.fillRect(0, 0, 692, 3); // Upper Border
		g.fillRect(691, 0, 3, 592); // Right Border
		
		// Slider
		g.setColor(Color.cyan);
		g.fillRect(sliderX, 550, 100, 8);
		
		// Ball
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
		
		if(TBricks<=0) {     // After finishing the game what will happen.
			play=false;
			ballDirectionX=0;
			ballDirectionY=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif" , Font.BOLD, 40));
			g.drawString("You WON ", 240, 300);
			g.setFont(new Font("serif" , Font.BOLD, 25));
			g.drawString("Press Enter to restart. ", 200, 350);
		}
		
		if(ballY>570) {     // What will happen after if the ball falls below the slider.
			play=false;
			ballDirectionX=0;
			ballDirectionY=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif" , Font.BOLD, 40));
			g.drawString("GAME OVER", 200, 300);
			g.setFont(new Font("serif" , Font.BOLD, 25));
			g.drawString("Press Enter to restart. ", 220, 350);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(sliderX, 550, 100, 8))) {
				ballDirectionY= -ballDirectionY;
			}
			
			A: for(int i=0; i<map.map.length; i++) {  // "map.map" first map represents the object of BricksDesign class and second
				for(int j=0; j<map.map[0].length; j++) {  // map represents the array 'map'.
					if(map.map[i][j]>0) {
						int brickX= j*map.bWidth + 80;
						int brickY= i*map.bHeight + 50;
						int brickWidth= map.bWidth;
						int brickHeight= map.bHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brickRect =  rect;
						
						if(ballRect.intersects(brickRect)) {  // Action of collision of a ball with the bricks.
							map.brickDisplay(0, i, j);
							TBricks--;
							score+= 10;
							
							if(ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width ) {
								ballDirectionX= - ballDirectionX;
							}
							else
							{
								ballDirectionY= - ballDirectionY;
							}
							
							break A;
						}
					}
				}
			}
			
			ballX+=ballDirectionX;
			ballY+=ballDirectionY;
			if(ballX<0) {
				ballDirectionX= -ballDirectionX;
			} // for colliosion with left border.
			if(ballY<0) {
				ballDirectionY= -ballDirectionY;
			} // for colliosion with top border.
			if(ballX>670) {
				ballDirectionX= -ballDirectionX;
			} // for colliosion with right border.
		}
		repaint();   // after every move whole objects whatever appears on the screen re-draws.
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {      // To make keys work.
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {  
			if(sliderX>=600) {
				sliderX=600;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			if(sliderX<10) {
				sliderX=10;
			}
			else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballX=120;
				ballY=350;
				ballDirectionX=-1;
				ballDirectionY=-2;
				sliderX=310;
				score=0;
				TBricks=21;
				map= new BricksDesign(3,7);
				
				repaint();
			}
		}
	}
	
	public void moveRight() {
		play=true;
		sliderX+=20;
	}
	
	public void moveLeft() {
		play=true;
		sliderX-=20;
	}

}
