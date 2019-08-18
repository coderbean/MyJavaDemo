package concurrent.singleton;

/**
 * Created by zhangbo54 on 2019-06-03.
 */
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getSingleton() throws InterruptedException {
        if (resource == null) {
            resource = new Resource(1, 2);
        }
        return resource;
    }
}
