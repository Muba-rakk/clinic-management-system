package dao;

import entity.Pendaftaran;
import java.util.List;

public interface PendaftaranDAO {
    void insert(Pendaftaran pendaftaran);
    List<Pendaftaran> findAll();
    Pendaftaran findById(int id);
    List<Pendaftaran> findByStatus(String status);
    void updateStatus(int id, String status);
}
