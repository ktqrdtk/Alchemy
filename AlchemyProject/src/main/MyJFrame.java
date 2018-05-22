package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class MyJFrame extends JFrame implements MouseListener
{
	public boolean needsToBeResized;
	private TimerTask resizeRun;
	
	private Grid grid;
	private double lineMultiplier;
	private int lineWidth;
	private boolean left;
	private Dimension glassPaneSize;
	public Item selectedItem;
	
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
									grid.revalidate();
								}
							}
							revalidate();
							repaint();
						}
						needsToBeResized = false;
						if(selectedItem != null)
						{
							selectedItem.setLocation(MouseInfo.getPointerInfo().getLocation());
							revalidate();
							repaint();
						}
					}
				};
		Timer timer = new Timer();
		timer.schedule(resizeRun, 1000, 17);
		addMouseListener(this);
	}
	
	public void initInventory(double lineMultiplier, int lineWidth, boolean left, Dimension glassPaneSize, Item[] items)
	{
		this.lineMultiplier = lineMultiplier;
		this.lineWidth = lineWidth;
		this.left = left;
		this.glassPaneSize = glassPaneSize;
		grid = new Grid(calcInventoryWidthSize(), calcImageSize(), Game.defaultSpacingSize, Game.defaultDistanceFromLine, left, glassPaneSize);
		for(int i = 0; i < items.length; i++)
		{
			grid.items.add(items[i]);
		}
		grid.revalidate();
		for(int i = 0; i < grid.items.size(); i++)
		{
			add(grid.items.get(i));
		}
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
	
	public void paint(Graphics g)
	{
		super.paint(g);
	}
	
	public boolean inInventory(Point input)
	{
		if(left)
		{
			if(input.x < lineMultiplier * glassPaneSize.width)
			{
				return true;
			}
			return false;
		}
		else
		{
			if(input.x > (lineMultiplier * glassPaneSize.width) + lineWidth)
			{
				return true;
			}
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent input) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent input) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent input) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent input) {
		//  Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent input)
	{
		System.out.println("inventory:" + inInventory(input.getPoint()) + " selecetedItem: " + selectedItem);
		if(!inInventory(input.getPoint())  && selectedItem != null)
		{
			Item newItem = new Item(this, selectedItem.getId(), true);
			newItem.setLocation(MouseInfo.getPointerInfo().getLocation());
			add(newItem);
			System.out.println("Actually Added: " + newItem + " ID: " + newItem.getId() + " totalNum: " + Item.totalItemNum);
		}
		selectedItem = null;
		repaint();
	}
}
