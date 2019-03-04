package realestate.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import realestate.utils.HtmlReader;

import javax.validation.Validation;
import javax.validation.Validator;


/**
 * Created by Neycho Damgaliev on 3/4/2019.
 */

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public HtmlReader htmlReader() {
        return new HtmlReader();
    }
}
