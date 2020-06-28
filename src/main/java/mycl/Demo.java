package mycl;

public class Demo {

    public static void main(String[] args) {
        ClassLoader c = new MyClassLoader();
        try {
            Class<?> k = c.loadClass("mycl.MyClass_");

            Object o1 = k.newInstance();
            System.out.println(o1);
            System.out.println(o1.getClass().getClassLoader());

            Object o2 = new MyClass();
            System.out.println(o2);
            System.out.println(o2.getClass().getClassLoader());

            System.out.println(o1.getClass() + "," + o2.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
