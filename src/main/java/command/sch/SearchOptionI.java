package command.sch;

import java.util.List;

import database.EmployeeDB;
import model.Employee;

public interface SearchOptionI {

    public List<Employee> searchByFirstName(SearchCommand.RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByLastName(SearchCommand.RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByPhoneNumberMid(SearchCommand.RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByPhoneNumberLast(SearchCommand.RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByBirthdayYear(SearchCommand.RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByBirthdayMonth(SearchCommand.RequestObj reqObj, EmployeeDB db);
    public List<Employee>  searchByBirthdayDay(SearchCommand.RequestObj reqObj, EmployeeDB db);
}
