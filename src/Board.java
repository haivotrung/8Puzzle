import java.util.*;
import java.util.stream.*;

public class Board {

    private int size;
    private int tilesNum;
    private int[][] tiles;
    public int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
    
    // construct a random board from an size-by-size array of blocks
    public Board(int size) {
        this.size = size;
        tilesNum = (size * size) -1; //

        tiles = new int[size][size];

        List<Integer> range = IntStream.range(1, size*size).boxed().collect(Collectors.toList());
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

    public int getInvCount(int[][] tiles){
        int invCount = 0;
        for (int i = 0; i < size - 1; i++){
            for (int j = i + 1; j < size; j++){

                if (tiles[i][j] > 0 && tiles[j][i] > 0 && tiles [j][i] > tiles[i][j]){
                    invCount++;
                }
            }
        }
        return invCount;
    }



/*
    // the heuristic to use
    public void setHeuristic(Heuristic h)

    // value of the heuristic on this board
    public int getValue( )
*/

    // board size N
    public int getSize() {
        return size;
    }

//    // returns the tile at (x, y)
//    public int getTileAt(int x, int y)

    // is this board the goal board?
    public boolean isGoal() {
        return Arrays.equals(tiles, goal);
    }

//    // does this board equal y?
//    public boolean equals(Object y)

//    // all neighboring boards
//    public Iterable<Board> neighbors()

    // is the board solvable?
    public boolean isSolvable(){
        return (getInvCount(tiles) % 2 == 0);
    }

    // string representation of the board
    // in the output format specified above
    public String toString() {

        String boardString = "";
        for (int i=0; i< tiles.length ; i++)
        {
            for (int j=0; j < tiles.length ; j++)
                boardString += tiles[i][j] + " ";
                boardString += "\n";
        }
        return boardString;
    }

/*    // you may find the following helper functions useful
    public boolean equals(Object o)

    // set the tile at (x, y) to val
    private void setTileAt(int x, int y, int val)

    // swap the tiles at (x1, y1) and (x2, y2)
    private void swap(int x1, int y1, int x2, int y2)

    // create a new Board with the same configuration as the parameter
    private Board makeCopy(Board b)

    // number of moves required to get to this state from the start state
    private int getDistanceFromStart( )*/
}
