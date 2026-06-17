package dao;

import entity.DetailPembayaran;
import java.util.List;

public interface DetailPembayaranDAO {
    void insert(DetailPembayaran detail);
    List<DetailPembayaran> findByPembayaranId(int pembayaranId);
}
