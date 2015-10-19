//File: READ_ME.txt
//Name: Christopher Thomas
//CWID:

InstrMemory
PC: Address of current instruction
PC -> InstrMemory
PC -> ALU(adder)
4 -> ALU
ALU -> ALU2
ALU -> ControlLine(MUX)

InstrMemory S-> Registers
InstrMemory T-> Registers
InstrMemory D-> Registers

Registers S-> ALU3
Registers D-> ControlLine3
InstrMemory D-> ControlLine3
ControlLine3 D-> ALU3

ALU3 address-> DataMemory
Registers data-> DataMemory

ALU3 -> ControlLine2
DataMemory -> ControlLine2
ControlLine2 -> Registers

InstrMemory -> ALU2
ALU2 -> ControlLine(MUX)

ControlLine -> PC

/*NOTES*/
x = A[i];
A: $s0
i: $s1
x: $s2

sll $t0, $s1, 2
add $t0, $t0, $s0
lw $s2, 0($t0)

lw $s2, ($s1<<2) ($s0)

RISC



while(true)
{

//FetchIntruction
instr = MEM[pc>>2];

imm = instr & caffff;
s = (instr >> sShift) & sMash;
t
d
shent

ToDo
0000 00ss ssst tttt dddd d000 0010 0000 //Convert
0000 0000 0000 0000 0000 0000 0010 0000 //to this
All letters replaced with Zeros

if ((instr && ADDmask) == ADDinstr) //instr is an ADD
{
  do Add instruction stuff
}
else if (instr is an AND)
{
  so AND instruction stuff
}
systemCall: exits program
}

ArrayList of the ADD, ADDI, ect... functions
For Loop through the ArrayList
if statement comparing at each index


int a,b;
int quotient = 0;
int bshift = b;
int bit = 1;
int rem=0;
while(bshift<rem) {
  bshift = bshift<<1;
  bit = bit <<1;
  while(bit!=0) {
    bshift = bdshift>>1;
    bit = bit >> 1;
    if(bshift<=rem) {
      rem=bshift;
      quotient += bit;
    }
  }
}

add $s1,$s2,$s3
add $s4,$s1,$s1

s1old -> MUX1
s1old -> MUX2
MUX1 && MUX2 -> ALU
ALU -> MUX1
ALU -> MUX2
ControLine -> MUX1


1. Fetch
2. Read Registers
3. Execute on ALU
4. Access data Memory
5. Write back to registers

/*

private long[] instructions = {
  00000000000000000000000000100000, //ADD
  00100000000000000000000000000000, //ADDI
  00100100000000000000000000000000, //ADDIU
  00000000000000000000000000100001, //ADDU
  00000000000000000000000000100100, //AND
  00110000000000000000000000000000, //ANDI
  00010000000000000000000000000000, //BEQ
  00000100000000010000000000000000, //BGEZ
  00000100000100010000000000000000, //BGEZAL
  00011100000000000000000000000000, //BGTZ
  00011000000000000000000000000000, //BLEZ
  00000100000000000000000000000000, //BLTZ
  00000100000100000000000000000000, //BLTZAL
  00010100000000000000000000000000, //BNE
  00000000000000000000000000011010, //DIV
  00001000000000000000000000000000, //J
  00001100000000000000000000000000, //JAL
  00000000000000000000000000001000, //JR
  10000000000000000000000000000000, //LB
  00111100000000000000000000000000, //LUI
  10001100000000000000000000000000, //LW
  00000000000000000000000000010000, //MFHI
  00000000000000000000000000010010, //MFLO
  00000000000000000000000000011000, //MULT
  00000000000000000000000000100101, //OR
  00110100000000000000000000000000, //ORI
  10100000000000000000000000000000, //SB
  00000000000000000000000000000000, //SLL
  00000000000000000000000000000100, //SLLV
  00000000000000000000000000101010, //SLT
  00101000000000000000000000000000, //SLTI
  00101100000000000000000000000000, //SLTIU
  00000000000000000000000000101011, //SLTU
  00000000000000000000000000000011, //SRA
  00000000000000000000000000000010, //SRL
  00000000000000000000000000000110, //SRLV
  00000000000000000000000000100010, //SUB
  00000000000000000000000000100011, //SUBU
  10101100000000000000000000000000, //SW
  10100000000000000000000000000000, //SB
  00000000000000000000000000001100, //SYSCALL
  00000000000000000000000000100110, //XOR
  00111000000000000000000000000000 //XORI
};
*/

/*

0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0, //ADD
0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //ADDI
0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //ADDIU
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1, //ADDU
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0, //AND
0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //ANDI
0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BEQ
0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BGEZ
0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BGEZAL
0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BGTZ
0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BLEZ
0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BLTZ
0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BLTZAL
0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //BNE
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0, //DIV
0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //J
0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //JAL
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0, //JR
1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //LB
0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //LUI
1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //LW
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0, //MFHI
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0, //MFLO
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0, //MULT
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1, //OR
0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //ORI
1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //SB
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //SLL
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0, //SLLV
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0, //SLT
0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //SLTI
0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //SLTIU
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1, //SLTU
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1, //SRA
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0, //SRL
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0, //SRLV
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0, //SUB
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1, //SUBU
1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //SW
1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //SB
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0, //SYSCALL
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0, //XOR
0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, //XORI

*/

/*
private String[] instructions = {
  "0000 0000 0000 0000 0000 0000 0010 0000", //ADD      index=0
  "0010 00ss ssst tttt iiii iiii iiii iiii", //ADDI
  "0010 01ss ssst tttt iiii iiii iiii iiii", //ADDIU
  "0000 00ss ssst tttt dddd d000 0010 0001", //ADDU
  "0000 00ss ssst tttt dddd d000 0010 0100", //AND      index=4
  "0011 00ss ssst tttt iiii iiii iiii iiii", //ANDI
  "0001 00ss ssst tttt iiii iiii iiii iiii", //BEQ
  "0000 01ss sss0 0001 iiii iiii iiii iiii", //BGEZ
  "0000 01ss sss1 0001 iiii iiii iiii iiii", //BGEZAL
  "0001 11ss sss0 0000 iiii iiii iiii iiii", //BGTZ      index=9
  "0001 10ss sss0 0000 iiii iiii iiii iiii", //BLEZ
  "0000 01ss sss0 0000 iiii iiii iiii iiii", //BLTZ
  "0000 01ss sss1 0000 iiii iiii iiii iiii", //BLTZAL
  "0001 01ss ssst tttt iiii iiii iiii iiii", //BNE
  "0000 00ss ssst tttt 0000 0000 0001 1010", //DIV       index=14
  "0000 10ii iiii iiii iiii iiii iiii iiii", //J
  "0000 11ii iiii iiii iiii iiii iiii iiii", //JAL
  "0000 00ss sss0 0000 0000 0000 0000 1000", //JR
  "1000 00ss ssst tttt iiii iiii iiii iiii", //LB
  "0011 11-- ---t tttt iiii iiii iiii iiii", //LUI      index=19
  "1000 11ss ssst tttt iiii iiii iiii iiii", //LW
  "0000 0000 0000 0000 dddd d000 0001 0000", //MFHI
  "0000 0000 0000 0000 dddd d000 0001 0010", //MFLO
  "0000 00ss ssst tttt 0000 0000 0001 1000", //MULT
  "0000 00ss ssst tttt dddd d000 0010 0101", //OR       index=24
  "0011 01ss ssst tttt iiii iiii iiii iiii", //ORI
  "1010 00ss ssst tttt iiii iiii iiii iiii", //SB
  "0000 00ss ssst tttt dddd dhhh hh00 0000", //SLL
  "0000 00ss ssst tttt dddd d--- --00 0100", //SLLV
  "0000 00ss ssst tttt dddd d000 0010 1010", //SLT      index=29
  "0010 10ss ssst tttt iiii iiii iiii iiii", //SLTI
  "0010 11ss ssst tttt iiii iiii iiii iiii", //SLTIU
  "0000 00ss ssst tttt dddd d000 0010 1011", //SLTU
  "0000 00-- ---t tttt dddd dhhh hh00 0011", //SRA
  "0000 00-- ---t tttt dddd dhhh hh00 0010", //SRL      index=34
  "0000 00ss ssst tttt dddd d000 0000 0110", //SRLV
  "0000 00ss ssst tttt dddd d000 0010 0010", //SUB
  "0000 00ss ssst tttt dddd d000 0010 0011", //SUBU
  "1010 11ss ssst tttt iiii iiii iiii iiii", //SW
  "1010 00ss ssst tttt iiii iiii iiii iiii", //SB      index=39
  "0000 00-- ---- ---- ---- ---- --00 1100", //SYSCALL
  "0000 00ss ssst tttt dddd d--- --10 0110", //XOR
  "0011 10ss ssst tttt iiii iiii iiii iiii" //XORI      index=42
};
*/
