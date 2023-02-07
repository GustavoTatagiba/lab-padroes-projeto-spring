package one.digitalinnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Padrão de Projeto para API REST utilizando a framework SpringBoot.
 * Implementação de Projeto com persistencia de dados e integração via API com o ViaCep.
 * Desenvolvido com apoio da plataforma dio.me.
 *
 * Projeto Spring Boot gerado via Spring Initializr (https://start.spring.io/)
 * <b>Módulos Selecionados</b>:
 * - Spring Data JPA (Java Persistence Api);
 * - Spring Web;
 * - H2 Database;
 * - OpenFeign
 * Obs.: Também foi incluído manualmente via POM, o módulo OpenApi/Swagger;
 *
 * <b>Especificações</b>:
 * - Spring Framework version: 2.5.4
 * - Java version: 11;
 * - OpenApi Version: 1.5.10;
 * - Spring Cloud Version: 2020.0.3
 *
 * <b>Implementações de Referência</b>:
 * https://github.com/digitalinnovationone/lab-padroes-projeto-spring
 * https://web.dio.me/lab/explorando-padroes-de-projetos-na-pratica-com-java/learning/975a7cad-08ec-43be-9f34-5c3c65aa6ba7
 * https://web.dio.me/lab/explorando-padroes-de-projetos-na-pratica-com-java/learning/5393f0ce-16cc-4132-9285-77743f5c6bb3
 *
 * @author Gustavo
 */

@EnableFeignClients //habilita o Feing dentro do Projeto
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

// acessar o OpenApi/Swagger através do navegador para testar o funcionamento: "http://127.0.0.1:8080/swagger-ui.html"