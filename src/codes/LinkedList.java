package codes;

public class LinkedList {
	Node2 first;
	Node2 last;

	public LinkedList() {
		first = null;
		last = null;
	}

	public Node2 getLast() {
		return last;
	}

	public void push(int[] board, int guess) {
		if (first == null) {
			Node2 temp = new Node2(guess);
			first = temp;
			last = temp;
			temp.setBoard(board);
		} else {
			Node2 temp = new Node2(guess);
			last.setNext(temp);
			temp.setPrev(last);
			last = temp;
			temp.setBoard(board);
		}
	}

	public void pop() {

		if (first == null)
			return;
		else if (first == last) {

			first = null;
			last = null;
		} else {
			last = last.getPrev();
			last.setNext(null);
		}

	}

	public void pop(int data) {
		Node2 temp = first;
		while (temp.getData() != data && temp.getNext() != null)
			temp = temp.getNext();
		if (temp.getData() == data && temp != last) {
			temp.getNext().setPrev(temp.getPrev());
			temp.getPrev().setNext(temp.getNext());
		} else if (temp.getData() == data && temp == last)
			pop();

	}

	public void display() {
		Node2 temp = first;
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
		System.out.println();
		temp = last;
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getPrev();
		}
		System.out.println();
	}

	public Node2 find(int data) {
		Node2 temp = first;
		while (temp.getData() != data && temp.getNext() != null)
			temp = temp.getNext();
		if (temp.getData() == data)
			return temp;
		else
			return null;
	}

	public void swap(int Node21, int Node22) {
		Node2 temp1 = find(Node21);
		Node2 temp2 = find(Node22);
		Node2 temp1Counter = temp1;
		Node2 temp2Counter = temp2;
		int Counter1 = 0;
		int Counter2 = 0;
		if (temp1 != null && temp2 != null) {

			while (temp1Counter != last) {
				temp1Counter = temp1Counter.getNext();
				Counter1++;
			}
			while (temp2Counter != last) {
				temp2Counter = temp2Counter.getNext();
				Counter2++;
			}
			if (Counter1 < Counter2) {
				Node2 temp = temp1;
				temp1 = temp2;
				temp2 = temp;
			}

			if (temp1.getNext() == temp2) {
				temp1.setNext(temp2.getNext());
				temp2.setPrev(temp1.getPrev());
				if (temp1 != first)
					temp1.getPrev().setNext(temp2);
				if (temp2 != last)
					temp1.getNext().setPrev(temp1);
				temp1.setPrev(temp2);
				temp2.setNext(temp1);
			} else if (temp1.getNext() == temp2.getPrev()) {
				temp2.setPrev(temp1.getPrev());
				temp1.setPrev(temp1.getNext());
				temp1.setNext(temp2.getNext());
				temp2.setNext(temp1.getPrev());
				temp1.getPrev().setNext(temp1);
				temp2.getNext().setPrev(temp2);
				if (temp1 != first)
					temp2.getPrev().setNext(temp2);
				if (temp2 != last)
					temp1.getNext().setPrev(temp1);
			} else {
				Node2 temp3 = temp1.getNext();
				Node2 temp4 = temp2.getPrev();
				temp1.setNext(temp2.getNext());
				if (temp2 != last)
					temp1.getNext().setPrev(temp1);
				temp3.setPrev(temp2);
				temp2.setPrev(temp1.getPrev());
				if (temp1 != first)
					temp2.getPrev().setNext(temp2);
				temp1.setPrev(temp4);
				temp2.setNext(temp3);
				temp4.setNext(temp1);
			}
			if (temp1 == first && temp2 == last) {
				first = temp2;
				last = temp1;
			} else if (temp1 == first)
				first = temp2;
			else if (temp2 == last)
				last = temp1;

		}
	}
}
