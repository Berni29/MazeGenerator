import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Maze {

    private Cell[][] maze;
    private LinkedList<Position> stack = new LinkedList<>();
    private Random rng = new Random();
    private ArrayList<String> textMaze = new ArrayList<>();

    public Maze(int width, int height) {
        maze = new Cell[width][height];
        for(int j=0; j<maze[0].length; j++) {
            for(int i=0; i<maze.length; i++) {
                maze[i][j] = new Cell();
            }
        }
        generateMaze();
    }

    private void generateMaze() {
        int x = 0 , y = 0;
        do {
            if(!maze[x][y].isVisited()) {
                stack.push(new Position(x, y));
                maze[x][y].visit();
            }
            String[] directions = checkPossibleDirections(x,y);
            switch(directions[rng.nextInt(directions.length)]) {
                case "left":
                    maze[x][y].makeDoor("left");
                    x--;
                    maze[x][y].makeDoor("right");
                    break;
                case "right":
                    maze[x][y].makeDoor("right");
                    x++;
                    maze[x][y].makeDoor("left");
                    break;
                case "bottom":
                    maze[x][y].makeDoor("top");
                    y--;
                    maze[x][y].makeDoor("bottom");
                    break;
                case "top":
                    maze[x][y].makeDoor("bottom");
                    y++;
                    maze[x][y].makeDoor("top");
                    break;
                default:
                    Position pos = stack.pop();
                    x = pos.getX();
                    y = pos.getY();
                    break;
            }

        } while(!stack.isEmpty());
    }

    private String[] checkPossibleDirections(int i, int j) {
        ArrayList<String> directions = new ArrayList<>();
        if(i>=0&&i<maze.length&&j>=0&&j<maze[0].length) {
            if(i-1>=0&&!maze[i-1][j].isVisited())
                directions.add("left");
            if(i+1<maze.length&&!maze[i+1][j].isVisited())
                directions.add("right");
            if(j-1>=0&&!maze[i][j-1].isVisited())
                directions.add("bottom");
            if(j+1<maze[0].length&&!maze[i][j+1].isVisited())
                directions.add("top");
        }
        if(directions.isEmpty())
            directions.add("none");

        return directions.toArray(new String[0]);
    }

    private void generateTextMaze() {
        String line = "";
        for(int x = 0; x<maze.length; x++) {
            for(int y = 0; y<maze[0].length; y++) {
                if(maze[x][y].isTopWall() && maze[x][y].isLeftWall()){
                    line = x+" "+y+" "+"PD\n";
                }
                else if(maze[x][y].isTopWall()) {
                    line = x+" "+y+" "+"P\n";
                }
                else if(maze[x][y].isLeftWall()) {
                    line = x+" "+y+" "+"D\n";
                }
                else {
                    line = x+" "+y+"\n";
                }
                textMaze.add(line);
            }
        }
    }

    public void writeToFile(File file) {
        generateTextMaze();
        try {
            FileWriter writer = new FileWriter(file);
            for (String line:textMaze) {
                writer.write(line);
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
