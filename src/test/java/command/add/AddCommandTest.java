package command.add;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
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
	void runTestSuccess1() {
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
		assertEquals(list.get(0).getFirstName(), "KYUMOK");
	}
	
	@Test
	void runTestFail1() {
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
}
