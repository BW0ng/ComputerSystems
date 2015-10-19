//File: MIPSsimulator.java
//Name: ChrisTopher Thomas
//CWID: 11652560

import java.io.*;
import java.lang.Math;
import java.util.*;
public class MIPSsimulator {

  public static void main(String[] args) {
    System.out.println(" ChrisTopher C. Thomas");
    System.out.println("----------------------");
    File file = new File("../TextFiles/" + args[0] + ".txt");
    new MIPSsim(file);
  }//main
}

class MIPSsim {
  //Time Program
  long startProgram;
  long endProgram;
  //Input File
  private File file;
  //Read File
  private BufferedReader bufferedReader;
  private FileReader fileReader;
  //Memory's
  private int mainMemorySize = (int)Math.pow(2,20);
  private int[] MM = new int[mainMemorySize]; // Main Memory
  /* General purpose register
      $0 = zero -> always value 0
      $1 = at -> Assembler Temp
      $2-$3 = v0-v1
      $4-$7 = a0-a3
      $8-$15,$24-$25 = t0-t9 -> temp registers
      $16-$23,$30 = s0-s8 -> saved registers
      $26-$27 = k0-k1 -> OS kernel & exception return
      $28 = gp -> Global pointer
      $29 = sp -> Stack pointer
      $31 = ra -> return adress
  */
  private int[] GPR = new int[32];
  private int PC,nPC,LO,HI;

  /*
      Start of Simulator
      -Takes in file from main method
  */
  public MIPSsim(File file) {
    this.file=file;
    startProgram = System.currentTimeMillis();
    for(int i=0;i<mainMemorySize;i++) MM[i]=0;
    for(int i=0;i<32;i++) GPR[i]=0;


    readFile();
    System.out.println(PC);
    for(int i=0;i<32;i++)
      if(GPR[i]!=0) System.out.println("Index: " + i + "   GPR:" +GPR[i]);
    for (int i=0;i<mainMemorySize;i++)
      if(MM[i]!=0) System.out.println("Index: " + i + "   MM:" + Integer.toBinaryString(MM[i]));


    //End of Program
    endProgram();
  }//MIPSsim

  //Interpret line from input file, extact hex code, convert to decimal and save
  private void interpret(String line) {
    boolean string1Hex=false;
    String string1="";
    String string2="";
    int decimal1=0;
    int decimal2=0;

    //if line is not blank or commented out read line
    if(line.length() > 0 && !(line.charAt(0)=='#' || line.charAt(0)== ' ')) {

      //init split
      String[] splited = line.split("\\s+");

      if(splited.length >= 2) {
        String h1 = splited[0];
        String h2 = splited[1];

        //check to see if first part is hexcode
        if(h1.charAt(1)=='0') {string1 = h1.substring(3,splited[0].length()-1);string1Hex=true;}
        //if not hexCode, must be [PC]..ect;
        else {string1 = h1.substring(1,splited[0].length()-1);string1Hex=false;}
        ////get second hex code (or in case of else statement first hexcode on line)
        string2 = h2.substring(2,splited[1].length());
      }

      //check to see if string1 is hexcode or not then parse to int
      if(string1.length()==8) {
        decimal1 = Integer.parseInt(string1,16);
      }
      //parse second string(hexcode) to int
      decimal2 = Integer.parseInt(string2,16);

      //if line(string1) not empty, save decimal2(hexCode2) to memeory at index decimal1(String 1)
      if(!string1.equals("") && string1Hex) {
        MM[decimal1/4] = decimal2;
      }else if(string1.equals("PC") && !string1Hex) {
        PC=decimal2;
        nPC=PC+4;
      }else if(string1.charAt(0)=='R' && !string1Hex) {
        string1=string1.substring(1);
        int rIndex = Integer.parseInt(string1);
        GPR[rIndex] = decimal2;
      }
    }
  }
  //Read in input file
  private void readFile() {
    try {
      fileReader = new FileReader(file);
      bufferedReader = new BufferedReader(fileReader);
      String line;
      while((line = bufferedReader.readLine()) != null) {
        interpret(line);
      }//while
    }//try
    catch(FileNotFoundException e) {e.printStackTrace();}
    catch(IOException e) {e.printStackTrace();}
    finally {
      try {
        bufferedReader.close();
      }catch (IOException e) {
        e.printStackTrace();
      }
    }//finally
  }//readFile

  /*
  //Check mips instructions
  private void mipsInstructions () {
    if() { //ADD - Add(with overflow)
      //$d = $s + $t; advance_pc(4)

    }else if() { //ADDI - Add immediate(with overflow)
      //$t = $s + imm; advance_pc(4);

    }else if() { //ADDIU - Add immediate unsigned(no overflow)
      //$t = $s + imm; advance_pc(4);

    }else if() { //ADDU - add unsighted(no overflow)
      //$d = $s + $t; advance_pc(4);

    }else if() { //AND - Bitwise and
      //$d = $s & $t; advance_pc(4);

    }else if() { //ANDI - Bitwise and immediate
      //$t = $s & imm; advance_pc(4);

    }else if() { //BEQ - Branch on equal
      //if $s == $t advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BGEZ - Branch on greater than or equal to zero
      //if $s >= 0 advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BGEZAL - Branch on greater than or equal to zero and link
      //if $s >= 0 $31 = PC + 8 (or nPC + 4); advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BGTZ - Branch on greater than zero
      //if $s > 0 advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BLEZ - Branch on less than or equal to zero
      //if $s <= 0 advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BLTZ - Branch on less than zero
      //if $s < 0 advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BLTZAL - Branch on less than zero and link
      //if $s < 0 $31 = PC + 8 (or nPC + 4); advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //BNE - Branch on not equal
      //if $s != $t advance_pc (offset << 2)); else advance_pc (4);

    }else if() { //DIV - Divide
      //$LO = $s / $t; $HI = $s % $t; advance_pc (4);

    }else if() { //J - Jump
      //PC = nPC; nPC = (PC & 0xf0000000) | (target << 2);

    }else if() { //JAL - Jump and link
      //$31 = PC + 8 (or nPC + 4); PC = nPC; nPC = (PC & 0xf0000000) | (target << 2);

    }else if() { //JR - Jump register
      //PC = nPC; nPC = $s;

    }else if() { //LB - Load byte
      //$t = MEM[$s + offset]; advance_pc (4);

    }else if() { //LUI - Load upper immediate
      //$t = (imm << 16); advance_pc (4);

    }else if() { //LW - Load word
      //$t = MEM[$s + offset]; advance_pc (4);

    }else if() { //MFHI - Move from HI
      //$d = $HI; advance_pc (4);

    }else if() { //MFLO - Move from LO
      //$d = $LO; advance_pc (4);

    }else if() { //MULT - Multiply
      //$LO = $s * $t; advance_pc (4);

    }else if() { //OR - Bitwise or
      //$d = $s | $t; advance_pc (4);

    }else if() { //ORI - Bitwise or immediate
      //$t = $s | imm; advance_pc (4);

    }else if() { //SB - Store byte
      //$d = $t << h; advance_pc (4);

    }else if() { //SLL - Shift left logical
      //$d = $t << h; advance_pc (4);

    }else if() { //SLLV - Shift left logical variable
      //$d = $t << $s; advance_pc (4);

    }else if() { //SLT - Set on less than (signed)
      //if $s < $t $d = 1; advance_pc (4); else $d = 0; advance_pc (4);

    }else if() { //SLTI - Set on less than immediate (signed)
      //if $s < imm $t = 1; advance_pc (4); else $t = 0; advance_pc (4);

    }else if() { //SLTIU - Set on less than immediate unsigned
      //if $s < imm $t = 1; advance_pc (4); else $t = 0; advance_pc (4);

    }else if() { //SLTU - Set on less than unsigned
      //if $s < $t $d = 1; advance_pc (4); else $d = 0; advance_pc (4);

    }else if() { //SRA - Shift right arithmetic
      //$d = $t >> h; advance_pc (4);

    }else if() { //SRL - Shift right logical
      //$d = $t >> h; advance_pc (4);

    }else if() { //SRLV - Shift right logical variable
      //$d = $t >> $s; advance_pc (4);

    }else if() { //SUB - Subtract
      //$d = $s - $t; advance_pc (4);

    }else if() { //SUBU - Subtract unsigned
      //$d = $s - $t; advance_pc (4);

    }else if() { //SW - Store word
      //MEM[$s + offset] = $t; advance_pc (4);

    }else if() { //SYSCALL - System call
      //advance_pc (4);

    }else if() { //XOR - Bitwise exclusive or
      //$d = $s ^ $t; advance_pc (4);

    }else if() { //XORI - Bitwise exclusive or immediate
      //$t = $s ^ imm; advance_pc (4);

    }else{
      System.out.println("Error - MIPSindtructions: Broken");
    }
  }

  private systemCall() {
    if() {
      System.out.println("a0: " + gpr[]);
    }else if() {

    }else if() {

    }else if() {

    }else if() {

    }else if() {

    }else if() {
      System.out.println("Ending Program...");
      System.exit(0);
    }
  }
  */


  //End Program: No methods past this point
  private void endProgram() {
    endProgram = System.currentTimeMillis();
    System.out.println("Program Ending...");
    System.out.println("Run Time: " + ((endProgram-startProgram)*.001) + "s");
    System.exit(0);
  }

  //Masks
  private String maskADD =  "11111111111111111111111111111111" ; //ADD
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
  private String[] maskInstructions = {
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

}//MIPSsim