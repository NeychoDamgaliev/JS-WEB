package fdmc.services;

import fdmc.domain.models.service.CatServiceModel;
import fdmc.domain.models.view.CatViewModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */
public interface CatService {

    boolean saveCat(CatServiceModel cat);

    List<CatServiceModel> findAllCats();
}
