package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game
{
	public static void main(String[] args)
	{
		new Game();
	}
	
	private JFrame frame;
	private JPanel mainPane;
	
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
		mainPane = new JPanel(new GridBagLayout())
				{
					public void paintComponent(Graphics g)
					{
						super.paintComponent(g);
					}
				};
		
		ComponentAdapter coAd = new ComponentAdapter()
				{
					public void componentResized(ComponentEvent ev)
					{
						Pictures.setScaledIcons(smartSize(ev.getComponent()), smartSize(ev.getComponent()));
					}
				};
		
		frame.add(mainPane);
		frame.getRootPane().addComponentListener(coAd);
		coAd.componentResized(new ComponentEvent(frame.getRootPane() ,101));
		
		GridBagConstraints cs = new GridBagConstraints();
		cs.gridheight = 10;
		cs.gridwidth = 10;
		cs.weightx = 0;
		cs.weighty = 0.1;
		
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 6;
		cs.fill = GridBagConstraints.VERTICAL;
		JPanel inventoryPane = new JPanel();
		inventoryPane.setBackground(Color.BLACK);
		mainPane.add(inventoryPane, cs);
		
		cs.gridx = 6;
		cs.gridy = 0;
		cs.gridwidth = 3;
		cs.fill = GridBagConstraints.VERTICAL;
		JPanel mixingPane = new JPanel();
		mixingPane.setBackground(Color.RED);
		mainPane.add(mixingPane, cs);
		
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}
	
	public int smartSize(Component input)
	{
		return 500;
	}
}
