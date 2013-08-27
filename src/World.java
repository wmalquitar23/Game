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
	ArrayList<Point> pos;
	int headX;
	int headY;
	int oldX;
	int oldY;
	int currBody;
	
	public World(String progName)
	{
		super(progName);
		currBody = 1;
		goUp = false;
		goDown = true;
		goLeft = false;
		goRight = false;
		isEaten = false;
		
		pos = new ArrayList<Point>();
		pos.add(new Point(260, 260));
		pos.add(new Point(260, 240));
		pos.add(new Point(260, 220));
		pos.add(new Point(260, 200));
		
		gameTimer = new Timer(100, new TimerRepaintListener());
		gameTimer.start();
		this.addKeyListener(new MyKeyBoardListener());
		//SpawnFood(g);
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
	
	public void paint(Graphics g)
	{
		headX = pos.get(0).GetX();
		headY = pos.get(0).GetY();
		oldX = headX;
		oldY = headY;
		super.paintComponents(g);
		//g.setColor(new Color(238,238,238));
		for (int point = 0;point<=1500;	point += 20)
		{
			g.drawLine(0,point,1500,point);
			g.drawLine(point,0,point,1500);
		}
		//printing position of body
		for(Point p : pos)
		{
			g.setColor(Color.black);
			g.drawRect(p.GetX(), p.GetY(), 20, 20);
			g.setColor(Color.blue);
			g.fillRect(p.GetX(), p.GetY(), 20, 20);
		}
		
		for(int i = 1; i < pos.size() ; i++)
		{
			pos.get(currBody).SetXY(oldX, oldY);
		}
		if (currBody != 3)
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
		
		pos.get(0).SetXY(headX, headY);

		//updating positions of body.
		
		/*for(Point p : pos)
		{
			//p.SetY(newPos);
			p.SetY(p.GetY() - 20);
			
		}*/
		if (isEaten)
			SpawnFood(g);
			//SpawnFood(g);
			//SpawnFood(g);
		//isEaten = false;
	}
	
	public void SpawnFood(Graphics g)
	{
		int x = GetRandomOnGrid(20,1);
		int y = GetRandomOnGrid(20,1);
		System.out.println(x + " " + y);
		g.setColor(Color.red);
		g.fillRect(x, y, 20, 20);
		g.drawRect(x, y, 20, 20);
	}
	
	public int GetRandomOnGrid(int high, int low)
	{
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
