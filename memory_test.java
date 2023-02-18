public class memory_test {

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
        runTests();
    }

    static void runTests() throws Exception {
        memory cpuMem = new memory();
        int j = 0;
        int x = 0;

        try {
            System.out.println("Initialized Memory = ");
            for (int i = 0; i < 500; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");

            longword testingLongword = new longword(-50);
            System.out.println("\nbits to be inserted: " + testingLongword.toString() + "or -50");
            System.out.println("Setting to address 0:");
            longword memIndex = new longword(0);
            cpuMem.write(memIndex, testingLongword);
            System.out.println("New Memory = ");
            for (int i = 0; i < 100; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");
            longword returnedWord = cpuMem.read(memIndex);
            System.out.println("Reading memory back from address from before:");
            System.out.println(returnedWord.toString());
            if (!testingLongword.toString().equals(returnedWord.toString())) {
                throw new Exception();
            }

            testingLongword = new longword(1000);
            System.out.println("\nbits to be inserted: " + testingLongword.toString() + "or " + testingLongword.getSigned());
            memIndex = new longword(500);
            System.out.println("Setting to address " + memIndex.getUnsigned() * 8 + ":");
            cpuMem.write(memIndex, testingLongword);
            System.out.println("New Memory (starting at index 500) = ");
            System.out.print("...");
            for (int i = 4000; i < 4100; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");
            returnedWord = cpuMem.read(memIndex);
            System.out.println("Reading memory back from address from before:");
            System.out.println(returnedWord.toString());
            if (!testingLongword.toString().equals(returnedWord.toString())) {
                throw new Exception();
            }

            testingLongword = new longword(-3145);
            System.out.println("\nbits to be inserted: " + testingLongword.toString() + "or " + testingLongword.getSigned());
            memIndex = new longword(1000);
            System.out.println("Setting to address " + memIndex.getUnsigned() * 8 + ":");
            cpuMem.write(memIndex, testingLongword);
            System.out.println("New Memory = ");
            for (int i = 8000; i < 8192; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");
            returnedWord = cpuMem.read(memIndex);
            System.out.println("Reading memory back from address from before:");
            System.out.println(returnedWord.toString());
            if (!testingLongword.toString().equals(returnedWord.toString())) {
                throw new Exception();
            }

            testingLongword = new longword(526);
            System.out.println("\nbits to be inserted: " + testingLongword.toString() + "or " + testingLongword.getSigned());
            memIndex = new longword(2);
            System.out.println("Setting to address " + memIndex.getUnsigned() * 8 + ":");
            cpuMem.write(memIndex, testingLongword);
            System.out.println("New Memory = ");
            for (int i = 0; i < 100; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");
            returnedWord = cpuMem.read(memIndex);
            System.out.println("Reading memory back from address from before:");
            System.out.println(returnedWord.toString());
            if (!testingLongword.toString().equals(returnedWord.toString())) {
                throw new Exception();
            }

            testingLongword = new longword(-5465445);
            System.out.println("\nbits to be inserted: " + testingLongword.toString() + "or " + testingLongword.getSigned());
            memIndex = new longword(1000);
            System.out.println("Setting to address " + memIndex.getUnsigned() * 8 + ":");
            cpuMem.write(memIndex, testingLongword);
            System.out.println("New Memory (starting at index 8000) = ");
            System.out.print("... \n");
            for (int i = 8000; i < 8192; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");
            returnedWord = cpuMem.read(memIndex);
            System.out.println("Reading memory back from address from before:");
            System.out.println(returnedWord.toString());
            if (!testingLongword.toString().equals(returnedWord.toString())) {
                throw new Exception();
            }

            testingLongword = new longword(-5465445);
            System.out.println("\nbits to be inserted: " + testingLongword.toString() + "or " + testingLongword.getSigned());
            memIndex = new longword(1000);
            System.out.println("Setting to address " + memIndex.getUnsigned() * 8 + ":");
            cpuMem.write(memIndex, testingLongword);
            System.out.println("New Memory (starting at index 8000) = ");
            System.out.print("... \n");
            for (int i = 8000; i < 8192; i++) {
                System.out.print(cpuMem.computerMemory[i]);
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
            j = 0;
            x = 0;
            System.out.print("... \n");
            returnedWord = cpuMem.read(memIndex);
            System.out.println("Reading memory back from address from before:");
            System.out.println(returnedWord.toString());
            if (!testingLongword.toString().equals(returnedWord.toString())) {
                throw new Exception();
            }

        }catch(Exception e){
            System.out.println("FAILED");
        }
    }
}
