package py.com.cleito.bank;


import java.util.ArrayList;
import java.util.List;

public enum User {
    cleito("cleito", "papelito"),
    diego("diego", "tijerita"),
    sinner("sinner", "piedrita");

    private String code;
    private String desc;

    private static List<User> usersList = new ArrayList<>();

    private User(String codigo, String descripcion) {
        this.code = codigo;
        this.desc = descripcion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static List<User> getUserList() {
        usersList.clear();
        for (User t : User.values()) {
            usersList.add(t);
        }
        return usersList;
    }


    /**
     * Metodo que verifica que exista un usuario
     * @param username
     * @return
     */
    public static Boolean existUser(String username) {
        Boolean b = false;
        for (User t : User.values()) {
            if (t.toString().equalsIgnoreCase(username)) {
                b = true;
            }
        }
        return b;
    }

}
