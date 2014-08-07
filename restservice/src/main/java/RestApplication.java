import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RestApplication extends Application {
    final Set<Class<?>> classes = new HashSet<>();

    @Override
    public Set<Class<?>> getClasses() {
//        classes.add(Rest.class);
        classes.add(Root.class);
        classes.add(ResourceSentiment.class);
        return Collections.unmodifiableSet(classes);
    }

}