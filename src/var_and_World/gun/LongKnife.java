package var_and_World.gun;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;

public class LongKnife extends MyObjects{
    double power;
    int quantity;
    int length;
    boolean ready = false;
    public LongKnife(int posX, int posY, boolean posZ, double weight, double power, int quantity, int length, boolean interaction, Colors color, World world)throws OutOfBoardException {
        super(posX, posY, posZ, weight, true, color, world);
        this.power = power;
        this.quantity = quantity;
        this.length = length;
    }


}