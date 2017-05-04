package codes;

public class Node {

	private int solution;
	private boolean[] possible = new boolean[10];
	private int boxID;
	private Node up;
	private Node down;
	private Node left;
	private Node right;

	public Node(int data) {
		for (int x = 1; x < 10; x++)
			getPossible()[x] = true;

		this.solution = data;
		up = null;
		down = null;
		left = null;
		right = null;
	}

	public void displayEverything() {
		System.out.println("Solution:" + solution);
		System.out.println("BoxID:" + boxID);
		System.out.println("Possibilities:");
		for (int x = 1; x < 10; x++)
			System.out.println(x + ":" + getPossible()[x]);
		System.out.println();
	}

	public int countPossible() {
		int counter = 0;
		for (int x = 1; x < 10; x++)
			if (possible[x] == true)
				counter++;
		return counter;
	}

	public int getOnlyPossible() {

		for (int x = 1; x < 10; x++)
			if (possible[x] == true)
				return x;
		return 0;

	}

	public boolean getPossibleAt(int index) {
		return possible[index];
	}

	public boolean[] getPossible() {
		return possible;
	}

	public void setPossible(boolean[] possible) {
		this.possible = possible;
	}

	public int getBoxID() {
		return boxID;
	}

	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}

	public void setPossibilityFalse(int number) {
		getPossible()[number] = false;
	}

	public int numberOfPossibilities() {
		int possibilities = 0;

		for (int x = 1; x < 10; x++) {
			if (getPossible()[x] == true) {
				possibilities++;
			}
		}

		return possibilities;
	}

	public int getSolution() {
		return solution;
	}

	public void setSolution(int data) {
		this.solution = data;
		for (int x = 0; x < 10; x++)// turn off all possibilities
			getPossible()[x] = false;
		getPossible()[data] = true;// turn on the possibility for the given data
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

}