import com.book.common.utils.UuidUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
    @Test
    public void t1(){
        //合同编号：当前日期+uuid
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String uuid = UuidUtil.get32UUID().substring(28).toUpperCase();
        String contractnumber = sdf.format(new Date()) + uuid;
        System.out.println(UuidUtil.get32UUID());
    }
}
