package main;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public class Grid 
{
	private int widthSize, spacingSize, imageSize, imageNumPerRow, distanceFromLine, actualWidthSize, actualImageSize;
	private boolean left;
	private Dimension glassPaneSize;
	public ArrayList<Item> items;
	public boolean valid;
	
	public Grid(int widthSize, int imageSize, int spacingSize, int distanceFromLine, boolean left, Dimension glassPaneSize)
	{
		valid = false;
		this.widthSize = widthSize;
		this.imageSize = imageSize;
		this.spacingSize = spacingSize;
		this.distanceFromLine = distanceFromLine;
		this.left = left;
		this.glassPaneSize = glassPaneSize;
		calcImageNumPerRow();
		items = new ArrayList<Item>();
	}
	
	public int calcImageNumPerRow()
	{
		actualImageSize = imageSize + spacingSize;
		actualWidthSize = widthSize - distanceFromLine;
		if(!left)
		{
			actualWidthSize -= Game.lineWidth;
		}
		imageNumPerRow = (int)((actualWidthSize)/ actualImageSize);
		return imageNumPerRow;
	}
	
	public void validate()
	{
		for(int i = 0; i < items.size(); i++)
		{
			Item curItem = items.get(i);
			Point coord = getLocation(i, left);
			int x = coord.x;
			int y = coord.y;
			if(x == -1 || y == -1)
			{
				System.out.println("Invalid Location: grid validate method.");
				return;
			}
			curItem.setLocation(x, y);
			valid = true;
		}
	}
	
	public void revalidate()
	{
		validate();
	}
	
	public Point getLocation(int input, boolean left)
	{
		int startingPoint = (int)((Game.lineDistanceMultiplier * glassPaneSize.width) + .5);
		Point returnValue = new Point(-1, -1);
		int xNumOfImages = xNumOfImages(input);
		int yNumOfImages = yNumOfImages(input);
		
		if(imageNumPerRow < 1)
		{
			return returnValue;
		}
		
		int negOrNot = 1;
		if(left){negOrNot = -1;}
		
		int countAwayFromStartingPointX = 0;
		if(!left)
		{
			countAwayFromStartingPointX += Game.lineWidth;
		}
		countAwayFromStartingPointX += distanceFromLine;
		countAwayFromStartingPointX += xNumOfImages * (imageSize + spacingSize);
		
		int countAwayFromStartingPointY = 0;
		countAwayFromStartingPointY += spacingSize;
		countAwayFromStartingPointY += yNumOfImages * (imageSize + spacingSize);
		
		countAwayFromStartingPointX *= negOrNot;
		countAwayFromStartingPointX += startingPoint;
		if(left)
		{
			//dont know why this needs to be here, but it works so leave it vvvvv
			countAwayFromStartingPointX -= imageSize + spacingSize;
		}
		returnValue.setLocation(countAwayFromStartingPointX, countAwayFromStartingPointY);
		return returnValue;
	}
	
	public int xNumOfImages(int input)
	{
		int returnValue = input;
		while(returnValue >= imageNumPerRow)
		{
			returnValue -= imageNumPerRow;
		}
		return returnValue;
	}
	
	public int yNumOfImages(int input)
	{
		int returnValue = 0;
		int count = input;
		while(count >= imageNumPerRow)
		{
			count -= imageNumPerRow;
			returnValue++;
		}
		return returnValue;
	}
	
	public boolean containsNum(int input)
	{
		for(int i = 0; i < items.size(); i++)
		{
			if(input == items.get(i).getId())
			{
				return true;
			}
		}
		return false;
	}
}
