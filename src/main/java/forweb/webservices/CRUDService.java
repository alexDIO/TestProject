package forweb.webservices;

import forweb.TransportMapHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import transport.classes.Sedan;
import transport.classes.Transport;
import transport.storages.TransportStorage;
import transport.storages.TransportStorageException;
import transport.storages.TransportStorageFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by olomakovskyi on 10/30/2014.
 */

@Component
@Path("/")
public class CRUDService {


    @Autowired
    private TransportMapHolder mapHolder;


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMap() throws IOException {
        if (mapHolder != null) {
            return mapHolder.getMap().toString();
        } else {
            return "No data";
        }
    }

    public void setMapHolder(TransportMapHolder mapHolder) {
        this.mapHolder = mapHolder;
    }
}
