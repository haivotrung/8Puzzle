public class Main {

    public static void main(String[] args) {
        Board b1 = new Board(3);

        System.out.print(b1.toString());
        System.out.println(b1.isSolvable());
        /*Heuristic h1 = new MovesMadeHeuristic( );
        Solver s1 = new UniformCostSolver(b1, h1);
        List<Board> solution = s1.getSolution( );
        for (Board step: solution) {
            System.out.println(step);
        }*/
    }
}
