public class ALU {

    /*
    * ALU calculator
    * */
    public static longword doOp(bit[] operation, longword a, longword b) throws Exception {
        bit bitTrue = new bit(true);
        bit bitFalse = new bit(false);
        bit[] bitOperation = operation;
        longword calculatedLongword = new longword();
        int i = 0;

        if (bitOperation[i].getValue() == true) { //1____
            i++;
            if (bitOperation[i].getValue() == false) { //10__
                i++;
                if (bitOperation[i].getValue() == false) { //100_
                    i++;
                    if (bitOperation[i].getValue() == false) { //1000 'and'
                        calculatedLongword = a.and(b);
                    } else { //1001 'or'
                        calculatedLongword = a.or(b);
                    }
                } else { //101_
                    i++;
                    if (bitOperation[i].getValue() == false) { //1010 'xor
                        calculatedLongword = a.xor(b);
                    } else { //1011 'not
                        calculatedLongword = a.not();
                    }
                }
            } else { //11__
                i++;
                if (bitOperation[i].getValue() == false) { //110_
                    i++;
                    if (bitOperation[i].getValue() == false) { //1100 'left shift'
                        int number = 0;
                        int bitsToBeShifted = 0;
                        int factor = 1;
                        for (int j = 31; j > 26; j--) { //iterate until -1 to count until index 0
                            //ignore all but the lowest 5 digits
                            //eval the integer number of how many bits to shift
                            if (b.getBit(j).getValue() == false) {
                                number = 0;
                            } else {
                                number = 1;
                            }
                            bitsToBeShifted = (int) ((number * factor) + bitsToBeShifted);
                            factor = factor * 2;
                        }
                        calculatedLongword = a.leftShift(bitsToBeShifted);

                    } else { //1101 'right shift'
                        int number = 0;
                        int bitsToBeShifted = 0;
                        int factor = 1;
                        for (int j = 31; j > 26; j--) { //iterate until -1 to count until index 0
                            //ignore all but the lowest 5 digits
                            //eval the integer number of how many bits to shift
                            if (b.getBit(j).getValue() == false) {
                                number = 0;
                            } else {
                                number = 1;
                            }
                            bitsToBeShifted = (int) ((number * factor) + bitsToBeShifted);
                            factor = factor * 2;
                        }
                        calculatedLongword = a.rightShift(bitsToBeShifted);
                    }
                } else { //111_
                    i++;
                    if (bitOperation[i].getValue() == false) { //1110 'add'
                        calculatedLongword = rippleAdder.add(a, b);
                    } else { //1111 'subtract'
                        calculatedLongword = rippleAdder.subtract(a, b);
                    }
                }
            }
        } else { //0111 'multiply'
            calculatedLongword = multiplier.multiply(a, b);
        }
        return calculatedLongword;
    }
}
