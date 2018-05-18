package main;

import java.util.ArrayList;

public class Grid 
{
	private int widthSize, spacingSize, imageSize, imageNumPerRow, distanceFromLine, actualWidthSize, actualImageSize;
	public ArrayList<Item> items;
	
	public Grid(int widthSize, int imageSize, int spacingSize, int distanceFromLine)
	{
		this.widthSize = widthSize;
		this.imageSize = imageSize;
		this.spacingSize = spacingSize;
		this.distanceFromLine = distanceFromLine;
		calcImageNumPerRow();
		items = new ArrayList<Item>();
	}
	
	public int calcImageNumPerRow()
	{
		actualImageSize = imageSize + spacingSize;
		actualWidthSize = widthSize - distanceFromLine;
		imageNumPerRow = (int)((actualWidthSize)/ actualImageSize);
		return imageNumPerRow;
	}
	
	public void addItems()
	{
		for(int i = 0; i < items.size(); i++)
		{
			Item curItem = items.get(i);
		}
	}
}
