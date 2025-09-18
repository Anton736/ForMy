package var_and_World;


import var_and_World.cartridges.*;
import var_and_World.controlObject.*;
import var_and_World.gun.*;
import var_and_World.live.*;
import var_and_World.live.typePeples.*;
import var_and_World.enums.*;

import var_and_World.myException.*;
import var_and_World.nature.*;
import var_and_World.shield.*;

import java.util.HashSet;
import java.util.Iterator;

public class Var {
   private Create skyClouds;
  private   World world;
    private Peoples andre = null;
    private Peoples napoleon = null;
  private int countFrance; //Количество французов
  private int countRussian; //Количество русских
 private int countClouds; //Количество облаков
 private int countArmor; //Количество брони
  private int countHorses; //Количество лошадей
 private int countInfantry;
    private int countCavalry;
   private int countArtillery;
  private int countCannon; //Количество пушек
  private   int countSword; //Количество мечей
  private   int countCannonball;
  private   int countRiver;
  private   HashSet <ControlPerson> controlMilitaries = new HashSet<>();

 private    HashSet <Armor> armors = new HashSet<>();
 private    HashSet <ControlRiver> rivers = new HashSet<>();
 private    HashSet <Horses> horses = new HashSet<>();
 private    HashSet <Cannon> cannons = new HashSet<>();
private     HashSet <Cannonball> cannonballs  = new HashSet<>();
 private    HashSet <Sword> swords = new HashSet<>();
    public Var(int x, int y ){
        this.countClouds = 10;
       this.world = new World(x, y);

        this.countFrance = (int)(Math.random()*15);
        this.countRussian = (int)(Math.random()*15);


        this.countArmor = countFrance+countRussian;
        this.countHorses = (int)(0.4*(countArmor)) + 2;
        this.countInfantry = (int)(0.4*(countArmor));
        this.countCavalry = (int)(0.4*(countArmor));
        this.countArtillery = countArmor - countCavalry -countInfantry;
        this.countCannon = (int)(0.3*countArtillery);
        this.countSword = countCavalry+countInfantry;
        this.countCannonball = 6 * countCannon;
        this.countRiver = 1;
        try{
        createArmor();
        createCannon();
        createCannonBall();
        createHorse();
        createCloudsAndSky();
        createSword();
        createRussianPeople();
        createFrenchPeople();
        createControlRiver();}
        catch (OutOfBoardException e){
            System.out.println("Ошибка которой быть не должно в классе Var c Create-рами " + e.getMessage());
        }
    }
    public void createRussianPeople() throws OutOfBoardException{


        System.out.println("Создаем русские войска");
        for(int i = 0; i < countRussian; i++){
            if((float)i/countRussian<0.4){

                controlMilitaries.add(new ControlPerson( new Infantry(0,0,TypeEnemy.RUSSIAN, world)));
            }
            else if((float)i/countRussian>=0.4&(float)i/countRussian<0.7){
               controlMilitaries.add(new ControlPerson(new  Cavalry(0,0,TypeEnemy.RUSSIAN,world)));
            }
            else {
              controlMilitaries.add(new ControlPerson(new  Artillery(0,0,world,TypeEnemy.RUSSIAN)));
            }

        }

    }
    public void createFrenchPeople() throws OutOfBoardException{
        System.out.println("Создаем французские войска");
        for(int i = 0; i < countFrance; i++){
            if((float)i/countFrance<0.4){
                controlMilitaries.add(new ControlPerson(new Infantry(0,0,TypeEnemy.FRENCH, world)));
            }
            else if((float)i/countFrance>=0.4&(float)i/countFrance<0.7){
               controlMilitaries.add(new ControlPerson(new Cavalry(0,0,TypeEnemy.FRENCH,world)));
            }
            else {
               controlMilitaries.add( new ControlPerson(new  Artillery(0,0,world,TypeEnemy.FRENCH)));
            }

        }

    }
    public void createCloudsAndSky(){

        double directionTG =(Math.random()*2-1)/Math.random();
        skyClouds =  Create.CreateSky(countClouds,2,directionTG,world);

    }
    public void createArmor()throws OutOfBoardException{
        System.out.println("Создаем бронежилеты");
        for (int i = 0; i < countArmor; i++){
            int posX = (int)(Math.random()*world.getX());
            int posY = (int)(Math.random()*world.getY());
            armors.add(new Armor(posX,posY,false,world));
        }
    }
    public void createHorse()throws OutOfBoardException{
        System.out.println("Создаем лошадей");
        for (int i = 0; i < countHorses; i++){
            int posX = (int)(Math.random()*world.getX());
            int posY = (int)(Math.random()*world.getY());
            horses.add(new Horses(posX,posY,world));
        }
    }
    public void createCannon()throws OutOfBoardException{
        System.out.println("Создаем Пушки");
        for (int i = 0; i < countCannon; i++){
            int posX = (int)(Math.random()*world.getX());
            int posY = (int)(Math.random()*world.getY());
            cannons.add(new Cannon(posX,posY,world));
        }
    }
    public void createSword()throws OutOfBoardException{
        System.out.println("Создаем мечи");
        for (int i = 0; i < countSword; i++){
            int posX = (int)(Math.random()*world.getX());
            int posY = (int)(Math.random()*world.getY());
            swords.add(new Sword(posX,posY,world));
        }
    }
    public void createCannonBall()throws OutOfBoardException{
        System.out.println("Создаем ядра");
        for (int i = 0; i < countCannonball; i++){
            int posX = (int)(Math.random()*world.getX());
            int posY = (int)(Math.random()*world.getY());
            cannonballs.add(new Cannonball(posX,posY,world));
        }
    }
    public void createControlRiver(){
        System.out.println("Создаем реки");
        for (int i = 0; i < countRiver; i++){
            int posX = (int)(Math.random()*world.getX());
            int posY = (int)(Math.random()*world.getY());
            int endPosX = (int)(Math.random()*world.getX());
            int endPosY = (int)(Math.random()*world.getY());
            rivers.add(new ControlRiver(posX,posY,endPosX,endPosY,true,world));
        }
    }
    public void varGO(){
        boolean isVar=true;
        byte russianIsWin = 0;
        boolean isUnInitialized = true;
        boolean isGo=true;

        while (isGo){
            if(isVar){
                Iterator<ControlPerson> iterator = controlMilitaries.iterator();
                if (controlMilitaries.isEmpty()){
                    isVar=false;
                }
                else {
            while(iterator.hasNext()){
                ControlPerson controlMilitary = iterator.next();

                if(controlMilitary.isLive()){
               isVar = controlMilitary.go();
                }
                else {
                    iterator.remove();
                }

            }}

            for (Cannonball cannonball: cannonballs){
                cannonball.makeMoveMe();
            }}

            else{
                if(isUnInitialized){
                    if (controlMilitaries.isEmpty()){

                        isUnInitialized=false;

                    }
                for(ControlPerson controlMilitary: controlMilitaries){

                    russianIsWin = controlMilitary.isRussian()?(byte)1:-1;
                    isUnInitialized=false;
                    break;
                }
                }
                else{ isGo= endVar(russianIsWin);}

            }
            for (ControlRiver river: rivers){
                river.controlRiverWork();
            }
            skyClouds.makeMoveClouds();
        }

    }


   public boolean endVar(byte isWin){

       if(andre==null||napoleon==null){
           System.out.println("Заключительная часть");
           try {
           andre = new Peoples("Андрей Болконский", 2, 3, TypeEnemy.RUSSIAN,world, Languages.Russian,QualificationType.CAVALRY, QualificationType.DIPLOMAT);
           napoleon = new Peoples("Наполеон", 0, 0, TypeEnemy.FRENCH,world, Languages.French,QualificationType.CAVALRY, QualificationType.COMMANDER);
           andre.addLanguage(Languages.French);}
       catch(OutOfBoardException e){

           System.out.println("Ошибка координаты Наполеона или Андрея были заданы не верно " + e.getMessage());
           return false;
       }}

       if(isWin==-1){

           if(!napoleon.gotoSomeone(andre)){
          Sounds sound= napoleon.makeVoice("Славная была битва мы сражались смелее яростнее и отважнее русских", -1.5);

              System.out.println(  sound.getVoice());
           andre.feel();
           return false;
           }


       }
       else if(isWin==1){


           if (!andre.gotoSomeone(napoleon)){
               Sounds sound= andre.makeVoice("Наполеон сдавайся ты проиграл", -1.5, Languages.French);
               System.out.println(  sound.getVoice());
           napoleon.feel();
           return false;}



       }
       else if(isWin==0){
           System.out.println("Ничья никто не выйграл все умерли");
           return false;
       }
       return true;


   }
}