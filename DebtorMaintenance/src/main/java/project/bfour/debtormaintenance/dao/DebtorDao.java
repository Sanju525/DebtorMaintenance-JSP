package project.bfour.debtormaintenance.dao;



import project.bfour.debtormaintenance.model.BankAccount;
import project.bfour.debtormaintenance.model.DebtorForm;
import project.bfour.debtormaintenance.model.DetailsForm;
import project.bfour.debtormaintenance.model.Transaction;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DebtorDao {

    public static boolean insertForm(Connection connection, DebtorForm debtorForm, BankAccount bankAccount, Transaction transaction) throws SQLException {
        String df_stmt = "insert into debtorform " +
                        " values(?,?,?,?,?,?,?,?)";
        String ba_stmt = "insert into bankform  values (?,?,?,?,?,?,?)";
        String txn_stmt = "insert into transaction values(?,?,?,?,?)";

        int debtorFormId = getNewDebtorFormId(connection);
        int bankFormId = getNewBankFormId(connection);

        PreparedStatement debtorPreparedStatement = connection.prepareStatement(df_stmt);
        debtorPreparedStatement.setInt(1, debtorFormId);
        debtorPreparedStatement.setString(2, debtorForm.getId());
        debtorPreparedStatement.setString(3, debtorForm.getName());
        debtorPreparedStatement.setString(4, debtorForm.getAddress1());
        debtorPreparedStatement.setString(5, debtorForm.getAddress2());
        debtorPreparedStatement.setLong(6, debtorForm.getFax());
        debtorPreparedStatement.setLong(7, debtorForm.getPhone());
        debtorPreparedStatement.setString(8, debtorForm.getEmail());
        int dStatus = debtorPreparedStatement.executeUpdate(); // save

        PreparedStatement bankPreparedStatement = connection.prepareStatement(ba_stmt);
        bankPreparedStatement.setInt(1, bankFormId);
        bankPreparedStatement.setInt(2, debtorFormId); // debtorFormId
        bankPreparedStatement.setLong(3, bankAccount.getNumber());
        bankPreparedStatement.setString(4, bankAccount.getBankName());
        bankPreparedStatement.setString(5, bankAccount.getBranchName());
        bankPreparedStatement.setString(6, bankAccount.getSwiftAddress());
        bankPreparedStatement.setString(7, bankAccount.getAccountCurrency());
        int bStatus = bankPreparedStatement.executeUpdate(); // save

        PreparedStatement txnStatement = connection.prepareStatement(txn_stmt);
        txnStatement.setString(1, transaction.getId());
        txnStatement.setInt(2, bankFormId); // bankFormId
        txnStatement.setString(3, transaction.getDateTime());
        txnStatement.setString(4, transaction.getStatus());
        txnStatement.setString(5, transaction.getInformation());
        int txnStatus = txnStatement.executeUpdate(); // error

        return dStatus == 1 && bStatus == 1 && txnStatus == 1;
    }

    public static Transaction getNewTxn(Connection connection, String debtorId) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); // sql formatting
        Transaction transaction = new Transaction(); // init
        transaction.setDebtorId(debtorId); // debtor
        transaction.setId(getNewTxnId(connection)); // new txn id
        transaction.setDateTime(formatter.format(LocalDateTime.now())); // date time
        transaction.setStatus("P"); // default
        transaction.setInformation("Pending"); // default
        return transaction;
    }

//    For admin to view all debtor details
    public static List<DetailsForm> getDebtorDetails(Connection connection) throws SQLException {
        String query = "select user.uid\n" +
                ", df.name, df.address1, df.address2, df.email, df.fax, df.phone\n" +
                ", bf.accountNumber, bf.bankName, bf.branchName, bf.swiftAddress, bf.accountCurrency\n" +
                ", tx.transactionId, tx.date_time, tx.status, tx.information from user\n" +
                "\n" +
                "inner join debtorform as df on df.debtorId=user.uid\n" +
                "\n" +
                "inner join bankform as bf on  bf.debtorFormId=df.debtorFormId\n" +
                "\n" +
                "inner join transaction as tx on tx.bankFormId=bf.bankFormId where tx.status=\"P\" " +
                "order by tx.date_time desc;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<DetailsForm> detailsForms = new ArrayList<>();
        while (resultSet.next()) {
            detailsForms.add(new DetailsForm(
                    resultSet.getString("uid"),
                    resultSet.getString("name"),
                    resultSet.getString("address1"),
                    resultSet.getString("address2"),
                    resultSet.getLong("fax"),
                    resultSet.getLong("phone"),
                    resultSet.getString("email"),
                    resultSet.getLong("accountNumber"),
                    resultSet.getString("bankName"),
                    resultSet.getString("branchName"),
                    resultSet.getString("swiftAddress"),
                    resultSet.getString("accountCurrency"),
                    resultSet.getString("transactionId"),
                    resultSet.getString("date_time"),
                    resultSet.getString("status"),
                    resultSet.getString("information")
            ));
        }
        return detailsForms;
    }

//    For Debtor to view
    public static List<DetailsForm> getDebtorDetails(Connection connection, String uid) throws SQLException {
        String query = "select user.uid\n" +
                ", df.name, df.address1, df.address2, df.email, df.fax, df.phone\n" +
                ", bf.accountNumber, bf.bankName, bf.branchName, bf.swiftAddress, bf.accountCurrency\n" +
                ", tx.transactionId, tx.date_time, tx.status, tx.information from user\n" +
                "\n" +
                "inner join debtorform as df on df.debtorId=user.uid\n" +
                "\n" +
                "inner join bankform as bf on  bf.debtorFormId=df.debtorFormId\n" +
                "\n" +
                "inner join transaction as tx on tx.bankFormId=bf.bankFormId where uid=? order by tx.date_time desc;";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uid);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<DetailsForm> detailsForms = new ArrayList<>();
        while (resultSet.next()) {
            detailsForms.add(new DetailsForm(
                    resultSet.getString("uid"),
                    resultSet.getString("name"),
                    resultSet.getString("address1"),
                    resultSet.getString("address2"),
                    resultSet.getLong("fax"),
                    resultSet.getLong("phone"),
                    resultSet.getString("email"),
                    resultSet.getLong("accountNumber"),
                    resultSet.getString("bankName"),
                    resultSet.getString("branchName"),
                    resultSet.getString("swiftAddress"),
                    resultSet.getString("accountCurrency"),
                    resultSet.getString("transactionId"),
                    resultSet.getString("date_time"),
                    resultSet.getString("status"),
                    resultSet.getString("information")
            ));
        }
        return detailsForms;
    }

//    for admin more Details view
    public static DetailsForm getDebtorDetailsByTxnId(Connection connection, String txnId) throws SQLException {
        String txnQuery = "select * from transaction where transactionId=?";
        PreparedStatement txnStatement = connection.prepareStatement(txnQuery);
        txnStatement.setString(1, txnId);
        ResultSet txnSet = txnStatement.executeQuery();
        txnSet.next();
        int bankFormId = txnSet.getInt("bankFormId");

        String bankQuery = "select * from bankform where bankFormId=?";
        PreparedStatement bankStatement = connection.prepareStatement(bankQuery);
        bankStatement.setInt(1, bankFormId);
        ResultSet bnkSet = bankStatement.executeQuery();
        bnkSet.next();
        int debtorFormId = bnkSet.getInt("debtorFormId");


        String debQuery = "select * from debtorform where debtorFormId=?";
        PreparedStatement debStatement = connection.prepareStatement(debQuery);
        debStatement.setInt(1, debtorFormId);
        ResultSet debSet = debStatement.executeQuery();
        debSet.next();

        return new DetailsForm(
                debSet.getString("debtorId"),
                debSet.getString("name"),
                debSet.getString("address1"),
                debSet.getString("address2"),
                debSet.getLong("fax"),
                debSet.getLong("phone"),
                debSet.getString("email"),
                bnkSet.getLong("accountNumber"),
                bnkSet.getString("bankName"),
                bnkSet.getString("branchName"),
                bnkSet.getString("swiftAddress"),
                bnkSet.getString("accountCurrency"),
                txnSet.getString("transactionId"),
                txnSet.getString("date_time"),
                txnSet.getString("Status"),
                txnSet.getString("information")
        );
    }

//    Private Methods
    private static String getNewTxnId(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String checkQuery = "select count(*) as count from transaction";
        resultSet = statement.executeQuery(checkQuery);
        resultSet.next();
        if (resultSet.getInt("count") == 0) {
            return "txn-100";
        }
        String query = "select max(transactionId) as max from transaction";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.next();
        String tid = resultSet.getString("max");
        tid = tid.replace("txn-", "");
        int id = Integer.parseInt(tid);
        return "txn-" + (++id);
    }

    private static int getNewDebtorFormId(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String checkQuery = "select count(*) as count from debtorform";
        resultSet = statement.executeQuery(checkQuery);
        resultSet.next();
        if (resultSet.getInt("count") == 0) {
            return 1;
        }
        String query = "select max(debtorFormId) as max from debtorform";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.next();
        int id = resultSet.getInt("max");
        return (++id);
    }

    private static int getNewBankFormId(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String checkQuery = "select count(*) as count from bankform";
        resultSet = statement.executeQuery(checkQuery);
        resultSet.next();
        if (resultSet.getInt("count") == 0) {
            return 1;
        }
        String query = "select max(bankFormId) as max from bankform";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.next();
        int id = resultSet.getInt("max");
        return (++id);
    }

}