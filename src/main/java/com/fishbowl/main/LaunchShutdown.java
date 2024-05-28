package com.fishbowl.main;

import java.io.File;
import java.util.List;

import es.us.isa.botica.runners.ShutdownLoader;
import es.us.isa.botica.utils.shutdown.ShutdownUtils;

public class LaunchShutdown {

  public static void main(String[] args) {
    ShutdownUtils.shutdown(new ShutdownLoader(new File("config.yml")));
  }
}
