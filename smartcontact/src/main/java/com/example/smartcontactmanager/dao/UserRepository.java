package com.example.smartcontactmanager.dao;

import com.example.smartcontactmanager.entities.Course;
import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.helper.AddAss;
import com.example.smartcontactmanager.helper.AddNote;
import com.example.smartcontactmanager.helper.Contact;
import com.example.smartcontactmanager.helper.KeyValuePair;
import com.example.smartcontactmanager.helper.three;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.sql.*;
// import com.dev.models.Restaurant;
// // import com.dev.models.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
// import org.springframework.stereotype.Component;
// import java.util.HashMap;
// import java.util.List;

// public interface UserRepository extends JpaRepository<User, Integer> {
//     @Query("select u from User u where u.email = :email")
//     public User getUserByUserName(@Param("email") String email);
// }

@Component
public class UserRepository{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private JdbcTemplate jdbcTemplate;



    @Autowired
    public UserRepository(DataSource dataSource){
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    String sqldriver="com.mysql.cj.jdbc.Driver";
    String urlname="jdbc:mysql://localhost:3306/newelearning?serverTimezone=UTC";
    String userid="root";
    String pass="password";

    public void update(User user) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "INSERT INTO user (id, first_name, last_name, contact, email, password, role, score) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, user.getFirstName());
        pstmt.setString(3, user.getLastName());
        pstmt.setString(4, user.getContact());
        pstmt.setString(5, user.getEmail());
        pstmt.setString(6, user.getPassword());
        pstmt.setString(7, user.getRole());
        pstmt.setLong(8, user.getScore());
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public User getUserByUserName(String email) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        // Connection con=DriverManager.getConnection(url, username, password);

        User user=null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, email);
        
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId();
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setContact(resultSet.getString("contact"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            user.setScore(resultSet.getLong("score"));
        }
    }

    return user;
    }

    public void updateAssStat(String email, Long id, Long cid) throws ClassNotFoundException, SQLException{

       Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "INSERT INTO assstat (email, assid, courseid) " +
                "VALUES (?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,email);
        pstmt.setLong(2, id);
        pstmt.setLong(3, cid);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public void adduserenroll(String email, int id) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "INSERT INTO studentenroll (email, courseID) " +
                "VALUES (?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,email);
        pstmt.setLong(2, id);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public List<Map<String,String>> getEnrolledCourse(String email) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select courseID from studentenroll where email = ?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,email);
  

        ResultSet resultSet = pstmt.executeQuery();
        List<Map<String,String>> l=new ArrayList<>();
        while(resultSet.next()) {
            Map<String, String> course = new HashMap<>();
        long courseId = resultSet.getLong("courseID");
        // Assuming you have a method to retrieve the course name based on the course ID
        String courseName = getCourseName(courseId);
        course.put(Long.toString(courseId), courseName);

        l.add(course);
        }

        System.out.println(l);

        return l;
    }

    public List<Map<String,String>> getUnenrolledCourse(String email, String s) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        System.out.println(s);
        String sql = "select courseid from course where course_name like ? and courseid not in (select courseID from studentenroll as s where s.email = ?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,"%"+s+"%");
        pstmt.setString(2,email);
  

        ResultSet resultSet = pstmt.executeQuery();
        List<Map<String,String>> l=new ArrayList<>();
        while(resultSet.next()) {
            Map<String, String> course = new HashMap<>();
        long courseId = resultSet.getLong("courseid");
        // Assuming you have a method to retrieve the course name based on the course ID
        String courseName = getCourseName(courseId);
        course.put(Long.toString(courseId), courseName);

        l.add(course);
        }

        System.out.println(l);

        return l;
    }

    public List<three> getUnenrolledCourses(String email, String s) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select courseid, course_name, price from course where course_name like ? and courseid not in (select courseID from studentenroll as s where s.email = ?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,"%"+s+"%");
        pstmt.setString(2,email);
  

        ResultSet resultSet = pstmt.executeQuery();
        List<three> l=new ArrayList<>();
        while(resultSet.next()) {
            three course = new three("","","");
        String courseId = Long.toString(resultSet.getLong("courseid"));
        // Assuming you have a method to retrieve the course name based on the course ID
        String courseName = resultSet.getString("course_name");
        String price= Long.toString(resultSet.getLong("price"));

        course.setFirst(courseId);
        course.setSecond(courseName);
        course.setThird(price);

        l.add(course);
        }

        // System.out.println(l);

        return l;
    }

    public String getCourseName(Long id) throws ClassNotFoundException, SQLException{

       Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        // Connection con=DriverManager.getConnection(url, username, password);

        String s="";
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        String sql = "SELECT course_name FROM course WHERE courseid = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, id);
        
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            s=resultSet.getString("course_name");
        }
    }
        return s;
    }

    public List<three> getAssignments(Long id, String email) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select assignment_name,id from ass_course where courseid = ? and id not in (select assid from assstat where email=? and courseid=?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,id);
        pstmt.setString(2, email);
        pstmt.setLong(3, id);
  

        ResultSet resultSet = pstmt.executeQuery();
        List<three> l=new ArrayList<>();
        while(resultSet.next()) {
           three course=new three("", "","");
        String ass = resultSet.getString("assignment_name");
        // Assuming you have a method to retrieve the course name based on the course ID
        Long score = getScore(resultSet.getLong("id"));
        course.setFirst(ass);
        course.setSecond(Long.toString(score));
        course.setThird(Long.toString(resultSet.getLong("id")));

        l.add(course);
        }

        System.out.println(l);

        return l;
    }

    public List<three> getSubmittedAssignments(String email, Long id) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select assid from assstat where email=? and courseid = ?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,email);
        pstmt.setLong(2, id);
  

        ResultSet resultSet = pstmt.executeQuery();
        List<three> l=new ArrayList<>();
        while(resultSet.next()) {
           three course=new three("", "","");
        String ass = getAssignmentName(resultSet.getLong("assid"));
        
        Long score = getScore(resultSet.getLong("assid"));
        course.setFirst(ass);
        course.setSecond(Long.toString(score));
        course.setThird(Long.toString(resultSet.getLong("assid")));

        l.add(course);
        }

        System.out.println(l);

        return l;
    }

    public String getAssignmentName(Long id) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select assignment_name from ass_course where id=?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        // pstmt.setString(1,email);
        pstmt.setLong(1, id);
  

        ResultSet resultSet = pstmt.executeQuery();
        
        String ass="";
        while(resultSet.next()) {
            ass = resultSet.getString("assignment_name");
        }

        // System.out.println(l);

        return ass;
    }

    public Long getScore(Long id) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select score from ass_course where id = ?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,id);
  

        ResultSet resultSet = pstmt.executeQuery();
        Long d=(long)0;
        while(resultSet.next()) {
        // String ass = resultSet.getString("assignment_name");
        // Assuming you have a method to retrieve the course name based on the course ID
        d = resultSet.getLong("score");
        // course.put(Long.toString(courseId), courseName);
        }

        // System.out.println(l);

        return d;
    }

    public void updateScore(String email, Long score) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "UPDATE user SET score = ? WHERE email = ?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,score);
        pstmt.setString(2, email);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("updated successfully");

    }

    public List<String> getNotes(Long id) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");

        String s="";
        List<String> l=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        String sql = "SELECT title FROM notes_course WHERE courseid = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, id);
        
        ResultSet resultSet = pstmt.executeQuery();
        
        while (resultSet.next()) {
            s=resultSet.getString("title");
            l.add(s);
        }
    }
        return l;
    }


    public void deleteenroll(String email, Long id) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "delete from studentenroll where email=? and courseID=?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setString(1,email);
        pstmt.setLong(2, id);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("updated successfully");

    }

    public void addcourse(Course course) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "INSERT INTO course (courseid, course_name, price, description) " +
                "VALUES (?,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,course.getCourseID());
        pstmt.setString( 2, course.getCourseName());
        pstmt.setLong(3,course.getPrice());
        pstmt.setString( 4, course.getDescription());
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public void removecourse(Long id) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "delete from course where courseid=?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,id);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public void removeassignment(Long id) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "delete from ass_course where id=?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,id);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public void removenote(Long id) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "delete from notes_course where noteid=?";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,id);
  

        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public void addassignment(AddAss addAss) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);


        String sql = "INSERT INTO ass_course(id, assignment_name, courseid, score) " +
                "VALUES (?,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);   

        pstmt.setLong(1,addAss.getId());
        pstmt.setString( 2, addAss.getName());
        pstmt.setLong(3, addAss.getCid());
        pstmt.setLong(4, addAss.getScore());
        
        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public void addnote(AddNote addNote) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;

        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "INSERT INTO notes_course(noteid, courseid, title) " +
                "VALUES (?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,addNote.getId());
        pstmt.setLong( 2, addNote.getCid());
        pstmt.setString(3, addNote.getName());
        
        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }
    
    public String findRole(String email) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        // Connection con=DriverManager.getConnection(url, username, password);

        User user=null;
        String role="";
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        String sql = "SELECT role FROM user WHERE email = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, email);
        
        
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            role=resultSet.getString("role");
        }
    }

    return role;
    }

    public void addcontact(Contact contact) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;

        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "INSERT INTO contact(id, name, email, subject, detail) " +
                "VALUES (?,?,?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);
        
        pstmt.setLong(1,contact.getId());
        pstmt.setString( 2, contact.getName());
        pstmt.setString(3, contact.getEmail());
        pstmt.setString(4, contact.getSubject());
        pstmt.setString(5, contact.getDetail());
        
        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public List<three> getContacts() throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select id, email, subject from contact";

        PreparedStatement pstmt=con.prepareStatement(sql);
  

        ResultSet resultSet = pstmt.executeQuery();
        List<three> l=new ArrayList<>();
        while(resultSet.next()) {
        
        Long id = resultSet.getLong("id");
        String email= resultSet.getString("email");
        String subject = resultSet.getString("subject");

        three s=new three(Long.toString(id),email,subject);
        l.add(s);
        
        }

        // System.out.println(l);

        return l;
    }

    public String getView(Long id) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select detail from contact where id=?";

        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setLong(1, id);
  

        ResultSet resultSet = pstmt.executeQuery();
        
        String s="";
        while(resultSet.next()) {
        s = resultSet.getString("detail");

        }

        // System.out.println(l);

        return s;
    }

    public Long findPrice(Long id) throws ClassNotFoundException, SQLException{
        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        
        Connection con=DriverManager.getConnection(url, username, password);

        
        String sql = "select price from course where courseid=?";

        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setLong(1, id);
  

        ResultSet resultSet = pstmt.executeQuery();
        
        Long p=(long)0;
        while(resultSet.next()) {
        p = resultSet.getLong("price");

        }

        // System.out.println(l);

        return p;
    }

    public void transact(String email, String cid) throws ClassNotFoundException, SQLException{

        Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        Connection con=DriverManager.getConnection(url, username, password);


        String sql = "INSERT INTO transaction(email, courseid, price) " +
                "VALUES (?,?,?)";

        PreparedStatement pstmt=con.prepareStatement(sql);   

        pstmt.setString(1,email);
        pstmt.setLong( 2, Long.parseLong(cid));
        pstmt.setLong(3, findPrice(Long.parseLong(cid)));

        
        pstmt.executeUpdate();

        con.close();
        System.out.println("inserted successfully");
    }

    public List<three> getTransaction(String email) throws ClassNotFoundException, SQLException{

       Class.forName(sqldriver);
        String url=urlname;
        String username=userid;
        String password=pass;
        // System.out.println("Inside update");
        // Connection con=DriverManager.getConnection(url, username, password);

        String s="";
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        String sql = "SELECT id, courseid, price FROM transaction WHERE email = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, email);
        
        ResultSet resultSet = pstmt.executeQuery();
        List<three> l=new ArrayList<>();
        while(resultSet.next()) {
            three k=new three("","","");
            k.setFirst(Long.toString(resultSet.getLong("id")));
            k.setSecond(getCourseName(resultSet.getLong("courseid")));
            k.setThird(Long.toString(resultSet.getLong("price")));

            l.add(k);
        }
        return l;
    }
        
    }
}


// @Repository
// public class UserRepository {
//     @Autowired
//     private JdbcTemplate template;

//     public void save(int id, String FirstName, String LastName, String email, String password, String role, String contact) {
//         String sql = "INSERT INTO user(id,FirstName,LastName,email,password,role,contact) VALUES (:id, :FirstName, :LastName, :email, :password, :role, :contact)";
//         template.update(sql, email, password);
//     }

//     // public User getUser(String username) {
//     //     String sql = "SELECT * FROM user WHERE username = ?";
//     //     return template.queryForObject(sql, new Object[] {username}, new BeanPropertyRowMapper<>(User.class));
//     // }

//     // public void update(User user) {
//     //     String sql = "UPDATE user SET password = ? WHERE username = ?";
//     //     template.update(sql, user.getPassword(), user.getUsername());
//     // }
// }
