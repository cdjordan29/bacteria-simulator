import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * View class handles the implementation of the user interface for Project 1
 * @author Daniel Jordan
 * @version 1.0
 */
public class View 
{
	private Grid grid;
	private JFrame frame;
	private JPanel gridPane, goButtonPane, leftPane, rightPane;
	private JLabel generationLabel, timeLabel, legendLabel1, legendLabel2;
	private JButton[][] buttonGrid;
	private JButton goButton, greenButton, yellowButton, orangeButton, redButton;

	/**
	 * Constructor for the View class with Grid parameter which allows View to 
	 * call methods of the Grid
	 * @param grid
	 */
	public View(Grid grid)
	{
		this.grid = grid;
	}

	/**
	 * updateButtons sets the text of the JButton[][] with the data from the Cell class
	 * also with Cell as a parameter it allows updateButtons to call methods of Cell
	 * @param g
	 */
	public void updateButtons(Cell[][] g)
	{		
		for(int row = 1; row < g.length; row++)
		{
			for(int col = 1; col < g[row].length; col++)
			{
				buttonGrid[row][col].setText(g[row][col].getBacteria() + "");
				buttonGrid[row][col].paintImmediately(buttonGrid[row][col].getVisibleRect());
			}
		}
	}
	
	/*
	 * updateLabels sets the text of the JLabels with the data in the 
	 * Grid class
	 * @param countGenerations
	 */
	public void updateLabels(int countGenerations)
	{
		final double TIME_PER_GENERATION = 6.3;
		generationLabel.setText("Number of generations: " + countGenerations);
		generationLabel.paintImmediately(generationLabel.getVisibleRect());
		timeLabel.setText("Time Elapsed: " + (countGenerations * TIME_PER_GENERATION) + " minutes.");
		timeLabel.paintImmediately(timeLabel.getVisibleRect());
	}
	
	/*
	 * colorButtons checks the individual Cells and depending on their value colors them 
	 * appropriately  
	 * @param g
	 */
	public void colorButtons(Cell[][] g)
	{
		for(int row = 1; row < g.length; row++)
		{
			for(int col = 1; col < g[row].length; col++)
			{
				if(g[row][col].getBacteria() < 500)
				{
					buttonGrid[row][col].setBackground(Color.green);
				}
				if(g[row][col].getBacteria() > 500 && g[row][col].getBacteria() <= 1000)
				{
					buttonGrid[row][col].setBackground(Color.yellow);
				}
				if(g[row][col].getBacteria() > 1000 && g[row][col].getBacteria() <= 2500)
				{
					buttonGrid[row][col].setBackground(Color.orange);
				}
				if(g[row][col].getBacteria() > 2500)
				{
					buttonGrid[row][col].setBackground(Color.red);
				}
			}
		}
	}
	
	/**
	 * prepareGUI creates the JFrame and adds the JPanels to the frame
	 */
	public void prepareGUI() throws java.lang.InterruptedException
	{
		frame = new JFrame("Project 1");
		frame.setSize(1800, 900);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		gridPane = new JPanel();
		goButtonPane = new JPanel();
		leftPane = new JPanel();
		rightPane = new JPanel();
		generationLabel = new JLabel();
		timeLabel = new JLabel();
		gridPane.setLayout(new GridLayout(25, 25));
		leftPane.setLayout(new GridLayout(6,1));
		rightPane.setLayout(new GridLayout(2, 1));
		goButtonPane.setLayout(new FlowLayout());
		frame.add(gridPane, BorderLayout.NORTH);
		frame.add(goButtonPane, BorderLayout.CENTER);
		frame.add(leftPane, BorderLayout.WEST);
		frame.add(rightPane, BorderLayout.EAST);
	}
	/**
	 * addComponentsToGUI adds all the components to the GUI
	 * by calling the private helper methods setupButtons() and setupLabels()
	 */
	public void addComponentsToGUI()throws java.lang.InterruptedException
	{
		setupButtons();
		setupLabelsAndLegend();
		frame.setVisible(true);
	}
	
	/**
	 * setupButtons is a private helper that sets up of all the JButtons on the GUI
	 */
	private void setupButtons()
	{
		buttonGrid = new JButton[26][26];
		goButton = new JButton("Go");
		greenButton = new JButton("500");
		yellowButton = new JButton("500-1000");
		orangeButton = new JButton("1000-2500");
		redButton = new JButton("2500+");
		for(int i = 1; i < buttonGrid.length; i++)
		{
			for(int j = 1; j < buttonGrid[i].length; j++)
			{
				buttonGrid[i][j] = new JButton();
				buttonGrid[i][j].setPreferredSize(new Dimension(30, 30));
				
				gridPane.add(buttonGrid[i][j]);
			}
		}
		goButton.setPreferredSize(new Dimension(100, 50));
		goButtonPane.add(goButton);
		goButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				grid.start();
			}
		});
		frame.setVisible(true);
	}
	
	/**
	 * setupLabelsAndLegend is a private helper that sets up all of the JLabels on the GUI
	 */
	private void setupLabelsAndLegend()
	{
		legendLabel1 = new JLabel("Colors/numbers correspond to ");
		legendLabel2 = new JLabel("organisms present in grid.");
		generationLabel = new JLabel(" Number of Generations: ");
		timeLabel = new JLabel(" Time Elapsed: ");
		leftPane.add(greenButton);
		greenButton.setBackground(Color.green);
		leftPane.add(yellowButton);
		yellowButton.setBackground(Color.yellow);
		leftPane.add(orangeButton);
		orangeButton.setBackground(Color.orange);
		leftPane.add(redButton);
		redButton.setBackground(Color.red);
		leftPane.add(legendLabel1);
		leftPane.add(legendLabel2);
		rightPane.add(generationLabel);
		rightPane.add(timeLabel);
		frame.setVisible(true);
	}
}
