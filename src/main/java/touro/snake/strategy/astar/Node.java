package touro.snake.strategy.astar;

import touro.snake.Direction;
import touro.snake.Square;

/**
 * A Node used in the AStar algorithm
 * https://www.youtube.com/watch?v=-L-WgKMFuhE
 */
public class Node extends Square {
    private Node parent;
    private double fromStart;
    private double fromEnd;

    public Node(Square square) {
        super(square);
    }

    public Node(Square square, Node parent, Square end) {
        super(square);
        setParent(parent);
        setEnd(end);
    }

    public Node(int x, int y) {
        super(x, y);
    }

    public Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
        fromStart = (parent.fromStart+1) ;
    }

    private void setEnd(Square square) {
        fromEnd = distance(square);
    }

    public double getCost() {
        return fromStart * 7 + fromEnd;
    }

<<<<<<< HEAD

    }


=======
    @Override
    public String toString() {
        return String.format("Node{%d, %d = %f}", getX(), getY(), getCost());
    }
}
>>>>>>> 1c0afa9f8444b5b6b7bc9e9595c0350c68dea4c5
