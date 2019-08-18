package pattern.singleton;

/**
 * Created by jacob on 2019-08-12.
 */
public enum Singleton implements MySingleton {
    INSTANCE {
        @Override
        public void println() {
            System.out.println("233");
        }
    };
    public static MySingleton getSingleton() {
        return INSTANCE;
    }
}
