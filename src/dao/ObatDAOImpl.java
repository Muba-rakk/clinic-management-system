package dao;

import entity.Obat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObatDAOImpl implements ObatDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public ObatDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Obat obat) {
        String sql = "INSERT INTO obat (nama_obat, satuan, harga_satuan, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obat.getNamaObat());
            ps.setString(2, obat.getSatuan());
            ps.setDouble(3, obat.getHargaSatuan());
            ps.setInt(4, obat.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert obat: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Obat> findAll() {
        String sql = "SELECT * FROM obat";
        List<Obat> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToObat(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data obat: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Obat findById(int id) {
        String sql = "SELECT * FROM obat WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToObat(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari obat: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void update(Obat obat) {
        String sql = "UPDATE obat SET nama_obat=?, satuan=?, harga_satuan=?, stok=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obat.getNamaObat());
            ps.setString(2, obat.getSatuan());
            ps.setDouble(3, obat.getHargaSatuan());
            ps.setInt(4, obat.getStok());
            ps.setInt(5, obat.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal update obat: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM obat WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal hapus obat: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateStok(int id, int stokBaru) {
        String sql = "UPDATE obat SET stok = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, stokBaru);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal update stok obat: " + e.getMessage(), e);
        }
    }

    // Helper method: mapping ResultSet ke objek Obat
    private Obat mapResultSetToObat(ResultSet rs) throws SQLException {
        Obat obat = new Obat();
        obat.setId(rs.getInt("id"));
        obat.setNamaObat(rs.getString("nama_obat"));
        obat.setSatuan(rs.getString("satuan"));
        obat.setHargaSatuan(rs.getDouble("harga_satuan"));
        obat.setStok(rs.getInt("stok"));
        return obat;
    }
}
