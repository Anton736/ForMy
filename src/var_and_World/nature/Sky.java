package var_and_World.nature;

import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.myException.OutOfBoardException;

public class Sky extends Nature{

    protected Sky(int posX, int posY, World world)throws OutOfBoardException {
        super(posX, posY, true, 10, Colors.BLUE, world);
       // System.out.println("Создаем небо");
    }


}