

#include "stdafx.h"
#include <stdio.h>

void test(void); /

int _tmain(int argc, _TCHAR* argv[])
{
    test();
    return 0;
}

void test()
{
    //
    union mmx_word {
        unsigned char byte[8];
        unsigned __int64 value;
    };

   
    mmx_word NUM1 = { 0,1,2,3,4,5,6,7 };
    mmx_word NUM2 = { 1,1,1,1,1,1,1,1 };

    __asm {
        //
        movq mm0, NUM1
        movq mm1, NUM2
  
        paddb mm0, mm1
      
        movq NUM1, mm0
        
        emms
    }

    
    printf("Result bytes: ")
    for (int i = 0; i < 8; i++) {
        printf("%u", (unsigned int)NUM1.byte[i]);
        if (i < 7) printf(",");
    }
    printf("\n");

    // 等待回车退出
    while (getchar() != '\n');
    while (getchar() != '\n');
}