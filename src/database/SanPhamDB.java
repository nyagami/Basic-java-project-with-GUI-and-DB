/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package database;
import java.sql.*;
import store.data.SanPham;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hoang
 */
public class SanPhamDB {
    
    // Tìm kiếm thông tin với từ khoá trong thanh tìm kiếm
    // Nếu thành tìm kiếm trống => Lấy toàn bộ thông tin
    public static List<SanPham> getALL(String term){
        List<SanPham> list = new ArrayList<>();
        String query = "SELECT * FROM SanPham ";
        if(term != null && term.length() > 0){
            query += "WHERE ma LIKE '%" + term + "%' " + "OR ten LIKE '%" + term +"%'";
        }
        try{
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                SanPham nv = new SanPham(rs.getString("ma"), rs.getString("ten"), 
                                            rs.getString("hang"), rs.getString("phien_ban"), 
                                            rs.getString("trang_thai"), rs.getInt("so_luong"));
                list.add(nv);
            }
        }catch(SQLException e){
             System.err.println(e);
        }
        return list;
    }
    
    // Thêm sản phẩm
    public static void insert(SanPham nv) {
        String query = "INSERT INTO SanPham(ma, ten, hang, phien_ban, trang_thai, so_luong) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nv.getMaSP());
            pstmt.setString(2, nv.getTen());
            pstmt.setString(3, nv.getHang());
            pstmt.setString(4, nv.getPhienBan());
            pstmt.setString(5, nv.getTrangThai());
            pstmt.setInt(6, nv.getSoLuong());
            pstmt.execute();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    
    // Xoá sản phẩm
    public static void delete(String ma){
        String query = "DELETE FROM SanPham WHERE ma = '" + ma+"'";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
             System.err.println(e);
        }
    }
    
    // Cập nhật sản phẩm
    public static void update(SanPham nv){
        String query = "UPDATE SanPham SET ten = ?, hang = ?, phien_ban = ?, trang_thai = ?, so_luong = ? WHERE ma = ?";
        try{
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nv.getTen());
            pstmt.setString(2, nv.getHang());
            pstmt.setString(3, nv.getPhienBan());
            pstmt.setString(4, nv.getTrangThai());
            pstmt.setInt(5, nv.getSoLuong());
            pstmt.setString(6, nv.getMaSP());
            pstmt.executeUpdate();
            conn.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
}
