package main;



public class Room {
    public int x, y, width, height;
    boolean enter = false;

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    

    public int[] getTopLeftCorner() {
        return new int[]{x, y};
    }

    public int[] getTopRightCorner() {
        return new int[]{x + width, y};
    }

    public int[] getBottomLeftCorner() {
        return new int[]{x, y + height};
    }

    public int[] getBottomRightCorner() {
        return new int[]{x + width - 1, y + height};
    }

    public boolean intersects(Room other) {

        return x <= other.x + other.width &&
               x + width >= other.x &&
               y <= other.y + other.height &&
               y + height >= other.y;
    }
}


