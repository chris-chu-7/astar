import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.ArrayList;

public class algo extends JPanel
implements MouseListener, MouseMotionListener
{
	  public algo() {
	        this.addMouseListener(this);
	    }
	
	static ArrayList<Integer> neighbors = new ArrayList<Integer>();
	static int[][] map;
	static boolean[][] marked;
	static boolean startgood = false;
	static boolean endgood = false;
	static boolean placed = startgood && endgood;
	static int startx, starty, endx, endy;
	static double mindis = 1000000000;
	static int minx, miny;
	static int orig;
	static int origy;
	
	
	public void timeDelay(long t) {
	    try {
	        Thread.sleep(t);
	    } catch (InterruptedException e) {}
	}
	
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		//draw a for loop to print the map
		for (int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				g.setColor(Color.WHITE);
				
				if(map[i][j] == 1) {
					g.setColor(Color.BLACK);
				}
				
				g.fillRect(i * 60, j * 60, map.length * 60, map[i].length *60);
				
			}
		}
		
		if(startgood) {
			g.setColor(Color.RED);
			g.fillRect(startx*60, starty  * 60, 60, 60);
			orig = startx;
			origy = starty;
		}
		
		if(endgood) {
			g.setColor(Color.GREEN);
			g.fillRect(endx * 60, endy  * 60, 60, 60);
		}
		
		if(placed) {
			
			
			mindis = 1000000000;
			
			//neighbors.add(startx);
			//neighbors.add(starty);
			
			g.setColor(Color.BLUE);	
			
			for(int i = 0; i < marked.length; i++) {
				for(int j = 0; j < marked[i].length; j++) {
					marked[i][j] = false;
				}
			}
//			
			g.setColor(Color.CYAN);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 60)); 
			g.drawString("Solving...", map.length * 20 / 2, map.length * 20 / 2 );
			System.out.println("Startx: " + startx + "  Starty: " + starty);
			System.out.println("Endx: " + endx + "  Endy: " + endy);
			
			marked[startx][starty] = true;
			while((startx != endx) && (starty != endy)) {
				
				for(int i = -1; i <= 1; i += 1) {
					for(int j = -1; j <= 1; j += 1) {
						if(((i == 0) && (j == 0))) {continue;} 
						
						else if(map[startx + i][starty + j] == 0) {
							//g.setColor(Color.BLUE);
							//g.fillRect(startx*60 + 60 * i, starty  * 60 + 60 * j, 60, 60);
							g.setColor(Color.YELLOW);
							g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
							double startdis = Math.round(computeDistance(startx, starty, startx + i, starty + j) * 100.0)/100.0;
							double enddis = Math.round(computeDistance(startx + i, starty + j, endx, endy) * 100.0)/100.0;
							double distance = Math.round((startdis + enddis )*100.0)/100.0;
							if(i == 0 && j == 0) {
								distance = 1000000000;
							}
							String distancetoString = distance + "";
							g.drawString(distancetoString, startx*60 + 60 * i + 25, starty  * 60 + 60 * j + 35);
							if(distance < mindis ) {
								mindis = distance;
								minx = startx + i;
								miny = starty + j;
						
							}
						} else {}
							
					}
				}
				
				
				
				//now you must label the shortest nodes into variables 
				g.setFont(new Font("TimesRoman", Font.PLAIN, 60)); 
				g.drawString("Solved!!!!  :D", map.length * 50 / 2, map.length * 50 / 2 );
				
				timeDelay(100);
				
				g.setColor(Color.ORANGE);
				g.setColor(new Color(148, 0, 211));
				g.fillRect(minx*60, miny  * 60, 60, 60);
				startx = minx;
				starty = miny;
				marked[startx][starty] = true;
				System.out.println(mindis);
				System.out.println("minx: " + minx + " miny: " + miny);
								
			}
			
			g.setColor(Color.RED);
			g.fillRect(orig * 60, origy  * 60, 60, 60);
			
			g.setColor(Color.GREEN);
			g.fillRect(endx * 60, endy  * 60, 60, 60);
			
		}
		
	}
	
	public static double computeDistance (double startx, double starty, double endx, double endy) {
		double solution = Math.sqrt(Math.pow(endx - startx, 2) + Math.pow(endy - starty, 2));
		return solution;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Welcome to the A* Shortest Pathfinding Robot Program \n *****"
				+ "**************************"
				+ "********************\n");
		System.out.println("How large would you like your graph to be? Enter 2 consecutive numbers, one for length, one for width:\n");
		Scanner sizeScan = new Scanner(System.in);
		int length = sizeScan.nextInt();
		int width = sizeScan.nextInt();
		map = new int[length][width];
		marked = new boolean[length][width];
		Random gridGenerate = new Random();
		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = gridGenerate.nextInt(2);
			}
		}
		JFrame f = new JFrame("A Star Pathfinder");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		algo star = new algo();
		f.add(star);
		f.setSize(length * 60, width * 60);
		f.setVisible(true);
		
		System.out.println("Left Click To specify the Start Node, Right Click to Specify the End Node.");
		
		System.out.println("Continue? (y/n)");
		Scanner question = new Scanner(System.in);
		int answer = question.nextInt();
		System.out.println(answer);
		if(answer == 1) {
			System.out.println("this stuff works!!!");
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (SwingUtilities.isLeftMouseButton(e)) {
			startgood = true;
			startx = e.getX()/60;
			starty = e.getY()/60;
			placed = startgood && endgood;
			repaint();
		}
		
		if (SwingUtilities.isRightMouseButton(e)) {
			endgood = true;
			endx = e.getX()/60;
			endy = e.getY()/60;
			placed = startgood && endgood;
			repaint();
		}

		
		}
		
	

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	
}