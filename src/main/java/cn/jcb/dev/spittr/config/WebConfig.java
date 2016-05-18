package cn.jcb.dev.spittr.config;

import javax.servlet.ServletContext;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("cn.jcb.dev.spittr.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/views/");
//		resolver.setSuffix(".jsp");
//		//默认解析的view对象是InternalResourceView
//		//如果Jsp中用到了Jstl，可以配置resolver解析出JstlView对象
//		//JSTL’s formatting tags need a Locale to properly format locale-specific values such as dates and money. And its message tags can use a Spring message source and a Locale to properly choose messages to render in HTML.
//		//resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
//		resolver.setExposeContextBeansAsAttributes(true);
//		return resolver;
//	}
	
	
//	@Bean
//	public ViewResolver viewResolver() {
//		return new TilesViewResolver();
//	}
	

	@Bean
	public ITemplateResolver templateResolver(ServletContext servletContext) {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}
	
	@Bean
	public ITemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver(ITemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}	
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/messages");
		//messageSource.setBasename("classpath://messages");
		// messageSource.setBasename("file:///etc/spittr/messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	/*
	 * a TilesConfigurer bean whose job is to locate and load tile definitions 
	 * and generally coordinate Tiles.
	 */
//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer tiles = new TilesConfigurer();
//		tiles.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
//		tiles.setCheckRefresh(true);
//		return tiles;
//	}
}
