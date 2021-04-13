import java.util.List;

public class UncheckExcept {
  private float height;

  public void setWeight(float height) {
    if (height < 0 || height > 2.8) {
      throw new IllegalArgumentException("Invalid height");
    }
    this.height = height;
  }

  public float getHeight() {
    return height;
  }

  public float divide(int x, int y) {
    return x / y;
  }

  
}
