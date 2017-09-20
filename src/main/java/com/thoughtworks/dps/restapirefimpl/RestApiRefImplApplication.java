package com.thoughtworks.dps.restapirefimpl;

import com.thoughtworks.dps.restapirefimpl.config.ArticleTypeConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

@SpringBootApplication
public class RestApiRefImplApplication {

	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {
			@Override
			public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                errorAttributes.remove("exception");
                errorAttributes.remove("status");
				return errorAttributes;
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApiRefImplApplication.class, args);
	}
}
