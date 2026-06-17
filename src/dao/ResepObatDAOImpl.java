package dao;

import entity.ResepObat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResepObatDAOImpl implements ResepObatDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public ResepObatDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ResepObat resepObat) {
        String sql = "INSERT INTO resep_obat (resep_id, obat_id, jumlah, aturan_pakai) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, resepObat.getResepId());
            ps.setInt(2, resepObat.getObatId());
            ps.setInt(3, resepObat.getJumlah());
            ps.setString(4, resepObat.getAturanPakai());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert resep obat: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ResepObat> findByResepId(int resepId) {
        String sql = "SELECT * FROM resep_obat WHERE resep_id = ?";
        List<ResepObat> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, resepId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToResepObat(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data resep obat: " + e.getMessage(), e);
        }
        return list;
    }

    // Helper method: mapping ResultSet ke objek ResepObat
    private ResepObat mapResultSetToResepObat(ResultSet rs) throws SQLException {
        ResepObat ro = new ResepObat();
        ro.setId(rs.getInt("id"));
        ro.setResepId(rs.getInt("resep_id"));
        ro.setObatId(rs.getInt("obat_id"));
        ro.setJumlah(rs.getInt("jumlah"));
        ro.setAturanPakai(rs.getString("aturan_pakai"));
        return ro;
    }
}
