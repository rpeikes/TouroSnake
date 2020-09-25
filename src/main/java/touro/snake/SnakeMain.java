package touro.snake;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.InputStream;

public class SnakeMain {

    public static void main(String[] args) {

        // Set up all class dependencies here.

        SnakeHeadStateMachine snakeHeadStateMachine = new SnakeHeadStateMachine(Direction.West);
        Snake snake = new Snake(snakeHeadStateMachine);
        FoodFactory foodFactory = new FoodFactory();
        try {
            InputStream inputStream = Garden.class.getClassLoader().getResourceAsStream("EatNoise.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            Garden garden = new Garden(snake, foodFactory, clip);
            GardenView gardenView = new GardenView(garden);
            SnakeKeyListener snakeKeyListener = new SnakeKeyListener(snake);

            GardenThread thread = new GardenThread(garden, gardenView);
            thread.start();

            new SnakeFrame(gardenView, snakeKeyListener).setVisible(true);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
