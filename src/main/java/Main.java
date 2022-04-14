import manager.EmployeeManager;
import model.UserRequest;
import model.UserRequestConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static EmployeeManager manager = new EmployeeManager();

    public static void main(String arg[]) throws IOException {
        System.out.println("start");

        List<String> inputs = ReadInput();
        Run(inputs);

        System.out.println("finish");
    }

    private static void Run(List<String> inputs) {

        for (String input : inputs){
            UserRequest request = UserRequestConverter.Convert(input);
            manager.Process(request);
        }
    }

    private static List<String> ReadInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        br.close();

        return new ArrayList<String>();
    }
}
