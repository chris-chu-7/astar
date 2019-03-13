//Christopher Chu Github: wanderingessence
//Date: 3/12/2018 
//Duration: 3 days to complete.

//This algorithm implementation partially solves the A* Problem with Flaws

//The algorithm is given by a "robot", denoted by a red square and a "destination of treasure", denoted
//by the green square and attempts to find the most efficient way for the robot to find the treasure
//by using A* with the minimum distance to the goal heuristic. The heuristic is partially effective, but 
//not 100%, as DFS was not performed in this implementation.

//Algorithm runs in O(n^2) and takes O(n^2) space. O(n^2) to iterate through all the neighbors, label
//each squares. We also find the path in the O(n^2) array. 



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
	static int infinitechecker;
	
	
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
				
				g.fillRect(i * 50, j * 50, map.length * 50, map[i].length *50);
				
			}
		}
		
		if(startgood) {
			g.setColor(Color.RED);
			g.fillRect(startx*50, starty  * 50, 50, 50);
			orig = startx;
			origy = starty;
		}
		
		if(endgood) {
			g.setColor(Color.GREEN);
			g.fillRect(endx * 50, endy  * 50, 50, 50);
		}
		
		if(placed) {
			
			marked[startx][starty] = true;
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
			g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
			g.drawString("Solving...", map.length * 50 / 2, map.length * 50 / 2 );
			System.out.println("Startx: " + startx + "  Starty: " + starty);
			System.out.println("Endx: " + endx + "  Endy: " + endy);
			
		//	marked[startx][starty] = true;
			while((startx != endx) && (starty != endy)) {
				infinitechecker++;
				if(infinitechecker == 20) {
					System.out.println("There is no possible path found for this. :(");
					g.setColor(Color.PINK);
					g.fillRect(minx * 50, miny  * 50, 50, 50);
					break;
				}
				
				for(int i = -1; i <= 1; i += 1) {
					for(int j = -1; j <= 1; j += 1) {
						if(((i == 0) && (j == 0)) || (marked[startx][starty])) {continue;} 
						
						else if(map[startx + i][starty + j] == 0) {
							//g.setColor(Color.BLUE);
							//g.fillRect(startx*50 + 50 * i, starty  * 50 + 50 * j, 50, 50);
							g.setColor(Color.YELLOW);
							g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
							double startdis = Math.round(computeDistance(startx, starty, startx + i, starty + j) * 500.0)/500.0;
							double enddis = Math.round(computeDistance(startx + i, starty + j, endx, endy) * 500.0)/500.0;
							double distance = Math.round((startdis + enddis )*500.0)/500.0;
							if(i == 0 && j == 0) {
								distance = 1000000000;
							}
							String distancetoString = distance + "";
							//g.drawString(distancetoString, startx*50 + 50 * i + 250, starty  * 50 + 50 * j + 350);
							if(distance <= mindis ) {
								mindis = distance;
								minx = startx + i;
								miny = starty + j;
						
							}
							
							//else set mindis to the minimum of the neighbors
						} else {}
							
					}
				}
				
				
				
				//now you must label the shortest nodes into variables 
				g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
				g.drawString("Solved!!!!  :D", map.length * 500 / 2, map.length * 500 / 2 );
				
				//timeDelay(500);
				
				g.setColor(Color.ORANGE);
				g.setColor(new Color(148, 0, 211));
				g.fillRect(minx*50, miny  * 50, 50, 50);
				startx = minx;
				starty = miny;
				//marked[startx][starty] = true;
				System.out.println(mindis);
				System.out.println("minx: " + minx + " miny: " + miny);
								
			}
			
			g.setColor(Color.RED);
			g.fillRect(orig * 50, origy  * 50, 50, 50);
			
			g.setColor(Color.GREEN);
			g.fillRect(endx * 50, endy  * 50, 50, 50);
			
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
		f.setSize(length * 50, width * 50);
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
			startx = e.getX()/50;
			starty = e.getY()/50;
			placed = startgood && endgood;
			repaint();
		}
		
		if (SwingUtilities.isRightMouseButton(e)) {
			endgood = true;
			endx = e.getX()/50;
			endy = e.getY()/50;
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