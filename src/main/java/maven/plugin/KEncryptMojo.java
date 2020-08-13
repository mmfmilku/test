package maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import util.FileUtil;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

@Mojo(name = "kencrypt", defaultPhase = LifecyclePhase.PACKAGE)
public class KEncryptMojo extends AbstractMojo {

    @Parameter(property = "project.build.finalName")
    private String finalName;

    @Parameter(property = "project.packaging")
    private String packaging;

    @Parameter(property = "project.build.directory")
    private String buildDir;

    @Parameter(property = "project.build.outputDirectory")
    private String outputDir;

    @Parameter(property = "file.separator")
    private String fileSeparator;

    @Parameter
    private List<String> encryptDirs;

    private Log logger = getLog();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String filePath = buildDir + fileSeparator + finalName + "." + packaging;
        logger.info("--- start encrypt your package file " + filePath + " ---");

        File f = new File(filePath);
        if (!f.exists()) {
            logger.error("package file not exists:" + filePath);
            return;
        }

        JarFile jarFile = null;
//        InputStream in = null;
//        JarInputStream jarIn = null;
        try {
            jarFile = new JarFile(f);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                System.out.println("-----------------");
                JarEntry jarEntry = entries.nextElement();
                System.out.println(jarEntry.getName());
                if (jarEntry.isDirectory())
                    continue;
                InputStream inputStream = jarFile.getInputStream(jarEntry);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null)
                    System.out.println(line);
                reader.close();
            }
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }

    }
}
