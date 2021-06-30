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
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static PipeType randomPipeType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
