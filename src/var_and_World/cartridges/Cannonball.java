package var_and_World.cartridges;
import var_and_World.enums.*;
import var_and_World.live.*;
import var_and_World.*;
import var_and_World.interfaces.*;
import var_and_World.myException.OutOfBoardException;

import java.util.HashSet;


public class Cannonball extends Cartridges implements MakeMoveMe{
    private int xEnd = 0;
    private int yEnd = 0;
    private boolean zEnd;
    private int speedEnd;
    private boolean isFire = false;
    private int xMiddle;
    private int yMiddle;
    private boolean zMiddle;
    private int speedMiddle;
    private double powEnd;
    public Cannonball(int posX, int posY, World world)throws OutOfBoardException {
        super(posX, posY, 10, 4, 10, 5, Colors.BLACK, world);
        xMiddle = getPosX();
        yMiddle = getPosY();
        zMiddle = getPosZ();
        speedMiddle=0;

    }

    private void giveDamageAll(World w, double power){
        int t = World.getS();
        int step = this.square/t>=1? square/t:0;
        for(int k =0; k<2*this.square; k+=t){
            for (int j = 0;j<2*this.square; j+=t){

                HashSet<Animals> b = this.getWorld().getObjects(k-this.square+this.getPosX(), j-this.square+this.getPosY(),  this.getPosZ(), Animals.class);
                for(Animals i: b){
                    this.giveDamage(i, power + this.damage);

                }

            }
        }
        this.setReadyDie();
        this.getWorld().deleteObject(this);
        System.out.println("Ядро взрывается");
    }
    @Override
    public boolean makeMoveMe(int x, int y, boolean z, int speed){
        if (isFire) {
            xMiddle=xEnd;
            yMiddle=yEnd;
            zMiddle = zEnd;
            speedMiddle = speedEnd;
            if(getPosX()==xEnd&&getPosZ()==zEnd&&getPosY()==yEnd){
                giveDamageAll(super.getWorld(), powEnd);}

                System.out.println("Ядро летит к цели");

        }
        else{
        if (getInteraction()) {
            xMiddle=x;
            yMiddle=y;
            zMiddle = z;
            speedMiddle = speed;
            int x1 = xMiddle - super.getPosX();
            int y1 = yMiddle - super.getPosY();
            if (x1 != 0 || y1 != 0) {
                int speedX = speedMiddle < Math.abs(x1) ? speedMiddle : x1;
                int speedY = speedMiddle < Math.abs(y1) ? speedMiddle : y1;
                super.setCoordination(Math.abs(speedX) * (int) Math.signum(x1), Math.abs(speedY) * (int) Math.signum(y1), zMiddle);

                return true;
            }
            //System.out.println("Ядро на месте");

        }else {System.out.println("ядро не активно");
        }

        }
        return false;
    }
    public  void  fire(int x, int y, boolean z, int speed, double pow){
        speedEnd = speed;
        isFire = true;
        xEnd =x;
        yEnd =y;
        zEnd =z;
        powEnd = pow;
        if(!makeMoveMe(xEnd, yEnd, zEnd, speed)){
        giveDamageAll(super.getWorld(), pow);}

    }
    public void makeMoveMe(){
        if( makeMoveMe(xMiddle,yMiddle,zMiddle,speedMiddle)&isFire){
            giveDamageAll(getWorld(), powEnd);
        }

    }
}