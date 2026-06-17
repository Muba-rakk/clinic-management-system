package dao;

import entity.Resep;
import java.util.List;

public interface ResepDAO {
    void insert(Resep resep);
    Resep findById(int id);
    List<Resep> findByRekamMedisId(int rekamMedisId);
}
