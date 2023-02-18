import java.util.Objects;

public class computer {
    private memory cpuMem = new memory(); //computer's memory
    private bit isRunningBit = new bit(false); //bit to check if code is running
    private longword PC = new longword(0); //program counter
    private longword[] registerArray = new longword[16]; //our registers
    private longword currentInstruction = new longword(0); //longword to store CURRENT longword in fetch
    private longword op1 = new longword(0);
    private longword op2 = new longword(0);
    private longword resultLocation = new longword(0); //longword to store the location of where result should go
    private longword result = new longword(0);
    private bit[] opcode = new bit[4]; //bit array to store operand command of instruction
    private longword value = new longword(0); //longword to store the value when doing move
    private bit signOfAddress = new bit(false); //store the sign of address when branching
    private boolean branchCheck = false; //boolean to inform store() method that branch was successful
    private longword stackPointer = new longword(1020); //stack pointer variable (initialized with 1020 bytes)

    //two bits for the compare command
    //lessOrGreater == true -> greater than
    //lessOrGreater == false -> less than
    //equalOrNotEqual == true -> equal
    //equalOrNotEqual == true -> not equal
    private bit lessOrGreater = new bit(false);
    private bit equalOrNotEqual = new bit(false);

    public computer() throws Exception {
    }

    void preload(String[] preloadBits) throws Exception {
        longword storingLong = new longword(); //longword we are building
        bit bitFalse = new bit(false); //variable for false
        bit bitTrue = new bit(true); //variable for true
        int x = 0; //COUNTER TO CHECK IF WE HAVE FILLED A LONGWORD WITH 2 INSTRUCTIONS
        int memCount = 0; //COUNTER FOR READING TO THE RIGHT PLACE IN MEMORY
        int j = 0; //COUNTER TO ITERATE THE ENTIRE INSTRUCTION
        int q = 0; //COUNTER TO ITERATE THE ENTIRE LONGWORD

        for(int i = 0; i < preloadBits.length; i++) {
            q = 0;
                while (q < preloadBits[i].length()) {
                    if (preloadBits[i].charAt(q) == '0') {
                        storingLong.setBit(j, bitFalse);
                        j++;

                    } else if(preloadBits[i].charAt(q) == '1'){
                        storingLong.setBit(j, bitTrue);
                        j++;

                    }else{}
                    q++;

                    if(j == 16 || j == 32){
                        x++;
                    }

                    if (x == 2) {
                        cpuMem.write(new longword(memCount), storingLong);
                        memCount = memCount + 4;
                        storingLong = new longword();
                        x = 0;
                        j = 0;
                    }
                }
        }
        this.isRunningBit.set(true); //when we are done pre-loading, we need to set our computer to be ready to run
    }

    void run() throws Exception {
        while(this.isRunningBit.getValue() == true){ //loop for as long as the running bit is is true
            fetch();
            decode();
            execute();
            store();
        }
    }

    private void fetch() throws Exception {
        this.currentInstruction = cpuMem.read(this.PC);
        this.PC = rippleAdder.add(this.PC, new longword(2));
    }

    private void decode() throws Exception {
        longword opShiftandMask = currentInstruction; //store a variable to manipulate to get our instructions

        //instead of using a bit mask, another way to get OP1 and OP2 is to just shift
        //since our shifts allow us to replace 1's for 0's if we move our bits the right way

        //get operand
        for(int i = 0; i < 4; i++){
            this.opcode[i] = opShiftandMask.getBit(i);
        }

        //if we are halted
        if(opcode[0].getValue() == false && opcode[1].getValue() == false && opcode[2].getValue() == false && opcode[3].getValue() == false){
            this.isRunningBit.set(false); //set running bit as stopped

            //if we are performing a move operation
        }else if(opcode[0].getValue() == false && opcode[1].getValue() == false && opcode[2].getValue() == false && opcode[3].getValue() == true){

            opShiftandMask = currentInstruction; //reset the instruction variable

            //get op1
            opShiftandMask = opShiftandMask.leftShift(4);
            opShiftandMask = opShiftandMask.rightShift(28);
            this.op1 = opShiftandMask;

            opShiftandMask = currentInstruction; //reset the instruction variable

            //get value
            opShiftandMask = opShiftandMask.leftShift(8);
            opShiftandMask = opShiftandMask.rightShift(24);
            this.value = opShiftandMask;

        }else {

            opShiftandMask = currentInstruction; //reset the instruction variable

            //get op1
            opShiftandMask = opShiftandMask.leftShift(4);
            opShiftandMask = opShiftandMask.rightShift(28);
            this.op1 = opShiftandMask;

            opShiftandMask = currentInstruction; //reset the instruction variable

            //get op2
            opShiftandMask = opShiftandMask.leftShift(8);
            opShiftandMask = opShiftandMask.rightShift(28);
            this.op2 = opShiftandMask;

            opShiftandMask = currentInstruction; //reset the instruction variable

            //get location to send result
            opShiftandMask = opShiftandMask.leftShift(12);
            opShiftandMask = opShiftandMask.rightShift(28);
            this.resultLocation = opShiftandMask;
        }
    }

    private void execute() throws Exception {

        //if we are performing a move
        if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
                this.opcode[2].getValue() == false && this.opcode[3].getValue() == true){

            //leave empty. There is no calculating to be done when moving, so we just want
            //to go straight to the store() method

            //if we're pushing/popping/calling/returning
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == true &&
                this.opcode[2].getValue() == true && this.opcode[3].getValue() == false){

            ///////////////////////////////

            //PUSH OPERATION
            if(this.op1.getBit(28).getValue() == false && this.op1.getBit(29).getValue() == false){

                //WHAT WE WANT:
                //put val inside register into stack location
                //subtract from stack

                //get the index of the register we want
                longword branchAddress = new longword(); //create new longword that will hold the register

                //put in our register address in the new longword
                for(int i = 28; i < 32; i++){
                    branchAddress.setBit(i, this.resultLocation.getBit(i));
                }

                int o = branchAddress.getSigned();
                int o1 = this.stackPointer.getSigned();
                int o2 = this.stackPointer.getSigned() * 8;
                int o3 = this.PC.getSigned();

                //add the register data in the correct spot in our stack
                int x = 0;
                for(int i = this.stackPointer.getSigned() * 8; i < this.stackPointer.getSigned() * 8 + 32; i++){
                    this.cpuMem.computerMemory[this.stackPointer.getSigned() * 8 + x] = registerArray[branchAddress.getSigned()].getBit(x);
                    x++;
                }

                longword t = new longword(); //create new longword that will hold the register

                //put in our register address in the new longword
                x = 0;
                for(int i = 0; i < 32; i++){
                    t.setBit(i, this.cpuMem.computerMemory[this.stackPointer.getSigned() * 8 + i]);
                }

                int o5 = t.getSigned();
                o5 = t.getSigned();

                //move the stack up
                this.stackPointer = rippleAdder.subtract(this.stackPointer, new longword(4));

                o1 = this.stackPointer.getSigned();
                o1 = this.stackPointer.getSigned();
                int o4 = registerArray[branchAddress.getSigned()].getSigned();
                o4 = registerArray[branchAddress.getSigned()].getSigned();
                o3 = this.PC.getSigned();
                o3 = this.PC.getSigned();

                //POP OPERATION
            }else if(this.op1.getBit(28).getValue() == false && this.op1.getBit(29).getValue() == true){

                //move the stack back down
                this.stackPointer = rippleAdder.add(this.stackPointer, new longword(4));

                //hold our stack pointer address
                longword stackAddr = this.stackPointer;

                //create a new longword to store the popped data from the stack
                longword poppedLocation = new longword();

                //what we're popping is the value of our pushed register, so take it from memory
                for(int i = 0; i < 32; i++){
                    poppedLocation.setBit(i, this.cpuMem.computerMemory[stackAddr.getSigned() * 8 + i]);
                }

                int o = poppedLocation.getSigned();
                int o1 = this.stackPointer.getSigned();

                //go to the popped register and save the register we're passing in into it
                this.registerArray[this.resultLocation.getSigned()] = poppedLocation;

                //clear out the spot in memory where our stack address used to be
                for(int i = 0; i < 32; i++){
                    this.cpuMem.computerMemory[this.stackPointer.getSigned() * 8 + i] = new bit(false);
                }

                o1 = this.stackPointer.getSigned();
                o1 = this.stackPointer.getSigned();
                int o3 = this.PC.getSigned();
                o3 = this.PC.getSigned();

                //CALL OPERATION
            }else if(this.op1.getBit(28).getValue() == true && this.op1.getBit(29).getValue() == false){

                //WHAT WE WANT:
                //put PC into stack location
                //change PC to jump address

                //put PC into stack location
                for(int i = 0; i < 32; i++){
                    this.cpuMem.computerMemory[this.stackPointer.getSigned() * 8 + i] = this.PC.getBit(i);
                }

                //get the address we want
                longword branchAddress = new longword(); //create new longword that will be the new amount of bytes to jump
                int x = 0;
                int y = 28;
                int z = 28;

                    while(x < 23){
                        branchAddress.setBit(x, new bit(false));
                        x++;
                    }

                branchAddress.setBit(x, this.op1.getBit(31)); //add the extra part of the address to our new longword

                //fill up the rest of the longword
                for(int i = 24; i < 32; i++){
                    if(y < 32){
                        branchAddress.setBit(i, this.op2.getBit(y));
                        y++;
                    }else{
                        branchAddress.setBit(i, this.resultLocation.getBit(z));
                        z++;
                    }
                }

                //set the PC as the new address (JUMP TO THE NEW ADDRESS!)
                this.PC = branchAddress;

                //move the stack up
                this.stackPointer = rippleAdder.subtract(this.stackPointer, new longword(4));

                int o3 = this.PC.getSigned();
                o3 = this.PC.getSigned();

                //RETURN OPERATION
            }else{

                //WHAT WE WANT:
                //get address from stack pointer (get PC back)
                //clear stack pointer
                //add to stack pointer to "pop"

                //move the stack down
                this.stackPointer = rippleAdder.add(this.stackPointer, new longword(4));

                //save stack pointer info
                longword stackAddr = this.stackPointer;
                int o1 = this.stackPointer.getSigned();

                //get the new location to jump to (address from the stack)
                longword poppedLocation = new longword();
                for(int i = 0; i < 32; i++){
                    poppedLocation.setBit(i, this.cpuMem.computerMemory[stackAddr.getSigned() * 8 + i]);
                }

                //jump to new location
                this.PC = poppedLocation;

                //clear the stack location
                for(int i = 0; i < 32; i++){
                    this.cpuMem.computerMemory[this.stackPointer.getSigned() * 8 + i] = new bit(false);
                }

                o1 = this.stackPointer.getSigned();
                int o3 = this.PC.getSigned();
                o3 = this.PC.getSigned();
            }

            //if we're jumping
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
                this.opcode[2].getValue() == true && this.opcode[3].getValue() == true){

            //leave empty. jumping occurs in the store() method

            //if we're comparing
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == true &&
                this.opcode[2].getValue() == false && this.opcode[3].getValue() == false){

            longword subtractedLong = rippleAdder.subtract(this.registerArray[this.op2.getSigned()], this.registerArray[this.resultLocation.getSigned()]);

            //based on our numbers, set the two bits accordingly
            //if the subtract resulted in a 0, that means theyre equal
            if(subtractedLong.toString().equals(new longword(0).toString())){
                this.lessOrGreater.set(false);
                this.equalOrNotEqual.set(true);

                //if the subtracted long results in a negative, that means register 1 is less than register 2
            }else if(subtractedLong.getBit(0).getValue() == true){
                this.lessOrGreater.set(false);
                this.equalOrNotEqual.set(false);

                //if the subtracted long is neither negative nor 0, that means reg1 is greater than reg2
            }else{
                this.lessOrGreater.set(true);
                this.equalOrNotEqual.set(false);
            }

            //if we're branching
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == true &&
                this.opcode[2].getValue() == false && this.opcode[3].getValue() == true){

            //[10] == greater than
            //[10] or [01] == greater than or equal to
            //[01] == equal
            //[00] == not equal
            bit[] conditionCode = new bit[2];
            conditionCode[0] = this.op1.getBit(28);
            conditionCode[1] = this.op1.getBit(29);

            this.signOfAddress = this.op1.getBit(2);

            //check if we want greater than
            if(conditionCode[0].getValue() == true && conditionCode[1].getValue() == false){
                //check our comparison bits
                if(this.lessOrGreater.getValue() == true && this.equalOrNotEqual.getValue() == false){
                    this.branchCheck = true;
                }
                //check if we want greater than or equal to
            }else if(conditionCode[0].getValue() == true && conditionCode[1].getValue() == true){
                //check our comparison bits
                if(this.lessOrGreater.getValue() == true || this.equalOrNotEqual.getValue() == true){
                    this.branchCheck = true;
                }
                //check if we want equal
            }else if(conditionCode[0].getValue() == false && conditionCode[1].getValue() == true){
                //check our comparison bits
                if(this.equalOrNotEqual.getValue() == true){
                    this.branchCheck = true;
                }
                //check if we want not equal
            }else{
                //check our comparison bits
                if(this.equalOrNotEqual.getValue() == false){
                    this.branchCheck = true;
                }
            }

        }else {
            this.result = ALU.doOp(opcode, this.op1, this.op2);
        }
    }

    private void store() throws Exception {

        //if we are moving
        if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
                this.opcode[2].getValue() == false && this.opcode[3].getValue() == true){

            //get the index of the register we want to put our value into
            int memIndex = op1.getSigned();
            this.registerArray[memIndex] = this.value; //insert our value there

            //if we're jumping
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
                    this.opcode[2].getValue() == true && this.opcode[3].getValue() == true){

            longword jumpAddress = new longword();
            int x = 28;
            int y = 28;
            int z = 28;
            int w = 0;

            //get the index of the register we want to go to
            for(int i = 16; i < 32; i++){
                if(w < 4){
                    jumpAddress.setBit(i, new bit(false));
                    w++;
                }else if(x < 32){
                    jumpAddress.setBit(i, this.op1.getBit(x));
                    x++;
                }else if(y < 32){
                    jumpAddress.setBit(i, this.op2.getBit(y));
                    y++;
                }else if(z < 32){
                    jumpAddress.setBit(i, this.resultLocation.getBit(z));
                    z++;
                }
            }

            //set the PC as the new address
            this.PC = jumpAddress;

            //if we're branching
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == true &&
                this.opcode[2].getValue() == false && this.opcode[3].getValue() == true){

            //if the branch was correct
            if(this.branchCheck == true){
                longword branchAddress = new longword(); //create new longword that will be the new amount of bytes to jump
                int x = 0;
                int y = 28;
                int z = 28;

                if(this.signOfAddress.getValue() == true) { //if we're sign extending

                    while (x < 23) { //fill the new address with 1s/trues in the space leading up to the given address
                        branchAddress.setBit(x, new bit(true));
                        x++;
                    }
                }else { //if we arent sign extending, just do the above, but replace the leadup 1s with 0s
                    while(x < 23){
                        branchAddress.setBit(x, new bit(false));
                        x++;
                    }
                }

                    branchAddress.setBit(x, this.op1.getBit(31)); //add the extra part of the address to our new longword

                    //fill up the rest of the longword
                    for(int i = 24; i < 32; i++){
                        if(y < 32){
                            branchAddress.setBit(i, this.op2.getBit(y));
                            y++;
                        }else{
                            branchAddress.setBit(i, this.resultLocation.getBit(z));
                            z++;
                        }
                    }

                this.branchCheck = false;

                //change the PC with our new address
                this.PC = rippleAdder.add(this.PC, branchAddress);
            }


            //if we're comparing
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == true &&
                this.opcode[2].getValue() == false && this.opcode[3].getValue() == false){


            //interrupt for displaying memory
        }else if((this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
            this.opcode[2].getValue() == true && this.opcode[3].getValue() == false)
            && (this.resultLocation.getBit(28).getValue() == false && this.resultLocation.getBit(29).getValue() == false
                && this.resultLocation.getBit(30).getValue() == false && this.resultLocation.getBit(31).getValue() == true)) {

            int j = 0;
            int x = 0;
                for (int i = 0; i < this.cpuMem.computerMemory.length; i++) {
                    System.out.print(this.cpuMem.computerMemory[i]);
                    j++;
                    if(j == 4){
                        System.out.print(" ");
                        x++;
                        j = 0;
                    }
                    if(x == 8){
                        System.out.println();
                        x = 0;
                    }
                }
            //interrupt for displaying registers
        }else if((this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
                this.opcode[2].getValue() == true && this.opcode[3].getValue() == false)
                && (this.resultLocation.getBit(28).getValue() == false && this.resultLocation.getBit(29).getValue() == false
                && this.resultLocation.getBit(30).getValue() == false && this.resultLocation.getBit(31).getValue() == false)) {

            for (int i = 0; i < this.registerArray.length; i++) {
                System.out.println(this.registerArray[i]);
            }

            //if we are halting (opcode = 0000)
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == false &&
                    this.opcode[2].getValue() == false && this.opcode[3].getValue() == false){

            this.isRunningBit.set(false); //set running bit as stopped

            //if we did any call/return/push/pop
        }else if(this.opcode[0].getValue() == false && this.opcode[1].getValue() == true &&
                this.opcode[2].getValue() == true && this.opcode[3].getValue() == false){

            //do nothing!

        }else{
            //get the index of the register we want to put our value into
            int memIndex = this.resultLocation.getSigned();
            this.registerArray[memIndex] = this.result; //insert our value there
        }
    }

}
