package dao;

import entity.Pasien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasienDAOImpl implements PasienDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public PasienDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pasien pasien) {
        String sql = "INSERT INTO pasien (nama, alamat, no_telepon, tanggal_lahir, jenis_kelamin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pasien.getNama());
            ps.setString(2, pasien.getAlamat());
            ps.setString(3, pasien.getNoTelepon());
            ps.setString(4, pasien.getTanggalLahir());
            ps.setString(5, pasien.getJenisKelamin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert pasien: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Pasien> findAll() {
        String sql = "SELECT * FROM pasien";
        List<Pasien> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPasien(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data pasien: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Pasien findById(int id) {
        String sql = "SELECT * FROM pasien WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPasien(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari pasien: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void update(Pasien pasien) {
        String sql = "UPDATE pasien SET nama=?, alamat=?, no_telepon=?, tanggal_lahir=?, jenis_kelamin=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pasien.getNama());
            ps.setString(2, pasien.getAlamat());
            ps.setString(3, pasien.getNoTelepon());
            ps.setString(4, pasien.getTanggalLahir());
            ps.setString(5, pasien.getJenisKelamin());
            ps.setInt(6, pasien.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal update pasien: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pasien WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal hapus pasien: " + e.getMessage(), e);
        }
    }

    // Helper method: mapping ResultSet ke objek Pasien
    private Pasien mapResultSetToPasien(ResultSet rs) throws SQLException {
        Pasien pasien = new Pasien();
        pasien.setId(rs.getInt("id"));
        pasien.setNama(rs.getString("nama"));
        pasien.setAlamat(rs.getString("alamat"));
        pasien.setNoTelepon(rs.getString("no_telepon"));
        pasien.setTanggalLahir(rs.getString("tanggal_lahir"));
        pasien.setJenisKelamin(rs.getString("jenis_kelamin"));
        pasien.setCreatedAt(rs.getDate("created_at"));
        return pasien;
    }
}
