import java.util.ArrayList;
public class MainMemory {
  private ArrayList mainMemory = new ArrayList();
  public MainMemory() {
    for (int i=0; i<1048576;i++) {
      mainMemory.add(0);
    }
  }
  public ArrayList getMainMemory(int slot) {
    return mainMemory.get(slot);
  }
  public void setMainMemory(int slot, int integer) {
    mainMemory.remove(slot);
    mainMemory.add(slot,integer);
  }
}
