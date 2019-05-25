import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cipher {
    static KeyHandler keyHandler = new KeyHandler();
    private ArrayList<Integer> createSubStituionArray(String key){
        ArrayList<Integer> substitutionArray = new ArrayList<>();
        for(int i = 0; i<key.length(); i++){
            for(int j = 0; j<key.length(); j++){
                substitutionArray.add(key.charAt(i)+key.charAt(j));
            }
        }
        return substitutionArray;
    }

    private String substituteString(String s , String key){
        ArrayList<Integer> array = createSubStituionArray(key);
        int sizeOfArray = array.size();
        int index = 0;
        int sizeOfString = s.length();
        String toOut = "";
        for(int i = 0; i<sizeOfString; i++){
            int charInt = s.charAt(i);
            if(charInt<30){
                toOut+="\n";
            }
            else{
                charInt-=32;
                charInt = ((array.get(index)+charInt)%95)+32;
                toOut+=(char)charInt;
            }
            index++;
            if(index == sizeOfArray){
                index = 0;
            }
        }
        return toOut;
    }
    private String getPlainText(String text, String key){
        ArrayList<Integer> array = createSubStituionArray(key);
        int sizeOfArray = array.size();
        int index = 0;

        String toOut = "";
        for(int i = 0; i<text.length(); i++){
            toOut += (char)(getShiftedIntForGetPlainText(text.charAt(i),array.get(index)));
            index++;
            if(index == sizeOfArray){
                index = 0;
            }
        }
        return toOut;
    }
    private int getShiftedIntForGetPlainText(int current, int shiftInt){
        int tobeShifted = 0;
        if(current == '\n'){
            return '\n';
        }
        current -=32;
        shiftInt = shiftInt%95;
        if(current>=shiftInt){
            tobeShifted = current-shiftInt;
        }
        else{
            tobeShifted = 95-(shiftInt-current);
        }
        tobeShifted+=32;
        return tobeShifted;
    }
    private String rotatetoCipher(String s, String key){
        String toOut = "";

        return toOut;
    }
    public String saveDetail(String userName, String plainText, String key, String url) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
        SqlHandler sqlHandler = new SqlHandler();
        String cipherText = substituteString(permuteData(plainText,key),key);
        sqlHandler.insertUser(userName,cipherText);
        FileReader fileReader = new FileReader();
        fileReader.writeToFile(url,cipherText);
        return cipherText;
    }
    public String encryptFileOnly(String fileName,String key){
        FileReader fileReader = new FileReader();
        String cipherText = substituteString(permuteData(fileReader.readFile(fileName),key),key);
        return cipherText;
    }
    public String getData(String userName, String key) throws SQLException {
        SqlHandler sqlHandler = new SqlHandler();
        if(!sqlHandler.checkUser(userName)){
            return "User Not Exit";
        }
        if(!keyHandler.CheckKey(key)){
            return "FAKE TEXT";
        }
        else{

            String cipherText = sqlHandler.getData(userName);
            String plainText = dePermutedata(getPlainText(cipherText,key),key);
            return plainText;
        }

    }
    public String getDataForFile(String cipherText,String key){
        String plainText = dePermutedata(getPlainText(cipherText,key),key);
        return plainText;
    }
    public String permuteData(String data, String key){
        String toOut = "";
        int[] intArray = getPermuteKey(key);
        int extra = (key.length()-(data.length()%key.length()));
        for(int i = 0; i<extra; i++){
            data+=" ";
        }
        int loopTimes = data.length()/key.length();
        int index = 0;
        for(int i = 0; i<loopTimes; i++){
            String tempToOut = "";
            for(int j = 0; j<key.length(); j++){
                tempToOut+=data.charAt(index+intArray[j]);

            }
            index+=key.length();
            toOut+=tempToOut;
        }
        return toOut;
    }
    public String dePermutedata(String data, String key){
        String toOut = "";
        int[] intArray = getPermuteKey(key);
        int loopTimes = data.length()/key.length();
        int index = 0;
        for(int i = 0; i<loopTimes; i++){
            String tempToOut = "";
            for(int j = 0; j<key.length(); j++){
                tempToOut+=data.charAt(index+indexOfInt(j,intArray));
            }
            index+=key.length();
            toOut+=tempToOut;
        }
        return toOut;
    }
    private int[] getPermuteKey(String key){
        char[] charArray = key.toCharArray();
        char[] charArrayOrigin = key.toCharArray();
        Arrays.sort(charArray);
        ArrayList<Character> tempCharArray = new ArrayList<>();
        int[] intArray = new int[8];
        for(int i = 0; i<key.length(); i++){
            intArray[i] = indexOfChar(charArrayOrigin[i],charArray)+ (Collections.frequency(tempCharArray, charArrayOrigin[i]));
            tempCharArray.add(charArrayOrigin[i]);
        }
        return intArray;
    }
    private int indexOfChar(char c, char[] array){
        for(int i = 0; i<array.length; i++){
            if(array[i]==c){
                return i;
            }
        }
        return -1;
    }
    private int indexOfInt(int c, int[] array){
        for(int i = 0; i<array.length; i++){
            if(array[i]==c){
                return i;
            }
        }
        return -1;
    }

}