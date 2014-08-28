//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//import org.codehaus.jackson.map.annotate.JsonSerialize;
//
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.ext.ContextResolver;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//@Produces(MediaType.APPLICATION_JSON)
//public class JacksonContextResolverImpl implements ContextResolver<ObjectMapper> {
//    private final ObjectMapper objectMapper;
//
//    public JacksonContextResolverImpl() {
//        objectMapper = new ObjectMapper()
//                .configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false)
//                .configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true)
//                .configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, true)
//                .setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
//    }
//
//    @Override
//    public ObjectMapper getContext(final Class<?> type)
//    {
//        return objectMapper;
//    }
//}