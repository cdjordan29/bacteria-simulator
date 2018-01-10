import java.util.Random;

/**
 * Grid class represents the grid as a 2-D array of Cells
 * and acts as the controller for Project 1
 * 
 * @author Daniel Jordan
 * @version 1.0
 */
public class Grid 
{
	private View v;
	private Cell[][] grid;
	private Random rand;

	/**
	 * Constructor for the Grid class. Establishes the 2-D array of Cells
	 * and also sets the initial random bacteria count of 0-250. 
	 */
	public Grid() 
	{
		v = new View(this);
		try 
		{
			v.prepareGUI();
			v.addComponentsToGUI();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		grid = new Cell[26][26];
		rand = new Random();
		for (int row = 0; row < grid.length; row++) 
		{
			for (int col = 0; col < grid[row].length; col++) 
			{
				grid[row][col] = new Cell();
				int startBacteriaCount = rand.nextInt(251);
				grid[row][col].setBacteria(startBacteriaCount);
			}
		}
	}
	
	/**
	 * start method is the control loop for Project 1
	 */
	public void start()
	{
		int sum_of_grid = 0;
		int countGenerations = 0;
		boolean done = false;
		while(!done)
		{
			v.updateButtons(grid);
			v.colorButtons(grid);
			v.updateLabels(countGenerations);
			for(int row = 1; row < grid.length; row++)
			{
				for(int col = 1; col < grid[row].length; col++)
				{
					sum_of_grid = sum_of_grid + grid[row][col].getBacteria();
				}
			}
			if(sum_of_grid >= 10000000)
			{
				done = true;
			}
			else
			{
				doubleBacteria();
				glide();
				countGenerations++;
			}
			try 
			{
				Thread.sleep(2000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * doubleBacteria doubles the number of bacteria in each Cell with each iteration 
	 */
	public void doubleBacteria()
	{
		for(int row = 1; row < grid.length; row++)
		{
			for(int col = 1; col < grid[row].length; col++)
			{
				grid[row][col].setBacteria(grid[row][col].getBacteria() * 2);
			}
		}
	}
	
	/**
	 * glide allows each Cell to check neighboring Cell and for each 1000 bacteria
	 * in neighboring Cell +10 to the current Cell
	 */
	public void glide()
	{
		for(int row = 1; row < grid.length; row++)
		{
			for(int col = 1; col < grid[row].length; col++)
			{
				grid[row][col].addBacteria(surroundingBacteria(row, col));
			}
		}
	}
	
	/**
	 * surroundingBacteria is a private helper to determine the result to add to the
	 * individual cells
	 * @return result, which is +10 for every 1000 in surrounding cells 
	 */
	private int surroundingBacteria(int row, int col)
	{
		int result = 0;
		
		for(int offRow = -1; offRow <= 1; offRow++)
		{
			for(int offCol = -1; offCol <= 1; offCol++)
			{
				if(isCellInGrid(col, row, offCol, offRow))
				{
					result += grid[row + offRow][col + offCol].getBacteria();
				}
			}
		}
		
		result = result/1000;
		result = result * 10;
		return result; 
	}
	
	/**
	 * Private helper to determine if a given position is valid 
	 * in determining the glide method
	 * @param i is the base column coordinate 
	 * @param j is the base row coordinate 
	 * @param x is the column offset
	 * @param y is the row offset
	 * @return result 
	 */
	private boolean isCellInGrid(int i, int j, int x, int y)
	{
		boolean result = true;
		if((x == 0) && (y == 0))
		{
			result = false;
		}
		else if (((i + x) < 0) || ((i + x) >= grid.length))
		{
			result = false; 
		}
		else if (((j + y) < 0) || ((j + y) >= grid[0].length))
		{
			result = false;
		}
		
		return result;
	}
	
}
