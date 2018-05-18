package main;

import java.awt.Container;

//0		1		2	3	4
//earth fire water air obsidian

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pictures 
{
	public static final int ITEM_IMAGE_NUM = 5;
	public static final int EARTH = 0, FIRE = 1, WATER = 2, AIR = 3, OBSIDIAN = 4;
	
	private static String backgroundLocation, earthLocation, fireLocation, waterLocation, airLocation, obsidianLocation;
	private static final String stringStarter = "Images/";
	private static Image[] images;
	private static ImageIcon[] originalIcons;
	public static ImageIcon[] scaledIcons;
	private static Image backgroundImg;
	private static ImageIcon backgroundOIcon, backgroundIcon;
	
	public static Dimension contentPaneSize;
	
	public static void setUp()
	{
		contentPaneSize = new Dimension();
		setLocations();
		setImages();
		setOriginalIcons();
	}
	
	private static void setLocations()
	{
		backgroundLocation = stringStarter + "background.png";
		earthLocation = stringStarter + "earth.png";
		fireLocation = stringStarter + "fire.png";
		waterLocation = stringStarter + "water.png";
		airLocation = stringStarter + "air.png";
		obsidianLocation = stringStarter + "obsidian.png";
	}
	
	private static void setImages()
	{
		try
		{
			Image earthImg = ImageIO.read(new File(earthLocation));
			Image fireImg = ImageIO.read(new File(fireLocation));
			Image waterImg = ImageIO.read(new File(waterLocation));
			Image airImg = ImageIO.read(new File(airLocation));
			Image obsidianImg = ImageIO.read(new File(obsidianLocation));
			
			backgroundImg = ImageIO.read(new File(backgroundLocation));
			
			Image[] tempArray = {earthImg, fireImg, waterImg, airImg, obsidianImg};
			images = tempArray;
		}
		catch(IOException ex)
		{
			System.out.println("Missing Images");
		}
	}
	
	private static void setOriginalIcons()
	{
		ImageIcon earthOIcon = new ImageIcon(images[0]);
		ImageIcon fireOIcon = new ImageIcon(images[1]);
		ImageIcon waterOIcon = new ImageIcon(images[2]);
		ImageIcon airOIcon = new ImageIcon(images[3]);
		ImageIcon obsidianOIcon = new ImageIcon(images[4]);
		
		backgroundOIcon = new ImageIcon(backgroundImg);
		
		ImageIcon[] tempOArray = {earthOIcon, fireOIcon, waterOIcon, airOIcon, obsidianOIcon};
		originalIcons = tempOArray;
		
		ImageIcon earthIcon = new ImageIcon(images[0]);
		ImageIcon fireIcon = new ImageIcon(images[1]);
		ImageIcon waterIcon = new ImageIcon(images[2]);
		ImageIcon airIcon = new ImageIcon(images[3]);
		ImageIcon obsidianIcon = new ImageIcon(images[4]);
		
		backgroundIcon = new ImageIcon(backgroundImg);
		
		ImageIcon[] tempArray = {earthIcon, fireIcon, waterIcon, airIcon, obsidianIcon};
		scaledIcons = tempArray;
		
		for(int i = 0; i < tempArray.length; i++)
		{
			if(!(tempArray[i].getImage().equals(tempOArray[i].getImage())))
			{
				System.out.println("Set up my code wrong in picture class. Scaled images initial should = original images");
				throw new NumberFormatException();
			}
		}
	}
	
	public static void setScaledIcons(int _1, int _2)
	{
		int new_1 = smartCast(_1);
		int new_2 = smartCast(_2);
		for(int i = 0; i < originalIcons.length; i++)
		{
			scaledIcons[i].setImage(scaleImageIcon(originalIcons[i], new_1, new_2));
		}
		
		backgroundIcon.setImage(scaleImageIcon(backgroundOIcon, contentPaneSize.width, contentPaneSize.height));
	}
	
	private static int smartCast(double input) 
	{
		return (int)(input + .5);
	}

	private static Image scaleImageIcon(ImageIcon input, int _1, int _2)
	{
		Image returnValue = input.getImage().getScaledInstance(_1, _2, java.awt.Image.SCALE_DEFAULT);
		return returnValue;
	}
	
	public static ImageIcon getBackgroundIcon()
	{
		return backgroundIcon;
	}
	
	public static int smartSize(Container input)
	{
		return smartSize(input.getSize());
	}
	
	public static int smartSize(Dimension input)
	{
		double squared = input.width * input.height;
		double scaled = squared * Game.screenMultiplierForImages;
		double imageLength = Math.sqrt(scaled);
		
		int casted = (int)(imageLength + .5);
		if(casted != 0)
		{
			return casted;
		}
		else
		{
			return -1;
		}
	}
}
