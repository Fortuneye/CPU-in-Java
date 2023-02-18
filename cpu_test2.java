import java.util.Objects;

public class cpu_test2 {
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
        cpu_test1.runTests();
        System.out.println("\nASSEMBLER TESTS\n");
        assembler_test.runTests();
        System.out.println("\nCPU TESTS 2 / BRANCH, COMPARE, JUMP\n");
        runTests();
    }

    public static void runTests() throws Exception {

        String[] testAssembler = {
                "jump 4",
                "jump 10",
                "jump 78",
                "jump 434",
                "compare R4 R2",
                "compare R7 R13",
                "compare R15 R8",
                "branchIfEqual -12",
                "branchIfEqual 555",
                "branchIfEqual 111",
                "branchIfNotEqual -99",
                "branchIfNotEqual 889",
                "branchIfNotEqual 123",
                "branchIfGreaterThan -657",
                "branchIfGreaterThan 778",
                "branchIfGreaterThan -2",
                "branchIfGreaterThanOrEqual 4",
                "branchIfGreaterThanOrEqual 874",
                "branchIfGreaterThanOrEqual -22",
        };
        
        String[] testAssemblerBits = {
                "0011 0000 0000 0100",
                "0011 0000 0000 1010",
                "0011 0000 0100 1110",
                "0011 0001 1011 0010",
                "0100 0000 0100 0010",
                "0100 0000 0111 1101",
                "0100 0000 1111 1000",
                "0101 0111 1111 0100",
                "0101 0100 0010 1011",
                "0101 0100 0110 1111",
                "0101 0011 1001 1101",
                "0101 0001 0111 1001",
                "0101 0000 0111 1011",
                "0101 1011 0110 1111",
                "0101 1001 0000 1010",
                "0101 1011 1111 1110",
                "0101 1100 0000 0100",
                "0101 1101 0110 1010",
                "0101 1111 1110 1010",
        };

        System.out.println("TESTING NEW ASSEMBLY INSTRUCTIONS");

        String[] assemblyCode = Assembler.assemble(testAssembler);
        for(int i = 0; i < assemblyCode.length; i++){
            if(Objects.equals(assemblyCode[i], testAssemblerBits[i])){
                System.out.println("'" + testAssembler[i] + "'" + " = " + assemblyCode[i]);
            }else{
                System.out.println("ERROR: " + testAssembler[i] + " -> (" + assemblyCode[i] +  ") IS NOT " + "(" +  testAssemblerBits[i] + ")");
                throw new Exception("WRONG");
            }
        }
        System.out.println("PASSED NEW ASSEMBLY INSTRUCTIONS!\n");

        System.out.println("CPU TESTS:\n");

        computer testCPU = new computer();

        /* TESTING jump */

        System.out.println("Testing Jump: CPU TEST 1");
        String[] test1 = {
                "jump 4",
                "move R1 5",
                "interrupt 0",
                "halt",
        };
        String[] assembledCode = Assembler.assemble(test1);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 2");
        String[] test2 = {
                "interrupt 0",
                "jump 6",
                "move R1 5",
                "interrupt 0",
                "halt",
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test2);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 3");
        String[] test3 = {
                "move R1 55",
                "jump 6",
                "move R1 5",
                "interrupt 0",
                "jump 12",
                "move R2 84",
                "interrupt 0",
                "halt",
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test3);
        testCPU.preload(assembledCode);
        testCPU.run();

        /* TESTING branchIfGreaterThan */

        System.out.println("\nTesting branchIfGreaterThan: CPU TEST 4");
        String[] test4 = {
                "move R0 20",
                "move R1 30",
                "move R2 3",
                "compare R1 R0",
                "branchIfGreaterThan 2",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test4);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 5");
        String[] test5 = {
                "move R0 55",
                "move R1 5",
                "move R2 3",
                "compare R1 R0",
                "branchIfGreaterThan 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test5);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 6");
        String[] test6 = {
                "move R0 88",
                "move R1 100",
                "compare R1 R0",
                "branchIfGreaterThan 4",
                "move R0 -1",
                "move R1 -1",
                "interrupt 0",
                "add R1 R0 R3",
                "compare R3 R0",
                "branchIfGreaterThan 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test6);
        testCPU.preload(assembledCode);
        testCPU.run();

        /* TESTING branchIfGreaterThanOrEqual */

        System.out.println("\nTesting branchIfGreaterThanOrEqual: CPU TEST 7");
        String[] test7 = {
                "move R0 20",
                "move R1 20",
                "move R2 3",
                "compare R1 R0",
                "branchIfGreaterThanOrEqual 2",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test7);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 8");
        String[] test8 = {
                "move R0 55",
                "move R1 5",
                "move R2 3",
                "compare R1 R0",
                "branchIfGreaterThanOrEqual 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test8);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 9");
        String[] test9 = {
                "move R0 88",
                "move R1 100",
                "compare R1 R0",
                "branchIfGreaterThanOrEqual 4",
                "move R0 -1",
                "move R1 -1",
                "interrupt 0",
                "move R3 88",
                "compare R3 R0",
                "branchIfGreaterThanOrEqual 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test9);
        testCPU.preload(assembledCode);
        testCPU.run();

        /* TESTING branchIfEqual */

        System.out.println("\nTesting branchIfEqual: CPU TEST 10");
        String[] test10 = {
                "move R0 30",
                "move R1 30",
                "move R2 3",
                "compare R1 R0",
                "branchIfEqual 2",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test10);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 11");
        String[] test11 = {
                "move R0 654",
                "move R1 654",
                "move R2 8",
                "compare R1 R0",
                "branchIfEqual 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test11);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 12");
        String[] test12 = {
                "move R0 5",
                "move R1 5",
                "compare R1 R0",
                "branchIfEqual 4",
                "move R0 -1",
                "move R1 -1",
                "interrupt 0",
                "move R3 5",
                "compare R3 R0",
                "branchIfEqual 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test12);
        testCPU.preload(assembledCode);
        testCPU.run();

        /* TESTING branchIfNotEqual */

        System.out.println("\nTesting branchIfNotEqual: CPU TEST 13");
        String[] test13 = {
                "move R0 30",
                "move R1 9",
                "move R2 3",
                "compare R1 R0",
                "branchIfNotEqual 2",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test13);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 14");
        String[] test14 = {
                "move R0 654",
                "move R1 8",
                "move R2 8",
                "compare R1 R0",
                "branchIfNotEqual 4",
                "move R14 -1",
                "move R15 -1",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test14);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 15");
        String[] test15 = {
                "move R0 5",
                "move R1 90",
                "compare R1 R0",
                "branchIfNotEqual 4",
                "move R0 -1",
                "move R1 -2",
                "interrupt 0",
                "move R3 5",
                "compare R3 R0",
                "branchIfNotEqual 4",
                "move R14 -1",
                "move R15 -2",
                "interrupt 0",
                "halt"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test15);
        testCPU.preload(assembledCode);
        testCPU.run();
    }
}
