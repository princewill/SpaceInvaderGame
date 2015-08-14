import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JOptionPane;

/**
 *GUI for SpaceInvaderGame here.
 * 
 * @author (Ohuabunwa Princewill)
 */
public class GraphicalSpaceInvaderGame extends Game implements ActionListener 
{
    /**
     * Instance variables
     */
    private JFrame gameFrame;   
    private JLabel[][] labels;
    private JPanel spacePane;
    private JPanel actionPane;
    private int size;
    JButton a,b,c;
   
    /**
     * Contructor that takes in one parameter of type Environment
     * Instantiates the instance variables
     */
           
    public GraphicalSpaceInvaderGame(Environment env)
    {
        super(env);
        size =env.getSize();   //get the size of the space.  
               
        gameFrame = new JFrame("Space Invader Game"); //creates a frame and gives it a title.     
        spacePane = new JPanel(new GridLayout(size,size)); //creates a panel for the space using gridlayout as the layout
       
        actionPane = new JPanel(new GridLayout(1,3)); //creates a panel for the buttons using gridlayout as the layout         
        
        gameFrame.getContentPane().setLayout(new GridLayout(2,1)); //makes the frame a gridlayout.
        
        labels = new JLabel[size][size]; //creates a 2D label array according to the size of the space
        
        /**
         * a loop that add each newly created label to the spacePane
         */
        for(int i = 0; i<size;i+=1)
        {
            for(int j=0;j<size;j+=1)
            {
                labels[i][j] = new JLabel();
                spacePane.add(labels[i][j]);                               
            }
            
        }
        
        spacePane.setBackground(Color.YELLOW); //sets the background for the space to yellow
        
        /**
         * creates buttons for the different commands.         * 
         */
         a =new JButton("<"); 
         b =new JButton(">");
         c =new JButton("Shoot");   
         
        /**
         * assigns actioncommand to each button
         */              
        a.setActionCommand("4");
        b.setActionCommand("6");
        c.setActionCommand("5"); 
        
        /**
         * adds each button to the actionPane
         */
        actionPane.add(a);
        actionPane.add(c);
        actionPane.add(b);       
       
        /**
         * adds a listener to each button
         */
        a.addActionListener(this);
        b.addActionListener(this);
        c.addActionListener(this);  
        
        /**
         * adds both actionPane and spacePane to the Frame
         */
        gameFrame.add(spacePane);                     
        gameFrame.add(actionPane); 
        
        gameFrame.setPreferredSize(new Dimension(700,450)); //sets the size of the frame
        
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        gameFrame.pack();
        gameFrame.setVisible(true);
       
    }    
    
    /**
     * <ul><li> Outputs the current state of the environment
     * <li> Gets console input from the user and delegates to the environment for processing
     * <li> Resolves
     * <li> Determines win or loss
     * <li> until the user quits.</ul>     */
    
    public void actionPerformed(ActionEvent event)
    {           
        String s = event.getActionCommand();
        char ch = s.charAt(0);
        env.processCommand(ch);
        env.resolve();         
        
        if (env.hasWon()) { 
                repaint();
                JOptionPane win = new JOptionPane();
                win.showMessageDialog(null,"Congratulations! You won!"); 
                b.setEnabled(false);
                a.setEnabled(false);
                c.setEnabled(false);
                return;                
            }
            
        if (env.hasLost()) {
                repaint();
                JOptionPane loss = new JOptionPane();                
                loss.showMessageDialog(null,"Oh no! You lost! Game over.");
                b.setEnabled(false);
                a.setEnabled(false);
                c.setEnabled(false);
                return;  
            }        
        
       repaint();
    }

    
    /**
     * show the current state of the game after it has been resolved
     */
    public void repaint()
    {   
        Space space=(Space)env;   
        for (int j=0; j < size; j++) {
            for (int i=0; i < size; i++) {
                if (space.getInvader()!=null&&space.hasInvaderAt(i,j)) labels[j][i].setText("W"); 
                else if (space.getCannon()!=null&&space.hasCannonAt(i,j))labels[j][i].setText("i");
                else if (space.hasBulletAt(i,j)) labels[j][i].setText("+");
                else labels[j][i].setText("-");
            }            
        }       
     }  
          
     public static void main(String[] args)
     {
         GraphicalSpaceInvaderGame g = new GraphicalSpaceInvaderGame(new Space());
         g.repaint();

     }
    
}
