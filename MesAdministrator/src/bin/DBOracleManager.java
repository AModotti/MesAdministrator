package bin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public class DBOracleManager {
	
	static Vector<Object> transactionrows = new Vector<Object>();
	static Vector<Object> deptrows = new Vector<Object>();
	static Vector<Object> islandrows = new Vector<Object>();
	static Vector<Object> linksrows = new Vector<Object>();
	static Vector<Object> clientupdateinforows = new Vector<Object>();
	static HashMap<Integer, String> importrows = new HashMap<>();
	static Vector<Object> historyimportrows = new Vector<Object>();
	static int transactionrecords = 0;
	static int deptrecords = 0;
	static int islandrecords = 0;
	static int linksrecords = 0;
	static int historyimportrecords = 0;
	static int clientupdateinforecords = 0;
	
	public static Vector<Object> getTransaction(String filters) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql; 
        
        sql = "SELECT ADUKID,"
            + "       ADDEPT,"
            + "       TRIM(ADISL),"
            + "       TO_CHAR((CAST((FROM_TZ(CAST(TO_DATE(TO_CHAR(ADDTUP,'DD/MM/YYYY HH24.MI.SS'),'DD/MM/YYYY HH24.MI.SS') AS TIMESTAMP),'GMT') AT TIME ZONE 'Europe/Rome') AS DATE)),'DD/MM/YYYY HH24.MI.SS'),"
            + "       ADYQ10DECD,"
            + "       ADYQ10ACCD,"
            + "       TRIM(ADMMCU),"
            + "       ADAN8,"
            + "       TRIM(ADALPH),"
            + "       ADURAB,"
            + "       TRIM(ADALPH1),"
            + "       ADDOCO,"
            + "       ADOPSQ,"
            + "       TRIM(ADOPSQDSC1),"
            + "       TRIM(ADMCU),"
            + "       ADPRJM,"
            + "       TRIM(ADLITM),"
            + "       TRIM(ADDSC1),"
            + "       TRIM(ADUSER),"
            + "       ADYQ10PR01,"
            + "       TRIM(ADINSERTUSER),"
            + "       TO_CHAR(ADINSERTDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(ADUPDATEUSER),"
            + "       TO_CHAR(ADUPDATEDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(ADDELETEUSER),"
            + "       TO_CHAR(ADDELETEDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(ADPROCESSEDUSER),"
            + "       TO_CHAR(ADPROCESSEDDATE,'DD/MM/YYYY HH24.MI.SS') "
            + "  FROM F55FQ10001 "
            + " WHERE 0 = 0 "
            +         filters
            + " ORDER BY 8,4 DESC,1 DESC";
	        
	    try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
		
	        transactionrows.removeAllElements();
	        
	        while (rs.next()) {
	        	Vector<Object> newRow = new Vector<Object>();
	            
	        	newRow.addElement(false);
	
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if(rs.getObject(i) == null){
	            		newRow.addElement("");
	            	}else{
	            		newRow.addElement(rs.getObject(i));
            		}
	            }
	
	            transactionrows.addElement(newRow);
	        }
	        
	        return transactionrows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getTransaction() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getTransaction() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static long insertTransaction(Vector<Object> transaction) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;
        long id = 0;
        
        sql = "INSERT INTO F55FQ10001"
        	+ "       (ADUKID,"
        	+ "        ADMMCU,"
        	+ "        ADAN8,"
        	+ "        ADMCU,"
        	+ "        ADYQ10DECD,"
        	+ "        ADYQ10ACCD,"
        	+ "        ADDOCO,"
        	+ "        ADOPSQ,"
        	+ "        ADSOQS,"
        	+ "        ADSOCN,"
        	+ "        ADREAC,"
        	+ "        ADYQ10DERN,"
        	+ "        ADDTUP,"
        	+ "        ADYQ10TRCD,"
        	+ "        ADYQ10REST,"
        	+ "        ADYQ10PR01,"
        	+ "        ADYQ10PR02,"
        	+ "        ADURDT,"
        	+ "        ADURAB,"
        	+ "        ADURAT,"
        	+ "        ADURCD,"
        	+ "        ADURRF,"
        	+ "        ADPID,"
        	+ "        ADJOBN,"
        	+ "        ADUSER,"
        	+ "        ADUPMJ,"
        	+ "        ADUPMT,"
        	+ "        ADSOS,"
        	+ "        ADALPH,"
        	+ "        ADALPH1,"
        	+ "        ADDEPT,"
        	+ "        ADPRJM,"
        	+ "        ADLITM,"
        	+ "        ADDSC1,"
        	+ "        ADOPSQDSC1,"
        	+ "        ADISL,"
        	+ "        ADPROCESSEDUSER,"
        	+ "        ADPROCESSEDDATE,"
        	+ "        ADINSERTUSER,"
        	+ "        ADINSERTDATE,"
        	+ "        ADUPDATEUSER,"
        	+ "        ADUPDATEDATE,"
        	+ "        ADDELETEUSER,"
        	+ "        ADDELETEDATE) "
        	+ "VALUES (F55FQ10001SEQ.NEXTVAL,"
        	+ "   	   ?,"																																										//ADMMCU
        	+ "        ?,"																																										//ADAN8
        	+ "        ?,"																																										//ADMCU
        	+ "        ?,"																																										//ADYQ10DECD
        	+ "        ?,"																																										//ADYQ10ACCD
        	+ "        ?,"																																										//ADDOCO
        	+ "        ?,"																																										//ADOPSQ
        	+ "        ?,"																																										//ADSOQS
        	+ "        ?,"																																										//ADSOCN
        	+ "        ?,"																																										//ADREAC
        	+ "        ?,"																																										//ADYQ10DERN
        	+ "        TO_DATE(TO_CHAR((CAST((FROM_TZ(TO_TIMESTAMP(?,'DD/MM/YYYY HH24.MI.SS'),'Europe/Rome') AT TIME ZONE 'GMT') AS DATE)),'DD/MM/YYYY HH24.MI.SS'),'DD/MM/YYYY HH24.MI.SS'),"	//ADDTUP
        	+ "        ?,"																																										//ADYQ10TRCD
        	+ "        ?,"																																										//ADYQ10REST
        	+ "        ?,"																																										//ADYQ10PR01
        	+ "        ?,"																																										//ADYQ10PR02
        	+ "        ?,"																																										//ADURDT
        	+ "        ?,"																																										//ADURAB
        	+ "        ?,"																																										//ADURAT
        	+ "        ?,"																																										//ADURCD
        	+ "        ?,"																																										//ADURRF
        	+ "        ?,"																																										//ADPID
        	+ "        ?,"																																										//ADJOBN																																											
        	+ "        ?,"																																										//ADUSER																																											
        	+ "        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"																																//ADUPMJ
        	+ "        ?,"																																										//ADUPMT
        	+ "        ?,"																																										//ADSOS
        	+ "        ?,"																																										//ADALPH
        	+ "		   ?,"																																										//ADALPH1
        	+ "        ?,"																																										//ADDEPT
        	+ "        ?,"																																										//ADPRJM
        	+ "        ?,"																																										//ADLITM
        	+ "        ?,"																																										//ADDSC1
        	+ "        ?,"																																										//ADOPSQDSC1
        	+ "        ?,"																																										//ADISL
        	+ "        ?,"																																										//ADPROCESSEDUSER
        	+ "        ?,"																																										//ADPROCESSEDDATE
        	+ "        ?,"																																										//ADINSERTUSER
        	+ "        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"																																		//ADINSERTDATE
        	+ "        ?,"																																										//ADUPDATEUSER
        	+ "        ?,"																																										//ADUPDATEDATE
        	+ "        ?,"																																										//ADDELETEUSER
        	+ "        ?)";																																										//ADDELETEDATE
        
	    try {
	    	
	    	conn = Settings.getDbConnection();
	    	String generatedColumns[] = {"ADUKID"};
            ps = conn.prepareStatement(sql,generatedColumns);
        	
			ps.setString(1, String.format("%12s", transaction.get(0).toString()));								//ADMMCU
			ps.setString(2, transaction.get(1).toString());														//ADAN8
			ps.setString(3, String.format("%12s",transaction.get(2).toString()));								//ADMCU
			ps.setString(4, transaction.get(3).toString());														//ADYQ10DECD
			ps.setString(5, transaction.get(4).toString());														//ADYQ10ACCD
			ps.setString(6, transaction.get(5).toString());														//ADDOCO
			ps.setString(7, transaction.get(6).toString());														//ADOPSQ
			ps.setString(8, transaction.get(7).toString());														//ADSOQS
			ps.setString(9, transaction.get(8).toString());														//ADSOCN
			ps.setString(10, transaction.get(9).toString());													//ADREAC
			ps.setString(11, transaction.get(10).toString());													//ADYQ10DERN
			ps.setString(12, transaction.get(11).toString());													//ADDTUP
			ps.setString(13, transaction.get(12).toString());													//ADYQ10TRCD
			ps.setString(14, transaction.get(13).toString());													//ADYQ10REST
			ps.setString(15, transaction.get(14).toString());													//ADYQ10PR01
			ps.setString(16, transaction.get(15).toString());													//ADYQ10PR02
			ps.setString(17, transaction.get(16).toString());													//ADURDT
			ps.setString(18, transaction.get(17).toString());													//ADURAB
			ps.setString(19, transaction.get(18).toString());													//ADURAT
			ps.setString(20, transaction.get(19).toString());													//ADURCD
			ps.setString(21, transaction.get(20).toString());													//ADURRF
			ps.setString(22, transaction.get(21).toString());													//ADPID
			ps.setString(23, transaction.get(22).toString());													//ADJOBN
			ps.setString(24, transaction.get(23).toString());													//ADUSER
			ps.setString(25, transaction.get(24).toString());													//ADUPMJ
			ps.setString(26, transaction.get(25).toString());													//ADUPMT
			ps.setString(27, transaction.get(26).toString());													//ADSOS
			ps.setString(28, transaction.get(27).toString());													//ADALPH
			ps.setString(29, transaction.get(28).toString());													//ADALPH1
			ps.setString(30, transaction.get(29).toString());													//ADDEPT
			ps.setString(31, transaction.get(30).toString());													//ADPRJM
			ps.setString(32, transaction.get(31).toString());													//ADLITM
			ps.setString(33, transaction.get(32).toString());													//ADDSC1
			ps.setString(34, transaction.get(33).toString());													//ADOPSQDSC1
			ps.setString(35, transaction.get(34).toString());													//ADISL
			ps.setString(36, transaction.get(35).toString());													//ADPROCESSEDUSER
			ps.setNull(37, (int) transaction.get(36));															//ADPROCESSEDDATE
			ps.setString(38, transaction.get(37).toString());													//ADINSERTUSER
			ps.setString(39, transaction.get(38).toString());													//ADINSERTDATE
			ps.setString(40, transaction.get(39).toString());													//ADUPDATEUSER
			ps.setNull(41, (int) transaction.get(40));															//ADUPDATEDATE
			ps.setString(42, transaction.get(41).toString());													//ADDELETEUSER
			ps.setNull(43, (int) transaction.get(42));															//ADDELETEDATE
			
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
                       
            if(rs.next()){
            	id = rs.getLong(1);
            }
            
            return id;
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertTransaction() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertTransaction() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static void updateTransaction(Vector<Object> transaction) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql; 
        
        sql = "UPDATE F55FQ10001"
        	+ "   SET ADMMCU = ?,"
        	+ "       ADAN8 = ?,"	
        	+ "       ADMCU = ?,"
        	+ "       ADYQ10DECD = ?,"
        	+ "       ADYQ10ACCD = ?,"
        	+ "       ADDOCO = ?,"
        	+ "       ADOPSQ = ?,"
        	+ "       ADDTUP = TO_DATE(TO_CHAR((CAST((FROM_TZ(TO_TIMESTAMP(?,'DD/MM/YYYY HH24.MI.SS'),'Europe/Rome') AT TIME ZONE 'GMT') AS DATE)),'DD/MM/YYYY HH24.MI.SS'),'DD/MM/YYYY HH24.MI.SS'),"
        	+ "       ADURAB = ?,"
        	+ "       ADALPH = ?,"
        	+ "       ADALPH1 = ?,"
        	+ "       ADDEPT = ?,"
        	+ "       ADPRJM = ?,"
        	+ "       ADLITM = ?,"
        	+ "       ADDSC1 = ?,"
        	+ "       ADOPSQDSC1 = ?,"
        	+ "       ADISL = ?,"
        	+ "       ADUSER = ?,"
        	+ "       ADUPDATEUSER = ?,"
        	+ "       ADUPDATEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
        	+ " WHERE ADUKID = ?";
	        
	    try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
			ps.setString(1, String.format("%12s", transaction.get(0).toString()));
			ps.setString(2, transaction.get(1).toString());
			ps.setString(3, String.format("%12s",transaction.get(2).toString()));
			ps.setString(4, transaction.get(3).toString());
			ps.setString(5, transaction.get(4).toString());
			ps.setString(6, transaction.get(5).toString());
			ps.setString(7, transaction.get(6).toString());
			ps.setString(8, transaction.get(7).toString());
			ps.setString(9, transaction.get(8).toString());
			ps.setString(10, transaction.get(9).toString());
			ps.setString(11, transaction.get(10).toString());
			ps.setString(12, transaction.get(11).toString());
			ps.setString(13, transaction.get(12).toString());
			ps.setString(14, transaction.get(13).toString());
			ps.setString(15, transaction.get(14).toString());
			ps.setString(16, transaction.get(15).toString());
			ps.setString(17, transaction.get(16).toString());
			ps.setString(18, transaction.get(17).toString());
			ps.setString(19, transaction.get(18).toString());
			ps.setString(20, transaction.get(19).toString());
			ps.setString(21, transaction.get(20).toString());
			
            ps.executeUpdate();
	        
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateTransaction() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateTransaction() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static long copyTransaction(Vector<Object> transaction) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;
        long id = 0;
        
        sql = "INSERT INTO F55FQ10001"
        	+ "       (ADUKID,"
        	+ "        ADMMCU,"
        	+ "        ADAN8,"
        	+ "        ADMCU,"
        	+ "        ADYQ10DECD,"
        	+ "        ADYQ10ACCD,"
        	+ "        ADDOCO,"
        	+ "        ADOPSQ,"
        	+ "        ADSOQS,"
        	+ "        ADSOCN,"
        	+ "        ADREAC,"
        	+ "        ADYQ10DERN,"
        	+ "        ADDTUP,"
        	+ "        ADYQ10TRCD,"
        	+ "        ADYQ10REST,"
        	+ "        ADYQ10PR01,"
        	+ "        ADYQ10PR02,"
        	+ "        ADURDT,"
        	+ "        ADURAB,"
        	+ "        ADURAT,"
        	+ "        ADURCD,"
        	+ "        ADURRF,"
        	+ "        ADPID,"
        	+ "        ADJOBN,"
        	+ "        ADUSER,"
        	+ "        ADUPMJ,"
        	+ "        ADUPMT,"
        	+ "        ADSOS,"
        	+ "        ADALPH,"
        	+ "        ADALPH1,"
        	+ "        ADDEPT,"
        	+ "        ADPRJM,"
        	+ "        ADLITM,"
        	+ "        ADDSC1,"
        	+ "        ADOPSQDSC1,"
        	+ "        ADISL,"
        	+ "        ADPROCESSEDUSER,"
        	+ "        ADPROCESSEDDATE,"
        	+ "        ADINSERTUSER,"
        	+ "        ADINSERTDATE,"
        	+ "        ADUPDATEUSER,"
        	+ "        ADUPDATEDATE,"
        	+ "        ADDELETEUSER,"
        	+ "        ADDELETEDATE) "
        	+ "VALUES (F55FQ10001SEQ.NEXTVAL,"
        	+ "   	   ?,"																																										//ADMMCU
        	+ "        ?,"																																										//ADAN8
        	+ "        ?,"																																										//ADMCU
        	+ "        ?,"																																										//ADYQ10DECD
        	+ "        ?,"																																										//ADYQ10ACCD
        	+ "        ?,"																																										//ADDOCO
        	+ "        ?,"																																										//ADOPSQ
        	+ "        ?,"																																										//ADSOQS
        	+ "        ?,"																																										//ADSOCN
        	+ "        ?,"																																										//ADREAC
        	+ "        ?,"																																										//ADYQ10DERN
        	+ "        TO_DATE(TO_CHAR((CAST((FROM_TZ(TO_TIMESTAMP(?,'DD/MM/YYYY HH24.MI.SS'),'Europe/Rome') AT TIME ZONE 'GMT') AS DATE)),'DD/MM/YYYY HH24.MI.SS'),'DD/MM/YYYY HH24.MI.SS'),"	//ADDTUP
        	+ "        ?,"																																										//ADYQ10TRCD
        	+ "        ?,"																																										//ADYQ10REST
        	+ "        ?,"																																										//ADYQ10PR01
        	+ "        ?,"																																										//ADYQ10PR02
        	+ "        ?,"																																										//ADURDT
        	+ "        ?,"																																										//ADURAB
        	+ "        ?,"																																										//ADURAT
        	+ "        ?,"																																										//ADURCD
        	+ "        ?,"																																										//ADURRF
        	+ "        ?,"																																										//ADPID
        	+ "        ?,"																																										//ADJOBN																																											
        	+ "        ?,"																																										//ADUSER																																											
        	+ "        DATE_TO_JULJDE(TO_DATE(?,'DD/MM/YYYY')),"																																//ADUPMJ
        	+ "        ?,"																																										//ADUPMT
        	+ "        ?,"																																										//ADSOS
        	+ "        ?,"																																										//ADALPH
        	+ "		   ?,"																																										//ADALPH1
        	+ "        ?,"																																										//ADDEPT
        	+ "        ?,"																																										//ADPRJM
        	+ "        ?,"																																										//ADLITM
        	+ "        ?,"																																										//ADDSC1
        	+ "        ?,"																																										//ADOPSQDSC1
        	+ "        ?,"																																										//ADISL
        	+ "        ?,"																																										//ADPROCESSEDUSER
        	+ "        ?,"																																										//ADPROCESSEDDATE
        	+ "        ?,"																																										//ADINSERTUSER
        	+ "        TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"																																		//ADINSERTDATE
        	+ "        ?,"																																										//ADUPDATEUSER
        	+ "        ?,"																																										//ADUPDATEDATE
        	+ "        ?,"																																										//ADDELETEUSER
        	+ "        ?)";																																										//ADDELETEDATE
        
	    try {
	    	
	    	conn = Settings.getDbConnection();
	    	String generatedColumns[] = {"ADUKID"};
            ps = conn.prepareStatement(sql,generatedColumns);
        	
			ps.setString(1, String.format("%12s", transaction.get(0).toString()));								//ADMMCU
			ps.setString(2, transaction.get(1).toString());														//ADAN8
			ps.setString(3, String.format("%12s",transaction.get(2).toString()));								//ADMCU
			ps.setString(4, transaction.get(3).toString());														//ADYQ10DECD
			ps.setString(5, transaction.get(4).toString());														//ADYQ10ACCD
			ps.setString(6, transaction.get(5).toString());														//ADDOCO
			ps.setString(7, transaction.get(6).toString());														//ADOPSQ
			ps.setString(8, transaction.get(7).toString());														//ADSOQS
			ps.setString(9, transaction.get(8).toString());														//ADSOCN
			ps.setString(10, transaction.get(9).toString());													//ADREAC
			ps.setString(11, transaction.get(10).toString());													//ADYQ10DERN
			ps.setString(12, transaction.get(11).toString());													//ADDTUP
			ps.setString(13, transaction.get(12).toString());													//ADYQ10TRCD
			ps.setString(14, transaction.get(13).toString());													//ADYQ10REST
			ps.setString(15, transaction.get(14).toString());													//ADYQ10PR01
			ps.setString(16, transaction.get(15).toString());													//ADYQ10PR02
			ps.setString(17, transaction.get(16).toString());													//ADURDT
			ps.setString(18, transaction.get(17).toString());													//ADURAB
			ps.setString(19, transaction.get(18).toString());													//ADURAT
			ps.setString(20, transaction.get(19).toString());													//ADURCD
			ps.setString(21, transaction.get(20).toString());													//ADURRF
			ps.setString(22, transaction.get(21).toString());													//ADPID
			ps.setString(23, transaction.get(22).toString());													//ADJOBN
			ps.setString(24, transaction.get(23).toString());													//ADUSER
			ps.setString(25, transaction.get(24).toString());													//ADUPMJ
			ps.setString(26, transaction.get(25).toString());													//ADUPMT
			ps.setString(27, transaction.get(26).toString());													//ADSOS
			ps.setString(28, transaction.get(27).toString());													//ADALPH
			ps.setString(29, transaction.get(28).toString());													//ADALPH1
			ps.setString(30, transaction.get(29).toString());													//ADDEPT
			ps.setString(31, transaction.get(30).toString());													//ADPRJM
			ps.setString(32, transaction.get(31).toString());													//ADLITM
			ps.setString(33, transaction.get(32).toString());													//ADDSC1
			ps.setString(34, transaction.get(33).toString());													//ADOPSQDSC1
			ps.setString(35, transaction.get(34).toString());													//ADISL
			ps.setString(36, transaction.get(35).toString());													//ADPROCESSEDUSER
			ps.setNull(37, (int) transaction.get(36));															//ADPROCESSEDDATE
			ps.setString(38, transaction.get(37).toString());													//ADINSERTUSER
			ps.setString(39, transaction.get(38).toString());													//ADINSERTDATE
			ps.setString(40, transaction.get(39).toString());													//ADUPDATEUSER
			ps.setNull(41, (int) transaction.get(40));															//ADUPDATEDATE
			ps.setString(42, transaction.get(41).toString());													//ADDELETEUSER
			ps.setNull(43, (int) transaction.get(42));															//ADDELETEDATE
			
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
                       
            if(rs.next()){
            	id = rs.getLong(1);
            }
            
            return id;
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.copyTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.copyTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.copyTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.copyTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.copyTransaction() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.copyTransaction() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void deleteTransaction(String transaction) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql; 
        
        sql = "UPDATE F55FQ10001 "
            	+ "   SET ADYQ10PR01 = ?,"
            	+ "       ADDELETEUSER = ?,"
            	+ "       ADDELETEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
            	+ " WHERE TRIM(ADUKID) = ?";
	        
	    try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
			ps.setString(1, "D");
			ps.setString(2, Settings.getIpAddress());
			ps.setString(3, Settings.getEventDate());
			ps.setString(4, transaction);
			
            ps.executeUpdate();
	          
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteTransaction() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteTransaction() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static Vector<Object> getDept() {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;       
        
        sql = "SELECT TRIM(DPDEPT),"
            + "       TRIM(DPALPH),"
            + "       DPENABLED,"
            + "       TRIM(DPINSERTUSER),"
            + "       TO_CHAR(DPINSERTDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(DPUPDATEUSER),"
            + "       TO_CHAR(DPUPDATEDATE,'DD/MM/YYYY HH24.MI.SS') "
            + "  FROM F55FQ10009 "
            + " WHERE DPDELETED = '0' "
            + " ORDER BY DPDEPT ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
		
	        deptrows.removeAllElements();
	        
	        while (rs.next()) {
	        	Vector<Object> newRow = new Vector<Object>();
	        	
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if(rs.getObject(i) == null){
	            		newRow.addElement("");
	            	}else{
	            		newRow.addElement(rs.getObject(i));
            		}
	            }
	
	            deptrows.addElement(newRow);
	        }
	        
	        return deptrows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getDept() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getDept() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

public static void insertDept(Vector<Object> dept) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "INSERT INTO F55FQ10009 "
           	+ "            (DPDEPT,"
           	+ "             DPALPH,"
           	+ "             DPENABLED,"
           	+ "             DPDELETED,"
           	+ "             DPINSERTUSER,"
           	+ "             DPINSERTDATE,"
           	+ "             DPUPDATEUSER,"
           	+ "             DPUPDATEDATE,"
           	+ "             DPDELETEUSER,"
           	+ "             DPDELETEDATE) "
           	+ "      VALUES(?,"
           	+ "             ?,"
           	+ "             ?,"
           	+ "             ?,"
           	+ "             ?,"
        	+ "             TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ? )";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, dept.get(0).toString());
			ps.setString(2, dept.get(1).toString());
			ps.setString(3, dept.get(2).toString());
			ps.setString(4, dept.get(3).toString());
			ps.setString(5, dept.get(4).toString());
			ps.setString(6, dept.get(5).toString());
			ps.setString(7, dept.get(6).toString());
			ps.setNull(8, (int) dept.get(7));
			ps.setString(9, dept.get(8).toString());
			ps.setNull(10, (int) dept.get(9));

            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertDept() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertDept() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void updateDept(Vector<Object> dept) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "UPDATE F55FQ10009 "
           	+ "   SET DPALPH = ?,"
           	+ "       DPENABLED = ?,"
           	+ "       DPUPDATEUSER = ?,"
        	+ "       DPUPDATEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
        	+ " WHERE TRIM(DPDEPT) = ?";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, dept.get(1).toString());
			ps.setString(2, dept.get(2).toString());
			ps.setString(3, dept.get(3).toString());
			ps.setString(4, dept.get(4).toString());
			ps.setString(5, dept.get(0).toString());

            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateDept() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateDept() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static void deleteDept(String dept) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "UPDATE F55FQ10009 "
           	+ "   SET DPDELETED = ?,"
           	+ "       DPDELETEUSER = ?,"
        	+ "       DPDELETEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
        	+ " WHERE TRIM(DPDEPT) = ?";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, "1");
			ps.setString(2, Settings.getIpAddress());
			ps.setString(3, Settings.getEventDate());
			ps.setString(4, dept);
			
            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteDept() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteDept() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static Vector<Object> getIsland() {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;       
        
        sql = "SELECT TRIM(ISADDR),"
            + "       TRIM(ISUKID),"
            + "       TRIM(ISALPH),"
            + "       TRIM(ISDEPT),"
            + "       ISENABLED,"
            + "       ISMODE,"
            + "       ISMULTI,"
            + "       ISENABLEDITEMSEL,"
            + "       TRIM(ISINSERTUSER),"
            + "       TO_CHAR(ISINSERTDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(ISUPDATEUSER),"
            + "       TO_CHAR(ISUPDATEDATE,'DD/MM/YYYY HH24.MI.SS') "
            + "  FROM F55FQ10006 "
            + " WHERE ISDELETED = '0' "
            + " ORDER BY ISUKID ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
		
	        islandrows.removeAllElements();
	        
	        while (rs.next()) {
	        	Vector<Object> newRow = new Vector<Object>();
	        	
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if(rs.getObject(i) == null){
	            		newRow.addElement("");
	            	}else{
	            		newRow.addElement(rs.getObject(i));
            		}
	            }
	
	            islandrows.addElement(newRow);
	        }
	        
	        return islandrows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getIsland() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getIsland() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void insertIsland(Vector<Object> island) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "INSERT INTO F55FQ10006 "
        	+ "            (ISADDR,"
        	+ "             ISUKID,"
        	+ "             ISALPH,"
        	+ "             ISDEPT,"
        	+ "             ISMODE,"
        	+ "             ISMULTI,"
        	+ "             ISENABLED,"
        	+ "             ISDELETED,"
        	+ "             ISENABLEDITEMSEL,"
        	+ "             ISINSERTUSER,"
        	+ "             ISINSERTDATE,"
        	+ "             ISUPDATEUSER,"
        	+ "             ISUPDATEDATE,"
        	+ "             ISDELETEUSER,"
        	+ "             ISDELETEDATE) "
        	+ "      VALUES(?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             TO_DATE(?,'DD/MM/YYYY HH24.MI.SS'),"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ? )";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, island.get(0).toString());
			ps.setString(2, island.get(1).toString());
			ps.setString(3, island.get(2).toString());
			ps.setString(4, island.get(3).toString());
			ps.setString(5, island.get(4).toString());
			ps.setString(6, island.get(5).toString());
			ps.setString(7, island.get(6).toString());
			ps.setString(8, island.get(7).toString());
			ps.setString(9, island.get(8).toString());
			ps.setString(10, island.get(9).toString());
			ps.setString(11, island.get(10).toString());
			ps.setString(12, island.get(11).toString());
			ps.setNull(13, (int) island.get(12));
			ps.setString(14, island.get(13).toString());
			ps.setNull(15, (int) island.get(14));

            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertIsland() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertIsland() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void updateIsland(Vector<Object> island) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "UPDATE F55FQ10006 "
        	+ "   SET ISADDR = ?,"
        	+ "       ISALPH = ?,"
        	+ "       ISDEPT = ?,"
        	+ "       ISMODE = ?,"
        	+ "       ISMULTI = ?,"
        	+ "       ISENABLED = ?,"
        	+ "       ISENABLEDITEMSEL = ?,"
        	+ "       ISUPDATEUSER = ?,"
        	+ "       ISUPDATEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
        	+ " WHERE TRIM(ISUKID) = ?";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, island.get(0).toString());
			ps.setString(2, island.get(2).toString());
			ps.setString(3, island.get(3).toString());
			ps.setString(4, island.get(4).toString());
			ps.setString(5, island.get(5).toString());
			ps.setString(6, island.get(6).toString());
			ps.setString(7, island.get(7).toString());
			ps.setString(8, island.get(8).toString());
			ps.setString(9, island.get(9).toString());
			ps.setString(10, island.get(1).toString());

            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateIsland() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateIsland() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static void deleteIsland(String island) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "UPDATE F55FQ10006 "
        	+ "   SET ISDELETED = ?,"
        	+ "       ISDELETEUSER = ?,"
        	+ "       ISDELETEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
        	+ " WHERE TRIM(ISUKID) = ?";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
			ps.setString(1, "1");
			ps.setString(2, Settings.getIpAddress());
			ps.setString(3, Settings.getEventDate());
			ps.setString(4, island);
			
            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteIsland() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteIsland() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static Vector<Object> getLinks(String filters) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;       
        
        sql = "SELECT TRIM(IMUKID),"
            + "       IMSEQ,"
            + "       IMAN8,"
            + "       TRIM(IMALPH),"
            + "       IMENABLED,"
            + "       TRIM(IMINSERTUSER),"
            + "       TO_CHAR(IMINSERTDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(IMUPDATEUSER),"
            + "       TO_CHAR(IMUPDATEDATE,'DD/MM/YYYY HH24.MI.SS') "
            + "  FROM F55FQ10008 "
            + " WHERE IMDELETED = '0' "
            +         filters
            + " ORDER BY IMUKID,IMSEQ ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
		
	        linksrows.removeAllElements();
	        
	        while (rs.next()) {
	        	Vector<Object> newRow = new Vector<Object>();
	            	        	
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if(rs.getObject(i) == null){
	            		newRow.addElement("");
	            	}else{
	            		newRow.addElement(rs.getObject(i));
            		}
	            }
	
	            linksrows.addElement(newRow);
	        }
	        
	        return linksrows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLinks() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getLinks() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLinks() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getLinks() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLinks() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLinks() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void insertLinks(Vector<Object> links) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;            
        
        sql = "INSERT INTO F55FQ10008 "
        	+ "            (IMUKID,"
        	+ "             IMSEQ,"
        	+ "             IMAN8,"
        	+ "             IMALPH,"
        	+ "             IMENABLED,"
        	+ "             IMDELETED,"
        	+ "             IMINSERTUSER,"
        	+ "             IMINSERTDATE,"
        	+ "             IMUPDATEUSER,"
        	+ "             IMUPDATEDATE,"
        	+ "             IMDELETEUSER,"
        	+ "             IMDELETEDATE)"
        	+ "      VALUES(?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             TO_DATE(?,'DD/MM/YYYY HH24.MI.SS')"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?,"
        	+ "             ?)";
        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, links.get(0).toString());
            ps.setString(2, links.get(1).toString());
            ps.setString(3, links.get(2).toString());
            ps.setString(4, links.get(3).toString());
            ps.setString(5, links.get(4).toString());
            ps.setString(6, links.get(5).toString());
            ps.setString(7, links.get(6).toString());
            ps.setString(8, links.get(7).toString());
            ps.setString(9, links.get(8).toString());
			ps.setNull(10, (int) links.get(9));
			ps.setString(11, links.get(10).toString());
			ps.setNull(12, (int) links.get(11));
            
            ps.executeUpdate();
                
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertLinks() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertLinks() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertLinks() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.insertLinks() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertLinks() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.insertLinks() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void updateLinks(Vector<Object> links) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;            
        
        sql = "UPDATE F55FQ10008 "
            	+ "   SET IMSEQ = ?,"
            	+ "       IMENABLED = ?,"
            	+ "       IMUPDATEUSER = ?,"
            	+ "       IMUPDATEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
            	+ " WHERE TRIM(IMUKID) = ?"
            	+ "   AND IMAN8 = ?";
        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
            ps.setString(1, links.get(1).toString());
            ps.setString(2, links.get(3).toString());
            ps.setString(3, links.get(4).toString());
            ps.setString(4, links.get(5).toString());
            ps.setString(5, links.get(0).toString());
            ps.setString(6, links.get(2).toString());
            
            ps.executeUpdate();
                
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateLinks() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateLinks() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateLinks() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.updateLinks() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateLinks() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.updateLinks() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static void deleteLink(Vector<Object> link) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        String sql;       
        
        sql = "UPDATE F55FQ10008 "
        	+ "   SET IMDELETED = ?,"
        	+ "       IMDELETEUSER = ?,"
        	+ "       IMDELETEDATE = TO_DATE(?,'DD/MM/YYYY HH24.MI.SS') "
        	+ " WHERE TRIM(IMUKID) = ? "
        	+ "   AND IMSEQ = ? "
        	+ "   AND IMAN8 = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
        	
			ps.setString(1, "1");
			ps.setString(2, Settings.getIpAddress());
			ps.setString(3, Settings.getEventDate());
			ps.setString(4, link.get(0).toString());
			ps.setString(5, link.get(1).toString());
			ps.setString(6, link.get(2).toString());
			
            ps.executeUpdate();
	            
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteLink() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteLink() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteLink() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.deleteLink() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteLink() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.deleteLink() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String getElementDescription(String element) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String elementdescription = null;
        
        sql = "SELECT TRIM(ABALPH) "
            + "  FROM F0101 "
            + " WHERE ABAN8 = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, element);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	elementdescription = rs.getString(1);
	        }
	        
	        return elementdescription; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getElementDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getElementDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getElementDescription() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getElementDescription() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getElementDescription() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getElementDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String getOperationDescription(String workorder,String operation) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String operationdescription = null;
        
        sql = "SELECT TRIM(WLDSC1) "
            + "  FROM F3112 "
            + " WHERE WLDOCO = ? "
            + "   AND WLOPSQ = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            ps.setString(2, operation);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	operationdescription = rs.getString(1);
	        }
	        
	        return operationdescription; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getOperationDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getOperationDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getOperationDescription() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getOperationDescription() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getOperationDescription() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getOperationDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String getWorkCenterOfOperation(String workorder,String operation) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String workcenterofoperation = null;
        
        sql = "SELECT TRIM(WLMCU) "
            + "  FROM F3112 "
            + " WHERE WLDOCO = ? "
            + "   AND WLOPSQ = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            ps.setString(2, operation);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	workcenterofoperation = rs.getString(1);
	        }
	        
	        return workcenterofoperation; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getWorkCenterOfOperation() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getWorkCenterOfOperation() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getWorkCenterOfOperation() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getWorkCenterOfOperation() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getWorkCenterOfOperation() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getWorkCenterOfOperation() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String getBranchOfWorkOrder(String workorder) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String branchofworkorder = null;
        
        sql = "SELECT TRIM(WAMMCU) "
            + "  FROM F4801 "
            + " WHERE WADOCO = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	branchofworkorder = rs.getString(1);
	        }
	        
	        return branchofworkorder; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getBranchOfWorkOrder() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getBranchOfWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getBranchOfWorkOrder() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getBranchOfWorkOrder() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getBranchOfWorkOrder() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getBranchOfWorkOrder() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String getItemOfWorkOrder(String workorder) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String getitemofworkorder = null;
        
        sql = "SELECT TRIM(WALITM) "
            + "  FROM F4801 "
            + " WHERE WADOCO = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	getitemofworkorder = rs.getString(1);
	        }
	        
	        return getitemofworkorder; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemOfWorkOrder() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getItemOfWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemOfWorkOrder() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getItemOfWorkOrder() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemOfWorkOrder() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemOfWorkOrder() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String getItemDescription(String workorder) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String getitemdescription = null;
        
        sql = "SELECT TRIM(WADL01) "
            + "  FROM F4801 "
            + " WHERE WADOCO = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	getitemdescription = rs.getString(1);
	        }
	        
	        return getitemdescription; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getItemDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemDescription() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getItemDescription() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemDescription() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getItemDescription() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static String getProject(String workorder) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String getproject = null;
        
        sql = "SELECT WAPRJM "
            + "  FROM F4801T "
            + " WHERE WADOCO = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	getproject = rs.getString(1);
	        }
	        
	        return getproject; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getProject() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getProject() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getProject() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getProject() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getProject() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getProject() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String checkIfBranchExists(String element) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String elementbranch = null;
        
        sql = "SELECT TRIM(MCMCU) "
            + "  FROM F0006 "
            + " WHERE TRIM(MCMCU) = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, element);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	elementbranch = rs.getString(1);
	        }
	        
	        return elementbranch; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfBranchExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfBranchExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfBranchExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfBranchExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfBranchExists() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfBranchExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static String checkIfWorkOrderExists(String element) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String elementworkorder = null;
        
        sql = "SELECT WADOCO "
            + "  FROM F4801 "
            + " WHERE WADOCO = ? "
            + "   AND WASRST < 94";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, element);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	elementworkorder = rs.getString(1);
	        }
	        
	        return elementworkorder; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfWorkOrderExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfWorkOrderExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfWorkOrderExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfWorkOrderExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfWorkOrderExists() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfWorkOrderExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static String checkIfOperationExists(String workorder, String operation) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String elementoperation = null;
        
        sql = "SELECT WLOPSQ "
            + "  FROM F3112 "
            + " WHERE WLDOCO = ? "
            + "   AND WLOPSQ = ? "
            + "   AND EXISTS(SELECT 'X' "
            + "                FROM F4801 "
            + "               WHERE WADOCO = WLDOCO "
            + "                 AND WASRST < 94)";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, workorder);
            ps.setString(2, operation);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	elementoperation = rs.getString(1);
	        }
	        
	        return elementoperation; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfOperationExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfOperationExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfOperationExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfOperationExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfOperationExists() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfOperationExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}

	public static String checkIfIslandExists(String element) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String elementisland = null;
        
        sql = "SELECT ISUKID "
            + "  FROM F55FQ10006 "
            + " WHERE TRIM(ISUKID) = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, element);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	elementisland = rs.getString(1);
	        }
	        
	        return elementisland; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfIslandExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfIslandExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static String checkIfElementExists(String element) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;   
        String elementid = null;
        
        sql = "SELECT ABAN8 "
            + "  FROM F0101 "
            + " WHERE ABAT1 IN ('WC','E') "
            + "   AND ABAN8 = ? ";
	        
        try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, element);
            
            rs = ps.executeQuery();
                    
	        while (rs.next()) {
	        	elementid = rs.getString(1);
	        }
	        
	        return elementid; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfIslandExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.checkIfIslandExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.checkIfIslandExists() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static HashMap<Integer, String> getLastImport() {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql; 
                
        sql = "SELECT TRIM(TRUSER),"
            + "       TO_CHAR(TRDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRDEPT,"
            + "       TO_CHAR(TRFROMDATE,'DD/MM/YYYY'),"
            + "       TO_CHAR(TRTODATE,'DD/MM/YYYY'),"
            + "       TRFIRSTID,"
            + "       TRREC,"
            + "       TRLASTID "
            + "  FROM F55FQ10199 "
            + " WHERE TRID = (SELECT MAX(TRID) FROM F55FQ10199)";
	        
	    try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            		
	        importrows.clear();
	        
	        while (rs.next()) {
	            importrows.put(0, rs.getString(1));
	            importrows.put(1, rs.getString(2));
	            importrows.put(2, rs.getString(3));
	            importrows.put(3, rs.getString(4));
	            importrows.put(4, rs.getString(5));
	            importrows.put(5, rs.getString(6));
	            importrows.put(6, rs.getString(7));
	            importrows.put(7, rs.getString(8));
	        }
	        
	        return importrows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLastImport() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getLastImport() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLastImport() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getLastImport() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLastImport() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getLastImport() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static Vector<Object> getHistoryImport() {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql; 
        
        sql = "SELECT TO_CHAR(TRDATE,'DD/MM/YYYY HH24.MI.SS'),"
            + "       TRIM(TRUSER),"
            + "       TRDEPT,"
            + "       TO_CHAR(TRFROMDATE,'DD/MM/YYYY'),"
            + "       TO_CHAR(TRTODATE,'DD/MM/YYYY'),"
            + "       TRFIRSTID,"
            + "       TRREC,"
            + "       TRLASTID "
            + "  FROM F55FQ10199 "
            + " ORDER BY TRDATE DESC";
	        
	    try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
		
	        historyimportrows.removeAllElements();
	        
	        while (rs.next()) {
	        	Vector<Object> newRow = new Vector<Object>();
	
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if(rs.getObject(i) == null){
	            		newRow.addElement("");
	            	}else{
	            		newRow.addElement(rs.getObject(i));
            		}
	            }
	
	            historyimportrows.addElement(newRow);
	        }
	        
	        return historyimportrows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getHistoryImport() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getHistoryImport() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getHistoryImport() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getHistoryImport() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getHistoryImport() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getHistoryImport() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static void importToJde(String dept,String fromdate,String todate) {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        String sqllock;
        String sqlunlock;
        String sqlmax;
        String sqllog;
        String sqlupdate;
        String sqlcount;
        int actualfirstid = 0;
        int actualrecords = 0;
        int actuallastid = 0;
        

	    try {
	    
	    	
	       	sqllock = "SELECT UKUKID FROM F00022 WHERE UKOBNM = 'FQ10001' FOR UPDATE";
	       	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sqllock);
            
            conn.setAutoCommit(false);

            rs = ps.executeQuery();

            while (rs.next()){
                actualfirstid = rs.getInt(1);
            }
            
            actualfirstid = actualfirstid - 1;
            
            ps.close();
         
            
	        sqlcount = "SELECT COUNT(*) "
	                 + "  FROM F55FQ10001 "
	                 + " WHERE TRIM(ADYQ10PR01) IS NULL " 
	                 + "   AND ADDEPT = '" + dept + "' "
	                 + "   AND TRUNC(ADDTUP) BETWEEN TO_DATE('" + fromdate + "','DD/MM/YYYY') AND  TO_DATE('" + todate + "','DD/MM/YYYY') "
	                 + "   AND NOT EXISTS(SELECT 'X' "
	                 + "                    FROM F55FQ10002 "
	                 + "                   WHERE USMMCU = ADMMCU "
	                 + "                     AND USAN8 = ADAN8 "
	                 + "                     AND USDTUP = ADDTUP "    
	                 + "                     AND USDOCO = ADDOCO "
	                 + "                     AND USOPSQ = ADOPSQ "
	                 + "                     AND USYQ10USST IN('3','4')) ";
             
            ps = conn.prepareStatement(sqlcount);

            rs = ps.executeQuery();

            while (rs.next()){
                actualrecords = rs.getInt(1);
            }
              
            ps.close();
            
            
	        sql = "INSERT INTO FQ10001 "
	            + "     SELECT (" + actualfirstid + " + ROWNUM),"
	            + "            A.* "  
	            + "       FROM (SELECT ADMMCU,"
	            + "                    ADAN8,"
	            + "                    ADMCU,"
	            + "                    ADYQ10DECD,"
	            + "                    ADYQ10ACCD,"
	            + "                    ADDOCO,"
	            + "                    ADOPSQ,"
	            + "                    ADSOQS,"
	            + "                    ADSOCN,"
	            + "                    ADREAC,"
	            + "                    ADYQ10DERN,"
	            + "                    ADDTUP,"
	            + "                    ADYQ10TRCD,"
	            + "                    ADYQ10REST,"
	            + "                    ADYQ10PR01,"
	            + "                    ADYQ10PR02,"
	            + "                    ADURDT,"
	            + "                    ADURAB,"
	            + "                    ADURAT,"
	            + "                    ADURCD,"
	            + "                    ADURRF,"
	            + "                    ADPID,"
	            + "                    ADJOBN,"
	            + "                    ADUSER,"
	            + "                    ADUPMJ,"
	            + "                    ADUPMT "
	            + "               FROM F55FQ10001 "
	            + "              WHERE TRIM(ADYQ10PR01) IS NULL "
	            + "                AND ADDEPT = '" + dept + "' "
	            + "                AND TRUNC(ADDTUP) BETWEEN TO_DATE('" + fromdate + "','DD/MM/YYYY') AND  TO_DATE('" + todate + "','DD/MM/YYYY') "    
	            + "                AND NOT EXISTS(SELECT 'X' "
	            + "                                 FROM F55FQ10002 "
	            + "                                WHERE USMMCU = ADMMCU "
	            + "                                  AND USAN8 = ADAN8 "
	            + "                                  AND USDTUP = ADDTUP "    
	            + "                                  AND USDOCO = ADDOCO "
	            + "                                  AND USOPSQ = ADOPSQ "
	            + "                                  AND USYQ10USST IN('3','4')) "    
	            + "   ORDER BY 2,12,4,5) A";

            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
            ps.close();

        
            sqlmax = "SELECT MAX(ADUKID) FROM FQ10001";
        	
            ps = conn.prepareStatement(sqlmax);

            rs = ps.executeQuery();

            while (rs.next()){
                actuallastid = rs.getInt(1);
            }
            
            actuallastid = actuallastid + 1;

            ps.close();
            
        
            sqlunlock = "UPDATE F00022 SET UKUKID = " + actuallastid + " WHERE UKOBNM = 'FQ10001'";
        
            ps = conn.prepareStatement(sqlunlock);

            rs = ps.executeQuery();                
            
            actualfirstid = actualfirstid + 1;
            
            ps.close();
            
        
	        sqllog = "INSERT INTO F55FQ10199 "
	               + "            (TRID,"
	               + "             TRDATE,"
	               + "             TRUSER,"
	               + "             TRFROMDATE,"
	               + "             TRTODATE,"
	               + "             TRDEPT,"
	               + "             TRISL," 
	               + "             TRFIRSTID,"
	               + "             TRREC,"
	               + "             TRLASTID) "
	               + "     VALUES (F55FQ10199SEQ.NEXTVAL,"
	               + "             TO_DATE('" + Settings.getEventDate() + "','DD/MM/YYYY HH24.MI.SS')" + ","
	               + "             '" + Settings.getIpAddress() + "',"
	               + "             TO_DATE('" + fromdate + "','DD/MM/YYYY HH24.MI.SS'),"
	               + "             TO_DATE('" + todate + "','DD/MM/YYYY HH24.MI.SS'),"
	               + "             '" + dept + "',"
	               + "			   '" + " " + "'," 
	               + "			   '" + actualfirstid + "',"
	               + "			   '" + actualrecords + "',"
	               + "			   '" + actuallastid + "')"; 
            
            ps = conn.prepareStatement(sqllog);

            rs = ps.executeQuery();  
            
 
	        sqlupdate = "UPDATE F55FQ10001 "
	                  + "   SET ADYQ10PR01 = 'P',"
	                  + "       ADPROCESSEDUSER = '" + Settings.getIpAddress() + "',"
	                  + "       ADPROCESSEDDATE = TO_DATE('" + Settings.getEventDate() + "','DD/MM/YYYY HH24.MI.SS')" 
	                  + " WHERE TRIM(ADYQ10PR01) IS NULL "
	                  + "   AND ADDEPT = '" + dept + "'"
	                  + "   AND TRUNC(ADDTUP) BETWEEN TO_DATE('" + fromdate + "','DD/MM/YYYY HH24.MI.SS') AND  TO_DATE('" + todate + "','DD/MM/YYYY HH24.MI.SS') "
	                  + "   AND NOT EXISTS(SELECT 'X' "
	                  + "                    FROM F55FQ10002 "
	                  + "                   WHERE USMMCU = ADMMCU "
	                  + "                     AND USAN8 = ADAN8 "
	                  + "                     AND USDTUP = ADDTUP "    
	                  + "                     AND USDOCO = ADDOCO "
	                  + "                     AND USOPSQ = ADOPSQ "
	                  + "                     AND USYQ10USST IN('3','4')) "; 
        
            ps = conn.prepareStatement(sqlupdate);

            rs = ps.executeQuery();
            
            conn.commit();
            conn.setAutoCommit(true);
            
            rs.close();
            ps.close();
		        
        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.importToJde() ]",Sqlex.getMessage(),null);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.importToJde() ]",e.getMessage(),null);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }           

	}
	
	public static Vector<Object> getClientUpdateInfo() {
		
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql; 
        
        sql = "SELECT TRIM(UPADDR),"
            + "       TRIM(UPAPPLICATION),"
            + "       TRIM(UPVER),"	
            + "       TO_CHAR(UPADATE,'DD/MM/YYYY HH24.MI.SS') "
            + "  FROM F55FQ10011 "
            + " ORDER BY UPVER ";
	        
	    try {
	    	
	    	conn = Settings.getDbConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            
	        ResultSetMetaData metaData = rs.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
		
	        clientupdateinforows.removeAllElements();
	        
	        while (rs.next()) {
	        	Vector<Object> newRow = new Vector<Object>();
	
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if(rs.getObject(i) == null){
	            		newRow.addElement("");
	            	}else{
	            		newRow.addElement(rs.getObject(i));
            		}
	            }
	
	            clientupdateinforows.addElement(newRow);
	        }
	        
	        return clientupdateinforows; 
		        
	    } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getClientUpdateInfo() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getClientUpdateInfo() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBOracleManager.getClientUpdateInfo() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBOracleManager.getClientUpdateInfo() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getClientUpdateInfo() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.getClientUpdateInfo() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static int transactionRecordCount(){
		
		transactionrecords = transactionrows.size();
		
		return transactionrecords;
	
	}
	
	public static int deptRecordCount(){
		
		deptrecords = deptrows.size();
		
		return deptrecords;
	
	}
	
	public static int islandRecordCount(){
		
		islandrecords = islandrows.size();
		
		return islandrecords;
	
	}
	
	public static int linksRecordCount(){
		
		linksrecords = linksrows.size();
		
		return linksrecords;
	
	}
	
	public static int historyImportRecordCount(){
		
		historyimportrecords = historyimportrows.size();
		
		return historyimportrecords;
	
	}

	public static int clientUpdateInfoRecordCount(){
		
		clientupdateinforecords = clientupdateinforows.size();
		
		return clientupdateinforecords;
	
	}

}
