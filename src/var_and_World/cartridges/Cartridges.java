package var_and_World.cartridges;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.live.*;
import var_and_World.myException.OutOfBoardException;

public abstract class Cartridges extends MyObjects{
    protected int square;
    protected double breakingThrough;
    double damage;
    public Cartridges(int posX, int posY, double weight, int square, double damage, double breakingThrough, World world)throws OutOfBoardException {
        super(posX, posY, false, weight, true, world);
        this.square = square;
        this.breakingThrough = breakingThrough;
        this.damage = damage;

    }
    public Cartridges(int posX, int posY, double weight, int square, double damage, double breakingThrough, Colors color, World world)throws  OutOfBoardException{
        super(posX, posY, true, weight, true, color, world);
        this.square = square;
        this.breakingThrough = breakingThrough;
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }
    public int getSquare(){return square;}
    public void giveDamage(Animals p, double damage){
        if(Math.sqrt(Math.pow(p.getPosX()-this.getPosX(), 2)+Math.pow(p.getPosY()-this.getPosY(),2))<=this.getSquare()){
            double x = (breakingThrough - p.getArmor())/ breakingThrough;
            x = x>0? x :0;
            System.out.println("Наносит урон");
            p.takeDamage(p, damage * x);

        }
    }

}