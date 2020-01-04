package de.nordakademie.informaticup.pandemicfighter.factories;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.Pathogen;

public class PathogenFactory {

    private static final String KEY_NAME = "name";
    private static final String KEY_INFECTIVITY = "infectivity";
    private static final String KEY_MOBILITY = "mobility";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_LETHALITY= "lethality";


    Pathogen createPathogen(JsonObject jsonPathogen){

        Pathogen pathogen = new Pathogen(
                jsonPathogen.get(KEY_NAME).getAsString(),
                jsonPathogen.get(KEY_INFECTIVITY).getAsString(),
                jsonPathogen.get(KEY_MOBILITY).getAsString(),
                jsonPathogen.get(KEY_DURATION).getAsString(),
                jsonPathogen.get(KEY_LETHALITY).getAsString()

        );
        return pathogen;

    }


}
