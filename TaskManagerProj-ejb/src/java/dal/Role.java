package dal;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public enum Role {
    ADMIN(1) {
        @Override
        public String getRusName() {
            return "Администратор";
        }
    },
    MANAGER(2) {
        @Override
        public String getRusName() {
            return "Менеджер";
        }
    },
    USER(3) {
        @Override
        public String getRusName() {
            return "Пользователь";
        }
    },
    UNKNOWN(4) {
        @Override
        public String getRusName() {
            return "Не определено";
        }
    };

    private Integer roleCode;

    Role(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    @Override
    public String toString() {
        return "Role{" + "roleCode=" + roleCode + '}';
    }

    public String getRusName() {
        return "Российское сокращение";
    }

    public static Integer getRoleCodeByRUname(String name) {

        switch (name) {
            case ("Администратор"):
                return Role.ADMIN.roleCode;
            case ("Менеджер"):
                return Role.MANAGER.roleCode;
            case ("Пользователь"):
                return Role.USER.roleCode;
            default:
                return Role.UNKNOWN.roleCode;
        }
    }

    public static String userRoleToString(Integer userRole) {
        switch (userRole) {
            case (1):
                return Role.ADMIN.getRusName();
            case (2):
                return Role.MANAGER.getRusName();
            case (3):
                return Role.USER.getRusName();
            default:
                return Role.UNKNOWN.getRusName();
        }
    }

}
