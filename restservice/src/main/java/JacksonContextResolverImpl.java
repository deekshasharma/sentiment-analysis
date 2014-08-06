import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonContextResolverImpl implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    public JacksonContextResolverImpl() {
        objectMapper = new ObjectMapper()
                .configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false /* force ISO8601 */);
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return objectMapper;
    }
}
