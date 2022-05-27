import java.awt.*;
import javax.swing.*;

public class SnakeApp extends JFrame{
    private GamePanel _game;
    public SnakeApp() {
       super("Snake Game");
       this.setSize(640,480);
       _game = new GamePanel(this);
       this.add(_game, BorderLayout.CENTER);
       this.setVisible(true);
    }
    public static void main(String[] args)
    {
      SnakeApp app = new SnakeApp();
    }
}