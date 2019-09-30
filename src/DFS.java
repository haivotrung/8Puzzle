import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;


public class DFS extends Solver {
    public DFS(Board startBoard, Heuristic h) {
        super(startBoard, h);
    }

    private List<Board> solve() {
        Board current = super.b;

        ArrayList<Board> visited = new ArrayList<>();
        Stack<Board> frontier = new Stack<>();
        System.out.println("Depth-First Search Initializing\n");
        long startTime = System.nanoTime();

        frontier.push(current);

        while (!frontier.isEmpty() && !current.isGoal()) {
            current = frontier.pop();

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


