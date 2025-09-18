package var_and_World.live.typePeples;
import var_and_World.*;

import var_and_World.enums.*;

import var_and_World.live.*;
import var_and_World.myException.NotFoundEnimyException;
import var_and_World.myException.OutOfBoardException;


import java.util.Set;
import java.util.logging.*;



public  class Military extends Peoples {

  private   static final Logger LOGGER = Logger.getLogger(Military.class.getName());
    protected Military(int posX, int posY, TypeEnemy typeEnemy, World world, QualificationType... qualification)throws OutOfBoardException {
        super(posX, posY, typeEnemy,world,Languages.NotMind,qualification);

    }

    public Military getEnemy()throws NotFoundEnimyException{

        Military enimyMilitary;
        //LOGGER.log(Level.INFO,"Ищем врага");
        int x0 = getPosX();
        int y0 = getPosY();
        int s = World.getS();

        int[][] d = {{1, 0, -1, 0},{0, 1, 0, -1}};
        int direction = 0;
        int stepsTaken = 0;
        int stepsNeeded = 1;
        int cellsChecked = 0;

        while (cellsChecked < this.getWorld().getX() * this.getWorld().getY()) {
            if (x0 >= 0 && x0 < this.getWorld().getX() && y0 >= 0 && y0 < this.getWorld().getY()) {
                cellsChecked++;
                Set<Military> militaries = this.getWorld().getObjects(x0, y0, Military.class).keySet();
                if (!militaries.isEmpty()) {
                    for (Military military : militaries) {
                        if (military.getTypeEnemy() != getTypeEnemy()) {
                            gotoSomeone(military);
                            LOGGER.log(Level.INFO, "Враг найден");
                            enimyMilitary = military;
                            return enimyMilitary;
                        }

                    }
                }

            }
            x0=x0+d[0][direction]*s;
            y0=y0+d[1][direction]*s;
            stepsTaken++;
            if(stepsTaken==stepsNeeded){
                stepsTaken=0;
                direction=(direction+1)%4;
                if((direction)%2==0){
                    stepsNeeded++;
                }
            }
        }
        throw new NotFoundEnimyException("Враги не найдены возможно их нет на карте");


    }
    public void makeAttack(Animals t, MyObjects p){


             System.out.println("Воин атакует");
        if(this.isNearObject(1, t)){
            t.takeDamage(p, this.getPower());}



    }
    public <X extends MyObjects> boolean goToSomeClasses(Class<X> targetClass){
        int x0 = getPosX();
        int y0 = getPosY();



        System.out.println("Ищем " + targetClass.getSimpleName());
        X objSomeClass=foundSomeThing(x0,y0,targetClass);

        return this.gotoSomeone(objSomeClass);
    }

}