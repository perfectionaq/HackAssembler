package com.company;


import java.util.ArrayList;
import java.util.List;

import static com.company.INSTRUCTION_TYPE.*;

// Take a symbol and gives back its machine instruction equivalent
final public class Code {

    //using symbol table to get the
    public SymbolTable symbolTable;

    public List<Instruction> getInstructionList() {
        return instructionList;
    }

    public void setInstructionList(List<Instruction> instructionList) {
        this.instructionList = instructionList;
    }

    private List<Instruction> instructionList;

    public Code(){
        symbolTable = new SymbolTable();
        instructionList = new ArrayList<>();
    }

    public String makeMachineInstruction(){
        StringBuilder builder = new StringBuilder();

        for(Instruction instruction: instructionList) {
            if(getInstructionType(instruction) == A_INSTRUCTION){
                builder.append(AInstruction(Integer.parseInt(instruction.getComp())))
                .append("\n");
            } else { // C-Instructions
                Instruction i = fixInstruction(instruction);
                int a = getCInstructionAType(i);

                builder.append("111")
                        .append("" + a)
                        .append(comp(i))
                        .append(dest(i))
                        .append(jmp(i))
                        .append("\n");


            }
        }
        return builder.toString();
    }

    private String bitRepresentation(int address){
        return Integer.toBinaryString(address);
    }

    private String fifteenBitRepresentation(int address){
        String addr = bitRepresentation(address);
        int neededBits = 15 - addr.length();
        String paddedString = "";

        for(int i = 0; i <  neededBits; i++){
            paddedString += "0";
        }
        paddedString += addr;

        return paddedString;
    }

    public String getAddress(int address){
        return fifteenBitRepresentation(address);
    }

    private INSTRUCTION_TYPE getInstructionType(Instruction instruction){
        if(instruction.getDest().equals("")){
            return A_INSTRUCTION;
        } else {
            return C_INSTRUCTION;
        }
    }

    private int getCInstructionAType(Instruction instruction){
        if(instruction.getComp().contains("M")) {
            return 1; // For C-Instruction containing M computations
        }
        return 0;
    }

    private String comp(Instruction instruction){
//        if(instruction.getComp() == null || instruction.getComp().equals("")){
//            return "000000";
//        } else {
            return symbolTable.getComp(instruction.getComp());
//        }
    }

    private String dest(Instruction instruction){
//        if(instruction.getDest() == null || instruction.getDest().equals("")){
//            return "000";
//        }
        return symbolTable.getDest(instruction.getDest());
    }

    private String jmp(Instruction instruction){
//        if(instruction.getJmp() == null || instruction.getJmp().equals("")){
//            return "000";
//        }
        return symbolTable.getJmp(instruction.getJmp());
    }

    private String AInstruction(int address){
        String addr = fifteenBitRepresentation(address);
        String instruction = "0" + addr;

        return instruction;
    }

    private Instruction fixInstruction(Instruction instruction){

        if(instruction.getComp().contains("J")){
            return new Instruction(null, instruction.getDest(), instruction.getComp());
        }

        return instruction;
    }
}
