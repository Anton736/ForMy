package var_and_World.live.typePeples;
import var_and_World.*;
import var_and_World.cartridges.Cannonball;
import var_and_World.enums.*;
import var_and_World.gun.*;
import var_and_World.myException.*;

import java.util.HashSet;

public class Artillery extends Military{
    private Cannon cannon;
    public Artillery(int posX, int posY, World p, TypeEnemy typeEnemy, QualificationType... qualificationType)throws OutOfBoardException {
        super(posX, posY,typeEnemy, p,combined(qualificationType, QualificationType.ARTILLERY));

    }
    public Artillery(int posX, int posY, World p, TypeEnemy typeEnemy)throws OutOfBoardException{
        super(posX, posY,typeEnemy, p, QualificationType.ARTILLERY);

    }

    public boolean addCannon(Cannon cannon){
        if(cannon!=null) {
            if (cannon.addPeople(this)) {
                setInteraction(false);
                this.cannon = cannon;
                return true;
            }
        }
        return false;
    }
    public  void deleteCannon(){

        if (cannon!=null){
            cannon.deletePeople(this);
            cannon = null;

        }
        setInteraction(true);
    }
    public boolean isCannon(){
    return cannon!=null;}
    public void fireOnEnemy() throws NotFoundEnimyException {
        if (cannon!=null){
            Military military = getEnemy();
            HashSet <Cannonball> cannonballHashSet = getWorld().getObjects(getPosX(),getPosY(),getPosZ(), Cannonball.class);
            Cannonball cannonball = this.getObjTypeInBag(Cannonball.class);

            put(cannonball);
            cannonballHashSet.add(cannonball);
            for (Cannonball cannonballs : cannonballHashSet){
                if (cannonballs!=null){
                    if( cannon.fire(military.getPosX(), military.getPosY(), military.getPosZ(), cannonballs))
                        {break;}
                }
            }

        }

    }

}