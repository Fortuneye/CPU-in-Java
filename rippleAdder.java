public class rippleAdder{

    /*
     * add 2 longwords
     * */
    public static longword add (longword a, longword b) throws Exception{
        bit carry = new bit (false);	//keep a record of whether or not to carry a 1 when adding bits
        int i = 31;
        bit bitTrue = new bit (true);
        bit bitFalse = new bit (false);
        longword addedLongword = new longword();

        while(i > (-1)){
            if(a.getBit(i).getValue() == false){
                if(b.getBit(i).getValue() == false){
                    if(carry.getValue() == false){ //a = 0, b = 0, carry = false
                        addedLongword.setBit(i, bitFalse); //result = 0
                        carry.set(false); //no carry
                        i--;

                    }else{  //a = 0, b = 0, carry = true
                        addedLongword.setBit(i, bitTrue); //result = 1
                        carry.set(false); //no carry
                        i--;
                    }
                }else{
                    if(carry.getValue() == false){ //a = 0, b = 1, carry = false
                        addedLongword.setBit(i, bitTrue); //result = 1
                        carry.set(false); //no carry
                        i--;
                    }else{ //a = 0, b = 1, carry = true
                        addedLongword.setBit(i, bitFalse); //result = 0
                        carry.set(true); //carry 1
                        i--;
                    }
                }

            }else if(a.getBit(i).getValue() == true){
                if(b.getBit(i).getValue() == false){
                    if(carry.getValue() == false){ //a = 1, b = 0, carry = false
                        addedLongword.setBit(i, bitTrue); //result = 1
                        carry.set(false); //no carry
                        i--;
                    }else{ //a = 1, b = 0, carry = true
                        addedLongword.setBit(i, bitFalse); //result = 0
                        carry.set(true); //carry 1
                        i--;
                    }
                }else{
                    if(carry.getValue() == false){ //a = 1, b = 1, carry = false
                        addedLongword.setBit(i, bitFalse); //result = 0
                        carry.set(true); //carry 1
                        i--;
                    }else{ //a = 1, b = 1, carry = true
                        addedLongword.setBit(i, bitTrue); //result = 1
                        carry.set(true); //carry 1
                        i--;
                    }
                }
            }
        }
        return addedLongword;
    }

    /*
     * subtract 2 longwords
     * */
    public static longword subtract (longword a, longword b) throws Exception{
        bit borrow = new bit (false);	//keep a record of whether or not to borrow a 1 when adding bits
        int i = 31;
        bit bitTrue = new bit (true);
        bit bitFalse = new bit (false);
        longword subtractedLongword = new longword();

        //if both a and b longwords are negative, add them
        if(a.getBit(0).getValue() == true) {
            if (b.getBit(0).getValue() == true) {
                subtractedLongword = add(a, b);
                return subtractedLongword;
            }
        }
        while (i > (-1)) {
            if (a.getBit(i).getValue() == false){
                if(b.getBit(i).getValue() == false){
                    if(borrow.getValue() == false){ //a = 0, b = 0, takeAway = false
                        subtractedLongword.setBit(i, bitFalse); //result = 0
                        borrow.set(false); //no borrow
                        i--;
                    }else{ //a = 0, b = 0, takeAway = true
                        subtractedLongword.setBit(i, bitTrue); //result = 1
                        borrow.set(true); //borrow 1
                        i--;
                    }
                }else{
                    if(borrow.getValue() == false){ //a = 0, b = 1, takeAway = false
                        subtractedLongword.setBit(i, bitTrue); //result = 1
                        borrow.set(true); //borrow 1
                        i--;
                    }else{ //a = 0, b = 1, takeAway = true
                        subtractedLongword.setBit(i, bitFalse); //result = 0
                        borrow.set(true); //borrow 1
                        i--;
                    }
                }


            } else if (a.getBit(i).getValue() == true) {
                if(b.getBit(i).getValue() == false){
                    if(borrow.getValue() == false){ //a = 1, b = 0, takeAway = false
                        subtractedLongword.setBit(i, bitTrue); //result = 1
                        borrow.set(false); //no borrow
                        i--;
                    }else{ //a = 1, b = 0, takeAway = true
                        subtractedLongword.setBit(i, bitFalse); //result = 0
                        borrow.set(false); //no takeAway
                        i--;
                    }
                }else{
                    if(borrow.getValue() == false){ //a = 1, b = 1, takeAway = false
                        subtractedLongword.setBit(i, bitFalse); //result = 0
                        borrow.set(false); //no takeAway
                        i--;
                    }else{ //a = 1, b = 1, takeAway = true
                        subtractedLongword.setBit(i, bitTrue); //result = 1
                        borrow.set(true); //borrow 1
                        i--;
                    }
                }
            }
        }
        return subtractedLongword;
    }
}