package entity;

import java.util.Date;

public class Resep {
    private int id;
    private int rekamMedisId;
    private Date tanggalResep;
    private String keterangan;

    public Resep() {
    }

    public Resep(int rekamMedisId, String keterangan) {
        this.rekamMedisId = rekamMedisId;
        this.keterangan = keterangan;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRekamMedisId() { return rekamMedisId; }
    public void setRekamMedisId(int rekamMedisId) { this.rekamMedisId = rekamMedisId; }

    public Date getTanggalResep() { return tanggalResep; }
    public void setTanggalResep(Date tanggalResep) { this.tanggalResep = tanggalResep; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    @Override
    public String toString() {
        return "Resep{id=" + id + ", rekamMedisId=" + rekamMedisId +
               ", tanggalResep=" + tanggalResep + ", keterangan='" + keterangan + "'}";
    }
}