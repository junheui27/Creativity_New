package command.del;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import command.CommandExecutor;
import command.add.AddCommand;
import command.sch.SearchCommand;
import database.EmployeeDB;
import model.CAREERLEVEL;
import model.COMMAND;
import model.Employee;
import model.UserRequest;

class DeleteCommandTest {
	CommandExecutor commandExecutor;
	SearchCommand searchCommand;
	EmployeeDB employeeDB;
	@BeforeEach
	public void initialise() {
		if(searchCommand == null)
			searchCommand = mock(SearchCommand.class);
		if(commandExecutor==null)
			commandExecutor = new DeleteCommand(searchCommand);
		if(employeeDB==null)
			employeeDB = new EmployeeDB();
	}
	
	
	@Test
	@DisplayName("DeleteCommandTest 성공(Mock객체활용)") 
	void runTestSuccess() {
		UserRequest ur = new UserRequest();
		ArrayList<String> ArgumentList = new ArrayList();
		ArgumentList.add("cl");
		ArgumentList.add("CL3");
		
		COMMAND command = null;
		command = COMMAND.DEL;
		List<String> optionList = new ArrayList();
		
		ur.setCommand(command);
		ur.setOptions(optionList);
		ur.setArguments(ArgumentList);
		
		List<Employee> employeeListTemp = new ArrayList<Employee>();
		Employee employeeTemp = new Employee();
		employeeTemp.setCl(CAREERLEVEL.CL3);
		employeeListTemp.add(employeeTemp);
		try {
			when(commandExecutor.run(ur, employeeDB)).thenReturn(employeeListTemp);
			List<Employee> employee = commandExecutor.run(ur, employeeDB);
			assertEquals(employee.get(0).getCl(), CAREERLEVEL.CL3);
		} catch (Exception e) {
			// TODO Aut o-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("DeleteCommandTest 성공2(실제 SearchCommand 객체 활용)")
	void runTestSucces2() {
		
	}

}
