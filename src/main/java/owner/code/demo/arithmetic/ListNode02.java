package owner.code.demo.arithmetic;

import java.util.HashMap;

/**
 * 判断链表是否有环
 */
public class ListNode02 {
    int val;
    ListNode02 next;
    ListNode02(int x,ListNode02 node) {
        val = x;
        next = node;
    }

    public static boolean hasLoop2(ListNode02 n) {
        ListNode02 target = n;
        HashMap<ListNode02,ListNode02> hashMap = new HashMap<>();
        while(target!=null){
            if(hashMap.get(target)!=null){
                return true;
            }
            hashMap.put(target,target);
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode02 node5 = new ListNode02(5,null);
        ListNode02 node4 = new ListNode02(4,node5);
        ListNode02 node3 = new ListNode02(3,node4);
        ListNode02 node2 = new ListNode02(2,node3);
        ListNode02 node1 = new ListNode02(1,node2);
        node5.next = node1;
        System.out.println(hasLoop2(node1));
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
