package cn.Esther.pojo;

public class UserVO {

    private String mobile;

    @Override
    public String toString() {
        return "cn.Esther.pojo.UserVO{" +
                "mobile='" + mobile + '\'' +
                '}'; }

    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
