
/**
 * A snake body part is an item that simply follows another snake part 
 * (head or other body part), which we'll call the "target"
 * 
*
* @author (Ohuabunwa Princewill)
*
 */
public class SnakeBodyPart extends Item {

    /**
     * Initialize the body part as per the supplied parameters
     */
    public SnakeBodyPart(int x, int y, Desert d) {
        super(x,y,d);
    }
}
