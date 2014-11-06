package transport.classes;

import java.util.Map;

/**
 * Created by olomakovskyi on 9/5/2014.
 */
public class TransportFactory {
    private Map<String, Class<? extends Transport>> map;

    public TransportFactory(Map<String, Class<? extends Transport>> map){
        this.map = map;
    }

    public Transport getTransport(String transportType) {
        try {
            return map.get(transportType.toLowerCase()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
