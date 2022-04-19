package printer;

import lombok.Builder;
import model.COMMAND;
import model.Employee;

import java.util.List;

public interface IPrintOption {

    List<String> print(COMMAND command, List<Employee> results);
}
