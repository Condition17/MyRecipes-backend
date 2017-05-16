package api.v1.AddOns;

import api.v1.Services.FTSService;
import org.hibernate.dialect.PostgreSQL9Dialect;

public class CustomPostgresDialect extends PostgreSQL9Dialect {

    public CustomPostgresDialect() {
        super();
        registerFunction("fts", new FTSService());
    }

}