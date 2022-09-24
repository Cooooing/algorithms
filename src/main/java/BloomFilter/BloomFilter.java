package BloomFilter;

import java.util.BitSet;

/**
 * 布隆过滤器
 */
public class BloomFilter {
    // 长度为10亿的比特位
    private static final int DEFAULT_SIZE = 256 << 22;
    // 使用的哈希函数（8个）
    private static final int[] seeds = {3, 5, 7, 11, 13, 17, 19, 23};
    private static final HashFunction[] functions = new HashFunction[seeds.length];
    // 初始化布隆过滤器
    private static BitSet bitSet = new BitSet(DEFAULT_SIZE);

    /**
     * 构造函数，初始化哈希函数
     */
    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            functions[i] = new HashFunction(DEFAULT_SIZE,seeds[i]);
        }
    }

    /**
     * 添加元素
     */
    public void add(String value){
        if (value!=null){
            for(HashFunction f : functions){
                bitSet.set(f.hash(value),true);
            }
        }
    }

    /**
     * 判断元素是否存在
     */
    public boolean contains(String value){
        if (value==null){
            return false;
        }
        boolean result = true;
        // 遍历所有哈希结果对应比特位，有一个返回false即break（不存在）
        for(HashFunction f :functions){
            result = bitSet.get(f.hash(value));
            if (!result){
                break;
            }
        }
        return result;
    }
}

/**
 * 哈希函数
 */
class HashFunction {

    private final int size;
    private final int seed;

    public HashFunction(int size, int seed) {
        this.size = size;
        this.seed = seed;
    }

    /**
     * 使用加法哈希算法
     */
    public int hash(String value) {
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++) {
            result = seed * result + value.charAt(i);
        }
        return (size - 1) & result;
    }
}