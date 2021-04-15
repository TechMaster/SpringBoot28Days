public class Building {
  public void openABuilding() {
    System.out.println("Building opens");
    openDoors();
  }

  public void openDoors() {
    System.out.println("Staff open doors");
    letEverythingEnter();
  }

  public void letEverythingEnter() {
    System.out.println("A snake enters building and bites a staff");
    throw new SnakeBiteException("It bites a staff");
  }
}
