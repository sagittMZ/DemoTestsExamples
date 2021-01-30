package configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)  //если FIRST, то берет данные из первого варианта,
//если MERGE, то подтягивает из следующих, по необходимости
@Config.Sources({
       // "system.properties", //high priority
        "classpath:example.properties"
})
public interface ExampleConfig extends Config {
    @DefaultValue("Азия")
    @Key("destination")
    String destination();

    @DefaultValue("test_db_postgresql_url")
    @Key("db_postgresql_url")
    String db_postgresql_url();

    @DefaultValue("test_db_postgresql_password")
    @Key("db_postgresql_password")
    String db_postgresql_password();

    @DefaultValue("org.postgresql.Driver")
    @Key("class_for_name_postgresql")
    String class_for_name_postgresql();

    @DefaultValue("blablabla")
    @Key("user_node")
    String user_node();




}
