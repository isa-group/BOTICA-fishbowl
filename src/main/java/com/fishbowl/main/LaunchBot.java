package com.fishbowl.main;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import es.us.isa.botica.launchers.AbstractLauncher;
import es.us.isa.botica.runners.BOTICALoader;
import es.us.isa.botica.utils.bot.BotHandler;

public class LaunchBot {

    private static String launchersPackage = "com.fishbowl.launchers";                           // The package where the launchers are located.

    public static void main(String[] args) {
        BOTICALoader loader = new BOTICALoader();

        String botType = loader.getBotType();
        String keyToPublish = loader.getKeyToPublish();
        String orderToPublish = loader.getOrderToPublish();
        AbstractLauncher launcher = BotHandler.handleLauncherType(botType, keyToPublish, orderToPublish, null, launchersPackage);

        if (launcher == null){
            throw new NullPointerException("Bot launcher does not exist");
        }

        launcher.setLauncherPackage(launchersPackage);

        try {
            String autonomyType = loader.getAutonomyType();
            if (autonomyType.equals("reactive")){
                loader.connectBotToRabbit(launcher);
            }else if (autonomyType.equals("proactive")) {
                launcher.checkBrokerConnection();
                Integer initialDelay = loader.getInitialDelay();
                Integer period = loader.getPeriod();
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                Runnable runnable = () -> loader.connectBotToRabbit(launcher);
                scheduler.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
