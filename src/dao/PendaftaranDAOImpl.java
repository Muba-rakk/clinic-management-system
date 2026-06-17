package dao;

import entity.Pendaftaran;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranDAOImpl implements PendaftaranDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public PendaftaranDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pendaftaran pendaftaran) {
        String sql = "INSERT INTO pendaftaran (pasien_id, dokter_id, tanggal_daftar, keluhan, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pendaftaran.getPasienId());
            ps.setInt(2, pendaftaran.getDokterId());
            ps.setDate(3, new java.sql.Date(pendaftaran.getTanggalDaftar().getTime()));
            ps.setString(4, pendaftaran.getKeluhan());
            ps.setString(5, pendaftaran.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert pendaftaran: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Pendaftaran> findAll() {
        String sql = "SELECT * FROM pendaftaran";
        List<Pendaftaran> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPendaftaran(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data pendaftaran: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Pendaftaran findById(int id) {
        String sql = "SELECT * FROM pendaftaran WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPendaftaran(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari pendaftaran: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Pendaftaran> findByStatus(String status) {
        String sql = "SELECT * FROM pendaftaran WHERE status = ?";
        List<Pendaftaran> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToPendaftaran(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari pendaftaran berdasarkan status: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE pendaftaran SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal update status pendaftaran: " + e.getMessage(), e);
        }
    }

    // Helper method: mapping ResultSet ke objek Pendaftaran
    private Pendaftaran mapResultSetToPendaftaran(ResultSet rs) throws SQLException {
        Pendaftaran pendaftaran = new Pendaftaran();
        pendaftaran.setId(rs.getInt("id"));
        pendaftaran.setPasienId(rs.getInt("pasien_id"));
        pendaftaran.setDokterId(rs.getInt("dokter_id"));
        pendaftaran.setTanggalDaftar(rs.getDate("tanggal_daftar"));
        pendaftaran.setKeluhan(rs.getString("keluhan"));
        pendaftaran.setStatus(rs.getString("status"));
        return pendaftaran;
    }
}
