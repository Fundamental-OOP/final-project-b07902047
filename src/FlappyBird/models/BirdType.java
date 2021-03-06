package FlappyBird.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BirdType {
    BLUE, RED, YELLOW;

    public static String[] getNames() {
        return new String[]{"blue", "red", "yellow"};
    }

    private static final List<BirdType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    public static BirdType randomBirdType(Random rnd) {
        return VALUES.get(rnd.nextInt(VALUES.size()));
    }
}
