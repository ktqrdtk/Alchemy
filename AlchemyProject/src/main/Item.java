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
			newItem.setLocation(MouseInfo.getPointerInfo().getLocation());
			frame.selectedItem = newItem;
			frame.add(frame.selectedItem);
		}
	}

	@Override
	public void mouseReleased(MouseEvent input)
	{
		frame.getMouseListeners()[0].mouseReleased(input);
	}
}
