package concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangbo54 on 2019-05-19.
 */
public class Hook {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1,2));
        for (Integer integer : integers) {
            if (1==integer) {
                integers.remove(integer);
            }
        }

        // integers.removeIf(integer -> 1 == integer);
        System.out.println(integers);
    }
}
