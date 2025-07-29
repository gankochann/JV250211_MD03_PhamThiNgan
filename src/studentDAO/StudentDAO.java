package studentDAO;

import entity.Student;
import until.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static int checkEmail(String email){
        Connection conn = null;
        CallableStatement callstm = null;
        int cntEmail = 0;
        try {
            conn = ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call check_exist_email(?,?)}");
            callstm.setString(1,email);
            callstm.registerOutParameter(2, Types.INTEGER);
            callstm.execute();
            cntEmail = callstm.getInt(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(conn,callstm);
        }
        return cntEmail;
    }

    public static List<Student> findAll(){
        Connection conn = null;
        CallableStatement callstm = null;
        List<Student> listStudents = null;
        try {
            conn = ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call findAll()}");
            ResultSet rs = callstm.executeQuery();
            listStudents = new ArrayList<>();
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone_number"));
                student.setRegisterDate(LocalDate.parse(rs.getString("register_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                student.setStatus(rs.getBoolean("status"));
                listStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn,callstm);
        }
        return listStudents;
    }


    public static boolean add(Student student){
        Connection conn = null;
        CallableStatement callstm = null;
        boolean result = false;
        try {
            conn =ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call add_student(?,?,?,?,?)}");
            callstm.setString(1, student.getName());
            callstm.setString(2, student.getEmail());
            callstm.setString(3, student.getPhone());
            callstm.setDate(4, java.sql.Date.valueOf(student.getRegisterDate()));
            callstm.setBoolean(5, student.isStatus());
            callstm.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn,callstm);
        }
        return result;
    }

    public static boolean update(Student student){
        Connection conn = null;
        CallableStatement callstm = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call update_student(?,?,?,?,?,?)}");
            callstm.setInt(1, student.getId());
            callstm.setString(2, student.getName());
            callstm.setString(3, student.getEmail());
            callstm.setString(4, student.getPhone());
            callstm.setDate(5, java.sql.Date.valueOf(student.getRegisterDate()));
            callstm.setBoolean(6, student.isStatus());
            callstm.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(conn,callstm);
        }
        return result;
    }

    public static Student findStudentById(int id){
        Connection conn = null;
        CallableStatement callstm = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call find_student_by_id(?)}");
           callstm.setInt(1,id);
           ResultSet rs = callstm.executeQuery();
           student = new Student();
           while (rs.next()){
               student.setId(rs.getInt("student_id"));
               student.setName(rs.getString("full_name"));
               student.setEmail(rs.getString("email"));
               student.setPhone(rs.getString("phone_number"));
               student.setRegisterDate(LocalDate.parse(rs.getString("register_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
               student.setStatus(rs.getBoolean("status"));
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn,callstm);
        }
        return student;
    }

    public static boolean delete(int id){
        Connection conn = null;
        CallableStatement callstm = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call delete_student(?)} ");
            callstm.setInt(1, id);
            callstm.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn,callstm);
        }
        return result;
    }

    public static List<Student> searchStudentByName(String name){
        Connection conn = null ;
        CallableStatement callstm = null;
        List<Student> list = null;
        try {
            conn = ConnectionDB.openConnection();
            callstm = conn.prepareCall("{call find_student_by_name(?)}");
            callstm.setString(1, name);
            ResultSet rs = callstm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone_number"));
                student.setRegisterDate(LocalDate.parse(rs.getString("register_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                student.setStatus(rs.getBoolean("status"));
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
