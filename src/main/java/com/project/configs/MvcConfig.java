package com.project.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;
=======
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
<<<<<<< HEAD
@EnableJpaAuditing
=======
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b
public class MvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("main/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file://"+fileUploadPath);
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasenames("messages.commons", "messages.validations", "messages.errors");

        return ms;
    }

<<<<<<< HEAD
    public HiddenHttpMethodFilter httpMethodFilter() { // GET, POST 이외의 것들
        return new HiddenHttpMethodFilter();
    }

=======
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b
}