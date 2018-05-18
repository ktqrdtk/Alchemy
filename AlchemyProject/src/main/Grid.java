package main;

public class Grid 
{
	private int widthSize, spacingSize, imageSize, imageNumPerRow, distanceFromLine, actualWidthSize, actualImageSize;
	
	public Grid(int widthSize, int imageSize, int spacingSize, int distanceFromLine)
	{
		this.widthSize = widthSize;
		this.imageSize = imageSize;
		this.spacingSize = spacingSize;
		this.distanceFromLine = distanceFromLine;
		calcImageNumPerRow();
	}
	
	public int calcImageNumPerRow()
	{
		actualImageSize = imageSize + spacingSize;
		actualWidthSize = widthSize - distanceFromLine;
		imageNumPerRow = (int)((actualWidthSize)/ actualImageSize);
		return imageNumPerRow;
	}
}
