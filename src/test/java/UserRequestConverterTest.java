import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.UserRequest;
import model.UserRequestConverter;

class UserRequestConverterTest {

	@Test
	void testConvert() {    
		UserRequestConverter tc =new UserRequestConverter();
		List<String> result = new ArrayList();
	    result.add("ADD,,,,99000101,A LEE,CL3,010-1234-5678,19920101,PRO");
	    result.add("ADD,,,,99100201,B KIM,CL3,010-2341-5678,19910202,ADV");
	    result.add("ADD,,,,00000301,C CHOI,CL2,010-3412-5678,19940403,EX");
	    result.add("ADD,,,,18000301,D SEO,CL2,010-4123-5678,19991231,PRO");
	    result.add("ADD,,,,21000301,E KANG,CL1,010-1234-6785,19990111,ADV");
	    result.add("ADD,,,,99000102,AA LEE,CL4,010-1234-5679,19930101,EX");
	    result.add("ADD,,,,99100202,BB KIM,CL4,010-2341-5679,19920202,PRO");
	    result.add("ADD,,,,00000302,CC CHOI,CL3,010-3412-5679,19950403,ADV");
	    result.add("ADD,,,,18000302,DD SEO,CL2,010-4123-5679,19991231,PRO");
	    result.add("ADD,,,,21000302,EE KANG,CL1,010-1234-6789,20000111,ADV");
	    result.add("ADD,,,,09000302,FF MOON,CL1,010-1234-6666,19901131,ADV");
	    result.add("SCH,-p,,,employeeNum,00000301");
	    result.add("DEL,-p,,,employeeNum,00000301");
	    result.add("SCH,-p,,,employeeNum,00000301");
	    result.add("MOD,-p,,,employeeNum,00000302,phoneNum,010-1111-2222");
	    result.add("SCH,,,,birthday,19991231");
	    result.add("DEL,,,,birthday,19991231");
	    result.add("MOD,-p,,,cl,CL1,cl,CL2");
	    result.add("DEL,-p,-f,,name,AA");
	    result.add("MOD,-p,-l,,phoneNum,2222,certi,PRO");
	    result.add("SCH,-p,-y,,birthday,1992");
	    for(String str: result) {
		    UserRequest Test = tc.convert(str);

		    String result_str = "";
		    result_str += Test.getCommand() + " ";

            for(String in : Test.getOptions()) {
            	result_str += in + " ";
            }
            for(String in : Test.getArguments()) {
            	result_str += in + " ";
            }
            System.out.println(result_str);
            
            String[] split = str.split(",");
            String compare = "";
            for(String str2 : split) {
            	if(str2.length() < 1) continue;
            	compare+=str2 + " ";
            }
            assertEquals(result_str, compare);
	    }
	}

}
