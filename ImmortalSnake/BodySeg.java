import java.awt.Graphics2D;

public class BodySeg extends Body
{
    private Body _nextSeg;
    
    public BodySeg(){
        this(java.awt.Color.green);
    }
    public BodySeg(java.awt.Color c){
        super(c);
    }
    public void grow(){
        super.move();
    }
    public void move(){
        int oldXLoc, oldYLoc;
        oldYLoc = (int) _bodySeg.getY();
        oldXLoc = (int) _bodySeg.getX();
        super.move();
        _nextSeg.move(oldXLoc, oldYLoc);
    }
    public void move(int x, int y){
        int oldXLoc, oldYLoc;
        oldYLoc = (int) _bodySeg.getY();
        oldXLoc = (int) _bodySeg.getX();
        setLocation(x, y);
        _nextSeg.move(oldXLoc, oldYLoc);
    }
    public void setNextSeg(Body aSegment){
        _nextSeg = aSegment;
    }
    public Body getNextSeg(){
        return _nextSeg;
    }
    public void fill(Graphics2D aBrush){
        super.fill(aBrush);
        _nextSeg.fill(aBrush);
    }
}

