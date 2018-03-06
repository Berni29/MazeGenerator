import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Maze {

    Cell[][] maze;
    LinkedList<Position> stack = new LinkedList<>();
    Random rng = new Random();

    public Maze(int width, int height) {
        maze = new Cell[width][height];
        for(int j=0; j<maze[0].length; j++) {
            for(int i=0; i<maze.length; i++) {
                maze[i][j] = new Cell();
            }
        }
        generateMaze();
        maze[0][0].makeDoor("left");
        maze[width-1][height-1].makeDoor("right");
    }

    private void generateMaze() {
        int i = 0 , j = 0;
        do {
            if(!maze[i][j].isVisited()) {
                stack.push(new Position(i, j));
                maze[i][j].visit();
            }
            String[] directions = checkPossibleDirections(i,j);
            switch(directions[rng.nextInt(directions.length)]) {
                case "left":
                    maze[i][j].makeDoor("left");
                    i--;
                    maze[i][j].makeDoor("right");
                    break;
                case "right":
                    maze[i][j].makeDoor("right");
                    i++;
                    maze[i][j].makeDoor("left");
                    break;
                case "bottom":
                    maze[i][j].makeDoor("top");
                    j--;
                    maze[i][j].makeDoor("bottom");
                    break;
                case "top":
                    maze[i][j].makeDoor("bottom");
                    j++;
                    maze[i][j].makeDoor("top");
                    break;
                default:
                    Position pos = stack.pop();
                    i = pos.getI();
                    j = pos.getJ();
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

    public void printMaze() {
        for(int j = 0; j<maze[0].length; j++) {
            for(int i = 0; i<maze.length; i++){
                if(maze[i][j].isTopWall())
                    System.out.print("+---");
                else
                    System.out.print("+   ");
            }
            System.out.println("+");
            for(int i = 0; i<maze.length; i++){
                if(maze[i][j].isLeftWall())
                    System.out.print("|   ");
                else
                    System.out.print("    ");
            }
            if(j<maze[0].length-1)
                System.out.println("|");
            else
                System.out.println(" ");
            if(j==maze[0].length-1) {
                for (int i = 0; i < maze.length; i++) {
                    if (maze[i][j].isBottomWall())
                        System.out.print("+---");
                    else
                        System.out.print("+   ");
                }
                System.out.println("+");
            }
        }
    }

    public String getMaze() {
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j<maze[0].length; j++) {
            for(int i = 0; i<maze.length; i++){
                if(maze[i][j].isTopWall())
                    sb.append("+---");
                else
                    sb.append("+   ");
            }
            sb.append("+\n");
            for(int i = 0; i<maze.length; i++){
                if(maze[i][j].isLeftWall())
                    sb.append("|   ");
                else
                    sb.append("    ");
            }
            if(j<maze[0].length-1)
                sb.append("|\n");
            else
                sb.append(" \n");
            if(j==maze[0].length-1) {
                for (int i = 0; i < maze.length; i++) {
                    if (maze[i][j].isBottomWall())
                        sb.append("+---");
                    else
                        sb.append("+   ");
                }
                sb.append("+\n");
            }
        }
        return sb.toString();
    }
}
