#include "stdafx.h" // 如果项目需要，保留。否则可以移除。
                    // 较新版本的 Visual Studio 可能使用 pch.h 或不需要预编译头文件。
#include <stdio.h>  // 用于 printf 和 getchar

// 根据你的 Visual Studio 版本和项目设置，
// _tmain 和 _TCHAR 可能需要调整为标准 main 和 char。
// 例如：int main(int argc, char* argv[])
int _tmain(int argc, _TCHAR* argv[])
{
    register int total = 0; //
    // 使用 for 循环计算 0 到 20 的和
    for (register int i = 0; i <= 20; i++) // 声明寄存器整型变量 i 作为循环计数器
    {
        total += i; // 将 i 的值累加到 total
    }

    printf("%d\n", total); // 
                          // 

    getchar(); // 

    return 0; 
}