//Best way of wtiring mips program
//Write program in C, then convert mips

int main(int argc, char *argv[]) {

  char *s[1000];
  char *t = malloc(1000); //Fancy version of what "char *s" does

  printf("enter a String: ");
  fgets(s,1000,stdin); //read input from keyboard
  reverseString(s,t);

  printf("t");
  exit(0);

}
//index: 0 1 2 3 4  5 ....
//   s = h e l l o \0 .....
//   t = o l l e h \0 .....
void reverseString(char *s, char *t) {

  int length = stringLength(s);
  int i;

  for (i=0;i<length;i++) {
    t[i] = s[length-i-1];
  }
  t[length] = '\0';

}

//Get length of char(string)
int stringLength(char *s) {

  int i=0;

  while (s[i]!='\0') {
    i++
  }

  return i;

}
