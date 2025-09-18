package var_and_World.nature;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;


public class Clouds extends Nature {
    private int speed;
    double directionTG;
    private int x;
    private int y;
    protected Clouds(int posX, int posY, Colors color, int speed,double directionTG, World world) throws  OutOfBoardException{
        super(posX, posY,true, 10, color, world);

        this.speed = speed;
        this.directionTG = directionTG;
        if (speed==0){
            x = getPosX();
            y = getPosY();
        }
        else {
            if(directionTG == 0) {
                y = 0;
                x = speed > 0 ? getWorld().getX() - getPosX() : -getPosX();
            }
            else {
                x = speed>0?getWorld().getX() - getPosX():-getPosX();

                y=directionTG*Math.signum(x)>0?getWorld().getY() - getPosY() : -getPosY();
                if((Math.abs((int)(x*directionTG))<=Math.abs( y))){
                    y = (int)(x*directionTG);
                }
                else x = (int)(y/directionTG);
            }
            x = x+getPosX();
            y = y+getPosY();
        }
    }
    protected Clouds(int posX, int posY, int speed,double directionTG, World world) throws OutOfBoardException {
        super(posX, posY, true, 10, Colors.WHITE, world);

        this.speed = speed;
        this.directionTG = directionTG;
        if (speed==0){
            x = getPosX();
            y = getPosY();
        }
        else {
            if(directionTG == 0) {
                y = 0;
                x = speed > 0 ? getWorld().getX() - getPosX() : -getPosX();
            }
            else {
                x = speed>0?getWorld().getX() - getPosX():-getPosX();

                y=directionTG*Math.signum(x)>0?getWorld().getY() - getPosY() : -getPosY();
                if((Math.abs((int)(x*directionTG))<=Math.abs( y))){
                    y = (int)(x*directionTG);
                }
                else x = (int)(y/directionTG);
            }
            x = x+getPosX();
            y = y+getPosY();
        }
    }

    public Clouds makeMoveMe(){
        if(speed!=0){
        int x1 = x - getPosX();
        int y1 = y - getPosY();

        if (x1!=0||y1!=0){
            int speedX = Math.abs(speed)<Math.abs(x1)?speed:x1;
            int speedY =Math.abs( speed)<Math.abs(y1)?speed:y1;
            super.setCoordination(Math.abs(speedX) * (int) Math.signum(x1), Math.abs(speedY) * (int) Math.signum(y1), false);

           // System.out.println("Объект перемещается");
            return this;
        }
        else {

        return cloudsRichHere();}
        }
        return this;


    }
    public Clouds cloudsRichHere(){

        if (getPosX() == x&&getPosY()==y){
            int x0 = speed>0?0:getWorld().getX();



            this.setReadyDie();
            this.getWorld().deleteObject(this);
            try {

           return new Clouds(x0,(int)(Math.random()*getWorld().getY() ),this.getColor(), speed, directionTG, getWorld());}
            catch (OutOfBoardException e){
                System.out.println("Ошибка попытка создать облако вне карты это плохо "+ e.getMessage());
                return null;
            }


        }

        return this;
    }
}