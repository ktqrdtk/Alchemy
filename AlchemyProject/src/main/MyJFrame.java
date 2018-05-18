package main;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class MyJFrame extends JFrame 
{
	public static int defaultSpacingSize = 5;
	public static int defaultDistanceFromLine = 5;
	
	public boolean needsToBeResized;
	private TimerTask resizeRun;
	
	private Grid grid;
	private double lineMultiplier;
	private int lineWidth;
	private boolean left;
	private Dimension glassPaneSize;
	
	public MyJFrame(String input)
	{
		super(input);
		resizeRun = new TimerTask()
				{
					public void run()
					{
						if(needsToBeResized)
						{
							if(getContentPane().getSize() != null)
							{
								Pictures.contentPaneSize.setSize(getContentPane().getSize());
							}
							Pictures.setScaledIcons(Game.smartSize(getContentPane()), Game.smartSize(getContentPane()));
							revalidate();
							repaint();
						}
						needsToBeResized = false;
					}
				};
		Timer timer = new Timer();
		timer.schedule(resizeRun, 1000, 1000);
	}
	
	public void initInventory(double lineMultiplier, int lineWidth, boolean left, Dimension glassPaneSize)
	{
		this.lineMultiplier = lineMultiplier;
		this.lineWidth = lineWidth;
		this.left = left;
		this.glassPaneSize = glassPaneSize;
		grid = new Grid(calcInventoryWidthSize(), calcImageSize(), defaultSpacingSize, defaultDistanceFromLine);
	}
	
	public void paintInventory() 
	{
		
	}
	
	public int calcInventoryWidthSize()
	{
		int paneWidth = glassPaneSize.width;
		if(left)
		{
			return (int)((paneWidth * lineMultiplier) - lineWidth + .5);
		}
		else
		{
			return paneWidth - ((int)((paneWidth * lineMultiplier) - lineWidth + .5));
		}
	}
	
	public int calcImageSize()
	{
		return Game.smartSize(glassPaneSize);
	}
}
