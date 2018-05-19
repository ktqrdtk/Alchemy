package main;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Item
{
	private int id;
	private ImageIcon pic;
	private int x, y;
	
	public Item(int id)
	{
		this.id = id;
		pic = Pictures.scaledIcons[id];
		x = 0; y = 0;
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
	
	public void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point getLocation()
	{
		return new Point(x, y);
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
}
