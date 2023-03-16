/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package store.data;

/**
 *
 * @author hoang
 */
public class GiaoDich {
    private int ma;
    private String ngayGiaoDich;
    private String maSP;
    private String maKH;
    private String tenSanPham;
    private  String tenKhachHang;
   
    public GiaoDich(int ma, String ngayGiaoDich, String maSP, String maKH, String tenSanPham, String tenKhachHang){
        this.ma = ma;
        this.ngayGiaoDich = ngayGiaoDich;
        this.maSP = maSP;
        this.maKH = maKH;
        this.tenSanPham = tenSanPham;
        this.tenKhachHang = tenKhachHang;
    }
    public GiaoDich(int ma, String ngayGiaoDich, String maSP, String maKH){
        this.ma = ma;
        this.ngayGiaoDich = ngayGiaoDich;
        this.maSP = maSP;
        this.maKH = maKH;
    }
    public int getMa(){
        return ma;
    }
    public String getNgayGiaoDich(){
        return ngayGiaoDich;
    }
    public String getMaSP(){
        return maSP;
    }
    public String getMaKH(){
        return maKH;
    }
    public String getTenSanPham(){
        return tenSanPham;
    }
    public String getTenKhachHang(){
        return tenKhachHang;
    }
}
