package project.bfour.debtormaintenance.model;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private String debtorId;
    private long number;
    private String bankName;
    private String branchName;
    private String swiftAddress;
    private String accountCurrency;

    public BankAccount() {
    }

    public BankAccount(String debtorId, long number, String bankName, String branchName, String swiftAddress, String accountCurrency) {
        this.debtorId = debtorId;
        this.number = number;
        this.bankName = bankName;
        this.branchName = branchName;
        this.swiftAddress = swiftAddress;
        this.accountCurrency = accountCurrency;
    }

    public String getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(String debtorId) {
        this.debtorId = debtorId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSwiftAddress() {
        return swiftAddress;
    }

    public void setSwiftAddress(String swiftAddress) {
        this.swiftAddress = swiftAddress;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }
}
