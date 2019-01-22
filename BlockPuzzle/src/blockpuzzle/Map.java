package blockpuzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wernich
 */
public class Map {
    
    public final static int ERROR = -1, WALL = 0, FLOOR = 1, BOX = 2, GOAL = 3,
            PLAYER = 4, GOALBOX = 5;
    public final static int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;

    private int pX, pY;
    private int rows, cols;
    private int[][] tiles;

    public Map(String fileName) {
        scanFile(fileName);
    }
    
    private void scanFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            if(sc.hasNext()) {
                String[] dim = sc.next().split("\\.");
                rows = Integer.parseInt(dim[0]);
                cols = Integer.parseInt(dim[1]);
                sc.nextLine();
            }
            tiles = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                String[] line = sc.next().split("\\.");
                for (int j = 0; j < line.length; j++) {                    
                    switch (line[j]) {
                        case "w":
                            tiles[i][j] = WALL;
                            break;
                        case "f":
                            tiles[i][j] = FLOOR;
                            break;
                        case "b":
                            tiles[i][j] = BOX;
                            break;
                        case "g":
                            tiles[i][j] = GOAL;
                            break;
                        case "p":
                            pX = j;
                            pY = i;
                            tiles[i][j] = PLAYER;
                            break;
                        default:
                            tiles[i][j] = ERROR;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public int getpX() {
        return pX;
    }

    public int getpY() {
        return pY;
    }
    
    public boolean isWin() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (tiles[row][col] == GOAL) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean canMove(int x, int y, int dir) {
        int newY = y, newX = x;
        if (x < 0 || y < 0 || x == cols || y == rows) {
            return false;
        }
        switch (tiles[y][x]) {
            case PLAYER:
                return true;
            case FLOOR:
                return true;
            case GOAL:
                return true;
            case BOX:
                switch (dir) {
                    case UP:
                        newY = y - 1;
                        break;
                    case DOWN:
                        newY = y + 1;
                        break;
                    case LEFT:
                        newX = x - 1;
                        break;
                    case RIGHT:
                        newX = x + 1;
                        break;
                }
                if (newX < 0 || newY < 0 || newX == cols || newY == rows) {
                    return false;
                }
                switch (tiles[newY][newX]) {
                    case FLOOR:
                        tiles[newY][newX] = BOX;
                        tiles[y][x] = FLOOR;
                        return true;
                    case GOAL:
                        tiles[newY][newX] = GOALBOX;
                        tiles[y][x] = FLOOR;
                        if (isWin()) {
                            Frame.screen.setVictory(true);
                        }
                        return true;
                    default:
                        return false;
                }
            case GOALBOX:
                switch (dir) {
                    case UP:
                        newY = y - 1;
                        break;
                    case DOWN:
                        newY = y + 1;
                        break;
                    case LEFT:
                        newX = x - 1;
                        break;
                    case RIGHT:
                        newX = x + 1;
                        break;
                }
                if (newX < 0 || newY < 0 || newX == cols || newY == rows) {
                    return false;
                }
                switch (tiles[newY][newX]) {
                    case FLOOR:
                        tiles[newY][newX] = BOX;
                        tiles[y][x] = GOAL;
                        return true;
                    case GOAL:
                        tiles[newY][newX] = GOALBOX;
                        tiles[y][x] = GOAL;
                        if (isWin()) {
                            Frame.screen.setVictory(true);
                        }
                        return true;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }
}