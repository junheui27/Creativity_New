import manager.EmployeeManager;
import model.UserRequest;
import model.UserRequestConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static EmployeeManager manager = new EmployeeManager();

    public static void main(String arg[]) throws IOException {
        System.out.println("start");

        // input.txt -> arg[0]
        ArrayList<String> inputs = readInput("input.txt");
        run(inputs);

        System.out.println("finish");
    }

    public static void run(ArrayList<String> inputs) {

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            manager.process(request);

            System.out.println(input);
        }
    }

    public static ArrayList<String> readInput(String str) throws IOException {

        ArrayList<String> inputarray = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/junheui/IdeaProjects/Creativity_New/src/"+str));
        String str2 = "";

        while((str2 = br.readLine())!=null){
            inputarray.add(str2);

        }
        br.close();

        return new ArrayList<String>();
    }
}
