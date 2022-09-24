import BloomFilter.BloomFilter;
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
}
