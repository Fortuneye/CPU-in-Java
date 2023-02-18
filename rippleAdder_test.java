import java.net.StandardSocketOptions;

public class rippleAdder_test {
    public static void main(String args[]) throws Exception {
        System.out.println("RIPPLEADDER TESTS:\n");
        runTests();
        System.out.println("LONGWORD TESTS:\n");
        longword_test.runTests();
        System.out.println("BIT TESTS\n");
        bit_test.runTests();
    }

    static void runTests() throws Exception {
        longword testingLong1 = new longword(50);
        longword testingLong2 = new longword(-545);
        longword testingLong3 = new longword(54968);
        longword testingLong4 = new longword(1);
        longword testingLong5 = new longword(-1);
        longword testingLong6 = new longword(0);
        longword testingLong7 = new longword(-65465455);
        longword expectedLong = new longword();
        longword actualLong = new longword();

        try{
            System.out.println("TESTING ADD");
            System.out.println("Expected = " + String.valueOf(1 + 0));
            expectedLong = new longword(1);
            actualLong = rippleAdder.add(testingLong4, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(1 + 1));
            expectedLong = new longword(2);
            actualLong = rippleAdder.add(testingLong4, testingLong4);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(54968 + 50));
            expectedLong = new longword(55018);
            actualLong = rippleAdder.add(testingLong3, testingLong1);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf((-545) + (-65465455)));
            expectedLong = new longword(-65466000);
            actualLong = rippleAdder.add(testingLong2, testingLong7);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(0 + 0));
            expectedLong = new longword(0);
            actualLong = rippleAdder.add(testingLong6, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf((-65465455) + 54968));
            expectedLong = new longword(-65410487);
            actualLong = rippleAdder.add(testingLong7, testingLong3);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }

        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("TESTING SUBTRACT");
            System.out.println("Expected = " + String.valueOf((-65465455) - 54968));
            expectedLong = new longword(-65520423);
            actualLong = rippleAdder.subtract(testingLong7, testingLong3);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf((-545) - 1));
            expectedLong = new longword(-546);
            actualLong = rippleAdder.subtract(testingLong2, testingLong5);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(0 - 0));
            expectedLong = new longword(0);
            actualLong = rippleAdder.subtract(testingLong6, testingLong6);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(1 - 1));
            expectedLong = new longword(0);
            actualLong = rippleAdder.subtract(testingLong4, testingLong4);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(54968 - 50));
            expectedLong = new longword(54918);
            actualLong = rippleAdder.subtract(testingLong3, testingLong1);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }
            System.out.println("Expected = " + String.valueOf(54968 - 1));
            expectedLong = new longword(54967);
            actualLong = rippleAdder.subtract(testingLong3, testingLong4);
            System.out.println("ACTUAL = " + actualLong.getSigned());
            if(expectedLong.toString().equals(actualLong.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }

        }catch(Exception e){
            System.out.println("FAILED\n");
        }
    }
}
