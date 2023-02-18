public class bit_test {

    public static void main(String args[]){
        runTests();
    }

    static void runTests(){
        bit bitTester1 = new bit(false); //bit testing variable object

        //TESTING VOID SET(BOOLEAN)
        try{
            System.out.println("Testing method 'void set(boolean)'");
            bitTester1.set(true);
            System.out.println("Expected value: true");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == false){
                throw new Exception();
            }
            bitTester1.set(false);
            System.out.println("Expected value: false");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == true){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        bitTester1 = new bit(false); //reset the bit used for testing

        //TESTING VOID TOGGLE()
        try{
            System.out.println("Testing method 'void toggle()'");
            bitTester1.toggle();
            System.out.println("Expected value: true");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == false){
                throw new Exception();
            }
            bitTester1.toggle();
            System.out.println("Expected value: false");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == true){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }
        bitTester1 = new bit(false); //reset the bit used for testing

        //TESTING VOID SET()
        try{
            System.out.println("Testing method 'void set()'");
            bitTester1.set();
            System.out.println("Expected value: true");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == false){
                throw new Exception();
            }
            bitTester1.set(true);
            bitTester1.set();
            System.out.println("Expected value: true");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == false){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        bitTester1 = new bit(false); //reset the bit used for testing

        //TESTING VOID CLEAR()
        try{
            System.out.println("Testing method 'void clear()'");
            bitTester1.clear();
            System.out.println("Expected value: false");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == true){
                throw new Exception();
            }
            bitTester1.set(false);
            bitTester1.clear();
            System.out.println("Expected value: false");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == true){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        bitTester1 = new bit(false); //reset the bit used for testing

        //TESTING VOID GETVALUE()
        try{
            System.out.println("Testing method 'void getValue()'");
            System.out.println("Expected value: false");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == true){
                throw new Exception();
            }
            bitTester1.set(true);
            System.out.println("Expected value: true");
            System.out.println("Actual value: " + bitTester1.getValue());
            if(bitTester1.getValue() == false){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        bitTester1 = new bit(false); //reset the bit used for testing

        bit bitTester2 = new bit(false); //create a new bit to use for testing

        //TESTING BIT AND(BIT OTHER)
        try{
            boolean testing; //store boolean results in variable
            //TEST: false/false
            System.out.println("Testing method 'bit and(bit other)'");
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            System.out.println("Expected value: false");
            testing = bitTester1.and(bitTester2).getValue();
            System.out.println("Performing 'and' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            //TEST: true/false
            bitTester1.toggle();
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.and(bitTester2).getValue();
            System.out.println("Expected value: false");
            System.out.println("Performing 'and' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            //TEST: false/true
            bitTester2.toggle();
            bitTester1.toggle();
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.and(bitTester2).getValue();
            System.out.println("Expected value: false");
            System.out.println("Performing 'and' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            //TEST: true/true
            bitTester1.toggle();
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.and(bitTester2).getValue();
            System.out.println("Expected value: true");
            System.out.println("Performing 'and' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        //reset both bit variables
        bitTester1 = new bit(false);
        bitTester2 = new bit(false);

        //TESTING BIT OR(BIT OTHER)
        try{
            boolean testing;
            //TEST: false/false
            System.out.println("Testing method 'bit or(bit other)'");
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            System.out.println("Expected value: false");
            testing = bitTester1.or(bitTester2).getValue();
            System.out.println("Performing 'or' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            //TEST: true/false
            bitTester1 = new bit(true);
            bitTester2 = new bit(false);
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.or(bitTester2).getValue();
            System.out.println("Expected value: true");
            System.out.println("Performing 'or' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            //TEST: false/true
            bitTester1 = new bit(false);
            bitTester2 = new bit(true);
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.or(bitTester2).getValue();
            System.out.println("Expected value: true");
            System.out.println("Performing 'or' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            //TEST: true/true
            bitTester1 = new bit(true);
            bitTester2 = new bit(true);
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.or(bitTester2).getValue();
            System.out.println("Expected value: true");
            System.out.println("Performing 'or' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        //reset both bit variables
        bitTester1 = new bit(false);
        bitTester2 = new bit(false);

        //TESTING BIT XOR(BIT OTHER)
        try{
            boolean testing;
            //TEST: false/false
            System.out.println("Testing method 'bit xor(bit other)'");
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            System.out.println("Expected value: false");
            testing = bitTester1.xor(bitTester2).getValue();
            System.out.println("Performing 'xor' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            //TEST: true/false
            bitTester1 = new bit(true);
            bitTester2 = new bit(false);
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.xor(bitTester2).getValue();
            System.out.println("Expected value: true");
            System.out.println("Performing 'xor' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            //TEST: false/true
            bitTester1 = new bit(false);
            bitTester2 = new bit(true);
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.xor(bitTester2).getValue();
            System.out.println("Expected value: true");
            System.out.println("Performing 'xor' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            //TEST: true/true
            bitTester1 = new bit(true);
            bitTester2 = new bit(true);
            System.out.println("Value 1: " + bitTester1.getValue());
            System.out.println("Value 2: " + bitTester2.getValue());
            testing = bitTester1.xor(bitTester2).getValue();
            System.out.println("Expected value: false");
            System.out.println("Performing 'xor' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        //reset bit variable
        bitTester1 = new bit(false);

        //TESTING BIT NOT()
        try{
            boolean testing;
            System.out.println("Testing method 'bit not()'");
            //TEST: false
            System.out.println("Expected value: true");
            testing = bitTester1.not().getValue();
            System.out.println("Performing 'not' operation: " + testing);
            if(testing == false){
                throw new Exception();
            }
            //TEST: true
            bitTester1 = new bit(true);
            testing = bitTester1.not().getValue();
            System.out.println("Expected value: false");
            System.out.println("Performing 'not' operation: " + testing);
            if(testing == true){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

        //TESTING TOSTRING()
        try{
            System.out.println("Testing method 'toString()'");
            //TEST: false
            System.out.println("Expected value: f");
            System.out.println("Performing 'toString' operation: " + bitTester1.toString());
            if(bitTester1.toString().compareTo("f") == 1){
                throw new Exception();
            }
            //TEST: true
            bitTester1 = new bit(true);
            System.out.println("Expected value: t");
            System.out.println("Performing 'toString' operation: " + bitTester1.toString());
            if(bitTester1.toString().compareTo("t") == 1){
                throw new Exception();
            }
            System.out.println("PASSED\n");
        }catch(Exception e){
            System.out.println("FAILED\n");
        }

    }
}