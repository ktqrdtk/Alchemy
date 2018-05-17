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
	
	private JFrame frame;
	private JPanel mainPanel;
	
	public Game()
	{
		Pictures.contentPaneSize = new Dimension(500, 500);
		Pictures.setUp();
		setUpScreen();
	}
	
	public void setUpScreen()
	{
		frame = new JFrame("Alchemy");
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
	}
	
	public int smartSize(Component input)
	{
		return 500;
	}
	
	public void glassPanePaint(Graphics g, JComponent glassPane)
	{
		g.drawImage(Pictures.scaledIcons[4].getImage(), 0, 0, null);
	}
	
	public void mainPanelPaint(Graphics g, JPanel panel)
	{
		
	}
	
	public void contentPanePaint(Graphics g, JComponent contentPane)
	{
		g.drawImage(Pictures.getBackgroundIcon().getImage(), 0, 0, contentPane);
	}
}
