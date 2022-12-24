#include <stdio.h>
#include <string.h>
#include <assert.h>	

int exponential(int numb, int exponent);
int iterativeConversion(char binary[]);
int bitwiseConversion(char binary[]);
void unittests(); 

/* 
    A program that converts 2's compliment
    binary integer representation into an integer
*/
int main(int argc, char *argv[]) {
    for (int i = 1; i < argc; i++) {
        // each argument is a 2's complement bit pattern 
        //printf("iterative %20s: %10d\n", argv[i], iterativeConversion(argv[i]));
        //printf("bitwise   %20s: %10d\n", argv[i], bitwiseConversion(argv[i]));
        printf("%20s: %10d\n", argv[i], bitwiseConversion(argv[i]));
    }

    unittests();
    return 0;
}


int bitwiseConversion(char binary[]) { 
    int len = strlen(binary);
    int finalValue;
    int negative;

    // setting the integer bits to all 0 or all 1
    if (binary[0] == '1') {
        finalValue = -1; // 1111 1111 1111 ...
        negative = 1;
    } else if (binary[0] == '0') { 
        finalValue = 0; // 0000 0000 0000 ...
        negative = 0;
    }

    for (int i = len; i >= 0; i--) {
        if (negative == 1) {
            // if the 2's complement is negative, toggle the 0 bits
            if (binary[i] == '0') { 
                finalValue = finalValue ^ (1 << (len - i -1));
            } 
        } else if (negative == 0) { 
            // if the 2's complement is positive, toggle the 1 bits
            if (binary[i] == '1') { 
                finalValue = finalValue ^ (1 << (len - i -1));
            }
        }
        
    }

    return finalValue;
}


/*
    An iterative approach for converting 2's complement binary to integer

    binary: binary sequence in the format of 2's compliment
*/
int iterativeConversion(char binary[]) { 
    int bitLength = strlen(binary);
    char twosComp[bitLength];
    char onesComp[bitLength];
    strcpy(twosComp, binary);

    int signMultiplier = 0;
    int valueAcc = 0;
    int finalValue = 0;
    
    if (twosComp[0] == '1') { // if msb indicates negative 
        signMultiplier = -1;

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
    } else if (twosComp[0] == '0') { // if msb indicates positive
        signMultiplier = 1;
        strcpy(onesComp, twosComp);
    }

    // adding the value of each bit
    for (int k = bitLength - 1; k >= 0; k--) { 
        valueAcc += (((int) onesComp[k] - '0') * exponential(2, (bitLength - k - 1)));
    }

    finalValue = valueAcc * signMultiplier;
    return finalValue;
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



void unittests() { 
    // Testing iterative function
    assert(iterativeConversion("00000000") == 0);
    assert(iterativeConversion("00000001") == 1);
    assert(iterativeConversion("11111111") == -1);
    assert(iterativeConversion("00000010") == 2);
    assert(iterativeConversion("11111110") == -2);
    assert(iterativeConversion("00110000") == 48);
    assert(iterativeConversion("11010000") == -48);
    assert(iterativeConversion("0011000000011000") == 12312);
    assert(iterativeConversion("1100111111101000") == -12312);

    // Testing bitwise function
    assert(bitwiseConversion("00000000") == 0);
    assert(bitwiseConversion("00000001") == 1);
    assert(bitwiseConversion("11111111") == -1);
    assert(bitwiseConversion("00000010") == 2);
    assert(bitwiseConversion("11111110") == -2);
    assert(bitwiseConversion("00110000") == 48);
    assert(bitwiseConversion("11010000") == -48);
    assert(bitwiseConversion("0011000000011000") == 12312);
    assert(bitwiseConversion("1100111111101000") == -12312);
}