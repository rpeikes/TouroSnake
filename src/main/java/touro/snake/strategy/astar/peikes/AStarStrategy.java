package touro.snake.strategy.astar.peikes;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Chooses a direction based on most efficient path as determined by A*  algorithm
 */

public class AStarStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Node start = new Node(snake.getHead());
        Node current;
        Direction directions[] = Direction.values();
        Food food = garden.getFood();
        List<Node> open = new ArrayList<Node>();
        List<Node> closed = new ArrayList<Node>();
        Stack<Node> route = new Stack<Node>();
        // Add the start node
        open.add(start);
        // Loop until food is found/the openList is not empty
        while (!open.isEmpty()) {
            if (food == null) {
                break;
            }
            // Get the current node-let the currentNode equal the node on the open list
            current = open.get(0);

            for (Node node : open) {
                if (node.getCost() < current.getCost()) {
                    current = node;
                }
            }
            //remove the currentNode from the openList and add it to the closedList
            open.remove(current);
            closed.add(current);

            //if currentNode is the goal, backtrack to get path
            if (current.getX() == food.getX() && current.getY() == food.getY()) {
                while (current!=start) {
                    route.push(current);
                    current = current.getParent();
                }
                while (!(current.getX() == food.getX() && current.getY() == food.getY())) {
                    Direction routeDirection = current.directionTo(route.peek());
                    snake.turnTo(routeDirection);
                    current = route.pop();

                }
                break;
            }

            // Generate children-let the children of the currentNode equal the adjacent-for each child in the children
            for (Direction direction : directions) {
                //Initialize child with current/start as its parent
                Square neighbor = current.moveTo(direction);
                // Child is on the closedList--if child is in the closedList--continue to beginning of for loop
                if (snake.contains(neighbor) || !neighbor.inBounds() || closed.contains(neighbor) || open.contains(neighbor)) {
                    continue;
                }else{
                    open.add(new Node (neighbor, current, food));
                }
            }
        }
    }
}
