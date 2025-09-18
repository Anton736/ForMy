package var_and_World;
public  class ObjAndBoolean{
    private MyObjects myObject;
    private boolean bool;
    public ObjAndBoolean(MyObjects myObject, boolean bool){
        this.myObject = myObject;
        this.bool = bool;
    }

    public MyObjects getMyObject() {
        return myObject;
    }
    public boolean getBool(){return bool;}
}