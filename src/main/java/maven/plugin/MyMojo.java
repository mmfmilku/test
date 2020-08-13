package maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "testplugin", defaultPhase = LifecyclePhase.PACKAGE)
public class MyMojo extends AbstractMojo {


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("this is my mojo!");
    }
}
