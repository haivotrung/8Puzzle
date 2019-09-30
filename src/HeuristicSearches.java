import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class HeuristicSearches extends Solver {
    public HeuristicSearches(Board startBoard, Heuristic h) {
        super(startBoard, h);
        System.out.println(h.getName()); //print names of different heuristics
    }

    private List<Board> solve() {
        ArrayList<Board> visited = new ArrayList<>();

        Board current = super.b;
        current.setCost(0);

        PriorityQueue<Board> frontier = new PriorityQueue<>();

        long startTime = System.nanoTime();
        frontier.add(current);

        while (!current.isGoal()) {
            current = frontier.poll();

            for (Board n : current.neighbors()) {
                if (!visited.contains(n) && !frontier.contains(n)) {
                    frontier.add(n);
                }
            }
            visited.add(current);
        }

        long endTime = System.nanoTime();
        long timeConverted = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);

        System.out.println("Moves made: " + visited.size());
        System.out.println("Time elapsed: " + timeConverted + " milliseconds" + "\n");

        solution.add(current);
        Board boardIter = current.getParentBoard();
        while (boardIter != null) {
            solution.add(boardIter);
            boardIter = boardIter.getParentBoard();
        }
        return solution;
    }

    @Override
    public List<Board> getSolution() {
        return solve();
    }
}
