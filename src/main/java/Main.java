import manager.EmployeeManager;
import model.UserRequest;
import model.UserRequestConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static EmployeeManager manager = new EmployeeManager();
    private String loadPath ="";
    private String SavePath ="";

    public static void main(String arg[]) throws IOException {
        System.out.println("start");

        // loadPath = arg[0];
        // savePath = arg[1];
        // input.txt -> arg[0]
        ArrayList<String> inputs = readInput("input.txt");
        ArrayList<String> outputs = run(inputs);
        writeOutput("output.txt",outputs);

        System.out.println("finish");
    }

    public static ArrayList<String> run(ArrayList<String> inputs){

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            manager.process(request);
        }
        return null;
    }

    public static ArrayList<String> readInput(String str) throws IOException {

        ArrayList<String> inputArray = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader("./"+str));
        String str2 = "";

        while((str2 = br.readLine())!=null){
            inputArray.add(str2);
        }
        br.close();

        return inputArray;
    }

    public static  void writeOutput(String str, ArrayList<String> outputs) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./" + str));

        for(String str2 : outputs){
            bw.write(str2);
        }
        bw.flush();
        bw.close();
    }
}
