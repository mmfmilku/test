package util;

import java.io.*;

public class FileUtil {

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator;

    //...
    private static final String RESOURCES_PATH = "src\\main\\resources\\";

    public static byte[] getResources(String path) {
        return getFileData(ROOT_PATH + RESOURCES_PATH + path);
    }

    /*加载文件数据*/
    public static byte[] getFileData(String filePath) {
        System.out.println("load file:" + filePath);
        byte[] data = new byte[0];
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("file not exists!");
            return data;
        }

        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(f);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1)
                out.write(buffer, 0, size);
            data = out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public static boolean writeFile(byte[] data, String name) {
        File f = new File(name);
        OutputStream out = null;

        try {
            out = new FileOutputStream(f);

            out.write(data, 0, data.length);

            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public static boolean writeResourcesFile(byte[] data, String name) {
        return writeFile(data, ROOT_PATH + RESOURCES_PATH + name);
    }

}
