// MASM_FP.cpp : Defines the entry point for the console application. 
#include "stdafx.h" 
#include <stdio.h> 
 
void test(void); // Function prototype (description) 
int _tmain(int argc, _TCHAR* argv[]) 
{ 
 test(); 
return 0; 
} 
 
// Put our unmanaged asm code in here 
void test() 
{ 
unsigned short num1; 
unsigned short num2; 
unsigned short result; 
 
 printf("Enter first number: "); 
scanf(" %hd",&num1); 
printf("Enter second number: "); 
scanf(" %hd",&num2); 
 
__asm 
{ 
 mov ax, num1 ; Direct addressing to access num1 
mov bx, num2 ; put value in num2 into bx 
add ax, bx ; ax=ax+bx 
mov result,ax; ; put value into result 
} 
 
 printf("%hd",result); // Display result 
 
// Wait for enter to be pressed before terminating 
while(getchar()!=10); // Clear buffer of previous <ret> 
while(getchar()!=10); // Wait for a new <ret> 
}