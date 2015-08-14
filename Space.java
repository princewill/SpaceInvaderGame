import java.util.*;
/**
 * Space: the final frontier! It is essentially a container for the invader and the cannon.
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public class Space extends Environment {
    private Invader invader;
    private Cannon cannon;
    private ArrayList<Bullet> bullets; 

    /**
     * Constructor: 
     * - set the size (used for both height and width) to the specified value
     * - create an invader and place it at the top left corner
     * - create a cannon and place it at the bottom centre
     */
    public Space(int size) {
        super(size);
        invader = new Invader(0,0,this);
        cannon = new Cannon(size/2,this);
        bullets = new ArrayList<Bullet>();
    }
    
    /** 
     * Space initialized using the default size (5)
     */
    public Space() {
        this(DEFAULT_SIZE);
    }
    
    /** accessor for invader */
    public Invader getInvader() {
        return invader;
    }
    
    /** accessor for cannon */
    public Cannon getCannon() {
        return cannon;
    }
    
    /** accessor for the bullets that have been fired */
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    
    /** when a bullet is fired, it should be added to the list of bullets */
    public void addBullet(Bullet b) {
        bullets.add(b);
    }
    
    public boolean hasInvaderAt(int x, int y) {
        return (invader.getX() == x) && (invader.getY() == y);
    }
    
    public boolean hasCannonAt(int x, int y) {
        return (cannon.getX() == x) && (cannon.getY() == y);
    }
    
    public boolean hasBulletAt(int x, int y) {
        for (Bullet b : bullets) {
            if ((b.getX() == x) && (b.getY() == y)) return true;
        }
        return false; //we only get here if didn't find a bullet at that coordinate
    }
    
    private void removeBulletAt(int x, int y) {
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            if ((b.getX() == x) && (b.getY() == y)) {
                it.remove();
                return;
            }
        }
    }
    
    @Override
    public String getCommands() {
        return "'4' for left, '6' for right, '5' to shoot";
    }
    
    @Override
    public void processCommand(char c) {
        switch (c) {
                case '4': cannon.moveLeft(); break;
                case '6': cannon.moveRight(); break;
                case '5': cannon.shoot(); break;
        }
    }
        
    /**
     * Determines the new state of the game based on the location of items.
     * In the case of Space Invaders: <ul>
     * <li> if a bullet has hit the cannon, set the cannon to null and remove the bullet
     * <li> if a bullet has hit the invader, set the invader to null and remove the bullet
     * <li> if a bullet has reached the edge of the screen, remove it. </ul>
     * NOTE: you can't modify a collection inside a foreach loop over that same collection!
     * Refer to the textbook section 4.12 and in particular 4.12.2 ("Removing Elements")
     * for one possible solution.
     */
    public void resolve() {
        invader.move();            
        for (Bullet b : bullets) b.move();
        
        if (hasBulletAt(cannon.getX(), cannon.getY())) {
            removeBulletAt(cannon.getX(), cannon.getY());
            cannon = null;
        }
        if (hasBulletAt(invader.getX(), invader.getY())) {
            removeBulletAt(cannon.getX(), cannon.getY());
            invader = null;
        }
        
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            if ((b.getY() == 0) || (b.getY() == getSize())) {
                it.remove();
            }
        }  
    }
    
    /**
     * Game is won if the invader has been destroyed!
     */
    public boolean hasWon() {
        return (invader == null);
    }
    
    /**
     * Game is lost if the cannon has been destroyed!
     */
    public boolean hasLost() {
        return (cannon == null);
    }
            
    
    /**
     * @return a string representation of space: use: <ul>
     * <li>" " for empty space
     * <li>"W" for the invader
     * <li>"i" for the cannon
     * <li>"." for a bullet </ul>
     * if invader and cannon overlap, only draw the invader.
     */
    public String toString() {
        String s = "";
        for (int j=0; j < size; j++) {
            for (int i=0; i < size; i++) {
                if (hasInvaderAt(i,j)) s += "W"; 
                else if (hasCannonAt(i,j)) s += "i";
                else if (hasBulletAt(i,j)) s += ".";
                else s+= " ";
            }
            s += "\n";
        }
        return s;
    }
    
}








