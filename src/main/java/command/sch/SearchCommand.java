package command.sch;

import command.CommandExecutor;
import database.EmployeeDB;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchCommand implements CommandExecutor, SearchOptionI {

    class ReqObj {
        String key;
        String value;
        String option;

        public ReqObj(String key, String value, String option) {
            this.key = key;
            this.value = value;
            this.option = option;
        }
    }

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
        List<Employee> resultEmpList = new ArrayList<>();

        if (isSearchCmd(request)) {
            ReqObj reqObj = checkRequest(request);
            return SearchEmployInfo(db, reqObj);
        }
        return resultEmpList;
    }


    private List<Employee> SearchEmployInfo(EmployeeDB db, ReqObj reqObj) {
        if (reqObj.key.equals("employeeNum")) {
            return searchByEmpNum(reqObj.value, db);
        }
        else if (reqObj.key.equals("name")) {
            return searchByName(db, reqObj);
        }
        else if (reqObj.key.equals("cl")) {
            return searchByCl(reqObj.value, db);
        }
        else if (reqObj.key.equals("phoneNum")) {
            return searchByPhoneNum(db, reqObj);
        }
        else if (reqObj.key.equals("cl")) {
            return searchByCl(reqObj.value, db);
        }
        else if (reqObj.key.equals("birthday")) {
            return searchByBirth(db, reqObj);
        }
        else if (reqObj.key.equals("certi")) {
            return searchByCerti(reqObj.value, db);
        }
        return null;
    }


    private List<Employee> searchByBirth(EmployeeDB db, ReqObj reqObj) {
        // -y / -m / -d :  ìƒ�ë…„ì›”ì�¼ì�˜ ì—°ë�„ë¡œ ê²€ìƒ‰ / -m : ìƒ�ë…„ì›”ì�¼ì�˜ ì›”ë¡œ ê²€ìƒ‰ / -d : ìƒ�ë…„ì›”ì�¼ì�˜ ì�¼ë¡œ ê²€ìƒ‰
        switch (reqObj.option) {
            case "-y":
                return searchBirthYear(reqObj.value, db);
            case "-m":
                return searchBirthMonth(reqObj.value, db);
            case "-d":
                return searchBirthDay(reqObj.value, db);
            default:
                return searchBirth(reqObj.value, db);
        }
    }

    private List<Employee> searchByPhoneNum(EmployeeDB db, ReqObj reqObj) {
        // -m / -l : 	ì „í™” ë²ˆí˜¸ ì¤‘ê°„ ìž�ë¦¬ë¡œ ê²€ìƒ‰ / -l : ì „í™” ë²ˆí˜¸ ë’·ìž�ë¦¬ë¡œ ê²€ìƒ‰
        switch (reqObj.option) {
            case "-m":
                return searchPhoneNumberMid(reqObj.value, db);
            case "-l":
                return searchPhoneNumberLast(reqObj.value, db);
            default:
                return searchPhoneNumber(reqObj.value, db);
        }
    }

    private List<Employee> searchByName(EmployeeDB db, ReqObj reqObj) {
        // -f / -l:  	ì„±ëª…ì�˜ ì�´ë¦„ìœ¼ë¡œ ê²€ìƒ‰ / -l : ì„±ëª…ì�˜ ì„±ìœ¼ë¡œ ê²€ìƒ‰
        switch (reqObj.option) {
            case "-f":
                return searchFirstName(reqObj.value, db);
            case "-l":
                return searchLastName(reqObj.value, db);
            default:
                return searchName(reqObj.value, db);
        }
    }

    private ReqObj checkRequest(UserRequest request) {
        String key = request.getArguments().get(0);
        String value = request.getArguments().get(1);
        String option = request.getOptions().get(0);

        return new ReqObj(key, value, option);
    }

    private boolean isSearchCmd(UserRequest request) {
        return request.getCommand().equals("SCH");
    }


    public List<Employee> searchByEmpNum(String empNum, EmployeeDB db) {
        List<Employee> resultList = new ArrayList<>();
//        if (db.empNumberMap.containsKey(empNum)) {
//            resultList.add(db.empNumberMap.get(empNum));
//        }
        return resultList;
    }

    private List<Employee> searchMapKeyValue(String key, EmployeeDB db) {
        List<Employee> resultList = new ArrayList<>();
//        for (Map.Entry<String, Employee> entry : db.nameMap.get(key).entrySet()) {
//            resultList.add(entry.getValue());
//        }
        return resultList;
    }

    public List<Employee> searchName(String name, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(name, db);
    }

    @Override
    public List<Employee> searchFirstName(String firstName, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(firstName, db);
    }

    @Override
    public List<Employee> searchLastName(String lastName, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(lastName, db);
    }


    public List<Employee> searchPhoneNumber(String phoneNum, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(phoneNum, db);
    }

    @Override
    public List<Employee> searchPhoneNumberMid(String phoneNumMid, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(phoneNumMid, db);
    }

    @Override
    public List<Employee> searchPhoneNumberLast(String phoneNumLast, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(phoneNumLast, db);
    }

    public List<Employee> searchByCl(String cl, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(cl, db);
    }

    public List<Employee> searchBirth(String birth, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(birth, db);
    }

    @Override
    public List<Employee> searchBirthYear(String birthYear, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(birthYear, db);
    }

    @Override
    public List<Employee> searchBirthMonth(String birthMon, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(birthMon, db);
    }

    @Override
    public List<Employee> searchBirthDay(String birthDay, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(birthDay, db);
    }

    public List<Employee> searchByCerti(String certi, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(certi, db);
    }
}
