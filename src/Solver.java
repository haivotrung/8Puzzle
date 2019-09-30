import java.util.ArrayList;
import java.util.List;

public abstract class Solver {
    final Board b;
    final List<Board> solution;

    Solver(Board startBoard, Heuristic h) {
        b = startBoard;
        b.setHeuristic(h);
        solution = new ArrayList<>();

    }

    public abstract List<Board> getSolution();
}

