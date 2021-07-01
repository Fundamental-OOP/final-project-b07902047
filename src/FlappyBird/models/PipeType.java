package FlappyBird.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PipeType
{
    GREEN, RED;

    private static final List<PipeType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));

    public static PipeType randomPipeType(Random rnd)  {
        return VALUES.get(rnd.nextInt(VALUES.size()));
    }
}
