package cn.jcb.dev.spittr.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		//configure Servlet 3.0(Tomcat7) multipart support 
		//限制2M附件，请求大小限制4M，无论文件大小，都写到临时目录中/tmp/spittr/uploads
		registration.setMultipartConfig(new MultipartConfigElement("/Users/john/dev/temp/spittr/uploads", 2097152, 4194304, 0));
	}
}
