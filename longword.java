public class longword {

    //bit array for storage
    bit[] internalBitStorageArray = new bit[32];

    /*
     * longword constructor using an integer
     * */
    public longword(int number) throws Exception {
        this.set(number);
    }

    /*
     * longword constructor using a bit array
     * */
    public longword(bit[] bitArray){
        this.internalBitStorageArray = bitArray;
    }

    /*
     * default longword constructor
     * */
    public longword() throws Exception {
        this.set(0);
    }

    /*
     * Get bit i
     * */
    bit getBit(int i) throws Exception
    {
        //if the index is out of bounds, throw exception
        if(i > 31){
            throw new Exception("Invalid index!");
        }else{

            bit gottenBit = this.internalBitStorageArray[i];
            return gottenBit;
        }
    }

    /*
     * set bit i's value
     * */
    void setBit(int i, bit value) throws Exception
    {
        //if the index is out of bounds, throw exception
        if(i > 31){
            throw new Exception("Invalid index!");
        }else{
            this.internalBitStorageArray[i] = value;
        }
    }

    /*
     * 'and' two longwords, returning a third
     * */
    longword and(longword other) throws Exception
    {
        longword logicalAndLongword = new longword();
        for(int i = 0; i < 32; i++){
            logicalAndLongword.setBit(i, this.internalBitStorageArray[i].and(other.getBit(i)));
        }
        return logicalAndLongword;
    }

    /*
     * 'or' two longwords, returning a third
     * */
    longword or(longword other) throws Exception
    {
        longword logicalOrLongword = new longword();
        for(int i = 0; i < 32; i++){
            logicalOrLongword.setBit(i, this.internalBitStorageArray[i].or(other.getBit(i)));
        }
        return logicalOrLongword;
    }

    /*
     * 'xor' two longwords, returning a third
     * */
    longword xor(longword other) throws Exception
    {
        longword logicalXorLongword = new longword();
        for(int i = 0; i < 32; i++){
            logicalXorLongword.setBit(i, this.internalBitStorageArray[i].xor(other.getBit(i)));
        }
        return logicalXorLongword;
    }

    /*
     * negate this longword, creating another
     * */
    longword not() throws Exception
    {
        longword logicalNotLongword = new longword();
        for(int i = 0; i < 32; i++){
            logicalNotLongword.setBit(i, this.internalBitStorageArray[i].not());
        }
        return logicalNotLongword;
    }

    /*
     * rightshift this longword by amount bits, creating a new longword
     * */
    longword rightShift(int amount) throws Exception
    {
        longword thisLongword = new longword(this.internalBitStorageArray);
        longword rightShiftLongword = new longword();
        bit bitFalse = new bit(false);
        int j = 0;

        if(amount > 31){
            throw new Exception();
        }
        if(amount == 0){
            return thisLongword;
        }

        //start at the position where the shifting starts and increment until the end
        //of the longword. int i starts at the beginning of the longworded to be shifted
        //and increments for as long as it can to create the desired right-shifting effect
        for(int i = amount; i < 32; i++){
            rightShiftLongword.setBit(i, this.internalBitStorageArray[j]);
            j++;
        }
        return rightShiftLongword;
    }

    /*
     * leftshift this longword by amount bits, creating a new longword
     * */
    longword leftShift(int amount) throws Exception
    {
        longword thisLongword = new longword(this.internalBitStorageArray);
        longword leftShiftLongword = new longword();
        bit bitFalse = new bit(false);
        int j = 31; //size of array

        if(amount > 31){
            throw new Exception();
        }
        if(amount == 0){
            return thisLongword;
        }
        //int i starts at the position where the last digit would be
        //and int j starts at the end of the longword we are shifting
        //this creates the left-shifting effect we need for a left shift
        for(int i = 31 - amount; i > (-1); i--){
            leftShiftLongword.setBit(i, this.internalBitStorageArray[j]);
            j--;
        }
        return leftShiftLongword;
    }

    @Override
    /*
     * returns a comma separated string of 0's and 1's: "0,0,0,0,0 (etcetera)" for example
     * */
    public String toString()
    {
        String longwordToString = ""; //make an empty string so it can be added to in the for-loop
        for(int i = 0; i < 32; i++){
            longwordToString = longwordToString.concat(this.internalBitStorageArray[i].toString() + ", ");
        }
        return longwordToString;
    }

    /*
     * returns the value of this longword as a long
     * */
    long getUnsigned()
    {
        long number = 0;
        int j = 0;
        long result = 0;
        long factor = 1;

        for (int i = 31; i > (-1); i--) { //iterate until -1 to count until index 0
            if (this.internalBitStorageArray[i].getValue() == false) {
                number = 0;
            } else {
                number = 1;
            }

            result = (number * factor) + result;
            factor = factor * 2;
            j++;
        }
        return result;
    }

    /*
     * returns the value of this longword as an int
     * */
    int getSigned() throws Exception
    {
        int number = 0;
        int j = 0;
        int result = 0;
        int factor = 1;
        int carrying = 30; //the current position while performing a two's complement;
                           //starts at 30 because we have to check both the position to
                           //the right and current position.
        bit bitTrue = new bit(true);
        bit bitFalse = new bit(false);

        //if the longword is a negative, we have to convert it into a two's complement form
        if(this.internalBitStorageArray[0].getValue() == true){
            longword signedLong = new longword(this.internalBitStorageArray);
            longword negatedLong = signedLong.not(); //step 1 of 2's complement: negate the longword
            bit carry = new bit(false); //keep a record of whether or not to carry a 1 when adding bits

            if(negatedLong.getBit(31).getValue() == true){ //if our last bit is a 1, we will need to carry
                carry.set(true);
                while (carry.getValue() == true){
                    //when carrying, set the value to the right of our current position to a 0
                    negatedLong.setBit(carrying + 1, bitFalse);
                    if(negatedLong.getBit(carrying).getValue() == true){ //if our current position is a 1...
                        carry.set(true); //keep the carrying boolean as true
                        carrying--;
                    }else{
                        negatedLong.setBit(carrying, bitTrue); //just set the current bit as a 1
                        carry.set(false);
                    }
                }
            }else { //if our last bit is a 0, just set it as a 1
                negatedLong.setBit(31, bitTrue);
            }
            for(int i = 31; i > (-1); i--) { //iterate until -1 to count until index 0
                if(negatedLong.getBit(i).getValue() == false){
                    number = 0;
                }else{
                    number = 1;
                }
                result = (int) ((number * factor) + result);
                factor = factor * 2;
                j++;
            }
            result = result * -1; //because it's negative, multiply number by -1 to keep its negation

        }else { //if it's a non-negative longword, calculate normally
            for (int i = 31; i > (-1); i--) { //iterate until -1 to count until index 0
                if (this.internalBitStorageArray[i].getValue() == false) {
                    number = 0;
                } else {
                    number = 1;
                }
                result = (int) ((number * factor) + result);
                factor = factor * 2;
                j++;
            }
        }
        return result;
    }

    /*
     * copies the values of the bits from another longword into this one
     * */
    void copy(longword other)
    {
        this.internalBitStorageArray = other.internalBitStorageArray;
    }

    /*
     * set the value of the bits of this longword (used for tests)
     * */
    void set(int value) throws Exception
    {
        int number = 0;
        int j = 31;
        int result = 0;
        int carrying = 30;
        bit bitTrue = new bit(true);
        bit bitFalse = new bit(false);
        int[] numberFinal = new int[32];
        boolean isNegative = false;

        if(value < 0){ //if our number is a negative...
            isNegative = true;
            value = value * -1;
        }

        while(value > 0) {
            result = value % 2; //use modulo on our number and 2 to get the remainder
            value = value / 2; //set our number as itself divided by 2
            numberFinal[j] = result; //insert resulting values in numberFinal array,
            j--;
        }

        //insert all numbers in our current bit array
        for(int i = 31; i > (-1); i--){
            if(numberFinal[i] == 1){
                this.internalBitStorageArray[i] = bitTrue;
            }else{
                this.internalBitStorageArray[i] = bitFalse;
            }
        }
        this.internalBitStorageArray[0] = bitFalse; //set first bit as 0
                                                    // (if it's a negative it will be reassigned as 1)
        //if the given number is a negative... perform two's compliment on our new longword
        if(isNegative == true){
            longword tempLong = new longword(this.internalBitStorageArray);
            longword negatedLong = tempLong.not();
            bit carry = new bit(false);
            if(negatedLong.getBit(31).getValue() == true){
                carry.set(true);
                while (carry.getValue() == true){
                    negatedLong.setBit(carrying + 1, bitFalse);
                    if(negatedLong.getBit(carrying).getValue() == true){
                        carry.set(true);
                        carrying--;
                    }else{
                        negatedLong.setBit(carrying, bitTrue);
                        carry.set(false);
                    }
                }
            }else{
                negatedLong.setBit(31, bitTrue);
            }
            negatedLong.setBit(0, bitTrue); //at end of two's compliment, assign first bit as 1 to preserve negation
            this.internalBitStorageArray = negatedLong.internalBitStorageArray;
        }
    }

}
