package touro.snake.strategy.astar.peikes;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Chooses a direction based on most effcient path as determined by A*  algorithm
 */

public class AStarStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Node start = new Node(snake.getHead());
        Node current = new Node(snake.getHead());
        Direction directions[] = Direction.values();
        Food food = garden.getFood();

        // Initialize both open and closed list
        List<Node> open = new ArrayList<Node>();
        List<Node> closed = new ArrayList<Node>();
        // Add the start node
        open.add(current);

        // Loop until you find the end/the openList is not empty
        while (!open.isEmpty()) {
            if (food == null) {
                break;
            }
            // Get the current node-let the currentNode equal the node with the least f value-Initialize child with current/start as its parent
            current = open.get(0);
            int currentIndex = 0;
            for (Node node : open) {
                if (node.getCost() < current.getCost()) {
                    current = node;
                }
            }

            //remove the currentNode from the openList
            open.remove(current);
            //add the currentNode to the closedList
            closed.add(current);

            //if currentNode is the goal
            if (current.getY() == food.getY() && current.getX() == food.getX()) {
                //Congrats! You've found the end! Backtrack to get path
                Stack<Node> route = new Stack<Node>();
                while (!(current.getY() == start.getY()) && !(current.getX() == start.getX())) {
                    route.add(current);
                    current = current.getParent();
                }
                while (!route.isEmpty()) {
                    Direction routeDirection = current.directionTo(route.peek());
                    snake.turnTo(routeDirection);
                    current = route.pop();
                }
            }

            // Generate children-let the children of the currentNode equal the adjacent-for each child in the children
            for (Direction direction : directions) {
                //Initialize child with current/start as its parent
                Node neighbor = new Node(current.moveTo(direction), current, food);

                // Child is on the closedList--if child is in the closedList--continue to beginning of for loop
                if (snake.contains(neighbor) || !neighbor.inBounds() || closed.contains(neighbor)) {
                    continue;
                }
                // Create the f values
                double cost = neighbor.getCost();

                if (!open.contains(neighbor)) {

                    // Child is already in openList--if child.position is in the openList's nodes positions
                } else {
                    //Compare Costs
                    for (Node node : open) {
                        if (neighbor.getCost() > node.getCost()) {
                            continue;
                        }
                    }
                }
                open.add(neighbor);
            }
        }
    }
}
