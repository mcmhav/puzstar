package puz;

import java.util.Vector;

public class EightPuzzle extends AStar{

  public EightPuzzle(Node i, Node g) {
    initialnode = i;
    goalnode = g;
  }

  public static void main(String[] args) {

	 int heuristic = 1;
	 int initial_state = 3;
	 
	 if (args.length > 0) {
		int arg = Integer.parseInt(args[0]);
		if ((0 <= arg) && (arg <= 4)) 
		  heuristic = arg;
	 }

	 if (args.length > 1) {
		int arg = Integer.parseInt(args[1]);
		if ((0 < arg) && (arg <= 4)) 
		  initial_state = arg;
	 }

    // Define initial and final states
	 int[] init1 = {2, 8, 3, 1, 6, 4, 7, 0, 5};   // Test example 1
	 int[] init2 = {5, 4, 0, 6, 1, 7, 8, 3, 2};   // Test example 2
	 int[] init3 = {5, 6, 7, 4, 0, 8, 3, 2, 1};   // Test example 3
	 int[] init4 = {0, 8, 3, 2, 1, 4, 7, 6, 5};   // Test example 4

	 int[] initarray = init1;
	 if (initial_state == 2)
		initarray = init2;
	 else if (initial_state == 3) 
		initarray = init3;
	 else if (initial_state == 4) 
		initarray = init4;
	 
    int[] goalarray = {1, 2, 3, 8, 0, 4, 7, 6, 5};

    // Create start and end states and nodes
    EightPuzzleState initsta = new EightPuzzleState(initarray);
    EightPuzzleState goalsta = new EightPuzzleState(goalarray);
    Node in = new Node(initsta, 0);
    Node go = new Node(goalsta, 0);

    // Instantiate EightPuzzle
    EightPuzzle puzzle = new EightPuzzle(in, go);

	 System.out.println("EightPuzzle");
	 System.out.println("Solving Eight Puzzle using heuristic: " + Integer.toString(heuristic));
	 System.out.println("Initial state: " + initsta.toString());
	 System.out.println("Goal    state: " + goalsta.toString());

    // Set the heuristic
	 EightPuzzleState.heuristic = heuristic;

	 // Solve
    puzzle.solve();
	 System.out.println("Done!\n");
  }

} // End class EightPuzzle