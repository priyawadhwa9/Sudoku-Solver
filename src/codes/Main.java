package codes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LinkedGrid lg = new LinkedGrid(9);
		lg.display();
		boolean continueSolving = false;
		do
		{
			continueSolving = lg.LogicOfTwoRow();
		}while(continueSolving == true);
		do
		{
			continueSolving = lg.LogicOfTwoColumn();
		}while(continueSolving == true);
		lg.diagnose();


	}

}
