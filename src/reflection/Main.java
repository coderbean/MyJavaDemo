package reflection;

import java.lang.reflect.Field;

/**
 * Created by jacob on 2019-08-05.
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();
        Class modelClass = model.getClass();
        Field name = modelClass.getDeclaredField("name");
        if (name!=null) {
            name.setAccessible(true);
            System.out.println(name.get(model));
            name.set(model,"name");
            System.out.println(name.get(model));
        }
    }
}
