package touro.snake;

import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.schwimmer.AStarStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GardenView extends JComponent {

    private final Garden garden;
    public static final int CELL_SIZE = 10;
    private SnakeStrategy strategy;
    public GardenView(Garden garden, SnakeStrategy strategy) {
        this.garden = garden;
        this.strategy = strategy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrass(g);
        paintFood(g);
        paintSnake(g);
    }

    void paintGrass(Graphics g) {
        // Berger
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    void paintSnake(Graphics g) {
        g.setColor(Color.RED);
        for (Square s : garden.getSnake().getSquares()) {
            g.fillRect(s.getX() * CELL_SIZE, s.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    void paintFood(Graphics g) {
        // Berger
        if (garden.getFood() != null) {
            Food food = garden.getFood();
            g.setColor(Color.LIGHT_GRAY);

            int x = food.getX() * CELL_SIZE;
            int y = food.getY() * CELL_SIZE;
            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        }
    }

    void paintPath(Graphics g) {
        g.setColor(Color.GRAY);
        ArrayList<Square> path = (ArrayList<Square>) strategy.getPath();
        for (Square square : path) {
            g.fillRect(square.getX(), square.getY(), CELL_SIZE, CELL_SIZE);
        }
    }

    void paintSearchSpace(Graphics g) {
        g.setColor(Color.BLUE);
        ArrayList<Square> searchSpace = (ArrayList<Square>) strategy.getSearchSpace();
        for (Square square : searchSpace) {
            g.fillRect(square.getX(), square.getY(), CELL_SIZE, CELL_SIZE);
        }
    }
}
