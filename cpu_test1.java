public class cpu_test1 {

    public static void main(String args[]) throws Exception {
        System.out.println("BIT TESTS\n");
        bit_test.runTests();
        System.out.println("LONGWORD TESTS:\n");
        longword_test.runTests();
        System.out.println("RIPPLEADDER TESTS:\n");
        rippleAdder_test.runTests();
        System.out.println("MULTIPLIER TESTS\n");
        multiplier_test.runTests();
        System.out.println("ALU TESTS\n");
        ALU_test.runTests();
        System.out.println("MEMORY TESTS\n");
        memory_test.runTests();
        System.out.println("\nCPU TESTS\n");
        runTests();
    }

    static void runTests() throws Exception {
        computer cpu = new computer();
        String[] preloaded = new String[]{
                "0010 0000 0000 0000", "0010 0000 0000 0001", //interrupts
                "0001 0000 0000 1010", "0001 0001 0101 0000",  //move 10 to reg0  0001 0000 0000 1010, move 80 to reg1  0001 0001 0101 0000
                "0001 0010 0000 0110", "0001 0011 0000 01111", //move 6 to reg2  0001 0010 0000 0110, move 7 to reg3  0001 0011 0000 01111
                "0001 0100 0000 0110", "0001 0101 0101 0000", //move 6 to reg4  0001 0100 0000 0110, move 80 to reg5  0001 0101 0101 0000
                "0001 0110 0100 0000", "1110 0111 0001 0111", //move 64 to reg6  0001 0110 0100 0000, add reg0 and reg1 and set to reg7  1110 0111 0001 0111
                "1110 0101 0011 1000", "1111 0110 0010 1001", //add reg5 and reg3 and set to reg8  1110 0101 0011 1000, subtract reg6 and reg2 and set to reg9  1111 0110 0010 1001
                "1111 0011 0010 1010", "1000 0000 0100 1011", //subtract reg3 and reg2 and set to reg10  1111 0011 0010 1010, logical AND reg0 and reg4 and set to reg11 1000 0000 0100 1011
                "1001 1001 0010 1100", "1010 0110 0101 1101", //logical OR reg9 and reg2 and set to reg12  1001 1001 0010 1100, logical XOR reg6 and reg5 and set to reg13 1010 0110 0101 1101
                "1011 0011 0011 1110", "1100 1100 1000 1111", //logical NOT reg3 and set to reg14  1011 0011 0011 1110, le0t shift reg12 by reg8 and set to reg15 1100 1100 1000 1111
                "1101 1111 1010 0000", "0111 0000 0100 0010", //right shift reg15 by reg10 and set to reg0 1101 1111 1010 0000, multiply reg0 and reg4 and set to reg2 0111 0000 0100 0010
                "0010 0000 0000 0000", "0010 0000 0000 0001", //interrupts
                "0000 0000 0000 0000" //halt
        };

        cpu.preload(preloaded);
        cpu.run();
    }

}
