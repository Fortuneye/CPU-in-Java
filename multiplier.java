public class multiplier {

    /*
    * multiply 2 longwords
    * */
    public static longword multiply (longword a, longword b) throws Exception {

        longword multipliedLong = new longword();
        longword multipliedLongSum = new longword();
        int i = 31; //a index
        int j = 31; //b index
        int x = 0; //left shift index
        bit bitTrue = new bit(true);
        bit bitFalse = new bit(false);

        while(i > (-1) && j > (-1)) {
            if (a.getBit(i).getValue() == true) {
                if (b.getBit(j).getValue() == true) { // 1 x 1 = 1
                    multipliedLong.setBit(i, bitTrue);
                    i--;
                    if (i == (-1)) {
                        i = 31; //reset index of a to end of longword
                        j--; //move index of b down one, these both emulate how "long" multiplication works

                        //add the sum to the newly evaluated longword
                        //shift that newly evaluated result by the number of times b's index has been moved down
                        multipliedLongSum = rippleAdder.add(multipliedLongSum, multipliedLong.leftShift(x));
                        x++;
                    }
                } else { //1 x 0 = 0
                    multipliedLong.setBit(i, bitFalse);
                    i--;
                    if (i == (-1)) {
                        i = 31; //reset index of a to end of longword
                        j--; //move index of b down one, these both emulate how "long" multiplication works

                        //add the sum to the newly evaluated longword
                        //shift that newly evaluated result by the number of times b's index has been moved down
                        multipliedLongSum = rippleAdder.add(multipliedLongSum, multipliedLong.leftShift(x));
                        x++;
                    }
                }

            } else if (a.getBit(i).getValue() == false) {
                if (b.getBit(j).getValue() == true) { //0 x 1 = 0
                    multipliedLong.setBit(i, bitFalse);
                    i--;
                    if (i == (-1)) {
                        i = 31; //reset index of a to end of longword
                        j--; //move index of b down one, these both emulate how "long" multiplication works

                        //add the sum to the newly evaluated longword
                        //shift that newly evaluated result by the number of times b's index has been moved down
                        multipliedLongSum = rippleAdder.add(multipliedLongSum, multipliedLong.leftShift(x));
                        x++;
                    }
                } else { //0 x 0 = 0
                    multipliedLong.setBit(i, bitFalse);
                    i--;
                    if (i == (-1)) {
                        i = 31; //reset index of a to end of longword
                        j--; //move index of b down one, these both emulate how "long" multiplication works

                        //add the sum to the newly evaluated longword
                        //shift that newly evaluated result by the number of times b's index has been moved down
                        multipliedLongSum = rippleAdder.add(multipliedLongSum, multipliedLong.leftShift(x));
                        x++;
                    }
                }
            }
        }
        return multipliedLongSum;
    }
}
