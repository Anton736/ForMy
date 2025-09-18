package var_and_World;

import var_and_World.*;
import var_and_World.live.*;
import var_and_World.myException.OutOfBoardException;
import java.util.logging.*;

public class Corpses extends MyObjects {
    private static final Logger LOGGER = Logger.getLogger(Corpses.class.getName());
    public Corpses(Animals p, World t)throws OutOfBoardException {
        super(p.getPosX(),p.getPosY(),false, p.getWeight(), false, p.getColor(), t);
        this.getWorld().deleteObject(p);
       LOGGER.log(Level.INFO,"Создаем труп");
    }
}