package main;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Item extends JComponent implements MouseListener
{
	private MyJFrame frame;
	private int id;
	private ImageIcon pic;
	private int x, y;
	public boolean clickable;
	public static int totalItemNum = 0;
	
	public Item(MyJFrame frame, int id, boolean clickable)
	{
		this.frame = frame;
		this.id = id;
		pic = Pictures.scaledIcons[id];
		x = 0; y = 0;
		setBounds(new Rectangle(x, y, pic.getIconWidth(), pic.getIconHeight()));
		this.clickable = clickable;
		if(clickable)
		{
			addMouseListener(this);
		}
		totalItemNum++;
	}
	
	public int getId()
	{
		return id;
	}
	
	public ImageIcon getPic()
	{
		return pic;
	}
	
	public void setId(int input)
	{
		id = input;
		pic = Pictures.scaledIcons[id];
	}

	public double distanceFrom(Point input)
	{
		double actX = x + (.5 * pic.getIconWidth());
		double actY = y + (.5 * pic.getIconHeight());
		double xDis = actX - input.x;
		double yDis = actY - input.y;
		double xSqu = xDis * xDis;
		double ySqu = yDis * yDis;
		double added = xSqu + ySqu;
		return Math.sqrt(added);
	}

	public double getRadius()
	{
		return distanceFrom(new Point(x ,y));
	}
	
	public void setLocation(Point input)
	{
		super.setLocation(input);
		x = input.x;
		y = input.y;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(pic.getImage(), x, y, null);
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
		if(clickable)
		{
			Item newItem = new Item(frame, id, false);
			totalItemNum--;
			//newItem.setLocation(MouseInfo.getPointerInfo().getLocation());
			frame.selectedItem = newItem;
			frame.add(newItem);
		}
	}

	@Override
	public void mouseReleased(MouseEvent input)
	{
		input.setSource(frame);
		
		//resets point to 0
		input.translatePoint(-input.getX(), -input.getY());
		
		//sets it to mouse pos
		input.translatePoint(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
		
		//accounts for frame's offset
		input.translatePoint(-1 * frame.getLocation().x, -1 * frame.getLocation().y);
		frame.getMouseListeners()[0].mouseReleased(input);
	}
	
	public String toString()
	{
		return super.toString() + " id: " + id;
	}
}
