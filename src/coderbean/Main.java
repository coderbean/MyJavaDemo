package coderbean;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangbo54 on 2017/10/19.
 */
public class Main {
    public static long add(long a, long b) {
        return a + b;
    }

    public static void main(String[] args) throws Exception {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(10, "10"));
        personList.add(new Person(20, "20"));
        List<Person> collect = personList.stream().map(v -> {
            v.setAge(v.getAge() + 1);
            return v;
        }).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(personList);

    }
}
