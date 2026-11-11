package dev.antoniogrillo.primo.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.jspecify.annotations.Nullable;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphQLResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected @Nullable GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if(ex instanceof GraphQLException e){
            return GraphqlErrorBuilder.newError(env)
                    .errorType(e.getStatus())
                    .message(e.getMessage()).build();
        }
        return GraphqlErrorBuilder.newError(env).message(ex.getMessage()).build();

    }
}
