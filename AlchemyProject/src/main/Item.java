package main;

import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Item
{
	private int id;
	private ImageIcon pic;
	int x, y, width, height;
	
	public Item(int id)
	{
		this.id = id;
		pic = Pictures.scaledIcons[id];
		x = 0; y = 0; width = pic.getIconWidth(); height = pic.getIconHeight();
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
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public Dimension getLocation()
	{
		return new Dimension(x, y);
	}
}
