package owner.code.demo.agent;

public class AgentTest {

    public static void main(String[] args) {
        AgentTest agentTest = new AgentTest();
        agentTest.testA();
        agentTest.testB();
    }
    public void testA() {
        System.out.println("AgentTest.testA ");
    }
    public void testB() {
        AgentTeacher c = new AgentTeacher();
        c.agentTeacherMethod();
        System.out.println("AgentTest.testB ");
    }

}
