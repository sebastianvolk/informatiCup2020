package de.nordakademie.informaticup.pandemicfighter.gameengine.factories;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.ValueUtility;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

class PathogenFactory {
    private static final String KEY_NAME = "name";
    private static final String KEY_INFECTIVITY = "infectivity";
    private static final String KEY_MOBILITY = "mobility";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_LETHALITY= "lethality";

    Pathogen createPathogen(JsonObject jsonPathogen){
        return new Pathogen(
                jsonPathogen.get(KEY_NAME).getAsString(),
                ValueUtility.getValueEqualToStringPathogen(jsonPathogen.get(KEY_INFECTIVITY).getAsString()),
                ValueUtility.getValueEqualToStringPathogen(jsonPathogen.get(KEY_MOBILITY).getAsString()),
                ValueUtility.getValueEqualToStringPathogen(jsonPathogen.get(KEY_DURATION).getAsString()),
                ValueUtility.getValueEqualToStringPathogen(jsonPathogen.get(KEY_LETHALITY).getAsString())
        );
    }
}
