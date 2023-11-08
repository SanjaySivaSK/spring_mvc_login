package com.codewithsanjay.Dao;

import com.codewithsanjay.Model.Student;
import com.codewithsanjay.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private String SELECT_QUERY = "SELECT id,Firstname,Lastname FROM student WHERE Firstname=? and Lastname=?";
    private String INSERT_QUERY = "insert into student(Firstname,Lastname,Country,Language,OS) values(?,?,?,?,?);";

    private String SELECT_ALL_QUERY="SELECT * FROM student";
    Connection connection;

    public StudentDao() throws SQLException, ClassNotFoundException {
        connection = Database.getconnection();


    }

    public Student loginuser(String Firstname, String Lastname) throws SQLException {
        Student student = null;


        PreparedStatement p1 = connection.prepareStatement(SELECT_QUERY);
        p1.setString(1, Firstname);
        p1.setString(2, Lastname);

        ResultSet r1 = p1.executeQuery();
        if (r1.next()) {
            student  = new Student();
            student .setId(Integer.parseInt(r1.getString("Id")));
            student .setFirstName(r1.getString("Firstname"));
            student .setLastName(r1.getString("Lastname"));

            System.out.println(student);

        }


        return student;
    }


    public void NewUser(String Firstname ,String Lastname,String Country,String Language,String OS ) throws SQLException {

        PreparedStatement p2 = connection.prepareStatement(INSERT_QUERY);
        System.out.println(p2);
        p2.setString(1, Firstname);
        p2.setString(2, Lastname);
        p2.setString(3, Country);
        p2.setString(4,Language);
        p2.setString(5,OS);
        System.out.println(p2);
        p2.executeUpdate();
        System.out.println(p2);

    }

    public List <Student> getstudent() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
        System.out.println("helooooo");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students= new ArrayList<>();

        while(resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("Id"));
            student.setFirstName(resultSet.getString("Firstname"));
            student.setLastName(resultSet.getString("Lastname"));
            student.setCountry(resultSet.getString("Country"));
            student.setLanguage(resultSet.getString("Language"));
            student.setOS(resultSet.getString("OS"));
            // Set other attributes as needed

            students.add(student);

        }

        return students;
    }


}
