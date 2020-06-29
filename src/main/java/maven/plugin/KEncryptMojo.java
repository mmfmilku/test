package maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "kencrypt", defaultPhase = LifecyclePhase.PACKAGE)
public class KEncryptMojo extends AbstractMojo {

    @Parameter(property = "project.basedir")
    private String baseDir;

    @Parameter(property = "project.build.finalName")
    private String finalName;

    @Parameter(property = "project.packaging")
    private String packaging;

    @Parameter(property = "project.build.directory")
    private String buildDir;

    @Parameter(property = "project.build.outputDirectory")
    private String outputDir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(baseDir + "," + finalName);
        System.out.println("encrypt mojo here!");
        System.out.println("baseDir:" + baseDir);
        System.out.println("finalName:" + finalName);
        System.out.println("packaging:" + packaging);
        System.out.println("buildDir:" + buildDir);
        System.out.println("outputDir:" + outputDir);
    }
}
