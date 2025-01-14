package helper;

import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Account> accounts = new ArrayList<Account>();
    private static AccountManager accountManager;
    private AccountManager(){}
    public static AccountManager getInstance(){
        if (accountManager == null){
            accountManager = new AccountManager();
        }
        return accountManager;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public void addDefaultAccount() throws SQLException {
        String url = "jdbc:mysql://localhost/java_lession";
        Connection conn = DriverManager.getConnection(url,"root","");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from account");
        while(rs.next()){
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int type = rs.getInt("type");
            int lock_status = rs.getInt("lock_status");
            if (lock_status == 1){
                accounts.add(new Account(email,password,type,true));
            }else{
                accounts.add(new Account(email,password,type,false));
            }
        }
        rs.close();
    }
    public void addAccount(Account account){
        accounts.add(account);
    }
}
