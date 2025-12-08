#include "stdafx.h"
#include <cstdio>

// Function Prototype
void print_binary(char*, unsigned int);

// Main function with the solution for Challenge 3
int _tmain(int argc, _TCHAR* argv[])
{
    unsigned int x;
    do {
        printf("Enter a number (or 0 to exit): ");
        scanf("%u", &x);
        if (x == 0) break;

        print_binary("Input:  ", x);

        // --- Start: Solution for Challenge 3 ---
        // This loop iterates through the bits of the input 'x'
        // and builds a new number 'reversed_x' with the bits in reverse order.
        unsigned int reversed_x = 0;
        for (int i = 0; i < 32; ++i) {
            if ((x >> i) & 1) {
                reversed_x |= (1 << (31 - i));
            }
        }
        x = reversed_x;
        // --- End: Solution for Challenge 3 ---

        print_binary("Output: ", x);
        printf("\n"); // Add a blank line for readability

    } while (1);

    return 0;
}

// Function Definition for print_binary
void print_binary(char text[], unsigned int x)
{
    printf("%s", text);
    for (int i = 31; i >= 0; i--)
    {
        if (((x >> i) & 1) == 0) printf("0"); else printf("1");
    }
    printf("\n");
}
