package game.world.generation;

import game.world.Direction;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CoordinatesTest {

    @Test
    public void equals_givenOtherCoordinatesObject_returnsCorrectValue() {
        Coordinates coords1 = new Coordinates(1, 2);
        Coordinates coords2 = new Coordinates(1, 2);
        Coordinates coords3 = new Coordinates(2, 3);

        assertEquals(coords1, coords2);
        assertNotEquals(coords1, coords3);
    }

    @Test(dataProvider = "neighbours")
    public void getNeighbour_givenADirection_returnsCoordsInThatDirection(Coordinates origin, Direction dir,
                                                                          Coordinates dest, boolean isNeighbour) {
        assertEquals(dest.equals(origin.getNeighbour(dir)), isNeighbour);
    }

    @DataProvider
    private Object[][] neighbours() {
        return new Object[][] {
                {new Coordinates(1, 1), Direction.NORTH, new Coordinates(1, 0), true},
                {new Coordinates(1, 1), Direction.NORTH, new Coordinates(0, 1), false},
                {new Coordinates(1, 1), Direction.WEST, new Coordinates(0, 1), true},
                {new Coordinates(1, 1), Direction.WEST, new Coordinates(1, 0), false},
                {new Coordinates(1, 1), Direction.EAST, new Coordinates(2, 1), true},
                {new Coordinates(1, 1), Direction.EAST, new Coordinates(1, 2), false},
                {new Coordinates(1, 1), Direction.SOUTH, new Coordinates(1, 2), true},
                {new Coordinates(1, 1), Direction.SOUTH, new Coordinates(2, 1), false}
        };
    }

    @Test(dataProvider = "validCoords")
    public void isValid_returnsTrueForPositiveCoordinates(Coordinates coords, boolean expectedValidity) {
        assertEquals(expectedValidity, coords.isValid());
    }

    @DataProvider
    private Object[][] validCoords() {
        return new Object[][] {
                {new Coordinates(0, 0), true},
                {new Coordinates(1, 1), true},
                {new Coordinates(-1, 1), false},
                {new Coordinates(1, -1), false},
                {new Coordinates(-1, -1), false}
        };
    }
}