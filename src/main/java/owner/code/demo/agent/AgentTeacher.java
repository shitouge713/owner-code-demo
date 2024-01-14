package owner.code.demo.agent;

public class AgentTeacher {
    public void agentTeacherMethod() {
        try {
            System.out.println("AgentTeacher.agentTeacherMethod");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
