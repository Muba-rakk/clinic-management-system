package dao;

import entity.DetailPembayaran;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPembayaranDAOImpl implements DetailPembayaranDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public DetailPembayaranDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(DetailPembayaran detail) {
        String sql = "INSERT INTO detail_pembayaran (pembayaran_id, deskripsi, jumlah_biaya) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getPembayaranId());
            ps.setString(2, detail.getDeskripsi());
            ps.setDouble(3, detail.getJumlahBiaya());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert detail pembayaran: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DetailPembayaran> findByPembayaranId(int pembayaranId) {
        String sql = "SELECT * FROM detail_pembayaran WHERE pembayaran_id = ?";
        List<DetailPembayaran> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pembayaranId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDetailPembayaran(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil detail pembayaran: " + e.getMessage(), e);
        }
        return list;
    }

    // Helper method: mapping ResultSet ke objek DetailPembayaran
    private DetailPembayaran mapResultSetToDetailPembayaran(ResultSet rs) throws SQLException {
        DetailPembayaran detail = new DetailPembayaran();
        detail.setId(rs.getInt("id"));
        detail.setPembayaranId(rs.getInt("pembayaran_id"));
        detail.setDeskripsi(rs.getString("deskripsi"));
        detail.setJumlahBiaya(rs.getDouble("jumlah_biaya"));
        return detail;
    }
}
