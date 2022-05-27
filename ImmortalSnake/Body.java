
/**
 * Write a description of class Head here.
 * 
 * @author (Greg Johnson) 
 * @version (a version number or a date)
 */
import java.awt.Graphics2D;

public class Body implements Movable
{
    protected SmartRectangle _bodySeg;
    private int _xDir = SnakeConstants.BLOCK_SIZE;
    private int _yDir = 0;
    private java.awt.Color _color;
    private int _panelWidth;
    private int _panelHeight;
    
    public Body(){
        this(java.awt.Color.green);
    }
    public Body(java.awt.Color c)
    {
        _color = c;
        _bodySeg = new SmartRectangle(_color);
        _bodySeg.setSize(SnakeConstants.BLOCK_SIZE,SnakeConstants.BLOCK_SIZE);
        this.setLocation(200,200);
        _panelWidth = 640;
        _panelHeight = 460;
        
    }
    public void setLocation(int x, int y){
       _bodySeg.setLocation(x,y);
    }
    public int getXLocation(){
        return (int)_bodySeg.getX();
    }
    public int getYLocation(){
        return (int)_bodySeg.getY();
    }
    
    public void move(){
        int newXLoc, newYLoc;
        newYLoc = (int) _bodySeg.getY() + _yDir;
        newXLoc = (int) _bodySeg.getX() + _xDir;
        if (newXLoc < 0)
            newXLoc = _panelWidth - SnakeConstants.BLOCK_SIZE;
        else if (newXLoc >= _panelWidth)
            newXLoc = 0;
        if (newYLoc < 0)
            newYLoc = _panelHeight - SnakeConstants.BLOCK_SIZE;
        else if (newYLoc >= _panelHeight)
            newYLoc = 0;
        this.setLocation(newXLoc, newYLoc);
    }
    public void move(int x, int y){
        setLocation(x, y);
    }
    
    public void moveUp(){
        if(_yDir == 0){
            _yDir = - SnakeConstants.BLOCK_SIZE;
            _xDir = 0;
        }
    }
    public void moveDown(){
        if(_yDir == 0){
            _yDir = SnakeConstants.BLOCK_SIZE;
            _xDir = 0;
        }
    }
    public void moveLeft(){
        if(_xDir == 0){
            _yDir = 0;
            _xDir = -SnakeConstants.BLOCK_SIZE;
        }
    }
    public void moveRight(){
        if(_xDir == 0){
            _yDir = 0;
            _xDir = SnakeConstants.BLOCK_SIZE;
        }
    }
    
    public void setSize(int xDim, int yDim){
        _bodySeg.setSize(xDim, yDim);
    }

    public void fill(Graphics2D aBrush){        
        _bodySeg.fill(aBrush);
    }
}
