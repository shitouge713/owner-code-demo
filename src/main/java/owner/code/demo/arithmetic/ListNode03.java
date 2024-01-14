package owner.code.demo.arithmetic;

/**
 * 删除链表的倒数第n个元素
 */
public class ListNode03 {
    int val;
    ListNode03 next;
    ListNode03(int x, ListNode03 node) {
        val = x;
        next = node;
    }

    public static ListNode03 removeNthFromEnd(ListNode03 head, int n) {
        ListNode03 tail = head;
        ListNode03 root = head;
        int size;
        for (size = 0; size <n && tail!=null; size++) {
            tail = tail.next;
        }
        if(size<n) return null;
        if(tail==null) return root.next;
        while(tail.next!=null){
            tail = tail.next;
            head = head.next;
        }
        head.next = head.next.next;
        return root;
    }


    public static void main(String[] args) {
        ListNode03 node5 = new ListNode03(5,null);
        ListNode03 node4 = new ListNode03(4,node5);
        ListNode03 node3 = new ListNode03(3,node4);
        ListNode03 node2 = new ListNode03(2,node3);
        ListNode03 node1 = new ListNode03(1,node2);
        System.out.println(removeNthFromEnd(node1,2));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode02{");
        sb.append("val='").append(val).append('\'');
        sb.append(", next='").append(next).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
