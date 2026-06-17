package dao;

import entity.RekamMedis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RekamMedisDAOImpl implements RekamMedisDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public RekamMedisDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(RekamMedis rekamMedis) {
        String sql = "INSERT INTO rekam_medis (pendaftaran_id, dokter_id, pasien_id, diagnosa, tindakan, catatan_tambahan, tanggal_periksa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rekamMedis.getPendaftaranId());
            ps.setInt(2, rekamMedis.getDokterId());
            ps.setInt(3, rekamMedis.getPasienId());
            ps.setString(4, rekamMedis.getDiagnosa());
            ps.setString(5, rekamMedis.getTindakan());
            ps.setString(6, rekamMedis.getCatatanTambahan());
            ps.setDate(7, new java.sql.Date(rekamMedis.getTanggalPeriksa().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert rekam medis: " + e.getMessage(), e);
        }
    }

    @Override
    public RekamMedis findById(int id) {
        String sql = "SELECT * FROM rekam_medis WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRekamMedis(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari rekam medis: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public RekamMedis findByPendaftaranId(int pendaftaranId) {
        String sql = "SELECT * FROM rekam_medis WHERE pendaftaran_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pendaftaranId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRekamMedis(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari rekam medis berdasarkan pendaftaran: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<RekamMedis> findByPasienId(int pasienId) {
        String sql = "SELECT * FROM rekam_medis WHERE pasien_id = ?";
        List<RekamMedis> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pasienId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToRekamMedis(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil riwayat rekam medis pasien: " + e.getMessage(), e);
        }
        return list;
    }

    // Helper method: mapping ResultSet ke objek RekamMedis
    private RekamMedis mapResultSetToRekamMedis(ResultSet rs) throws SQLException {
        RekamMedis rm = new RekamMedis();
        rm.setId(rs.getInt("id"));
        rm.setPendaftaranId(rs.getInt("pendaftaran_id"));
        rm.setDokterId(rs.getInt("dokter_id"));
        rm.setPasienId(rs.getInt("pasien_id"));
        rm.setDiagnosa(rs.getString("diagnosa"));
        rm.setTindakan(rs.getString("tindakan"));
        rm.setCatatanTambahan(rs.getString("catatan_tambahan"));
        rm.setTanggalPeriksa(rs.getDate("tanggal_periksa"));
        return rm;
    }
}
