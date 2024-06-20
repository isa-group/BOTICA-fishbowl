package es.us.isa.botica.fishbowl;

public record Position(int x, int y) {
  @Override
  public String toString() {
    return "(%d,%d)".formatted(this.x, this.y);
  }
}
