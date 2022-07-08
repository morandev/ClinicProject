package ar.com.digitalhouse.ctd.clinicproject.model.login;

public enum Rol {
    ROLE_USER, ROLE_ADMIN;
    public static String getUserInstance() {
        return ROLE_USER.toString();
    }
    public static String getAdminInstance() {
        return ROLE_ADMIN.toString();
    }
}
