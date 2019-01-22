package blockpuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Wernich
 */
public class Screen extends JPanel{
    
    private final Map map;
    private BufferedImage wallImg = null, floorImg = null, boxImg = null,
            goalBoxImg = null, playerImg = null, goalImg = null,
            victoryImg = null;
    private boolean victory = false;

    public Screen(Map map) {
        this.map = map;
        loadImgs();
        repaint();
    }
    
    private void loadImgs() {
        try {
            wallImg = ImageIO.read(new File("src\\blockpuzzle\\res\\wall.jpg"));
            floorImg = ImageIO.read(new File("src\\blockpuzzle\\res\\floor.jpg"));
            boxImg = ImageIO.read(new File("src\\blockpuzzle\\res\\box.jpg"));
            goalBoxImg = ImageIO.read(new File("src\\blockpuzzle\\res\\goalBox.jpg"));
            playerImg = ImageIO.read(new File("src\\blockpuzzle\\res\\player.jpg"));
            goalImg = ImageIO.read(new File("src\\blockpuzzle\\res\\goal.jpg"));
            victoryImg = ImageIO.read(new File("src\\blockpuzzle\\res\\victory.png"));
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g) {
        int[][] tiles = map.getTiles();
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                switch (tiles[row][col]) {
                    case Map.WALL:
                        g.drawImage(wallImg, col*64, row*64, 64, 64, this);
                        break;
                    case Map.FLOOR:
                        g.drawImage(floorImg, col*64, row*64, 64, 64, this);
                        break;
                    case Map.PLAYER:
                        g.drawImage(floorImg, col*64, row*64, 64, 64, this);
                        break;
                    case Map.GOAL:
                        g.drawImage(goalImg, col*64, row*64, 64, 64, this);
                        break;
                    case Map.BOX:
                        g.drawImage(boxImg, col*64, row*64, 64, 64, this);
                        break;
                    case Map.GOALBOX:
                        g.drawImage(goalBoxImg, col*64, row*64, 64, 64, this);
                        break;
                    default:
                        g.setColor(Color.BLACK);
                        g.fillRect(col*64, row*64, 64, 64);
                        break;
                }
            }
        }       
        int pX = Frame.player.getxCoord();
        int pY = Frame.player.getyCoord();
        g.drawImage(playerImg, pX*64, pY*64, 64, 64, this);
        
        if (victory) {
            g.drawImage(victoryImg, map.getCols()*64/2 - 96, map.getRows()*64/2 - 32, 192, 64, this);
            Frame.running = false;
        }
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }
}
