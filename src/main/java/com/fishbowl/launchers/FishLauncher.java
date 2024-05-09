package com.fishbowl.launchers;

import java.util.Properties;
import java.util.Random;

import org.json.JSONObject;

import es.us.lsi.botica.launchers.AbstractLauncher;

public class FishLauncher extends AbstractLauncher {
    
    private Random random = new Random();

    public FishLauncher(String keyToPublish, String orderToPublish, Properties botProperties) {
        super(keyToPublish, orderToPublish, botProperties);
    }

    @Override
    protected void botAction() {

        Properties botProperties = getBotProperties();
        Integer fishXPosition = Integer.parseInt(botProperties.getProperty("bot.xPosition"));
        Integer fishYPosition = Integer.parseInt(botProperties.getProperty("bot.yPosition"));

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
        botProperties.setProperty("bot.xPosition", fishXPosition.toString());
        botProperties.setProperty("bot.yPosition", fishYPosition.toString());
        setBotProperties(botProperties);
    }

    @Override
    protected JSONObject createMessage() {

        Properties botProperties = getBotProperties();
        String fishSilouette = botProperties.getProperty("bot.fishSilouette");
        Integer fishXPosition = Integer.parseInt(botProperties.getProperty("bot.xPosition"));
        Integer fishYPosition = Integer.parseInt(botProperties.getProperty("bot.yPosition"));
        
        JSONObject message = new JSONObject();
        message.put("order", this.orderToPublish);
        message.put("fishSilouette", fishSilouette);
        message.put("fishXPosition", fishXPosition);
        message.put("fishYPosition", fishYPosition);
        return message;
    }

    @Override
    protected boolean shutdownCondition() {
        return true;
    }
}
