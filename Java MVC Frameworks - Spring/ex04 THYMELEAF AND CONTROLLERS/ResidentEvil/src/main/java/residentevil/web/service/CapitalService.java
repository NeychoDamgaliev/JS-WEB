package residentevil.web.service;

import residentevil.web.domain.models.service.CapitalServiceModel;
import residentevil.web.domain.models.view.CapitalListViewModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
public interface CapitalService {
    List<CapitalListViewModel> findAllCapitals();
}
