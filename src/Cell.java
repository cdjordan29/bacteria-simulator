/**
 * Cell class represents a single cell in Project 1
 * @author Daniel Jordan
 * @version 1.0
 */

public class Cell
{
	private int bacteria;
	
	/**
	 * Constructor for the Cell class.
	 */
	public Cell()
	{
		this.bacteria = 0;
	}
	
	/**
	 * Sets the number of bacteria for the individual cells
	 * @param value, will be the new value for bacteria in the cell
	 */
	public void setBacteria(int value)
	{
		this.bacteria = value;
	}
	
	/**
	 * Gets the count of bacteria for individual cells
	 * @return an int representing the bacteria in the cell
	 */
	public int getBacteria() 
	{
		return bacteria;
	}
	
	/**
	 * Allows the adding of bacteria to the individual cells
	 * @param value, will be added to the count of the bacteria in the cell 
	 */
	public void addBacteria(int value)
	{
		this.bacteria += value;
	}
}
