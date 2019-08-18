package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by zhangbo54 on 2019-06-03.
 */
public class Main {
    public static void main(String[] args) {
        Vector<Integer> list = new Vector<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 7; i++) {
            iterator.next();
        }

        for (Integer integer : list) {
            if (integer == 1) {
                list.remove(integer);
                break;
            }
        }

        System.out.println(iterator.next()); // expected 7, but found 8
    }
}
