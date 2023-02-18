public class multiplier_test {
    public static void main(String args[]) throws Exception {
        System.out.println("BIT TESTS\n");
        bit_test.runTests();
        System.out.println("LONGWORD TESTS:\n");
        longword_test.runTests();
        System.out.println("RIPPLEADDER TESTS:\n");
        rippleAdder_test.runTests();
        System.out.println("MULTIPLIER TESTS\n");
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
        longword expectedLong = new longword();
        longword actualLong = new longword();

        try {

            System.out.println("TESTING MULTIPLY");
            System.out.println("Expected = " + String.valueOf(170 * 204));
            expectedLong = new longword(34680);
            actualLong = multiplier.multiply(testingLong1, testingLong2);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(798798749 * -1));
            expectedLong = new longword(-798798749);
            actualLong = multiplier.multiply(testingLong5, testingLong7);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(-654654 * -5));
            expectedLong = new longword(3273270);
            actualLong = multiplier.multiply(testingLong4, testingLong3);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(1 * 0));
            expectedLong = new longword(0);
            actualLong = multiplier.multiply(testingLong6, testingLong8);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if (expectedLong.toString().equals(actualLong.toString()) == false) {
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(1 * 204));
            expectedLong = new longword(204);
            actualLong = multiplier.multiply(testingLong6, testingLong2);
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
