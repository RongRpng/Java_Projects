package cn.Esther.backup;

public class User {
    private Long user_id;

    private String user_name;

    private String password;

    private int role;

    private Long createAt;


    public Long getId() {
        return user_id;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String user_name) {
        this.user_name = user_name ;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
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
                "id=" + user_id +
                ", name='" + user_name + '\'' +
                ", role='" + role + '\'' +
                ", createAt=" + createAt +
                '}';
    }

}