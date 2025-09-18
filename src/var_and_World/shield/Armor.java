package var_and_World.shield;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.live.*;
import var_and_World.myException.OutOfBoardException;

public class Armor extends Protective {
    boolean isUsed = false;
    public Armor(int posX, int posY, boolean posZ, World world)throws OutOfBoardException {
        super(posX, posY, false, 20, 10,5, Colors.NONE, world);

    }
    public Armor(int posX, int posY, boolean posZ, Colors color, World world)throws OutOfBoardException{
        super(posX, posY, false, 20, 10,5, color, world);

    }


    public void use(Animals p) {
        if(p.haveUnBag(this)){
        if(!isUsed){

            p.setHp(hp+p.getHp());
            p.setArmor(strength+p.getArmor());
            isUsed = true;
           // System.out.println("Используем бронежилет");
        }
        }
    }
    public void unused(Animals p) {
        if(isUsed){
            p.setHp(hp-p.getHp());
            p.setArmor(strength-p.getArmor());
            isUsed = false;
           // System.out.println("Снимаем бронежилет");
        }
    }
}