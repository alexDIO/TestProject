package forweb.webservices;

import forweb.TransportMapHolder;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import transport.classes.Transport;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olomakovskyi on 11/5/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/ApplicationContextTest.xml")
public class CRUDServiceTest {

    @Autowired
    private TransportMapHolder holder;

    @Test
    public void testGetTransportByPassengerCount(){
        CRUDService service = new CRUDService();
        service.setMapHolder(holder);
        holder.addTransport(1, "Sedan", "Audi", "red", 2000, 5, "petrol", "automate", 0);
        holder.addTransport(2,"Coupe","BMW","white",2009,2,"gas","manual",0);
        holder.addTransport(3,"Bus","Toyota","black",2008,100,"petrol","manual",0);
        holder.addTransport(4,"Truck","Volvo","green",2001,0,"petrol","manual",100500);

        List<Transport> methodResultList = service.getTransportByPassengerCount(2,100);
        List<Transport> mockList = new ArrayList<>();
        mockList.add(holder.getMap().get(1));
        mockList.add(holder.getMap().get(2));
        mockList.add(holder.getMap().get(3));

        Assert.assertEquals(mockList, methodResultList);
    }

    @Test
    public void testGetAveragePassengerCount(){
        CRUDService service = new CRUDService();
        service.setMapHolder(holder);
        holder.addTransport(1, "Sedan", "Audi", "red", 2000, 5, "petrol", "automate", 0);
        holder.addTransport(2,"Coupe","BMW","white",2009,2,"gas","manual",0);
        holder.addTransport(3,"Bus","Toyota","black",2008,100,"petrol","manual",0);
        holder.addTransport(4,"Truck","Volvo","green",2001,0,"petrol","manual",100500);

        BigDecimal resultMethod = service.getAveragePassengerCount();
        BigDecimal mockAverage = new BigDecimal(26.75);
        Assert.assertEquals(mockAverage, resultMethod);
    }

    public void setHolder(TransportMapHolder holder) {
        this.holder = holder;
    }
}
