package dev.antoniogrillo.primo.graphql;

import dev.antoniogrillo.primo.entity.Immobile;
import dev.antoniogrillo.primo.facade.def.ImmobileGraphQLFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImmobileResolver {

    private final ImmobileGraphQLFacade facade;

    @QueryMapping
    public List<Immobile> getImmobili(){
        return facade.getImmobili();
    }
}
