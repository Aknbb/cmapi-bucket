package com.aknbb.cmapibucket.initializer;

import com.aknbb.cmapibucket.CmapibucketApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CmapibucketApplication.class);
    }

}
