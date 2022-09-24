import BloomFilter.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

public class BloomFilterTest {
    @Test
    public void bloomFilter() {
        BloomFilter bloomFilter = new BloomFilter();
        for (int i = 0; i < 100000; i++) {
            bloomFilter.add(String.valueOf(i));
        }
        System.out.println(bloomFilter.contains("1"));
        System.out.println(bloomFilter.contains("2"));
        System.out.println(bloomFilter.contains("3"));
        System.out.println(bloomFilter.contains("100001"));
    }

    @Test
    public void googleBloomFilter() {
        com.google.common.hash.BloomFilter<Integer> bloomFilter = com.google.common.hash.BloomFilter.create(Funnels.integerFunnel(), 100000, 0.0001);
        for (int i = 0; i < 100000; i++) {
            bloomFilter.put(i);
        }
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        System.out.println(bloomFilter.mightContain(3));
        System.out.println(bloomFilter.mightContain(100001));
    }
}
