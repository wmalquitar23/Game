import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.ArrayList;

public class World extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer gameTimer;
	boolean goUp;
	boolean goDown;
	boolean goLeft;
	boolean goRight;
	boolean isEaten;
	boolean isFoodExists;
	ArrayList<Point> pos;
	int headX;
	int headY;
	int oldX;
	int oldY;
	int currBody;
	
	int foodLocX;
	int foodLocY;
	
	int counter = 0;
	
	boolean stopDrawGrid;
	
	public World(String progName)
	{
		super(progName);
		currBody = 1;
		goUp = false;
		goDown = true;
		goLeft = false;
		goRight = false;
		isEaten = false;
		isFoodExists = true;
		
		stopDrawGrid = true;
		
		pos = new ArrayList<Point>();
		pos.add(new Point(260, 260));
		pos.add(new Point(260, 240));
		pos.add(new Point(260, 220));
		pos.add(new Point(260, 200));
		
		gameTimer = new Timer(50, new TimerRepaintListener());
		gameTimer.start();
		this.addKeyListener(new MyKeyBoardListener());
		setFoodLocation();
		//paintGrid(new Graphics());
	}
	
	private class MyKeyBoardListener implements KeyListener
	{
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			char key = e.getKeyChar();
			if ((key == 'w') || (key == 'W'))
			{
				setAllToFalse();
				goUp = true;
			}
			else if ((key == 's') || (key == 'S'))
			{
				setAllToFalse();
				goDown = true;
			}
			else if ((key == 'a') || (key == 'A'))
			{
				setAllToFalse();
				goLeft = true;
			}
			else if ((key == 'd') || (key == 'D'))
			{
				setAllToFalse();
				goRight = true; 
			}
			else if ((key == 'p') || (key == 'P'))
				gameTimer.stop();
			else if ((key == 'r') || (key == 'R'))
				gameTimer.start();
			
			
			System.out.println(key);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		public void setAllToFalse()
		{
			goLeft = false;
			goRight = false;
			goUp = false;
			goDown = false;
		}
	}
	
	private class TimerRepaintListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
		}
	}
	
	public void paintGrid(Graphics g)
	{
		for (int point = 0;point<=1500;	point += 20)
		{
			g.drawLine(0,point,1500,point);
			g.drawLine(point,0,point,1500);
		}	
	}
	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		
		//getting the head positions
		headX = pos.get(0).GetX();
		headY = pos.get(0).GetY();
		oldX = headX;
		oldY = headY;
		
		//g.setColor(new Color(238,238,238));
		//print grid
		
		if (stopDrawGrid  == true)
		{
			for (int point = 0;point<=1500;	point += 20)
			{
				g.drawLine(0,point,1500,point);
				g.drawLine(point,0,point,1500);
				//System.out.println("hello");
			}
		}
		stopDrawGrid = false;
		//printing position of snake's body
		for(Point p : pos)
		{
			//g.setColor(Color.black);
			g.setColor(Color.blue);
			g.drawRect(p.GetX(), p.GetY(), 20, 20);
			g.fillRect(p.GetX(), p.GetY(), 20, 20);
		}
		
		//setting of new body positions
		for(int i = 1; i < pos.size() ; i++)
		{
			pos.get(currBody).SetXY(oldX, oldY);
		}
		if (currBody != pos.size() - 1)
			currBody++;
		else
			currBody = 1;
		
		
		if (goUp == true)
			headY -= 20;
		else if (goDown == true)
			headY += 20;
		else if (goLeft == true)
			headX -= 20;
		else if (goRight == true)
			headX += 20; 
		
		pos.get(0).SetXY(headX, headY); //set the new position of snake's  head

		//print food
		//if (isFoodExists)
		//{
			if (isEaten)
			{
				setFoodLocation();
				isEaten = false;
			}
			
			g.setColor(Color.red);
			g.fillRect(foodLocX, foodLocY, 20, 20);
			g.drawRect(foodLocX, foodLocY, 20, 20);
			//isFoodExists = false;
		//}
		
		//check if eaten.
		if (isFoodEaten())
		{
			isEaten = true;
			pos.add(new Point(pos.get(pos.size()-1).GetX(), pos.get(pos.size()-1).GetY()));
		}
	
	}
	
	public boolean isFoodEaten()
	{
		return ((pos.get(0).GetX() == foodLocX) && (pos.get(0).GetY() == foodLocY));
	}
	
	
	public void setFoodLocation()
	{
		foodLocX = GetRandomOnGrid(20,1);
		foodLocY = GetRandomOnGrid(20,1);
	}
	
	public int GetRandomOnGrid(int high, int low)
	{
		//setting of random location exactly within the grid.
		return (int)(Math.floor(Math.random() * (1 + high - low)) + low) * 20;
	}
	
	public static void main(String [] args)
	{
		World window = new World("Sample Movement");
																																												
		//System.out.println(window.getBackground());
		//window.getContentPane().add(new World());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(585,585);	
		window.setBackground( new Color(6,204,174));
		window.setVisible(true);
		window.setResizable(false);
	}

}
