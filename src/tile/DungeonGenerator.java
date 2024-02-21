package tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DungeonGenerator {


    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    
    private class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false; // Rooms are already connected
            }

            parent[rootX] = rootY;
            return true;
        }
    }
	
    private class Corridor implements Comparable<Corridor> {
        Room room1;
        Room room2;
        double distance;

        public Corridor(Room room1, Room room2) {
            this.room1 = room1;
            this.room2 = room2;
            this.distance = calculateDistance(room1, room2);
        }

        private double calculateDistance(Room r1, Room r2) {
            double dx = r1.x + r1.width / 2 - (r2.x + r2.width / 2);
            double dy = r1.y + r1.height / 2 - (r2.y + r2.height / 2);
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override
        public int compareTo(Corridor other) {
            return Double.compare(this.distance, other.distance);
        }
    }
	


    private static final int MAX_ROOMS = 100;
    private static final int MIN_ROOM_SIZE = 10;
    private static final int MAX_ROOM_SIZE = 20;
    private static final int DUNGEONMAP_WIDTH = 150;
    private static final int DUNGEONMAP_HEIGHT = 150;

    private List<Room> rooms;
	private List<int[]> corners = new ArrayList<>();
    public char[][] matrix;

    private List<Corridor> corridors;

    public DungeonGenerator() {
        rooms = new ArrayList<>();
        matrix = new char[DUNGEONMAP_HEIGHT][DUNGEONMAP_WIDTH];
        initializeDungeon();
        generateRooms();
        placePlayer();
        placeFinalBoss();
        printDungeon();
       
    }

    private void initializeDungeon() {
        for (int i = 0; i < DUNGEONMAP_WIDTH; i++) {
            for (int j = 0; j < DUNGEONMAP_HEIGHT; j++) {
                matrix[j][i] = ' ';
            }
        }
    }

    private void generateRooms() {


    

        Random random = new Random();
        for (int i = 0; i < MAX_ROOMS  + 1; i++) {
            int width = random.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;
            int height = random.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;
            int x = random.nextInt(DUNGEONMAP_WIDTH  - 5 - width);
            int y = random.nextInt(DUNGEONMAP_HEIGHT - 5 - height);

            Room newRoom = new Room(x, y, width, height);

            if (!roomIntersects(newRoom)) {
                rooms.add(newRoom);
                for (int roomX = x; roomX < x + width; roomX++) {
                    for (int roomY = y; roomY < y + height; roomY++) {
                        if (roomY == y || roomY == y + height - 1) {
                            matrix[roomY][roomX] = '-';
                        } else if (roomX == x || roomX == x + width - 1) {
                        	matrix[roomY][roomX] = '|';
                        } else {
                        	matrix[roomY][roomX] = ' ';
                        }
                    }
                }
            }

   
        }
        
    	Room finalBossRoom = new Room(130,130,20,20);

            for (int i = 0; i < rooms.size(); i++) {
            	if (rooms.get(i).intersects(finalBossRoom)) {
                    for (int roomX = rooms.get(i).x; roomX < rooms.get(i).x + rooms.get(i).width; roomX++) {
                        for (int roomY = rooms.get(i).y; roomY < rooms.get(i).y + rooms.get(i).height; roomY++) {
                            	matrix[roomY][roomX] = ' ';
                            
                        }
                        }
                    
                    rooms.remove(i);
                    }
            	}
            
    
                
            

    	rooms.add(finalBossRoom);     
        for (int roomX = rooms.get(rooms.size()-1).x; roomX < rooms.get(rooms.size()-1).x + rooms.get(rooms.size()-1).width; roomX++) {
            for (int roomY = rooms.get(rooms.size()-1).y; roomY < rooms.get(rooms.size()-1).y + rooms.get(rooms.size()-1).height; roomY++) {
                if (roomY == rooms.get(rooms.size()-1).y || roomY == rooms.get(rooms.size()-1).y + rooms.get(rooms.size()-1).height - 1) {
                    matrix[roomY][roomX] = '-';
                } else if (roomX == rooms.get(rooms.size()-1).x || roomX == rooms.get(rooms.size()-1).x + rooms.get(rooms.size()-1).width - 1) {
                	matrix[roomY][roomX] = '|';
                } else {
                	matrix[roomY][roomX] = ' ';
                }
            }
        }
        for (Room room : rooms) {
            corners.add(room.getTopLeftCorner());
            corners.add(room.getTopRightCorner());
            corners.add(room.getBottomLeftCorner());
            corners.add(room.getBottomRightCorner());
            
        }
        


        generateCorridors();
        
        for (Room room : rooms) {
            for (int x = room.x + 1; x < room.x + room.width - 1; x++) {
                for (int y = room.y + 1; y < room.y + room.height - 1; y++) {
                    	matrix[y][x] = '.';
                }
            }
        }
        
    }
    


    public void generateCorridors(){
        corridors = new ArrayList<>();

        // Assuming each room's center is represented by a Point object
        for (int i = 0; i < rooms.size() - 1; i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                corridors.add(new Corridor(rooms.get(i), rooms.get(j)));
            }
        }

        // Sort corridors by distance
        Collections.sort(corridors);

        // Initialize Union-Find data structure
        UnionFind unionFind = new UnionFind(rooms.size());

        // Build corridors using dogleg algorithm
        for (Corridor corridor : corridors) {
            int room1Index = rooms.indexOf(corridor.room1);
            int room2Index = rooms.indexOf(corridor.room2);

            if (unionFind.union(room1Index, room2Index)) {
                connectRoomsDogleg(corridor.room1, corridor.room2);
            }
        }
    }

    private void connectRoomsDogleg(Room room1, Room room2) {
        Point center1 = new Point(room1.x + room1.width / 2, room1.y + room1.height/2);
        Point center2 = new Point(room2.x + room2.width / 2, room2.y + room2.height/2);
        
        Point intermediatePoint = new Point(center1.x, center2.y);

        // Connect rooms with a series of L-shaped corridors


        
            drawVerticalCorridor(center1.x, intermediatePoint.x , center1.y, intermediatePoint.y, center2.x, center2.y);
            drawHorizontalCorridor(intermediatePoint.x, center2.x, intermediatePoint.y, center2.y, center1.x, center1.y);


    }

    
    private void drawVerticalCorridor(int startX, int endX, int startY, int endY, int start2X, int start2Y) {
        int minX = Math.max(1, Math.min(startX, DUNGEONMAP_WIDTH - 1));
        int minY = Math.max(1, Math.min(startY, endY));
        int maxY = Math.min(DUNGEONMAP_HEIGHT - 1, Math.max(startY, endY));
        
        
        int horizontalMinY = Math.max(1, Math.min(start2Y, DUNGEONMAP_HEIGHT - 1));
        int horizontalMinX = Math.max(1, Math.min(endX, start2X));
        int horizontalMaxX = Math.min(DUNGEONMAP_WIDTH - 1, Math.max(endX, start2X));
        
        int minXCount = 0;
        int minYCount = 0;
        boolean cornerHit = false;

        for (int y = minY; y <= maxY; y++) {

        	if (minX >= 0 && minX < DUNGEONMAP_WIDTH && (matrix[y][minX] == '|' || matrix[y][minX] == '-')) {
        		
        		minXCount++;  
        		cornerHit = containsCoordinate(minX, y);

        }

    }

        for (int x = horizontalMinX; x <= horizontalMaxX; x++) {

            if (horizontalMinY >= 0 && horizontalMinY < DUNGEONMAP_HEIGHT && (matrix[horizontalMinY][x] == '-' || matrix[horizontalMinY][x] == '|')) {
            	
            	minYCount++; 
            	cornerHit = containsCoordinate(x, horizontalMinY);

            }
        }

        
    if (minXCount > 2) {
    	minX++;
    	if (cornerHit) {
    		minX++;
    	}
    }

    if (minYCount > 2) {
    	maxY++;
    	minY++;
    	if (cornerHit) {
        	maxY++;
        	minY++;
    	}

    }
    if ((minXCount > 2 && minYCount > 2) || (minXCount == 0 && minYCount > 2) || (minXCount > 2 && minYCount == 0)) {
    	drawVerticalCorridor(minX, minX, minY, maxY,horizontalMaxX ,horizontalMinY);
    }
    else {
        for (int y = minY; y <= maxY; y++) {
            if (minX >= 0 && minX < DUNGEONMAP_WIDTH) {

                		matrix[y][minX] = '#';
            }
        }
    }



    }



    
    
    private void drawHorizontalCorridor(int startX, int endX, int startY, int endY, int start2X , int start2Y) {
    	
        int minY = Math.max(1, Math.min(startY, DUNGEONMAP_HEIGHT - 1));
        int minX = Math.max(1, Math.min(startX, endX));
        int maxX = Math.min(DUNGEONMAP_WIDTH - 1, Math.max(startX, endX));
        
        int verticalMinX = Math.max(1, Math.min(start2X, DUNGEONMAP_WIDTH - 1));
        int verticalMinY = Math.max(1, Math.min(start2Y, startY));
        int verticalMaxY = Math.min(DUNGEONMAP_HEIGHT - 1, Math.max(start2Y, startY));

        
        int minYCount = 0;
        int minXCount = 0;
        boolean cornerHit = false;

        for (int x = minX; x <= maxX; x++) {

            if (minY >= 0 && minY < DUNGEONMAP_HEIGHT && (matrix[minY][x] == '-' || matrix[minY][x] == '|')) {
            	
            	minYCount++;  
            	cornerHit = containsCoordinate(x, minY);


            }
        }

        for (int y = verticalMinY; y <= verticalMaxY; y++) {

        	if (verticalMinX >= 0 && verticalMinX < DUNGEONMAP_WIDTH && (matrix[y][verticalMinX] == '|' || matrix[y][verticalMinX] == '-')) {
        		
        		minXCount++;  
        		cornerHit = containsCoordinate(verticalMinX,y);

        	}
        
        }


        if (minYCount > 2) {
        	minY++;
        	
        	if (cornerHit) {
        		minY++;
        	}

        }
        if (minXCount > 2) {
        	minX++;
        	maxX++;
        	
        	if (cornerHit) {
        		minX++;
        		maxX++;
        	}


        }

        if ((minXCount > 2 && minYCount > 2) || (minXCount == 0 && minYCount > 2) || (minXCount > 2 && minYCount == 0)) {
        	drawHorizontalCorridor(minX, maxX, minY, minY, verticalMinX,verticalMinY);
        }
        else {
            for (int x = minX; x <= maxX; x++) {
                if (minY >= 0 && minY < DUNGEONMAP_HEIGHT) {
                		matrix[minY][x] = '#';      	
                }

            }
        }


    }
    
    private boolean containsCoordinate(int targetX, int targetY) {
        for (int[] corner : corners) {
            if (corner[0] == targetX && corner[1] == targetY) {
                return true; // Coordinate found in the list
            }
        }
        return false; // Coordinate not found in the list
    }





   
    private boolean roomIntersects(Room newRoom) {
        for (Room room : rooms) {
            if (room.intersects(newRoom)) {
                return true;
            }
        }
        return false;
    }
    
    public void placePlayer() {
        Room nearestRoom = findNearestRoom(0, 0);

        if (nearestRoom != null) {
            int playerX = nearestRoom.x + nearestRoom.width / 2;
            int playerY = nearestRoom.y + nearestRoom.height / 2;
            matrix[playerY][playerX] = '@';
        }
    }

    private Room findNearestRoom(int targetX, int targetY) {
        Room nearestRoom = null;
        double minDistance = Double.MAX_VALUE;

        for (Room room : rooms) {
            double distance = Math.sqrt(Math.pow(targetX - (room.x + room.width / 2), 2)
                    + Math.pow(targetY - (room.y + room.height / 2), 2));

            if (distance < minDistance) {
                minDistance = distance;
                nearestRoom = room;
            }
        }

        return nearestRoom;
    }
    
    public void placeFinalBoss() {
        Room nearestRoom = findNearestRoom(DUNGEONMAP_WIDTH - 1, DUNGEONMAP_HEIGHT - 1);

        if (nearestRoom != null) {
            int bossX = nearestRoom.x + nearestRoom.width / 2;
            int bossY = nearestRoom.y + nearestRoom.height / 2;
            matrix[bossY][bossX] = 'F';
        }
    }
    

    

    


    private void printDungeon() {
        for (int j = 0; j < DUNGEONMAP_HEIGHT; j++) {
            for (int i = 0; i < DUNGEONMAP_WIDTH; i++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }
    
    


}