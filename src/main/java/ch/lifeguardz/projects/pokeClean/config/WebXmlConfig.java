package ch.lifeguardz.projects.pokeClean.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import ch.lifeguardz.projects.pokeClean.Application;

public class WebXmlConfig extends SpringBootServletInitializer {

    /* (non-Javadoc)
     * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}