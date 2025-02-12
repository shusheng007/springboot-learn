package top.ss007.composite.common.config.converters;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import top.ss007.composite.common.config.Developer;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/13 13:27
 * description:
 */

@Component
@ConfigurationPropertiesBinding
public class DeveloperConverter implements Converter<String, Developer> {
    @Override
    public Developer convert(String source) {
        String[] info= source.split(";");
        return new Developer(info[0],Integer.parseInt(info[1]),info[2]);
    }
}
