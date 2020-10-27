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

    public Node moveTo(Direction direction){
        switch (direction) {
            case North:
                return new Node(super.getX(), super.getY() - 1);
            case East:
                return new Node(super.getX() + 1, super.getY());
            case South:
                return new Node(super.getX(), super.getY() + 1);
            case West:
                return new Node(super.getX() - 1, super.getY());
            default:
                throw new RuntimeException(direction + " is not a known Direction");
        }
    }

}
