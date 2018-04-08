import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2017/7/19.
 */
public class SetTest {
    public static void main(String[] args) {
        String[] strs = {"aaa","bbb","ccc","ccc","ddd","ddd","eee","eee","fff"};
        Set<String> set = new HashSet<String>();
        for (String s : strs) {
            set.add(s);
        }
        for (String s: set){
            System.out.println(s);
        }
    }
}
