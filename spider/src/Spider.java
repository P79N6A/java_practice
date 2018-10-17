import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {

    public static void main(String[] args) throws Exception{
        findMail();
    }

    /**
     * @Description:  网上爬虫邮箱
     * @Param: []
     * @return: void
     * @Author: Mr.Jiao
     * @Date: 2018/10/17
     */
    public static void findMail() throws Exception{
        URL url = new URL("http://www.ivsky.com/tupian/keai_guobao_xiongmao_v40607/");
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String line = null;

        String reg = "https?://(\\.?\\w+)+(/\\w+)+\\.(jpg|png)";
        Pattern pattern = Pattern.compile(reg);
        while( (line = bufferedReader.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                String path = matcher.group();
                String targetFile = "E:\\IDEA\\down\\"+ UUID.randomUUID()+path.substring(path.lastIndexOf("."));
                System.out.println(targetFile);
                DownUtil downUtil = new DownUtil(path, targetFile);
                downUtil.download();
            }
        }

    }
}

