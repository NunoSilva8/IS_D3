package database;

public interface DatabaseTablesInfo {
    // Table names
    String CLIENT_TABLE_NAME = "client";
    String MANAGER_TABLE_NAME = "manager";
    String CURRENCY_TABLE_NAME = "currency";

    // Column names
    String CLIENT_ID = "id";
    String CLIENT_NAME = "name";
    String CLIENT_BALANCE = "balance";
    String CLIENT_MANAGER_ID = "manager_id";
    String CLIENT_TOTAL_PAYMENTS = "payment_total";
    String CLIENT_TOTAL_CREDITS = "credit_total";
    String CLIENT_PAYMENTS_LAST_TWO_MONTHS = "payments_last_two_months";
    String CLIENT_BALANCE_LAST_MONTH = "balance_last_month";

    String MANAGER_ID = "id";
    String MANAGER_NAME = "name";
    String MANAGER_REVENUE = "revenue";

    String CURRENCY_NAME = "name";
    String CURRENCY_TO_EURO = "to_euro";
}
