// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed.
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
(REFRESH)
//starts at first pixel
@SCREEN
D=A
@addr
M=D

//tests keyboard conditions
@KBD
D=M
@WHITE
D;JEQ
@BLACK
0;JMP

(WHITE)
@value
M=0
@DRAW
0;JMP

(BLACK)
@value
M=-1
@DRAW
0;JMP

(DRAW)
//draws the pixel
@value
D=M
@addr
A=M
M=D

//goes to the next pixel
@addr
M=M+1

//checks if done
D=M
@KBD
D=A-D
@REFRESH
D;JEQ

//if not done, keep drawing
@DRAW
0;JMP
