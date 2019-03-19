package residentevil.web.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import residentevil.web.domain.models.binding.CapitalBindingModel;

import javax.validation.constraints.NotNull;

/**
 * Created by Neycho Damgaliev on 3/19/2019.
 */
@Component
public class StringToCapitalBindingModelConverter implements Converter<String, CapitalBindingModel> {

    @Override
    public CapitalBindingModel convert(@NotNull String id) {
        CapitalBindingModel capitalBindingModel = new CapitalBindingModel();
        capitalBindingModel.setId(Long.parseLong(id));
        return capitalBindingModel;
    }
}
