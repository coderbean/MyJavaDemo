package MontyHoll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by coderbean on 2019-06-21.
 */
public class Main2 {
    public static final boolean CAR = true; // 是车
    public static final boolean GOAT = false;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int changeSuccess = 0; // 换选换奖次数
        int totalCount = 100_0000; // 总次数

        List<List<Boolean>> lists = Arrays.asList( // 所有可能的初始状态
                Arrays.asList(CAR, GOAT, GOAT),
                Arrays.asList(GOAT, CAR, GOAT),
                Arrays.asList(GOAT, GOAT, CAR));
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<Boolean>> resultList = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            Future<Boolean> resultFuture = executorService.submit(() -> {
                int randomIndex = ((int) (Math.random() * 100)) % lists.size();
                List<Boolean> doors = lists.get(randomIndex); // 随机选择一种初始情况
                int firstChoose = ((int) (Math.random() * 100)) % 3; // 随机选择一个门
                int openDoorIndex = 0;
                do {
                    openDoorIndex = ((int) (Math.random() * 100)) % 3;
                } while (openDoorIndex == firstChoose || doors.get(openDoorIndex)); // 随机打开一扇羊的门
                int secondChoose = 3 - firstChoose - openDoorIndex;
                return doors.get(secondChoose); // 返回换选是否获奖
            });
            resultList.add(resultFuture);
        }

        for (Future<Boolean> booleanFuture : resultList) {
            Boolean aBoolean = booleanFuture.get();
            if (aBoolean) {
                changeSuccess++;
            }
        }

        System.out.println("换选获胜次数：" + changeSuccess);
        System.out.println("总次数：" + totalCount);
        System.out.println("换选获胜几率：" + (changeSuccess * 1.0 / totalCount));

        executorService.shutdown();
        while (executorService.isTerminated()) {
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        }
    }
}