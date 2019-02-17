package fdmc.services;

import fdmc.domain.entities.Cat;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/17/2019.
 */

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private  final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveCat(CatServiceModel cat) {
        try {
            this.catRepository.save(this.modelMapper.map(cat, Cat.class));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<CatServiceModel> findAllCats() {
        try{
            return this.catRepository.findAll().stream()
                    .map(cat -> this.modelMapper.map(cat, CatServiceModel.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return  null;
        }
    }
}
