package forweb.webservices;

import forweb.TransportMapHolder;
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

    @Autowired
    private TransportMapHolder mapHolder;


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, Transport> getMap() throws IOException {
        if (mapHolder != null) {
            return mapHolder.getMap();
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transport getTransportById(@PathParam("id") int id) {
        return mapHolder.getMap().get(id);
    }

    @GET
    @Path("/from_{from}_to_{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transport> getTransportByPassengerCount(@PathParam("from") int from, @PathParam("to") int to) {
        List<Transport> resultList = new ArrayList<>();
        Map<Integer, Transport> storage = mapHolder.getMap();
        for (Transport storedTransport : storage.values()) {
            if (storedTransport instanceof PassengerTransport && ((PassengerTransport) storedTransport).getPassengersCount() >= from && ((PassengerTransport) storedTransport).getPassengersCount() <= to) {
                resultList.add(storedTransport);
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
