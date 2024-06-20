package es.us.isa.botica.fishbowl.manager;

import es.us.isa.botica.bot.BotApplicationRunner;

public class ManagerBotLauncher {
  public static void main(String[] args) {
    BotApplicationRunner.run(new ManagerBot(), args);
  }
}
