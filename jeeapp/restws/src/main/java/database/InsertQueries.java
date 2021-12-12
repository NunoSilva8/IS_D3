package database;


import static database.DatabaseTablesInfo.*;

public interface InsertQueries {
    String INSERT_CLIENT = "INSERT INTO " + CLIENT_TABLE_NAME +
            " (" +  CLIENT_NAME + ", " + CLIENT_BALANCE + ", " + CLIENT_MANAGER_ID + ", " + CLIENT_TOTAL_PAYMENTS +
            ", " + CLIENT_TOTAL_CREDITS + ", " + CLIENT_PAYMENTS_LAST_TWO_MONTHS + ", " + CLIENT_BALANCE_LAST_MONTH + ")" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    String INSERT_MANAGER = "INSERT INTO " + MANAGER_TABLE_NAME +
            " (" + MANAGER_NAME + ", " + MANAGER_REVENUE + ")" +
            " VALUES (?, 0)";

    String INSERT_CURRENCY = "INSERT INTO " + CURRENCY_TABLE_NAME +
            " (" + CURRENCY_NAME + ", " + CURRENCY_TO_EURO + ")" +
            " VALUES (?, ?)";

}
