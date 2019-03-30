package residentevil.web.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import residentevil.web.domain.entities.Capital;
import residentevil.web.domain.entities.Role;
import residentevil.web.repository.CapitalRepository;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
@Configuration
public class ApplicationBeanConfig {
    @Autowired
    private final CapitalRepository capitalRepository;

    public ApplicationBeanConfig(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }

    @Bean
    public ModelMapper modelMapper () {
        ModelMapper mapper = new ModelMapper();
        init(mapper);
        return mapper;
    }

    private void init(ModelMapper modelMapper){
        Converter<String, Capital> stringToCapitalConverter = new Converter<String, Capital>() {
            public Capital convert(MappingContext<String, Capital> context) {
                if(context.getSource()!=null) {
                    context.getSource();
                    return capitalRepository.findById(context.getSource()).orElse(null);
                } else {
                    return null;
                }
            }
        };

        Converter<Capital, String> capitalToStringConverter = new Converter<Capital, String>() {
            public String convert(MappingContext<Capital, String> context) {
                if(context.getSource()!=null) {
                    context.getSource();
                    return context.getSource().getName();
                } else {
                    return null;
                }
            }
        };

        Converter<Role, String> roleToStringConverter = new Converter<Role, String>() {
            public String convert(MappingContext<Role, String> context) {
                if(context.getSource()!=null) {
                    context.getSource();
                    return context.getSource().getAuthority();
                } else {
                    return null;
                }
            }
        };

        modelMapper.addConverter(stringToCapitalConverter);
        modelMapper.addConverter(capitalToStringConverter);
        modelMapper.addConverter(roleToStringConverter);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return  new BCryptPasswordEncoder();
    }
}
