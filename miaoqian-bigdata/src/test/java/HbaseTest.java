/**
 * Created by admin on 2017/3/27.
 */
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

public class HbaseTest {

    private static final String TABLE_NAME = "test";

    private static final String ROW_KEY = "row1";

    private static final String COLUMN_FAMILY = "cf";

    private static final String QUALIFIER = "a";

    public static void main(String[] args) {
        // 加载Spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-hbase.xml");
        // 获取HbaseTemplate
        HbaseTemplate hbaseTemplate = (HbaseTemplate) applicationContext.getBean("hbaseTemplate");
        // 通过表名和rowKey获取最近一行数据
        String result = hbaseTemplate.get(TABLE_NAME, ROW_KEY, new RowMapper<String>() {
            public String mapRow(Result result, int rowNum) throws Exception {
                return Bytes.toString(result.getValue(COLUMN_FAMILY.getBytes(), QUALIFIER.getBytes()));
            }
        });
        System.out.println(result);
    }
}
