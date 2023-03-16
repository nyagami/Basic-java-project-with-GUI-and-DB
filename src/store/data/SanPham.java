/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package store.data;

/**
 *
 * @author hoang
 */
public class SanPham {

    private String maSP;
    private String ten;
    private String hang;
    private String phienBan;
    private String trangThai;
    private int soLuong;
    
    public SanPham(String maSP, String ten, String hang, String phienBan, String trangThai, int soLuong){
        this.maSP = maSP;
        this.ten = ten;
        this.hang = hang;
        this.phienBan = phienBan;
        this.trangThai = trangThai;
        this.soLuong = soLuong;
    }
    
    public String getMaSP(){
        return maSP;
    }
    public String getTen(){
        return ten;
    }
    public String getHang(){
        return hang;
    }
    public String getPhienBan(){
        return phienBan;
    }
    public String getTrangThai(){
        return trangThai;
    }
    public int getSoLuong(){
        return soLuong;
    }
}
