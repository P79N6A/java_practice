import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiao on 11/26/2018.
 */
public class NumTest {

    public static void main(String[] args){

        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(new Date());
        for (int i= 0;i<10;i++){
            StringBuilder stringBuilder = new StringBuilder(format);
            System.out.println(stringBuilder.append(String.format("%06d",i)));
        }
    }
}
