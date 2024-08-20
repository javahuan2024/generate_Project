import com.baomidou.mybatisplus.annotation.DbType;
import com.yh.mybatis.GenFileTypeEnum;
import com.yh.mybatis.MybatisGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 生成数据库实体
 * @author ian
 */
public class GenerateDB {

    /**
     * 参数
     */
    private static final String dbUrl = "jdbc:mysql://localhost:3306/smbms?characterEncoding=utf8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true";
    private static final String dbUserName = "root";
    private static final String dbPassWord = "root";
    private static final String packageFix = "com.yh";
    private static final String javaFilePathFix = "/com/yh";

    /**
     * 忽略生成的文件
     */
    private static List<String> ignoreGenFileTypes = Arrays.asList(GenFileTypeEnum.vue.name());

    // private static List<String> ignoreGenFileTypes = Arrays.asList(GenFileTypeEnum.vue.name());

    /**
     * table前缀
     */
    private static String[] prefix = new String[]{"smbms_"};

    /**
     * 要生成的表名
     */
    private static String[] tables = {"smbms_address","smbms_bill","smbms_provider","smbms_role","smbms_user"};

    /**
     * 生成方法
     */
    public static void main(String[] args) throws IOException, TemplateException {
        MybatisGenerator mybatisGenerator = new MybatisGenerator();
        mybatisGenerator.exec(DbType.MYSQL.getDb(), dbUrl, dbUserName, dbPassWord, packageFix, javaFilePathFix, prefix, tables, ignoreGenFileTypes);
    }

}
