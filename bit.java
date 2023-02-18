public class bit {

    private boolean modifyingBit;

    /*
     * bit constructor
     * */
    public bit(boolean givenBoolean){
        this.modifyingBit = givenBoolean;
    }

    /*
     * sets the value of the bit
     * */
    void set(boolean value)
    {
        this.modifyingBit = value;
    }

    /*
     * changes the value from true to false or false to true
     * */
    void toggle()
    {
        if(this.modifyingBit == true){
            this.modifyingBit = false;
        }else{
            this.modifyingBit = true;
        }
    }

    /*
     * sets the bit to true
     * */
    void set()
    {
        this.modifyingBit = true;
    }

    /*
     * sets the bit to false
     * */
    void clear()
    {
        this.modifyingBit = false;
    }

    /*
     * returns the current value
     * */
    boolean getValue()
    {
        return this.modifyingBit;
    }

    /*
     * performs and on two bits and returns a new bit set to the result
     * */
    bit and(bit other)
    {
        //creating a new bit to save the result of the 'and' function
        bit bitToBeSet = new bit(false);

        //the only time using a logical 'and' will result in 'true' is if
        //both values are true, so set bit 'bitToBeSet' as false at any other moment
        if(this.modifyingBit == true){
            if(other.getValue() == true) {
                bitToBeSet.set(true);
            }else{
                bitToBeSet.set(false);
            }
        }else{
            bitToBeSet.set(false);
        }
        return bitToBeSet;
    }

    /*
     * performs or on two bits and returns a new bit set to the result
     * */
    bit or(bit other)
    {
        //creating a new bit to save the result of the 'or' function
        bit bitToBeSet = new bit(false);

        //a logical 'or' will only give a 'false' if both values are false
        //so set bit 'bitToBeSet' as true at any other moment
        if(this.modifyingBit == false){
            if(other.getValue() == false) {
                bitToBeSet.set(false);
            }else{
                bitToBeSet.set(true);
            }
        }else{
            bitToBeSet.set(true);
        }
        return bitToBeSet;
    }

    /*
     * performs xor on two bits and returns a new bit set to the result
     * */
    bit xor(bit other)
    {
        //creating a new bit to save the result of the 'xor' function
        bit bitToBeSet = new bit(false);

        //a logical 'xor' is only true when only one of the two values being
        //evaluated are true, so set bit 'bitToBeSet' as false at any other moment
        if(this.modifyingBit == false){
            if(other.getValue() == true){
                bitToBeSet.set(true);
            }else{
                bitToBeSet.set(false);
            }
        }else if(this.modifyingBit == true){
            if(other.getValue() == false){
                bitToBeSet.set(true);
            }else{
                bitToBeSet.set(false);
            }
        }else{
            bitToBeSet.set(false);
        }
        return bitToBeSet;
    }

    /*
     * performs not on the existing bit, returning the result as a new bit
     * */
    bit not()
    {
        //creating a new bit to save the result of the 'not' function
        bit bitToBeSet = new bit(false);

        //logical 'not' simply flips the value, so just set the current bit as
        //the opposite boolean
        if(this.modifyingBit == true){
            bitToBeSet.set(false);
        }else{
            bitToBeSet.set(true);
        }
        return bitToBeSet;
    }

    @Override
    /*
     * returns “t” or “f”
     * */
    public String toString()
    {
        if(this.modifyingBit == true){
            return "t";
        }else{
            return "f";
        }
    }
}
