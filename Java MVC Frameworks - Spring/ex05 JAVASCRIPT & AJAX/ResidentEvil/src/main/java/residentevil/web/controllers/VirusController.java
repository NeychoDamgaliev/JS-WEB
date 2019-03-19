package residentevil.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.web.domain.models.binding.VirusBindingModel;
import residentevil.web.domain.models.service.VirusServiceModel;
import residentevil.web.domain.models.service.VirusShowServiceModel;
import residentevil.web.domain.models.view.CapitalListViewModel;
import residentevil.web.service.CapitalService;
import residentevil.web.service.VirusService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    private final ModelMapper modelMapper;
    private final VirusService virusService;
    private final CapitalService capitalService;

    @Autowired
    public VirusController(CapitalService capitalService, ModelMapper modelMapper, VirusService virusService) {
        this.capitalService = capitalService;
        this.modelMapper = modelMapper;
        this.virusService = virusService;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView,
                            @ModelAttribute(name = "bindingModel") VirusBindingModel bindingModel) {


        modelAndView.addObject("bindingModel", bindingModel);
        modelAndView.addObject("allCapitals", this.capitalService.findAllCapitals());
        return super.view("add-virus", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute(name = "bindingModel") VirusBindingModel bindingModel,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);
            modelAndView.addObject("allCapitals", this.capitalService.findAllCapitals());
            return super.view("add-virus", modelAndView);
        }
        this.virusService.spreadVirus(this.modelMapper.map(bindingModel, VirusServiceModel.class));
        return super.redirect("/viruses/show");
    }

    //    @GetMapping("/show")
//    public ModelAndView show(ModelAndView modelAndView) {
//        List<VirusShowServiceModel> allViruses = this.virusService.findAllViruses();
//        modelAndView.addObject("allViruses", allViruses);
//        return super.view("show-virus", modelAndView);
//    }
    @GetMapping("/show")
    public ModelAndView show() {
        return super.view("showJS");
    }

    @GetMapping(value = "/fetch", produces = "application/json")
    @ResponseBody
    public Object fetchData() {
        return new ArrayList<String>() {{
            add("Pesho");
            add("Gosho");
            add("Stamat");
        }};
    }


    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") String id,
                             @ModelAttribute(name = "bindingModel") VirusBindingModel bindingModel,
                             ModelAndView modelAndView) {

        modelAndView.addObject("bindingModel", this.virusService.findById(id));
        modelAndView.addObject("allCapitals", this.capitalService.findAllCapitals());
        return super.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit")
    public ModelAndView editConfirm(@RequestParam("id") String id,
                                    @Valid @ModelAttribute(name = "bindingModel") VirusBindingModel bindingModel,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingModel", bindingModel);
            modelAndView.addObject("allCapitals", this.capitalService.findAllCapitals());
            return super.view("edit-virus", modelAndView);
        }
        this.virusService.editVirus(this.modelMapper.map(bindingModel, VirusServiceModel.class), id);
        return super.redirect("/viruses/show");
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") String id,
                               @ModelAttribute(name = "bindingModel") VirusBindingModel bindingModel,
                               ModelAndView modelAndView) {

        modelAndView.addObject("bindingModel", this.virusService.findById(id));
        modelAndView.addObject("allCapitals", this.capitalService.findAllCapitals());
        return super.view("delete-virus", modelAndView);
    }

    @PostMapping("/delete")
    public ModelAndView deleteConfirm(@RequestParam("id") String id) {
        this.virusService.deleteVirus(id);
        return super.redirect("/viruses/show");
    }
}
