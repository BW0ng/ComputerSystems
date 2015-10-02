public class MIPSsimulator {

  public static void main(String[] args) {
    new MIPSsim();
  }

}
class MIPSsim {
  private MainMemory mm;
  private GeneralPurposeRegisters gpr;
  private SpecialPurposeRegisters spr;
  public MIPSsim() {
    mm = new MainMemory();
    gpr = new GeneralPurposeRegisters();
    spr = new SpecialPurposeRegisters();
    //Do Shit
  }

}
