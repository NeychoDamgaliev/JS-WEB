package residentevil.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import residentevil.web.converters.StringToCapitalBindingModelConverter;

/**
 * Created by Neycho Damgaliev on 3/19/2019.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToCapitalBindingModelConverter());
    }
}
