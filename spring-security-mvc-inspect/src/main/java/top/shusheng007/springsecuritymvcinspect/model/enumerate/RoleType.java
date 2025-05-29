package top.shusheng007.springsecuritymvcinspect.model.enumerate;

public enum RoleType {
    ADMIN("admin"), USER("user");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
