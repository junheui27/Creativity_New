import manager.EmployeeManager;
import model.UserRequest;
import model.UserRequestConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    //public EmployeeManager manager = new EmployeeManager();
    private static String loadPath ="";
    private static String SavePath ="";

    public static void main(String arg[]) throws IOException {
        System.out.println("start");

        loadPath = arg[0];
        SavePath = arg[1];
        List<String> inputs = new ArrayList<String>();

        if(isValidInputFileExistTrue(loadPath)) 
        {
        	inputs = readInput(loadPath);
            List<String> outputs = run(inputs);
            writeOutput(SavePath,outputs);
        }
        System.out.println("finish");
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

        EmployeeManager manager = new EmployeeManager();
        List<String> outputResults = new ArrayList<>();

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            List<String> outputForOneInput = manager.process(request);
            if(outputForOneInput != null && outputForOneInput.size() > 0){
                outputResults.addAll(outputForOneInput);
            }
        }

        return outputResults;
    }

    public static List<String> readInput(String str) throws IOException {

        List<String> inputArray = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader("./"+str));
        String str2 = "";

        while((str2 = br.readLine())!=null){
            if(isValidInputFormatTrue(str2))
                inputArray.add(str2);
        }
        br.close();

        return inputArray;
    }
    
    public static  void writeOutput(String str, List<String> outputs) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + str,true));

        for(String str2 : outputs){
            bw.write(str2 + "\n");
        }
        bw.flush();
        bw.close();
    }
}