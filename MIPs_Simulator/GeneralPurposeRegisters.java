import java.util.ArrayList;
public class GeneralPurposeRegisters {
  private ArrayList gpr = new ArrayList();
  public GeneralPurposeRegisters() {
    for (int i=0; i<32;i++) {
      gpr.add(0);
    }
  }
  public ArrayList getGPR(int slot) {
    return gpr.get(slot);
  }
  public void setGPR(int slot, int integer) {
    gpr.remove(slot);
    gpr.add(slot,integer);
  }
}
