package com.fishbowl.main;

import es.us.isa.botica.utils.configuration.CreateConfiguration;

import java.io.File;

public class ConfigurationSetup {

    private static final String CONFIG_PATH = "config.yml";

    public static void main(String[] args) {
        String configPath = CONFIG_PATH;
        if (args.length == 1) {
            configPath = args[0];
        }

        CreateConfiguration.createConfiguration(new File(configPath));
    }
}
