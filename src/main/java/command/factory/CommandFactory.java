package command.factory;

import command.CommandExecutor;
import command.add.AddCommand;
import command.del.DeleteCommand;
import command.mod.ModifyCommand;
import command.sch.SearchCommand;
import model.UserRequest;

public class CommandFactory {

    final private CommandExecutor addCommand;
    final private CommandExecutor delCommand;
    final private CommandExecutor modCommand;
    final private CommandExecutor schCommand;
    final private CommandExecutor noneCommand;

    public CommandFactory(){
        addCommand = new AddCommand();
        schCommand = new SearchCommand();
        delCommand = new DeleteCommand(schCommand);
        modCommand = new ModifyCommand(schCommand);
        noneCommand = new NoneCommand();
    }

    public CommandExecutor GetCommand(UserRequest request){
        switch (request.getCommand()){
            case ADD:
                return addCommand;
            case DEL:
                return delCommand;
            case MOD:
                return modCommand;
            case SCH:
                return schCommand;
        }
        return noneCommand;
    }
}
