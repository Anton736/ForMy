package var_and_World.interfaces;

import var_and_World.enums.*;
import var_and_World.nature.*;
public interface MakeSounds {

    Sounds makeSound();
    String getVoice();
    boolean getSpeech();
    Languages getLanguage();
}
