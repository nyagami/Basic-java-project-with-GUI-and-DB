/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package database;
import java.sql.*;
import store.data.GiaoDich;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hoang
 */
public class GiaoDichDB {
    
    // Tìm kiếm thông tin với từ khoá trong thanh tìm kiếm
    // Nếu thành tìm kiếm trống => Lấy toàn bộ thông tin
    public static List<GiaoDich> getALL(String term){
        List<GiaoDich> list = new ArrayList<>();
        String query = """
                       SELECT 
                       GiaoDich.*, SanPham.ten as ten_san_pham, KhachHang.ho_ten as ten_khach_hang 
                       FROM GiaoDich JOIN SanPham JOIN KhachHang 
                       ON ma_sp = SanPham.ma AND ma_kh = KhachHang.ma 
                       """;
        if(term != null && term.length() > 0){
            query += "WHERE SanPham.ten LIKE '%" + term + "%' " + "OR KhachHang.ho_ten LIKE '%" + term +"%'";
        }
        try{
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                GiaoDich gd = new GiaoDich(rs.getInt("ma"), rs.getString("ngay_giao_dich"), rs.getString("ma_sp"), 
                                            rs.getString("ma_kh"), rs.getString("ten_san_pham"), 
                                            rs.getString("ten_khach_hang"));
                list.add(gd);
            }
        }catch(SQLException e){
             System.err.println(e);
        }
        return list;
    }
    
    // Thêm giao dịch (mã giao dịch tự động thêm bởi db)
    public static void insert(GiaoDich gd) {
        String query = "INSERT INTO GiaoDich(ngay_giao_dich, ma_sp, ma_kh) VALUES (?, ?, ?)";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gd.getNgayGiaoDich());
            pstmt.setString(2, gd.getMaSP());
            pstmt.setString(3, gd.getMaKH());
            pstmt.execute();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    
    // Xoá giao dịch
    public static void delete(int ma){
        String query = "DELETE FROM GiaoDich WHERE ma = ?";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(0, ma);
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
             System.err.println(e);
        }
    }
    
    // cập nhật giao dịch
    public static void update(GiaoDich gd){
        String query = "UPDATE SanPham SET ngay_giao_dich = ?, ma_sp = ?, ma_kh = ? WHERE ma = ?";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, gd.getNgayGiaoDich());
            pstmt.setString(2, gd.getMaSP());
            pstmt.setString(3, gd.getMaKH());
            pstmt.setInt(4, gd.getMa());
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}
