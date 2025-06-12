package com.nsu.soap.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing a singleton EntityManagerFactory.
 */
public final class JPAUtil {
    // Register shutdown hook as soon as this class is loaded
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::shutdown, "JPAUtil-ShutdownHook"));
    }

    // Private constructor to prevent instantiation
    private JPAUtil() { }

    /**
     * Holder class for lazy-loading the EntityManagerFactory in a thread-safe way.
     */
    private static class EMFHolder {
        private static final EntityManagerFactory EMF = buildEntityManagerFactory();

        private static EntityManagerFactory buildEntityManagerFactory() {
            Map<String, Object> props = new HashMap<>();

            // Load properties from EnvConfig
           /* props.put("jakarta.persistence.jdbc.user", EnvConfig.get("db.user"));
            props.put("jakarta.persistence.jdbc.password", EnvConfig.get("db.password"));
            props.put("jakarta.persistence.jdbc.url", EnvConfig.get("db.url"));
            props.put("jakarta.persistence.schema-generation.database.action", EnvConfig.get("db.schema.action"));
            props.put("jakarta.persistence.schema-generation.script.action", EnvConfig.get("db.schema.action"));
            props.put("hibernate.hbm2ddl.auto", EnvConfig.get("hb.hbm2ddl.auto"));
            props.put("hibernate.show_sql", EnvConfig.get("hb.show_sql"));
            props.put("hibernate.format_sql", EnvConfig.get("hb.format_sql"));
            props.put("hibernate.dialect", EnvConfig.get("hb.dialect"));
            props.put("hibernate.use_sql_comments", EnvConfig.get("hb.use_sql_comments"));*/

            // Load properties from System environment or configuration Azure App Service
            props.put("jakarta.persistence.jdbc.user", System.getenv("DB_USER"));
            props.put("jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD"));
            props.put("jakarta.persistence.jdbc.url", System.getenv("DB_URL"));
            props.put("jakarta.persistence.schema-generation.database.action", System.getenv("DB_SCHEMA_ACTION"));
            props.put("jakarta.persistence.schema-generation.script.action", System.getenv("DB_SCHEMA_ACTION"));
            props.put("hibernate.hbm2ddl.auto", System.getenv("HB_HBM2DDL_AUTO"));
            props.put("hibernate.show_sql", System.getenv("HB_SHOW_SQL"));
            props.put("hibernate.format_sql", System.getenv("HB_FORMAT_SQL"));
            props.put("hibernate.dialect", System.getenv("HB_DIALECT"));
            props.put("hibernate.use_sql_comments", System.getenv("HB_USE_SQL_COMMENTS"));

            // you can add other dynamic or vendor-specific properties here
            return Persistence.createEntityManagerFactory(
                    "FlightSOAPWebServicePU", props
            );
        }
    }

    /**
     * Returns the singleton EntityManagerFactory.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return EMFHolder.EMF;
    }

    /**
     * Closes the EntityManagerFactory if it's open. Called on JVM shutdown.
     */
    public static void shutdown() {
        EntityManagerFactory emf = EMFHolder.EMF;
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
