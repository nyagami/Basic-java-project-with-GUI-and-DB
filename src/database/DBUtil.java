/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package database;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hoang
 */
public class DBUtil {
    public static String url = "jdbc:mysql://localhost:3306/store";         // đường dẫn tới bảng
    public static String user = "chinh";    //tên người dùng admin  
    public static String password = "1234";  // mật khẩu
    
    // Tạo kết nối tới db
    public static Connection getConnection(){
        Connection conn = null;
        try {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
   // Tạo bảng nếu trong db chưa có
    public static void createDB() {
        String NhanVien = """ 
                            CREATE TABLE IF NOT EXISTS NhanVien(
                                 ma CHAR(10) UNIQUE PRIMARY KEY,
                                 ho_ten VARCHAR(50) NOT NULL,
                                 dia_chi VARCHAR(100),
                                 ngay_sinh VARCHAR(20),
                                 gioi_tinh VARCHAR(10),
                                 sdt VARCHAR(20) 
                            );
                          """;
        String KhachHang = """
                           CREATE TABLE IF NOT EXISTS KhachHang(
                           	ma CHAR(10) UNIQUE PRIMARY KEY,
                               ho_ten VARCHAR(50) NOT NULL,
                               dia_chi VARCHAR(100),
                               ngay_sinh VARCHAR(20) ,
                               gioi_tinh VARCHAR(10) ,
                               sdt VARCHAR(20)
                           );
                           """;
        String SanPham = """
                         CREATE TABLE IF NOT EXISTS SanPham(
                         	ma CHAR(10) UNIQUE PRIMARY KEY,
                             ten VARCHAR(50) NOT NULL,
                             hang VARCHAR(50),
                             phien_ban VARCHAR(20),
                             trang_thai VARCHAR(50) DEFAULT 'Không biết',
                             so_luong INT DEFAULT 0
                         );
                         """;
        String Giaodich = """
                          CREATE TABLE IF NOT EXISTS GiaoDich(
                                ma INT AUTO_INCREMENT PRIMARY KEY,
                              ma_kh CHAR(10) NOT NULL,
                              ma_nv CHAR(10) NOT NULL,
                              ngay_giao_dich VARCHAR(20) NOT NULL,
                              FOREIGN KEY (ma_kh) REFERENCES KhachHang(ma) ON DELETE CASCADE,
                              FOREIGN KEY (ma_nv) REFERENCES NhanVien(ma) ON DELETE CASCADE
                          );
                          """;
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(NhanVien);
            stmt.execute(KhachHang);
            stmt.execute(SanPham);
            stmt.execute(Giaodich);
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}
