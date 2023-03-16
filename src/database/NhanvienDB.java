/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package database;
import java.sql.*;
import store.data.NhanVien;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hoang
 */
public class NhanvienDB {
    // Tìm kiếm thông tin với từ khoá trong thanh tìm kiếm
    // Nếu thành tìm kiếm trống => Lấy toàn bộ thông tin
    public static List<NhanVien> getALL(String term){
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT * FROM NhanVien ";
        if(term != null && term.length() > 0){
            query += "WHERE ma LIKE '%" + term + "%' " + "OR ho_ten LIKE '%" + term +"%'";
        }
        try{
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                NhanVien nv = new NhanVien(rs.getString("ma"), rs.getString("ho_ten"), 
                                            rs.getString("dia_chi"), rs.getString("ngay_sinh"), 
                                            rs.getString("gioi_tinh"), rs.getString("sdt"));
                list.add(nv);
            }
        }catch(SQLException e){
             System.err.println(e);
        }
        return list;
    }
    
    // Thêm nhân viên
    public static void insert(NhanVien nv) {
        String query = "INSERT INTO NhanVien(ma, ho_ten, dia_chi, ngay_sinh, gioi_tinh, sdt) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nv.getMaNV());
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
    
    // Xoá nhân viên
    public static void delete(String ma){
        String query = "DELETE FROM NhanVien WHERE ma = '" + ma+"'";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
             System.err.println(e);
        }
    }
    
    // Cập nhật nhân viên
    public static void update(NhanVien nv){
        String query = "UPDATE NhanVien SET ho_ten = ?, dia_chi = ?, ngay_sinh = ?, gioi_tinh = ?, sdt = ? WHERE ma = ?";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nv.getHoTen());
            pstmt.setString(2, nv.getDiaChi());
            pstmt.setString(3, nv.getNgaySinh());
            pstmt.setString(4, nv.getGioiTinh());
            pstmt.setString(5, nv.getSdt());
            pstmt.setString(6, nv.getMaNV());
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}
