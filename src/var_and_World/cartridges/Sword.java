package var_and_World.cartridges;

import var_and_World.*;
import var_and_World.myException.OutOfBoardException;

public class Sword extends BladedWeapon{
    public Sword(int posX, int posY, World world)throws OutOfBoardException {
        super(posX, posY, 6, 2, 5, 30, world);

    }

}