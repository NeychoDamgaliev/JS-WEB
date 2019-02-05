package meTube.service;

import meTube.domain.entities.Tube;
import meTube.domain.models.service.TubeServiceModel;
import meTube.repository.MeTubeRepository;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
public class MeTubeServiceImpl implements MeTubeService {

    private final MeTubeRepository repository;
    private final ModelMapper modelMapper;

    @Inject
    public MeTubeServiceImpl(MeTubeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void saveTube(TubeServiceModel tsm) {
        this.repository.save(this.modelMapper.map(tsm, Tube.class));
    }

    @Override
    public TubeServiceModel findTubeByName(String name) {

        Tube tube = this.repository.findByName(name).orElse(null);
        TubeServiceModel tsm = this.modelMapper.map(tube, TubeServiceModel.class);

        return tsm;
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        return this.repository.findAll().stream()
                .map(t-> this.modelMapper.map(t,TubeServiceModel.class))
                .collect(Collectors.toList());
    }
}
