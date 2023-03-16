/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package store.data;

/**
 *
 * @author hoang
 */
public class KhachHang {
    private String maNV;
    private String hoTen;
    private String diaChi;
    private String ngaySinh;
    private String gioiTinh;
    private String sdt;
    
    public KhachHang(String maNV, String hoTen, String diaChi, String ngaySinh, String gioiTinh, String sdt){
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
    }
    
    public String getMaKH(){
        return maNV;
    }
    public String getHoTen(){
        return hoTen;
    }
    public String getDiaChi(){
        return diaChi;
    }
    public String getNgaySinh(){
        return ngaySinh;
    }
    public String getGioiTinh(){
        return gioiTinh;
    }
    public String getSdt(){
        return sdt;
    }
}
