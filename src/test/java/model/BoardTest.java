package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(4, 4);
    }

    @Test
    void testUpdateProximityOnEdge() {
        Coordinate bombCoordinate = new Coordinate(0, 3);
        board.setCell(new BombedCell(), bombCoordinate);
        board.updateProximity(bombCoordinate);

        int[][] expectedProximities = {
                {0, 0, 1, 9},
                {0, 0, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        verifyProximities(expectedProximities);
    }

    @Test
    void testUpdateProximity() {
        Coordinate bombCoordinate = new Coordinate(1, 1);
        board.setCell(new BombedCell(), bombCoordinate);
        board.updateProximity(bombCoordinate);

        int[][] expectedProximities = {
                {1, 1, 1, 0},
                {1, 9, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        };

        verifyProximities(expectedProximities);
    }

    @Test
    void testUpdateProximityTwice() {
        Coordinate bomb1 = new Coordinate(1, 1);
        Coordinate bomb2 = new Coordinate(2, 2);
        board.setCell(new BombedCell(), bomb1);
        board.setCell(new BombedCell(), bomb2);
        board.updateProximity(bomb1);
        board.updateProximity(bomb2);

        int[][] expectedProximities = {
                {1, 1, 1, 0},
                {1, 9, 2, 1},
                {1, 2, 9, 1},
                {0, 1, 1, 1}
        };

        verifyProximities(expectedProximities);
    }

    private void verifyProximities(int[][] expectedProximities) {
        for (int x = 0; x < expectedProximities.length; x++) {
            for (int y = 0; y < expectedProximities[x].length; y++) {
                Coordinate c = new Coordinate(x, y);
                Cell cell = board.getCell(c);
                if (cell instanceof FreeCell) {
                    int actualProximity = ((FreeCell) cell).getProximity();
                    assertEquals(expectedProximities[x][y], actualProximity,
                            "Proximity at (" + x + ", " + y + ") " +
                                    "should be " + expectedProximities[x][y]);
                }
            }
        }
    }


    @Test
    void testGenerateRandomCoordinates_RightNumber() {
        Set<Coordinate> coordinates = board.generateRandomCoordinates(8, new HashSet<>());
        assertEquals(8, coordinates.size());
    }

    @Test
    void testGenerateRandomCoordinates_Randomness() {
        Set<Coordinate> coordinates1 = board.generateRandomCoordinates(8, new HashSet<>());
        Set<Coordinate> coordinates2 = board.generateRandomCoordinates(8, new HashSet<>());
        assertNotEquals(coordinates1, coordinates2);
    }

  @Test
  void testSafeZone(){
      Set<Coordinate> safeZone = board.generateSafeZoneCoordinates(new Coordinate(1,1));
      Set<Coordinate> bombCoordinates = board.generateRandomCoordinates(3, safeZone);
      board.fillWithBombs(bombCoordinates);
      boolean check = true;
      for(int x=0; x<=2; x++){
          for(int y=0; y<=2; y++){
              if(board.getCell(new Coordinate(x,y)) instanceof BombedCell) check = false;
          }
      }
      assertTrue(check);
  }

}
