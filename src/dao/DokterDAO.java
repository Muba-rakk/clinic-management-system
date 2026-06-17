package dao;

import entity.Dokter;
import java.util.List;

public interface DokterDAO {
    void insert(Dokter dokter);
    List<Dokter> findAll();
    Dokter findById(int id);
    void update(Dokter dokter);
    void delete(int id);
}
