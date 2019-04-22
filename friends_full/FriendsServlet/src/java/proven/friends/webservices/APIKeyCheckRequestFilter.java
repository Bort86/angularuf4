package proven.friends.webservices;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import proven.msg.Message;

/**
 * Filter that secure Service with API KEY
 * ContainerResponseFilter are server-side filters.
 * The filter is a provider class and thus must be marked with @Provider. 
 * This ensures that the filters are automatically discovered. 
 * If the filter is not marked with @Provider, it
 * needs to be explicitly registered in the Application class.
 * Filter is applied globally on all resources with the incoming HTTP request.
 * @author ProvenSoft
 */

//@Provider
public class APIKeyCheckRequestFilter implements ContainerRequestFilter {

    private static final String API_KEY = "X-API-KEY";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) 
            throws IOException {
        final String apiKey = containerRequestContext.getHeaders().getFirst(API_KEY);
        URI myURI = containerRequestContext.getUriInfo().getAbsolutePath();
        String myPath = myURI.getPath();
        // securing the services
        if (!isPublicService(myPath) && !isValidApiKey(apiKey)) {
            containerRequestContext
                .abortWith(
                    Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity(new Gson().toJson(new Message(0, "API Key not valid")))
                        .build()
                );
        }
    }

    /**
     * Validates if service is Public to access without ApiKey
     * @param path : path of service
     * @return
     */
    public boolean isPublicService(String path) {
        boolean isPublic = false;
        // TODO : put public services
        if(path.endsWith("/services/friend-service/friends")) {
            isPublic = true;
        }
        //return isPublic;
        return true;  //change this to protect api with token.
    }

    /**
     * Validates if apiKey is valid
     * @param apiKey : api key of service
     * @return
     */
    public boolean isValidApiKey(String apiKey) {
        boolean isValid = false;
        // TODO : validation of key 
        if (apiKey != null && !apiKey.isEmpty()) {
            isValid = true;
        }
        return isValid;
    }

}
