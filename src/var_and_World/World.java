package var_and_World;

import var_and_World.myException.OutOfBoardException;
import var_and_World.nature.*;
import java.security.cert.Extension;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class World {
    private static int s;
   private static Cell[][] gameBoard;
   private final int x;
  private   final int y;


    public class Cell {
        private int x;
        private int y;
        HashMap<MyObjects, Boolean> cellSet;
        private Cell(int s, int x, int y){
             cellSet = new HashMap<>();
            this.x =x;// координаты массива
            this.y = y;
        }
        public <T extends MyObjects> HashMap<T, Boolean>  getObjects(Class<T> clazz){
            HashMap<T, Boolean> someMap = new HashMap<>();
            for (Map.Entry<MyObjects, Boolean> entry : this.cellSet.entrySet()){
                if(clazz.isInstance(entry.getKey())){
                    someMap.put(clazz.cast(entry.getKey()),entry.getValue());
                }
            }
          //  System.out.println("Возвращаем объекты определенного типа из ячейки");
            return someMap;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


/*    public World(int x, int y, int s){Разделение на квадраты мне кажется так будет оперативней, но систему надо додумать к сожалению не успел
        gameBoard = new Cell[x/s][y/s];
        this.s = s;
        for (int i = 1; i<=x; i+=s){
            for (int j = 1; j <= y; y +=s){
                gameBoard[i][j]=new Cell(s, x, y);
            }
        }
    }*/
    public World(int x, int y){
        gameBoard = new Cell[x][y];
        s = 1;
        this.x=x-1;
        this.y = y-1;
        for (int i = 0; i<x; i+=1){
            for (int j = 0; j < y; j +=1){
                gameBoard[i][j] = new Cell(1, i, j);
            }
        }
        System.out.println("Создаем игровое поле");

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell[][] getGameBoard(){return gameBoard;}
    public static int getS(){return s;}
    public Cell getCell(int x, int y) {
        if((x/s)>=0 && (x/s)<gameBoard.length && (y/s)>=0 && (y/s)<gameBoard[x/s].length){
        return gameBoard[x/s][y/s];}
        return null;
    }
    public <T extends MyObjects> HashMap<T, Boolean> getObjects(int x, int y, Class<T> clazz){
        HashMap<T, Boolean> someMap = new HashMap<>();


        Cell cell = getCell(x,y);
        if(cell!=null){
        for (Map.Entry<MyObjects, Boolean> entry : cell.cellSet.entrySet()){
            if(clazz.isInstance(entry.getKey())){
                someMap.put(clazz.cast(entry.getKey()),entry.getValue());
            }
        }}

        return someMap;
    }
    public  <T extends MyObjects> HashSet<T> getObjects(int x, int y, boolean z, Class<T> clazz){
        HashMap<T, Boolean> someMap = new HashMap<>();
        Cell cell = getCell(x,y);
        if(cell!=null){
        for (Map.Entry<MyObjects, Boolean> entry : cell.cellSet.entrySet()){
            if(clazz.isInstance(entry.getKey())& z == entry.getValue()){
                someMap.put(clazz.cast(entry.getKey()),entry.getValue());
            }
        }}
       // System.out.println("Возвращаем объекты определенного типа и на конкретной высоте из ячейки из ячейки");
        return  new HashSet<>(someMap.keySet());
    }

     void addObject (MyObjects p)throws OutOfBoardException{
        int x1 = (p.posX)/s;
        int y1 = (p.posY) / s;

        if(x1>=0&&x1<gameBoard.length&&y1>=0&&y1<gameBoard[x1].length){

            gameBoard[x1][y1].cellSet.put(p, p.posZ);
       // System.out.println("Добавляем объект");
            }
        else{
         throw new OutOfBoardException("Попытка поставить объект " + p.getClass().getSimpleName()+ " в точку вне карты, с координатами " + "("+x1+", "+y1+")");

        }
    }
    public void deleteObject(MyObjects myObj){
        if(myObj.getReadyDie()){
            getCell(myObj.getPosX(),myObj.getPosY()).cellSet.remove(myObj);
            //System.out.println("Удаляем объект");
        }
    }


}