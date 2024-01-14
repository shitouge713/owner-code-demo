package owner.code.demo.jvm;

public class Teacher {

    private String name;

    private String age;

    private String address;

    private String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Teacher(String name, String age, String address, String sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
    }
}
