import java.util.Random;
import java.util.Random.*;
public class KeyHandler {
    public String createKey(){
        String s = "";
        int sum = 0;
        int mul = 1;
        int randomNum = 1;
        for(int i = 0; i<6; i++){
            Random rand = new Random();
            randomNum = rand.nextInt(90-65+1)+65;
            char c = (char)randomNum;
            s+=c;
            sum+=randomNum;

        }
        mul = sum*randomNum;
        System.out.println("Sum: "+sum+" Mul: "+mul+" sumMod: "+((sum%26)+65)+" mulMod: "+((mul%26)+65));
        char c1 = (char) ((sum%26)+65);
        char c2 = (char) ((mul%26)+65);
        s = s+c1+c2;
        return s;
    }
    public boolean CheckKey(String s){
        if(s.length()!=8 ){
            return false;
        }
        if(!s.matches("[A-Z]{8}")){
            return false;
        }
        int sum  = 0;
        int mul = 1;
        for(int i = 0; i < 6; i++){
            sum+=s.charAt(i);
        }
        mul = sum*s.charAt(5);
        int c1 = (char) ((sum%26)+65);
        int c2 = (char) ((mul%26)+65);
        if(c1 == s.charAt(6) && c2 == s.charAt(7)){
            return true;
        }
        else{
            return false;
        }
    }



}
