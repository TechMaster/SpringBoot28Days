package vn.techmaster.vincinema.model;

public enum Rating {
  P("Mọi lứa tuổi"),
  C13("13 tuổi trở lên"),
  C16("16 tuổi trở lên"),
  C18("18 tuổi trở lên");

  public final String label;
  private Rating(String label) {
      this.label = label;
  }
}
