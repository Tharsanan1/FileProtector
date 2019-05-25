import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        KeyHandler keyHandler = new KeyHandler();
        Cipher cipher = new Cipher();
        String key = keyHandler.createKey();
        System.out.println("key: "+key);
        String plainText = "\nMy Name is Barry Lyndon and I am the FLASH";
        System.out.println("char at: "+(int)plainText.charAt(0)+" mm");
        //System.out.println("Plain Text: "+plainText+" \ncipher text: "+cipher.substituteString(plainText,key)+" \nplain Text again: "+cipher.getPlainText(cipher.substituteString(plainText,key),key)+" \nisEqual: "+plainText.equals(cipher.getPlainText(cipher.substituteString(plainText,key),key)));
        DisplayText displayTextFrame = new DisplayText();
        GUI newFrame = new GUI();
        ArrayList<Character> tempCharArray = new ArrayList<>();
        tempCharArray.add('w');
        tempCharArray.add('w');
        tempCharArray.add('e');
        tempCharArray.add('w');
        System.out.println("count: "+Collections.frequency(tempCharArray,'p'));
        FileReader fileReader = new FileReader();
        fileReader.readFile("");
        System.out.println("Origin:tharsanan is my name permuted:"+cipher.permuteData("tharsanan is my name","GDGGSGDS")+"depermuted:"+cipher.dePermutedata(cipher.permuteData("tharsanan is my name","GDGGSGDS"),"GDGGSGDS"));
    }
}