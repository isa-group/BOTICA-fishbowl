package com.fishbowl.launchers;

import java.util.Properties;

import org.json.JSONObject;

import com.botica.launchers.AbstractLauncher;
import com.fishbowl.utils.FishTank;

public class FishManagerLauncher extends AbstractLauncher {

    private static final String FISH_X_POSITION = "fishXPosition";
    private static final String FISH_Y_POSITION = "fishYPosition";
    private static final String FISH_SILOUETTE = "fishSilouette";

    private FishTank fishTank;

    public FishManagerLauncher(String keyToPublish, String orderToPublish, Properties botProperties) {
        super(keyToPublish, orderToPublish, botProperties);
        this.fishTank = new FishTank();
    }

    @Override
    protected void botAction() {
        String fishSilouette = messageData.getString(FISH_SILOUETTE);
        Integer fishXPosition = messageData.getInt(FISH_X_POSITION);
        Integer fishYPosition = messageData.getInt(FISH_Y_POSITION);

        if (fishTank.getFishSilouette(fishXPosition, fishYPosition).equals("-")) {
            fishTank.updateFishPosition(fishXPosition, fishYPosition, fishSilouette);
            System.out.println(fishTank.getFishTank());
        }
    }

    @Override
    protected JSONObject createMessage() {
        JSONObject message = new JSONObject();
        return message;
    }
    
}
