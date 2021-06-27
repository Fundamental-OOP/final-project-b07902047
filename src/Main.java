import java.util.HashMap;

import java.util.Map;
public class Main {

    public static void main(String[] args) {
        Map<String, Integer> config = new HashMap<String, Integer>();
        config.put("ScreenX", 288);
        config.put("ScreenY", 512);
        config.put("BirdInitX", 40);
        config.put("BirdWidth", 34);
        config.put("BirdHeight", 24);
        config.put("BirdInitY", 244);
        config.put("MaxVelocity", 10);
        config.put("FlapVelocity", -10);
        config.put("PipeWidth", 52);
        config.put("MinPipeGapY", 50);
        config.put("MaxPipeGapY", 150);
        config.put("MinPipeGapX", 50);
        config.put("MaxPipeGapX", 150);
        config.put("ForwardSpeed", 4);

        EventManager eventManager = new EventManager();      
    }
}
