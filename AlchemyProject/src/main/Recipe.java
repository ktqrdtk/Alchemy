package main;

import java.util.ArrayList;

public class Recipe
{
	private static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
	private boolean real;
	private int ing1, ing2, result = -1;
	
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
		recipeList.add(this);
	}
	
	public int getResult()
	{
		if(real)
		{
			System.out.println("OOPS");
			return result;
		}
		else
		{
			for(int i = 0; i < recipeList.size(); i++)
			{
				if(ingMatch(recipeList.get(i)))
				{
					return recipeList.get(i).result;
				}
			}
			return -1;
		}
	}
	
	public boolean ingMatch(Recipe input)
	{
		if((ing1 == input.ing1) && (ing2 == input.ing2))
		{
			return true;
		}
		else if((ing1 == input.ing2) && (ing2 == input.ing1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return Pictures.getInverseId(ing1) + " + " + Pictures.getInverseId(ing2) + " = " + Pictures.getInverseId(result);
	}
	
	public static Recipe getRecipe(int index)
	{
		return recipeList.get(index);
	}
	
	public static int listLength()
	{
		return recipeList.size();
	}
}