package dao;

import entity.Pasien;
import java.util.List;

public interface PasienDAO {
    void insert(Pasien pasien);
    List<Pasien> findAll();
    Pasien findById(int id);
    void update(Pasien pasien);
    void delete(int id);
}
