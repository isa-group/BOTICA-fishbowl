package com.fishbowl.launchers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;

import com.botica.launchers.AbstractLauncher;
import com.fishbowl.utils.FishTank;

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

        Integer fishTankVersion = Integer.parseInt(botProperties.getProperty("bot.tankInitialVersion"));
        String fishTankChars = botProperties.getProperty("bot.tankState");
        FishTank fishTank = new FishTank(fishTankChars);

        if (fishTank.getFishSilouette(fishXPosition, fishYPosition).equals("-")) {
            fishTank.updateFishPosition(fishXPosition, fishYPosition, fishSilouette);
            logger.info("{} fish position updated to ({},{})", fishSilouette, fishXPosition, fishYPosition);
            try (FileOutputStream fos = new FileOutputStream("src/main/resources/fishTank" + fishTankVersion++ + ".txt")) {
                fos.write(fishTank.getFishTank().getBytes());
            } catch (IOException e) {
                logger.error("Error writing the fish tank in the file", e);
            }
            botProperties.setProperty("bot.tankInitialVersion", String.valueOf(fishTankVersion++));
            botProperties.setProperty("bot.tankState", fishTank.getFishTank());
        }
    }

    @Override
    protected JSONObject createMessage() {
        JSONObject message = new JSONObject();
        return message;
    }
    
}
