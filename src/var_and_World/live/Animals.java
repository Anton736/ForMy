package var_and_World.live;
import var_and_World.*;
import var_and_World.cartridges.Cartridges;
import var_and_World.enums.*;

import var_and_World.myException.OutOfBoardException;
import var_and_World.nature.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import var_and_World.interfaces.*;

public abstract class Animals extends MyObjects implements MakeMoveMe, MakeSounds {
    private HashSet<MyObjects> bag = new HashSet<>();
    private Set<Integer> effects;
    protected double spirituality = 1;
    protected String voice;
    protected boolean speech;
    int speed;
    double power;
    boolean manual;
    boolean inGroup;
    private double hp;
    double armor = 0;
    protected boolean isCanSaddle = true;

    protected Animals(double hp, double power, int speed, boolean manual,int posX, int posY, boolean posZ, double weight, Colors color, boolean speech, double armor, World p )throws OutOfBoardException{
        super(posX, posY, posZ, weight, true, color, p);
        this.speed = speed;
        this.manual = manual;
        this.power = power;
        this.inGroup = false;
        this.hp = hp;
        this.speech = speech;
        this.armor = armor;

    }
    protected Animals(double hp, double power, int speed, boolean manual, int posX, int posY, boolean posZ, double weight, boolean speech, World p) throws OutOfBoardException {
        super(posX, posY, posZ, weight, true, Colors.NONE, p);
        this.speed = speed;
        this.manual = manual;
        this.power = power;
        this.inGroup = false;

        this.hp = hp;
        this.speech = speech;
    }
    @Override
    public Sounds makeSound() {
        try {


       // System.out.println("Издает звук");
        return new Sounds(this, super.getWorld());}
        catch (OutOfBoardException e){
            System.out.println("Звук был выпущен вне карты это по меньшей мере странно " + e.getMessage());
            return null;
        }
    }
    @Override
    public String getVoice(){
        return voice;
    }
    @Override
    public boolean getSpeech(){return speech;}
    public void takeDamage(MyObjects p, double damage){
        if (p instanceof Cartridges && this.isNearObject(((Cartridges)p).getSquare(), p)){
            hp = hp - damage;
        //    System.out.println("Получает урон");
            if (hp <= 0){
                super.setReadyDie();
                Corpses corpses = die();
                System.out.println("Умирает " + this.getClass().getSimpleName());
            }
        }
       else if(getPosX() == p.getPosX() && getPosY() == p.getPosY() && getPosZ() == p.getPosZ()){//проверка равенства координат
            hp = hp - damage;
        //    System.out.println("Получает урон");
            if (hp <= 0){
                super.setReadyDie();
                Corpses corpses = die();
                System.out.println("Умирает " + this.getClass().getSimpleName());
            }
       }

    }

    @Override
    public boolean makeMoveMe(int x, int y, boolean z, int speed){
        if (getInteraction()){
       int x1 = x - super.getPosX();
       int y1 = y - super.getPosY();
       if (x1!=0||y1!=0){
           int speedX = speed<Math.abs(x1)?speed:x1;
           int speedY = speed<Math.abs(y1)?speed:y1;
           super.setCoordination(Math.abs(speedX)*(int)Math.signum(x1),Math.abs(speedY)*(int)Math.signum(y1), z);

           System.out.println("Движется " + getClass().getSimpleName());
           return true;
       }

     //  System.out.println("Animal coming in place");
        }
        else {
            System.out.println("Персонаж не активен "+ this.getClass().getSimpleName());
        }
        carryBag();
        return false;
    }

    public boolean gotoSomeone(MyObjects myObjects){
        if(myObjects!=null&&(myObjects.getInteraction()||myObjects instanceof Peoples)){
       // System.out.println("Идет за кем-то");
        return (makeMoveMe(myObjects.getPosX(),myObjects.getPosY(), myObjects.getPosZ(), this.speed));}
        return false;

    }
    public double getHp(){
        return this.hp;
    }
    public double getPower(){
        return this.power;
    }

    public double getArmor(){return this.armor;}
    public void setHp(double hp) {
        this.hp = hp;
    }
    public boolean getIsCanSaddle(){return isCanSaddle;}
    public void setArmor(double armor) {
        this.armor = armor;
    }
    public <T extends MyObjects> T getObjTypeInBag(Class<T> clazz){
        for (MyObjects type : bag){
            if(clazz.isInstance(type)){
                return clazz.cast(type);
            }

        }
        return null;
    }

    public boolean take(MyObjects p){
        if(p!=null){
        if(p.inBag(this)){
            power = power - p.getWeight();
            bag.add(p);
            System.out.printf(this.getClass().getSimpleName() + " взял %s в сумку\n", p.getClass().getSimpleName());

            return true;
        }}
        return false;
    }
    public void put(MyObjects p){
        if(p!=null){
        bag.remove(p);
        power = power + p.getWeight();
        System.out.println(this + "Выложил объект из сумки");
        p.goToAnimal();}
    }
    public Corpses die(){
        try {


        if(super.getReadyDie()){
         return new Corpses(this, super.getWorld());
        }
        return  null;}
        catch (OutOfBoardException e){
            System.out.println("Попытка поставить труп на место животного которое находится вне карты"+e.getMessage());
            return null;
        }
    }
    public boolean haveUnBag(MyObjects p){
      //  System.out.println(this + "проверяет есть ли объект в портфеле");
        return bag.contains(p);

    }
    public void setSpeed(int a){speed = speed+a;}
    public void carryBag(){
        Iterator<MyObjects> iterator = bag.iterator();
        while(iterator.hasNext()){
            MyObjects myObj = iterator.next();
            myObj.goToAnimal();
        }
    }
}
