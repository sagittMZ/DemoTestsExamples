package Database;

import configs.ExampleConfig;

import org.aeonbits.owner.ConfigFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBMethods {

    ExampleConfig config = ConfigFactory.newInstance().create(ExampleConfig.class);
    //some external db
    String DB_URL_POSTGRESQL = config.db_postgresql_url(),
           PASS_USER_NODE = config.db_postgresql_password(),
           USER_NODE = config.user_node(),
           CLASS_FOR_NAME_POSTGRESQL = config.class_for_name_postgresql();
    //local db
    private static final String
            CLASS_FOR_NAME_SQLITE="org.sqlite.JDBC",
            USER_SQLITE="",
            PASS_SQLITE="",
            DB_URL_SQLITE ="jdbc:sqlite:sqlite/autotests";

    private static DBMethods instance;
    private Connection connection;

    public DBMethods(String db_type) throws SQLException {
        if (db_type.equals("poqstgresql")) {
            try {
                Class.forName(CLASS_FOR_NAME_POSTGRESQL);
                this.connection = DriverManager.getConnection(DB_URL_POSTGRESQL, USER_NODE, PASS_USER_NODE);
            } catch (ClassNotFoundException ex) {
                System.out.println("Database Connection Creation Failed : " + ex.getMessage());
            }
        } else if (db_type.equals("sqlite")){
            try {
                Class.forName(CLASS_FOR_NAME_SQLITE);
                this.connection = DriverManager.getConnection(DB_URL_SQLITE, USER_SQLITE, PASS_SQLITE);
            } catch (ClassNotFoundException ex) {
                System.out.println("Database Connection Creation Failed : " + ex.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBMethods getInstance(String db_type) throws SQLException {
        if (instance == null) {
            instance = new DBMethods(db_type);
        } else if (instance.getConnection().isClosed()) {
            instance = new DBMethods(db_type);
        }
        return instance;
    }

    public ResultSet selectQuery(String query, String db_type) throws SQLException {
        Statement statement = getInstance(db_type).getConnection().createStatement();
        ResultSet result = statement.executeQuery(query);

        return result;
    }

    public int insertQuery(String query, String db_type) throws SQLException {
        int result = 0;
        PreparedStatement pstmt  = getInstance(db_type).getConnection().prepareStatement(query);
        try {
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("can't insert this query: \n"+query);
            e.printStackTrace();
        }

        return result;
    }

    public void createTable(String query) throws SQLException {
        Statement statement = getInstance("sqlite").getConnection().createStatement();
        statement.execute(query);

    }

    public void dropTable(String query) throws SQLException {
        Statement statement = getInstance("sqlite").getConnection().createStatement();
        statement.execute(query);
    }

    /**
     * create_table method example
     * @author sagitt
     */
    public void createTable() throws SQLException {
        DBMethods db= new DBMethods("sqlite");
        String table_name = "some_name";

        String query = "CREATE TABLE IF NOT EXISTS "+ table_name+ "(\n"
                + "    id STRING NOT NULL PRIMARY KEY,\n"
                + "    content_id STRING  NOT NULL,\n"
                + "    name STRING  NOT NULL,\n"
                + "    content_type STRING  NOT NULL,\n"
                + "    created_at STRING  NOT NULL"
                + ");";
        db.createTable(query);
    }
    /**
     * select_query method example
     * @author sagitt
     */
    public void selectFromTable() throws SQLException {
        List<String> test_content_id = new ArrayList<String>();
        String table_name = "some_name";
        String query = "select * from "+table_name;
        ResultSet content_info = selectQuery(query,"sqlite");
        while(content_info.next()){
            //вывод найденных в столбце "content_id" значений
            System.out.println(content_info.getString("content_id"));
            //заполнение списка для дальнейшего использования найденными значениями
            test_content_id.add(content_info.getString("content_id"));
        }
    }
    /**
     * insert_query method example
     * @author sagitt
     */
    public void InsertToTable() throws SQLException {
        String table_name = "existed_content";
        String query = "INSERT INTO "+table_name+"(id, content_id, name, content_type, created_at) VALUES (\n"
                + "'some_id', 'some_content_id', 'some_name', 'come_content_type', 'some_created_at')";
        //получение количество строк
        int amount = insertQuery(query,"sqlite");
        //вывод найденных значений в столбце "content_id"
        System.out.println("inserted rows amount is: "+amount);
    }
}
