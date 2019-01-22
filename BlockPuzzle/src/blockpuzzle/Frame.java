package blockpuzzle;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author Wernich
 */
public class Frame extends JFrame implements KeyListener{

    public static Player player;
    public static Screen screen;
    public static boolean running = false;
    Map map;   
    
    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Puzzle map is hardcoded for now
        map = new Map("maps\\puzzle1.txt");
        setSize(map.getCols()*64 + 6, map.getRows()*64 + 29);
        setResizable(false);
        setTitle("BlockMoverPuzzle");
        init(map);
        startGame();
    }
    

    private void init(Map map) {
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1, 0, 0));
        addKeyListener(this);

        screen = new Screen(map);
        add(screen);

        setVisible(true);
    }
    
    private void startGame() {
        running = true;
        player = new Player(map.getpX(), map.getpY());
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (running) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_A) {
                int pX = player.getxCoord();
                int pY = player.getyCoord();
                if (map.canMove(pX - 1, pY, Map.LEFT)) {
                    player.moveLeft();
                    screen.repaint();
                }
            }

            if (key == KeyEvent.VK_D) {
                int pX = player.getxCoord();
                int pY = player.getyCoord();
                if (map.canMove(pX + 1, pY, Map.RIGHT)) {
                    player.moveRight();
                    screen.repaint();
                }
            }

            if (key == KeyEvent.VK_W) {
                int pX = player.getxCoord();
                int pY = player.getyCoord();
                if (map.canMove(pX, pY - 1, Map.UP)) {
                    player.moveUp();
                    screen.repaint();
                }
            }

            if (key == KeyEvent.VK_S) {
                int pX = player.getxCoord();
                int pY = player.getyCoord();
                if (map.canMove(pX, pY + 1, Map.DOWN)) {
                    player.moveDown();
                    screen.repaint();
                }
            }
        }
    }
}
