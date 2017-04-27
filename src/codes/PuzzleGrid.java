package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PuzzleGrid {
	private Node root;
	private int dimension;
	public int solutions = 0;
	public PuzzleGrid(int size) throws IOException
	{

		// Creating the first node
		this.dimension = size;
		root = new Node(0);
	
		Node marker = root;
	
		// Creating the first row
		for (int x = 0; x < dimension - 1; x++) 
		{
			Node temp = new Node(0);
			marker.setRight(temp);
			temp.setLeft(marker);
			marker = temp;
		}
	
		Node rowMarker = root;
		//create all next rows
		for (int y = 0; y < dimension - 1; y++) 
		{
			Node temp = new Node(0);
			rowMarker.setDown(temp);
			temp.setUp(rowMarker);
		
			for (int x = 0; x < dimension - 1; x++) 
			{
				marker = temp;
				temp = new Node(0);
				marker.setRight(temp);
				temp.setLeft(marker);
				temp.getLeft().getUp().getRight().setDown(temp);
				temp.setUp(temp.getLeft().getUp().getRight());
			}
			
				rowMarker = rowMarker.getDown();
		
		}
		//set boxIDs
		rowMarker = root;
		
		for (int y = 0; y < 9; y++)
		{
			Node temp = rowMarker;
			for(int x = 0; x < 9; x++)
			{
				temp.setBoxID(x/3 + 1 + y/3*3 );
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
		}
		// populate with puzzle
		File f = new File("puzzle.txt");
		Scanner fileInput = new Scanner(f);
		rowMarker = root;
		while(rowMarker != null)
		{
			Node temp = rowMarker;
			while(temp != null)
			{
				temp.setData(fileInput.nextInt());
			}
		}
		fileInput.close();
	}

	public void display() 
	{
		Node rowMarker = root;
		int counter2 = 1;
		while (rowMarker != null) 
		{
			Node temp = rowMarker;
			int counter1 = 1;
			
			while (temp != null)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getRight();
				if(counter1 % 3 == 0)
					System.out.print(" | ");
				counter1++;
			}
			System.out.println();
			rowMarker = rowMarker.getDown();
			if(counter2 % 3 == 0)
				System.out.println("--------------------------");
			counter2++;
		}
	}
	public void solve(Node currentNode, int number)
	{
		currentNode.setData(number);
		Node temp = currentNode.getUp();
		//eliminate for row and column
		while(temp != null)
		{
			temp.Eliminate(number);
			temp = temp.getUp();
		}
		temp = currentNode.getDown();
		while(temp != null)
		{
			temp.Eliminate(number);
			temp = temp.getDown();
		}
		temp = currentNode.getRight();
		while(temp != null)
		{
			temp.Eliminate(number);
			temp = temp.getRight();
		}
		temp = currentNode.getLeft();
		while(temp != null)
		{
			temp.Eliminate(number);
			temp = temp.getLeft();
		}
		//eliminate for box
		Node rowMarker = root;
		
		while(rowMarker != null)
		{
			temp = rowMarker;
			while(temp != null)
			{
				if(temp.getBoxID() == currentNode.getBoxID() && temp != currentNode)
					temp.Eliminate(number);
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
		}
	}

	public Node getRoot() {
		return root;
	}
}
	

