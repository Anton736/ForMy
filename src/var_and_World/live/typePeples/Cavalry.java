package var_and_World.live.typePeples;
import var_and_World.*;
import var_and_World.enums.*;
import var_and_World.gun.Cannon;
import var_and_World.live.Horses;
import var_and_World.myException.OutOfBoardException;

public class Cavalry extends Military{
    public Cavalry(int posX, int posY, TypeEnemy typeEnemy, World p, QualificationType... qualification)throws OutOfBoardException{
        super(posX, posY,typeEnemy, p, combined(qualification, QualificationType.CAVALRY));

    }
    public Cavalry(int posX, int posY, TypeEnemy typeEnemy, World p)throws OutOfBoardException {
        super(posX, posY,typeEnemy, p,  QualificationType.CAVALRY);

    }




}