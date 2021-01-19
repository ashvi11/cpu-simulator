
This design is for CSCI6461 project1.

By: Kaibo Zhang, Xinlong Han, Ashvi Soni

# Introduction
This a Java application that allows user to run machine language and display on user interface. It can realize five functions, which are ”LDR”, “STR”, “LDA”, “LDX” and “STX”, by receiving code from user input panel. The explanation of each instruction code and how to use this program will explain in following part. 

# Simulator Layout
![image](https://github.com/Johnkb-git/cpu_simulator/blob/master/img/1.png)

Part 1: this part shows every memory address and corresponding value. Total memory address are 2048 and user can drag on the right side or use mouse wheel to shift.

Part 2: this part shows register status after user input instruction. It includes PC, MAR, MBR, IR, CC, MFR, four general register and three index register.

Part 3: this part is user input panel. User can input instruction code here and click button to observe different status of each register and memory.

Part 4: this part has five buttons listed from left to right and their function is 1. step run instruction code in input panel, 2. run all instruction code in input panel, 3. read code from file, 4. reset program and 5. exit program.

There are only three buttons that functional for project 1, which is run button(second), reset button(fourth) and exit button(fifth). Other buttons are for future develop and if user click it, it will have no response.

# Instruction Code
The input panel can ONLY receive those five instructions code listed in the following. If user input other that those five instructions code or not following syntax, this program will throw exception.

1.	LDR r, x, address [, I] (ldr r, x, address [, I])

Read content from memory address and load it to register r. x means which index register you want use. You can write a capitalized “I” in the end of this code and it’s means index indirect addressing. E.g. Without “I”, r1c(EA). if code with “I”, r c(c(EA)).

Example: LDR 1, 0, 10

It can read as: load register 1 with contents of memory location 10 and add with index register x.

2.	STR r, x, address [, I] (str r, x, address [, I])

Store register r content to memory address. x means which index register you want use. “I” has same function as LDR explained.

Example: STR 1, 0, 10

It can read as: store content in register 1 to memory address 10. 

3.	LDA r, x, address [, I] (lda r, x, address [, I])
4.	LDX r, x, address [, I] (LDX r, x, address [, I])
5.	STX r, x, address [, I] (STX r, x, address [, I])

# Input Formats
By input panel: Directly input instruction code listed above.

Syntax tips:
1.	Leave one space between function name and parameter.
2.	Using comma and one space to separate each input parameter, such as: register 1, register 2, address 3.
3.	To run multiple instruction code, use semicolon for each complete instruction code. 
For example:
STR 1, 0, 10;
LDR 2, 3, 10;
LDA 3, 2, 30;

By input file: TBD. We are not yet ready to implement this function for project 1.
