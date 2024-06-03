package com.fishbowl.main;

import es.us.isa.botica.runners.ShutdownLoader;
import es.us.isa.botica.utils.shutdown.ShutdownUtils;
import java.io.File;

public class LaunchShutdown {

  public static void main(String[] args) {
    ShutdownUtils.shutdown(new ShutdownLoader(new File("config.yml")));
  }
}
