
/**
 * An environment is a superclass of concrete classes that act as containers for
 * items in various games. All it provides is a size and a DEFAULT_SIZE (5), 
 * and a print method.
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public abstract class Environment {
    public static int DEFAULT_SIZE = 5;
    protected int size;
    
    /**
     * initialize the size of the environment
     */
    public Environment(int size) {
        this.size = size;
    }
    
    /** initialize the environment using the default size (5) */
    public Environment() {this(DEFAULT_SIZE);}

    /** accessor for the size of the environment */
    public int getSize() {return size;}
    
    /** 
     * @returns a string listing the acceptable commands for the game
     */
    public abstract String getCommands();
    
    /**
     * Process the command supplied as a parameter (typically a player move)
     */
    public abstract void processCommand(char c);
    
    /**
     * Move various items, and
     * determine the new state of the game based on the location of items and player
     */
    public abstract void resolve();
    
    /**
     * Determine loss (if not applicable for this game just return false)
     */
    public boolean hasLost() {return false;}
    
    /**
     * Determine if the player has won (if not applicable just return false)
     */
    public boolean hasWon() {return false;}
    
    /**
     * outputs a string representation of the environment to the console
     */
    public void print() {
        System.out.println(this);
    }
}
