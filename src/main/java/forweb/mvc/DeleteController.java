package forweb.mvc;

import forweb.TransportMapHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import transport.storages.ManufacturerStorage;

/**
 * Created by olomakovskyi on 11/4/2014.
 */

@Controller
@Scope("session")
public class DeleteController {
    @Autowired
    private TransportMapHolder holder;
    @Autowired
    private ManufacturerStorage manufacturers;

    @RequestMapping("/delete")
    public String processRequest(ModelMap model) {
        model.addAttribute("holder", holder);
        model.addAttribute("manufacturers", manufacturers);
        return "Deletion";
    }

    public TransportMapHolder getHolder() {
        return holder;
    }

    public void setHolder(TransportMapHolder holder) {
        this.holder = holder;
    }

    public ManufacturerStorage getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(ManufacturerStorage manufacturers) {
        this.manufacturers = manufacturers;
    }

}
