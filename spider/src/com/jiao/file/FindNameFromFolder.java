package com.jiao.file;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiao on 11/27/2018.
 */
public class FindNameFromFolder {
    private final  static AtomicInteger ac = new AtomicInteger(1);

    /**
    * @Description: 根据路径输出该文件夹下的所有文件的文件名（非文件夹）
    * @Param: [filename]
    * @return: void
    * @Author: Mr.Jiao
    * @Date: 11/27/2018
    */
    public void findFileName(File filename) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\gitrepo\\other\\e3mallFileList.md",true);

        // 如果是路径  则遍历子目录 否则输出文件的文件名
        if (filename.isDirectory()){
            File[] files = filename.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return !pathname.getName().endsWith(".rar");
                }
            });
            for(File f:files){
                findFileName(f);
            }
        }else{
          if (filename.getName().endsWith(".flv")){

             if (filename.getName().startsWith("01")){
                 StringBuilder stringBuilder = new StringBuilder();
                 fileOutputStream.write(stringBuilder.append("第").append(ac.getAndIncrement()).append("天").toString().getBytes());
                 fileOutputStream.write("\n".getBytes());

             }
              fileOutputStream.write(filename.getName().getBytes());
              fileOutputStream.write("\n".getBytes());
        }
        }
        fileOutputStream.close();
    }




    
    public static void main(String[] args) throws Exception{
        File file = new File("F:\\项目二：宜立方商城(80-93天）【更多资料和求职分享请关注公众号：菜鸟名企梦】");
        FindNameFromFolder findNameFromFolder = new FindNameFromFolder();
        findNameFromFolder.findFileName(file);
    }
}
