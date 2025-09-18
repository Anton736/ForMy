package var_and_World.nature;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;


public abstract class Nature extends MyObjects {
    protected double spirituality;
    public Nature(int posX, int posY, boolean posZ, double spirituality, Colors color, World world)throws OutOfBoardException {
        super(posX, posY, posZ, 0, false, color, world);
        this.spirituality = spirituality;
    }
    public Nature(int posX, int posY, boolean posZ, double spirituality, World world) throws OutOfBoardException{
        super(posX, posY, posZ, 0, false, Colors.NONE, world);
        this.spirituality = spirituality;
    }
    public double getSpirituality(){
        return this.spirituality;
    }

}