package var_and_World.nature;

import var_and_World.World;
import var_and_World.enums.Colors;
import var_and_World.myException.OutOfBoardException;

import java.util.HashSet;

public class Create {
   private static boolean isCreate = false;
   private HashSet<Clouds> clouds = new HashSet<>();
    private Create(int clouds, int speed,double directionTG ,World world){

            System.out.println("Создаем небо и облака");
            for (World.Cell[] cell: world.getGameBoard()){
                for (World.Cell cell1: cell){
                    try {


                    new Sky(cell1.getX(), cell1.getY(), world);}
                    catch (OutOfBoardException e){
                        System.out.println("Ошибка в размещении неба таково быть не должно "+ e.getMessage());
                    }
                }
            }
            for (int i=0; i<clouds; i++){
                int posX = (int)(Math.random()*world.getX());
                int posY = (int)(Math.random()*world.getY());
                try {


                this.clouds.add(new Clouds(posX, posY,  Colors.WHITE, speed, directionTG, world));}
                catch (OutOfBoardException e){
                    System.out.println("Попытка добавить не удавшееся облако в HashSet в классе Create" + e.getMessage());
                }
            }




    }
    public static Create CreateSky(int clouds, int speed,double directionTG ,World world){
        if(!isCreate){
            isCreate = true;
           return new  Create(clouds, speed, directionTG, world);


        }
        return null;
    }

    //public HashSet<Clouds> getClouds(){return clouds;}
    public void makeMoveClouds(){
        HashSet <Clouds> newClods = new HashSet<>();
        for (Clouds cloud: clouds){

            newClods.add( cloud.makeMoveMe());
        }
        clouds=newClods;
    }
}