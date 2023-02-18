import java.util.Objects;

public class assembler_test {

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
        runTests();
    }

    public static void runTests() throws Exception {

        String[] assemblyInstructions = {
                "move R1 -1",
                "move R2 10",
                "move R3 38",
                "add R3 R2 R4",
                "add R4 R1 R5",
                "add R1 R2 R6",
                "subtract R2 R5 R7",
                "subtract R5 R3 R0",
                "subtract R3 R2 R4",
                "multiply R3 R2 R4",
                "multiply R5 R13 R7",
                "multiply R0 R1 R2",
                "and R9 R1 R10",
                "and R10 R4 R8",
                "and R3 R2 R9",
                "or R1 R2 R15",
                "or R15 R2 R4",
                "or R5 R6 R13",
                "xor R13 R2 R4",
                "xor R7 R6 R5",
                "xor R3 R10 R11",
                "not R11 R2 R12",
                "not R13 R0 R1",
                "not R1 R2 R4",
                "rightshift R9 R0 R3",
                "rightshift R7 R3 R14",
                "rightshift R8 R5 R15",
                "leftshift R13 R14 R15",
                "leftshift R10 R9 R8",
                "leftshift R3 R2 R4",
                "interrupt 0",
                "interrupt 1",
                "jump 20",
                "jump 522",
                "jump 44",
                "halt"
        };

        String[] actualAssembly = {
                "0001 0001 1111 1111",
                "0001 0010 0000 1010",
                "0001 0011 0010 0110",
                "1110 0011 0010 0100",
                "1110 0100 0001 0101",
                "1110 0001 0010 0110",
                "1111 0010 0101 0111",
                "1111 0101 0011 0000",
                "1111 0011 0010 0100",
                "0111 0011 0010 0100",
                "0111 0101 1101 0111",
                "0111 0000 0001 0010",
                "1000 1001 0001 1010",
                "1000 1010 0100 1000",
                "1000 0011 0010 1001",
                "1001 0001 0010 1111",
                "1001 1111 0010 0100",
                "1001 0101 0110 1101",
                "1010 1101 0010 0100",
                "1010 0111 0110 0101",
                "1010 0011 1010 1011",
                "1011 1011 0010 1100",
                "1011 1101 0000 0001",
                "1011 0001 0010 0100",
                "1101 1001 0000 0011",
                "1101 0111 0011 1110",
                "1101 1000 0101 1111",
                "1100 1101 1110 1111",
                "1100 1010 1001 1000",
                "1100 0011 0010 0100",
                "0010 0000 0000 0000",
                "0010 0000 0000 0001",
                "0011 0000 0001 0100",
                "0011 0010 0000 1010",
                "0011 0000 0010 1100",
                "0000 0000 0000 0000"

        };
        String[] assemblyCode = Assembler.assemble(assemblyInstructions);

        for(int i = 0; i < assemblyCode.length; i++){
            if(Objects.equals(assemblyCode[i], actualAssembly[i])){
                System.out.println("'" + assemblyInstructions[i] + "'" + " = " + assemblyCode[i]);
            }else{
                System.out.println("ERROR: " + assemblyInstructions[i] + " -> " + assemblyCode[i] +  " IS NOT " + assemblyCode[i]);
                throw new Exception("WRONG");
            }
        }
        System.out.println("PASSED!");
    }

}
