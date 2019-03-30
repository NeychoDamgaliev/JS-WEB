package residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import residentevil.web.domain.models.service.VirusShowServiceModel;
import residentevil.web.domain.models.view.CapitalListTableViewModel;
import residentevil.web.domain.models.view.CapitalListViewModel;
import residentevil.web.repository.CapitalRepository;
import residentevil.web.service.CapitalService;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 3/22/2019.
 */
@Controller
@RequestMapping("/capitals")
public class CapitalController  extends BaseController{

    private final CapitalService capitalService;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalController(CapitalService capitalService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/show-table",produces = "application/text")
    public ModelAndView showTable(ModelAndView modelAndView) {

        List<CapitalListTableViewModel> allCapitals = this.capitalService.findAllCapitalsTable();
        modelAndView.addObject("allCapitals", allCapitals);
        return super.view("/tables/capital-table", modelAndView);
    }
}
