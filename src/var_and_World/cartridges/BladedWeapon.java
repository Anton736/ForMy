package var_and_World.cartridges;
import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;

public class BladedWeapon extends Cartridges{

    public BladedWeapon(int posX, int posY, double weight, int square, double damage, double breakingThrough, World world)throws OutOfBoardException {
        super(posX, posY,  weight, square, damage, breakingThrough, world);

    }
    public BladedWeapon(int posX, int posY, double weight, int square, double damage, double breakingThrough, Colors color, World world)throws OutOfBoardException{
        super(posX, posY, weight, square, damage, breakingThrough, color, world);

    }


}