package dtos;
import entities.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
    private String roleName;
    private List<UserDTO> userList;
    private List<Role> roleList;


    public RoleDTO(Role role) {
        this.roleName = roleName;
        this.userList = userList;
    }

    public static List<RoleDTO> getDtos(List<Role> roles){
        List<RoleDTO> roledtos = new ArrayList();
        roles.forEach(rl->roledtos.add(new RoleDTO(rl)));
        return roledtos;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
