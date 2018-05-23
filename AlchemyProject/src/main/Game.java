package main;

import java.awt.Color;
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
	public static final double lineDistanceMultiplier = .1;
	public static final boolean leftSideInventory = true;
	public static final int defaultSpacingSize = 2;
	public static final int defaultDistanceFromLine = defaultSpacingSize;
	
	public Game()
	{
		Pictures.setUp();
		setUpScreen();
	}
	
	public void setUpScreen()
	{
		frame = new MyJFrame("Alchemy");
		frame.setLayout(null);
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
		frame.getGlassPane().setPreferredSize(frame.getSize());
		Item[] tempArray = {new Item(frame, Pictures.FIRE, true), new Item(frame, Pictures.WATER, true), new Item(frame, Pictures.EARTH, true), new Item(frame, Pictures.AIR, true)};
		frame.initInventory(lineDistanceMultiplier, lineWidth, leftSideInventory, frame.getGlassPane().getPreferredSize(), tempArray);
	}
	
	public void glassPanePaint(Graphics g, JComponent glassPane)
	{
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
