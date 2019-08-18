package pattern.singleton;

/**
 * Created by jacob on 2019-08-12.
 */
public class Main {
    public static void main(String[] args) {
        MySingleton singleton = Singleton.getSingleton();
        singleton.println();
        Singleton instance = Singleton.INSTANCE;

    }
}
