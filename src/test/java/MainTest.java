import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void readInputTest() throws IOException {
        String str = "input.txt";
        ArrayList<String> array = Main.readInput(str);
        ArrayList<String> result = new ArrayList<String>();
        result.add("ADD,,,,99000101,A LEE,CL3,010-1234-5678,19920101,PRO");
        result.add("ADD,,,,99100201,B KIM,CL3,010-2341-5678,19910202,ADV");
        result.add("ADD,,,,00000301,C CHOI,CL2,010-3412-5678,19940403,EX");
        result.add("ADD,,,,18000301,AB SEO,CL2,010-4123-5678,19981231,PRO");
        result.add("ADD,,,,21000301,BC KANG,CL1,010-1234-6785,19990111,ADB");

        for(int i =0 ; i < array.size(); i++){
            assertEquals(array.indexOf(i), result.indexOf(i));
        }
    }
}