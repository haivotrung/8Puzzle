public class hammingAStarHeuristic implements Heuristic {
    private int cost;

    public hammingAStarHeuristic() {
        cost = 0;
    }

    public String getName() {
        return "Hamming A Star Search Initializing\n";
    }


    public int getValue(Board b1) {
        cost = b1.getDistanceFromStart() + b1.hamming();
        return cost;
    }
}
