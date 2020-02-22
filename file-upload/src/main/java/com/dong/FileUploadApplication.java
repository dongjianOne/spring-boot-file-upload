package com.dong;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

	/**
	 * 处理当上传文件大小大于10M时连接重置文体
	 * @return
	 */
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbedded(){
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>){
				((AbstractHttp11Protocol) connector.getProtocolHandler()).setMaxExtensionSize(-1);
			}
		});
		return tomcat;
	}
}
