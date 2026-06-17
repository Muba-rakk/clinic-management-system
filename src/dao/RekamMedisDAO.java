package dao;

import entity.RekamMedis;
import java.util.List;

public interface RekamMedisDAO {
    void insert(RekamMedis rekamMedis);
    RekamMedis findById(int id);
    RekamMedis findByPendaftaranId(int pendaftaranId);
    List<RekamMedis> findByPasienId(int pasienId);
}
