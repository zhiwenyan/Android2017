package zhiwenyan.cmccaifu.com.android2017.JavaData.hashmap;

/**
 * Description:
 * Data：9/11/2018-9:58 AM
 *
 * @author yanzhiwen
 */

/**
 * 手写 HashMap 一些关键代码
 *
 * @param <K>
 * @param <V>
 * @author hcDarren
 */
public class HashMap<K, V> {
    // 需求例子 ：跳转到直播页，首先跳转到登录页，登录成功之后再跳转到直播页
    //（明天）比较好的解决方案，随着业务逻辑越来越复杂的情况下，不需要去刻意的去修改原来的代码
    // apt aspectj 做过哪些优化

    /**
     * 散列 table（桶）
     */
    public MapEntry[] table;

    /**
     * 存放的个数
     */
    transient int size;

    /**
     * 扩容阀值
     */
    int threshold = 8;

    /**
     * 扩容因子 , 也有一些技巧
     */
    final float loadFactor = 0.75f;

    // 自己实现一个 HashMap , 怎么实现？ list
    public class MapEntry<K, V> {
        K key;
        V value;
        MapEntry<K, V> next;
        int hash;// key 的 hash 值

        public MapEntry(int hash, K key, V value, MapEntry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public V put(K key, V value) {// 如果用数组去实现 复杂度是 O(n)
        if (table == null) {
            table = new MapEntry[8];
        }

        // 是不是空
        if (key == null) {
            // 自行看 hashMap 的源码
            return null;
        }

        // 1. 找到 table 的位置
        int hash = hash(key);
        int index = getIndex(hash, table.length);

        // 2. 判断有没有存在该 key
        for (MapEntry<K, V> e = table[index]; e != null; e = e.next) {
            Object k; // 一丁点优化做好
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }

        }
        // 3. 添加一个新的 MapEntry
        addEntry(hash, key, value, index);
        return null;
    }

    /**
     * 添加一个新的 Entry
     *
     * @param hash
     * @param key
     * @param value
     * @param index
     */
    private void addEntry(int hash, K key, V value, int index) {
        // hash_shift 16  value() >> 16
        // 判断要不要扩容  jni 源码记住  mask_bits(value() >> hash_shift, hash_mask)
        // 1. hash 值相等两个对象不一定相等 （两个对象不相等 hash 值有可能相等）
        // 2. hash 值不相等的两个对象，这两个对象肯定不相等
        if (size >= threshold && table[index] != null) {
            resize(size << 1);
            // 重新计算 index
            index = getIndex(hash, table.length);
        }
        // 添加
        createEntry(hash, key, value, index);

        size++;
    }

    /**
     * 创建一个新的
     *
     * @param hash
     * @param key
     * @param value
     * @param index
     */
    private void createEntry(int hash, K key, V value, int index) {
        MapEntry<K, V> newEntry = new MapEntry<>(hash, key, value, table[index]);
        table[index] = newEntry;
        size++;
    }

    private void resize(int newCapacity) {
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        // 直接把之前的数组搬过来 ，行不行？ 扩容之后 index 会变 复杂度 O(n)
        transfrom(newTable);
        table = newTable;
        threshold = ( int ) (newCapacity * loadFactor);
    }

    /**
     * 重新计算逻动 散列
     *
     * @param newTable
     */
    private void transfrom(HashMap<K, V>.MapEntry<K, V>[] newTable) {
        int newCapacity = newTable.length;

        for (MapEntry<K, V> e : table) {
            while (null != e) {
                // 从原来的数组中获取数据 Entry , 保证新的数组能链上
                MapEntry<K, V> next = e.next;

                // 重新计算 index
                int index = getIndex(e.hash, newCapacity);
                // 保证新的数组能链上
                e.next = newTable[index];
                newTable[index] = e;

                e = next;
            }
        }
    }

    /**
     * 通过 hash 值找到所在的 table 的 index
     *
     * @param hash
     * @return
     */
    private int getIndex(int hash, int length) {
        // 2 的幂次  ， 降低查询时间复杂度 ， 让散列更散
        return hash & length - 1;
    }

    private int hash(K key) {
        int h = 0;
        return (key == null) ? 0 : (h = key.hashCode() ^ (h >>> 16));
    }

    public V get(K key) {// 如果用数组去实现 复杂度是 O(n)
        if (key == null) {
            // 自行看源码
            return null;
        }

        MapEntry<K, V> entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    private MapEntry<K, V> getEntry(K key) {
        // 1. 找到 table 的位置
        int hash = hash(key);
        int index = getIndex(hash, table.length);

        // 2. 判断有没有存在该 key
        for (MapEntry<K, V> e = table[index]; e != null; e = e.next) {
            Object k; // 一点优化做好
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e;
            }

        }
        return null;
    }

    public int getSize() {
        return table.length;
    }

    public static void main(String[] args) {
        HashMap<Integer, Student> hashMap = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            hashMap.put(i, new Student("steven" + i));
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(hashMap.get(i).name);
        }

        HashMap<Student, Integer> map = new HashMap<>();


        for (int i = 0; i < 8; i++) {
            map.put(new Student("steven" + i), i);

        }
        for (int i = 0; i < 8; i++) {
            System.out.println(map.get(new Student("steven"+i)));
        }

        java.util.HashMap<Student,Integer> map1=new java.util.HashMap<>();

    }
}
