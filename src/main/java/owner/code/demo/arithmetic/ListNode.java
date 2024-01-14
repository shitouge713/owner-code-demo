package owner.code.demo.arithmetic;

/**
 * 链表的反转
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x,ListNode node) {
        val = x;
        next = node;
    }
    /**
     * 非递归实现
     * @param head
     * @return
     */
    public static ListNode reverseList01(ListNode head) {
        ListNode a = null;
        while(head!=null){
            ListNode temp = head.next;
            head.next = a;
            a = head;
            head = temp;
        }
        return a;
    }
    /**
     * 递归实现
     * @param head
     * @return
     */
    public static ListNode reverseList02(ListNode head) {
        if(head==null||head.next ==null){
            return head;
        }
        ListNode prev = reverseList02(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5,null);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        System.out.println(node1.toString());
        ListNode rever = reverseList01(node1);
        System.out.println(rever.toString());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode{");
        sb.append("val='").append(val).append('\'');
        sb.append(", next='").append(next).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
