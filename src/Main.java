import java.util.Collections;
import java.util.List;

class Main {

    public static void main(String[] args) {
        Board b1 = new Board(3);
        while (!b1.isSolvable()) {
            System.out.println(b1);
            System.out.println("Unsolvable. Generating new Board..." + "\n");
            b1 = new Board(3);
        }
        // copies
        Board b = b1;
        Board b2 = b1;
        Board b3 = b1;
        Board b4 = b1;

        System.out.println(b1);

        //weighted-cost
        Heuristic h = new weightedCostHeuristic();
        Solver s = new HeuristicSearches(b2, h);
        List<Board> solution = s.getSolution();
        printSolution(solution);

        //Breadth-First
        Solver s3 = new BFS(b3, h);
        List<Board> solution3 = s3.getSolution();
        printSolution(solution3);

        //Depth First
        Solver s4 = new DFS(b4, h);
        List<Board> solution4 = s4.getSolution();
        printSolution(solution4);

        //Manhattan A Star
        Heuristic h1 = new manhattanAStarHeuristic();
        Solver s1 = new HeuristicSearches(b1, h1);
        List<Board> solution1 = s1.getSolution();
        printSolution(solution1);

        //Hamming A Star
        Heuristic h2 = new hammingAStarHeuristic();
        Solver s2 = new HeuristicSearches(b, h2);
        List<Board> solution2 = s2.getSolution();
        printSolution(solution2);

    }

    private static void printSolution(List<Board> s) {
        Collections.reverse(s);
        if (s.size() > 10) {
            List<Board> firstFive = s.subList(0, 5);
            List<Board> lastFive = s.subList(s.size() - 5, s.size());
            for (Board step : firstFive) System.out.println(step);
            System.out.println("...");
            for (Board step : lastFive) System.out.println(step);
        } else {
            for (Board step : s) System.out.println(step);
        }
        System.out.println("Moves to goal: " + (s.size() - 1) + "\n");
    }
}
