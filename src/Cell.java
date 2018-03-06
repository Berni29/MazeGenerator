public class Cell {
    private boolean visited = false;

    public boolean isLeftWall() {
        return leftWall;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public boolean isTopWall() {
        return topWall;
    }

    public boolean isBottomWall() {
        return bottomWall;
    }

    public boolean isVisited() {
        return visited;
    }

    private boolean leftWall = true , rightWall = true , topWall = true , bottomWall = true;

    public void visit() {
        visited = true;
    }

    public void makeDoor(String direction) {

        switch(direction){
            case "left":
                leftWall = false;
                break;
            case "right":
                rightWall = false;
                break;
            case "top":
                topWall = false;
                break;
            case "bottom":
                bottomWall = false;
                break;
        }
    }
}
