package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void testDefualtDirection(){
        assertEquals("NORTH", Direction.NORTH.name());
    }

    @Test
    void testDirectionEquals(){
       assertTrue(Direction.NORTH.equals(Direction.NORTH));
    }
}