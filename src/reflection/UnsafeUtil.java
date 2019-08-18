package reflection;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by jacob on 2019-08-05.
 */
public class UnsafeUtil {

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        return (Unsafe) theUnsafe.get(null);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        System.out.println(unsafe);
        Person person = new Person();
        Field age = Person.class.getDeclaredField("age");
        long offset = unsafe.objectFieldOffset(age); // 这里注意这个方法是用来获取 offset 的，也是 unsafe 里面的方法
        // 使用 CAS 的时候不需要设置 theUnsafe.setAccessible(true); 可访问性
        System.out.println(unsafe.compareAndSwapInt(person, offset, 10, 20)); // false
        System.out.println(person.getAge()); // 0
        System.out.println(unsafe.compareAndSwapInt(person, offset, 0, 20)); // true
        System.out.println(person.getAge()); // 20
        System.out.println("-------");
        // 对于类的静态变量也可以使用 CAS，需要替换的对象为 类本身，同时过去 offset的方法也改为 unsafe.staticFieldOffset
        Field weight = Person.class.getDeclaredField("weight");
        long staticFieldOffset = unsafe.staticFieldOffset(weight);
        System.out.println(Person.weight); //10
        System.out.println(unsafe.compareAndSwapInt(Person.class, staticFieldOffset, 0, 15)); // false
        System.out.println(Person.weight); //10
        System.out.println(unsafe.compareAndSwapInt(Person.class, staticFieldOffset, 10, 15)); // true
        System.out.println(Person.weight); // 15


    }
}

class Person {
    static int weight = 10;
    private String name = "zhangbo";
    private int age = 0;

    public int getAge() {
        return age;
    }
}
