package cn.Esther.pojo;

public class User {
    private Long userId;

    private String userName;

    private String password;

    private Integer role;

    private Long createAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        System.out.println("get UserName is"+userName);
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("Pass user_name is"+userName);
        this.userName=userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("Pass password");
        this.password = password ;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createAt=" + createAt +
                '}';
    }
}