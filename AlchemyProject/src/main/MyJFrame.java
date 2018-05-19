package main;

import java.awt.Dimension;
import java.awt.Graphics;
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
	private boolean left, paintSelectedItem, repaintAlways;
	private Dimension glassPaneSize;
	private Item selectedItem;
	
	public MyJFrame(String input)
	{
		super(input);
		repaintAlways = false;
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
						
						if(repaintAlways)
						{
							repaint();
						}
					}
				};
		Timer timer = new Timer();
		timer.schedule(resizeRun, 1000, 100);
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
		enableMouse();
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
		
		//item paint
		if(grid.valid)
		{
			for(int i = 0; i < grid.items.size(); i++)
			{
				Item curItem = grid.items.get(i);
				Point location = curItem.getLocation();
				g.drawImage(curItem.getPic().getImage(), location.x, location.y, null);
			}
		}
		
		//mouse painting if holding item
		if(paintSelectedItem && selectedItem != null)
		{
			g.drawImage(selectedItem.getPic().getImage(), getMousePosition().x - (selectedItem.getPic().getIconWidth() / 2), getMousePosition().y - (selectedItem.getPic().getIconHeight() / 2), null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent input)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent input)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent input)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent input)
	{
		selectedItem = getItemAt(input.getPoint());
		grabSelectedItem();
	}

	@Override
	public void mouseReleased(MouseEvent input)
	{	
		//dropSelectedItem(input);
		selectedItem = null;
	}
	
	public Item getItemAt(Point input)
	{
		if(inInventory(input))
		{
			Item closestItem = grid.items.get(0);
			for(int i = 0; i < grid.items.size(); i++)
			{
				Item curItem = grid.items.get(i);
				if(curItem.distanceFrom(input) < closestItem.distanceFrom(input))
				{
					closestItem = curItem;
				}
			}
			if(closestItem.distanceFrom(input) > closestItem.getRadius())
			{
				return null;
			}
			return closestItem;
		}
		else
		{
			return null;
		}
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
	
	public void enableMouse()
	{
		addMouseListener(this);
	}
	
	public void grabSelectedItem()
	{
		paintSelectedItem = true;
		repaintAlways = true;
	}
}
