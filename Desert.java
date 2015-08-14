import java.util.*;
/**
 * The desert is essentially a container for the snake and the current mouse.
 * @author (Ohuabunwa Princewill)
 *
 */
public class Desert extends Environment {
    private SnakeHead snakeHead;
    //private SnakeHead oldSnakeHeadLocation;
    private int oldx, oldy;
    private Mouse mouse;
    private ArrayList<SnakeBodyPart> snakeBodyParts;

    /**
     * Constructor: <ul>
     * <li>set the size (used for both height and width) to the specified value<li>
     * <li>create a snake and place it at the center</li>
     * <li>place a mouse at a random location that is not overlapping with the snake
     * or a snake part</li>
     * </ul>
     */
    public Desert(int size){
        super(size);
        snakeHead = new SnakeHead(this);
        snakeBodyParts = new ArrayList<SnakeBodyPart>();
        mouse = new Mouse(this);
    }
    
    /** Desert initialized using the default size (5) */
    public Desert() {this(DEFAULT_SIZE);}

    /** accessor for snake head */
    public SnakeHead getSnakeHead() {
        return snakeHead;
    }
    
    /** @return whether there's a snake head at the supplied location */
    public boolean hasSnakeHeadAt(int x, int y) {
        return (snakeHead.getX() == x) && (snakeHead.getY() == y);
    }
    
    public boolean hasSnakeBodyPartAt(int x, int y) {
        for (SnakeBodyPart sbp: snakeBodyParts) {
            if ((sbp.getX() == x) && (sbp.getY() == y)) return true;
        }
        return false;
    }
    
    private boolean hasMouseAt(int x, int y) {
        return (mouse.getX() == x) && (mouse.getY() == y);
    }
    
    /*public boolean hasSnakeBodyPartAt(int x, int y) {
        for (SnakeBodyPart b : snakeBodyParts) {
            if ((b.getX() == x) && (b.getY() == y)) return true;
        }
        return false; //we only get here if didn't find a bullet at that coordinate
    } */
    
    @Override
    public String getCommands() {
        return "'4' for left, '5' to move forward, '6' for right";
    }
    
    @Override
    public void processCommand(char c) {
        oldx = snakeHead.getX();
        oldy = snakeHead.getY();
        switch (c) {
                case '4': snakeHead.moveLeft(); break;
                case '6': snakeHead.moveRight(); break;
                case '5': snakeHead.moveForward(); break;
        }
    }
    
    /**
     * Check to see if the snake head has reached the mouse. 
     * If that's the case, replace the mouse with a new one.
     */
    public void resolve() {
        if (hasSnakeHeadAt(mouse.getX(),mouse.getY())) {
            snakeBodyParts.add(0,new SnakeBodyPart(oldx, oldy, this));
            mouse = new Mouse(this);
        }
        else {
            if (!(snakeBodyParts.isEmpty())) {
                //instead of moving everyone, just add a body part where the head was, and remove the tail
                snakeBodyParts.add(0,new SnakeBodyPart(oldx, oldy, this));
                snakeBodyParts.remove(snakeBodyParts.size() - 1); //remove the tail
            }
        }
    }
    
    @Override
    public boolean hasLost() {
        return hasSnakeBodyPartAt(snakeHead.getX(), snakeHead.getY());
    }
    
    /**
     * @return a string representation of desert: use: <ul>
     * <li>" " for empty space
     * <li>the string representation of the snake head 
     * <li>"m" for the mouse </ul>
     * if snake head and mouse overlap, only draw the snake head
     */
    public String toString() {
        String s = "";
        for (int j=0; j < size; j++) {
            for (int i=0; i < size; i++) {
                if (hasSnakeHeadAt(i,j)) s += snakeHead.toString(); 
                else if (hasSnakeBodyPartAt(i,j)) s += "o";
                else if (hasMouseAt(i,j)) s += "m";
                else s+= ".";
            }
            s += "\n";
        }
        return s;
    }
        
}








