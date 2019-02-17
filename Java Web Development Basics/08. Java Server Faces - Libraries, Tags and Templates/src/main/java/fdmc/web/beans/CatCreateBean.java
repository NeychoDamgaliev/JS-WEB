package fdmc.web.beans;

import fdmc.domain.models.binding.CatCreateBindingModel;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.repository.CatRepository;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */
@Named(value = "catCreate")
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel catCreateBindingModel;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatCreateBean() {
    }


    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void catRegister() throws IOException {
        this.catService.saveCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/javaServerFaces/all-cats.xhtml");
    }
}
