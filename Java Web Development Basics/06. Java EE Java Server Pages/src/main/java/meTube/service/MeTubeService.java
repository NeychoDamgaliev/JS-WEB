package meTube.service;

import meTube.domain.models.service.TubeServiceModel;

import java.util.List;

/**
 * Created by Neycho Damgaliev on 2/3/2019.
 */
public interface MeTubeService {

    void saveTube(TubeServiceModel tsm);

    TubeServiceModel findTubeByName(String name);

    List<TubeServiceModel> findAllTubes();
}

