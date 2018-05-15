package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		mainPane = new JPanel()
				{
					public void paintComponent(Graphics g)
					{
						super.paintComponent(g);
						g.drawImage(Pictures.scaledIcons[4].getImage(), 0, 0, null);
					}
				};
		mainPane.setBackground(new Color(102, 204, 0));
		
		ComponentAdapter coAd = new ComponentAdapter()
				{
					public void componentResized(ComponentEvent ev)
					{
						Pictures.setScaledIcons(smartSize(ev.getComponent()), smartSize(ev.getComponent()));
					}
				};
		
		frame.getContentPane().add(mainPane);
		frame.getRootPane().addComponentListener(coAd);
		coAd.componentResized(new ComponentEvent(frame.getRootPane(), 101));
		GridBagLayout gbl_mainPane = new GridBagLayout();
		gbl_mainPane.columnWidths = new int[] {10, 10, 10, 0};
		gbl_mainPane.rowHeights = new int[]{10, 0};
		gbl_mainPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		mainPane.setLayout(gbl_mainPane);
		
		JPanel mixingPane = new JPanel();
		mixingPane.setBackground(Color.BLACK);
		GridBagConstraints gbc_mixingPane = new GridBagConstraints();
		gbc_mixingPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_mixingPane.insets = new Insets(0, 0, 0, 5);
		gbc_mixingPane.gridx = 1;
		gbc_mixingPane.gridy = 0;
		mainPane.add(mixingPane, gbc_mixingPane);
		JPanel inventoryPane = new JPanel();
		inventoryPane.setPreferredSize(new Dimension(inventoryPane.getPreferredSize().width, inventoryPane.getPreferredSize().height));
		inventoryPane.setBackground(Color.BLUE);
		GridBagConstraints gbc_inventoryPane = new GridBagConstraints();
		gbc_inventoryPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_inventoryPane.gridx = 2;
		gbc_inventoryPane.gridy = 0;
		mainPane.add(inventoryPane, gbc_inventoryPane);
		
		frame.pack();
		frame.repaint();
		frame.setVisible(true);
	}
	
	public int smartSize(Component input)
	{
		return 50;
	}
}
