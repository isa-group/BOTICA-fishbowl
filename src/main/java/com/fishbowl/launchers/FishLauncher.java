package com.fishbowl.launchers;

import java.util.Properties;
import java.util.Random;

import org.json.JSONObject;

import com.botica.launchers.AbstractLauncher;

public class FishLauncher extends AbstractLauncher {

    private Integer fishXPosition;
    private Integer fishYPosition;
    private String fishSilouette;
    
    private Random random = new Random();

    public FishLauncher(String keyToPublish, String orderToPublish, Properties botProperties) {
        super(keyToPublish, orderToPublish, botProperties);

        this.fishXPosition = Integer.valueOf(botProperties.getProperty("bot.xPosition"));
        this.fishYPosition = Integer.valueOf(botProperties.getProperty("bot.yPosition"));
        this.fishSilouette = botProperties.getProperty("bot.fishSilouette");
        
    }

    @Override
    protected void botAction() {

        int movement = random.nextInt(8);
        
        switch (movement) {
            case 0:
                if (fishXPosition > 0 && fishYPosition > 0) {
                    fishXPosition--;
                    fishYPosition--;
                }
                break;
            case 1:
                if (fishYPosition > 0) {
                    fishYPosition--;
                }
                break;
            case 2:
                if (fishXPosition < 9 && fishYPosition > 0) {
                    fishXPosition++;
                    fishYPosition--;
                }
                break;
            case 3:
                if (fishXPosition > 0) {
                    fishXPosition--;
                }
                break;
            case 4:
                if (fishXPosition < 9) {
                    fishXPosition++;
                }
                break;
            case 5:
                if (fishXPosition > 0 && fishYPosition < 9) {
                    fishXPosition--;
                    fishYPosition++;
                }
                break;
            case 6:
                if (fishYPosition < 9) {
                    fishYPosition++;
                }
                break;
            case 7:
                if (fishXPosition < 9 && fishYPosition < 9) {
                    fishXPosition++;
                    fishYPosition++;
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected JSONObject createMessage() {
        JSONObject message = new JSONObject();
        message.put("order", this.orderToPublish);
        message.put("fishSilouette", fishSilouette);
        message.put("fishXPosition", fishXPosition);
        message.put("fishYPosition", fishYPosition);
        return message;
    }
}
