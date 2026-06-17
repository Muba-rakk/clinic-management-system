package dao;

import entity.Dokter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DokterDAOImpl implements DokterDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public DokterDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Dokter dokter) {
        String sql = "INSERT INTO dokter (nama, spesialisasi, no_telepon, jadwal_praktek) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dokter.getNama());
            ps.setString(2, dokter.getSpesialisasi());
            ps.setString(3, dokter.getNoTelepon());
            ps.setString(4, dokter.getJadwalPraktek());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert dokter: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Dokter> findAll() {
        String sql = "SELECT * FROM dokter";
        List<Dokter> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToDokter(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data dokter: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Dokter findById(int id) {
        String sql = "SELECT * FROM dokter WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDokter(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari dokter: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void update(Dokter dokter) {
        String sql = "UPDATE dokter SET nama=?, spesialisasi=?, no_telepon=?, jadwal_praktek=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dokter.getNama());
            ps.setString(2, dokter.getSpesialisasi());
            ps.setString(3, dokter.getNoTelepon());
            ps.setString(4, dokter.getJadwalPraktek());
            ps.setInt(5, dokter.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal update dokter: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM dokter WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal hapus dokter: " + e.getMessage(), e);
        }
    }

    // Helper method: mapping ResultSet ke objek Dokter
    private Dokter mapResultSetToDokter(ResultSet rs) throws SQLException {
        Dokter dokter = new Dokter();
        dokter.setId(rs.getInt("id"));
        dokter.setNama(rs.getString("nama"));
        dokter.setSpesialisasi(rs.getString("spesialisasi"));
        dokter.setNoTelepon(rs.getString("no_telepon"));
        dokter.setJadwalPraktek(rs.getString("jadwal_praktek"));
        return dokter;
    }
}
