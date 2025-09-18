package var_and_World.shield;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;

public abstract class Protective extends MyObjects{
    double hp;
    double strength;

    public Protective(int posX, int posY, boolean posZ, double hp, double weight, double strength, Colors color, World world)throws OutOfBoardException {
        super(posX, posY, posZ, weight, true, color, world);
        this.hp=hp;
        this.strength = strength;
    }

}