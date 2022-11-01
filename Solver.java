import java.util.Arrays;
/**
 * Solver that calculates the solution(s) to an n queen(s) problem.
 */
public class Solver
{
    private int noOfQueens; //Number og queens
    private int[] queens; //An array with an element for each row and an integer corresponding to the column for that queen.
    private int noOfSolutions; //Number of solutions for any given n queens.
    private boolean showSolutions = true; //Used to specify if the solution should be printed.
    /**
     * Inserts any given number of queens and prints out the solutions.
     *
     * @param noOfQueens Number of queens to be placed.
     */
    public void findAllSolutions(int noOfQueens) {
        this.noOfQueens = noOfQueens;
        queens = new int[noOfQueens]; //Creates an array with the size of noOfQueens.
        this.noOfSolutions = 0;
        if(showSolutions) {
            System.out.println("***************************************************");
            System.out.println("Solutions for " + noOfQueens + " Queens:");
            System.out.println(" ");
        }
        long starting = System.currentTimeMillis(); //Timer used to calculate how long the solutions that to print.
        positionQueens(0);
        long ending = System.currentTimeMillis();
        if(showSolutions) {
            System.out.println(" ");
            System.out.println("A total of " + noOfSolutions + " solutions were found in: " + (ending-starting) + " ms");
            System.out.println("***************************************************");
        }
        //Test for how many queens solutions that can be found within 3 min.
        //15 Queens took 28,941 sec to find all solutions.
        //16 Queens took 3,28 min to find all solutions.
        //So therefor within 3 min is 15 Queens the maximum, tested with a Macbook Pro M1 chip.

    }
    /**
     * Positions a queen in the specified row from the parameter.
     *
     * @param row The given row to place a queen in.
     */
    private void positionQueens(int row) {
        if(row < noOfQueens) {
            for(int i = 0; i < noOfQueens; i++) { //Runs through all the columns to check where the queen can be placed.
                if(legal(row, i)) { //Uses the Legal method to check if a queen can be placed at column i.
                    queens[row] = i;
                    positionQueens(row+1);
                }
            }
        } else {
            noOfSolutions++;
            printSolution();
        }
    }
    /**
     * Method used to check if a queen can be placed at (row & column)
     *
     * @param row The row for which the queens stand on.
     * @param col The column for which the queens stand on.
     * @return boolean used to check if queen can be placed true if it can be placed and false if not.
     */
    private boolean legal(int row, int col) {
        for(int i = row-1; i >= 0; i--) { //First for loop checks below.
            if(queens[i] == col) {
                return false;
            }
        }
        for(int i = row-1, j = col-1; i >= 0; i--, j--) { //Second for loop checks the diagonal to the left.
            if(queens[i] == j) {
                return false;
            }
        }
        for(int i = row-1, j = col+1; i >= 0; i--, j++) { //Third for loop checks the diagonal to the right.
            if(queens[i] == j) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints out the solution.
     */
    private void printSolution() {
        if(showSolutions) {
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < noOfQueens; i++) {
                result.append(" ").append(convert(i, queens[i]));
        }
            System.out.println(result);
        }
    }
    /**
     * Method used to convert row and column values to chess notation.
     *
     * @param row Given row.
     * @param col Given column.
     * @return Returns a string with chess notation.
     */
    private String convert(int row, int col) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        return letters.charAt(col) + String.valueOf(row + 1);
    }

    /**
     * Method used to run through an interval of queens with the use of findAllSolutions to print out a table containing details to the solution.
     *
     * @param min Lowest number of queens in the given interval.
     * @param max Highest number of queens in the given interval
     */
    public void findNoOfSolutions(int min, int max) {
        showSolutions = false; //Sets to false so findAllSolutions doesn't print out its solution.
        System.out.println("***************************************************");
        System.out.println("Queens      Solutions     Time(ms)      Solution/ms");
        for(int i = min; i <= max; i++) { //Runs through the entire interval.
            long starting = System.currentTimeMillis();
            findAllSolutions(i);
            long ending = System.currentTimeMillis();
            long duration = 1 + ending-starting;
            System.out.format("%6d  %,12d  %,10d %,12d %n", noOfQueens, noOfSolutions, duration, noOfSolutions/duration);
        }
        System.out.println("***************************************************");
        showSolutions = true;
    }
    /**
     * The two below methods are used to check if the legal code works.
     */
    public static void main(String[] args) {
        Solver s = new Solver();
        s.testLegal(8,4,6,3,5);
    }

    public void testLegal(int n, int... pos) {
        noOfQueens = n;
        queens = Arrays.copyOf(pos, n);
        System.out.print(n + "x" + n + " with queens in: " +
                Arrays.toString(pos) + " => Legal positions: ");
        for(int i=0; i < n; i++) {
            if( legal(pos.length,i) ) { System.out.print(i + " "); }
        }
        System.out.println();
    }
}
