package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(1);
        Plip p = new Plip(1.1);
        c.attack(p);
        assertEquals(2.1, c.energy(), 0.01);

    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(1);
        Clorus newc = c.replicate();
        assertEquals(c.energy() * 0.5, newc.energy(), 0.01);

        assertNotEquals(c, newc);
    }

    @Test
    public void testChoose() {
        // if no empty squares, STAY
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // if plips are seen, ATTACK
        // the one of the neighbors should be empty
        Clorus c2 = new Clorus(1);
        HashMap<Direction, Occupant> topPlip = new HashMap<>();
        topPlip.put(Direction.TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Impassible());
        topPlip.put(Direction.LEFT, new Empty());
        topPlip.put(Direction.RIGHT, new Impassible());

        Action actual1 = c2.chooseAction(topPlip);
        Action expected1 = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected1, actual1);

        // if has energy greater than 1, REPLICATE
        // one neighbor should be empty
        // and no plip
        Clorus c3 = new Clorus(2);
        HashMap<Direction, Occupant> bottomEmpty = new HashMap<>();
        bottomEmpty.put(Direction.TOP, new Impassible());
        bottomEmpty.put(Direction.BOTTOM, new Empty());
        bottomEmpty.put(Direction.LEFT, new Impassible());
        bottomEmpty.put(Direction.RIGHT, new Impassible());

        Action actual2 = c3.chooseAction(bottomEmpty);
        Action expected2 = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(actual2, expected2);

        // otherwise MOVE
        // one neighbor should be empty
        // no plip
        // energy is smaller than 1
        Clorus c4 = new Clorus(0.5);
        HashMap<Direction, Occupant> leftEmpty = new HashMap<>();
        bottomEmpty.put(Direction.TOP, new Impassible());
        bottomEmpty.put(Direction.BOTTOM, new Impassible());
        bottomEmpty.put(Direction.LEFT, new Empty());
        bottomEmpty.put(Direction.RIGHT, new Impassible());

        Action actual3 = c4.chooseAction(bottomEmpty);
        Action expected3 = new Action(Action.ActionType.MOVE, Direction.LEFT);
        assertEquals(actual3, expected3);



    }
}
