#include "stdafx.h" 
#include <stdio.h> 
 
void test(void); // Function prototype (description) 
int _tmain(int argc, _TCHAR* argv[]) 
{ 
test(); 
return 0; 
} 
 
void test() 
{ 
 float A=2,C=0; 
 unsigned short cntrl=0x3FF,stat; 
 __asm 
 { 
 FINIT 
 FLDCW cntrl ; Round even, Mask Interrupts 
 FLD A ; Push SX onto FP stack 
 
 FSTSW stat ; Load FPU status into [stat] 
 FSTP C ; Copy result from stack into HY 
 } 
 
// Binary representation of the 4 bytes, (32 bits) coding HY 
printf("Binary:"); 
unsigned char byt; 
for(int x=3;x>=0;x--) 
{ 
 byt=*((unsigned char *)&C+x); 
 for(int y=128;y>0;y/=2) 
 { 
 if ((y&byt)==0) printf("0"); else printf("1"); 
 } 
} 
 
// Decimal format 
printf("\nDecimal: %3.0f",C); 
 
// Hex format 
printf("\nHex:"); 
for(int x=3;x>=0;x--) 
{ 
 byt=*((unsigned char *)&C+x); 
 
 printf("%x",(unsigned int)byt); 
} 
 
// Decimal 4 byte format 
printf("\nDecimal (4bytes):"); 
for(int x=3;x>=0;x--) 
{ 
 byt=*((unsigned char *)&C+x); 
 printf("%d,",(unsigned int)byt); 
} 
// 
while(getchar()!=10); 
while(getchar()!=10); 
} 