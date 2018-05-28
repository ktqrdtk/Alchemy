package main;

//0		1		2	3	4
//earth fire water air obsidian

import java.awt.Container;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pictures 
{
	public static final int ITEM_IMAGE_NUM = 5;
	public static HashMap<String, Integer> itemIds;
	private static Path[] locations;
	private static final String stringStarter = "/Images/", backgroundName = "background.png";
	private static BufferedImage[] images;
	private static ImageIcon[] originalIcons;
	public static ImageIcon[] scaledIcons;
	private static BufferedImage backgroundImg;
	private static ImageIcon backgroundOIcon, backgroundIcon;
	
	public static Dimension contentPaneSize;
	
	public static void setUp()
	{
		itemIds = new HashMap<String, Integer>(ITEM_IMAGE_NUM);
		contentPaneSize = new Dimension();
		setLocations();
		setImages();
		setOriginalIcons();
	}
	
	private static void setLocations()
	{
		//this code VVVV, is from: https://stackoverflow.com/a/28057735/9053026
		try
		{
			URI uri = Pictures.class.getResource(stringStarter).toURI();
	        Path myPath;
	        if (uri.getScheme().equals("jar"))
	        {
	            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap(), Thread.currentThread().getContextClassLoader());
	            myPath = fileSystem.getPath(stringStarter);
	            fileSystem.close();
	        }
	        else
	        {
	            myPath = Paths.get(uri);
	        }
	        Stream<Path> walk = Files.walk(myPath, 1);
	        ArrayList<Path> tempLocations = new ArrayList<Path>();
	        for (Iterator<Path> it = walk.iterator(); it.hasNext();)
	        {
	        	Path next = it.next();
	            tempLocations.add(next);
	        }
	        walk.close();
	        tempLocations.remove(0);//removes the folder path
	        locations = new Path[tempLocations.size() - 1];//-1 for background
	        boolean before = true;
	        for(int i = 0; i < tempLocations.size() - 1; i++)
	        {
	        	if(before)
	        	{
		        	if(!tempLocations.get(i).endsWith(backgroundName))
		        	{
		        		locations[i] = tempLocations.get(i);
		        		itemIds.put(getEnding(locations[i]), i);
		        	}
		        	else
		        	{
		        		before = false;
		        		i--;
		        	}
	        	}
	        	else
	        	{
	        		locations[i] = tempLocations.get(i + 1);
	        		itemIds.put(getEnding(locations[i]), i);
	        	}
	        }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private static void setImages()
	{
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
		try
		{
			for(int i = 0; i < locations.length; i++)
			{
				BufferedImage bi = ImageIO.read(locations[i].toFile());
				list.add(bi);
			}
			backgroundImg = ImageIO.read(Pictures.class.getResource(stringStarter + backgroundName));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		images = list.toArray(new BufferedImage[list.size()]);
	}
	
	private static void setOriginalIcons()
	{
		ArrayList<ImageIcon> oList = new ArrayList<ImageIcon>();
		ArrayList<ImageIcon> sList = new ArrayList<ImageIcon>();
		for(int i = 0; i < images.length; i++)
		{
			oList.add(new ImageIcon(images[i]));
			sList.add(new ImageIcon(images[i]));
		}
		
		backgroundOIcon = new ImageIcon(backgroundImg);
		backgroundIcon = new ImageIcon(backgroundImg);
		
		originalIcons = oList.toArray(new ImageIcon[oList.size()]);
		scaledIcons = sList.toArray(new ImageIcon[sList.size()]);
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
	
	public static int getId(String input)
	{
		return itemIds.get(input);
	}
	
	public static String getEnding(Path p)
	{
		String path = p.toString();
		int slashCount = 0;
		for(int i = 0; i < path.length(); i++)
		{
			if(path.charAt(i) == '\\')
			{
				slashCount++;	
			}
		}
		int curSlashes = 0;
		String returnValue = "";
		boolean passedDot = false;
		for(int i = 0; i < path.length(); i++)
		{
			if(path.charAt(i) == '\\')
			{
				curSlashes++;
			}
			else if(curSlashes == slashCount)
			{
				if(path.charAt(i) == '.')
				{
					passedDot = true;
				}
				else if(!passedDot)
				{
					returnValue += path.charAt(i);
				}
			}
		}
		return returnValue;
	}
}
