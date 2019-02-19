package fdmc.web.beans;

import fdmc.domain.models.view.CatViewModel;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */

@Named(value = "cats")
@RequestScoped
public class AllCatsBean implements Serializable {

    private List<CatViewModel> catsList;

    private String sortDir;

    private CatService catService;
    private ModelMapper modelMapper;


    public AllCatsBean() {
    }

    @Inject
    public AllCatsBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
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
        String d = "";
        return catsList;
    }

    public void setCatsList(List<CatViewModel> catsList) {
        this.catsList = catsList;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public void sortByName() {
        fixSordDirection();
        if("asc".equals(this.sortDir)) {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> a.getName().compareTo(b.getName()))
                    .collect(Collectors.toList());
        } else {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> b.getName().compareTo(a.getName()))
                    .collect(Collectors.toList());
        }
    }
    public void sortByBreed() {
        fixSordDirection();
        if("asc".equals(this.sortDir)) {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> a.getBreed().compareTo(b.getBreed()))
                    .collect(Collectors.toList());
        } else {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> b.getBreed().compareTo(a.getBreed()))
                    .collect(Collectors.toList());
        }
    }

    public void sortByColor() {
        fixSordDirection();
        if("asc".equals(this.sortDir)) {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> a.getColor().compareTo(b.getColor()))
                    .collect(Collectors.toList());
        } else {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> b.getColor().compareTo(a.getColor()))
                    .collect(Collectors.toList());
        }
    }

    public void sortByGender() {
        fixSordDirection();
        if("asc".equals(this.sortDir)) {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> a.getGender().compareTo(b.getGender()))
                    .collect(Collectors.toList());
        } else {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> b.getGender().compareTo(a.getGender()))
                    .collect(Collectors.toList());
        }
    }

    public void sortByPrice() {
        fixSordDirection();
        if("asc".equals(this.sortDir)) {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> a.getPrice().compareTo(b.getPrice()))
                    .collect(Collectors.toList());
        } else {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> b.getPrice().compareTo(a.getPrice()))
                    .collect(Collectors.toList());
        }
    }

    public void sortByAddedOn() {
        fixSordDirection();
        if("asc".equals(this.sortDir)) {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> a.getAddedOn().compareTo(b.getAddedOn()))
                    .collect(Collectors.toList());
        } else {
            this.catsList = this.catsList
                    .stream()
                    .sorted((a,b) -> b.getAddedOn().compareTo(a.getAddedOn()))
                    .collect(Collectors.toList());
        }
    }

    private void fixSordDirection(){
        this.sortDir = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("sortDir");
    }
}
