package command.sch;

import java.util.ArrayList;
import java.util.List;

import database.EmployeeDB;
import model.CAREERLEVEL;
import model.CERTI;
import model.Employee;

public interface SearchOptionI {

    public List<Employee>  searchFirstName(String firstName, EmployeeDB db);
    public List<Employee>  searchLastName(String lastName, EmployeeDB db);
    public List<Employee>  searchPhoneNumberMid(String phoneNumMid, EmployeeDB db);
    public List<Employee>  searchPhoneNumberLast(String phoneNumLast, EmployeeDB db);
    public List<Employee>  searchBirthYear(String birthYear, EmployeeDB db);
    public List<Employee>  searchBirthMonth(String birthMon, EmployeeDB db);
    public List<Employee>  searchBirthDay(String birthDay, EmployeeDB db);
}
