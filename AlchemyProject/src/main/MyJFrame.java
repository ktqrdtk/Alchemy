package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class MyJFrame extends JFrame 
{
	public boolean needsToBeResized;
	private TimerTask resizeRun;
	
	private Grid grid;
	private double lineMultiplier;
	private int lineWidth;
	private boolean left, actualStart;
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
							if(getContentPane().getSize() != null && Pictures.smartSize(getContentPane()) != -1)
							{
								Pictures.contentPaneSize.setSize(getContentPane().getSize());
								Pictures.setScaledIcons(Pictures.smartSize(getContentPane()), Pictures.smartSize(getContentPane()));
								if(grid != null & grid.items != null)
								{
									revalidateItems(1000);
								}
							}
							revalidate();
							repaint();
						}
						needsToBeResized = false;
					}
				};
		Timer timer = new Timer();
		timer.schedule(resizeRun, 1000, 1000);
	}
	
	public void initInventory(double lineMultiplier, int lineWidth, boolean left, Dimension glassPaneSize, Item[] items)
	{
		this.lineMultiplier = lineMultiplier;
		this.lineWidth = lineWidth;
		this.left = left;
		this.glassPaneSize = glassPaneSize;
		grid = new Grid(calcInventoryWidthSize(), calcImageSize(), Game.defaultSpacingSize, Game.defaultDistanceFromLine, left, glassPaneSize, getInsets().top);
		for(int i = 0; i < items.length; i++)
		{
			grid.items.add(items[i]);
		}
		revalidateItems(1000);
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
		return Pictures.smartSize(glassPaneSize);
	}
	
	public void revalidateItems(int wait)
	{
		Timer timer = new Timer();
		TimerTask task = new TimerTask()
				{
					public void run()
					{
						grid.revalidate();
						repaint();
					}
				};
		timer.schedule(task, wait);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		if(grid.valid)
		{
			for(int i = 0; i < grid.items.size(); i++)
			{
				Item curItem = grid.items.get(i);
				Dimension location = curItem.getLocation();
				g.drawImage(curItem.getPic().getImage(), location.width, location.height, null);
			}
		}
	}
}
