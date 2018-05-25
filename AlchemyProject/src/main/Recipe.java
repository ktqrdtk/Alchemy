package main;

import java.util.ArrayList;

public class Recipe
{
	private static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	private boolean real;
	private int ing1, ing2, result;
	
	public Recipe(String input1, String input2)
	{
		this(Pictures.getId(input1), Pictures.getId(input2));
	}
	
	public Recipe(int id1, int id2)
	{
		real = false;
		ing1 = id1;
		ing2 = id2;
	}
	
	public Recipe(String input1, String input2, String input3)
	{
		this(Pictures.getId(input1), Pictures.getId(input2), Pictures.getId(input3));
	}
	
	public Recipe(int id1, int id2, int id3)
	{
		real = true;
		ing1 = id1;
		ing2 = id2;
		result = id3;
	}
	
	public boolean exists()
	{
		if(real)
		{
			return true;
		}
		else
		{
			for(int i = 0; i < recipeList.size(); i++)
			{
				if(ingMatch(recipeList.get(i)))
				{
					return true;
				}
			}
			return false;
		}
	}
	
	public boolean ingMatch(Recipe input)
	{
		if((ing1 != input.ing1) && (ing1 != input.ing2))
		{
			return false;
		}
		
		if((ing2 != input.ing1) && (ing2 != input.ing2))
		{
			return false;
		}
		
		return true;
	}
	
	public static 
}