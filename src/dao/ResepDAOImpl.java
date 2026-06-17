package dao;

import entity.Resep;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResepDAOImpl implements ResepDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public ResepDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Resep resep) {
        String sql = "INSERT INTO resep (rekam_medis_id, tanggal_resep, keterangan) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, resep.getRekamMedisId());
            ps.setDate(2, new java.sql.Date(resep.getTanggalResep().getTime()));
            ps.setString(3, resep.getKeterangan());
            ps.executeUpdate();

            // Ambil generated key dan set ke objek resep
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    resep.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert resep: " + e.getMessage(), e);
        }
    }

    @Override
    public Resep findById(int id) {
        String sql = "SELECT * FROM resep WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToResep(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari resep: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Resep> findByRekamMedisId(int rekamMedisId) {
        String sql = "SELECT * FROM resep WHERE rekam_medis_id = ?";
        List<Resep> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rekamMedisId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToResep(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari resep berdasarkan rekam medis: " + e.getMessage(), e);
        }
        return list;
    }

    // Helper method: mapping ResultSet ke objek Resep
    private Resep mapResultSetToResep(ResultSet rs) throws SQLException {
        Resep resep = new Resep();
        resep.setId(rs.getInt("id"));
        resep.setRekamMedisId(rs.getInt("rekam_medis_id"));
        resep.setTanggalResep(rs.getDate("tanggal_resep"));
        resep.setKeterangan(rs.getString("keterangan"));
        return resep;
    }
}
