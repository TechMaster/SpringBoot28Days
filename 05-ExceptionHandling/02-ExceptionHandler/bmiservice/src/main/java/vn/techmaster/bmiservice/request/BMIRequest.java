package vn.techmaster.bmiservice.request;

public class BMIRequest {
  private float height;
  private float weight;
  public BMIRequest(float height, float weight) {
    this.height = height;
    this.weight = weight;
  }
  public float getHeight() {
    return height;
  }
  public void setHeight(float height) {
    this.height = height;
  }
  public float getWeight() {
    return weight;
  }
  public void setWeight(float weight) {
    this.weight = weight;
  }  
}
