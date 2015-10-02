import java.util.ArrayList;
public class SpecialPurposeRegisters {
  private ArrayList pc  = new ArrayList(); //PC - the Program Counter
  private ArrayList npc = new ArrayList(); //nPC - the next Program Counter
  private ArrayList lo = new ArrayList(); //LO - used to store the results of multiplication and division
  private ArrayList hi = new ArrayList(); //HI - used to store the results of multiplication and division
  public SpecialPurposeRegisters() {
    for (int i=0; i<32;i++) {
      pc.add(0);
      npc.add(0);
      lo.add(0);
      hi.add(0);
    }
  }

  //Getters
  public ArrayList getPC(int slot) {
    return pc.get(slot);
  }
  public ArrayList getNPC(int slot) {
    return npc.get(slot);
  }
  public ArrayList getLO(int slot) {
    return lo.get(slot);
  }
  public ArrayList getHI(int slot) {
    return hi.get(slot);
  }
  //Setters
  public void setPC(int slot, int integer) {
    pc.remove(slot);
    pc.add(slot,integer);
  }
  public void setNPC(int slot, int integer) {
    npc.remove(slot);
    npc.add(slot,integer);
  }
  public void setLO(int slot, int integer) {
    lo.remove(slot);
    lo.add(slot,integer);
  }
  public void setHI(int slot, int integer) {
    hi.remove(slot);
    hi.add(slot,integer);
  }
}

Special urpose registers.
