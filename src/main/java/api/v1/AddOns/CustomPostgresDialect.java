package api.v1.AddOns;

import api.v1.Services.FTSService;
import ch.qos.logback.core.db.dialect.PostgreSQLDialect;
import ch.qos.logback.core.db.dialect.*;
import org.hibernate.dialect.Dialect.*;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.function.SQLFunction;

import java.util.Locale;

/**
 * Created by cristi on 5/7/17.
 */
public class CustomPostgresDialect extends PostgreSQL9Dialect {

    public CustomPostgresDialect() {
        super();
        registerFunction("fts", new FTSService());
    }

}