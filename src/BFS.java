import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BFS extends Solver {
    public BFS(Board startBoard, Heuristic h) {
        super(startBoard, h);
    }


    private List<Board> solve() {
        ArrayList<Board> visited = new ArrayList<>();
        Queue<Board> frontier = new LinkedList<>();

        Board current = super.b;
        System.out.println("Breadth-First Search Initializing\n");
        long startTime = System.nanoTime();

        frontier.add(current);

        while (!current.isGoal()) {
            current = frontier.poll();

            for (Board neighbor : current.neighbors()) {
                if (!visited.contains(neighbor) && !frontier.contains(neighbor)) {
                    frontier.add(neighbor);
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

    public List<Board> getSolution() {
        return solve();
    }
}
