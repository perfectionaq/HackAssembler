package com.company;

enum INSTRUCTION_TYPE{
    A_INSTRUCTION, C_INSTRUCTION, L_INSTRUCTION;
}

final public class Instruction {
    private String dest;
    private String comp;
    private String jmp;
    private INSTRUCTION_TYPE type;

    Instruction(String dest, String comp, String jmp){
        this.dest = dest;
        this.comp = comp;
        this.jmp = jmp;
        //this.type = type;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getJmp() {
        return jmp;
    }

    public void setJmp(String jmp) {
        this.jmp = jmp;
    }
}
