import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.sound.sampled.*;

public class GamePanel extends JPanel implements ActionListener{

    private BodySeg _snake;
    private javax.swing.Timer _timer;
    private BufferedImage _image;
    private KeyInteractor _upListener, _downListener;
    private KeyInteractor _leftListener, _rightListener;
    private Clip _clip;
    private int _x, _y;
    private Random _rand;

    public GamePanel(JFrame app){
        this.setSize(640, 480);
        this.setBackground(java.awt.Color.white);
        this.setPreferredSize(new Dimension(640, 480));
        BodySeg segment;
        Tail segment2;
        _snake = new BodySeg();
        _snake.setLocation(300,100);
        segment = new BodySeg();
        _snake.setNextSeg(segment);
        segment2 = new Tail();
        segment.setNextSeg(segment2);
        _upListener = new KeyUpListener(this);
        _downListener = new KeyDownListener(this);
        _leftListener = new KeyLeftListener(this);
        _rightListener = new KeyRightListener(this);
        _timer = new javax.swing.Timer(50,this);
        _timer.start();

        try{
            _image = ImageIO.read(new File("cake.gif"));
        }
        catch (IOException e){
            System.out.println("Error opening image file.");
        }
        try {
          // Open an audio input stream.
          File soundFile = new File("bite.wav");
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);         // Get a sound clip resource.
          _clip = AudioSystem.getClip();
          // Open audio clip and load samples from the audio input stream.
          _clip.open(audioIn);
          //clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        _x=300;
        _y=100;
        _rand= new Random();
    }
    public void paintComponent(Graphics aBrush){
        super.paintComponent(aBrush);
        Graphics2D aBetterBrush = (Graphics2D) aBrush;
        aBrush.drawImage(_image, _x, _y, SnakeConstants.BLOCK_SIZE, SnakeConstants.BLOCK_SIZE,null);
        _snake.fill(aBetterBrush);
    }
    public void actionPerformed(ActionEvent e){
        if (_snake.getXLocation() == _x && _snake.getYLocation() ==_y){
            _x=_rand.nextInt(31)*SnakeConstants.BLOCK_SIZE;
            _y=_rand.nextInt(23)*SnakeConstants.BLOCK_SIZE;
            BodySeg seg = new BodySeg();

            seg.setNextSeg(_snake.getNextSeg());
            _snake.setNextSeg(seg);
            seg.setLocation(_snake.getXLocation(), _snake.getYLocation());
            _snake.grow();

            if (_clip.isRunning())
                _clip.stop();   // Stop the player if it is still running
            _clip.setFramePosition(0); // rewind to the beginning
            _clip.start();     // Start playing
        }
        _snake.move();
        repaint();
    }
    private class KeyUpListener extends KeyInteractor 
    {
        public KeyUpListener(JPanel p)
        {
            super(p,KeyEvent.VK_UP);
        }
        
        public  void actionPerformed (ActionEvent e) {
            _snake.moveUp();           
        }
    }
    private class KeyDownListener extends KeyInteractor 
    {
        public KeyDownListener(JPanel p)
        {
            super(p,KeyEvent.VK_DOWN);
        }
        
        public  void actionPerformed (ActionEvent e) {
            _snake.moveDown();           
        }
    }
    private class KeyLeftListener extends KeyInteractor 
    {
        public KeyLeftListener(JPanel p)
        {
            super(p,KeyEvent.VK_LEFT);
        }
        
        public  void actionPerformed (ActionEvent e) {
            _snake.moveLeft();           
        }
    }
    private class KeyRightListener extends KeyInteractor 
    {
        public KeyRightListener(JPanel p)
        {
            super(p,KeyEvent.VK_RIGHT);
        }
        
        public void actionPerformed (ActionEvent e) {
            _snake.moveRight();           
        }
    }
}
