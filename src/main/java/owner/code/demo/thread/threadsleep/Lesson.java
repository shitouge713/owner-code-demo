package owner.code.demo.thread.threadsleep;

public class Lesson {
    int schoolid;
    int studentCount; //学生数量
    public int getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(int schoolid) {
        this.schoolid = schoolid;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public Lesson(int schoolid, int studentCount) {
        this.schoolid = schoolid;
        this.studentCount = studentCount;
    }
}
