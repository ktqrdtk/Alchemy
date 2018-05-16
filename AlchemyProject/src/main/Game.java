package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;

import java.awt.FlowLayout;

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
						Pictures.setScaledIcons(smartSize(ev.getComponent()), smartSize(ev.getComponent()));
					}
				};
		frame.getRootPane().addComponentListener(coAd);
		coAd.componentResized(new ComponentEvent(frame.getRootPane() ,101));
		
		
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBackground(Color.BLUE);
		mainPanel.add(inventoryPanel, "cell 0 0,grow");
		
		JPanel mixingPanel = new JPanel();
		mixingPanel.setBackground(Color.GREEN);
		mainPanel.add(mixingPanel, "cell 1 0 2 1,grow");
		mixingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		frame.setGlassPane(new JComponent()
				{
					protected void paintComponent(Graphics g)
					{
						glassPanePaint(g, this);
					}
				});
		
		frame.pack();
		frame.repaint();
		frame.getGlassPane().setVisible(true);
		frame.setVisible(true);
	}
	
	public int smartSize(Component input)
	{
		return 50;
	}
	
	public void glassPanePaint(Graphics g, JComponent glassPane)
	{
		
	}
	
	public void mainPanelPaint(Graphics g, JPanel panel)
	{
		
	}
}
