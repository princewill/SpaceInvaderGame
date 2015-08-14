import java.util.*;
/**
 * A mouse is an item placed randomly in the desert for the sole purpose
 * of being eaten by a snake! Its initial location must not overlap with the snake (or its parts)
 *
 * @author (Ohuabunwa Princewill)
 *
 */

public class Mouse extends Item {
    private Random r;

    /**
     * initialize the mouse by randomly placing it in the desert,
     * but not on top of the snake 
     */
    public Mouse(Desert d) {
        super(0,0,d); //temporary assignment of location
        r = new Random();
        int s = d.getSize();
        do {
            x = r.nextInt(s-1);
            y = r.nextInt(s-1);
        }
        while (d.hasSnakeHeadAt(x,y) || d.hasSnakeBodyPartAt(x,y));            
    }
}
