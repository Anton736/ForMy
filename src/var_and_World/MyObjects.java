package var_and_World;
import var_and_World.live.*;

import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;


public abstract class MyObjects{
  private   World world;
  private   boolean isMakeMove = false;
    int posX;
    int posY;
    boolean posZ;
   private double  weight;
    boolean interaction;
 private    Colors color;
   private Animals animals;
    private boolean readyDie = false;
    protected MyObjects(int posX, int posY, boolean posZ, double weight, boolean interaction, Colors color, World p)throws OutOfBoardException{


            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            p.addObject(this);
        this.color = color;
        this.interaction = interaction;
        this.weight = weight;


        world = p;

    }
    protected MyObjects(int posX, int posY, boolean posZ, double weight, boolean interaction, World p)throws OutOfBoardException{


        this.color = Colors.NONE;
        this.interaction = interaction;
        this.weight = weight;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
            p.addObject(this);
        world = p;

    }
    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }
    public boolean getPosZ(){
        return this.posZ;
    }
    private void setPosZ(boolean b){posZ = b;}
    private void setPosX(int a){
        this.posX = posX + a;
    }
    private void setPosY(int a){
        this.posY = posY + a;
    }
    protected void setCoordination(int x, int y, boolean z){
        boolean z0 = this.posZ;
        try{
            readyDie=true;
            this.getWorld().deleteObject(this);//Проблема удаления
            readyDie=false;

            setPosX(x);
            setPosY(y);
            setPosZ(z);
        this.getWorld().addObject(this);}

        catch (OutOfBoardException e){
            readyDie = false;
            this.getWorld().deleteObject(this);

            setPosX(-x);
            setPosY(-y);
            setPosZ(z0);
            try {

            this.getWorld().addObject(this);
            System.out.println("Возвращаем объект на место"+e.getMessage() + "  в координаты");}
            catch (OutOfBoardException t){
                System.out.println("Что-то очень странное не получилось поставить объект в исходное положение проверь код в MyObject"+t.getMessage());

            }
        }
    }
    public double getWeight(){
        return this.posX;
    }
    public boolean getInteraction(){
        return this.interaction;
    }
    protected void setInteraction(boolean interaction){this.interaction = interaction;}
    public World getWorld(){return this.world;}
    public Colors getColor(){return this.color;}
    public World.Cell getSpace(){

        if(null!= this.getWorld().getCell(this.posX, this.posY)){
            return this.getWorld().getCell(this.posX, this.posY);
        }

        return null;
    }
    public boolean getReadyDie(){return  this.readyDie;}
    public void setReadyDie(){  this.readyDie = true;}
    public boolean isNearObject(int x, MyObjects myObjects){
        //System.out.println("Определяем близко объект или нет");
        return Math.pow(x, 2)>= Math.pow(myObjects.getPosX()-getPosX(), 2)+ Math.pow(myObjects.getPosY()-getPosY(), 2);
    }
    public boolean inBag(Animals animals){
        if (this.isNearObject(1, animals) & animals.getPower()>=weight& interaction){
            posX = animals.getPosX();
            posY = animals.getPosY();
            posZ = animals.getPosZ();
           this.animals = animals;
            return true;
        }
        return false;

    }
    public void goToAnimal(){
        if(animals!=null&&!animals.getReadyDie()&&animals.haveUnBag(this)){
        setCoordination(animals.getPosX()-posX,animals.getPosY()-posY,animals.getPosZ());}
        else if(animals!=null&&(animals.getReadyDie()||!animals.haveUnBag(this))){
            animals=null;
        }
    }

}