import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestApplication extends Application {
    final Set<Class<?>> classes = new HashSet<>();

    @Override
    public Set<Class<?>> getClasses() {
        classes.add(ResourceSentiment.class);
        return Collections.unmodifiableSet(classes);
    }

}