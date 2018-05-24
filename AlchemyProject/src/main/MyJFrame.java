package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
	private ArrayList<Item> activeItems;
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
							selectedItem.setLocation(new Point((int)(MouseInfo.getPointerInfo().getLocation().x - (.5 * selectedItem.getActualSize())), (int)(MouseInfo.getPointerInfo().getLocation().y - (.5 * selectedItem.getActualSize()))));
							revalidate();
							repaint();
						}
					}
				};
		Timer timer = new Timer();
		timer.schedule(resizeRun, 1000, 17);
		activeItems = new ArrayList<Item>();
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
		if(!inInventory(input.getPoint())  && selectedItem != null)
		{
			Item newItem = new Item(this, selectedItem.getId(), true);
			newItem.setLocation(new Point((int)(MouseInfo.getPointerInfo().getLocation().x - (.5 * newItem.getActualSize())), (int)(MouseInfo.getPointerInfo().getLocation().y - (.5 * newItem.getActualSize()))));
			newItem.setNum(selectedItem.getNum());
			add(newItem);
			if(!containsNumber(selectedItem.getNum(), activeItems))
			{
				activeItems.add(newItem);
			}
			maybeCrossed(newItem);
			remove(selectedItem);
		}
		else if(selectedItem != null)
		{
			activeItems.remove(selectedItem);
			remove(selectedItem);
		}
		selectedItem = null;
		repaint();
	}
	
	public void setCorrectBounds()
	{
		for(int i = 0; i < grid.items.size(); i++)
		{
			Item curItem = grid.items.get(i);
			curItem.setBounds(curItem.getBounds().x, curItem.getBounds().y, calcImageSize(), calcImageSize());
		}
	}
	
	public void maybeCrossed(Item input)
	{
		for(int i = 0; i < activeItems.size(); i++)
		{
			Item curItem = activeItems.get(i);
			if(!input.equals(curItem))
			{
				if(input.contains(curItem.getLocation()))
				{
					cross(curItem, input);
					return;
				}
			}
		}
	}
	
	public boolean containsNumber(int num, ArrayList<Item> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(num == list.get(i).getNum())
			{
				return true;
			}
		}
		return false;
	}
	
	public void cross(Item item1, Item item2)
	{
		// TODO
	}
}
