package com.example.bff.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RoleService {

    private final WebClient roleWebClient;

    @Value("${azure.functions.base-url-2}")
    private String baseUrl;

    public RoleService(WebClient roleWebClient) {
        this.roleWebClient = roleWebClient;
    }

    public Mono<String> createRole(String roleJson) {
        return roleWebClient.post()
                .uri(baseUrl + "/CreateUserRole")
                .bodyValue(roleJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getRolesByUserId(Integer idUser) {
        return roleWebClient.get()
                .uri(baseUrl + "/GetUserRoles?idUsuario=" + idUser)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> updateRole(String roleJson) {
        return roleWebClient.put()
                .uri(baseUrl + "/UpdateUserRole")
                .bodyValue(roleJson)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> deleteRole(Integer idUser, Integer idRol) {
        String uri = baseUrl + "/DeleteUserRole?idUsuario=" + idUser + "&idRol=" + idRol;
        System.out.println("Enviando solicitud DELETE a: " + uri);
        
        return roleWebClient.delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Respuesta exitosa: " + response))
                .doOnError(error -> System.err.println("Error en la solicitud: " + error.getMessage()));
    }
}
