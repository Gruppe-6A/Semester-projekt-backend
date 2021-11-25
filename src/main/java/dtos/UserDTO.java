package dtos;

import entities.RenameMe;
import entities.Role;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String userName;
    private String userPass;
    private List<String> roleList = new ArrayList<>();
    private RoleDTO roleDTO;
    public UserDTO(String userName, String userPass, List<String> roleList) {
        this.userName = userName;
        this.userPass = userPass;
        this.roleList = roleList;
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roleList = user.getRolesAsStrings();
    }

    public static List<UserDTO> getDtos(List<User> user){
        List<UserDTO> userdto = new ArrayList();
        user.forEach(us->userdto.add(new UserDTO(us)));
        return userdto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }
}
