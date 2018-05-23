package main;

import java.awt.Color;
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
	public boolean clickable;
	private int idNum;
	private int actualSize;
	public static int totalItemNum = 0;
	
	public Item(MyJFrame frame, int id, boolean clickable)
	{
		idNum = totalItemNum;
		this.frame = frame;
		this.id = id;
		pic = Pictures.scaledIcons[id];
		setLocation(0, 0);
		try
		{
			actualSize = frame.calcImageSize();
		}
		catch(Exception ex)
		{
			actualSize = 0;
		}
		setBounds(new Rectangle(getLocation().x, getLocation().y, actualSize, actualSize));
		this.clickable = clickable;
		if(clickable)
		{
			addMouseListener(this);
		}
		totalItemNum++;
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
	
	@SuppressWarnings("unused")
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(pic.getImage(), 0, 0, null);
		if(false)//change to true to show bounds
		{
			switch(id)
			{
			case 0:
				g.setColor(Color.PINK);
				break;
			case 1:
				g.setColor(Color.RED);
				break;
			case 2:
				g.setColor(Color.BLUE);
				break;
			case 3:
				g.setColor(Color.WHITE);
				break;
			default:
				g.setColor(Color.BLACK);
			}
			g.fillRect(0, 0, getBounds().width, getBounds().height);
		}
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
			totalItemNum--;
			updateActualSize();
			newItem.setLocation(new Point((int)(MouseInfo.getPointerInfo().getLocation().x - (.5 * actualSize)), (int)(MouseInfo.getPointerInfo().getLocation().y - (.5 * actualSize))));
			frame.selectedItem = newItem;
			frame.add(newItem);
		}
	}

	@Override
	public void mouseReleased(MouseEvent input)
	{
		input.setSource(frame);
		
		//resets point to 0
		input.translatePoint(-input.getX(), -input.getY());
		
		//sets it to mouse pos
		input.translatePoint(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
		
		//accounts for frame's offset
		input.translatePoint(-1 * frame.getLocation().x, -1 * frame.getLocation().y);
		frame.getMouseListeners()[0].mouseReleased(input);
	}
	
	public String toString()
	{
		return super.toString() + " id: " + id + " num: " + idNum;
	}
	
	public void updateActualSize()
	{
		actualSize = frame.calcImageSize();
		System.out.println(actualSize);
	}
}