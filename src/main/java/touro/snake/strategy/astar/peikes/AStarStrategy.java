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
        Direction directions[] = Direction.values();
        Food food = garden.getFood();

        // Initialize both open and closed list
        List<Node> open = new ArrayList<Node>();
        List<Node> closed = new ArrayList<Node>();
        // Add the start node
        open.add(start);

        // Loop until you find the end// the openList is not empty
        while (!open.isEmpty()) {
            // Get the current node
            // let the currentNode equal the node with the least f value

            //Initialize child with current/start as its parent
            start = open.get(0);
            if (snake.contains(start) || !start.inBounds() || closed.contains(start)) {
                continue;
            }
            if (!open.contains(start)) {
                open.add(start);
            } else {
                //Compare Costs??
            }
            //remove the currentNode from the openList
            open.remove(start);
            //add the currentNode to the closedList
            closed.add(start);


            //if currentNode is the goal
            if (start.getY() == food.getY() && start.getX() == food.getX()) {
                //Congrats! You've found the end! Backtrack to get path
                Stack<Node> route = new Stack<Node>();
                while (start.getY() == start.getY() && start.getX() == start.getX()) {
                    route.add(start);
                    start = start.getParent();
                }
                while (!route.isEmpty()) {
                    Direction routeDirection = start.directionTo(route.peek());
                    snake.turnTo(routeDirection);
                    start = route.pop();
                }
            }

            // Generate children-let the children of the currentNode equal the adjacent-for each child in the children
            for (Direction direction : directions) {
                //Initialize child with current/start as its parent
                Node neighbor = new Node(start.moveTo(direction), start, food);

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
                    if (neighbor.getCost() > open.get(open.indexOf(neighbor)).getCost()) {
                        continue;
                    }
                }
                // Child is already in openList--continue to beginning of for loop
                open.add(neighbor);
            }
        }
    }
}
