package main;

//0		1		2	3	4
//earth fire water air obsidian

import java.awt.Container;

import java.awt.Dimension;
import java.awt.Image;
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
import javax.swing.ImageIcon;

public class Pictures 
{
	public static final int ITEM_IMAGE_NUM = 5;
	public static HashMap<String, Integer> itemIds;
	private static Path[] locations;
	private static final String stringStarter = "/Images/", backgroundName = "background.png";
	private static ImageIcon[] originalIcons;
	public static ImageIcon[] scaledIcons;
	private static ImageIcon backgroundOIcon, backgroundIcon;
	private static ArrayList<String> inverseIds = new ArrayList<String>();
	private static URI uri;
	
	public static Dimension contentPaneSize;
	
	public static void setUp()
	{
		itemIds = new HashMap<String, Integer>(ITEM_IMAGE_NUM);
		contentPaneSize = new Dimension();
		setLocations();
		setOriginalIcons();
	}
	
	private static void setLocations()
	{
		//this code VVVV, is from: https://stackoverflow.com/a/28057735/9053026
		try
		{
			uri = Pictures.class.getResource(stringStarter).toURI();
	        Path myPath;
	        if (uri.getScheme().equals("jar"))
	        {
	            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap(), Thread.currentThread().getContextClassLoader());
	            myPath = fileSystem.getPath(stringStarter);
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
						inverseIds.add(getEnding(locations[i]));
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
					inverseIds.add(getEnding(locations[i]));
					itemIds.put(getEnding(locations[i]), i);
	        	}
	        }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private static void setOriginalIcons()
	{
		ArrayList<ImageIcon> oList = new ArrayList<ImageIcon>();
		ArrayList<ImageIcon> sList = new ArrayList<ImageIcon>();
		if(uri.getScheme().equals("jar"))
		{
			for(int i = 0; i < locations.length; i++)
			{
				oList.add(new ImageIcon(Pictures.class.getResource(getEndingWithDot(locations[i]))));
				sList.add(new ImageIcon(Pictures.class.getResource(getEndingWithDot(locations[i]))));
			}
			
			backgroundIcon = new ImageIcon(Pictures.class.getResource(stringStarter + backgroundName));
			backgroundOIcon = new ImageIcon(Pictures.class.getResource(stringStarter + backgroundName));
			
			originalIcons = oList.toArray(new ImageIcon[oList.size()]);
			scaledIcons = sList.toArray(new ImageIcon[sList.size()]);
		}
		else
		{
			for(int i = 0; i < locations.length; i++)
			{
				oList.add(new ImageIcon(Pictures.class.getResource(stringStarter + getEndingWithDot(locations[i]))));
				sList.add(new ImageIcon(Pictures.class.getResource(stringStarter + getEndingWithDot(locations[i]))));
			}
			
			backgroundIcon = new ImageIcon(Pictures.class.getResource(stringStarter + backgroundName));
			backgroundOIcon = new ImageIcon(Pictures.class.getResource(stringStarter + backgroundName));
			
			originalIcons = oList.toArray(new ImageIcon[oList.size()]);
			scaledIcons = sList.toArray(new ImageIcon[sList.size()]);
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
	
	public static int getId(String input)
	{
		return itemIds.get(input);
	}
	
	public static String getInverseId(int input)
	{
		return inverseIds.get(input);
	}
	
	public static String getEnding(Path p)
	{
		char actualChar;
		if(uri.getScheme().equals("jar"))
		{
			actualChar = '/';
		}
		else
		{
			actualChar = '\\';
		}
		String path = p.toString();
		int slashCount = 0;
		for(int i = 0; i < path.length(); i++)
		{
			if(path.charAt(i) == actualChar)
			{
				slashCount++;	
			}
		}
		int curSlashes = 0;
		String returnValue = "";
		boolean passedDot = false;
		for(int i = 0; i < path.length(); i++)
		{
			if(path.charAt(i) == actualChar)
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
	
	public static String getEndingWithDot(Path p)
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
		for(int i = 0; i < path.length(); i++)
		{
			if(path.charAt(i) == '\\')
			{
				curSlashes++;
			}
			else if(curSlashes == slashCount)
			{
				returnValue += path.charAt(i);
			}
		}
		return returnValue;
	}
}
