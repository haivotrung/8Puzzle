public class manhattanAStarHeuristic implements Heuristic {
    private int cost;

    public manhattanAStarHeuristic() {
        cost = 0;
    }

    public String getName() {
        return "Manhattan A Star Search Initializing\n";
    }

    public int getValue(Board b1) {
        cost = b1.getDistanceFromStart() + b1.manhattan();
        return cost;
    }
}
