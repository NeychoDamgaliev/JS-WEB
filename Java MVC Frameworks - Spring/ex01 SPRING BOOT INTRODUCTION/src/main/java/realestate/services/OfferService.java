package realestate.services;

import realestate.domain.models.binding.OfferFindBindingModel;
import realestate.domain.models.service.OfferServiceModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/4/2019.
 */
public interface OfferService {

    void registerOffer(OfferServiceModel model);

    List<OfferServiceModel> findAllOffers();

    void findOffer(OfferFindBindingModel offerFindBindingModel);
}
