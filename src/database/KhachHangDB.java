/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package database;
import java.sql.*;
import store.data.KhachHang;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hoang
 */

public class KhachHangDB {
    // Tìm kiếm thông tin với từ khoá trong thanh tìm kiếm
    // Nếu thành tìm kiếm trống => Lấy toàn bộ thông tin
    public static List<KhachHang> getALL(String term){
        List<KhachHang> list = new ArrayList<>();
        String query = "SELECT * FROM KhachHang ";
        if(term != null && term.length() > 0){
            query += "WHERE ma LIKE '%" + term + "%' " + "OR ho_ten LIKE '%" + term +"%'";
        }
        try{
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                KhachHang nv = new KhachHang(rs.getString("ma"), rs.getString("ho_ten"), 
                                            rs.getString("dia_chi"), rs.getString("ngay_sinh"), 
                                            rs.getString("gioi_tinh"), rs.getString("sdt"));
                list.add(nv);
            }
        }catch(SQLException e){
             System.err.println(e);
        }
        return list;
    }
    
    // Thêm khách hàng
    public static void insert(KhachHang nv) {
        String query = "INSERT INTO KhachHang(ma, ho_ten, dia_chi, ngay_sinh, gioi_tinh, sdt) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nv.getMaKH());
            pstmt.setString(2, nv.getHoTen());
            pstmt.setString(3, nv.getDiaChi());
            pstmt.setString(4, nv.getNgaySinh());
            pstmt.setString(5, nv.getGioiTinh());
            pstmt.setString(6, nv.getSdt());
            pstmt.execute();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    
    // Xoá khách hàng
    public static void delete(String ma){
        String query = "DELETE FROM KhachHang WHERE ma = '" + ma+"'";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
             System.err.println(e);
        }
    }
    
    // Cập nhật khách hàng
    public static void update(KhachHang nv){
        String query = "UPDATE KhachHang SET ho_ten = ?, dia_chi = ?, ngay_sinh = ?, gioi_tinh = ?, sdt = ? WHERE ma = ?";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nv.getHoTen());
            pstmt.setString(2, nv.getDiaChi());
            pstmt.setString(3, nv.getNgaySinh());
            pstmt.setString(4, nv.getGioiTinh());
            pstmt.setString(5, nv.getSdt());
            pstmt.setString(6, nv.getMaKH());
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}
