package var_and_World.controlObject;

import var_and_World.River;
import var_and_World.World;
import var_and_World.myException.OutOfBoardException;
import var_and_World.shield.Dam;

import java.util.HashSet;

public class ControlRiver {
    int posX;
    int posY;
    int endPosX;
    int endPosY;
    World world;
    boolean isHavaDam;
   private static HashSet<Dam> dams = new HashSet<>();
   private HashSet<River> rivers = new HashSet<>();
    public ControlRiver(int posX, int posY, int endPosX, int endPosY, boolean isHavaDam, World world){
        this.posX = posX;
        this.posY =posY;
        this.endPosX =posX;
        this.endPosY =posY;
        this.world =world;
        this.isHavaDam = isHavaDam;
        if(isHavaDam){
            isHavaDam = false;
            for (int i = 0; i<3; i++){
               Dam dam=  Dam.createDam((posX+endPosX)/2+i, (posY+endPosY)/2 +i, false, 2, world);
                       if(dam!=null){
                dams.add(dam);}
            }
        }
    }
    public void controlRiverWork(){
        try {


            rivers.add(new River(posX, posY, world));
            for (River river : rivers) {
                river.makeMoveMe(endPosX, endPosY, false, 2);
            }
            for (Dam dam : dams) {
                dam.damWork();
            }
        }
        catch (OutOfBoardException e){
            System.out.println("Попытка создать реку вне карты "+e.getMessage());
        }

    }
    public HashSet<Dam> getDams(){return dams;}
}