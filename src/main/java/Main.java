import manager.EmployeeManager;
import model.UserRequest;
import model.UserRequestConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static EmployeeManager manager = new EmployeeManager();
    private static String loadPath ="";
    private static String SavePath ="";

    public static void main(String arg[]) {
        loadPath = arg[0];
        SavePath = arg[1];
        List<String> inputs = new ArrayList<String>();
        
        if(!isValidInputFileExistTrue(loadPath)){
            System.out.println("no file");
            return;
        }

        inputs = readInput(loadPath);
        if(inputs == null || inputs.size() == 0){
            System.out.println("wrong file");
            return;
        }

        List<String> outputs = run(inputs);
        writeOutput("SavePath",outputs);
    }
    

    static boolean isValidInputFileExistTrue(String str) {
    	File file = new File("./"+str); 
    	return file.exists();
    }


    
    static boolean isValidInputFormatTrue(String str) {
    	String regx = "^[A-Z]{3}\\,.*";
    	return Pattern.matches(regx, str);
    }


    static boolean isValidOutputFormatTrue(String str) {
    	String regx = "^[A-Z]{3}\\,.*";
    	return Pattern.matches(regx, str);
    }

    public static List<String> run(List<String> inputs){

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            manager.process(request);
        }
        return inputs;
    }

    public static List<String> readInput(String str){

        List<String> inputArray = new ArrayList();
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("./"+str));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return inputArray;
        }

        try{
            String str2 = "";

            while((str2 = br.readLine())!=null){
                if(isValidInputFormatTrue(str2))
                    inputArray.add(str2);
            }
            br.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return inputArray;
    }

    public static  void writeOutput(String str, List<String> outputs) {

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("./" + str));

            for(String str2 : outputs){
                bw.write(str2 + "\n");
            }
            bw.flush();
            bw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
