package touro.snake;

import javax.swing.*;
import java.awt.*;

public class GardenView extends JComponent {

    private final Garden garden;
    public static final int CELL_SIZE = 10;

    public GardenView(Garden garden) {
        this.garden = garden;
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
            g.setColor(Color.RED);
            g.fillRect(s.getX() * CELL_SIZE, s.getY() * CELL_SIZE, CELL_SIZE/2, CELL_SIZE);
            g.setColor(Color.MAGENTA);
            g.fillRect(s.getX() * CELL_SIZE + (CELL_SIZE/2), s.getY() * CELL_SIZE, CELL_SIZE/2, CELL_SIZE);
        }
    }

    void paintFood(Graphics g) {
        // Berger
        if (garden.getFood() != null) {
            Food food = garden.getFood();
            int x = food.getX() * CELL_SIZE;
            int y = food.getY() * CELL_SIZE;
            Image bread = Toolkit.getDefaultToolkit().getImage("roll_of_bread.png");
            g.drawImage(bread, x, y, this);
        }

    }
}
