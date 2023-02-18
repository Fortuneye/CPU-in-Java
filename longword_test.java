public class longword_test {

    public static void main(String args[]) throws Exception {

        System.out.println("LONGWORD TESTS:\n");
        runTests();
        System.out.println("BIT TESTS:\n");
        bit_test.runTests();
    }

    static void runTests() throws Exception {
        bit bitFalse = new bit(false);
        bit bitTrue = new bit(true);
        longword longwordTester1 = new longword(70);
        longword longwordTester2 = new longword(-98454);
        longword longwordTester3 = new longword(2132165654);
        longword longwordTester4 = new longword(-87984546);
        longword longwordTester5 = new longword(0);
        longword longwordTester6 = new longword(1);
        longword longwordTester7 = new longword(-1);
        longword longBitwiseTemp = new longword();

        System.out.println("Longword tester 1 value: 70 (536870982 after testing setBit(i, value))");
        System.out.println("Longword tester 2 value: -98454");
        System.out.println("Longword tester 3 value: 2132165654");
        System.out.println("Longword tester 4 value: -87984546");
        System.out.println("Longword tester 5 value: 0");
        System.out.println("Longword tester 6 value: 1");
        System.out.println("Longword tester 7 value: -1 (-3 after testing setBit(i, value)\n");

        try{
            System.out.println("Testing method 'toString()' and 'void set()'");
            System.out.println("Expected value: f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, " +
                    "f, f, f, t, f, f, f, t, t, f, or 70");
            System.out.println("Actual value: " + longwordTester1.toString());
            if(longwordTester1.toString().equals("f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, " +
                    "f, f, f, t, f, f, f, t, t, f, ") == false){
                throw new Exception();
            }
            System.out.println("Expected value: t, t, t, t, t, f, t, f, t, t, f, f, f, f, f, t, f, t, t, t, f, t, t, f, " +
                    "f, t, f, t, t, t, t, f, or -87984546");
            System.out.println("Actual value: " + longwordTester4.toString());
            if(longwordTester4.toString().equals("t, t, t, t, t, f, t, f, t, t, f, f, f, f, f, t, f, t, t, t, f, t, t, " +
                    "f, f, t, f, t, t, t, t, f, ") == false){
                throw new Exception();
            }
            System.out.println("Expected value: f, t, t, t, t, t, t, t, f, f, f, t, f, t, t, f, f, t, f, f, f, t, f, " +
                    "f, f, f, f, t, f, t, t, f,  or 2132165654");
            System.out.println("Actual value: " + longwordTester3.toString());
            if(longwordTester3.toString().equals("f, t, t, t, t, t, t, t, f, f, f, t, f, t, t, f, f, t, f, f, f, t, f," +
                    " f, f, f, f, t, f, t, t, f, ") == false){
                throw new Exception();
            }
            System.out.println("Expected value: t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, f, t, t, t, t, t, t, " +
                    "t, f, t, t, f, t, f, t, f, or -98454");
            System.out.println("Actual value: " + longwordTester2.toString());
            if(longwordTester2.toString().equals("t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, f, t, t, t, t, t, t, " +
                    "t, f, t, t, f, t, f, t, f, ") == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }

        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'getBit(int i)'");
            System.out.println("Expected value: 0 (false)(index 6)");
            System.out.println("Actual value: " + longwordTester1.getBit(6).toString());
            if(longwordTester1.getBit(6) == bitFalse){
                throw new Exception();
            }
            System.out.println("Expected value: 1 (true)(index 10)");
            System.out.println("Actual value: " + longwordTester1.getBit(10).toString());
            if(longwordTester2.getBit(10) == bitFalse){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }

        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'setBit(int i, bit value)'");
            System.out.println("Expected value: (index 2 becomes 1/true) f, f, t, f, f, f, f, f, f, f, f, f, f, " +
                    "f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f, 0,");
            longwordTester1.setBit(2, bitTrue);
            System.out.println("Actual value: " + longwordTester1.toString());
            if(longwordTester1.getBit(2) == bitFalse){
                throw new Exception();
            }
            System.out.println("NEW VALUE OF LONGWORD 1: 536870982");
            System.out.println("Expected value: (index 30 becomes 0/false) t, t, t, t, t, t, t, t, t, t, t, t, t, t, ," +
                    " t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, t, f, t,");
            longwordTester7.setBit(30, bitFalse);
            System.out.println("Actual value: " + longwordTester7.toString());
            if(longwordTester1.getBit(30) == bitTrue){
                throw new Exception();
            }else{
                System.out.println("NEW VALUE OF LONGWORD 7: -3");
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED");
        }

        try{
            System.out.println("Testing method 'longword and(longword other)'");
            longword longBitwiseExpected = new longword(536870978);
            System.out.println("Expected value: (536870982 & -984549) " + longBitwiseExpected.toString());
            longword longBitwiseActual =  longwordTester1.and(longwordTester2);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(2046837782);
            System.out.println("Expected value: (2132165654 & -87984546) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester4.and(longwordTester3);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'longword or(longword other)'");
            longword longBitwiseExpected = new longword(-98434);
            System.out.println("Expected value: (2132165654 | -984549) " + longBitwiseExpected.toString());
            longword longBitwiseActual =  longwordTester3.or(longwordTester2);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(-2656674);
            System.out.println("Expected value: (2132165654 | -87984546) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester3.or(longwordTester4);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'longword xor(longword other)'");
            longword longBitwiseExpected = new longword(-4);
            System.out.println("Expected value: (-3 XOR 1) " + longBitwiseExpected.toString());
            longword longBitwiseActual =  longwordTester7.xor(longwordTester6);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(-2132264068);
            System.out.println("Expected value: (2132165654 XOR -984549) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester3.xor(longwordTester2);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'longword not()'");
            longword longBitwiseExpected = new longword(87984545);
            System.out.println("Expected value: (~87984546) " + longBitwiseExpected.toString());
            longword longBitwiseActual =  longwordTester4.not();
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(-2132165655);
            System.out.println("Expected value: (~2132165654) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester3.not();
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'longword rightShift(int amount)'");
            longword longBitwiseExpected = new longword(2082193);
            System.out.println("Expected value: (Rightshift 2132165654 10 times) " + longBitwiseExpected.toString());
            longword longBitwiseActual =  longwordTester3.rightShift(10);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(1);
            System.out.println("Expected value: (Rightshift -87984546 31 times) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester4.rightShift(31);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(14046);
            System.out.println("Expected value: (Rightshift 898945 6 times) " + longBitwiseExpected.toString());
            longBitwiseTemp = new longword(898945);
            longBitwiseActual =  longBitwiseTemp.rightShift(6);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(0);
            System.out.println("Expected value: (Rightshift 1 22 times) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester6.rightShift(22);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'longword leftShift(int amount)'");
            longword longBitwiseExpected = new longword(-351938184);
            System.out.println("Expected value: (Leftshift -87984546 2 times) " + longBitwiseExpected.toString());
            longword longBitwiseActual =  longwordTester4.leftShift(2);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(1073741824);
            System.out.println("Expected value: (Leftshift 1 30 times) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester6.leftShift(30);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(520);
            System.out.println("Expected value: (Leftshift 65 3 times) " + longBitwiseExpected.toString());
            longBitwiseTemp = new longword(65);
            longBitwiseActual =  longBitwiseTemp.leftShift(3);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(536870982);
            System.out.println("Expected value: (Leftshift 536870982 0 times) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester1.leftShift(0);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }
            longBitwiseExpected = new longword(-98304);
            System.out.println("Expected value: (Leftshift -3 15 times) " + longBitwiseExpected.toString());
            longBitwiseActual =  longwordTester7.leftShift(15);
            System.out.println("Actual value: " + longBitwiseActual.toString());
            if(longBitwiseExpected.toString().equals(longBitwiseActual.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'long getSigned()'");
            System.out.println("Expected value: -98454");
            int longBitwiseActual =  longwordTester2.getSigned();
            System.out.println("Actual value: " + longBitwiseActual);
            if(longBitwiseActual == -98454){
                System.out.println("Expected value: 536870982");
                longBitwiseActual = longwordTester1.getSigned();
                System.out.println("Actual value: " + longBitwiseActual);
                if(longBitwiseActual == 536870982){
                    System.out.println("Expected value: 1");
                    longBitwiseActual = longwordTester6.getSigned();
                    System.out.println("Actual value: " + longBitwiseActual);
                    if(longBitwiseActual == 1){
                        System.out.println("PASSED\n");
                    }else{
                        throw new Exception();
                    }
                }else{
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'long getUnsigned()'");
            System.out.println("Expected value: 4294868842");
            long longBitwiseActual =  longwordTester2.getUnsigned();
            System.out.println("Actual value: " + longBitwiseActual);
            if(longBitwiseActual == 4294868842L){
                System.out.println("Expected value: 2132165654");
                longBitwiseActual = longwordTester3.getUnsigned();
                System.out.println("Actual value: " + longBitwiseActual);
                if(longBitwiseActual == 2132165654){
                    System.out.println("Expected value: 4206982750");
                    longBitwiseActual = longwordTester4.getUnsigned();
                    System.out.println("Actual value: " + longBitwiseActual);
                    if(longBitwiseActual == 4206982750L){
                        System.out.println("PASSED\n");
                    }else{
                        throw new Exception();
                    }
                }else{
                    throw new Exception();
                }
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        try{
            System.out.println("Testing method 'void copy()'");
            System.out.println("Expected value: " + longwordTester1.toString());
            longword longBitwiseCopy =  new longword();
            longBitwiseCopy.copy(longwordTester1);
            System.out.println("Actual value: " + longBitwiseCopy.toString());
            if(longBitwiseCopy.toString().equals(longwordTester1.toString()) == false){
                throw new Exception();
            }
            longBitwiseCopy.copy(longwordTester7);
            System.out.println("Expected value: " + longwordTester7.toString());
            System.out.println("Actual value: " + longBitwiseCopy.toString());
            if(longBitwiseCopy.toString().equals(longwordTester7.toString()) == false){
                throw new Exception();
            }else{
                System.out.println("PASSED\n");
            }
        }catch(Exception e){
            System.out.println("FAILED\n");
        }
    }
}
