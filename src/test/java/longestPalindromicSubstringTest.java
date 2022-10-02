import LongestPalindromicSubstring.BF;
import LongestPalindromicSubstring.Manacher;
import org.junit.Test;

public class longestPalindromicSubstringTest {
    @Test
    public void longestPalindromicSubstringTest() {
        String a = String.valueOf(get());
        System.out.println("BF:" + BF.BF(a));
        System.out.println("Manacher:" + Manacher.Manacher(a));
    }

    public static char[] get(){
        char[] chars = getHuiWen(10000).toCharArray();
        for (int i = 0; i < Math.random() * 50; i++) {
            char[] temp = getHuiWen((int) (Math.random()*100)).toCharArray();
            int k=0;
            int a1 = (int) (Math.random()*(10000- temp.length));
            for (int j = a1; j < a1+temp.length; j++,k++) {
                chars[j]=temp[k];
            }
        }
        return chars;
    }

    public static String getHuiWen(int len){
        char[] chars = new char[len];
        for (int i = 0; i < chars.length / 2 + 1; i++) {
            chars[i]=chars[chars.length-i-1]= (char) (Math.random()*26+'a');
        }
        return String.valueOf(chars);
    }
}
