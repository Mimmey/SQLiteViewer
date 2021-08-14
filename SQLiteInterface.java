package viewer;

import org.sqlite.SQLiteDataSource;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteInterface {

    public static DefaultTableModel getTableModel(String databaseName, String query) {
        String url = "jdbc:sqlite:C:" + databaseName;

        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(url);

        try (Connection connection = sqLiteDataSource.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet resultSet = stmt.executeQuery(query)) {
                    DefaultTableModel model = new DefaultTableModel();
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    String[] columns = new String[resultSetMetaData.getColumnCount()];
                    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                        columns[i - 1] = resultSetMetaData.getColumnLabel(i);
                    }

                    model.setColumnIdentifiers(columns);
                    while (resultSet.next()) {
                        String[] row = new String[resultSetMetaData.getColumnCount()];
                        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                            row[i - 1] = resultSet.getString(i);
                        }

                        model.addRow(row);
                        model.fireTableDataChanged();
                    }

                    return model;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> getTables(String databaseName) {
        String url = "jdbc:sqlite:C:" + databaseName;

        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(url);

        try (Connection connection = sqLiteDataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ALL_TABLES_IN_DATABASE.getQuery())) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    ArrayList<String> list = new ArrayList<>();

                    while (resultSet.next()) {
                        String test = resultSet.getString("name");
                        list.add(test);
                    }
                    if (list.isEmpty()) {
                        return null;
                    }
                    return list;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
