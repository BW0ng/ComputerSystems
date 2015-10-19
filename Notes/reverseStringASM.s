<<<<<<< Updated upstream
# Topher Comment = comment Prof Cline did NOT provide in his code...
=======
# Topher Comment = Comment Prof Cline did NOT but in his code...
>>>>>>> Stashed changes
#Steps
# First: Write C code
# Second: Comment out C code
# Third: After Commented code's location, write MIPs code
# Fourth: Celebrate

#-----------------------------------------------------
# data segment - declare global data

.data
    prompt:   asciiz    "Enter a String: "

#-----------------------------------------------------
# text  segment - the instructions of the program

.text

#int main(int argc, char *argv[]) {
#
#  char *s[1000];
#  char *t = malloc(1000);
#
#  printf("enter a String: ");
#  fgets(s,1000,stdin);
#  reverseString(s,t);
#
#  printf("t");
#  exit(0);
#
#}

# Topher Comment: translate line by line
main:
  # $s0 = s
  # $s1 = t

  # char *s = malloc(1000);
  #
  li $v0, 9  # Topher Comment: load imm
  li $a0, 1000
  syscall
  move $s0, $v0 # Topher Comment: Copying $v0 to $s0

  # char *t = malloc(1000);
  #
  li $v0, 9
  li $a0, 1000
  syscall
  move $s1, $v0 # Topher Comment: same thing except stored in $s1

  # printf("enter a String: ");
  #
  li $v0, 4
  la $a0, prompt
  syscall

  # fgets(s,1000,stdin);
  #
  li $v0, 8
  move $a0, $s0
  li $a1, 1000
  syscall

  # reverseString(s,t);
  #
  addi $sp, $sp, -4 # make space on stack
  sw $ra, 0($sp) # push $ra on stack

  move $a0, $s0 # copy parameters to $a registers
  move $a1, $s1
  jal reverseString  # actually make the call

  nop
  low $ra 0($sp) # pop $ra from stack
  addi $sp, $sp, 4

  # printf("t");
  #
  li $v0, 4
  move $a0, $s1 # Topher Comment: We don't care about $s0 cause we are printing $s1 only
  syscall

  # exit(0);
  #
  li $v0, 10
  syscall

#-----------------------------------------------------

#void reverseString(char *s, char *t) {
#
#  int length = stringLength(s);
#  int i;
#
#  for (i=0;i<length;i++) {
#    t[i] = s[length-i-1];
#  }
#  t[length] = '\0';
#
#}

reverseString:
  # s = $a0
  # t = $a1
  # length = $t0
  # i = $t1
  # &t[i] = $t2             # Topher Comment: & = address
  # &s[length-i-1] = $t3    # Topher Comment: Address of the thing I want
  # s[length-i-1] = $t4     # Topher Comment: Thing I want
  # &t[length] = $t5

  # int length = stringLength(s);
  # save $a0, $a1, $ra, on stack
  addi $sp, $sp, $ -12
  sw $a0, -8($sp)         # Topher Comment: Can do 'this' backwards
  sw $a1, -4($sp)
  sw $ra, 0($sp)          # Topher Comment: End if 'This'
  move $a0, $a0
  jal stringLength
  nop
  lw $a0, -8($sp)
  lw $a1, -4($sp)
  lw $ra, 0($sp)
  addi $sp, $sp, 12
  move  $t0, $v0

  #  for (i=0;i<length;i++) {
  #
  li $t1, 0       # i = 0
forReverse:
  bge $t1, $t0, afterForReverse
  nop
    # t[i] = s[length-i-1];

    sub $t3, $t0, $t1   # &s[length-i-1]
    addi $t3, $t3, -1
    sll $t3, $t3, 2
    lw $t4, 0($t3)      # s[length-i-1]

    sll $t2, $t1, 2     # i*4    &t[i]
    add $t2, $t2, $a1
    sb %t4, 0($t3)


  addi $t1, $t1, 1      # i++
  j forReverse
  nop
afterForReverse:

  # t[length] = '\0'
  #
  sll $t5, $t0, 2
  add $t5, $t5, $a1
  sb $zero, 0($t5)

  # return from function
  jr $ra
  nop


#-----------------------------------------------------

#int stringLength(char *s) {
#
#  int i=0;
#
#  while (s[i]!='\0') {
#    i++
#  }
#
#  return i;
#
#}

#DID NOT GET TO/RUN OUT OF CLASS TIME
