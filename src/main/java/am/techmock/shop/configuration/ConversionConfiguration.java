package am.techmock.shop.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

@AutoConfiguration()
public class ConversionConfiguration {

	@Bean
	public ObjectMapper defaultObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public ConversionServiceFactoryBean conversionService(Set<Converter<?, ?>> converters) {
		var factory = new ConversionServiceFactoryBean();
		factory.setConverters(converters);
		return factory;
	}
}
