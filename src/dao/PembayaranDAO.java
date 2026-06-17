package dao;

import entity.Pembayaran;
import java.util.List;
import java.util.Date;

public interface PembayaranDAO {
    void insert(Pembayaran pembayaran);
    Pembayaran findById(int id);
    Pembayaran findByPendaftaranId(int pendaftaranId);
    List<Pembayaran> findByTanggal(Date start, Date end);
    List<Pembayaran> findAll();
}
