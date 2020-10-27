package touro.snake.strategy.astar.peikes;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Chooses a direction based on most effcient path as determined by A*  algorithm
 */

public class AStarStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Node start = new Node(snake.getHead());
        Direction directions[] = Direction.values();
        Food food = garden.getFood();
        Double shortestDistance = Double.MAX_VALUE;
        Direction bestDirection = null;



        // Initialize both open and closed list
        ArrayList<Node> open = new ArrayList<Node>();
        ArrayList<Node> closed = new ArrayList<Node>();


        // Add the start node
        open.add(start);

        // Loop until you find the end// the openList is not empty
        while (!open.isEmpty()) {

            // Get the current node
           // let the currentNode equal the node with the least f value
            for (int i = 0; i < directions.length; i++) {
                //Initialize child with current/start as its parent
                Node neighbor = new Node(start.moveTo(directions[i]), start, food);
                if (snake.contains(neighbor) || !neighbor.inBounds() || closed.contains(neighbor)) {
                    continue;
                }
                if (!open.contains(neighbor)){
                    open.add(neighbor);
                } else{
                    //Compare Costs??
                }


            //remove the currentNode from the openList
                open.remove(neighbor);
            //add the currentNode to the closedList
                closed.add(neighbor);

            // Found the goal
            //if currentNode is the goal

              if (neighbor.getY() == food.getY() && neighbor.getX() == food.getX())  {
                  //Congratz! You've found the end! Backtrack to get path
              }

           // Generate children
            //let the children of the currentNode equal the adjacent nodes
            //for each child in the children


             for (int j = 0; j < directions.length; j++) {


                 //Initialize child with current/start as its parent
                 //Node neighbor = new Node(start.moveTo(directions[j]), start, food);

                 // Child is on the closedList--if child is in the closedList--continue to beginning of for loop
                 if (snake.contains(neighbor) || !neighbor.inBounds() || closed.contains(neighbor)) {
                     continue;
                 }
                 // Create the f, g, and h values
                 //child.g = currentNode.g + distance between child and current
                 //child.h = distance from child to end
                 //child.f = child.g + child.h
                 double cost = neighbor.getCost();

                 if (!open.contains(neighbor)) {
                     open.add(neighbor);
                 } else {
                     //Compare Costs??
                 }

             }

                // Child is already in openList
                //if child.position is in the openList's nodes positions
                //if the child.g is higher than the openList node's g
                //continue to beginning of for loop

               /*
                // Add the child to the openList
                add the child to the openList*/
        }


       /* // for each child node
        for (int i = 0; i < directions.length; i++) {
            //Initialize child with current/start as its parent
            Node neighbor = new Node(start.moveTo(directions[i]), start, food);
            if (snake.contains(neighbor) || !neighbor.inBounds() || closed.contains(neighbor)) {
                continue;
            }
            if (!open.contains(neighbor)){
                open.add(neighbor);
            } else{
                //Compare Costs??
            }
            double cost = neighbor.getCost();

            if (snake.contains(neighbor) || !neighbor.inBounds() || closed.contains(neighbor)) {
                continue;
            }
*/
        }
        //snake.turnTo(bestDirection);
    }
}
