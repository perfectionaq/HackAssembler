package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private File assemblyFile;
    private Scanner fileReader;
    List<Instruction> instructionList;

    private final int NUM_OF_INSTRUCTION_PARTS = 3;

    Parser(String fileName){
        instructionList = new ArrayList<>();
        assemblyFile = new File(fileName);

        try{
            fileReader = new Scanner(assemblyFile);
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    List<Instruction> parse(){
        while(hasMoreCommands()){
            String filteredCommand = commentFilter(fetchNextCommand().replaceAll("\\s+", ""));
            if(filteredCommand.length() <= 0 || isComment(filteredCommand))
                continue;


            String[] instructionParts = filteredCommand.split("=|@|;");
            Instruction currentInstruction = makeInstruction(instructionParts, filteredCommand.contains("="));
            instructionList.add(currentInstruction);
        }
        return instructionList;
    }

    boolean hasMoreCommands(){
        return fileReader.hasNextLine();
    }

    String fetchNextCommand(){
        return fileReader.nextLine();
    }

    boolean isComment(String instruction){
        return instruction.charAt(0) == '/';
    }

    // removes comments from instructions
    String commentFilter(String instruction){
        if(instruction.indexOf('/') == -1)
            return instruction;

        return instruction.substring(0, instruction.indexOf('/'));
    }

    void printInstructions(){
        for(Instruction instruction: instructionList){
            System.out.println(instruction.getDest() + " " + instruction.getComp() + " " + instruction.getJmp());
        }
    }

    Instruction makeInstruction(String[] instructionParts, boolean hasDest){
        int instructionPartsLength = instructionParts.length;
        int numberOfMissingParts = NUM_OF_INSTRUCTION_PARTS - instructionPartsLength;

        //used to make sure
        String[] paddedParts = new String[3];

        //Fill in the given parts
        for(int i = 0; i < instructionPartsLength; i++){
            paddedParts[i] = instructionParts[i];
        }

        //Pad the rest of the parts with empty strings
        for(int i = instructionPartsLength; i < paddedParts.length; i++) {
            paddedParts[i] = null;
        }

        return new Instruction(paddedParts[0], paddedParts[1], paddedParts[2]);
    }
}
