package com.bradesco.sistemabradesco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
               .info(new Info()
                       .title("Gerenciador de Protocolos")
                       .description("Esta API foi desenvolvida por alunos participantes da residência do Porto Digital, em colaboração com a empresa Bradesco, para o gerenciamento de protocolos. Com esta ferramenta, você pode acessar e gerenciar todos os seus protocolos de maneira eficiente e segura.</p>\r\n" + //
                                                      "Para começar, tente realizar as requisições listadas abaixo:.")
                       .version("Versão 1.0")
                       .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                       .contact(new Contact()
                               .name("Squad33")
                               .email("squad33.residencia@gmail.com")));
    }
}
