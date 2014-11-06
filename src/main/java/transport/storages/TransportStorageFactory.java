package transport.storages;


import java.io.IOException;

/**
 * Created by olomakovskyi on 9/4/2014.
 */
public interface TransportStorageFactory {

    public TransportStorage getStorage() throws IOException, TransportStorageException;

}
