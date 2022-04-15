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


        List<String> inputs = readInput();
        run(inputs);

        System.out.println("finish");
    }

    private static void run(List<String> inputs) {

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            manager.process(request);

            System.out.println(input);
        }
    }

    private static List<String> readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/junheui/IdeaProjects/Creativity_New/src/input.txt"));

        String str;

        while((str = br.readLine())!=null){
            System.out.println(str);
        }
        br.close();

        return new ArrayList<String>();
    }
}
