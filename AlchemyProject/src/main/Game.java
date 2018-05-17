package main;

import java.awt.Color;
import java.awt.Component;
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
	public static final double screenMultiplierForImages = .05;
	public static final int lineWidth = 10;
	public static final double lineDistanceMultiplier = .9;
	public static final boolean leftSideInventory = true;
	
	public Game()
	{
		Pictures.contentPaneSize = new Dimension(500, 500);
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
						if((frame.getContentPane().getSize().height != 0) && 
								frame.getContentPane().getSize().width != 0)
						{
							Pictures.contentPaneSize.setSize(frame.getContentPane().getSize());
						}
						Pictures.setScaledIcons(smartSize(ev.getComponent()), smartSize(ev.getComponent()));
						frame.revalidate();
						frame.repaint();
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
	
	public int smartSize(Component input)
	{
		double width = frame.getContentPane().getSize().width;
		double height = frame.getContentPane().getSize().height;
		
		double added = screenMultiplierForImages *(width + height);
		int average = (int)((added / 2) + .5);
		
		
		return average;
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
