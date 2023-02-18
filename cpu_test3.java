public class cpu_test3 {

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
        cpu_test2.runTests();
        System.out.println("\nCPU TESTS 3 / CALL, PUSH, POP, RETURN\n");
        runTests();
    }

    public static void runTests() throws Exception {
        computer testCPU = new computer();

        System.out.println("CPU TEST 1");
        String[] test1 = {
                "Call 6",
                "interrupt 0",
                "halt",
                "interrupt 1",
                "return"
        };
        String[] assembledCode = Assembler.assemble(test1);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 2");
        String[] test2 = {
                "move R1 3",
                "move R2 4",
                "push R1",
                "push R2",

                "pop R1",
                "pop R2",
                "add R1 R2 R3",
                "push R3",

                "interrupt 0",
                "interrupt 1"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test2);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 3");
        String[] test3 = {
                "move R0 3",
                "move R1 9",
                "call 12",
                "interrupt 0",
                "Halt",
                "move R15 -1",
                "interrupt 1",
                "return"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test3);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 4");
        String[] test4 = {
                "move R0 3",
                "move R1 9",
                "move R2 10",
                "call 16",
                "add R0 R1 R3",
                "add R1 R2 R4",
                "interrupt 0",
                "Halt",
                "move R15 -1",
                "return"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test4);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 5");
        String[] test5 = {
                "Call 8",
                "halt",
                "move R15 -1",
                "move R14 -1",
                "move R0 1",
                "move R1 2",
                "add R0 R1 R2",
                "interrupt 0"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test5);
        testCPU.preload(assembledCode);
        testCPU.run();

        System.out.println("\nCPU TEST 6");
        String[] test6 = {
                "move R0 1",
                "move R1 2",
                "push R1",
                "push R0",
                "move R3 8",
                "pop R15",
                "pop R14",
                "interrupt 0"
        };
        testCPU = new computer();
        assembledCode = Assembler.assemble(test6);
        testCPU.preload(assembledCode);
        testCPU.run();
    }
}
