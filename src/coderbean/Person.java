package coderbean;

/**
 * Created by zhangbo54 on 2019-03-20.
 */
public class Person {
    public Person(int age, String name) {
        System.out.println(this.age);
        // System.out.println(a);
        // this.age = age;
        // this.name = name;
        this.a = age;
    }

    private int age;
    private String name;
    private final int a;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Person p = new Person(2,"2");
        System.out.println(p.a);

    }
}


