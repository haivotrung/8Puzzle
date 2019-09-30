import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board implements Comparable<Board> {

    private final int size;
    private final int[][] tiles;
    private Board parentBoard;
    private int cost;
    private int moves;
    private Heuristic boardHeuristic;

    // construct a random board from an size-by-size array of blocks
    public Board(int size) {
        this.size = size;
        this.parentBoard = null;
        this.moves = 0;
        this.tiles = new int[size][size];

        //generate random numbers
        List<Integer> range = IntStream.range(0, size * size).boxed().collect(Collectors.toList());
        Collections.shuffle(range);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (range.size() > 0) {
                    int temp = range.get(0);
                    tiles[i][j] = temp;
                    range.remove(0);
                }
            }
        }
    }

    // get inversion count
    private int getInvCount() {
        int invCount = 0;
        List<Integer> array = new ArrayList<>();

        //convert 2D array to 1D array
        for (int[] tile : tiles) {
            for (int j = 0; j < tiles.length; j++) {
                array.add(tile[j]);
            }
        }

        Integer[] convertedArray = new Integer[array.size()];
        array.toArray(convertedArray);

        for (int i = 0; i < convertedArray.length - 1; i++) {
            for (int j = i + 1; j < convertedArray.length; j++) {
                if (convertedArray[i] != 0 && convertedArray[j] != 0 && convertedArray[i] > convertedArray[j]) {
                    invCount++;
                }
            }
        }

        return invCount;
    }

    // find Position of blank row from bottom
    private int findBlankRow() {
        int blankRow = 0;
        // start from bottom-right corner of matrix
        for (int i = size - 1; i >= 0; i--)
            for (int j = size - 1; j >= 0; j--)
                if (tiles[i][j] == 0) {
                    blankRow = size - i;
                    break;
                }
        return blankRow;
    }

    // is the board solvable?
    public boolean isSolvable() {
        // Count inversions in given puzzle
        int invCount = getInvCount();

        // If grid is odd, return true if inversion
        // count is even.
        if (size % 2 == 0) {
            return ((invCount % 2) == 0);
        } else     // grid is even
        {
            int pos = findBlankRow();
            return !((invCount + pos) % 2 == 0);
        }
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (size == 4) {
            return (manhattan() == 0);
        } else {
            return (getInvCount() == 0) && (getZeroPosition()[0] == (size - 1) && getZeroPosition()[1] == (size - 1));
        }
    }

    // the heuristic to use
    public void setHeuristic(Heuristic h) {
        this.boardHeuristic = h;
    }

    // value of the heuristic on this board
    private int getValue() {
        return boardHeuristic.getValue(this);
    }

    //compare function for PriorityQueue
    public int compareTo(Board board) {
        if (this.cost > board.cost) {
            return 1;
        } else if (this.cost < board.cost) {
            return -1;
        }
        return 0;
    }

    // returns the tile at (x, y)
    public int getTileAt(int x, int y) {
        return tiles[x][y];
    }

    // number of blocks out of place
    public int hamming() {
        int outOfPlaces = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tileVal = getTileAt(i, j);
                if (tileVal == 0) { // the zero tile
                    continue;
                }

                // check to see if the tile value that's goal
                if (tileVal != i * size + j + 1) {
                    outOfPlaces++;
                }
            }
        }
        return outOfPlaces;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int mdists = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tileVal = getTileAt(i, j);
                if (tileVal == 0) { // the zero tile
                    continue;
                }

                mdists += Math.abs(i - ((tileVal - 1) / size)); // row values
                mdists += Math.abs(j - ((tileVal - 1) % size)); // col values
            }
        }
        return mdists;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int x = 0;
        int y = 0;
        ArrayList<Board> nboards = new ArrayList<>();

        x = getZeroPosition()[0];
        y = getZeroPosition()[1];

        Board bc = makeCopy();
        Board bc1 = makeCopy();
        Board bc2 = makeCopy();
        Board bc3 = makeCopy();

        if (x - 1 >= 0) {
            bc.swap(x, y, x - 1, y);
            bc.setCost(bc.getValue());
            nboards.add(bc);
        }
        if (x + 1 < size) {
            bc1.swap(x, y, x + 1, y);
            bc1.setCost(bc1.getValue());
            nboards.add(bc1);
        }
        if (y - 1 >= 0) {
            bc2.swap(x, y, x, y - 1);
            bc2.setCost(bc2.getValue());
            nboards.add(bc2);
        }
        if (y + 1 < size) {
            bc3.swap(x, y, x, y + 1);
            bc3.setCost(bc3.getValue());
            nboards.add(bc3);
        }
        return nboards;
    }

    public Board getParentBoard() {
        return parentBoard;
    }

    //get cost for PriorityQueue for Heuristics
    public int getCost() {
        return cost;
    }

    //change cost for PriorityQueue for Heuristics
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int[] getZeroPosition() {
        int[] zeroPos = new int[2];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == 0) {
                    zeroPos[0] = i;
                    zeroPos[1] = j;
                }
            }
        return zeroPos;
    }

    // set the tile at (x, y) to val
    private void setTileAt(int x, int y, int val) {
        tiles[x][y] = val;
    }

    // swap the tiles at (x1, y1) and (x2, y2)
    private void swap(int x1, int y1, int x2, int y2) {
        int swapTile = getTileAt(x1, y1);

        setTileAt(x1, y1, getTileAt(x2, y2));
        setTileAt(x2, y2, swapTile);
    }

    // create a new Board with the same configuration as the parameter
    private Board makeCopy() {
        Board newBoard = new Board(size);


        for (int i = 0; i < size; i++)
            System.arraycopy(this.tiles[i], 0, newBoard.tiles[i], 0, size);

        newBoard.parentBoard = this;
        newBoard.setHeuristic(this.boardHeuristic);
        newBoard.moves = this.moves + 1;

        return newBoard;
    }


    // string representation of the board
    // in the output format specified above
    public String toString() {

        StringBuilder boardString = new StringBuilder();
        for (int[] tile : tiles) {
            for (int j = 0; j < tiles.length; j++) {
                boardString.append(tile[j]).append(" ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.size != this.size)
            return false;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        return true;
    }

    // number of moves required to get to this state from the start state
    public int getDistanceFromStart() {
        return moves;
    }
}
