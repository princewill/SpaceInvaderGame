
/**
 * An item is something that has x, y coordinates and belongs to a given game 
 * 
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public class Item {
    protected int x, y;
    protected Environment env;
    
    /**
     * Assign the game, give an initial position (0,0)
     */
    public Item(Environment env) {
        this(0,0,env);
    }
    
    /**
     * Initialize the location and game as per the supplied parameters.
     */
    public Item(int x, int y, Environment env) {
        this.x = x;
        this.y = y;
        this.env = env;
    }

    /** accessor for item's x coordinate*/
    public int getX() {return x;}
    /** accessor for item's y coordinate*/
    public int getY() {return y;}
    
}

