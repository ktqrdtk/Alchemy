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
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][]"));
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBackground(Color.BLUE);
		mainPanel.add(inventoryPanel, "cell 1 0,grow");
		
		JPanel mixingPanel = new JPanel();
		mixingPanel.setBackground(Color.GREEN);
		mainPanel.add(mixingPanel, "cell 0 0,grow");
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}
	
	public int smartSize(Component input)
	{
		return 50;
	}
}
