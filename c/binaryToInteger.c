#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int exponential(int numb, int exponent);
int iterativeConversion(char binary[]);
int bitwiseConversion(char binary[]);

/* 
    A program that converts 2's compliment
    binary integer representation into an integer
*/
int main(int argc, char *argv[]) {
    char twosComp[] = "11111110";
    int bitLength = strlen(twosComp);
    char onesComp[bitLength];

    int signMultiplier = 0;
    int valueAcc = 0;
    int finalValue = 0;

    // checking msb to see if the integer is negative
    if (twosComp[0] == '1') { 
        signMultiplier = -1;
    } else { 
        signMultiplier = 1;
    }

    // flipping the bits to find 1's compliment
    for (int i = 0; i < bitLength; i++) { 
        if (twosComp[i] == '1') { 
            onesComp[i] = '0';
        } else if (twosComp[i] == '0') { 
            onesComp[i] = '1';
        }
    }

    // adding 1 to find the original int
    if (onesComp[bitLength] == '0') { 
        onesComp[bitLength] = '1';
    } else {
        for (int j = bitLength - 1; j >= 0; j--) { 
            if (onesComp[j] == '1') { 
                onesComp[j] = '0';
            } else if (onesComp[j] == '0') { 
                onesComp[j] = '1';
                break;
            }
        }
    }

    for (int k = bitLength - 1; k >= 0; k--) { 
        valueAcc += (((int) onesComp[k] - '0') * exponential(2, (bitLength - k - 1)));
    }
    printf("acc value: %d\n", valueAcc);
    finalValue = valueAcc * signMultiplier;
    printf("final value: %d", finalValue);
}

/* 
    A exponential function to avoid importing all of math.h
*/
int exponential(int numb, int exponent) {
    int acc = 1;
    for (int i = 0; i < exponent; i++) { 
        acc *= numb;
    }

    return acc;
}