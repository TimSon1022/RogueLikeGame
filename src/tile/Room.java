package tile;



class Room {
    int x, y, width, height;

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
        return new int[]{x + width - 1, y};
    }

    public int[] getBottomLeftCorner() {
        return new int[]{x, y + height - 1};
    }

    public int[] getBottomRightCorner() {
        return new int[]{x + width - 1, y + height - 1};
    }

    public boolean intersects(Room other) {

        return x <= other.x + other.width &&
               x + width >= other.x &&
               y <= other.y + other.height &&
               y + height >= other.y;
    }
}


