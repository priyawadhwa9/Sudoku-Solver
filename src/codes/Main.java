package codes;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		LinkedGrid lg = new LinkedGrid(9);
		LinkedList ll = new LinkedList();
		boolean guessed = false;
		lg.display();
		
		while (lg.getSolvedCounter() < 81) {
			int unsolved = 0;
			while (lg.getSolvedCounter() < 81 && unsolved < 100) {
				boolean solved = false;
				if (guessed == true)
					if (lg.checkLogic() == false)
					{
						lg.restore(ll.getLast().getBoard());
						int guess = ll.getLast().getData();
						for(int x = guess; x > 0; x--)
							lg.getFirstAvailiable().setPossibilityFalse(x);
						ll.pop();
						
					}
				if (lg.solveOnlyPossible() == true)
					solved = true;
				while (lg.solveOnlyPossible())
					lg.solveOnlyPossible();
				if (lg.solveByElimination() == true)
					solved = true;
				while (lg.solveByElimination())
					lg.solveByElimination();

				lg.LogicOfTwo();
				lg.LogicOfThree();
				if (solved == false)
					unsolved++;
				
			}
			if (lg.getSolvedCounter() < 81) {
				
				if (guessed == true)
					if (lg.checkLogic() == false)
					{
						lg.restore(ll.getLast().getBoard());
						int guess = ll.getLast().getData();
						for(int x = guess; x > 0; x--)
							lg.getFirstAvailiable().setPossibilityFalse(x);
						ll.pop();
					}
				ll.push(lg.getBoard(), lg.getGuess());
				lg.solve(lg.getFirstAvailiable(), lg.getGuess());
				
				guessed = true;
				
			}
		}
		lg.display();
		System.out.println(lg.getSolvedCounter());
		System.out.println("done");
		lg.diagnose();

	}
}