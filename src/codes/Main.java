package codes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LinkedGrid lg = new LinkedGrid(9);
		
		lg.display();
		
		lg.display();
		// lg.diagnose();

		int unsolved = 0;
		while (lg.getSolvedCounter() < 82) {
			unsolved = 0;
			while (lg.getSolvedCounter() < 82 && unsolved < 20) {
				while (lg.solveOnlyPossible())
					lg.solveOnlyPossible();
				if (lg.LogicOfTwo() == false && lg.LogicOfThree() == false)
					unsolved++;
			}
			lg.diagnose();
			// if(lg.getSolvedCounter()<82)
			// lg.guess();
		}

		

	}

}
