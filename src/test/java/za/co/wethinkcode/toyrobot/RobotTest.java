package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Command;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void setStatus(){
        Robot bot = new Robot("TestDummy");
        bot.setStatus("New status set!");
        assertEquals("New status set!", bot.getStatus());
    }

    @Test
    void initialPosition() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals(robot.CENTRE, robot.getPosition());
        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals("[0,0] CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertTrue(robot.handleCommand(shutdown));
    }

    @Test
    void forward() {
        Robot robot = new Robot("CrashTestDummy");
        Command forward = Command.create("forward 10");
        assertTrue(robot.handleCommand(forward));
        Position expectedPosition = new Position(robot.CENTRE.getX(), robot.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void forwardForward() {
        Robot robot = new Robot("CrashTestDummy");
        Command forward = Command.create("forward 10");
        assertTrue(robot.handleCommand(forward));
        Command forwardAgain = Command.create("forward 5");
        assertTrue(robot.handleCommand(forwardAgain));
        Position expectedPosition = new Position(robot.CENTRE.getX(), robot.CENTRE.getY() + 15);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 5 steps.", robot.getStatus());
    }

    @Test
    void tooFarForward() {
        Robot robot = new Robot("CrashTestDummy");
        Position currentPosition = robot.getPosition();
        Command forward = Command.create("forward 100000");
        assertTrue(robot.handleCommand(forward));
        assertEquals(currentPosition, robot.getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }

    @Test
    void help() {
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(robot.handleCommand(help));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'", robot.getStatus());
    }
}