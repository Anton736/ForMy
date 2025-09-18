package var_and_World.live;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;
import var_and_World.nature.*;

public class Horses extends Animals{
    public Horses(int posX, int posY, Colors color, double armor, World p)throws OutOfBoardException{
        super(100, 20, 4, true, posX, posY, false, 5, color, false,armor, p);
        voice = "И-и-го-го";
    }
    public Horses(int posX, int posY, World p)throws OutOfBoardException{
        super(100, 20, 4, true, posX, posY, false, 5, false, p);

        voice = "И-и-го-го";
    }
    @Override
    public Languages getLanguage(){
        return null;
    }
    @Override
    public Sounds makeSound() {
        try{
        return new Sounds(this, 0,  getWorld());} catch (OutOfBoardException e) {
            System.out.println("Звук был выпущен вне карты это по меньшей мере странно" + e.getMessage());
            return null;
        }
    }

    public boolean makeMoveMe(int x, int y, boolean z, int speed){

        if(getWorld().getObjects(this.getPosX(), this.getPosY(), this.getPosZ(), River.class).isEmpty()) return super.makeMoveMe(x,y,z,speed);
        else return super.makeMoveMe(x,y,z,speed-1);
    }
}