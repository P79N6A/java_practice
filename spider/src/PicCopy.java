import java.io.RandomAccessFile;

/**
 * Created by jiao on 2018/10/24.
 */

public class PicCopy
{
    // 定义下载资源的路径
    private String path;
    // 指定所下载的文件的保存位置
    private String targetFile;
    // 定义需要使用多少线程下载资源
    private int threadNum;
    // 定义下载的线程对象
    private DownThread[] threads;
    // 定义下载的文件的总大小
    private int fileSize;

    public PicCopy(String path,String targetFile) {
        this.path = path;
        this.threadNum = 5;
        // 初始化threads数组
        threads = new DownThread[5];
        this.targetFile = targetFile;
    }

    public void download() throws Exception
    {
        RandomAccessFile reader=new RandomAccessFile(path,"r");
        // 得到文件大小
        fileSize = (int) reader.length();
        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        // 设置本地文件的大小
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i < threadNum; i++)
        {
            // 计算每条线程的下载的开始位置
            int startPos = i * currentPartSize;
            // 每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile,
                    "rw");
            // 定位该线程的下载位置
            currentPart.seek(startPos);
            // 创建下载线程
            threads[i] = new DownThread(startPos, currentPartSize,
                    currentPart);
            // 启动下载线程
            threads[i].start();
        }
    }
    public static void main(String[] args) throws Exception{
        PicCopy picCopy = new PicCopy("C:\\Users\\jiao\\AppData\\Local\\Temp\\msohtmlclip1\\01" +
                "\\clip_image011.jpg", "1.jpg");
        picCopy.download();
    }
    // 获取下载的完成百分比
    public double getCompleteRate()
    {
        // 统计多条线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++)
        {
            sumSize += threads[i].length;
        }
        // 返回已经完成的百分比
        return sumSize * 1.0 / fileSize;
    }

    private class DownThread extends Thread
    {
        // 当前线程的下载位置
        private int startPos;
        // 定义当前线程负责下载的文件大小
        private int currentPartSize;
        // 当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        // 定义已经该线程已下载的字节数
        public int length;

        public DownThread(int startPos, int currentPartSize,
                          RandomAccessFile currentPart)
        {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run()
        {
            try
            {
                RandomAccessFile reader =new RandomAccessFile(path,"r");
                // 跳过startPos个字节，表明该线程只下载自己负责哪部分文件。
                reader.seek(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                // 读取网络数据，并写入本地文件
                while (length < currentPartSize
                        && (hasRead = reader.read(buffer)) != -1)
                {
                    currentPart.write(buffer, 0, hasRead);
                    // 累计该线程下载的总大小
                    length += hasRead;
                }
                currentPart.close();
                reader.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }




}