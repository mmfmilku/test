import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/*
* rar文件解压示例
* */
public class UnRar{

    public static void main(String[] args) {
        try {
            unrar("F:\\dev\\project\\sxf-spa-20200701-my.rar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unrar(String path) throws Exception {
        File rarFile = new File(path);
        if (rarFile.isDirectory()) {
            System.out.println("请输入文件路径而非文件夹路径");
            return;
        }
        String outDir = path.substring(0, path.lastIndexOf(".")) + File.separator;
        System.out.println("解压目录:" + outDir);
        unrar(rarFile, outDir);
    }

    public static void unrar(File rarFile, String outDir) throws Exception {
        File outFileDir = new File(outDir);
        if (!outFileDir.exists()) {
            boolean isMakDir = outFileDir.mkdirs();
            if (isMakDir) {
                System.out.println("创建压缩目录成功");
            }
        }
        Archive archive = new Archive(new FileInputStream(rarFile));
        FileHeader fileHeader = archive.nextFileHeader();
        while (fileHeader != null) {
            if (fileHeader.isDirectory()) {
                fileHeader = archive.nextFileHeader();
                continue;
            }
            File out = new File(outDir + fileHeader.getFileNameString());
            if (!out.exists()) {
                if (!out.getParentFile().exists()) {
                    out.getParentFile().mkdirs();
                }
                out.createNewFile();
            }
            FileOutputStream os = new FileOutputStream(out);
            archive.extractFile(fileHeader, os);

            os.close();

            fileHeader = archive.nextFileHeader();
        }
        archive.close();
    }

}
