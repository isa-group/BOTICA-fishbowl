package com.fishbowl.launchers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;

import com.botica.launchers.AbstractLauncher;
import com.fishbowl.utils.Fishbowl;

public class FishManagerLauncher extends AbstractLauncher {

    private static final String FISH_X_POSITION = "fishXPosition";
    private static final String FISH_Y_POSITION = "fishYPosition";
    private static final String FISH_SILOUETTE = "fishSilouette";

    public FishManagerLauncher(String keyToPublish, String orderToPublish, Properties botProperties) {
        super(keyToPublish, orderToPublish, botProperties);
    }

    @Override
    protected void botAction() {
        String fishSilouette = messageData.getString(FISH_SILOUETTE);
        Integer fishXPosition = messageData.getInt(FISH_X_POSITION);
        Integer fishYPosition = messageData.getInt(FISH_Y_POSITION);

        Integer fishbowlVersion = Integer.parseInt(botProperties.getProperty("bot.fishbowlVersion"));
        String fishbowlChars = botProperties.getProperty("bot.fishbowlState");
        Fishbowl fishbowl = new Fishbowl(fishbowlChars);

        if (fishbowl.getFishSilouette(fishXPosition, fishYPosition).equals("-")) {
            fishbowl.updateFishPosition(fishXPosition, fishYPosition, fishSilouette);
            logger.info("{} fish position updated to ({},{})", fishSilouette, fishXPosition, fishYPosition);
            try (FileOutputStream fos = new FileOutputStream("src/main/resources/fishbowl/v" + fishbowlVersion++ + ".txt")) {
                fos.write(fishbowl.getFishbowl().getBytes());
            } catch (IOException e) {
                logger.error("Error writing the fishbowl in the file", e);
            }
            botProperties.setProperty("bot.fishbowlVersion", String.valueOf(fishbowlVersion++));
            botProperties.setProperty("bot.fishbowlState", fishbowl.getFishbowl());
        }
    }

    @Override
    protected JSONObject createMessage() {
        JSONObject message = new JSONObject();
        return message;
    }

    @Override
    protected boolean shutdownCondition() {
        return false;
    }
    
}
