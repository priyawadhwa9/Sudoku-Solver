package codes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LinkedGrid lg = new LinkedGrid(9);
		lg.display();
		//lg.diagnose();
		boolean continueSolving = false;
		for(int x = 0; x < 20; x++)
		{
			do
			{
				continueSolving = lg.solveOnlyPossible();
				}while(continueSolving == true);
			lg.LogicOfTwoColumn();
			lg.LogicOfTwoRow();
		}
		
		lg.diagnose();


	}

}
