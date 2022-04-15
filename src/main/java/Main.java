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

    public static void main(String arg[]) throws Exception {
        System.out.println("start");

        List<String> inputs = readInput();
        run(inputs);

        System.out.println("finish");
    }

    private static void run(List<String> inputs) throws Exception {

        for (String input : inputs){
            UserRequest request = UserRequestConverter.convert(input);
            manager.process(request);
        }
    }

    private static List<String> readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        br.close();

        return new ArrayList<String>();
    }
}
