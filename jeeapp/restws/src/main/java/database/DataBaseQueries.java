package database;

import static database.DatabaseTablesInfo.*;

public interface DataBaseQueries {

    String SELECT_ALL_CLIENTS = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            " FROM " + CLIENT_TABLE_NAME;

    String SELECT_ALL_MANAGERS = "SELECT " + MANAGER_ID +
            ", " + MANAGER_NAME +
            ", " + MANAGER_REVENUE +
            " FROM " + MANAGER_TABLE_NAME;

    String SELECT_ALL_CURRENCIES = "SELECT " + CURRENCY_NAME +
            ", " + CURRENCY_TO_EURO +
            " FROM " + CURRENCY_TABLE_NAME;

    String SELECT_CLIENT_CREDITS_BY_ID = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            ", " + CLIENT_TOTAL_CREDITS +
            " FROM " + CLIENT_TABLE_NAME +
            " WHERE " + CLIENT_ID + " = ?";

    String SELECT_CLIENT_PAYMENTS_BY_ID = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            ", " + CLIENT_TOTAL_PAYMENTS +
            " FROM " + CLIENT_TABLE_NAME +
            " WHERE " + CLIENT_ID + " = ?";

    String SELECT_CLIENT_BALANCE_BY_ID = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            ", " + CLIENT_BALANCE +
            " FROM " + CLIENT_TABLE_NAME +
            " WHERE " + CLIENT_ID + " = ?";

    String SELECT_SUM_CLIENT_CREDITS = "SELECT SUM(" + CLIENT_TOTAL_CREDITS + ") FROM " + CLIENT_TABLE_NAME;

    String SELECT_SUM_CLIENT_PAYMENTS = "SELECT SUM(" + CLIENT_TOTAL_PAYMENTS + ") FROM " + CLIENT_TABLE_NAME;

    String SELECT_SUM_CLIENT_BALANCES = "SELECT SUM(" + CLIENT_BALANCE + ") FROM " + CLIENT_TABLE_NAME;

    String SELECT_CLIENT_LAST_MONTH_BILL = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            ", " + CLIENT_BALANCE_LAST_MONTH +
            " FROM " + CLIENT_TABLE_NAME +
            " WHERE " + CLIENT_ID + " = ?";

    String SELECT_CLIENTS_NO_PAYMENTS_LAST_TWO_MONTHS = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            ", " + CLIENT_BALANCE +
            " FROM " + CLIENT_TABLE_NAME +
            " WHERE " + CLIENT_PAYMENTS_LAST_TWO_MONTHS + " = 0";

    String SELECT_CLIENT_MIN_BALANCE = "SELECT " + CLIENT_ID +
            ", " + CLIENT_NAME +
            ", MIN(" + CLIENT_BALANCE +
            ") FROM " + CLIENT_TABLE_NAME;

    String SELECT_MANAGER_MOST_REVENUE = "SELECT " + MANAGER_ID +
            ", " + MANAGER_NAME +
            ", MAX(" + MANAGER_REVENUE +
            ") FROM " + MANAGER_TABLE_NAME;

}
