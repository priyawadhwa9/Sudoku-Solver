package codes;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LinkedGrid {

	private Node root;
	private int dimension;
	private int solvedCounter;

	public int getSolvedCounter() {
		return solvedCounter;
	}

	public LinkedGrid(int size) throws IOException {
		solvedCounter = 0;
		// Creating the first node
		this.dimension = size;
		root = new Node(0);

		Node marker = root;

		// Creating the first row
		for (int x = 0; x < dimension - 1; x++) {
			Node temp = new Node(0);
			marker.setRight(temp);
			temp.setLeft(marker);
			marker = temp;
		}

		Node rowMarker = root;

		for (int y = 0; y < dimension - 1; y++) {
			Node temp = new Node(0);
			rowMarker.setDown(temp);
			temp.setUp(rowMarker);

			for (int x = 0; x < dimension - 1; x++) {
				marker = temp;
				temp = new Node(0);
				marker.setRight(temp);
				temp.setLeft(marker);
				temp.getLeft().getUp().getRight().setDown(temp);
				temp.setUp(temp.getLeft().getUp().getRight());
			}
			rowMarker = rowMarker.getDown();
		}

		// At this point the whole grid is created
		// We will go through and set BoxID's

		Node temp = root;
		rowMarker = root;

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				temp.setBoxID(x / 3 + 1 + (y / 3 * 3));
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
		// display();
		// diagnose();
		
		// Populating the Grid from a file

		Scanner fileIn = new Scanner(new File("Sudoku.txt"));
		rowMarker = root;
		int number;

		while (rowMarker != null) {
			temp = rowMarker;
			while (temp != null) {
				number = fileIn.nextInt();
				if (number != 0)
					solve(temp, number);
				temp = temp.getRight();
				// display();
				// diagnose();
			}

			rowMarker = rowMarker.getDown();
		}
		fileIn.close();
	}

	public void guess() {
		System.out.println("we must make a guess");
	}

	public void solve(Node currentNode, int number) {
		currentNode.setSolution(number);

		Node temp = currentNode.getUp();
		while (temp != null) {
			temp.setPossibilityFalse(number);
			temp = temp.getUp();
		}

		temp = currentNode.getDown();
		while (temp != null) {
			temp.setPossibilityFalse(number);
			temp = temp.getDown();
		}

		temp = currentNode.getLeft();
		while (temp != null) {
			temp.setPossibilityFalse(number);
			temp = temp.getLeft();
		}

		temp = currentNode.getRight();
		while (temp != null) {
			temp.setPossibilityFalse(number);
			temp = temp.getRight();
		}

		// Setting all Boxes with the same BoxId to be false
		int currentBoxID = currentNode.getBoxID();
		temp = root;
		Node rowMarker = root;
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (temp.getBoxID() == currentBoxID && temp != currentNode)
					temp.setPossibilityFalse(number);
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
		solvedCounter++;

	}

	public boolean solveOnlyPossible() {
		Node temp = root;
		Node rowMarker = root;
		boolean solved = false;
		while (rowMarker != null) {
			temp = rowMarker;
			while (temp != null) {
				if (temp.countPossible() == 1 && temp.getSolution() == 0) {
					solve(temp, temp.getOnlyPossible());
					display();
					System.out.println();
				}
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
		}
		return solved;
	}

	public void solveTwoRow(Node currentNode, Node exclude, int number) {

		Node temp = currentNode.getLeft();
		while (temp != null) {
			if (temp != exclude)
				temp.setPossibilityFalse(number);
			temp = temp.getLeft();
		}

		temp = currentNode.getRight();
		while (temp != null) {
			if (temp != exclude)
				temp.setPossibilityFalse(number);
			temp = temp.getRight();
		}

	}

	public void solveTwoColumn(Node currentNode, Node exclude, int number) {

		Node temp = currentNode.getUp();
		while (temp != null) {
			if (temp != exclude)
				temp.setPossibilityFalse(number);
			temp = temp.getUp();
		}

		temp = currentNode.getDown();
		while (temp != null) {
			if (temp != exclude)
				temp.setPossibilityFalse(number);
			temp = temp.getDown();
		}

	}

	public void solveTwoBox(Node currentNode, Node exclude, int number) {
		int currentBoxID = currentNode.getBoxID();
		Node temp = root;
		Node rowMarker = root;
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (temp.getBoxID() == currentBoxID && temp != currentNode && temp != exclude)
					temp.setPossibilityFalse(number);
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
	}

	public void solveThreeRow(Node currentNode, Node exclude, Node exclude2, int number) {
		Node temp = currentNode.getLeft();
		while (temp != null) {
			if (temp != exclude && temp != exclude2)
				temp.setPossibilityFalse(number);
			temp = temp.getLeft();
		}

		temp = currentNode.getRight();
		while (temp != null) {
			if (temp != exclude && temp != exclude2)
				temp.setPossibilityFalse(number);
			temp = temp.getRight();
		}
	}

	public void solveThreeColumn(Node currentNode, Node exclude, Node exclude2, int number) {
		Node temp = currentNode.getUp();
		while (temp != null) {
			if (temp != exclude && temp != exclude2)
				temp.setPossibilityFalse(number);
			temp = temp.getUp();
		}

		temp = currentNode.getDown();
		while (temp != null) {
			if (temp != exclude && temp != exclude2)
				temp.setPossibilityFalse(number);
			temp = temp.getDown();
		}
	}

	public void solveThreeBox(Node currentNode, Node exclude, Node exclude2, int number) {
		int currentBoxID = currentNode.getBoxID();
		Node temp = root;
		Node rowMarker = root;
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (temp.getBoxID() == currentBoxID && temp != currentNode && temp != exclude && temp != exclude2)
					temp.setPossibilityFalse(number);
				temp = temp.getRight();
			}
			rowMarker = rowMarker.getDown();
			temp = rowMarker;
		}
	}

	public boolean LogicOfTwo() {
		// check row
		Node rowMarker = root;
		Node temp = root;
		Node currentNode = root;
		boolean pair = false;
		boolean solved = false;
		while (rowMarker != null) {
			currentNode = rowMarker;
			while (currentNode != null) {
				pair = false;
				temp = rowMarker;
				if (currentNode.countPossible() == 2) {
					while (temp != null && pair == false) {
						if (Arrays.equals(currentNode.getPossible(), temp.getPossible()) && temp != currentNode) {
							pair = true;
							boolean[] copy = new boolean[10];
							copy = currentNode.getPossible();
							int possible1 = 0;
							int possible2 = 0;
							for (int x = 1; x < 10; x++) {
								if (copy[x] == true)
									possible1 = x;
							}
							for (int x = 1; x < 10; x++) {
								if (copy[x] == true && x != possible1)
									possible2 = x;
							}
							solveTwoRow(currentNode, temp, possible1);
							solveTwoRow(currentNode, temp, possible2);
							solved = true;
							display();
							System.out.println();
						}

						temp = temp.getRight();
					}

				}
				currentNode = currentNode.getRight();
			}
			rowMarker = rowMarker.getDown();
		}
		// check column
		rowMarker = root;
		temp = root;
		currentNode = root;
		pair = false;
		while (rowMarker != null) {
			currentNode = rowMarker;
			while (currentNode != null) {
				pair = false;
				temp = rowMarker;
				if (currentNode.countPossible() == 2) {
					while (temp != null && pair == false) {
						if (Arrays.equals(currentNode.getPossible(), temp.getPossible()) && temp != currentNode) {
							pair = true;
							boolean[] copy = new boolean[10];
							copy = currentNode.getPossible();
							int possible1 = 0;
							int possible2 = 0;
							for (int x = 1; x < 10; x++) {
								if (copy[x] == true)
									possible1 = x;
							}
							for (int x = 1; x < 10; x++) {
								if (copy[x] == true && x != possible1)
									possible2 = x;
							}
							solveTwoColumn(currentNode, temp, possible1);
							solveTwoColumn(currentNode, temp, possible2);
							solved = true;
							display();
							System.out.println();
						}

						temp = temp.getDown();
					}

				}
				currentNode = currentNode.getDown();
			}
			rowMarker = rowMarker.getRight();
		}
		// check box
		rowMarker = root;
		temp = root;
		currentNode = root;
		pair = false;
		Node row = root;
		while (rowMarker != null) {
			while (currentNode != null) {
				if (currentNode.countPossible() == 2) {
					row = root;
					temp = root;
					pair = false;
					while (row != null && pair == false) {
						while (temp != null && pair == false) {
							if (Arrays.equals(currentNode.getPossible(), temp.getPossible()) && temp != currentNode
									&& temp.getBoxID() == currentNode.getBoxID()) {
								pair = true;
								boolean[] copy = new boolean[10];
								copy = currentNode.getPossible();
								int possible1 = 0;
								int possible2 = 0;
								for (int x = 1; x < 10; x++) {
									if (copy[x] == true)
										possible1 = x;
								}
								for (int x = 1; x < 10; x++) {
									if (copy[x] == true && x != possible1)
										possible2 = x;
								}
								solveTwoBox(currentNode, temp, possible1);
								solveTwoBox(currentNode, temp, possible2);
								solved = true;
								display();
								System.out.println();
							}
							temp = temp.getRight();
						}
						row = row.getDown();
						temp = row;
					}
				}
				currentNode = currentNode.getRight();
			}
			rowMarker = rowMarker.getDown();
			currentNode = rowMarker;
		}
		return solved;
	}

	public boolean LogicOfThree() {
		Node rowMarker = root;
		Node temp = root;
		Node temp2 = root;
		Node currentNode = root;
		boolean match = false;
		boolean solved = false;
		// check row
		while (rowMarker != null) {
			while (currentNode != null) {
				match = false;
				temp = rowMarker;
				if (currentNode.countPossible() == 3) {
					while (temp != null && match == false) {
						if (Arrays.equals(currentNode.getPossible(), temp.getPossible()) && temp != currentNode) {
							temp2 = temp.getRight();
							while (temp2 != null && match == false) {
								if (Arrays.equals(currentNode.getPossible(), temp2.getPossible())
										&& temp2 != currentNode) {
									match = true;
									boolean[] copy = new boolean[10];
									copy = currentNode.getPossible();
									int possible1 = 0;
									int possible2 = 0;
									int possible3 = 0;
									for (int x = 1; x < 10; x++) {
										if (copy[x] == true)
											possible1 = x;
									}
									for (int x = 1; x < 10; x++) {
										if (copy[x] == true && x != possible1)
											possible2 = x;
									}
									for (int x = 1; x < 10; x++) {
										if (copy[x] == true && x != possible1 && x != possible2)
											possible3 = x;
									}
									solveThreeRow(currentNode, temp, temp2, possible1);
									solveThreeRow(currentNode, temp, temp2, possible2);
									solveThreeRow(currentNode, temp, temp2, possible3);
									solved = true;
									display();
									System.out.println();
								}
								temp2 = temp2.getRight();
							}
						}
						temp = temp.getRight();
					}

				}
				currentNode = currentNode.getRight();
			}
			rowMarker = rowMarker.getDown();
			currentNode = rowMarker;
		}
		// check column
		rowMarker = root;
		temp = root;
		currentNode = root;
		match = false;
		while (rowMarker != null) {
			while (currentNode != null) {
				match = false;
				temp = rowMarker;
				if (currentNode.countPossible() == 3) {
					while (temp != null && match == false) {
						if (Arrays.equals(currentNode.getPossible(), temp.getPossible()) && temp != currentNode) {
							temp2 = temp.getDown();
							while (temp2 != null && match == false) {
								if (Arrays.equals(currentNode.getPossible(), temp2.getPossible())
										&& temp2 != currentNode) {
									match = true;
									boolean[] copy = new boolean[10];
									copy = currentNode.getPossible();
									int possible1 = 0;
									int possible2 = 0;
									int possible3 = 0;
									for (int x = 1; x < 10; x++) {
										if (copy[x] == true)
											possible1 = x;
									}
									for (int x = 1; x < 10; x++) {
										if (copy[x] == true && x != possible1)
											possible2 = x;
									}
									for (int x = 1; x < 10; x++) {
										if (copy[x] == true && x != possible1 && x != possible2)
											possible3 = x;
									}
									solveThreeColumn(currentNode, temp, temp2, possible1);
									solveThreeColumn(currentNode, temp, temp2, possible2);
									solveThreeColumn(currentNode, temp, temp2, possible3);
									solved = true;
									display();
									System.out.println();
								}
								temp2 = temp2.getDown();
							}
						}
						temp = temp.getDown();
					}

				}
				currentNode = currentNode.getDown();
			}
			rowMarker = rowMarker.getRight();
			currentNode = rowMarker;
		}
		// check box
		rowMarker = root;
		temp = root;
		currentNode = root;
		match = false;
		Node row = root;
		Node row2 = root;
		while (rowMarker != null) {
			while (currentNode != null) {
				if (currentNode.countPossible() == 2) {
					row = root;
					temp = root;
					match = false;
					while (row != null && match == false) {
						while (temp != null && match == false) {
							if (Arrays.equals(currentNode.getPossible(), temp.getPossible()) && temp != currentNode
									&& temp.getBoxID() == currentNode.getBoxID()) {
								while (temp2 != null && match == false) {
									temp2 = temp;
									if (Arrays.equals(currentNode.getPossible(), temp2.getPossible())
											&& temp2 != currentNode && temp2.getBoxID() == currentNode.getBoxID()) {
										match = true;
										boolean[] copy = new boolean[10];
										copy = currentNode.getPossible();
										int possible1 = 0;
										int possible2 = 0;
										int possible3 = 0;
										for (int x = 1; x < 10; x++) {
											if (copy[x] == true)
												possible1 = x;
										}
										for (int x = 1; x < 10; x++) {
											if (copy[x] == true && x != possible1)
												possible2 = x;
										}
										for (int x = 1; x < 10; x++) {
											if (copy[x] == true && x != possible1 && x != possible2)
												possible3 = x;
										}
										solveThreeBox(currentNode, temp, temp2, possible1);
										solveThreeBox(currentNode, temp, temp2, possible2);
										solveThreeBox(currentNode, temp, temp2, possible3);
										solved = true;
										display();
										System.out.println();
									}
									temp2 = temp2.getRight();
								}
								row2 = row2.getDown();
								temp2 = row;
							}
							temp = temp.getRight();

						}
						row = row.getDown();
						temp = row;
					}
				}
				currentNode = currentNode.getRight();
			}
			rowMarker = rowMarker.getDown();
			currentNode = rowMarker;
		}
		return solved;
	}
	
	public void display() {
		Node rowMarker = root;
		int counter2 = 1;
		while (rowMarker != null) {
			Node temp = rowMarker;
			int counter1 = 1;

			while (temp != null) {
				System.out.print(temp.getSolution() + " ");
				temp = temp.getRight();
				if (counter1 % 3 == 0)
					System.out.print(" | ");
				counter1++;
			}
			System.out.println();
			rowMarker = rowMarker.getDown();
			if (counter2 % 3 == 0)
				System.out.println("--------------------------");
			counter2++;
		}
	}
	
	public void diagnose() {
		Node temp = root;
		int choice = 0;
		Scanner input = new Scanner(System.in);
		do {
			temp.displayEverything();

			System.out.println("0.Exit");
			System.out.println("8.Go Up");
			System.out.println("2.Go Down");
			System.out.println("4.Go Left");
			System.out.println("6.Go Right");

			choice = input.nextInt();

			if (choice == 8)
				temp = temp.getUp();
			else if (choice == 2)
				temp = temp.getDown();
			else if (choice == 4)
				temp = temp.getLeft();
			else if (choice == 6)
				temp = temp.getRight();

		} while (choice != 0);

	}
	
	public void solvemethod1(Node currentNode) {
		if (currentNode.getSolution() == 0 && currentNode.numberOfPossibilities() == 1) {
			for (int x = 0; x < 10; x++) {
				if (currentNode.getPossible()[x] == true) {
					solve(currentNode, x);
					break;
				}
			}
		}
	}

}