package api.v1.Services;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

import java.util.List;

/**
 * Created by cristi on 5/7/17.
 */
public class FTSService implements SQLFunction {
    public static final String FTS_VECTOR_FIELD = "search_text";

    @Override
    public boolean hasArguments() {
        return false;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
        return false;
    }

    @Override
    public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String render(Type firstArgumentType, List args, SessionFactoryImplementor factory) throws QueryException {
        if (args!= null && args.size() < 2) {
            throw new IllegalArgumentException(
                    "The function must be passed 2 arguments");
        }

        String fragment = null;
        String ftsConfig = null;
        String field = null;
        String value = null;
        if(args.size() == 3) {
            ftsConfig = (String) args.get(0);
            field = (String) args.get(1);
            value = (String) args.get(2);
            fragment = field+" @@ plainto_tsquery("+ftsConfig+", "+value+")";
        } else {
            field = (String) args.get(0);
            value = (String) args.get(1);
            fragment = field+" @@ plainto_tsquery("+value+")";
        }
        return fragment;
    }
}
