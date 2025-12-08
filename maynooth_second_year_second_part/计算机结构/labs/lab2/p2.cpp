// MASM_FP.cpp : 定义控制台应用程序的入口点。
// 功能：Part 2——使用 x86 内联汇编，实现两个 unsigned short 的相加

#include "stdafx.h"
#include <stdio.h>

void test(void); // 函数原型

int _tmain(int argc, _TCHAR* argv[])
{
    test();
    return 0;
}

void test()
{
    unsigned short num1 = 0;
    unsigned short num2 = 0;
    unsigned short result = 0;

    // 从控制台输入两个数
    printf("请输入第一个数 (0~65535): ");
    scanf("%hu", &num1);
    printf("请输入第二个数 (0~65535): ");
    scanf("%hu", &num2);

    // 内联汇编：result = num1 + num2
    __asm
    {
        mov ax, num1    ; AX ← num1
        mov bx, num2    ; BX ← num2
        add ax, bx      ; AX = AX + BX
        mov result, ax  ; result ← AX
    }

    // 输出结果
    printf("两数之和为: %hu\n", result);

    // 等待用户按回车退出
    printf("按回车键退出...");
    // 清掉上一行 scanf 的残余换行
    while (getchar() != '\n');
    // 等待新的回车
    while (getchar() != '\n');
}