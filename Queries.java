package viewer;

public enum Queries {
    SELECT_ALL_TABLES_IN_DATABASE("SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%'"),
    SELECT_FROM("SELECT * FROM ?");

    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
