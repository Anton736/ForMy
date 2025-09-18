package var_and_World.shield;
import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.interfaces.MakeMoveMe;
import var_and_World.live.Animals;
import var_and_World.myException.OutOfBoardException;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;

public class Dam extends Protective {
    private int square;
    HashSet <River> water = new HashSet<>();
    private Dam(int posX, int posY, boolean posZ, int square, World world) throws OutOfBoardException {

        super(posX, posY, false, 1000,1000 , 200, Colors.NONE, world);
        this.square = square;
        //System.out.println("Создаем плотину");
    }
    public static Dam createDam(int posX, int posY, boolean posZ, int square, World world){
        try {
            return new Dam(posX,posY,posZ,square,world);
        } catch (OutOfBoardException e) {
            System.out.println("Объект плотины не может быть создан " + e.getMessage());
            return null;
        }
    }
    public void damWork(){
        int t = World.getS();
        int step = this.square/t>=1? square/t:0;
        if(water.size()<=5){
        for(int k =0; k<2*this.square; k+=t){
            for (int j = 0;j<2*this.square; j+=t){
                HashSet<River> b = new HashSet<>();
                 b = this.getWorld().getObjects(k-this.square+this.getPosX(), j-this.square+this.getPosY(),  this.getPosZ(), River.class);
                for(River i: b){
                    water.add(i);
                    i.restAgainst(this);
                }

            }
        }}
        else{
            Iterator<River> iterator = water.iterator();
            int i =0;
            while (iterator.hasNext()){
                River river = iterator.next();
                if ( i <3){
                    i++;
                river.setReadyDie();
                this.getWorld().deleteObject(river);
                iterator.remove();}
                try{
               Field field = river.getClass().getSuperclass().getDeclaredField("interaction");
               field.setAccessible(true);
               field.set(river,true);

                }
                catch (NoSuchFieldException | IllegalAccessException e){
                    System.out.println(e+" ошибка при установлении interaction");
                }
            }
        }
    }
    public int getSquare(){return square;}
}