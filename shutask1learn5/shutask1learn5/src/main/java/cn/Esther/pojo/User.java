package cn.Esther.pojo;
//实体类
public class User {
    private Long id;
    private String name;
    private String qq;
    private Integer type;

    private Long time;
    private String link;
    private Long createAt;
    private Long createBy;
    private Long updateAt;
    private Long updateBy;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }


    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }


    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getQq(){
        return qq;
    }
    public void setQq( String qq){
       this.qq = qq;
    }
    public Integer getType(){
        return type;
    }
    public void setType( Integer type){
        this.type = type;
    }

}
