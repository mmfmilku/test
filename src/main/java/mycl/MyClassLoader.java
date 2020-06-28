package mycl;

import org.junit.Test;
import sun.misc.Launcher;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class MyClassLoader extends ClassLoader{

//    public MyClassLoader(ClassLoader parent) {
//        super(parent);
//    }

    public MyClassLoader() {
        super();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass:" + name);
        byte[] data = getClassData(name);
        Class<?> c = null;
        if (data != null) {
            c = defineClass(name, data, 0, data.length);
        }
        return  c;
    }

    private static byte[] getClassData(String name) {
        String rootPath = System.getProperty("user.dir");
//        String contentRoot = "\\src\\main\\java\\";
//        String contentRoot = "\\target\\classes\\";
        String contentRoot = "\\src\\main\\resources\\myclass\\";
//        String fileName = name.replaceAll("\\.", "\\\\");
        String fileName = name.substring(name.indexOf('.') + 1);
        String filePath = rootPath + contentRoot + fileName + ".class";
        return loadFile(filePath);
    }

    private static byte[] loadFile(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("文件不存在:" + filePath);
            return null;
        }
        InputStream in = null;
        ByteArrayOutputStream out = null;
        byte[] data = null;
        try {
            in = new FileInputStream(f);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1];
            int size = 0;
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer);
            }
            data = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("data:" + Arrays.toString(data));
        return data;
    }

    @Test
    public void test() {
        String name = "mycl.MyClass";
        byte[] data = getClassData(name);
        System.out.println(new String(data));
        System.out.println(Arrays.toString(data));
    }

}
