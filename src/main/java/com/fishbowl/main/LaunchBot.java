package com.fishbowl.main;

import es.us.isa.botica.configuration.MainConfiguration;
import es.us.isa.botica.launchers.AbstractLauncher;
import es.us.isa.botica.runners.BOTICALoader;
import es.us.isa.botica.utils.bot.BotHandler;

public class LaunchBot {

    private static String launchersPackage = "com.fishbowl.launchers";                           // The package where the launchers are located.

    public static void main(String[] args) {
        MainConfiguration configuration = new BOTICALoader().loadConfiguration();
        AbstractLauncher launcher = BotHandler.handleLauncherType(launchersPackage, configuration);

        if (launcher == null){
            throw new NullPointerException("Bot launcher does not exist");
        }

        launcher.setLauncherPackage(launchersPackage);
        launcher.launchBot();
    }
}
