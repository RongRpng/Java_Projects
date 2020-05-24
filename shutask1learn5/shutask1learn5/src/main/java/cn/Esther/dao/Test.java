package cn.Esther.dao;

//@Component
public class Test {
    private String name;

//    @Autowired
//    private User user;
//    @Resources(name="test1")
//    private User user;

    public Test(String name){
        this.name= name;
    }

    public Test(){

    }

    public String getName() {
        System.out.println("你的名字是"+this.name);
        return  this.name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void dogSay(){
        System.out.println("汪汪汪~");
    }

    public void catSay(){
        System.out.println("喵喵喵~");
    }


}
