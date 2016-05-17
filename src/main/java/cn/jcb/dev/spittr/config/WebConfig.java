package cn.jcb.dev.spittr.config;

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

@Configuration
@EnableWebMvc
@ComponentScan("cn.jcb.dev.spittr.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

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
	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
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
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
		tiles.setCheckRefresh(true);
		return tiles;
	}
}
