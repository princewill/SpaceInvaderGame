
/**
 * The snake head is the player! It is an item that can 
 * move one step forward or to its left or right.
 * 
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public class SnakeHead extends Item {
    //private enum Direction {NORTH, SOUTH, WEST, EAST}
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private int direction;

    /**
     * Initialize the snake head by setting its location in the center of the desert,
     * and its initial direction which is North.
     */
    public SnakeHead(Desert d) {
        super(d.getSize()/2, d.getSize()/2, d);
        direction = NORTH;
    }
    
    /**
     * Move one step forward 
     */
    public void moveForward() {
        switch (direction) {
            case NORTH: if (y > 0) y--; break;
            case SOUTH: if (y < (env.getSize() - 1)) y++; break;
            case WEST: if (x > 0) x--; break;
            case EAST: if (x < (env.getSize() - 1)) x++; break;
        }
    }
    
    /** 
     * Move one step to the left. This also changes the snake's direction.
     */
    public void moveLeft() {
        switch (direction) {
            case NORTH: if (x > 0) x--; direction = WEST; break;
            case SOUTH: if (x < (env.getSize() - 1)) x++; direction = EAST; break;
            case WEST: if (y < (env.getSize() - 1)) y++; direction = SOUTH; break;
            case EAST: if (y > 0) y--; direction = NORTH; break;
        }
    }
    
    /**
     * Move one step to the right. This also changes the snake's direction.
     */
    public void moveRight() {
        switch (direction) {
            case NORTH: if (x < (env.getSize() - 1)) x++; direction = EAST; break;
            case SOUTH: if (x > 0) x--; direction = WEST; break;
            case WEST: if (y > 0) y--; direction = NORTH; break;
            case EAST: if (y < (env.getSize() - 1)) y++; direction = SOUTH; break;
        }
    }

    /**
     * @return a string representation of the snake's head. This string will reflect
     * the snake head's current direction: <ul>
     * <li>"^" for North
     * <li>"v" for South
     * <li>"<" for West
     * <li>">" for East
     * </ul>
     */
    public String toString() {
        switch (direction) {
            case NORTH: return "^";
            case SOUTH: return "v";
            case WEST: return "<";
            case EAST: return ">";
        }
        return "";
    }   
}
