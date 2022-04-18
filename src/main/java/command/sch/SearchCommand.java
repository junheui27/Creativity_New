package command.sch;

import command.CommandExecutor;
import database.EmployeeDB;
import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class RequestObj {
    String key;
    String value;
    String option;

    RequestObj(String key, String value, String option) {
        this.key = key;
        this.value = value;
        this.option = option;
    }
}


public class SearchCommand implements CommandExecutor,SearchOptionI{

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
        List<Employee> resultEmpList = new ArrayList<>();

        if (isSearchCmd(request)) {
            RequestObj reqObj = makeReqObj(request);
            resultEmpList = SearchEmployeeList(reqObj, db);
        }
        return resultEmpList;
    }

    private RequestObj makeReqObj(UserRequest request) {
        String key = request.getArguments().get(0);
        String value = request.getArguments().get(1);
        String option = request.getOptions().get(0);

        return new RequestObj(key, value, option);
    }

    private boolean isSearchCmd(UserRequest request) {
        return request.getCommand().equals("SCH");
    }


    private List<Employee> SearchEmployeeList(RequestObj reqObj, EmployeeDB db) {

        if (reqObj.key.equals("employeeNum")) {
            return searchByEmpNum(reqObj, db);
        } else if (reqObj.key.equals("name")) {
            return searchByName(reqObj, db);
        } else if (reqObj.key.equals("phoneNum")) {
            return searchByPhoneNumber(reqObj, db);
        } else if (reqObj.key.equals("cl")) {
            return searchByCl(reqObj, db);
        } else if (reqObj.key.equals("birthday")) {
            return searchByBirthday(reqObj, db);
        } else if (reqObj.key.equals("certi")) {
            return searchByCerti(reqObj, db);
        }
        return new ArrayList<>();
    }


    public List<Employee> searchByBirthday(RequestObj reqObj, EmployeeDB db) {
        // -y / -m / -d :  생년월일의 연도로 검색 / -m : 생년월일의 월로 검색 / -d : 생년월일의 일로 검색
        switch (reqObj.option) {
            case "-y":
                return searchByBirthdayYear(reqObj, db);
            case "-m":
                return searchByBirthdayMonth(reqObj, db);
            case "-d":
                return searchByBirthdayDay(reqObj, db);
            default:
                return searchByKeyValue(reqObj.key, reqObj.value, db);
        }
    }

    public List<Employee> searchByPhoneNumber(RequestObj reqObj, EmployeeDB db) {

        // -m / -l : 	전화 번호 중간 자리로 검색 / -l : 전화 번호 뒷자리로 검색
        switch (reqObj.option) {
            case "-m":
                return searchByPhoneNumberMid(reqObj, db);
            case "-l":
                return searchByPhoneNumberLast(reqObj, db);
            default:
                return searchByKeyValue(reqObj.key, reqObj.value, db);
        }
    }

    public List<Employee> searchByName(RequestObj reqObj, EmployeeDB db) {
        // -f / -l:  	성명의 이름으로 검색 / -l : 성명의 성으로 검색
        switch (reqObj.option) {
            case "-f":
                return searchByFirstName(reqObj, db);
            case "-l":
                return searchByLastName(reqObj, db);
            default:
                return searchByKeyValue(reqObj.key, reqObj.value, db);
        }
    }


    public List<Employee> searchByEmpNum(RequestObj reqObj, EmployeeDB db) {
        return searchByKeyValue(reqObj.key, reqObj.value, db);
    }

    private List<Employee> searchByKeyValue(String key, String value, EmployeeDB db) {
        return db.findEmployeeByColumn(key, value);
    }

    @Override
    public List<Employee> searchByFirstName(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String firstName = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, firstName, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrFullName = itrEmployee.getName();
            String itrFirstName = itrFullName.substring(0, itrFullName.indexOf(" "));

            if (firstName.equals(itrFirstName)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    @Override
    public List<Employee> searchByLastName(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String lastName = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, lastName, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrFullName = itrEmployee.getName();
            String itrLasttName = itrFullName.substring(itrFullName.lastIndexOf(" ") + 1);

            if (lastName.equals(itrLasttName)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    @Override
    public List<Employee> searchByPhoneNumberMid(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String phoneNumberMid = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, phoneNumberMid, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrPhoneNumber = itrEmployee.getPhoneNumber();
            String itrPhoneNumberMid = itrPhoneNumber.split("-")[1];

            if (phoneNumberMid.equals(itrPhoneNumberMid)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    @Override
    public List<Employee> searchByPhoneNumberLast(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String phoneNumberLast = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, phoneNumberLast, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrPhoneNumber = itrEmployee.getPhoneNumber();
            String itrPhoneNumberLast = itrPhoneNumber.split("-")[2];

            if (phoneNumberLast.equals(itrPhoneNumberLast)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    public List<Employee> searchByCl(RequestObj reqObj, EmployeeDB db) {
        return searchByKeyValue(reqObj.key, reqObj.value, db);
    }

    @Override
    public List<Employee> searchByBirthdayYear(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String birthdayYear = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, birthdayYear, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrBirthday = itrEmployee.getBirthday();
            String itrBirthdayYear = itrBirthday.substring(0, 4);

            if (birthdayYear.equals(itrBirthdayYear)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    @Override
    public List<Employee> searchByBirthdayMonth(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String birthdayMonth = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, birthdayMonth, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrBirthday = itrEmployee.getBirthday();
            String itrBirthdayMonth = itrBirthday.substring(4, 6);

            if (birthdayMonth.equals(itrBirthdayMonth)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    @Override
    public List<Employee> searchByBirthdayDay(RequestObj reqObj, EmployeeDB db) {
        List<Employee> emplyeeList = new ArrayList<>();
        String birthdayDay = reqObj.value;
        emplyeeList = searchByKeyValue(reqObj.key, birthdayDay, db);

        List<Employee> resultList = new ArrayList<>();
        for (Iterator<Employee> itr = emplyeeList.iterator(); itr.hasNext(); ) {
            Employee itrEmployee = itr.next();
            String itrBirthday = itrEmployee.getBirthday();
            String itrBirthdayDay = itrBirthday.substring(6, 8);

            if (birthdayDay.equals(itrBirthdayDay)) {
                resultList.add(itrEmployee);
            }
        }
        return resultList;
    }

    public List<Employee> searchByCerti(RequestObj reqObj, EmployeeDB db) {
        return searchByKeyValue(reqObj.key, reqObj.value, db);
    }
}
