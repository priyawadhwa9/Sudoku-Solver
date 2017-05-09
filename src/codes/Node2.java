package codes;

public class Node2 {
	private int data;
	private Node2 next;
	private Node2 prev;
	private int[] board = new int[81];
	public Node2() {
		next = null;
		prev = null;
	}
	public int[] getBoard() {
		return board;
	}
	public void setBoard(int[] board) {
		this.board = board;
	}
	public void setBoardAt(int index, int number)
	{
		this.board[index] = number;
	}
	public Node2(int dataIn) {
		data = dataIn;
		next = null;
		prev = null;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node2 getNext() {
		return next;
	}
	public void setNext(Node2 next) {
		this.next = next;
	}
	public Node2 getPrev() {
		return prev;
	}
	public void setPrev(Node2 prev) {
		this.prev = prev;
	}
}
