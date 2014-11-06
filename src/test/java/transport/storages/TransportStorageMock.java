package transport.storages;

import transport.classes.Transport;
import transport.classes.TransportPojo;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by olomakovskyi on 11/5/2014.
 */
public class TransportStorageMock implements TransportStorage {
    @Override
    public void addTransport(TransportPojo inPojo) throws IOException {

    }

    @Override
    public void deleteTransport(int id) throws IOException {

    }

    @Override
    public Map<Integer, Transport> getAllTransport() throws IOException {
        return new HashMap<>();
    }
}
