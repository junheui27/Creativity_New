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

    public static void main(String arg[]) throws IOException {
        System.out.println("start");

        loadPath = arg[0];
        SavePath = arg[1];
        List<String> inputs = new ArrayList<String>();

        if(isValidInputFileExistTrue(loadPath))
        {
            inputs = readInput(loadPath);
            run(inputs,SavePath);

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

    public static void  run(List<String> inputs,String savePath){

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            request.setOutputPath(savePath);
            manager.process(request);
        }

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

}
