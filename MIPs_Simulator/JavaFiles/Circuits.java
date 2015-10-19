/*
  Curcuits
  - Alu 1,4,32 bit
  - AND
  - Full Adder
  - Multiplexer 2,4 bit //UNFORSURE ON 2
  - OR
  - XOR
*/
public class Circuits {
  /*
    ALU 1bit
    returns cout,s0
  */
  public int[] ALU1BIT(int f1,int f0, int inva,int invb,int cin,int a0,int b0) {
    int tempXor0 = XOR(a0,inva);
    int tempXor1 = XOR(b0,invb);

    int tempAnd0 = AND(tempXor0,tempXor1);
    int tempOr0 = OR(tempXor0,tempXor1);
    int tempXor2 = XOR(tempXor0,tempXor1);

    int tempFullAdder[] = FULLADDER(tempXor0,tempXor1,cin);
    int tempMux = MULTIPLEXER4BIT(tempFullAdder[1],tempAnd0,tempOr0,tempXor2,f1,f0);

    //return cout, s0
    int[] returnVal = {tempFullAdder[0],tempMux};
    return returnVal;
  }
  /*
    ALU 4bit
    returns s3,s2,s1,s0,cout
  */
  public int[] ALU4BIT(int f1,int f0, int inva,int invb,int cin,int a3,int a2,int a1,int a0,int b3,int b2,int b1,int b0) {
    int[] tempAlu0 = ALU1BIT(f1,f0,inva,invb,cin,a0,b0);
    int[] tempAlu1 = ALU1BIT(f1,f0,inva,invb,tempAlu0[0],a1,b1);
    int[] tempAlu2 = ALU1BIT(f1,f0,inva,invb,tempAlu1[0],a2,b2);
    int[] tempAlu3 = ALU1BIT(f1,f0,inva,invb,tempAlu2[0],a3,b3);
    //return s3,s2,s1,s0,cout
    int[] returnVal = {tempAlu3[1],tempAlu2[1],tempAlu1[1],tempAlu0[1],tempAlu3[0]};
    return returnVal;
  }
  /*
    ALU 32bit
    returns 32bit number,cout
  */
  public int[] ALU32BIT (int f1,int f0, int inva,int invb, int cin, int[] a,int[] b) {
    int[] tempAlu0 = ALU4BIT(f1,f0,inva,invb,cin,a[3],a[2],a[1],a[0],b[3],b[2],b[1],b[0]);
    int[] tempAlu1 = ALU4BIT(f1,f0,inva,invb,tempAlu0[4],a[7],a[6],a[5],a[4],b[7],b[6],b[5],b[4]);
    int[] tempAlu2 = ALU4BIT(f1,f0,inva,invb,tempAlu1[4],a[11],a[10],a[9],a[8],b[11],b[10],b[9],b[8]);
    int[] tempAlu3 = ALU4BIT(f1,f0,inva,invb,tempAlu2[4],a[15],a[14],a[13],a[12],b[15],b[14],b[13],b[12]);
    int[] tempAlu4 = ALU4BIT(f1,f0,inva,invb,tempAlu3[4],a[19],a[18],a[17],a[16],b[19],b[18],b[17],b[16]);
    int[] tempAlu5 = ALU4BIT(f1,f0,inva,invb,tempAlu4[4],a[23],a[22],a[21],a[20],b[23],b[22],b[21],b[20]);
    int[] tempAlu6 = ALU4BIT(f1,f0,inva,invb,tempAlu5[4],a[27],a[26],a[25],a[24],b[27],b[26],b[25],b[24]);
    int[] tempAlu7 = ALU4BIT(f1,f0,inva,invb,tempAlu6[4],a[31],a[30],a[29],a[28],b[31],b[30],b[29],b[28]);
    //returns 32bit number,cout
    int[] returnVal = {
      tempAlu7[3],tempAlu7[2],tempAlu7[1],tempAlu7[0],
      tempAlu6[3],tempAlu6[2],tempAlu6[1],tempAlu6[0],
      tempAlu5[3],tempAlu5[2],tempAlu5[1],tempAlu5[0],
      tempAlu4[3],tempAlu4[2],tempAlu4[1],tempAlu4[0],
      tempAlu3[3],tempAlu3[2],tempAlu3[1],tempAlu3[0],
      tempAlu2[3],tempAlu2[2],tempAlu2[1],tempAlu2[0],
      tempAlu1[3],tempAlu1[2],tempAlu1[1],tempAlu1[0],
      tempAlu0[3],tempAlu0[2],tempAlu0[1],tempAlu0[0],
      tempAlu7[4]
    };
    return returnVal;
  }
  /*
    AND gate
    if: contains 0 return 0
    else: return 1
  */
  public int AND(int a,int b) {
    return a*b;
  }
  public int[] AND(int[] a,int[] b) {
    int[] returnVal = new int[32];
    for (int i=0;i<32;i++) {
      returnVal[i] = a[i]*b[i];
    }
    return returnVal;
  }
  /*
    Full Adder
    returns carry,sum;
  */
  public int[] FULLADDER(int a,int b,int c) {
    int tempAnd0 = AND(a,b); //feeds into tempOr0
    int tempAnd1 = AND(a,c); //feeds into tempOr0
    int tempAnd2 = AND(b,c); //feeds into tempOr1
    //Carry
    int tempOr0 = OR(tempAnd0,tempAnd1); //feeds into tempOr1
    int tempOr1 = OR(tempAnd2,tempOr0); // Carry Output
    //Sum
    int tempXor0 = XOR(a,b); //feeds into tempXor1
    int tempXor1 = XOR(c,tempXor0); //Sum Output
    //return Carry & Sum
    int[] returnVal = {tempOr1,tempXor1};
    return returnVal;
  }
  /*
    Multiplexer 2bit
    UNFORSURE ON BUILD
  */
  public int MULTIPLEXER2BIT(int a,int b,int s0) {
    int nots0;
    if (s0==0) nots0=1; else nots0=0; //~s0

    int tempAnd0 = AND(nots0,a);
    int tempAnd1 = AND(s0,b);

    int tempOr0 = OR(tempAnd0,tempAnd1);

    return tempOr0;
  }
  /*
    Multiplexer 4bit
  */
  public int MULTIPLEXER4BIT(int a,int b,int c,int d,int s1,int s0) {
    int nots1,nots0;
    if (s1==0) nots1=1; else nots1=0; //~s1
    if (s0==0) nots0=1; else nots0=0; //~s0

    int tempAnd0 = AND(nots1,nots0);
    int tempAnd1 = AND(tempAnd0,a); //Feeds into tempOr0
    int tempAnd2 = AND(nots1,s0);
    int tempAnd3 = AND(tempAnd2,b); //Feeds into tempOr0
    int tempAnd4 = AND(s1,nots0);
    int tempAnd5 = AND(tempAnd4,c); //Feeds into tempOr1
    int tempAnd6 = AND(s1,s0);
    int tempAnd7 = AND(tempAnd6,d); //Feeds into tempOr1

    int tempOr0 = OR(tempAnd1,tempAnd3);
    int tempOr1 = OR(tempAnd5,tempAnd7);
    int tempOr2 = OR(tempOr0,tempOr1);

    return tempOr2;
  }
  /*
    OR gate
    if: contains 1 return 1
    else: return 0
  */
  public int OR(int a,int b) {
    if (a==1 || b==1) return 1;
    else return 0;
  }
  /*
    XOR gate
    if: Both inputs are different return 1;
    else: return 0;
  */
  public int XOR(int a,int b) {
    if (a!=b) return 1;
    else return 0;
  }
}
