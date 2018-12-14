package com.easybug;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
	  @Bean
	    public Docket createRestApi() {//创建Docket的Bean
		  ParameterBuilder parameterBuilder = new ParameterBuilder();
		  List<Parameter> parameters = new ArrayList<Parameter>();
		  parameterBuilder.name("token")
				  .description("token")
				  .modelRef(new ModelRef("string"))
				  .parameterType("header")
				  .required(false).build();
		  	parameters.add(parameterBuilder.build());
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()//返回一个ApiSelectorBuilder实例控制哪些接口暴露给Swagger来展现 
	                .apis(RequestHandlerSelectors.basePackage("com.easybug.web"))//扫描该包路径下所有Controller定义的API，并产生文档内容
	                .paths(PathSelectors.any())
	                .build().globalOperationParameters(parameters);
	    }

	    private ApiInfo apiInfo() {//用来创建Api的基本信息
	        return new ApiInfoBuilder()
	                .title("hifriends apis")
	                .description("接口")
	                .termsOfServiceUrl("http://blog.didispace.com/")
	                .contact("tigerJay")
	                .version("1.0")
	                .build();
	    }

}
