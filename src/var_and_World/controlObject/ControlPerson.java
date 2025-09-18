package var_and_World.controlObject;


import var_and_World.cartridges.*;
import var_and_World.enums.QualificationType;
import var_and_World.enums.TypeEnemy;
import var_and_World.gun.Cannon;
import var_and_World.live.*;
import var_and_World.live.typePeples.*;
import var_and_World.myException.NotFoundEnimyException;
import var_and_World.myException.OutOfBoardException;
import var_and_World.shield.Armor;

import java.util.HashSet;

public class ControlPerson {
    private Military military;
   private boolean isWeapon1;
   private boolean isWeapon2;
   private boolean isFoundWeapon1 = true;
   private boolean isFoundWeapon2 = true;



    public ControlPerson(Military military){
         this.military = military;

    }

    public boolean foundWeapon1(){
        if(military.getClass() == Infantry.class){
            isFoundWeapon1 = (military.goToSomeClasses(BladedWeapon.class));
            return !isFoundWeapon1;
        }
        else if(military.getClass()== Cavalry.class){
            isFoundWeapon1 = (military.goToSomeClasses(Horses.class));
            return !isFoundWeapon1;
        }
        else if (military.getClass()==Artillery.class&&military.foundSomeThing(military.getPosX(), military.getPosY(), Cannonball.class)!=null){

           isFoundWeapon1 = (military.goToSomeClasses(Cannonball.class));

           return !isFoundWeapon1;


        }
        else if(military.getClass()==Artillery.class){
            try {

            military= new Infantry(military.getPosX(), military.getPosY(), military.getTypeEnemy(), military.getWorld(), QualificationType.ARTILLERY);}
            catch (OutOfBoardException e){
                System.out.println("Попытка создать воина в классе ControlPerson вне поля действий это плохо" + e.getMessage());
            }
        }
        isFoundWeapon1 = false;
        return  true;
    }
    public boolean takeWeapon1(){
        if(military.getClass() == Infantry.class){

         return isWeapon1 = military.take(military.foundSomeThing(military.getPosX(), military.getPosY(), BladedWeapon.class));
        }
        else if(military.getClass()== Cavalry.class){

          return isWeapon1 = military.saddle(military.foundSomeThing(military.getPosX(), military.getPosY(), Horses.class));



        }
        else if (military.getClass()==Artillery.class){

            return isWeapon1 = military.take(military.foundSomeThing(military.getPosX(), military.getPosY(), Cannonball.class));

        }
        return isWeapon1 = true;
    }
    public boolean foundWeapon2(){
        if(military.getClass() == Infantry.class){
            isFoundWeapon2 = (military.goToSomeClasses(Armor.class));
            return !isFoundWeapon2;
        }
        else if(military.getClass()== Cavalry.class){
            isFoundWeapon2 = (military.goToSomeClasses(BladedWeapon.class));
            return !isFoundWeapon2;
        }
        else if (military.getClass()==Artillery.class){
            isFoundWeapon2 =(military.goToSomeClasses(Cannon.class));
            return !isFoundWeapon2;
        }
        isFoundWeapon2 = false;
        return true;
    }
    public boolean takeWeapon2(){
        if(military.getClass() == Infantry.class){

            return isWeapon2 = military.take(military.foundSomeThing(military.getPosX(), military.getPosY(), Armor.class));
        }
        else if(military.getClass()== Cavalry.class){

            return isWeapon2 = military.take(military.foundSomeThing(military.getPosX(), military.getPosY(), BladedWeapon.class));



        }
        else if (military.getClass()==Artillery.class){

            return isWeapon2 = ((Artillery)military).addCannon(military.foundSomeThing(military.getPosX(), military.getPosY(), Cannon.class));

        }
        return isWeapon2 = true;
    }

    public void addAndAttackEnemy()throws NotFoundEnimyException {
        if (military.getClass()==Artillery.class&&military.foundSomeThing(military.getPosX(), military.getPosY(),Cannonball.class)!=null){
            ((Artillery)military).fireOnEnemy();

            HashSet <Cannonball> cannonballHashSet = military.getWorld().getObjects(military.getPosX(),military.getPosY(),military.getPosZ(), Cannonball.class);
            Cannonball cannonball = military.getObjTypeInBag(Cannonball.class);
            isWeapon1 = cannonball != null || !cannonballHashSet.isEmpty();
            isWeapon2 = ((Artillery) military).isCannon();


        }
        else {
            if (military.getClass()==Artillery.class){
                ((Artillery)military).deleteCannon();
            }
          if(!military.gotoSomeone((military.getEnemy()))){

              BladedWeapon bladedWeapon= military.getObjTypeInBag(BladedWeapon.class);
              if(bladedWeapon!=null){
              military.makeAttack(military.getEnemy(), bladedWeapon);}
              else  military.makeAttack(military.getEnemy(), military);


          }
        }
    }
    public boolean go(){
        if (!isWeapon1 & isFoundWeapon1){
            if(foundWeapon1()){
             isWeapon1=  takeWeapon1();}
            return true;
        }
        else if(!isWeapon2 & isFoundWeapon2) {
            if(foundWeapon2()){
            isWeapon2=  takeWeapon2();}
            return true;
        }

        else{
            try{
             addAndAttackEnemy();
            return true;}
            catch (NotFoundEnimyException e){
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
    public boolean isRussian(){
        return military.getTypeEnemy() == TypeEnemy.RUSSIAN;
    }
    public boolean isLive(){

        return !military.getReadyDie();
    }

}