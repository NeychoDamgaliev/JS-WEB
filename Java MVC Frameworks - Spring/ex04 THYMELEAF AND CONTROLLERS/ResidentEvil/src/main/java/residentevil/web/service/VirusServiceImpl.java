package residentevil.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import residentevil.web.domain.entities.Virus;
import residentevil.web.domain.models.binding.VirusBindingModel;
import residentevil.web.domain.models.service.VirusServiceModel;
import residentevil.web.domain.models.service.VirusShowServiceModel;
import residentevil.web.repository.VirusRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Neycho Damgaliev on 3/17/2019.
 */
@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean spreadVirus(VirusServiceModel model) {
        return this.virusRepository.save(this.modelMapper.map(model, Virus.class)) != null;
    }

    @Override
    public boolean editVirus(VirusServiceModel model, String id) {
        Virus editedVirus = this.modelMapper.map(model, Virus.class);

        Virus virus = this.copyVirusDataToDBObject(editedVirus, id);
        return this.virusRepository.saveAndFlush(virus) != null;
    }

    private Virus copyVirusDataToDBObject(Virus editedVirus, String id) {
        Virus virus = new Virus();
        virus.setId(id);
        virus.setCapitals(editedVirus.getCapitals());
        virus.setCreator(editedVirus.getCreator());
        virus.setCurable(editedVirus.getCurable());
        virus.setDeadly(editedVirus.getDeadly());
        virus.setDescription(editedVirus.getDescription());
        virus.setHoursUntilTurn(editedVirus.getHoursUntilTurn());
        virus.setMagnitude(editedVirus.getMagnitude());
        virus.setMutation(editedVirus.getMutation());
        virus.setName(editedVirus.getName());
        virus.setReleasedOn(editedVirus.getReleasedOn());
        virus.setSideEffects(editedVirus.getSideEffects());
        virus.setTurnoverRate(editedVirus.getTurnoverRate());

        return virus;
    }

    @Override
    public List<VirusShowServiceModel> findAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(v -> this.modelMapper.map(v, VirusShowServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public VirusBindingModel findById(String id) {
        return this.modelMapper.map(this.virusRepository.findById(id).get(), VirusBindingModel.class);
    }

    @Override
    public void deleteVirus(String id) {
        this.virusRepository.deleteById(id);
    }
}
