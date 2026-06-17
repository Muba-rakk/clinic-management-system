package dao;

import entity.Pembayaran;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PembayaranDAOImpl implements PembayaranDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public PembayaranDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pembayaran pembayaran) {
        String sql = "INSERT INTO pembayaran (pendaftaran_id, tanggal_bayar, total_bayar, metode_bayar) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pembayaran.getPendaftaranId());
            ps.setDate(2, new java.sql.Date(pembayaran.getTanggalBayar().getTime()));
            ps.setDouble(3, pembayaran.getTotalBayar());
            ps.setString(4, pembayaran.getMetodeBayar());
            ps.executeUpdate();

            // Ambil generated key dan set ke objek pembayaran
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pembayaran.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert pembayaran: " + e.getMessage(), e);
        }
    }

    @Override
    public Pembayaran findById(int id) {
        String sql = "SELECT * FROM pembayaran WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPembayaran(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari pembayaran: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Pembayaran findByPendaftaranId(int pendaftaranId) {
        String sql = "SELECT * FROM pembayaran WHERE pendaftaran_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pendaftaranId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPembayaran(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari pembayaran berdasarkan pendaftaran: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Pembayaran> findByTanggal(java.util.Date start, java.util.Date end) {
        String sql = "SELECT * FROM pembayaran WHERE tanggal_bayar BETWEEN ? AND ?";
        List<Pembayaran> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(start.getTime()));
            ps.setDate(2, new java.sql.Date(end.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToPembayaran(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil laporan pembayaran: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Pembayaran> findAll() {
        String sql = "SELECT * FROM pembayaran";
        List<Pembayaran> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToPembayaran(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil semua pembayaran: " + e.getMessage(), e);
        }
        return list;
    }

    // Helper method: mapping ResultSet ke objek Pembayaran
    private Pembayaran mapResultSetToPembayaran(ResultSet rs) throws SQLException {
        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setId(rs.getInt("id"));
        pembayaran.setPendaftaranId(rs.getInt("pendaftaran_id"));
        pembayaran.setTanggalBayar(rs.getDate("tanggal_bayar"));
        pembayaran.setTotalBayar(rs.getDouble("total_bayar"));
        pembayaran.setMetodeBayar(rs.getString("metode_bayar"));
        return pembayaran;
    }
}
