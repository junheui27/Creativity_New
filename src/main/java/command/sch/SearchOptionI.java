package command.sch;

import java.util.ArrayList;
import java.util.List;

import database.EmployeeDB;
import model.Employee;

public interface SearchOptionI {

    public List<Employee> searchByFirstName(RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByLastName(RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByPhoneNumberMid(RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByPhoneNumberLast(RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByBirthdayYear(RequestObj reqObj, EmployeeDB db);
    public List<Employee> searchByBirthdayMonth(RequestObj reqObj, EmployeeDB db);
    public List<Employee>  searchByBirthdayDay(RequestObj reqObj, EmployeeDB db);
}
