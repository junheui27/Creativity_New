package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    private COMMAND command;
    private List<String> options;
    private List<String> arguments;
}