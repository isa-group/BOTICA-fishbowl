package es.us.isa.botica.fishbowl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FishbowlTest {
  @Test
  void testRender() {
    Fishbowl fishbowl = new Fishbowl(9);
    fishbowl.setPosition("1", new Position(8, 8));
    fishbowl.setPosition("1", new Position(0, 0));
    fishbowl.setPosition("2", new Position(5, 5));

    String render = fishbowl.render();
    assertThat(render)
        .isEqualTo(
            """
1--------
---------
---------
---------
---------
-----2---
---------
---------
---------"""); // formatter bug: https://github.com/google/google-java-format/issues/883
  }
}
