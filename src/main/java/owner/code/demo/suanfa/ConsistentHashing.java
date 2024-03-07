package owner.code.demo.suanfa;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 如何使用一致性哈希算法将16个节点均匀分布在哈希环上，并根据给定的键找到对应的节点。
 */
public class ConsistentHashing {
    private final SortedMap<Integer, String> circle = new TreeMap<>();
    //节点数
    private final int numberOfReplicas = 100;

    public void addNode(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hash = (node + i).hashCode();
            circle.put(hash, node);
        }
    }

    public String getNode(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = key.hashCode();
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, String> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public static void main(String[] args) {
        ConsistentHashing consistentHashing = new ConsistentHashing();
        String[] nodes = {"Node1", "Node2", "Node3", "Node4", "Node5", "Node6", "Node7", "Node8",
                "Node9", "Node10", "Node11", "Node12", "Node13", "Node14", "Node15", "Node16"};
        //16个节点映射成1600个虚拟节点
        for (String node : nodes) {
            consistentHashing.addNode(node);
        }
        // Test getting node for a key
        String key = "some_key";
        String nodeForKey = consistentHashing.getNode(key);
        System.out.println("Node for key '" + key + "': " + nodeForKey);
    }
}
