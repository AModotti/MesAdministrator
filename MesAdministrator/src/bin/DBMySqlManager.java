package bin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

public class DBMySqlManager {
	
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
            + "       DATE_FORMAT(CONVERT_TZ(ADDTUP,'UTC','SYSTEM'),'%d/%m/%Y %H.%i.%s'),"
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
            + "       DATE_FORMAT(ADINSERTDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(ADUPDATEUSER),"
            + "       DATE_FORMAT(ADUPDATEDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(ADDELETEUSER),"
            + "       DATE_FORMAT(ADDELETEDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(ADPROCESSEDUSER),"
            + "       DATE_FORMAT(ADPROCESSEDDATE,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getTransaction() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getTransaction() ]");
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
        	+ "       (ADMMCU,"
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
        	+ "VALUES (?,"																																										//ADMMCU
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
        	+ "        CONVERT_TZ(DATE_FORMAT(STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),'%Y-%m-%d %H:%i:%s'),'SYSTEM','UTC'),"																			//ADDTUP
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
        	+ "        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE('?','%d/%m/%Y'))),"																									//ADUPMJ
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
        	+ "        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"																																		//ADINSERTDATE
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertTransaction() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertTransaction() ]");
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
        	+ "       ADDTUP = CONVERT_TZ(DATE_FORMAT(STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),'%Y-%m-%d %H:%i:%s'),'SYSTEM','UTC'),"
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
        	+ "       ADUPDATEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateTransaction() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateTransaction() ]");
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
            	+ "       (ADMMCU,"
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
            	+ "VALUES (?,"																																										//ADMMCU
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
            	+ "        CONVERT_TZ(DATE_FORMAT(STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),'%Y-%m-%d %H:%i:%s'),'SYSTEM','UTC'),"																			//ADDTUP
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
            	+ "        CONCAT(1,SUBSTRING(?, 9, 2),DAYOFYEAR(STR_TO_DATE('14/09/2016','%d/%m/%Y'))),"																							//ADUPMJ
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
            	+ "        STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"																																		//ADINSERTDATE
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.copyTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.copyTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.copyTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.copyTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	return 0;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.copyTransaction() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.copyTransaction() ]");
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
            	+ "       ADDELETEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteTransaction() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteTransaction() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteTransaction() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteTransaction() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteTransaction() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteTransaction() ]");
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
            + "       DATE_FORMAT(DPINSERTDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(DPUPDATEUSER),"
            + "       DATE_FORMAT(DPUPDATEDATE,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getDept() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getDept() ]");
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
        	+ "             STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertDept() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertDept() ]");
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
        	+ "       DPUPDATEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateDept() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateDept() ]");
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
        	+ "       DPDELETEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteDept() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteDept() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteDept() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteDept() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteDept() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteDept() ]");
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
            + "       DATE_FORMAT(ISINSERTDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(ISUPDATEUSER),"
            + "       DATE_FORMAT(ISUPDATEDATE,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getIsland() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getIsland() ]");
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
        	+ "             STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertIsland() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertIsland() ]");
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
        	+ "       ISUPDATEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateIsland() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateIsland() ]");
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
        	+ "       ISDELETEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteIsland() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteIsland() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteIsland() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteIsland() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteIsland() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteIsland() ]");
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
            + "       DATE_FORMAT(IMINSERTDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(IMUPDATEUSER),"
            + "       DATE_FORMAT(IMUPDATEDATE,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLinks() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getLinks() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLinks() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getLinks() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLinks() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLinks() ]");
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
        	+ "             STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s'),"
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertLinks() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertLinks() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertLinks() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.insertLinks() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertLinks() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.insertLinks() ]");
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
            	+ "       IMUPDATEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateLinks() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateLinks() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateLinks() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.updateLinks() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateLinks() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.updateLinks() ]");
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
        	+ "       IMDELETEDATE = STR_TO_DATE(?,'%d/%m/%Y %H.%i.%s') "
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteLink() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteLink() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteLink() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.deleteLink() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteLink() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.deleteLink() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getElementDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getElementDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getElementDescription() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getElementDescription() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getElementDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getElementDescription() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getOperationDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getOperationDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getOperationDescription() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getOperationDescription() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getOperationDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getOperationDescription() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getWorkCenterOfOperation() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getWorkCenterOfOperation() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getWorkCenterOfOperation() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getWorkCenterOfOperation() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getWorkCenterOfOperation() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getWorkCenterOfOperation() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getBranchOfWorkOrder() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getBranchOfWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getBranchOfWorkOrder() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getBranchOfWorkOrder() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getBranchOfWorkOrder() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getBranchOfWorkOrder() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemOfWorkOrder() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getItemOfWorkOrder() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemOfWorkOrder() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getItemOfWorkOrder() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemOfWorkOrder() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemOfWorkOrder() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemDescription() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getItemDescription() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemDescription() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getItemDescription() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemDescription() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getItemDescription() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getProject() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getProject() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getProject() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getProject() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getProject() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getProject() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfBranchExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfBranchExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfBranchExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfBranchExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfBranchExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfBranchExists() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfWorkOrderExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfWorkOrderExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfWorkOrderExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfWorkOrderExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfWorkOrderExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfWorkOrderExists() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfOperationExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfOperationExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfOperationExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfOperationExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfOperationExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfOperationExists() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfIslandExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfIslandExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfIslandExists() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.checkIfIslandExists() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.checkIfIslandExists() ]");
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
            + "       DATE_FORMAT(TRDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRDEPT,"
            + "       DATE_FORMAT(TRFROMDATE,'%d/%m/%Y'),"
            + "       DATE_FORMAT(TRTODATE,'%d/%m/%Y'),"
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLastImport() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getLastImport() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLastImport() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getLastImport() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLastImport() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getLastImport() ]");
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
        
        sql = "SELECT DATE_FORMAT(TRDATE,'%d/%m/%Y %H.%i.%s'),"
            + "       TRIM(TRUSER),"
            + "       TRDEPT,"
            + "       DATE_FORMAT(TRFROMDATE,'%d/%m/%Y'),"
            + "       DATE_FORMAT(TRTODATE,'%d/%m/%Y'),"
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getHistoryImport() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getHistoryImport() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getHistoryImport() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getHistoryImport() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getHistoryImport() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getHistoryImport() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
        }

	}
	
	public static void importToJde(String dept,String fromdate,String todate) {
		
		Connection oracleconn = null;
		Connection mysqlconn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        String sqlmysql;
        String sqloracle;
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
        	
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
	        String oracledbconnectionstring  = "jdbc:oracle:thin:@192.168.2.12:1521:E1DATA";
	        String oracledbusername = Settings.getImportToJdeUser();
	        String oracledbpassword = Settings.getImportToJdeUser();
	        
			oracleconn = DriverManager.getConnection(oracledbconnectionstring,oracledbusername,oracledbpassword);
			mysqlconn = Settings.getDbConnection();
			
		        
			sqllock = "SELECT UKUKID FROM F00022 WHERE UKOBNM = 'FQ10001' FOR UPDATE";
    		    
            ps = oracleconn.prepareStatement(sqllock);
            
            oracleconn.setAutoCommit(false);

            rs = ps.executeQuery();

            while (rs.next()) {
                actualfirstid = rs.getInt(1);
            }
            
            actualfirstid = actualfirstid - 1;
            
            ps.close();
	    
            
	        sqlcount = "SELECT COUNT(*) "
	                 + "  FROM F55FQ10001 "
	                 + " WHERE ADYQ10PR01 = '' " 
	                 + "   AND TRIM(ADDEPT) = '" + dept + "' "
	                 + "   AND DATE_FORMAT(ADDTUP,'%d/%m/%Y') BETWEEN DATE_FORMAT(STR_TO_DATE('" + fromdate + "','%d/%m/%Y'),'%d/%m/%Y') AND DATE_FORMAT(STR_TO_DATE('" + todate + "','%d/%m/%Y'),'%d/%m/%Y') "
	                 + "   AND NOT EXISTS(SELECT 'X' "
	                 + "                    FROM F55FQ10002 "
	                 + "                   WHERE USMMCU = ADMMCU "
	                 + "                     AND USAN8 = ADAN8 "
	                 + "                     AND USDTUP = ADDTUP "    
	                 + "                     AND USDOCO = ADDOCO "
	                 + "                     AND USOPSQ = ADOPSQ "
	                 + "                     AND USYQ10USST IN('3','4')) ";
   
	        ps = mysqlconn.prepareStatement(sqlcount);
	
            rs = ps.executeQuery();
	
            while (rs.next()) {
                actualrecords = rs.getInt(1);
            }
	        
            ps.close();
                
            
	        sqlmysql = "SELECT (" + actualfirstid + " + @curRow := @curRow + 1) AS row_number,"
	                 + "        A.* "  
	                 + "  FROM (SELECT ADMMCU,"
	                 + "               ADAN8,"
	                 + "               ADMCU,"
	                 + "               ADYQ10DECD,"
	                 + "               ADYQ10ACCD,"
	                 + "               ADDOCO,"
	                 + "               ADOPSQ,"
	                 + "               ADSOQS,"
	                 + "               ADSOCN,"
	                 + "               ADREAC,"
	                 + "               ADYQ10DERN,"
	                 + "               ADDTUP,"
	                 + "               ADYQ10TRCD,"
	                 + "               ADYQ10REST,"
	                 + "               ADYQ10PR01,"
	                 + "               ADYQ10PR02,"
	                 + "               ADURDT,"
	                 + "               ADURAB,"
	                 + "               ADURAT,"
	                 + "               ADURCD,"
	                 + "               ADURRF,"
	                 + "               ADPID,"
	                 + "               ADJOBN,"
	                 + "               ADUSER,"
	                 + "               ADUPMJ,"
	                 + "               ADUPMT "
	                 + "          FROM F55FQ10001 "
	                 + "          JOIN (SELECT @curRow := 0) R "
	                 + "         WHERE ADYQ10PR01 = '' " 
	                 + "           AND TRIM(ADDEPT) = '" + dept + "' "
	                 + "           AND DATE_FORMAT(ADDTUP,'%d/%m/%Y') BETWEEN DATE_FORMAT(STR_TO_DATE('" + fromdate + "','%d/%m/%Y'),'%d/%m/%Y') AND DATE_FORMAT(STR_TO_DATE('" + todate + "','%d/%m/%Y'),'%d/%m/%Y') "
	                 + "           AND NOT EXISTS(SELECT 'X' "
	                 + "                            FROM F55FQ10002 "
	                 + "                           WHERE USMMCU = ADMMCU "
	                 + "                             AND USAN8 = ADAN8 "
	                 + "                             AND USDTUP = ADDTUP "    
	                 + "                             AND USDOCO = ADDOCO "
	                 + "                             AND USOPSQ = ADOPSQ "
	                 + "                             AND USYQ10USST IN('3','4')) "    
	                 + " ORDER BY 2,12,4,5) A";
	            
	        
	        sqloracle = "INSERT INTO FQ10001 VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

	        ps1 = mysqlconn.prepareStatement(sqlmysql);
	        ps2 = oracleconn.prepareStatement(sqloracle);

	        rs = ps1.executeQuery();
            
	        while (rs.next()) {
        	   for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
        		   ps2.setObject(i + 1, rs.getObject(i + 1));
	           }
	           ps2.executeUpdate(); 
	        }

	        ps1.close();
	        ps2.close();
	        
	        
	        sqlmax = "SELECT MAX(ADUKID) FROM FQ10001";
        	
            ps = oracleconn.prepareStatement(sqlmax);

            rs = ps.executeQuery();

            while (rs.next()){
                actuallastid = rs.getInt(1);
            }
            
            actuallastid = actuallastid + 1;
  
            ps.close();
            
            
            sqlunlock = "UPDATE F00022 SET UKUKID = " + actuallastid + " WHERE UKOBNM = 'FQ10001'";

            ps = oracleconn.prepareStatement(sqlunlock);

            rs = ps.executeQuery();                
            
            actualfirstid = actualfirstid + 1;
            
            ps.close();
        
            
	        sqllog = "INSERT INTO F55FQ10199 "
	               + "            (TRDATE,"
	               + "             TRUSER,"
	               + "             TRFROMDATE,"
	               + "             TRTODATE,"
	               + "             TRDEPT,"
	               + "             TRISL," 
	               + "             TRFIRSTID,"
	               + "             TRREC,"
	               + "             TRLASTID) "
	               + "     VALUES (STR_TO_DATE('" + Settings.getEventDate() + "','%d/%m/%Y %H.%i.%s')" + ","
	               + "             '" + Settings.getIpAddress() + "',"
	               + "             STR_TO_DATE('" + fromdate + "','%d/%m/%Y'),"
	               + "             STR_TO_DATE('" + todate + "','%d/%m/%Y'),"
	               + "             '" + dept + "',"
	               + "			   '" + " " + "'," 
	               + "			   '" + actualfirstid + "',"
	               + "			   '" + actualrecords + "',"
	               + "			   '" + actuallastid + "')"; 

            ps = mysqlconn.prepareStatement(sqllog);

            ps.executeUpdate();  
            
            ps.close();
        
            
	        sqlupdate = "UPDATE F55FQ10001 "
	                  + "   SET ADYQ10PR01 = 'P',"
	                  + "       ADPROCESSEDUSER = '" + Settings.getIpAddress() + "',"
	                  + "       ADPROCESSEDDATE = STR_TO_DATE('" + Settings.getEventDate() + "','%d/%m/%Y %H.%i.%s')" 
	                  + " WHERE ADYQ10PR01 = '' " 
	                  + "       AND TRIM(ADDEPT) = '" + dept + "' "
	                  + "       AND DATE_FORMAT(ADDTUP,'%d/%m/%Y') BETWEEN DATE_FORMAT(STR_TO_DATE('" + fromdate + "','%d/%m/%Y'),'%d/%m/%Y') AND DATE_FORMAT(STR_TO_DATE('" + todate + "','%d/%m/%Y'),'%d/%m/%Y') "
	                  + "       AND NOT EXISTS(SELECT 'X' "
	                  + "                        FROM F55FQ10002 "
	                  + "                       WHERE USMMCU = ADMMCU "
	                  + "                         AND USAN8 = ADAN8 "
	                  + "                         AND USDTUP = ADDTUP "    
	                  + "                         AND USDOCO = ADDOCO "
	                  + "                         AND USOPSQ = ADOPSQ "
	                  + "                         AND USYQ10USST IN('3','4')) "; 
  
            ps = mysqlconn.prepareStatement(sqlupdate);

            ps.executeUpdate();
            
            oracleconn.commit();
            oracleconn.setAutoCommit(true);
            
            ps.close();
            mysqlconn.close();
            oracleconn.close();
            
        } catch (SQLException Sqlex) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.importToJde() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.importToJde() ]",Sqlex.getMessage(),null);
        	notification.setModal(true);
        	notification.setVisible(true);

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.importToJde() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.importToJde() ]",e.getMessage(),null);
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
            
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
            
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
                	LogWriter.write("[E] Chiusura PreparedStatement");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }

            if (mysqlconn != null) {
                try {
                	mysqlconn.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBOracleManager.importToJde() ]");
                	LogWriter.write("[E] Chiusura Connection");
                	LogWriter.write("[E] " + e.getMessage());
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                }
            }
            
            if (oracleconn != null) {
                try {
                	oracleconn.close();
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
            + "       DATE_FORMAT(UPADATE,'%d/%m/%Y %H.%i.%s') "  
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
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getClientUpdateInfo() ]");
			LogWriter.write("[E] " + Sqlex.getMessage());
			LogWriter.write("[E] " + sql);
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getClientUpdateInfo() ]",Sqlex.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;

        } catch (Exception e) {

        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
			LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getClientUpdateInfo() ]");
			LogWriter.write("[E] " + e.getMessage());
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	Notifications notification = new Notifications("[ DBMySqlManager.getClientUpdateInfo() ]",e.getMessage(),sql);
        	notification.setModal(true);
        	notification.setVisible(true);
	    	return null;
        	
        } finally {
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getClientUpdateInfo() ]");
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
                	LogWriter.write("[E] Errore in classe: [ DBMySqlManager.getClientUpdateInfo() ]");
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
