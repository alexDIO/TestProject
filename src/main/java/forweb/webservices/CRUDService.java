package forweb.webservices;

import forweb.TransportMapHolder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transport.classes.PassengerTransport;
import transport.classes.Transport;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by olomakovskyi on 10/30/2014.
 */

@Component
@Path("/")
public class CRUDService {

    final static Logger logger = Logger.getLogger(CRUDService.class);

    @Autowired
    private TransportMapHolder mapHolder;


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, Transport> getMap() throws IOException {
        logger.info("Requested all transports");
        if (mapHolder != null) {
            Map<Integer, Transport> resultMap = mapHolder.getMap();
            logger.info(String.format("Was got map: %s", resultMap));
            return resultMap;
        } else {
            logger.error("Returned empty map.");
            return Collections.EMPTY_MAP;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transport getTransportById(@PathParam("id") int id) {
        logger.info(String.format("Requested transport with id %s", id));
        Transport resultTransport = mapHolder.getMap().get(id);
        if (resultTransport != null) {
            logger.info(String.format("Was got transport: %s", resultTransport));
            return mapHolder.getMap().get(id);
        } else {
            logger.error(String.format("Transport with id %s not found", id));
            return null;
        }
    }

    @GET
    @Path("/from_{from}_to_{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transport> getTransportByPassengerCount(@PathParam("from") int from, @PathParam("to") int to) {
        logger.info(String.format("Requested transports with passenger count from %s to %s.", from, to));
        List<Transport> resultList = new ArrayList<>();
        Map<Integer, Transport> storage = mapHolder.getMap();
        for (Transport storedTransport : storage.values()) {
            if (storedTransport instanceof PassengerTransport && ((PassengerTransport) storedTransport).getPassengersCount() >= from && ((PassengerTransport) storedTransport).getPassengersCount() <= to) {
                logger.info(String.format("Following transport fits criteria: %s", storedTransport));
                resultList.add(storedTransport);
            } else {
                logger.warn(String.format("Following transport doesn't fit criteria: %s", storedTransport));
            }
        }
        return resultList;
    }

    @GET
    @Path("/av_count")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getAveragePassengerCount() {
        int passengersSum = 0;
        Map<Integer, Transport> storage = mapHolder.getMap();
        for (Transport storedTransport : storage.values()) {
            if (storedTransport instanceof PassengerTransport)
                passengersSum += ((PassengerTransport) storedTransport).getPassengersCount();
        }
        return BigDecimal.valueOf(passengersSum).divide(BigDecimal.valueOf(storage.size()));
    }

    public void setMapHolder(TransportMapHolder mapHolder) {
        this.mapHolder = mapHolder;
    }
}
