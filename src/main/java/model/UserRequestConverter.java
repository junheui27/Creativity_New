package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserRequestConverter {
    public static UserRequest convert(String input){
    	UserRequest A = new UserRequest();
    	
    	String[] split = input.split(",");
    	
        A.setCommand(getcommand(split[0]));

    	A.setArguments(getArgument(split));
    	
    	A.setOptions(getOption(split));	
    	
        return A;
    }


    public static boolean isValidCommandTrue(String str) {
    	return str.equals("ADD") || str.equals("DEL") || str.equals("MOD") || str.equals("SCH");
    }

    public static boolean isValidnameTrue(String str, String option) {

    	String regx_name = "^[A-Z]*\\s[A-Z]*$";
    	if(option.equals("-f") || option.equals("-l")) regx_name = "^[A-Z]*$";
    	return Pattern.matches(regx_name, str) && str.length() <= 15;
    }
    
    public static boolean isValidclTrue(String str) {
    	String regx_cl = "^CL[1-4]$";
    	return Pattern.matches(regx_cl, str);
    }
    
    public static boolean isValidphoneNumTrue(String str, String option) {
    	String regx_phone = "^010-(?:\\d{3}|\\d{4})-\\d{4}$";
    	if(option.equals("-m") || option.equals("-l")) regx_phone = "^\\d{4}$";
    	return Pattern.matches(regx_phone, str);
    }
    
    public static boolean isValidbirthdaytTrue(String str, String option) {
    	String regx_brith = "^[12]\\d{7}$";
    	if(option.equals("-y")) regx_brith = "^[12]\\d{3}$";
    	else if(option.equals("-m")) regx_brith = "^\\d{2}$";
    	else if(option.equals("-d")) regx_brith = "^\\d{2}$";
    	return Pattern.matches(regx_brith, str);
    }
    
    public static boolean isValidcertiTrue(String str) {
    	return str.equals("PRO") || str.equals("ADV") || str.equals("EX");
    }
    
    public static boolean isValidemployeeNumTrue(String str) {
    	String regx_employnum = "^\\d{8}$";
    	return Pattern.matches(regx_employnum, str);
    }
    
    public static boolean isValidAddArgsTrue(String[] str) {
    	String option = "";
    	return isValidemployeeNumTrue(str[4]) && isValidnameTrue(str[5],option) && isValidclTrue(str[6])
    			&& isValidphoneNumTrue(str[7],option) && isValidbirthdaytTrue(str[8],option) && isValidcertiTrue(str[9]);
    }

    public static boolean isValidFirstArgsTrue(String str) {
    	if(str.equals("employeeNum")||str.equals("name")||str.equals("cl")||
    			str.equals("phoneNum")||str.equals("birthday")||str.equals("certi")) return true;
    	return false;
    }

    public static boolean isValidSecondArgsTrue(String str1,String str2, String option) {

    	if(str1.equals("employeeNum")) return isValidemployeeNumTrue(str2);
    	else if(str1.equals("name")) return isValidnameTrue(str2,option);
    	else if(str1.equals("cl")) return isValidclTrue(str2);
    	else if(str1.equals("phoneNum")) return isValidphoneNumTrue(str2,option);
    	else if(str1.equals("birthday")) return isValidbirthdaytTrue(str2,option);
    	else if(str1.equals("certi")) return isValidcertiTrue(str2);
    	return false;
    }
    
    
    public static boolean isValidOptionTrue(String str) {
    	String regx = "^\\-[dflmpy]";
    	return Pattern.matches(regx, str);
    }
    
    public static COMMAND getcommand(String str) {
    	if(isValidCommandTrue(str)) {
    		return COMMAND.valueOf(str);
    	}
    	return null; // valid 오류 시 리턴 어떻게 할건지?
    }

    public static List<String> getArgument(String []str) {
    	List<String> Argument = new ArrayList();
    	String option = str[2];

    	if(str[0].contains("ADD") && isValidAddArgsTrue(str)) {
        	for(int index = 4 ; index < str.length ; index++) {
        			Argument.add(str[index]);
        	}
    	}
    	else if((str[0].contains("DEL") || str[0].contains("SCH"))){

    		if(isValidFirstArgsTrue(str[4]) && isValidSecondArgsTrue(str[4],str[5],option)) {
    			Argument.add(str[4]);
    			Argument.add(str[5]);
    		}
    	}
    	else if(str[0].contains("MOD")) {

    		if(isValidFirstArgsTrue(str[4]) && isValidSecondArgsTrue(str[4],str[5],option)) {
    			Argument.add(str[4]);
    			Argument.add(str[5]);
    		}
    		//if(isValidFirstArgsTrue(str[6]) && isValidSecondArgsTrue(str[6],str[7],option)) {
			if(isValidFirstArgsTrue(str[6])){
    			Argument.add(str[6]);
    			Argument.add(str[7]);
    		}
    	}
    	return Argument;
    }
    
    public static List<String> getOption(String []str) {
	   	 List<String> Argument = new ArrayList();
		 
	   	for(int index = 1 ; index <= 3 ; index++) {
	   		if(isValidOptionTrue(str[index]) || str[index].equals(" ") || str[index].isEmpty())
	   			Argument.add(str[index]);
	   	}
	   	return Argument;
    }
}
