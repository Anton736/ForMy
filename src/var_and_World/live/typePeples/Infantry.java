package var_and_World.live.typePeples;
import var_and_World.*;

import var_and_World.cartridges.*;
import var_and_World.enums.QualificationType;
import var_and_World.live.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;

public class Infantry extends Military{
    public Infantry(int posX, int posY, TypeEnemy typeEnemy, World p, QualificationType... qualification)throws  OutOfBoardException{
        super(posX, posY, typeEnemy, p, combined(qualification, QualificationType.INFANTRY));

    }
    public Infantry(int posX, int posY, TypeEnemy typeEnemy, World p)throws OutOfBoardException {
        super(posX, posY, typeEnemy, p,  QualificationType.INFANTRY);

    }
    @Override
    public void makeAttack(Animals t, MyObjects p){

        if(p instanceof BladedWeapon&&this.isNearObject((((BladedWeapon) p).getSquare()), t)){
           // System.out.println("Пехотинец атакует мечом");
            ((BladedWeapon) p).giveDamage(t, this.getPower()+((BladedWeapon) p).getDamage());

        }
        else if(this.isNearObject(1, t)){t.takeDamage(p, this.getPower());
      //  System.out.println("Пехотинец атакует не мечом");
        }

    }


}