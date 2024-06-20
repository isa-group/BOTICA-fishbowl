package es.us.isa.botica.fishbowl.manager;

import es.us.isa.botica.bot.AbstractBotApplication;
import es.us.isa.botica.fishbowl.Fishbowl;
import es.us.isa.botica.fishbowl.Position;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerBot extends AbstractBotApplication {
  private static final Logger log = LoggerFactory.getLogger(ManagerBot.class);
  private static final Path FILES_DIRECTORY = Path.of("/app/fishbowl");
  private static final int FISHBOWL_SIZE = 9;

  private final Fishbowl fishbowl = new Fishbowl(FISHBOWL_SIZE);
  private int fileVersion = 1;

  @Override
  public void onOrderReceived(String raw) {
    JSONObject message = new JSONObject(raw);

    String fish = message.getString("silhouette");
    Position lastPosition = this.fishbowl.getPosition(fish);
    Position newPosition = new Position(message.getInt("x"), message.getInt("y"));

    log.info("Fish {} moved! {} -> {}", fish, lastPosition, newPosition);
    this.fishbowl.setPosition(fish, newPosition);
    this.saveFile();
  }

  private void saveFile() {
    Path filePath = FILES_DIRECTORY.resolve("v" + this.fileVersion++ + ".txt");
    try {
      Files.writeString(filePath, this.fishbowl.render());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
