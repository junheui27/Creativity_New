package command.add;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import command.CommandExecutor;
import database.EmployeeDB;
import model.COMMAND;
import model.Employee;
import model.UserRequest;


class AddCommandTest {
	AddCommand addCommand;
	@BeforeEach
	public void initialise() {
		addCommand = new AddCommand();
	}
	@Test
	@DisplayName("[정상Case] 정상적으로 User정보 add 후 반환 되는지")
	void runTestSuccess1() throws Exception {
		UserRequest ur = new UserRequest();
		ArrayList<String> ArgumentList = new ArrayList();
		ArgumentList.add("18050301");
		ArgumentList.add("KYUMOK KIM");
		ArgumentList.add("CL2");
		ArgumentList.add("010-9777-6055");
		ArgumentList.add("19980906");
		ArgumentList.add("PRO");
		
		COMMAND command = null;
		command = COMMAND.ADD;
		List<String> optionList = new ArrayList();
		
		ur.setCommand(command);
		ur.setOptions(optionList);
		ur.setArguments(ArgumentList);
		
	    EmployeeDB employeeDB = new EmployeeDB();
		List<Employee> list = addCommand.run(ur, employeeDB);
		
		assertEquals(list.size(), 1);
	}
	
	@Test
	@DisplayName("[비정상Case] run 함수에 인자가 null이 들어갈 경우")
	void runTestFail1() throws Exception {
		UserRequest ur = new UserRequest();
		ArrayList<String> ArgumentList = new ArrayList();
		ArgumentList.add("18050301");
		ArgumentList.add("KYUMOK KIM");
		ArgumentList.add("CL2");
		ArgumentList.add("010-9777-6055");
		ArgumentList.add("19980906");
		ArgumentList.add("PRO");
		
		COMMAND command = null;
		command = COMMAND.ADD;
		List<String> optionList = new ArrayList();
		
		ur.setCommand(command);
		ur.setOptions(optionList);
		ur.setArguments(ArgumentList);
		
		List<Employee> list = addCommand.run(ur, null);
		
		assertEquals(list.size(), 0);
	}
	
	@Test
	@DisplayName("[비정상Case] 사번 8자리가 아니면 비어있는 리스트 반환")
	void runTestFail2() throws Exception {
		UserRequest ur = new UserRequest();
		ArrayList<String> ArgumentList = new ArrayList();
		ArgumentList.add("18050301123123");
		ArgumentList.add("KYUMOK KIM");
		ArgumentList.add("CL2");
		ArgumentList.add("010-9777-6055");
		ArgumentList.add("19980906");
		ArgumentList.add("PRO");
		
		COMMAND command = null;
		command = COMMAND.ADD;
		List<String> optionList = new ArrayList();
		
		ur.setCommand(command);
		ur.setOptions(optionList);
		ur.setArguments(ArgumentList);
		EmployeeDB employeeDB = new EmployeeDB();

		List<Employee> ret = addCommand.run(ur, employeeDB);
		Assertions.assertTrue(ret.size()==0);

	}
}
