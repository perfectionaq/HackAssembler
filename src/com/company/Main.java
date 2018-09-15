package com.company;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Please provide a file name.");
            return;
        }

        Parser parser = new Parser(args[0]);
        Code code = new Code();
        ArrayList<Instruction> instructions = (ArrayList<Instruction>) parser.parse();
        printInstructions(instructions);
        code.setInstructionList(instructions);

        String machineCode = code.makeMachineInstruction();
        System.out.println(machineCode);
//        System.out.println(code.symbolTable.getAddress("D+A"));
    }

    static void printInstructions(ArrayList<Instruction> instructionList){
        for(Instruction instruction: instructionList){
            System.out.println(instruction.getDest() + " " + instruction.getComp() + " " + instruction.getJmp());
        }
    }
}
