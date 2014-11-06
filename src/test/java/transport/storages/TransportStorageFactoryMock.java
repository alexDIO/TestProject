package transport.storages;

/**
 * Created by olomakovskyi on 11/5/2014.
 */
public class TransportStorageFactoryMock implements TransportStorageFactory{

    public TransportStorage getStorage() {
        return new TransportStorageMock();
    }
}
