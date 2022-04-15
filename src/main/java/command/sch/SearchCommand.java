package command.sch;

import command.CommandExecutor;
import database.EmployeeDB;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchCommand implements CommandExecutor, SearchOptionI {

    private List<Employee> resultEmpList = new ArrayList<>();

    @Override
    public List<Employee> run(UserRequest request, EmployeeDB db) {
        try{
            if(!request.getCommand().equals("SCH")) {
                System.out.println("MOD 입력이 잘못 되었습니다.");
                //throw new Exception("mod err");
                String key= request.getArguments().get(0);
                String value= request.getArguments().get(1);
                String option = null;
                if(request.getOptions().size() > 0){
                    option= request.getOptions().get(0);
                }

                if(key.equals("employeeNum")) {
                    resultEmpList = searchEmpNum(value,db);
                }
                else if(key.equals("name")){
                    // -f / -l:  	성명의 이름으로 검색 / -l : 성명의 성으로 검색
                    switch (option) {
                        case "-f":
                            resultEmpList = searchFirstName(value,db);
                        case "-l":
                            resultEmpList = searchLastName(value,db);
                        default:
                            resultEmpList = searchName(value,db);
                    }
                }
                else if(key.equals("cl")){
                    resultEmpList = searchCerti(value,db);
                }
                else if(key.equals("phoneNum")){
                    // -m / -l : 	전화 번호 중간 자리로 검색 / -l : 전화 번호 뒷자리로 검색
                    switch (option){
                        case "-m":
                            resultEmpList = searchPhoneNumberMid(value,db);
                        case "-l":
                            resultEmpList = searchPhoneNumberLast(value,db);
                        default:
                            resultEmpList = searchPhoneNumber(value,db);
                    }
                }
                else if(key.equals("cl")){
                    resultEmpList = searchCl(value,db);
                }
                else if(key.equals("birthday")){
                    // -y / -m / -d :  생년월일의 연도로 검색 / -m : 생년월일의 월로 검색 / -d : 생년월일의 일로 검색
                    switch (option){
                        case "-y":
                            resultEmpList = searchBirthYear(value,db);
                        case "-m":
                            resultEmpList = searchBirthMonth(value,db);
                        case "-d":
                            resultEmpList = searchBirthDay(value,db);
                        default:
                            resultEmpList = searchBirth(value,db);
                    }
                }
                else if(key.equals("certi")){
                    resultEmpList = searchCerti(value,db);
                }
                else{
                    System.out.println("key 값이 잘못 들어왔습니다.");
                    throw new Exception("key err");
                }
            }
        }
        catch (Exception e){

        }
        for(Employee emp: resultEmpList){
            System.out.println(emp);
        }
        return resultEmpList;
    }


    public List<Employee> searchEmpNum(String empNum, EmployeeDB db) {
        List<Employee> resultList = new ArrayList<>();
        if(db.empNumberMap.containsKey(empNum)){
            resultList.add(db.empNumberMap.get(empNum));
        }
        return resultList;
    }

    private List<Employee> searchMapKeyValue(String key, EmployeeDB db) {
        List<Employee> resultList = new ArrayList<>();
        for (Map.Entry<String, Employee> entry : db.nameMap.get(key).entrySet()) {
            resultList.add(entry.getValue());
        }
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

    public List<Employee> searchCl(String cl, EmployeeDB db) {
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

    public List<Employee> searchCerti(String certi, EmployeeDB db) {
        //List<Employee> resultList = new ArrayList<>();
        return searchMapKeyValue(certi, db);
    }
}
