package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MyJFrame extends JFrame 
{
	private Grid grid;
	private double lineMultiplier;
	private int lineWidth;
	private boolean left;
	private Dimension glassPaneSize;
	
	public MyJFrame(String input)
	{
		super(input);
	}
	
	public void initInventory(double lineMultiplier, int lineWidth, boolean left, Dimension glassPaneSize)
	{
		this.lineMultiplier = lineMultiplier;
		this.lineWidth = lineWidth;
		this.left = left;
		this.glassPaneSize = glassPaneSize;
		grid = new Grid();
		grid.setWidthPixels(calcInventoryWidthPixels());
	}
	
	public void paintInventory() 
	{
		
	}
	
	public int calcInventoryWidthPixels()
	{
		int paneWidth = glassPaneSize.width;
		if(left)
		{
			return (int)(paneWidth * lineMultiplier + .5);
		}
		else
		{
			return paneWidth - ((int)(paneWidth * lineMultiplier + .5));
		}
	}
}
