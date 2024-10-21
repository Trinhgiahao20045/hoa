/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Hoa;

/**
 *
 * @author Administrator
 */
public class HoaDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Hoa> getTop10() {
        ArrayList<Hoa> ds = new ArrayList<>();
        String sql = "select top 10 * from Hoa order by gia desc";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                 ds.add(new Hoa(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }

    //Phương thức đọc hoa theo thể loại
    public ArrayList<Hoa> getByCategoryId(int maloai) {
        ArrayList<Hoa> ds = new ArrayList<>();
        String sql = "select * from Hoa where maloai=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maloai);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new Hoa(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5), rs.getDate(6)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }

    public static void main(String[] args) {
        HoaDAO hoaDao = new HoaDAO();
        ArrayList<Hoa> dsHoa = hoaDao.getTop10();
        for (Hoa hoa : dsHoa) {
            System.out.println(hoa);
        }
        
        dsHoa = hoaDao.getByCategoryId(2);
        for (Hoa hoa : dsHoa) {
            System.out.println(hoa);
        }
    }
}
