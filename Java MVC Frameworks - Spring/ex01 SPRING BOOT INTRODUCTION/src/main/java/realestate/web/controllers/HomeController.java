package realestate.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import realestate.domain.models.view.OfferViewModel;
import realestate.services.OfferService;
import realestate.utils.HtmlReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 3/2/2019.
 */
@Controller
public class HomeController {

    private final OfferService offerService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return prepareHtml();
    }

    private String prepareHtml() throws IOException {

        List<OfferViewModel> offers = this.offerService.findAllOffers().stream()
                .map(offerServiceModel -> this.modelMapper.map(offerServiceModel,OfferViewModel.class))
                .collect(Collectors.toList());
        StringBuilder offersHtml = new StringBuilder();

        if(offers.size()==0) {
            offersHtml.append("<div class=\"apartment\" style=\"border: red solid 2px\">")
                    .append("There aren't any offers!")
                    .append("</div>");

        } else {

            offers.forEach(offer -> {
                offersHtml.append("<div class=\"apartment\">").append(System.lineSeparator())
                        .append(String.format("<p>Rent: %.2f</p>", offer.getApartmentRent())).append(System.lineSeparator())
                        .append(String.format("<p>Type: %s</p>", offer.getApartmentType())).append(System.lineSeparator())
                        .append(String.format("<p>Commission: %.2f</p>", offer.getAgencyCommission())).append(System.lineSeparator())
                        .append("</div>");
            });
        }
        return this.htmlReader.readHtmlFile("src/main/resources/static/index.html").replace("{{offers}}",offersHtml.toString());
    }
    /*
        <div class="apartment">
		<p>Rent: 700.00</p>
		<p>Type: Two Room apartment</p>
		<p>Commission: 50.00</p>
	</div>
	*/
}
