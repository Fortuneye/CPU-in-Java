public class memory {

    bit[] computerMemory = new bit[8192]; //1024 bytes

    //constructor initializes mem with 0's/false
    public memory(){
        bit bitFalse = new bit (false);
        for(int i = 0; i < 8192; i++){
            computerMemory[i] = bitFalse;
        }
    }

    //read from memory variable
    public longword read(longword address) throws Exception {

        //address is a byte address = index to be used as the starting index
        long indexToBeInserted;
        if(address == null){ //if address is null, use 0 as an index
            indexToBeInserted = 0;
        }else{
            //else, multiply the unsigned address number by 8 to get the index
            //longword address is a byte number, so we must convert
            indexToBeInserted = address.getUnsigned() * 8;
        }
        bit[] returnedMem = new bit[32];
        int j = 0;

        if(indexToBeInserted >= 8192){ //if the index is out of bounds, throw exception
            throw new Exception();
        }

        //insert the bits we want in a new bit array
        for(int i = (int) indexToBeInserted; i < indexToBeInserted + 32; i++){
            returnedMem[j] = computerMemory[i];
            j++;
        }
        return new longword(returnedMem); //create a longword from our new bit array and return
    }

    //write a longword to a space in memory
    public void write(longword address, longword value) throws Exception {

        //address is a byte address = index to be used as the starting index
        long indexToBeInserted;
        if(address == null){ //if address is null, use 0 as an index
            indexToBeInserted = 0;
        }else{
            //else, multiply the unsigned address number by 8 to get the index
            //longword address is a byte number, so we must convert
            indexToBeInserted = address.getUnsigned() * 8;
        }
        if(indexToBeInserted >= 8192){ //if the index is out of bounds, throw exception
            throw new Exception();
        }
        bit[] returnedMem = new bit[32];
        int j = 0;

        for(int i = 0; i < 32; i++){
            returnedMem[i] = value.getBit(i);
        }

        for(int i = (int) indexToBeInserted; i < indexToBeInserted + 32; i++){
            if(i >= 8192){ //if the index is out of bounds, throw exception
                throw new Exception();
            }
            computerMemory[i] = returnedMem[j];
            j++;
        }
    }
}
