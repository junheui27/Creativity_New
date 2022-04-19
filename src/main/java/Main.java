import manager.EmployeeManager;
import model.UserRequest;
import model.UserRequestConverter;

import java.io.*;
import java.nio.file.InvalidPathException;
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

        try{
            isValidInputFileExistTrue(loadPath);
            inputs = readInput(loadPath);
            run(inputs,SavePath);



        }catch(Exception e) {
            System.out.println(e);
        }

        System.out.println("finish");
    }
    

    static boolean isValidInputFileExistTrue(String path) throws Exception {
    	File file = new File("./"+path);

        if(file.exists()==false){throw new FileNotFoundException(" file not exists");}
    	if(path.indexOf('\0')!=-1){throw new InvalidPathException(path," file not exists");}
    	return true;
    }


    
    static boolean isValidInputFormatTrue(String str) {
    	String regx = "^[A-Z]{3}\\,.*";
    	return Pattern.matches(regx, str);
    }


    static boolean isValidOutputFormatTrue(String str) {
    	String regx = "^[A-Z]{3}\\,.*";
    	return Pattern.matches(regx, str);
    }

    public static List<String> run(List<String> inputs,String savePath) throws Exception {

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            request.setOutputPath(savePath);
            manager.process(request);
        }
        return inputs;
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
