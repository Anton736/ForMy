package var_and_World.live;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;
import var_and_World.nature.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Peoples extends Animals {
    private HashSet<Languages> language = new HashSet<>();

    private  HashSet<QualificationType> qualification;
    private TypeEnemy typeEnemy;

   private double sense;
   private final String name;
    public Peoples(String name,int posX, int posY, TypeEnemy typeEnemy,World p,  Languages languages,QualificationType... qualification)throws OutOfBoardException{
        super(40, 10, 2, false, posX, posY, false, 2, true, p);
        if(qualification!=null) this.qualification = new HashSet<>(Arrays.asList(qualification));
        else this.qualification = new HashSet<>();

        this.typeEnemy = typeEnemy;
        this.isCanSaddle = false;
        this.name = name;
        language.add(languages);
    }
    public Peoples(int posX, int posY, TypeEnemy typeEnemy,World p,  Languages languages,QualificationType... qualification)throws OutOfBoardException{
        super(100, 100, 2, false, posX, posY, false, 2, true, p);
        if(qualification!=null) this.qualification = new HashSet<>(Arrays.asList(qualification));
        else this.qualification = new HashSet<>();

        this.typeEnemy = typeEnemy;
        this.isCanSaddle = false;
        this.name = "NoName";
        language.add(languages);
    }
    public String getName(){return name;}
    public void addSpirituality(double spirituality){
        this.spirituality = Math.min(this.spirituality + spirituality, 3);
        this.setHp(spirituality*this.getHp());
        this.power = spirituality*power;
        this.speed = (int)(spirituality * this.speed);


    }
    public void addLanguage(Languages... languages){
        language.addAll(Arrays.asList(languages));
    }
    public void feel(){

        Set <Nature> natures= getWorld().getObjects(getPosX(), getPosY(),Nature.class).keySet();
        for (Nature nature : natures){
            if(nature.getClass()== Sounds.class){
                if(((Sounds)nature).getLanguages()==null){
                    addSpirituality(nature.getSpirituality());
                    System.out.println("Слышит какие-то звуки");
                }
                else if(language.contains(((Sounds)nature).getLanguages())){
                    addSpirituality(nature.getSpirituality());
                    System.out.println("Слышит чью-то речь");
                }
                else System.out.println("Не понимает чужую речь");
            }else{
        addSpirituality(nature.getSpirituality());

            }
        }
    }
    public boolean saddle(Animals animal){
        if(animal.manual & this.getPosX()==animal.getPosX() & this.getPosY() == animal.getPosY() & this.getPosZ() == animal.getPosZ()& animal.getIsCanSaddle() == true){

            power = animal.getPower()+power/2;
            speed = animal.speed + speed/2;
            animal.gotoSomeone(this);
            System.out.println(this.getClass().getSimpleName()+" оседлал лошадь");
            animal.isCanSaddle = false;
            return true;
        }
        return false;
    }
    protected static QualificationType[] combined(QualificationType[] qualifications, QualificationType qualification){
        QualificationType[] comb= Arrays.copyOf(qualifications, qualifications.length+1);
        comb[qualifications.length] = qualification;

        return comb;
    }
    public TypeEnemy getTypeEnemy(){
        return this.typeEnemy;

    }
    public HashSet<QualificationType> getQualification(){return qualification;}
    public boolean checkQualification(QualificationType qualificationType){
       // System.out.println("проверяем наличие образования");
        return qualification.contains(qualificationType);}
    public void setLanguage(Languages... args){
        language = new HashSet<>(Arrays.asList(args));
        //System.out.println("устанавливаем язык");
    }
    @Override
    public boolean makeMoveMe(int x, int y, boolean z, int speed){
        if(getWorld().getObjects(this.getPosX(), this.getPosY(), this.getPosZ(), River.class).isEmpty()) return super.makeMoveMe(x,y,z,speed);
        else return super.makeMoveMe(x,y,z,speed-1);
    }
    @Override
    public Sounds makeSound() {

        try {

        return new Sounds(this, sense, getWorld());}
        catch (OutOfBoardException e){
            System.out.println("Звук был выпущен вне карты это по меньшей мере странно" + e.getMessage());
            return null;
        }



    }
    public Sounds makeSound(Languages languages) {
        if(language.contains(languages)){
            try {

                return new Sounds(this, sense, getWorld(), languages);}
            catch (OutOfBoardException e){
                System.out.println("Звук был выпущен вне карты это по меньшей мере странно" + e.getMessage());
                return null;
            }}

        System.out.println("Не умею говорить на этом языке");
        return null;
    }
    @Override
    public  Languages getLanguage(){
        for (Languages languages:language){
        return languages;
        }
        return null;
    }
    public Sounds makeVoice(String voice, double sense){
        this.voice = voice;
        this.sense = sense;
        return makeSound();
    }
    public Sounds makeVoice(String voice, double sense, Languages language){
        this.voice = voice;
        this.sense = sense;
        return makeSound(language);
    }

    public <X extends MyObjects> X foundSomeThing(int startX, int startY, Class<X> targetClass){
        int x0 = startX;
        int y0 = startY;
        int s = World.getS();

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int direction = 0;
        int stepsTaken = 0;
        int stepsNeeded = 1;
        int cellsChecked = 0;

        while (cellsChecked < this.getWorld().getX() * this.getWorld().getY()) {
            if (x0 >= 0 && x0 < this.getWorld().getX() && y0 >= 0 && y0 < this.getWorld().getY()) {
                cellsChecked++;
                for (X obj : this.getWorld().getObjects(x0, y0, targetClass).keySet()) {
                 //   System.out.println("Объект найден: " + obj);
                    return obj;
                }
            }
            x0 += dx[direction] * s;
            y0 += dy[direction] * s;
            stepsTaken++;


            if (stepsTaken == stepsNeeded) {
                stepsTaken = 0;
                direction = (direction + 1) % 4;
                if (direction % 2 == 0) {
                    stepsNeeded++;

                }
            }
        }


        return null;


    }
}