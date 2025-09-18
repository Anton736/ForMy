package var_and_World.nature;
import var_and_World.*;
import var_and_World.enums.Languages;
import var_and_World.interfaces.MakeSounds;
import var_and_World.myException.OutOfBoardException;

import java.util.HashSet;

public  class Sounds extends Nature{
    private boolean speech;
    protected String voice = "Бабах";
   private Languages languages;
    private String names;
   protected int timeLive = 5;
   private HashSet<Sounds> childSounds = new HashSet<>();

    public Sounds(MyObjects p,    World world) throws OutOfBoardException {
        super(p.getPosX(), p.getPosY(), p.getPosZ(), 0, world);
        if(p instanceof MakeSounds){
        this.voice = ((MakeSounds)p).getVoice();
        this.speech = ((MakeSounds) p).getSpeech();}
        //System.out.println("Создаем звук, не несущий окраски");
        names = p.getClass().getSimpleName();

        this.spread();
    }

    public Sounds(MyObjects p, double spirituality,   World world)throws OutOfBoardException{
        super(p.getPosX(), p.getPosY(), p.getPosZ(), spirituality, world);
        if(p instanceof MakeSounds){
            this.voice = ((MakeSounds)p).getVoice();
            this.speech = ((MakeSounds) p).getSpeech();
            if(speech){
                languages = ((MakeSounds) p).getLanguage();
            }
        }

       // System.out.println("Создаем звук");
        names = p.getClass().getSimpleName();

        this.spread();
    }
    public Sounds(MyObjects p, double spirituality,   World world, Languages language)throws OutOfBoardException{
        super(p.getPosX(), p.getPosY(), p.getPosZ(), spirituality, world);
        if(p instanceof MakeSounds){
            this.voice = ((MakeSounds)p).getVoice();
            this.speech = ((MakeSounds) p).getSpeech();
            if(speech){
                this.languages = language;
            }
        }

        // System.out.println("Создаем звук");
        names = p.getClass().getSimpleName();

        this.spread();
    }
    private Sounds(Sounds p, int posX, int posY,double spirituality,   World world)throws OutOfBoardException{
        super(posX, posY, p.getPosZ(), spirituality, world);
            this.voice = p.getVoice();
            this.speech =  p.getSpeech();
            if(speech){
               this.languages = p.getLanguages();
            }


        // System.out.println("Создаем звук");
        names = p.getClass().getSimpleName();


    }


    public boolean getSpeech(){
        return this.speech;
    }
    public Languages getLanguages(){return languages;}
    public String getVoice(){
        return this.voice;
    }
    public void spread(){
        int posX = this.getPosX();
        int posY = this.getPosY();

        int s = World.getS();
        int[][] step = new int[][]{ {0,1,0,-1},
                {1,0,-1,0}};
        int direction = 0;
        int stepsTaken =0;
        int stepsNeeded = 1;
        if(timeLive==5){
        for (int i =1; i< 16; i++){
            posX = step[0][direction]*s + posX;
            posY = step[1][direction]*s + posY;
            stepsTaken++;
            if(stepsTaken==stepsNeeded){
                stepsTaken = 0;
                direction= (direction+1)%4;
                if(direction%2==0){
                    stepsNeeded++;

                }
            }
            try {

            if(posX>=0&posX<getWorld().getX()&&(posY>=0&posY<getWorld().getY())){
               Sounds p=new Sounds(this, posX, posY,spirituality, getWorld());
               childSounds.add(p);
               p.timeLive=4;
            }}
            catch (OutOfBoardException e){
                System.out.println("Ошибка в распространении звука такое не должно случаться");
            }

        }
        }
        else if(timeLive<=0){
            for (Sounds sound:childSounds ){
            sound.setReadyDie();
            sound.getWorld().deleteObject(sound);}

        }
        timeLive--;
    }

}