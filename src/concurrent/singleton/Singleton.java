package concurrent.singleton;

/**
 * Created by zhangbo54 on 2019-06-03.
 */
public class Singleton {
    private static Resource resource = new Resource(2, 3);

    public static Resource getSingleton() {
        return resource;
    }
}
