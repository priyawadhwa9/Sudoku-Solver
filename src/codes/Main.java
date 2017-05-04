package codes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LinkedGrid lg = new LinkedGrid(9);
		while (lg.getSolvedCounter() < 82) {
			
			while (lg.solveOnlyPossible())
				lg.solveOnlyPossible();
			while (lg.solveByElimination())
				lg.solveByElimination();
			lg.LogicOfTwo();
			lg.LogicOfThree();
		}
		lg.diagnose();

		System.out.println("done");
		lg.diagnose();

	}
}