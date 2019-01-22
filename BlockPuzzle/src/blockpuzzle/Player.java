package blockpuzzle;

/**
 *
 * @author Wernich
 */
public class Player {
    
    private int xCoord, yCoord;

    public Player(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord - 1;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord - 1;
    }
    
    public void moveRight() {
        xCoord = xCoord + 1; 
    }
    
    public void moveLeft() {
        xCoord = xCoord - 1; 
    }
    
    public void moveUp() {
        yCoord = yCoord - 1; 
    }
    
    public void moveDown() {
        yCoord = yCoord + 1; 
    }

}
