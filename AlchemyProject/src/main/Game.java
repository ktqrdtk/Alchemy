package main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class Game
{
	public static void main(String... args)
	{
		new Game();
	}
	
	private JFrame frame;
	
	public Game()
	{
		setUpScreen();
		Pictures.setUp();
	}
	
	public void setUpScreen()
	{
		frame = new JFrame("Alchemy")
				{
					public void paint(Graphics g)
					{
						g.drawImage(Pictures.scaledIcons[0].getImage(), 0, 0, null);
					}
				};
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ComponentAdapter ca = new ComponentAdapter()
				{
					public void componentResized(ComponentEvent ev)
					{
						Pictures.setScaledIcons(smartSize(ev.getComponent()), smartSize(ev.getComponent()));
					}
				};
				
		frame.getRootPane().addComponentListener(ca);
		ca.componentResized(new ComponentEvent(frame.getRootPane() ,101));
		frame.pack();
		frame.repaint();
	}
	
	public int smartSize(Component input)
	{
		return 5;
	}
}
