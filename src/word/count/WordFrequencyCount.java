package word.count;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by jacob on 2019-08-05.
 */
public class WordFrequencyCount {

    public static void wordFrequencyCount(int n) throws ExecutionException, InterruptedException {
        File wf = new File("news.txt");
        int taskNum = n;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskNum);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskNum; i++) {
            Callable c = new MyCallable(wf, i / taskNum, (i + 1) / taskNum * wf.length());
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        TreeMap tMap = new TreeMap();
        for (Future f : list) {
            HashMap hMap = (HashMap) f.get();
            //对不同线程处理的结果进行整合  
            tMap.putAll(hMap);
        }

        //打印输出，查看结果
        //Iterator iterator = tMap.entrySet().iterator();
        List<Map.Entry<String, Integer>> entrieList = new ArrayList<Map.Entry<String, Integer>>(tMap.entrySet());
        //通过比较器实现排序
        Collections.sort(entrieList, new Comparator<Map.Entry<String, Integer>>() {
            //降序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> map = entrieList.remove(i);
            System.out.println(map.getKey() + ":" + map.getValue());
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        wordFrequencyCount(10);
    }
}


class MyCallable implements Callable<Object> {
    private FileChannel fc;
    private FileLock fl;
    private MappedByteBuffer mbBuf;
    private HashMap hm;

    public MyCallable(File src, long start, long end) {
        try {
            //得到当前文件的通道  
            fc = new RandomAccessFile(src, "rw").getChannel();
            //锁定当前文件的部分  
            fl = fc.lock(start, end, false);
            //对当前文件片段建立内存映射，如果文件过大需要切割成多个片段  
            mbBuf = fc.map(FileChannel.MapMode.READ_ONLY, start, end);
            //创建HashMap实例存放处理结果  
            hm = new HashMap<String, Integer>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object call() throws Exception {
        String str = StandardCharsets.UTF_8.decode(mbBuf).toString();
        //使用StringTokenizer分析单词
        StringTokenizer token = new StringTokenizer(str);
        String word;
        while (token.hasMoreTokens()) {
            //将处理结果放到一个HashMap中
            word = token.nextToken();
            if (null != hm.get(word)) {
                hm.put(word, (Integer) hm.get(word) + 1);
            } else {
                hm.put(word, 1);
            }
        }
        try {
            //释放文件锁  
            fl.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hm;
    }
}