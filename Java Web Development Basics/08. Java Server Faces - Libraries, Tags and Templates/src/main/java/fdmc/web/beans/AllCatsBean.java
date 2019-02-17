package fdmc.web.beans;

import fdmc.domain.models.view.CatViewModel;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */
@Named(value = "cats")
@RequestScoped

public class AllCatsBean {

    private List<CatViewModel> catsList;

    private CatService catService;
    private ModelMapper modelMapper;


    public AllCatsBean() {
    }

    @Inject
    public AllCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;

        try {
            this.catsList = this.catService.findAllCats()
                    .stream()
                    .map(cat -> this.modelMapper.map(cat, CatViewModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            this.catsList = new ArrayList<>();
        }
    }

    public List<CatViewModel> getCatsList() {
        return catsList;
    }

    public void setCatsList(List<CatViewModel> catsList) {
        this.catsList = catsList;
    }

    public List<CatViewModel> sort(){

        this.catsList = this.catsList.stream()
                .sorted((a, b) -> b.getName().compareTo(a.getName()))
                .collect(Collectors.toList());
        return this.catsList;
    }

}
