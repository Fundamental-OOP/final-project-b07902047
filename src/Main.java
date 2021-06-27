import java.util.HashMap;

import java.util.Map;
public class Main {

    public static void main(String[] args) {
        Map<String, Integer> config = new HashMap<String, Integer>();
        config.put("ScreenX", 288);
        config.put("ScreenY", 512);
        config.put("BirdInitX", 40);
        config.put("BirdInitY", 244);
        config.put("MaxVelocity", 10);
        config.put("FlapVelocity", -10);
        config.put("MinColumnGapY", 50);
        config.put("MaxColumnGapY", 150);
        config.put("MinColumnGapX", 50);
        config.put("MaxColumnGapX", 150);
        config.put("ForwardSpeed", 4);

        EventManager eventManager = new EventManager();      
    }
}
