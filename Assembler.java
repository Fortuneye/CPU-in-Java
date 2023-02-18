import java.util.ArrayList;

public class Assembler {

    private static String lexer(String currentCommand) throws Exception {
        char[] stringChar = currentCommand.toCharArray(); //turn our string into an array
        ArrayList<Object> tokenizedStatement = new ArrayList<>(); //arraylist for our TOKENS
        ArrayList<Object> lexedStatement = new ArrayList<>(); //arraylist for our passed in string, delimited
        boolean alreadyLexed = false; //boolean to make sure we don't go through loops unnecessarily

        //ALL keywords in language + capitalized versions
        String[] keywords = {
                "move", "Move",
                "add", "Add",
                "subtract", "Subtract",
                "multiply", "Multiply",
                "and", "And",
                "or", "Or",
                "xor", "Xor",
                "not", "Not",
                "rightshift", "Rightshift",
                "leftshift", "Leftshift",
                "halt", "Halt",
                "Interrupt", "interrupt",
                "jump", "Jump",
                "compare", "Compare",
                "branchIfEqual", "BranchIfEqual",
                "branchIfNotEqual", "BranchIfNotEqual",
                "branchIfGreaterThan", "BranchIfGreaterThan",
                "branchIfGreaterThanOrEqual", "BranchIfGreaterThanOrEqual",
                "stack", "Stack",
                "compare", "Compare",
                "call", "Call",
                "return", "Return",
                "Push", "push",
                "Pop", "pop"
        };

        //strings of all registers
        String[] registers = {
                "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8"
                , "R9", "R10", "R11", "R12", "R13", "R14", "R15", "R16"
        };

        int j = 0;
        while(j < currentCommand.length()) { //iterate through entire char array
            StringBuilder newToken = new StringBuilder();

            while (j < stringChar.length && stringChar[j] != ' ') { //loop until we hit a space
                newToken.append(stringChar[j]);
                j++;
            }
            j++;

            String test = newToken.toString();

            for (String keyword : keywords) { //loop entire array of keywords
                if (newToken.toString().contains(keyword)) { //if what we just parsed is a keyword,
                    lexedStatement.add("KEYWORD"); //add KEYWORD to our token list
                    tokenizedStatement.add(newToken.toString()); //and add the parsed chunk into our tokenized statement list
                    alreadyLexed = true;
                    break;
                }
            }

            if(!alreadyLexed) {
                if (newToken.toString().contains("R")) { //if what we have isn't a keyword and it contains an R...
                    for (String register : registers) { //this means its a register, so check to find which register it is
                        if (newToken.toString().contains(register)) {
                            lexedStatement.add("REGISTER"); //add REGISTER to our token list
                            tokenizedStatement.add(newToken.toString()); //and add the parsed statement to its list as well
                            break;
                        }
                    }

                } else {
                    try { //if neither of the above were true, that means it's most likely an integer
                        Integer.parseInt(newToken.toString()); //TRY parseInt on the string
                        lexedStatement.add("NUMBER"); //if it works, add NUMBER to the token list
                        tokenizedStatement.add(newToken.toString()); //and add the statement to its list
                        break;
                    } catch (Exception e) {
                        throw new Exception("Error! Incorrect syntax!"); //if the parse didn't work, then it's not correct
                    }
                }
            }
            alreadyLexed = false;
        }

        return parser(lexedStatement, tokenizedStatement); //when we complete tokenizing and lexing, send both lists to the parser
    }

    //an expression will either be in the form of <KEYWORD> <FIRST REGISTER> <SECOND REGISTER> <RESULT REGISTER>
    //                                         or <KEYWORD> <RESULT REGISTER> <NUMBER>
    //                                         or <KEYWORD> <REGISTER> <REGISTER>
    //                                         or <KEYWORD> <NUMBER>
    //                                         or <KEYWORD>
    private static String parser(ArrayList lexTokens, ArrayList tokenList) throws Exception {

        //Any ALU related operations
        if((lexTokens.size() == 4) && lexTokens.get(0) == "KEYWORD" && lexTokens.get(1) == "REGISTER" && lexTokens.get(2) == "REGISTER" && lexTokens.get(3) == "REGISTER"){
            return keywordConverter((String) tokenList.get(0)) + " " + numberConverter((String) tokenList.get(1), 0) + " " +
                    numberConverter((String) tokenList.get(2), 0) + " " + numberConverter((String) tokenList.get(3), 0);

            //move operation
        }else if((lexTokens.size() == 3) &&lexTokens.get(0) == "KEYWORD" && lexTokens.get(1) == "REGISTER" && lexTokens.get(2) == "NUMBER"){

            //since move is a "special" operation, we want to check our tokens just to make sure it's correct
            if(tokenList.get(0).equals("Move") || tokenList.get(0).equals("move")) {
                return keywordConverter((String) tokenList.get(0)) + " " + numberConverter((String) tokenList.get(1), 0) + " " +
                        numberConverter((String) tokenList.get(2), 1);
            }else{
                throw new Exception("Error! Incorrect syntax!");
            }

            //interrupts
        }else if((lexTokens.size() == 3) &&lexTokens.get(0) == "KEYWORD" && lexTokens.get(1) == "REGISTER" && lexTokens.get(2) == "REGISTER"){

            //since compare is a "special" operation, we want to check our tokens just to make sure it's correct
            if(tokenList.get(0).equals("compare") || tokenList.get(0).equals("Compare")) {
                return keywordConverter((String) tokenList.get(0)) + " " + "0000" + " " +
                        numberConverter((String) tokenList.get(1),  0) + " " + numberConverter((String) tokenList.get(2), 0);
            }else{
                throw new Exception("Error! Incorrect syntax!");
            }

            //interrupts
        }else if((lexTokens.size() == 2) && lexTokens.get(0) == "KEYWORD" && lexTokens.get(1) == "NUMBER"){

            //since interrupt is a "special" operation, we want to check our tokens just to make sure it's correct
            if(tokenList.get(0).equals("Interrupt") || tokenList.get(0).equals("interrupt")) {
                return keywordConverter((String) tokenList.get(0)) + " " + numberConverter((String) tokenList.get(1), 2);

            }else if(tokenList.get(0).equals("jump") || tokenList.get(0).equals("Jump")){
                return keywordConverter((String) tokenList.get(0)) + " " + numberConverter((String) tokenList.get(1), 2);

            }else if(tokenList.get(0).equals("branchIfEqual") || tokenList.get(0).equals("BranchIfEqual")
                    || tokenList.get(0).equals("branchIfNotEqual") || tokenList.get(0).equals("BranchIfNotEqual")
                    || tokenList.get(0).equals("branchIfGreaterThan") || tokenList.get(0).equals("BranchIfGreaterThan")
                    || tokenList.get(0).equals("branchIfGreaterThanOrEqual") || tokenList.get(0).equals("BranchIfGreaterThanOrEqual")){

                return keywordConverter((String) tokenList.get(0)) + numberConverter((String) tokenList.get(1), 3);

            }else if(tokenList.get(0).equals("Call") || tokenList.get(0).equals("call")){
                return keywordConverter((String) tokenList.get(0)) + numberConverter((String) tokenList.get(1), 4);

            }else{
                throw new Exception("Error! Incorrect syntax!");
            }

            //push and pop
        }else if(((lexTokens.size() == 2) && (lexTokens.get(0)) == "KEYWORD") && (lexTokens.get(1) == "REGISTER")){

            if(tokenList.get(0).equals("Push") || tokenList.get(0).equals("push")){
                return keywordConverter((String) tokenList.get(0)) + " " + numberConverter((String) tokenList.get(1), 0);

            }else if(tokenList.get(0).equals("Pop") || tokenList.get(0).equals("pop")){
                return keywordConverter((String) tokenList.get(0)) + " " + numberConverter((String) tokenList.get(1), 0);

            }else{
                throw new Exception("Error! Incorrect syntax!");
            }

            //halt
        }else if((lexTokens.size() == 1) && lexTokens.get(0) == "KEYWORD") {

            //since halt is a "special" operation, we want to check our tokens just to make sure it's correct
            if(tokenList.get(0).equals("Halt") || tokenList.get(0).equals("halt")) {
                return keywordConverter((String) tokenList.get(0));

                //since return is a "special" operation, we want to check our tokens just to make sure it's correct
            }else if(tokenList.get(0).equals("Return") || tokenList.get(0).equals("return")) {
                return keywordConverter((String) tokenList.get(0));
            }else{
                throw new Exception("Error! Incorrect syntax!");
            }

        }else{
            throw new Exception("Error! Incorrect syntax!");
        }
    }

    //build the correct number given the string and if we need to make it 4 bits or not
    //int moveJumpOrNot is a check to tell if we want a number for:
    //                                                              last 4 bits = 0
    //                                                              last 8 bits = 1
    //                                                              last 12 bits = 2
    //                                                              last 9 bits = 3
    //                                                              last 10 bits = 4
    private static String numberConverter(String input, int moveJumpOrOther) throws Exception {
        longword newNum = new longword();

        //if our input is a register...
        if(input.contains("R")){
            input = input.substring(1); //remove the R
        }

        newNum = new longword(Integer.parseInt(input));

        String longwordToString = ""; //make an empty string so it can be added to in the for-loop
        String bitStr = "";
        int x = 0;

        if(moveJumpOrOther == 0) { //if we're MOVING/or just needing 4 bits

            for (int j = 28; j < 32; j++) { //loop our new longword 4 times

                if(x == 4){
                    longwordToString = longwordToString.concat(" "); //add a space after 4 bits
                    x = 0;
                }

                if (newNum.getBit(j).getValue() == true) {
                    bitStr = "1";
                } else {
                    bitStr = "0";
                }
                longwordToString = longwordToString.concat(bitStr); //add bits to a new string
                x++;
            }
        }else if(moveJumpOrOther == 1){ //if we're doing any standard case
            for (int j = 24; j < 32; j++) { //loop our new longword 8 times

                if(x == 4){
                    longwordToString = longwordToString.concat(" "); //add a space after 4 bits
                    x = 0;
                }

                if (newNum.getBit(j).getValue() == true) {
                    bitStr = "1";
                } else {
                    bitStr = "0";
                }

                longwordToString = longwordToString.concat(bitStr); //add bits to a new string
                x++;
            }
        }else if(moveJumpOrOther == 2){ //if we're jumping

            //jumping cannot take negative numbers, so throw an error if there is onw
            if(newNum.getBit(0).getValue() == true){
                throw new Exception("ERROR! Incorrect syntax!");
            }

            for (int j = 20; j < 32; j++) { //loop our new longword 8 times

                if(x == 4){
                    longwordToString = longwordToString.concat(" "); //add a space after 4 bits
                    x = 0;
                }

                if (newNum.getBit(j).getValue() == true) {
                    bitStr = "1";
                } else {
                    bitStr = "0";
                }

                longwordToString = longwordToString.concat(bitStr); //add bits to a new string
                x++;
            }
        }else if(moveJumpOrOther == 3){ //if we're doing a branch (9 bits)

            //add the sign bit
            if(newNum.getBit(0).getValue() == true){
                longwordToString = longwordToString.concat("1");
            }else{
                longwordToString = longwordToString.concat("0");
            }

            x = 3; //set as 3 so that we can create a space in the right place of our instructions
            for (int j = 23; j < 32; j++) { //loop our new longword 9 times

                if(x == 4){
                    longwordToString = longwordToString.concat(" "); //add a space after 4 bits
                    x = 0;
                }

                if (newNum.getBit(j).getValue() == true) {
                    bitStr = "1";
                } else {
                    bitStr = "0";
                }

                longwordToString = longwordToString.concat(bitStr); //add bits to a new string
                x++;
            }
        }else if(moveJumpOrOther == 4){ //if we're doing a branch (10 bits)

            //make sure num is positive
            if(newNum.getBit(0).getValue() == true){
                throw new Exception("NUMBER IS NEGATIVE WHEN IT SHOULD NOT BE!");
            }

            x = 2; //set as 3 so that we can create a space in the right place of our instructions
            for (int j = 22; j < 32; j++) { //loop our new longword 9 times

                if(x == 4){
                    longwordToString = longwordToString.concat(" "); //add a space after 4 bits
                    x = 0;
                }

                if (newNum.getBit(j).getValue() == true) {
                    bitStr = "1";
                } else {
                    bitStr = "0";
                }

                longwordToString = longwordToString.concat(bitStr); //add bits to a new string
                x++;
            }
        }else{
            throw new Exception("ERROR! Incorrect syntax!");
        }
        return longwordToString;
    }

    //keyword to opcode converter
    private static String keywordConverter(String input) throws Exception {
        if(input.contentEquals("Move") || input.contentEquals("move")){
            return "0001";
        }else if(input.contentEquals("Add") || input.contentEquals("add")){
            return "1110";
        }else if(input.contentEquals("Subtract") || input.contentEquals("subtract")){
            return "1111";
        }else if(input.contentEquals("Multiply") || input.contentEquals("multiply")){
            return "0111";
        }else if(input.contentEquals("And") || input.contentEquals("and")){
            return "1000";
        }else if(input.contentEquals("Or") || input.contentEquals("or")){
            return "1001";
        }else if(input.contentEquals("Xor") || input.contentEquals("xor")){
            return "1010";
        }else if(input.contentEquals("Not") || input.contentEquals("not")){
            return "1011";
        }else if(input.contentEquals("Rightshift") || input.contentEquals("rightshift")){
            return "1101";
        }else if(input.contentEquals("Leftshift") || input.contentEquals("leftshift")){
            return "1100";
        }else if(input.contentEquals("Halt") || input.contentEquals("halt")){
            return "0000 0000 0000 0000";
        }else if(input.contentEquals("Interrupt") || input.contentEquals("interrupt")){
            return "0010";
        }else if(input.contentEquals("Jump") || input.contentEquals("jump")){
            return "0011";
        }else if(input.contentEquals("Compare") || input.contentEquals("compare")){
            return "0100";

            //[10] == greater than
            //[10] or [01] == greater than or equal to
            //[01] == equal
            //[00] == not equal
        }else if(input.contentEquals("branchIfEqual") || input.contentEquals("BranchIfEqual")){
            return "0101 01";
        }else if(input.contentEquals("branchIfNotEqual") || input.contentEquals("BranchIfNotEqual")){
            return "0101 00";
        }else if(input.contentEquals("branchIfGreaterThan") || input.contentEquals("BranchIfGreaterThan")){
            return "0101 10";
        }else if(input.contentEquals("branchIfGreaterThanOrEqual") || input.contentEquals("BranchIfGreaterThanOrEqual")){
            return "0101 11";
        }else if(input.contentEquals("Stack") || input.contentEquals("stack")){
            return "0110";
        }else if(input.contentEquals("Call") || input.contentEquals("call")){
            return "0110 10";
        }else if(input.contentEquals("Push") || input.contentEquals("push")){
            return "0110 0000 0000";
        }else if(input.contentEquals("Pop") || input.contentEquals("pop")){
            return "0110 0100 0000";
        }else if(input.contentEquals("Return") || input.contentEquals("return")){
            return "0110 1100 0000 0000";
        }else{
            throw new Exception("Error! Keyword is undefined!");
        }
    }

    public static String[] assemble (String[] commands) throws Exception {

        String[] newlyMadeAssembly = new String[commands.length]; //a new array just for our newly created code

        for(int i = 0; i < commands.length; i++){
            String currentCommand = commands[i];

            newlyMadeAssembly[i] = lexer(currentCommand); //send the current string to the lexer (which sends its values to the parser)
        }
        return newlyMadeAssembly;
    }
}
