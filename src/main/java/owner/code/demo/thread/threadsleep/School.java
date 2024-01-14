package owner.code.demo.thread.threadsleep;

public class School {
    int shoolid;
    String schoolname; //学校名字

    public int getShoolid() {
        return shoolid;
    }

    public void setShoolid(int shoolid) {
        this.shoolid = shoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public School(int shoolid, String schoolname) {
        this.shoolid = shoolid;
        this.schoolname = schoolname;
    }
}
