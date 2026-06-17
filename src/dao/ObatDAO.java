package dao;

import entity.Obat;
import java.util.List;

public interface ObatDAO {
    void insert(Obat obat);
    List<Obat> findAll();
    Obat findById(int id);
    void update(Obat obat);
    void delete(int id);
    void updateStok(int id, int stokBaru);
}
