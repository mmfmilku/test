package study;

import java.util.HashMap;
import java.util.Map;

/**
 *  初始状态：[head(tail)]
 *  数据状态：[head] <-> [data0] <-> [data1] <-> [data2] <-> [data3(tail)]
 *  head节点不保存数据，越靠近head表示使用频率越高。当容量达到上限，淘汰tail处节点，并将该其前一节点设为新tail节点
 * */
public class LRUCache<K, V> {

    // 使用Mao做数据存储
    Map<K, Node> map;
    // 使用双向链表维护缓存数据淘汰关系
    Node head,tail;
    // 缓存容量
    int capacity;

    public LRUCache(int capacity) {
        // 初始化Map容量，避免后续Map内部扩容
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        // 初始化哨兵节点
        head = tail = new Node(null,null);
    }

    // 将节点添加至顶部（head节点后）
    private void addFirst(Node node) {
        node.next = head.next;
        head.next = node;
        node.pre = head;
        if (node.next == null) {
            tail = node;
            return;
        }
        node.next.pre = node;
    }

    // 移除节点
    private void remove(Node node) {
        if (node == tail) {
            tail = node.pre;
            tail.next = null;
            node.pre = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }

    // 开放方法，获取缓存
    public V get(K key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            if (node != head.next) {
                // 移除原位置的节点
                remove(node);
                // 将节点添加至顶部
                addFirst(node);
            }
            return node.val;
        }
        return null;
    }

    // 开放方法，添加缓存
    public void put(K key, V value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            map.remove(key);
        }
        while (map.size() >= capacity) {
            // 达到容量上限，移除尾部节点
            Node eldest = tail;
            remove(eldest);
            map.remove(eldest.key);
        }
        Node node = new Node(key,value);
        addFirst(node);
        map.put(key,node);
    }

    // 每一节点包含key，value
    private class Node {
        K key;
        V val;
        Node pre;
        Node next;
        Node(K key, V val){
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(100);
        cache.put("a", "data1");
        cache.put("b", "data2");
        String b = cache.get("b");
        System.out.println(b);
    }
    
}
