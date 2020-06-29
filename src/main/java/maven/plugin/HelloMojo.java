package maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

@Mojo(name = "hello", defaultPhase = LifecyclePhase.COMPILE)
public class HelloMojo extends AbstractMojo {

    @Parameter
    private String name;

    @Parameter
    private List<String> list;

    @Parameter(property = "maven.home")
    private String mavenHome;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("Hello " + name + "!!!");
        System.out.println("your list is:" + list.toString());
        System.out.println("mavenHome:" + mavenHome);
    }
}
