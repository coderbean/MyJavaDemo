package reference;

/**
 * Created by zhangbo54 on 2019-05-13.
 */
public class MemConsumer {
    private static final int SIZE = 1024 * 5;

    public static void cosumeMem() {
        String[] array = new String[SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 'a'; j < 'z'; j++) {
                array[i] += (char) j;
            }
        }
    }
}
