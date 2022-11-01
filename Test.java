public class Test {
    public static void main(String[] args) {
        Solver s = new Solver();
        s.findAllSolutions(1); //Find the solution to 1 queen.

        System.out.println(" ");

        s.findAllSolutions(2); //Find the solutions to 2 queens.

        System.out.println(" ");

        s.findAllSolutions(6); //Find the solutions to 6 queens.

        System.out.println(" ");

        s.findNoOfSolutions(1,12); //Runs through the interval of queens from 1 to 12.
    }
}


