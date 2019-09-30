public class weightedCostHeuristic implements Heuristic {
    private int weightedCost;

    public weightedCostHeuristic() {
        weightedCost = 0;
    }

    public String getName() {
        return "Weighted-Cost Search Initializing\n";
    }

    public int getValue(Board b) {
        weightedCost = b.getParentBoard().getCost() + b.getTileAt(b.getParentBoard().getZeroPosition()[0], b.getParentBoard().getZeroPosition()[1]);
        return weightedCost;
    }
}
