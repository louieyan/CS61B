package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    private double mvEnergyLose = 0.03;
    private double stayEnergyLose = 0.01;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }


    @Override
    public void move() {
        energy -= mvEnergyLose;
        energy = Math.max(energy, 0);
    }

    @Override
    public void stay() {
        energy -= stayEnergyLose;
        energy = Math.max(energy, 0);
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        Clorus offspring = new Clorus(energy * 0.5);
        return offspring;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.addLast(key);
            } else if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.addLast(key);
            }
        }


        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plipNeighbors.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
