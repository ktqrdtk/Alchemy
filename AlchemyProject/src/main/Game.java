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
						g.drawImage(Pictures.scaledIcons[4].getImage(), 0, 0, null);
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
		mainPanel.setLayout(new MigLayout("", "[500px]", "[500px]"));
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBackground(Color.BLUE);
		mainPanel.add(inventoryPanel, "cell 0 0,grow");
		
		JPanel mixingPanel = new JPanel();
		mixingPanel.setBackground(new Color(0, 255, 0));
		mainPanel.add(mixingPanel, "cell 1 0 2 1,grow");
		mixingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}
	
	public int smartSize(Component input)
	{
		return 50;
	}
}
