package var_and_World;
import var_and_World.enums.*;
import var_and_World.interfaces.*;
import var_and_World.myException.OutOfBoardException;
import var_and_World.shield.Dam;

public class River extends MyObjects implements MakeMoveMe {

    public River(int posX, int posY, World world) throws OutOfBoardException {
        super(posX,posY, false, 1, true, Colors.NONE, world);
           }

    @Override
    public boolean makeMoveMe(int x, int y, boolean z, int speed){
        int x1 = x - super.getPosX();
        int y1 = y - super.getPosY();
        if (getInteraction()){
        if (x1!=0|y1!=0){
            int speedX = speed<Math.abs(x1)?speed:x1;
            int speedY = speed<Math.abs(y1)?speed:y1;
            super.setCoordination(speedX*(int)Math.signum(x1),speedY*(int)Math.signum(y1), z);
            x1 = x - super.getPosX();
            y1 = y - super.getPosY();
            // System.out.println("Объект перемещается");
            return true;
        }
            //System.out.println("Объект достиг точки");
        }
        //System.out.println("Объект не активен");
        return false;
    }
    public void restAgainst(Dam dam){
        if(Math.pow(getPosX()- dam.getPosX(),2)+Math.pow(getPosY()- dam.getPosY(),2)<=Math.pow(dam.getSquare(),2)){
            interaction = false;
        }
    }

}