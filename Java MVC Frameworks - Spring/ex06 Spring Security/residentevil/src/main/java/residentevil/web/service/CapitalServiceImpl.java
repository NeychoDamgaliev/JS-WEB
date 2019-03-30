package residentevil.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.web.domain.models.view.CapitalListTableViewModel;
import residentevil.web.domain.models.view.CapitalListViewModel;
import residentevil.web.repository.CapitalRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
@Service
public class CapitalServiceImpl implements CapitalService {


    private CapitalRepository capitalRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CapitalListViewModel> findAllCapitals() {
        return this.capitalRepository.findAllOrderedByName()
                .stream()
                .map(c->this.modelMapper.map(c, CapitalListViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CapitalListViewModel findById(String id) {
        return this.modelMapper.map(this.capitalRepository.findById(id).get(),CapitalListViewModel.class);
    }

    @Override
    public List<CapitalListTableViewModel> findAllCapitalsTable() {
        return this.capitalRepository.findAllOrderedByName()
                .stream()
                .map(c->this.modelMapper.map(c, CapitalListTableViewModel.class))
                .collect(Collectors.toList());
    }
}
