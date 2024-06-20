package es.us.isa.botica.fishbowl.fish;

import es.us.isa.botica.bot.AbstractBotApplication;
import java.util.Random;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishBot extends AbstractBotApplication {
  private static final Logger log = LoggerFactory.getLogger(FishBot.class);
  private static final int MAX_INDEX = 9;

  private final Random random = new Random();

  private String silhouette;
  private int x;
  private int y;

  @Override
  public void configure() {
    this.silhouette = System.getenv("FISH_SILHOUETTE");
    this.x = Integer.parseInt(System.getenv("FISH_POSITION_X"));
    this.y = Integer.parseInt(System.getenv("FISH_POSITION_Y"));
  }

  @Override
  public void executeAction() {
    int previousX = this.x;
    int previousY = this.y;

    this.x += this.nextMovement(this.x);
    this.y += this.nextMovement(this.y);
    log.info("Moved! ({},{}) -> ({},{})", previousX, previousY, this.x, this.y);

    publishOrder(
        new JSONObject()
            .put("silhouette", this.silhouette)
            .put("x", this.x)
            .put("y", this.y)
            .toString());
  }

  private int nextMovement(int currentPosition) {
    int minMovement = currentPosition == 0 ? 0 : -1;
    int maxMovement = currentPosition == MAX_INDEX - 1 ? 1 : 2;
    return this.random.nextInt(minMovement, maxMovement);
  }
}
