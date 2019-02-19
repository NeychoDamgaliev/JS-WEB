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


    public void sort(String colName) {
        fixSordDirection();
        try {
            this.catsList = this.catService.findAllCatsOrdered(colName.toLowerCase(),this.sortDir.toLowerCase())
                    .stream()
                    .map(cat -> this.modelMapper.map(cat, CatViewModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            this.catsList = new ArrayList<>();
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
