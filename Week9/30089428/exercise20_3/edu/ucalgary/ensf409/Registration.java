/**
 * @author Zhifan Li
 * @version 1.0
 * @since 1.0
 */


package edu.ucalgary.ensf409;

import java.sql.*;
/**
 * Class Registration:
 */
public class Registration{
	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;
	private Connection dbConnect;
	private ResultSet results;
	/**
	 * get Connection from mysql-connector-java(the jar file in class path).
	 * and store the instance as a private data member dbConnect.
	 */
	public void initializeConnection(){
		try{
			dbConnect=DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Constructor
	 * @param url DBURL
	 * @param username USERNAME
	 * @param password PASSWORD
	 */
	public Registration(String url, String username, String password){
		DBURL=url;
		USERNAME=username;
		PASSWORD=password;

	}
	/**
	 * getDburl
	 * @return String 
	 */
	public String getDburl(){
		return DBURL;
	}
	/**
	 * getUsername
	 * @return String 
	 */
	public String getUsername(){
		return USERNAME;
	}
	/**
	 * getPassword
	 * @return PASSWORD
	 */
	public String getPassword(){
		return PASSWORD;
	}
	/**
	 * selectAllNames accepts a paramter,and instance of the Connection will return an instance of a Statement object by calling createStatement(),
	 * the statement will  execute by calling executeQuery, which executes SELECT * FROM tableName on the database, and returns ResultSet object.
	 * if cannot find the table, print the stacktrace.
	 * @param tableName
	 * @return	String of names
	 */
	public String selectAllNames(String tableName){
		StringBuilder names=new StringBuilder();
		try{
			Statement myStmt=dbConnect.createStatement();
			results=myStmt.executeQuery("SELECT * FROM "+tableName);
			while(results.next()){
				names.append(results.getString("LName"));
				names.append(",");
				names.append(results.getString("FName"));
				names.append("\n");
			}
			myStmt.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return names.deleteCharAt(names.length()-1).toString();
	}
	/**
	 * showStudios accepts no paramter,and instance of the Connection will return an instance of a Statement object by calling createStatement(),
	 * the statement will  execute by calling executeQuery, which executes SELECT * FROM STUDIO on the database, and returns ResultSet object.
	 * if cannot find the table, print the stacktrace.
	 * @return String of studios
	 */
	public String showStudios(){
		StringBuilder names=new StringBuilder();
		try{
			Statement myStmt=dbConnect.createStatement();
			results=myStmt.executeQuery("SELECT * FROM  STUDIO ");
			while(results.next()){
				names.append(results.getString("Name"));
				names.append("\n");
			}
			myStmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return names.deleteCharAt(names.length()-1).toString();
	}
	/**
	 * insertNewCompetitor
	 * if the age is smaller than 5 or greater than 18, throw IllegalArgumentException;
	 * dbConnect gets a statement object by calling createStatement().
	 * the Statement will execute by calling executeQuery,which executes "SELECT * FROM TEACHER" on database.
	 * After checking TeacherID is valid,
	 * dbConnect gets a preparedStatement object by calling prepareStatement().
	 * by calling setString, several data inserted into the placeholder of the sql insert command.
	 * the Statement will execute by calling executeUpdate(),which executes the insertion on database.
	 * @param CompetitorID CompetitorID
	 * @param LName  LName
	 * @param FName	FName
	 * @param Age Age
	 * @param Instrument Instrument
	 * @param TeacherID TeacherID
	 */
	public void insertNewCompetitor(String CompetitorID,String LName,String FName, int Age, String Instrument, String TeacherID){
		try{
			if(Age<5||Age>18){
				throw new IllegalArgumentException("Competitor's Age is not between 5 and 18.");
			}
			Statement myStmt=dbConnect.createStatement();
			results=myStmt.executeQuery("SELECT * FROM TEACHER");
			int j=0;
			while(results.next()){
				if(TeacherID.equals(results.getString("TeacherID"))){
					j++;
				}
			}
			if(j==0){
				throw new IllegalArgumentException("TeacherID not found.");
			}
			myStmt.close();

			String query="INSERT INTO COMPETITOR (CompetitorID, LName, FName, Age, Instrument, TeacherID) VALUES (?,?,?,?,?,?)";
			PreparedStatement insertStatement=dbConnect.prepareStatement(query);
			insertStatement.setString(1, CompetitorID);
			insertStatement.setString(2, LName);
			insertStatement.setString(3, FName);
			insertStatement.setInt(4, Age);
			insertStatement.setString(5, Instrument);
			insertStatement.setString(6, TeacherID);
			int rowCount=insertStatement.executeUpdate();


			System.out.println("Competitor inserted:"+rowCount);
			
			insertStatement.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * dbConnect gets a statement object by calling createStatement().
	 * the Statement will execute by calling executeQuery,which executes "SELECT * FROM TEACHER" on database.
	 * if the teacher has not been regiestered, check if the studio exists, if not:create the studio with the given information.
	 * the Statement will execute by calling executeQuery,which executes "SELECT * FROM STUDIO" on database.
	 * and after checking if the studio exists, there is an option to do insertion,
	 * dbConnect gets a preparedStatement object by calling prepareStatement().
	 * by calling setString, several data inserted into the placeholder of the sql insert command.
	 * the Statement will execute by calling executeUpdate(),which executes the insertion of the studio on database.
	 * After that, insert teacher into the database by a PreparedStatement object by using dbConnect to call the preparedStatement(), 
	 * by calling setString, several data inserted into the placeholder of the sql insert command.
	 * and using the object to call executeUpdate().
	 * @param TeacherID TeacherID
	 * @param LName LName
	 * @param FName FName
	 * @param teacherPhone Phone of teacher
	 * @param StudioName	StudioName
	 * @param studioPhone	Phone of studio
	 * @param Address	Address of studio
	 */
	public void registerNewTeacher(String TeacherID,String LName,String FName, String teacherPhone,String StudioName, String studioPhone, String Address){
	
		try{
			Statement myStmt=dbConnect.createStatement();
			results=myStmt.executeQuery("SELECT * FROM TEACHER");
				while(results.next()){
				if(TeacherID.equals(results.getString("TeacherID"))){
				throw new IllegalArgumentException("Specified Teacher already registered.");
			
				}

			}
			myStmt.close();
			Statement searchStudio=dbConnect.createStatement();
			results=searchStudio.executeQuery("SELECT * FROM STUDIO");
			int i=0;
			while(results.next()){
				if(StudioName.equals(results.getString("Name"))){ //search if the studio's name exists. if yes, i=1.
					i=1;
				}
				

			}
			searchStudio.close();
			if(i==0){    // if studio name does not exist, insert it in the table STUDIO
			String query="INSERT INTO STUDIO (Name, Phone, Address) VALUES (?,?,?)";
			PreparedStatement addStudio=dbConnect.prepareStatement(query);
			addStudio.setString(1, StudioName);
			addStudio.setString(2, studioPhone);
			addStudio.setString(3, Address);
			int rowEffected=addStudio.executeUpdate();
			System.out.println("Studio inserted:"+StudioName+"Rows affected: "+rowEffected);
			addStudio.close();

			}
			String queryTeacher="INSERT INTO TEACHER (TeacherID, LName, FName, Phone, StudioName) VALUES (?,?,?,?,?)";
			PreparedStatement addTeacher=dbConnect.prepareStatement(queryTeacher);
			addTeacher.setString(1, TeacherID);
			addTeacher.setString(2, LName);
			addTeacher.setString(3, FName);
			addTeacher.setString(4, teacherPhone);
			addTeacher.setString(5, StudioName);
			int rowEffected2=addTeacher.executeUpdate();
			System.out.println("Teacher inserted:"+TeacherID+" Rows affected: "+rowEffected2);
			addTeacher.close();
	    }catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * dbConnect gets a preparedStatement object by calling prepareStatement().
	 * the Statement will execute by calling executeUpdate(),which executes the deletion on database.
	 * @param CompetitorID the id of the competitor to be deleted
	 */
	public void deleteCompetitor(String CompetitorID){
		try {
            String query = "DELETE FROM COMPETITOR WHERE CompetitorID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, CompetitorID);
                        
            int rowCount = myStmt.executeUpdate();
            System.out.println("deleteCompetitor is called, Rows affected: " + rowCount);
            
            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	/**
	 * dbConnect gets a preparedStatement object by calling prepareStatement().
	 * the Statement will execute by calling executeUpdate(),which executes the deletion on database.
	 * @param TeacherID the id of the teacher to be deleted
	 */
	public void deleteTeacher(String TeacherID){
		try {
            String query = "DELETE FROM TEACHER WHERE TeacherID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, TeacherID);
                        
            int rowCount = myStmt.executeUpdate();
            System.out.println("deleteTeacher is called, Rows affected: " + rowCount);
            
            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

	/**
	 * main method
	 * @param args optional
	 */
	public static void main (String[] args) {
		
		Registration myJDBC=new Registration("jdbc:mysql://localhost/competition","zhifanli","Lizhifan123");
		myJDBC.initializeConnection();
		System.out.println(myJDBC.selectAllNames("competitor"));
        /*
        Example:
        Williams, Sophie
        Warren, Harper
        */
                
        System.out.println(myJDBC.selectAllNames("teacher"));
        /*
        Example:
        Estrada, Ethan
        Lee, Jasmine
        */

        System.out.println(myJDBC.showStudios());
        /*
        Example:
        Harmony Inc.
        Melody Time
        Music Mastery
        */
        System.out.println();
        myJDBC.insertNewCompetitor("123", "Smyth", "Ali", 15, "Oboe", "0023");
        myJDBC.registerNewTeacher("0987", "Marasco", "Emily", "403-222-5656", "Marasco Music", "587-222-5656", "123 Main Street NW");        
        
       System.out.println(myJDBC.selectAllNames("teacher"));
       System.out.println();
       System.out.println(myJDBC.selectAllNames("competitor"));
        myJDBC.deleteCompetitor("123");
        myJDBC.deleteTeacher("0987");
	}

}

