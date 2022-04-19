package printer;

import model.COMMAND;
import model.Employee;

import java.util.List;

public interface IPrintOption {

    public void print(COMMAND command, List<Employee> results);
}
