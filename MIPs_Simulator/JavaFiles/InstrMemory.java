import java.util.Arrays;

public class InstrMemory {
  Circuits circuit;
  //Intruction Masks
  private int[] maskADD = { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }; //ADD
  private int[] maskADDI = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //ADDI
  private int[] maskADDIU = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //ADDIU
  private int[] maskADDU = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //ADDU
  private int[] maskAND = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //AND
  private int[] maskANDI = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //ANDI
  private int[] maskBEQ = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BEQ
  private int[] maskBGEZ = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BGEZ
  private int[] maskBGEZAL = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BGEZAL
  private int[] maskBGTZ = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BGTZ
  private int[] maskBLEZ = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BLEZ
  private int[] maskBLTZ = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BLTZ
  private int[] maskBLTZAL = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BLTZAL
  private int[] maskBNE = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //BNE
  private int[] maskDIV = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }; //DIV
  private int[] maskJ = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //J
  private int[] maskJAL = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //JAL
  private int[] maskJR = { 1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }; //JR
  private int[] maskLB = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //LB
  private int[] maskLUI = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //LUI
  private int[] maskLW = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //LW
  private int[] maskMFHI = { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //MFHI
  private int[] maskMFLO = { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //MFLO
  private int[] maskMULT = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }; //MULT
  private int[] maskOR = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //OR
  private int[] maskORI = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //ORI
  private int[] maskSLL = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 }; //SLL
  private int[] maskSLLV = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 }; //SLLV
  private int[] maskSLT = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //SLT
  private int[] maskSLTI = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //SLTI
  private int[] maskSLTIU = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //SLTIU
  private int[] maskSLTU = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //SLTU
  private int[] maskSRA = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 }; //SRA
  private int[] maskSRL = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 }; //SRL
  private int[] maskSRLV = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //SRLV
  private int[] maskSUB = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //SUB
  private int[] maskSUBU = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1 }; //SUBU
  private int[] maskSW = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //SW
  private int[] maskSB = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //SB
  private int[] maskSYSCALL = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 }; //SYSCALL
  private int[] maskXOR = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 }; //XOR
  private int[] maskXORI = { 1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 }; //XORI
  private int[][] maskInstructions = {
    maskADD,maskADDI,maskADDIU,maskADDU,maskAND,
    maskANDI,maskBEQ,maskBGEZ,maskBGEZAL,maskBGTZ,
    maskBLEZ,maskBLTZ,maskBLTZAL,maskBNE,maskDIV,
    maskJ,maskJAL,maskJR,maskLB,maskLUI,maskLW,
    maskMFHI,maskMFLO,maskMULT,maskOR,maskORI,
    maskSLL,maskSLLV,maskSLT,maskSLTI,maskSLTIU,
    maskSLTU,maskSRA,maskSRL,maskSRLV,maskSUB,
    maskSUBU,maskSW,maskSB,maskSYSCALL,maskXOR,maskXORI
  };
  private String[] instructionNames = {
    "ADD","ADDI","ADDIU","ADDU","AND","ANDI","BEQ",
    "BGEZ","BGEZAL","BGTZ","BLEZ","BLTZ","BLTZAL",
    "BNE","DIV","J","JAL","JR","LB","LUI","LW","MFHI",
    "MFLO","MULT","OR","ORI","SLL","SLLV","SLT",
    "SLTI","SLTIU","SLTU","SRA","SRL","SRLV","SUB",
    "SUBU","SW","SB","SYSCALL","XOR","XORI"
  };

  //constructor
  public InstrMemory() {
    circuit = new Circuits();
  }

  //convert a instruction(String) to an int array for future use.
  public int[] convertInstr(String instruction) {
    int[] instr = new int[32];
    int spaceCount = 0;
    for (int i=0;i<instruction.length();i++) {
      if (instruction.charAt(i)=='1') instr[i-spaceCount]=1;
      else if (instruction.charAt(i)==' ') {spaceCount++;}
      else instr[i-spaceCount]=0;
    }
    return instr;
  }
  private int[] zeroArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  //figure out what instruction the user want's to do - //final product should be all zero's
  public void checkInstruction(int[] instr) {
    for(int i=0;i<maskInstructions.length;i++) {
      if (Arrays.equals(circuit.AND(instr,maskInstructions[i]), zeroArray)) {
        System.out.println("" + instructionNames[i]);
        if(instructionNames[i].equals("SYSCALL")) System.exit(0); //If SYSCALL exit program
        else System.out.println(instructionNames[i]);
      }
    }
  }

}







/*
    0000 0000 0000 0000 0000 0000 0010 0000, //ADD      index=0
    0010 00ss ssst tttt iiii iiii iiii iiii, //ADDI
    0010 01ss ssst tttt iiii iiii iiii iiii, //ADDIU
    0000 00ss ssst tttt dddd d000 0010 0001, //ADDU
    0000 00ss ssst tttt dddd d000 0010 0100, //AND      index=4
    0011 00ss ssst tttt iiii iiii iiii iiii, //ANDI
    0001 00ss ssst tttt iiii iiii iiii iiii, //BEQ
    0000 01ss sss0 0001 iiii iiii iiii iiii, //BGEZ
    0000 01ss sss1 0001 iiii iiii iiii iiii, //BGEZAL
    0001 11ss sss0 0000 iiii iiii iiii iiii, //BGTZ      index=9
    0001 10ss sss0 0000 iiii iiii iiii iiii, //BLEZ
    0000 01ss sss0 0000 iiii iiii iiii iiii, //BLTZ
    0000 01ss sss1 0000 iiii iiii iiii iiii, //BLTZAL
    0001 01ss ssst tttt iiii iiii iiii iiii, //BNE
    0000 00ss ssst tttt 0000 0000 0001 1010, //DIV       index=14
    0000 10ii iiii iiii iiii iiii iiii iiii, //J
    0000 11ii iiii iiii iiii iiii iiii iiii, //JAL
    0000 00ss sss0 0000 0000 0000 0000 1000, //JR
    1000 00ss ssst tttt iiii iiii iiii iiii, //LB
    0011 11-- ---t tttt iiii iiii iiii iiii, //LUI      index=19
    1000 11ss ssst tttt iiii iiii iiii iiii, //LW
    0000 0000 0000 0000 dddd d000 0001 0000, //MFHI
    0000 0000 0000 0000 dddd d000 0001 0010, //MFLO
    0000 00ss ssst tttt 0000 0000 0001 1000, //MULT
    0000 00ss ssst tttt dddd d000 0010 0101, //OR       index=24
    0011 01ss ssst tttt iiii iiii iiii iiii, //ORI
    1010 00ss ssst tttt iiii iiii iiii iiii, //SB
    0000 00ss ssst tttt dddd dhhh hh00 0000, //SLL
    0000 00ss ssst tttt dddd d--- --00 0100, //SLLV
    0000 00ss ssst tttt dddd d000 0010 1010, //SLT      index=29
    0010 10ss ssst tttt iiii iiii iiii iiii, //SLTI
    0010 11ss ssst tttt iiii iiii iiii iiii, //SLTIU
    0000 00ss ssst tttt dddd d000 0010 1011, //SLTU
    0000 00-- ---t tttt dddd dhhh hh00 0011, //SRA
    0000 00-- ---t tttt dddd dhhh hh00 0010, //SRL      index=34
    0000 00ss ssst tttt dddd d000 0000 0110, //SRLV
    0000 00ss ssst tttt dddd d000 0010 0010, //SUB
    0000 00ss ssst tttt dddd d000 0010 0011, //SUBU
    1010 11ss ssst tttt iiii iiii iiii iiii, //SW
    1010 00ss ssst tttt iiii iiii iiii iiii, //SB      index=39
    0000 00-- ---- ---- ---- ---- --00 1100, //SYSCALL
    0000 00ss ssst tttt dddd d--- --10 0110, //XOR
    0011 10ss ssst tttt iiii iiii iiii iiii //XORI      index=42
*/



/*
private int[] instrADD = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0}; //ADD
private int[] instrADDI = {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //ADDI
private int[] instrADDIU = {0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //ADDIU
private int[] instrADDU = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}; //ADDU
private int[] instrAND = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0}; //AND
private int[] instrANDI = {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //ANDI
private int[] instrBEQ = {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BEQ
private int[] instrBGEZ = {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BGEZ
private int[] instrBGEZAL = {0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BGEZAL
private int[] instrBGTZ = {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BGTZ
private int[] instrBLEZ = {0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BLEZ
private int[] instrBLTZ = {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BLTZ
private int[] instrBLTZAL = {0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BLTZAL
private int[] instrBNE = {0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //BNE
private int[] instrDIV = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0}; //DIV
private int[] instrJ = {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //J
private int[] instrJAL = {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //JAL
private int[] instrJR = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0}; //JR
private int[] instrLB = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //LB
private int[] instrLUI = {0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //LUI
private int[] instrLW = {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //LW
private int[] instrMFHI = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0}; //MFHI
private int[] instrMFLO = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0}; //MFLO
private int[] instrMULT = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0}; //MULT
private int[] instrOR = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1}; //OR
private int[] instrORI = {0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //ORI
private int[] instrSLL = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //SLL
private int[] instrSLLV = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0}; //SLLV
private int[] instrSLT = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0}; //SLT
private int[] instrSLTI = {0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //SLTI
private int[] instrSLTIU = {0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //SLTIU
private int[] instrSLTU = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1}; //SLTU
private int[] instrSRA = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1}; //SRA
private int[] instrSRL = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0}; //SRL
private int[] instrSRLV = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0}; //SRLV
private int[] instrSUB = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0}; //SUB
private int[] instrSUBU = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1}; //SUBU
private int[] instrSW = {1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //SW
private int[] instrSB = {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //SB
private int[] instrSYSCALL = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0}; //SYSCALL
private int[] instrXOR = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0}; //XOR
private int[] instrXORI = {0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //XORI




private String instrADD =  "0000 0000 0000 0000 0000 0000 0010 0000"; //ADD      index=0
private String instrADDI =   "0010 00ss ssst tttt iiii iiii iiii iiii"; //ADDI
private String instrADDIU =   "0010 01ss ssst tttt iiii iiii iiii iiii"; //ADDIU
private String instrADDU =   "0000 00ss ssst tttt dddd d000 0010 0001"; //ADDU
private String instrAND =  "0000 00ss ssst tttt dddd d000 0010 0100"; //AND      index=4
private String instrANDI =   "0011 00ss ssst tttt iiii iiii iiii iiii"; //ANDI
private String instrBEQ =  "0001 00ss ssst tttt iiii iiii iiii iiii"; //BEQ
private String instrBGEZ =   "0000 01ss sss0 0001 iiii iiii iiii iiii"; //BGEZ
private String instrBGEZAL =   "0000 01ss sss1 0001 iiii iiii iiii iiii"; //BGEZAL
private String instrBGTZ =   "0001 11ss sss0 0000 iiii iiii iiii iiii"; //BGTZ      index=9
private String instrBLEZ =  "0001 10ss sss0 0000 iiii iiii iiii iiii"; //BLEZ
private String instrBLTZ =  "0000 01ss sss0 0000 iiii iiii iiii iiii"; //BLTZ
private String instrBLTZAL =  "0000 01ss sss1 0000 iiii iiii iiii iiii"; //BLTZAL
private String instrBNE =  "0001 01ss ssst tttt iiii iiii iiii iiii"; //BNE
private String instrDIV =  "0000 00ss ssst tttt 0000 0000 0001 1010"; //DIV       index=14
private String instrJ =  "0000 10ii iiii iiii iiii iiii iiii iiii"; //J
private String instrJAL =  "0000 11ii iiii iiii iiii iiii iiii iiii"; //JAL
private String instrJR =  "0000 00ss sss0 0000 0000 0000 0000 1000"; //JR
private String instrLB =  "1000 00ss ssst tttt iiii iiii iiii iiii"; //LB
private String instrLUI =  "0011 11-- ---t tttt iiii iiii iiii iiii"; //LUI      index=19
private String instrLW =  "1000 11ss ssst tttt iiii iiii iiii iiii"; //LW
private String instrMFHI =  "0000 0000 0000 0000 dddd d000 0001 0000"; //MFHI
private String instrMFLO =  "0000 0000 0000 0000 dddd d000 0001 0010"; //MFLO
private String instrMULT =  "0000 00ss ssst tttt 0000 0000 0001 1000"; //MULT
private String instrOR =  "0000 00ss ssst tttt dddd d000 0010 0101"; //OR       index=24
private String instrORI =  "0011 01ss ssst tttt iiii iiii iiii iiii"; //ORI
private String instrSLL =  "0000 00ss ssst tttt dddd dhhh hh00 0000"; //SLL
private String instrSLLV =  "0000 00ss ssst tttt dddd d--- --00 0100"; //SLLV
private String instrSLT =  "0000 00ss ssst tttt dddd d000 0010 1010"; //SLT      index=29
private String instrSLTI =  "0010 10ss ssst tttt iiii iiii iiii iiii"; //SLTI
private String instrSLTIU =  "0010 11ss ssst tttt iiii iiii iiii iiii"; //SLTIU
private String instrSLTU =  "0000 00ss ssst tttt dddd d000 0010 1011"; //SLTU
private String instrSRA =  "0000 00-- ---t tttt dddd dhhh hh00 0011"; //SRA
private String instrSRL =  "0000 00-- ---t tttt dddd dhhh hh00 0010"; //SRL      index=34
private String instrSRLV =  "0000 00ss ssst tttt dddd d000 0000 0110"; //SRLV
private String instrSUB =  "0000 00ss ssst tttt dddd d000 0010 0010"; //SUB
private String instrSUBU =  "0000 00ss ssst tttt dddd d000 0010 0011"; //SUBU
private String instrSW =  "1010 11ss ssst tttt iiii iiii iiii iiii"; //SW
private String instrSB =  "1010 00ss ssst tttt iiii iiii iiii iiii"; //SB      index=39
private String instrSYSCALL =  "0000 00-- ---- ---- ---- ---- --00 1100"; //SYSCALL
private String instrXOR =  "0000 00ss ssst tttt dddd d--- --10 0110"; //XOR
private String instrXORI =  "0011 10ss ssst tttt iiii iiii iiii iiii"; //XORI





//print masks
public void printMask() {
  String instruction;
  int[] instr;
  int spaceCount;
  for (int j=0;j<instructions.length;j++) {
    instruction = instructions[j];
    instr = new int[32];
    spaceCount = 0;

    for(int i=0;i<instruction.length();i++) {
      if (instruction.charAt(i)=='1' || instruction.charAt(i)=='0') instr[i-spaceCount]=1;
      else if (instruction.charAt(i)==' ') {spaceCount++;}
      else instr[i-spaceCount]=0;
    }

    System.out.print("private int[] mask" +  instructionNames[j] + " = { ");
    for(int i=0;i<32;i++) {
      if(i==31) System.out.print(instr[i] + " };");
      else System.out.print(instr[i] + ",");
    }
    System.out.println(" //" + instructionNames[j]);

  }
*/

/*
class Converters {
  //Working
  public int[] hexToBinary(String hexCode) {
    int[] binaryNumber= new int[32];
    for (int i=0;i<hexCode.length();i++) {
      if(hexCode.charAt(i)=='0'){
        binaryNumber[(i*4)] = 0;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='1'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='2'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='3'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='4'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='5'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='6'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='7'){
        binaryNumber[i*4] = 0;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='8'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='9'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='A' || hexCode.charAt(i)=='a'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='B' || hexCode.charAt(i)=='b'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 0;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='C' || hexCode.charAt(i)=='c'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='D' || hexCode.charAt(i)=='d'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 0;
        binaryNumber[(i*4)+3] = 1;
      }
      else if(hexCode.charAt(i)=='E' || hexCode.charAt(i)=='e'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 0;
      }
      else if(hexCode.charAt(i)=='F' || hexCode.charAt(i)=='f'){
        binaryNumber[i*4] = 1;
        binaryNumber[(i*4)+1] = 1;
        binaryNumber[(i*4)+2] = 1;
        binaryNumber[(i*4)+3] = 1;
      }
      else {
        System.out.println("Error: hexToBinary()");
        System.exit(0);
      }
    }
    return binaryNumber;
  }//hexToBinary

  public String binaryToHex(int[] binary) {
    String hexCode="";
    for (int i=0;i<8;i++) {
      int a = binary[i*4];
      int b = binary[i*4+1];
      int c = binary[i*4+2];
      int d = binary[i*4+3];

      if(a==0 && b==0 && c==0 && d==0) hexCode+='0';
      else if(a==0 && b==0 && c==0 && d==1) hexCode+='1';
      else if(a==0 && b==0 && c==1 && d==0) hexCode+='2';
      else if(a==0 && b==0 && c==1 && d==1) hexCode+='3';
      else if(a==0 && b==1 && c==0 && d==0) hexCode+='4';
      else if(a==0 && b==1 && c==0 && d==1) hexCode+='5';
      else if(a==0 && b==1 && c==1 && d==0) hexCode+='6';
      else if(a==0 && b==1 && c==1 && d==1) hexCode+='7';
      else if(a==1 && b==0 && c==0 && d==0) hexCode+='8';
      else if(a==1 && b==0 && c==0 && d==1) hexCode+='9';
      else if(a==1 && b==0 && c==1 && d==0) hexCode+='A';
      else if(a==1 && b==0 && c==1 && d==1) hexCode+='B';
      else if(a==1 && b==1 && c==0 && d==0) hexCode+='C';
      else if(a==1 && b==1 && c==0 && d==1) hexCode+='D';
      else if(a==1 && b==1 && c==1 && d==0) hexCode+='E';
      else if(a==1 && b==1 && c==1 && d==1) hexCode+='F';
      else {
        System.out.println("Error: binaryToHex()");
        System.exit(0);
      }
    }//for loop
    return hexCode;
  }//binaryToHex

  //Broken
}





//Masks
private String maskADD =  "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1" ; //ADD
private String maskADDI =  "11111100000000000000000000000000" ; //ADDI
private String maskADDIU =  "11111100000000000000000000000000" ; //ADDIU
private String maskADDU =  "11111100000000000000011111111111" ; //ADDU
private String maskAND =  "11111100000000000000011111111111" ; //AND
private String maskANDI =  "11111100000000000000000000000000" ; //ANDI
private String maskBEQ =  "11111100000000000000000000000000" ; //BEQ
private String maskBGEZ =  "11111100000111110000000000000000" ; //BGEZ
private String maskBGEZAL =  "11111100000111110000000000000000" ; //BGEZAL
private String maskBGTZ =  "11111100000111110000000000000000" ; //BGTZ
private String maskBLEZ =  "11111100000111110000000000000000" ; //BLEZ
private String maskBLTZ =  "11111100000111110000000000000000" ; //BLTZ
private String maskBLTZAL =  "11111100000111110000000000000000" ; //BLTZAL
private String maskBNE =  "11111100000000000000000000000000" ; //BNE
private String maskDIV =  "11111100000000001111111111111111" ; //DIV
private String maskJ =  "11111100000000000000000000000000" ; //J
private String maskJAL =  "11111100000000000000000000000000" ; //JAL
private String maskJR =  "11111100000111111111111111111111" ; //JR
private String maskLB =  "11111100000000000000000000000000" ; //LB
private String maskLUI =  "11111100000000000000000000000000" ; //LUI
private String maskLW =  "11111100000000000000000000000000" ; //LW
private String maskMFHI =  "11111111111111110000011111111111" ; //MFHI
private String maskMFLO =  "11111111111111110000011111111111" ; //MFLO
private String maskMULT =  "11111100000000001111111111111111" ; //MULT
private String maskOR =  "11111100000000000000011111111111" ; //OR
private String maskORI =  "11111100000000000000000000000000" ; //ORI
private String maskSLL =  "11111100000000000000000000111111" ; //SLL
private String maskSLLV =  "11111100000000000000000000111111" ; //SLLV
private String maskSLT =  "11111100000000000000011111111111" ; //SLT
private String maskSLTI =  "11111100000000000000000000000000" ; //SLTI
private String maskSLTIU =  "11111100000000000000000000000000" ; //SLTIU
private String maskSLTU =  "11111100000000000000011111111111" ; //SLTU
private String maskSRA =  "11111100000000000000000000111111" ; //SRA
private String maskSRL =  "11111100000000000000000000111111" ; //SRL
private String maskSRLV =  "11111100000000000000011111111111" ; //SRLV
private String maskSUB =  "11111100000000000000011111111111" ; //SUB
private String maskSUBU =  "11111100000000000000011111111111" ; //SUBU
private String maskSW =  "11111100000000000000000000000000" ; //SW
private String maskSB =  "11111100000000000000000000000000" ; //SB
private String maskSYSCALL =  "11111100000000000000000000111111" ; //SYSCALL
private String maskXOR =  "11111100000000000000000000111111" ; //XOR
private String maskXORI =  "11111100000000000000000000000000" ; //XORI
*/
