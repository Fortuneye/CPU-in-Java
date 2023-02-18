public class ALU_test {
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
        runTests();
    }

    static void runTests() throws Exception {
        longword testingLong1 = new longword(170);
        longword testingLong2 = new longword(204);
        longword testingLong3 = new longword(-5);
        longword testingLong4 = new longword(-654654);
        longword testingLong5 = new longword(798798749);
        longword testingLong6 = new longword(1);
        longword testingLong7 = new longword(-1);
        longword testingLong8 = new longword(0);
        bit bitTrue = new bit (true);
        bit bitFalse = new bit (false);
        bit[] and = {bitTrue, bitFalse, bitFalse, bitFalse}; //1000
        bit[] or = {bitTrue, bitFalse, bitFalse, bitTrue}; //1001
        bit[] xor = {bitTrue, bitFalse, bitTrue, bitFalse}; //1010
        bit[] not = {bitTrue, bitFalse, bitTrue, bitTrue}; //1011
        bit[] leftShift = {bitTrue, bitTrue, bitFalse, bitFalse}; //1100
        bit[] rightShift = {bitTrue, bitTrue, bitFalse, bitTrue}; //1101
        bit[] add = {bitTrue, bitTrue, bitTrue, bitFalse}; //1110
        bit[] subtract = {bitTrue, bitTrue, bitTrue, bitTrue}; //1111
        bit[] multiply = {bitFalse, bitTrue, bitTrue, bitTrue}; //0111
        longword expectedLong = new longword();
        longword actualLong = new longword();

        try {
            System.out.println("and");
            System.out.println("Expected = " + String.valueOf(136));
            expectedLong = new longword(136);
            actualLong = ALU.doOp(and, testingLong1, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-654654));
            expectedLong = new longword(-654654);
            actualLong = ALU.doOp(and, testingLong4, testingLong7);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(140));
            expectedLong = new longword(140);
            actualLong = ALU.doOp(and, testingLong2, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nor");
            System.out.println("Expected = " + String.valueOf(798798813));
            expectedLong = new longword(798798813);
            actualLong = ALU.doOp(or, testingLong2, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-5));
            expectedLong = new longword(-5);
            actualLong = ALU.doOp(or, testingLong4, testingLong3);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-1));
            expectedLong = new longword(-1);
            actualLong = ALU.doOp(or, testingLong1, testingLong7);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nxor");
            System.out.println("Expected = " + String.valueOf(-798798750));
            expectedLong = new longword(-798798750);
            actualLong = ALU.doOp(xor, testingLong7, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(102));
            expectedLong = new longword(102);
            actualLong = ALU.doOp(xor, testingLong2, testingLong1);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(654649));
            expectedLong = new longword(654649);
            actualLong = ALU.doOp(xor, testingLong3, testingLong4);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nnot");
            System.out.println("Expected = " + String.valueOf(-171));
            expectedLong = new longword(-171);
            actualLong = ALU.doOp(not, testingLong1, testingLong1);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-798798750));
            expectedLong = new longword(-798798750);
            actualLong = ALU.doOp(not, testingLong5, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(0));
            expectedLong = new longword(0);
            actualLong = ALU.doOp(not, testingLong7, testingLong1);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nleft-shifting");
            System.out.println("Expected = " + String.valueOf(408));
            expectedLong = new longword(408);
            actualLong = ALU.doOp(leftShift, testingLong2, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-1610612736));
            expectedLong = new longword(-1610612736);
            actualLong = ALU.doOp(leftShift, testingLong5, testingLong5); //lshift by 29
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-1024));
            expectedLong = new longword(-1024);
            actualLong = ALU.doOp(leftShift, testingLong7, testingLong1); //lshift by 10
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nright-shifting");
            System.out.println("Expected = " + String.valueOf(102));
            expectedLong = new longword(102);
            actualLong = ALU.doOp(rightShift, testingLong2, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(199699687));
            expectedLong = new longword(199699687);
            actualLong = ALU.doOp(rightShift, testingLong5, testingLong4); //rshift by 2
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(195019));
            expectedLong = new longword(195019);
            actualLong = ALU.doOp(rightShift, testingLong5, testingLong2); //rshift by 12
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nAdding");
            System.out.println("Expected = " + String.valueOf(170 + 204));
            expectedLong = new longword(374);
            actualLong = ALU.doOp(add, testingLong1, testingLong2);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-654654 + 798798749));
            expectedLong = new longword(798144095);
            actualLong = ALU.doOp(add, testingLong4, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-5 + 1));
            expectedLong = new longword(-4);
            actualLong = ALU.doOp(add, testingLong3, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nSubtracting");
            System.out.println("Expected = " + String.valueOf(170 - 204));
            expectedLong = new longword(-34);
            actualLong = ALU.doOp(subtract, testingLong1, testingLong2);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-654654 - 798798749));
            expectedLong = new longword(-799453403);
            actualLong = ALU.doOp(subtract, testingLong4, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-5 - 1));
            expectedLong = new longword(-6);
            actualLong = ALU.doOp(subtract, testingLong3, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("\nMultiplying");
            System.out.println("Expected = " + String.valueOf(170 * 204));
            expectedLong = new longword(34680);
            actualLong = ALU.doOp(multiply, testingLong1, testingLong2);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-654654 * -1));
            expectedLong = new longword(654654);
            actualLong = ALU.doOp(multiply, testingLong4, testingLong7);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-5 * 0));
            expectedLong = new longword(0);
            actualLong = ALU.doOp(multiply, testingLong3, testingLong8);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(1 * 204));
            expectedLong = new longword(204);
            actualLong = ALU.doOp(multiply, testingLong6, testingLong2);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            } else {
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

    }
}
