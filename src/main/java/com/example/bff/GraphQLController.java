package com.example.bff;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bff.services.GraphQLService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/graphql")
public class GraphQLController {

    private final GraphQLService graphQLService;

    public GraphQLController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping("/user")
    public Mono<String> executeUserGraphQL(@RequestBody GraphQLService.GraphQLRequest request) {
        return graphQLService.executeUserGraphQLQuery(request.getQuery(), request.getVariables());
    }

    @PostMapping("/role")
    public Mono<String> executeRoleGraphQL(@RequestBody GraphQLService.GraphQLRequest request) {
        return graphQLService.executeRolGraphQLQuery(request.getQuery(), request.getVariables());
    }
}

