package com.fishbowl.launchers;

import java.util.Random;

import es.us.isa.botica.configuration.MainConfiguration;
import org.json.JSONObject;

import es.us.isa.botica.launchers.AbstractLauncher;

public class FishLauncher extends AbstractLauncher {

    private static Integer fishXPosition = Integer.parseInt(System.getenv("FISH_POSITION_X"));
    private static Integer fishYPosition = Integer.parseInt(System.getenv("FISH_POSITION_Y"));

    private final Random random = new Random();

    public FishLauncher(MainConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected void botAction() {
        logger.info("Fish position: ({},{})", fishXPosition, fishYPosition);

        int movement = random.nextInt(8);
        
        switch (movement) {
            case 0:
                if (fishXPosition > 0 && fishYPosition > 0) {
                    fishXPosition = fishXPosition - 1;
                    fishYPosition = fishYPosition - 1;
                }
                break;
            case 1:
                if (fishYPosition > 0) {
                    fishYPosition = fishYPosition - 1;
                }
                break;
            case 2:
                if (fishXPosition < 9 && fishYPosition > 0) {
                    fishXPosition = fishXPosition + 1;
                    fishYPosition = fishYPosition - 1;
                }
                break;
            case 3:
                if (fishXPosition > 0) {
                    fishXPosition = fishXPosition - 1;
                }
                break;
            case 4:
                if (fishXPosition < 9) {
                    fishXPosition = fishXPosition + 1;
                }
                break;
            case 5:
                if (fishXPosition > 0 && fishYPosition < 9) {
                    fishXPosition = fishXPosition - 1;
                    fishYPosition = fishYPosition + 1;
                }
                break;
            case 6:
                if (fishYPosition < 9) {
                    fishYPosition = fishYPosition + 1;
                }
                break;
            case 7:
                if (fishXPosition < 9 && fishYPosition < 9) {
                    fishXPosition = fishXPosition + 1;
                    fishYPosition = fishYPosition + 1;
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected JSONObject createMessage() {
        JSONObject message = new JSONObject();
        message.put("order", botTypeConfiguration.getPublishConfiguration().getOrder());
        message.put("fishSilouette", System.getenv("FISH_SILHOUETTE"));
        message.put("fishXPosition", fishXPosition);
        message.put("fishYPosition", fishYPosition);
        return message;
    }

    @Override
    protected boolean shutdownCondition() {
        return true;
    }
}
