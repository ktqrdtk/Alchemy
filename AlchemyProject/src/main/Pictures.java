package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pictures 
{
	private static String earthLocation, fireLocation, waterLocation, airLocation, obsidianLocation;
	private static final String stringStarter = "Images/";
	private static Image[] images;
	private static ImageIcon[] originalIcons;
	public static ImageIcon[] scaledIcons;
	
	public static void setUp()
	{
		setLocations();
		setImages();
		setOriginalIcons();
	}
	
	private static void setLocations()
	{
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
		
		ImageIcon[] tempArray = {earthOIcon, fireOIcon, waterOIcon, airOIcon, obsidianOIcon};
		originalIcons = tempArray;
		scaledIcons = tempArray;
	}
	
	public static void setScaledIcons(int _1, int _2)
	{
		int new_1 = smartCast(_1);
		int new_2 = smartCast(_2);
		for(int i = 0; i < originalIcons.length; i++)
		{
			scaledIcons[i].setImage(scaleImageIcon(originalIcons[i], new_1, new_2));
		}
	}
	
	private static int smartCast(double input) 
	{
		return (int)(input + .5);
	}

	private static Image scaleImageIcon(ImageIcon input, int _1, int _2)
	{
		Image returnValue = input.getImage().getScaledInstance(_1, _2, java.awt.Image.SCALE_SMOOTH);
		return returnValue;
	}
}
