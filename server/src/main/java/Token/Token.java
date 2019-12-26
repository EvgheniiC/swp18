package Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Token implements IToken{
    List<Integer> tokens = new ArrayList<>();
    Random randomInt = new Random();

    @Override
    public int createToken(){
        int number;
        boolean numberFree =true;
        while(true){
            number = randomInt.nextInt(101);
            if(tokens.isEmpty()){
                tokens.add(number);
                return number;
            }else{
                for(int n:tokens){
                    if(n == number) {
                        numberFree = false;
                    }
                }
                if(numberFree){
                    tokens.add(number);
                    return number;
                }
            }
        }
    }
    @Override
    public void removeToken(int token){
        int index = tokens.indexOf(token);
        tokens.remove(index);
    }
    @Override
    public List<Integer> getAllTokens(){
        return tokens;
    }
}
