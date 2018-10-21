package zhiwenyan.cmccaifu.com.android2017.JavaData.hashmap1;

/**
 * Description:手写HashMap
 * Data：10/10/2018-2:36 PM
 *
 * @author yanzhiwen
 */
public class HashMap<K, V> {
    public MapEntry<K, V>[] table = new MapEntry[10];
    transient int size;

    public class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    public V put(K key, V value) { //如果用数组去实现 复杂度是O(n)
        //1、构建一个MapEntry

        //2、for循环数组有没有包含key，如果有替换（循环数组）

        //3、没有包含新增（可能需要扩容）
        table[size++] = new MapEntry<>(key, value);

        return null;
    }

    public V get(K key) {
        for (int i = 0; i < table.length; i++) {
            if (key == table[i].key) {
                return table[i].value;
            }
        }
        return null;
    }

}
