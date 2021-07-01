package FlappyBird.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum BackgroundTheme {
    DAY, NIGHT;

    public static String[] getNames() {
        return new String[]{"background-day", "background-night"};
    }

    private static final List<BackgroundTheme> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    public static BackgroundTheme randomBackgroundTheme(Random rnd)  {
        return VALUES.get(rnd.nextInt(VALUES.size()));
    }
}
