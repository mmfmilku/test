package study;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class FileSortedOutput {

    public static void main(String[] args) {

        String path = "C:\\Users\\miku\\Desktop\\persona5.html";
        FileReader reader = null;
        Map<Character, Integer> map = new TreeMap<>();
        try {
            reader = new FileReader(new File(path));
            int read = reader.read();
            while (read != -1) {
                char c = (char) read;
                Integer count = map.get(c);
                if (count != null) {
                    map.put(c, count + 1);
                } else
                    map.put(c, 1);
                read = reader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        for (char c : map.keySet()) {
            Integer count = map.get(c);

            System.out.print("数量：" + count + "，内容：" + c + "。");
        }

        map.forEach((k,v) -> {});
    }

}
