package database;

import java.sql.SQLException;

public class DatabaseOperations {

    public static void checkTables() {

        String[] tables = new String[]{"RSS.USERS", "RSS.FEEDLISTS", "RSS.FEEDS", "RSS.ITEMS"};

        for (String s : tables) {
            DatabaseConnector.connect();
            try {
                String sql = "select * from "+s;
                DatabaseConnector.statement = DatabaseConnector.connection.prepareStatement(sql);
                DatabaseConnector.result = DatabaseConnector.statement.executeQuery(sql);

                if (DatabaseConnector.result.next()) {
                    System.out.println("Existe la tabla "+s);
                }
            } catch (Exception e) {
                String errorPK = "java.sql.SQLSyntaxErrorException: ORA-00942: table or view does not exist";
                    if (e.toString().equals(errorPK)) {
                        System.out.println("No Existe la tabla "+s);
                    }
                    e.printStackTrace();
            } finally {
                DatabaseConnector.disconnect();
            }
        }
    }
}

/*
 * SELECT 
 * 
 * 
 * public static void checkTables() {
        DatabaseConnector.connect();
        try {
            String sql = "select * from RSS.USERS";
            DatabaseConnector.statement = DatabaseConnector.connection.prepareStatement(sql);
            DatabaseConnector.result = DatabaseConnector.statement.executeQuery(sql);
            while (DatabaseConnector.result.next()) {
                String str = DatabaseConnector.result.getString(1);
                System.out.println("SELECTED FROM DB: "+str);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        } finally {
            DatabaseConnector.disconnect();
        }
    }
 * 
 * 
 * 
 */
    
    /*

    public static void createTables() {            //for debugging, this will be in a SQL script.
        try {
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createPARENTWEB = "CREATE TABLE PARENTWEBS (WEB_URL VARCHAR2(1000), CONSTRAINT PARENTWEBS_PK PRIMARY KEY (WEB_URL))";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createPARENTWEB);
                    DatabaseConnector.disconnect();
                    System.out.println("Table PARENTWEBS CREATED.");
                } catch (SQLException e) {
                    //e.printStackTrace();
                }
            }
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINKS = "CREATE TABLE LINKS (LINK_URL VARCHAR2(1000), PARENT_WEB VARCHAR2(1000), CONSTRAINT LINKS_PK PRIMARY KEY (LINK_URL), CONSTRAINT LINKS_PARENT_WEB_FK FOREIGN KEY (PARENT_WEB) REFERENCES PARENTWEBS (WEB_URL))";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINKS);
                    DatabaseConnector.disconnect();
                    System.out.println("Table LINKS CREATED.");
                } catch (SQLException e) {
                    //e.printStackTrace();
                }
            }
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINKS = "CREATE TABLE QUEUE (QUEUE_NUMBER NUMBER , WEB_URL VARCHAR2(1000), PRIORITY NUMBER , CONSTRAINT QUEUE_PK PRIMARY KEY (QUEUE_NUMBER))";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINKS);
                    DatabaseConnector.disconnect();
                    System.out.println("Table QUEUE CREATED.");
                } catch (SQLException e) {
                    //e.printStackTrace();
                }
            }
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINKS = "CREATE TABLE SCANNED_LINKS (WEB_URL VARCHAR2(1000), CONSTRAINT SCANNED_LINKS_PK PRIMARY KEY (WEB_URL))";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINKS);
                    DatabaseConnector.disconnect();
                    System.out.println("Table SCANNED_LINKS CREATED.");
                } catch (SQLException e) {
                    //e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    ////////////////////////////////////////////// INSERTS //////////////////////////////////////
    
    
    public static void insertWEB(String parentWeb) {
        try {
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createPARENTWEB = "INSERT INTO PARENTWEBS VALUES (\'" + parentWeb + "\')";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createPARENTWEB);
                    DatabaseConnector.disconnect();
                } catch (SQLException e) {
                    //System.out.println("Padre ya existente.");
                    //e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertLINK(String link, String parentWeb) {
        try {
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINK = "INSERT INTO LINKS VALUES (\'" + link + "\', \'" + parentWeb + "\')";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINK);
                    DatabaseConnector.disconnect();
                    System.out.println("LINK insertado: " + link);
                } catch (SQLException e) {
                    //System.out.println("LINK NO insertado.");
                    String errorPK = "java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FINDME.LINKS_PK) violated";
                    if (e.equals(errorPK)) {
                        System.out.println("Ya existe Link.");
                    }
                    //e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insertLinkToScannedLinks(String link) {
        try {
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINK = "INSERT INTO SCANNED_LINKS VALUES (\'" + link + ")";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINK);
                    DatabaseConnector.disconnect();
                    System.out.println("LINK insertado: " + link);
                } catch (SQLException e) {
                    //System.out.println("LINK NO insertado.");
                    String errorPK = "java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FINDME.LINKS_PK) violated";
                    if (e.equals(errorPK)) {
                        System.out.println("Ya existe Link.");
                    }
                    //e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insertLinkToQueue(String link) {
        int priority = 100;
        
        if(linkAlreadyScanned(link))
            priority = 0;
  
        try {
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINK = "INSERT INTO QUEUE VALUES (QUEUE_SEQ.NEXTVAL, \'" + link + "\', " + priority +")";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINK);
                    DatabaseConnector.disconnect();
                    System.out.println("LINK INSERTED ON QUEUE: " + link +" WITH PRIORITY "+priority);
                } catch (SQLException e) {
                    //System.out.println("LINK NO insertado.");
                    String errorPK = "java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FINDME.LINKS_PK) violated";
                    if (e.equals(errorPK)) {
                        System.out.println("Ya existe Link.");
                    }
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    ////////////////////////////////////////////// GETTERS //////////////////////////////////////
    public static ArrayList getLinks() {

        ArrayList<String> links = new ArrayList();

        try {
            //String sql = "select * from parentwebs";
            String sql = "select * from links";
            DatabaseConnector.connect();
            PreparedStatement statement = DatabaseConnector.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String parentUrl = result.getString(1);
                links.add(parentUrl);
                //System.out.println("SELECTED FROM DB: "+parentUrl);
            }
            DatabaseConnector.disconnect();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }

        return links;

    }
    
        public static ArrayList getLinksFromQueue() {

        ArrayList<String> links = new ArrayList();

        try {
            //String sql = "select * from parentwebs";
            String sql = "SELECT * FROM QUEUE ORDER BY PRIORITY DESC";
            DatabaseConnector.connect();
            PreparedStatement statement = DatabaseConnector.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String link_url = result.getString(2);
                links.add(link_url);
            }
            DatabaseConnector.disconnect();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }

        return links;

    }
    
    public static boolean linkAlreadyScanned(String link) {

        ArrayList<String> scanned_links = new ArrayList();

        try {
            String sql = "SELECT * FROM SCANNED_LINKS";
            DatabaseConnector.connect();
            PreparedStatement statement = DatabaseConnector.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String parentUrl = result.getString(1);
                scanned_links.add(parentUrl);
            }
            
            for (int i = 0; i < scanned_links.size(); i++) {
                if (scanned_links.get(i).equals(link))
                    return true;
            }
            
            DatabaseConnector.disconnect();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
        return false;
    }
    
    
        ////////////////////////////////////////////// DELETE //////////////////////////////////////

    
      public static void deleteLinkFromQueue(String link) {
        try {
            DatabaseConnector.connect();
            if (DatabaseConnector.connection != null) {
                try {
                    String createLINK = "DELETE QUEUE WHERE WEB_URL=\'"+link+"\')";
                    DatabaseConnector.statement = DatabaseConnector.connection.createStatement();
                    DatabaseConnector.statement.execute(createLINK);
                    DatabaseConnector.disconnect();
                    System.out.println("LINK DELETED FROM QUEUE: " + link);
                } catch (SQLException e) {
                    //System.out.println("LINK NO insertado.");
                    String errorPK = "java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique constraint (FINDME.LINKS_PK) violated";
                    if (e.equals(errorPK)) {
                        System.out.println("Ya existe Link.");
                    }
                    //e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/