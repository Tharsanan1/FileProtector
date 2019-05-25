import java.io.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class FileReader  {
    public String readFile(String fileName) {
        String line = "";
        String data = "";
        try {
            java.io.FileReader fileReader = new java.io.FileReader(fileName);//C:\Users\kthar\Desktop\file.txt
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                data+=line;
                data+="\n";
            }
            data=data.substring(0,data.length()-1);
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            data = "File not found";
            System.out.println("Unable to open file '" +fileName + "'");
        } catch (IOException ex) {
            data = "File not found";
            System.out.println("Error reading file '"+ fileName + "'");
        }
        return data;
    }
    public void writeToFile(String fileName,String data) throws FileNotFoundException, UnsupportedEncodingException {
        try{
            String[] stringArray = data.split("\n");
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            for(int i = 0;  i<stringArray.length; i++){
                writer.println(stringArray[i]);
            }
            writer.close();
        }
        catch (FileNotFoundException ex){

        }

    }
}
