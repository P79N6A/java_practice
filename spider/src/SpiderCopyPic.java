import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiao on 2018/10/24.
 */
public class SpiderCopyPic  {

    public static void main(String[] args) throws  Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\gitrepo" +
                "\\java_practice\\spider\\src\\file.txt")));

        String line = null;

        String reg = "(C:\\\\Users\\\\jiao\\\\AppData\\\\Local).+[.jpg|.gif]";
//        String reg = "https://img-blog.+SouthEast";
        Pattern pattern = Pattern.compile(reg);
        while( (line = bufferedReader.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                String path = matcher.group();
//                String targetFile = "E:\\gitrepo\\mybatis-only\\mybatisNotes\\pic2"+ UUID.randomUUID()+".jpg";
                String targetFile = "E:\\gitrepo\\linux-practice\\搭建zookeeper和solr集群\\img"+ path.substring(path.lastIndexOf("\\"));
                System.out.println(targetFile);
                PicCopy picCopy = new PicCopy(path, targetFile);
                picCopy.download();
            }
        }

    }


}

