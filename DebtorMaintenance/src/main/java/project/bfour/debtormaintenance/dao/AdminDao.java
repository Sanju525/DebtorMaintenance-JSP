package project.bfour.debtormaintenance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminDao {

    public int update(String txnId){ // Authorize
        int status=0;
        try {
            Connection con=MySqlConn.getCon();
            PreparedStatement ps=con.prepareStatement("update transaction set status=\"A\"," +
                    "information=\"Authorised\" where transactionId=?");
            ps.setString(1, txnId);
            status=ps.executeUpdate();
        } catch(Exception e){
            System.out.println(e);
        }
        return status;

    }

    public int reject(String txnId, String reason){ // Reject
        int status=0;
        try{
            Connection con=MySqlConn.getCon();
            PreparedStatement ps=con.prepareStatement("update transaction set status=\"R\", information=? " +
                    "where transactionId=?");
            ps.setString(1, reason);
            ps.setString(2, txnId);
            status=ps.executeUpdate();
        } catch(Exception e){
            System.out.println(e);
        }
        return status;
    }
}
