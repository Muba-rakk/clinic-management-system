package dao;

import entity.ResepObat;
import java.util.List;

public interface ResepObatDAO {
    void insert(ResepObat resepObat);
    List<ResepObat> findByResepId(int resepId);
}
