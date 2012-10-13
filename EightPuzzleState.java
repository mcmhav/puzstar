package puz;
import java.util.Vector;

public class EightPuzzleState extends State {

  static int heuristic = 0;  // static class variable to select heuristic
                             // to call from estimate

  public int[] value;        // object data repr.

  // constructor
  public EightPuzzleState(int[] v) {
    value = v;
  }

  // equality check
  public boolean equals(State state) {
	 EightPuzzleState s = (EightPuzzleState)state;
    boolean flag = true;
    for (int i = 0; i < 9; i++) if (value[i] != s.value[i]) flag = false;
    return flag;
  }

  // to String conversion; for printing
  public String toString() {
    String s = "(";
    for (int i = 0; i < 9; i++)
      s = s + value[i] + ",";    
    return s + "\b)";
  }

  // successor states
  public Vector successors() {
	 Vector s = new Vector();
	 int hole = 0;
	 
	 // Locate hole
	 for (int i = 0; i < 9; i++) if (value[i] == 0) hole = i;

	 // move up OK?
	 if ((hole / 3) > 0) addSuccessor(hole, hole - 3, s, value);
	 // move down OK?
	 if ((hole / 3) < 2) addSuccessor(hole, hole + 3, s, value);
	 // move left OK?
	 if ((hole % 3) > 0) addSuccessor(hole, hole - 1, s, value);
	 // move right OK?
	 if ((hole % 3) < 2) addSuccessor(hole, hole + 1, s, value);
	 return s;
  }

  // helper for successors method; adds a new successor state to vector
  private void addSuccessor(int old_loc, int new_loc, Vector v, int[] old) {
	 int[] val = (int[])old.clone();
	 val[old_loc] = val[new_loc];
	 val[new_loc] = 0;
	 v.insertElementAt(new EightPuzzleState(val), 0);
  }

  // interface to estimate h
  public int estimate(State goal) {
	 EightPuzzleState goalstate = (EightPuzzleState)goal;
	 int est = 0;
	 if (heuristic == 0) est = heuristic0(goalstate);
	 else if (heuristic == 1) est = heuristic1(goalstate);
	 else if (heuristic == 2) est = heuristic2(goalstate);
	 else if (heuristic == 3) est = heuristic3(goalstate);
	 else if (heuristic == 4) est = heuristic4(goalstate);
	 return est;
  }

  // h^_0 = 0
  private int heuristic0(EightPuzzleState goalstate) {
	 return 0;
  }

  // h^_1 = number of misplaced tiles
  private int heuristic1(EightPuzzleState goalstate) {
	 int[] goal = goalstate.value;
	 int misplaced = 0;
	 for (int i = 0; i < 9; i++) if (goal[i] != value[i]) misplaced++;
	 return misplaced;
  }

  // h^_2 = Manhattan distance
  private int heuristic2(EightPuzzleState goalstate) {
	 int[] goal = goalstate.value;
	 int distance = 0;
	 for (int i = 0; i < 9; i++) {
		int c = value[i];
		int v = 0;
		for (int j = 0; j < 9; j++) if (c == goal[j]) v = j;
		if (c != 0)
		  distance = distance + 
			 Math.abs((i % 3) - (v % 3)) +  // horisontal distance
			 Math.abs((i / 3) - (v / 3));   // vertical distance
	 }
	 return distance;
  }
  
  // h^_3 = 2 * Manhattan distance; not admissible
  private int heuristic3(EightPuzzleState goalstate) {
	 int[] goal = goalstate.value;
	 int distance = 0;
	 for (int i = 0; i < 9; i++) {
		int c = value[i];
		int v = 0;
		for (int j = 0; j < 9; j++) if (c == goal[j]) v = j;
		if (c != 0)
		  distance = distance + 
			 Math.abs((i % 3) - (v % 3)) +  // horisontal distance
			 Math.abs((i / 3) - (v / 3));   // vertical distance
	 }
	 return 2*distance;
  }

  // h^_3 = Sum of squared Manhattan distance components; not admissible
  private int heuristic4(EightPuzzleState goalstate) {
	 int[] goal = goalstate.value;
	 int distance = 0;
	 for (int i = 0; i < 9; i++) {
		int c = value[i];
		int v = 0;
		for (int j = 0; j < 9; j++) if (c == goal[j]) v = j;
		if (c != 0) {
		  int xd = Math.abs((i % 3) - (v % 3));
		  int yd = Math.abs((i / 3) - (v / 3));
		  distance = distance + (xd * xd) + (yd * yd);
		}
	 }
	 return distance;
  }

}  // End class EightPuzzleState}