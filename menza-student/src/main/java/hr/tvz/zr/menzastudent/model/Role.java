package hr.tvz.zr.menzastudent.model;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_MENZA("ROLE_MENZA");

    public String role;
    private Role(String role){
        this.role = role;
    }
}
