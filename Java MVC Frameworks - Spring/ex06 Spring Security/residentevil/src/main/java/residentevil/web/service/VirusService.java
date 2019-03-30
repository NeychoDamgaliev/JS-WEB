package residentevil.web.service;

import residentevil.web.domain.models.binding.VirusBindingModel;
import residentevil.web.domain.models.service.VirusServiceModel;
import residentevil.web.domain.models.service.VirusShowServiceModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/17/2019.
 */
public interface VirusService {
    boolean spreadVirus(VirusServiceModel model);
    boolean editVirus(VirusServiceModel model, String id);
    List<VirusShowServiceModel> findAllViruses();
    VirusBindingModel findById(String id);

    void deleteVirus(String id);
}
