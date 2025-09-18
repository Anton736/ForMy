package var_and_World.gun;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.cartridges.*;
import var_and_World.live.*;
import var_and_World.live.typePeples.Military;
import var_and_World.myException.OutOfBoardException;

import java.util.HashSet;

public class Cannon extends LongKnife{
    private HashSet<Peoples> quantityPeoples = new HashSet<>();
    private TypeEnemy typeCannon;

    public Cannon(int posX, int posY,  World world)throws OutOfBoardException {
        super(posX, posY, false, 60, 25, 3, 8, true, Colors.BLACK, world);

    }
    public boolean addPeople(Peoples peoples){
        isLivePerson();
        if(isNearObject(2,peoples) & (typeCannon == null || typeCannon == peoples.getTypeEnemy()||peoples.getTypeEnemy() == null)){
        quantityPeoples.add(peoples);
        typeCannon=peoples.getTypeEnemy();
        return true;
        }
        return false;

    }
    public void deletePeople(Peoples military){
        if ((military.getReadyDie()||isNearObject(2,military))&quantityPeoples.contains(military)){
            quantityPeoples.remove(military);
            if (!quantityPeoples.isEmpty()){
            for (Peoples military1: quantityPeoples){
                typeCannon=military1.getTypeEnemy();
                break;
            }}
            else typeCannon=null;

        }

    }
    public void preparation(Cannonball cannonball){
        if(quantityPeoples.size() >= 3&isNearObject(2, cannonball)){
            int count = 0;
            isLivePerson();
            for (Peoples p : quantityPeoples) {


                if (p.checkQualification(QualificationType.ARTILLERY)& typeCannon== null&!p.getReadyDie()) {

                    typeCannon = p.getTypeEnemy();

                  //  System.out.println("Пушка готова стрелять");
                }
                if(p.getTypeEnemy() == typeCannon&!p.getReadyDie()){
                    count++;
                }
                if (count>=3& typeCannon!=null){
                    ready = true;
                }



            }
        }

    }
    private boolean preparationFire(Cannonball t, int posX, int posY, boolean posZ){

        if(ready) {
            System.out.println("Пушка стреляет");
            t.fire(posX, posY, posZ, 6,super.power);

            ready=false;
            return true;
        }
        return false;
    }
    public boolean fire(int posX, int posY, boolean posZ, Cannonball cannonball){
        preparation(cannonball);
        return preparationFire(cannonball, posX, posY, posZ);

    }
    public void isLivePerson(){
        if(quantityPeoples!=null){
            for (Peoples peoples : quantityPeoples){
                if (peoples.getReadyDie()){
                    deletePeople(peoples);
                }
            }
        }
    }
}