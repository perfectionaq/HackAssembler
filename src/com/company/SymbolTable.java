package com.company;

import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.HashMap;
import java.util.Map;

final public class SymbolTable {

    private Map<String, String> symbolTable;
    private Map<String, String> compTable;
    private Map<String, String> destTable;
    private Map<String, String> jmpTable;

    SymbolTable(){
        symbolTable = new HashMap<>();
        compTable = new HashMap<>();
        destTable = new HashMap<>();
        jmpTable = new HashMap<>();

        //Adding all predefined assembly instructions and their corresponding machine instructions

        symbolTable.put(null, "000");

        //Comp instruction symbols in the C-Instructions
        compTable.put(null, "000");
        compTable.put("0", "101010");
        compTable.put("1", "111111");
        compTable.put("-1", "111010");
        compTable.put("D", "001100");
        compTable.put("A", "110000");
        compTable.put("!D", "001101");
        compTable.put("!A", "110001");
        compTable.put("-D", "001111");
        compTable.put("-A", "110011");
        compTable.put("D+1", "111101");
        compTable.put("A+1", "110111");
        compTable.put("D-1", "001110");
        compTable.put("A-1", "110010");
        compTable.put("D+A", "000010");
        compTable.put("D-A", "010011");
        compTable.put("A-D", "000111");
        compTable.put("D&A", "000000");
        compTable.put("D|A", "010101");
        compTable.put("M", "110000");
        compTable.put("!M", "110001");
        compTable.put("-M", "110011");
        compTable.put("M+1", "110111");
        compTable.put("M-1", "110010");
        compTable.put("D+M", "000010");
        compTable.put("D-M", "010011");
        compTable.put("M-D", "000111");
        compTable.put("D&M", "000000");
        compTable.put("D|M", "010101");




        //predefined symbols for addresses to be used in A-instructions
        symbolTable.put("R0", "0");
        symbolTable.put("R1", "1");
        symbolTable.put("R2", "2");
        symbolTable.put("R3", "3");
        symbolTable.put("R4", "4");
        symbolTable.put("R5", "5");
        symbolTable.put("R6", "6");
        symbolTable.put("R7", "7");
        symbolTable.put("R8", "8");
        symbolTable.put("R9", "9");
        symbolTable.put("R10", "10");
        symbolTable.put("R11", "11");
        symbolTable.put("R12", "12");
        symbolTable.put("R13", "13");
        symbolTable.put("R14", "14");
        symbolTable.put("R15", "15");

        //Destination instruction in the C-instruction
        destTable.put(null, "000");
        destTable.put("M", "001");
        destTable.put("D", "010");
        destTable.put("MD", "011");
        destTable.put("A", "100");
        destTable.put("AM", "101");
        destTable.put("AMD", "111");

        //Jump instructions
        jmpTable.put(null, "000");
        jmpTable.put("JGT", "001");
        jmpTable.put("JEQ", "010");
        jmpTable.put("JGE", "011");
        jmpTable.put("JLT", "100");
        jmpTable.put("JNE", "101");
        jmpTable.put("JLE", "110");
        jmpTable.put("JMP", "111");

        //Other Predefined symbols
        symbolTable.put("SP", "0");
        symbolTable.put("LCL", "1");
        symbolTable.put("ARG", "2");
        symbolTable.put("THIS", "3");
        symbolTable.put("THAT", "4");
        symbolTable.put("SCREEN", "16384"); //start address of the screen memory map
        symbolTable.put("KBD", "24576"); // address of the memory map of the keyboard
    }

    public boolean contains(String symbol){
        return symbolTable.containsKey(symbol);
    }

    public void addEntry(String symbol, String address){
        symbolTable.put(symbol, address);
    }

    public String getAddress(String symbol){
        return symbolTable.get(symbol);
    }
    public String getComp(String symbol){
        return compTable.get(symbol);
    }
    public String getDest(String symbol){
        return destTable.get(symbol);
    }
    public String getJmp(String symbol){
        return jmpTable.get(symbol);
    }
}
