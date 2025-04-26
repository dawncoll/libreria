package daw.libreria.web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;


@Configuration
@EnableWebMvc
@ComponentScan({ "daw.libreria" })
/**
 * Java configuration file that is used for Spring MVC and Thymeleaf
 * configurations
 */
public class WebMVCConfig implements WebMvcConfigurer,  ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(new String[] {".html", ".xhtml"});
        return resolver;
    }
    
   

//    @Bean
//    public ViewResolver javascriptViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine(javascriptTemplateResolver()));
//        resolver.setContentType("application/javascript");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames(new String[] {".js"});
//        return resolver;
//    }
//
//    @Bean
//    public ViewResolver plainViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine(plainTemplateResolver()));
//        resolver.setContentType("text/plain");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setViewNames(new String[] {".txt"});
//        return resolver;
//    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        engine.setTemplateEngineMessageSource(messageSource());
        return engine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

//    private ITemplateResolver javascriptTemplateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("/WEB-INF/js/");
//        resolver.setCacheable(false);
//        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
//        return resolver;
//    }
//
//    private ITemplateResolver plainTemplateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("/WEB-INF/txt/");
//        resolver.setCacheable(false);
//        resolver.setTemplateMode(TemplateMode.TEXT);
//        return resolver;
//    }

    @Bean
    @Description("Spring Message Resolver")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**", "/css/**")
//            .addResourceLocations("classpath:/static/images/", "/WEB-INF/resources/", "/WEB-INF/css/");
//    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/images/**")
            .addResourceLocations("classpath:/static/images/");
    }

    
}
