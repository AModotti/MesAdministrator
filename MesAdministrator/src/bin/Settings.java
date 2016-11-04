package bin;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.TimeZone;

public class Settings {
    
    private static String dbconnectionstring;
    private static String driver; 
    private static String dbuser;  
    private static String importtojdeuser;
    private static String dbpassword;
    private static String environment;
    private static String updateserverpath;
    private static String application;
    private static String enablelog;

    public static void storageSettings(String xmldbconnectionstring,String xmldriver,String xmldbuser,String xmlimporttojdeuser,String xmlupdateserverpath,String xmlapplication,String xmlenablelog){
    	
    	dbconnectionstring = xmldbconnectionstring;
    	driver = xmldriver;
    	dbuser = xmldbuser;
    	importtojdeuser = xmlimporttojdeuser;
    	updateserverpath = xmlupdateserverpath;
    	application = xmlapplication;
    	enablelog = xmlenablelog;
    	
    	if(dbuser.equals("PRODDTA")){
    		dbpassword = "PRODDTA";
    	}else if(dbuser.equals("CRPDTA")){
    		dbpassword = "CRPDTA";
    	}else if(dbuser.equals("admin")){
    		dbpassword = "sowhat";
    	}else if(dbuser.equals("root")){
    		dbpassword = "sowhat";
    	}else{
    		dbpassword = "";
    	}
    	
    }
    
    public static String getParameters() {
    	
    	return "Update server : " + updateserverpath + " | " + "Log : " + enablelog;
    }
    
    public static String getDbConnectionString() {
    	
    	return dbconnectionstring;
    }
    
    public static String getDriver() {
    	
    	return driver;
    }
    
    public static String getUpdateServerPath() {
    	
    	return updateserverpath;
    }
    
    public static String getImportToJdeUser() {
    	
    	return importtojdeuser;
    }
    
    public static String getLogTracing() {
    	
    	return enablelog;
    }
    
    public static String getApplication() {
    	
    	return application;
    }
    
    public static String checkDbConnection() {
    	 
        String checkdbConnection = null;
        
        try{
            Class.forName(driver).newInstance();
            checkdbConnection = "true";

        }catch (ClassNotFoundException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
            checkdbConnection = "false";

        }catch (InstantiationException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
            checkdbConnection = "false";

        }catch (IllegalAccessException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
            checkdbConnection = "false";
        }
        

        try{
        	Connection dbConnection = DriverManager.getConnection(dbconnectionstring,dbuser,dbpassword);
        	dbConnection.close();
        	checkdbConnection = "true";
        }catch (SQLException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.checkDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
            checkdbConnection = "false";
        }

        return checkdbConnection;
 
    }
    
    public static Connection getDbConnection() {
 
        Connection dbConnection = null;

        try{

            Class.forName(driver).newInstance();

        }catch (ClassNotFoundException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        }catch (InstantiationException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        }catch (IllegalAccessException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        }
        

        try{

            dbConnection = DriverManager.getConnection(dbconnectionstring,dbuser,dbpassword);

            return dbConnection;

        }catch (SQLException e) {
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.getDbConnection() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        }

        return dbConnection;
 
    }
    
    public static String getEnvironment(){
        
        if(dbuser.equals("CRPDTA")){ 
            environment = "Test";
        }else if (dbuser.equals("PRODDTA")){ 
            environment = "Produzione";  
        }else if (dbuser.equals("admin")){ 
            environment = "Dipartimentale";  
        }else if (dbuser.equals("root")){ 
            environment = "Dipartimentale";  
        }
        
        return environment;
    
    }
        
    public static String getDate(){
        
        String datenow;
        
        Date date = new Date(); 
        
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                              
        datenow = dateformat.format(date);
        
        return datenow;
        
    }
    
    public static String getDateForFileLog(){
        
        String datenow;
        
        Date date = new Date(); 
        
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                              
        datenow = dateformat.format(date);
        
        return datenow;
        
    }
    
    public static String getDateForEventLog(){
        
        String datenow;
        
        Date date = new Date(); 
        
        DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
                              
        datenow = dateformat.format(date);
        
        return datenow;
        
    }
    
    public static String getTime(){
        
        String timenow;
        
        Date date = new Date(); 

        DateFormat timeformat = new SimpleDateFormat("HHmmss");

        timenow = timeformat.format(date);
        
        return timenow;
        
    }
    
    public static String getTimeForEventLog(){
        
        String timenow;
        
        Date date = new Date(); 

        DateFormat timeformat = new SimpleDateFormat("HH.mm.ss");

        timenow = timeformat.format(date);
        
        return timenow;
        
    }
    
    public static String getTimestamp(){
        
        String timestamp;
        
        Date date = new Date(); 
        
        DateFormat dateformatGMT = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss");
        
        dateformatGMT.setTimeZone(TimeZone.getTimeZone("GMT")); 
        
        timestamp = dateformatGMT.format(date);
                
        return timestamp;
        
    }
    
    public static String getEventDate(){
        
        String eventdate;
        
        Date date = new Date(); 
        
        DateFormat dateformatevent = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss");
                
        eventdate = dateformatevent.format(date);
        
        return eventdate;
        
    }
    
    public static String getIpAddress(){
    	
    	String currentHostIpAddress = null;
		String currentHostIpAddresses = "";
		Enumeration<NetworkInterface> netInterfaces = null;
		
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();

            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress addr = address.nextElement();
                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
                            && !(addr.getHostAddress().indexOf(":") > -1)) {
                        currentHostIpAddress = addr.getHostAddress();
                        currentHostIpAddresses = currentHostIpAddresses + "/" + currentHostIpAddress;
                    }
                }
            }
            if (currentHostIpAddress == null) {
                currentHostIpAddress = "127.0.0.1";
            }

        } catch (SocketException e) {
            currentHostIpAddress = "127.0.0.1";
            LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ Settings.getIpAddress() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        }
      
        currentHostIpAddresses = currentHostIpAddresses.substring(1,currentHostIpAddresses.length());
		return currentHostIpAddresses;
    }
}
