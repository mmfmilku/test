package test;

import org.junit.Test;
import org.springframework.util.FileCopyUtils;
import sun.misc.Launcher;
import sun.misc.Unsafe;

public class ClassLoaderTest {

    @Test
    public void test0() {
        String a = "";
        System.out.println("test");
        Runtime.getRuntime().addShutdownHook(new Thread(){

        });
    }

    @Test
    public void comp() {
        String in = "EFCBA532DEA789FF";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length() / 2; i++) {
            int num = Integer.parseInt(in.substring(i * 2, 2 + i * 2), 16);
            char cnum = (char)num;
            out.append(cnum);
            System.out.println(num + ":" + cnum);
        }
        System.out.println("in:" + in + ",out:" + out.toString());

    }

    @Test
    public void classTest() throws ClassNotFoundException {
        Class<?> a = Class.forName("org.apache.ibatis.cache.NullCacheKey");

        try {
            System.out.println(a.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sysProp() {
        System.out.println(System.getProperty("java.class.path"));
    }

    @Test
    public void classLoader() {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        try {
            Class<?> c = appClassLoader.loadClass("java.lang.String");
            Class<?> c1 = appClassLoader.loadClass("java.lang.String");
            System.out.println(c1);
//            Object o = c.newInstance();
//            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void ctest() {
        ClassLoader c1 = this.getClass().getClassLoader();
        ClassLoader c2 = ClassLoader.getSystemClassLoader();
    }

    @Test
    public void fileTest() {
        String name = "java.lang.String";
        System.out.println("java.class.path:" + System.getProperty("java.class.path"));
        System.out.println("user.name:" + System.getProperty("user.name"));
        System.out.println("user.home:" + System.getProperty("user.home"));
        System.out.println("user.dir:" + System.getProperty("user.dir"));
        System.out.println(getClass().getClassLoader());
    }

}























