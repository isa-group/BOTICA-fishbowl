package es.us.isa.botica.fishbowl;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Fishbowl {
  private final Map<String, Position> fishPositions = new HashMap<>();
  private final int size;

  public Fishbowl(int size) {
    this.size = size;
  }

  public Position getPosition(String fish) {
    return this.fishPositions.get(fish);
  }

  public void setPosition(String fish, Position position) {
    if (!this.isValid(position)) {
      throw new IndexOutOfBoundsException(
          "%s is out of bounds (size=%d)".formatted(position, this.size));
    }
    this.fishPositions.put(fish, position);
  }

  public Map<Position, List<String>> getFishesByPosition() {
    return this.fishPositions.entrySet().stream()
        .collect(groupingBy(Entry::getValue, mapping(Entry::getKey, toList())));
  }

  public String render() {
    Map<Position, List<String>> fishesByPosition = this.getFishesByPosition();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        List<String> fishes = fishesByPosition.get(new Position(i, j));
        if (fishes == null || fishes.isEmpty()) {
          builder.append("-");
        } else if (fishes.size() == 1) {
          builder.append(fishes.getFirst());
        } else {
          builder.append("O");
        }
      }
      if (i < this.size - 1) {
        builder.append("\n");
      }
    }
    return builder.toString();
  }

  private boolean isValid(Position position) {
    return position.x() >= 0
        && position.x() < this.size
        && position.y() >= 0
        && position.y() < this.size;
  }
}
