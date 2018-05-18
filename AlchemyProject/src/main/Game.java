package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class Game
{
	public static void main(String[] args)
	{
		new Game();
	}
	
	private MyJFrame frame;
	private JPanel mainPanel;
	public static final double screenMultiplierForImages = .001;
	public static final int lineWidth = 10;
	public static final double lineDistanceMultiplier = .9;
	public static final boolean leftSideInventory = false;
	
	public Game()
	{
		Pictures.setUp();
		setUpScreen();
	}
	
	public void setUpScreen()
	{
		frame = new MyJFrame("Alchemy");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel()
				{
					public void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						mainPanelPaint(g, this);
					}
				};
		mainPanel.setBackground(Color.YELLOW);
		
		ComponentAdapter coAd = new ComponentAdapter()
				{
					public void componentResized(ComponentEvent ev)
					{
						frame.needsToBeResized = true;
					}
				};
		frame.getRootPane().addComponentListener(coAd);
		frame.setGlassPane(new JComponent()
				{
					protected void paintComponent(Graphics g)
					{
						glassPanePaint(g, this);
					}
				});
		frame.setContentPane(new JComponent()
				{
					protected void paintComponent(Graphics g)
					{
						contentPanePaint(g, this);
					}
				});
		frame.pack();
		frame.repaint();
		frame.getGlassPane().setVisible(true);
		frame.setVisible(true);
		coAd.componentResized(new ComponentEvent(frame.getRootPane(), 101));
		frame.initInventory(lineDistanceMultiplier, lineWidth, leftSideInventory, frame.getGlassPane().getSize());
		frame.paintInventory();
	}
	
	public static int smartSize(Container input)
	{
		return smartSize(input.getSize());
	}
	
	public static int smartSize(Dimension input)
	{
		double squared = input.width * input.height;
		double scaled = squared * screenMultiplierForImages;
		double imageLength = Math.sqrt(scaled);
		
		return (int)(imageLength + .5);
	}
	
	public void glassPanePaint(Graphics g, JComponent glassPane)
	{
		g.drawImage(Pictures.scaledIcons[4].getImage(), 0, 0, null); //background
		g.setColor(Color.GRAY);
		g.fillRect((int) (glassPane.getWidth() * lineDistanceMultiplier), 0, lineWidth, glassPane.getHeight());//Line seperating panels, int x, int y, int width, int height
	}
	
	public void mainPanelPaint(Graphics g, JPanel panel)
	{
		
	}
	
	public void contentPanePaint(Graphics g, JComponent contentPane)
	{
		g.drawImage(Pictures.getBackgroundIcon().getImage(), 0, 0, contentPane);
	}
}
