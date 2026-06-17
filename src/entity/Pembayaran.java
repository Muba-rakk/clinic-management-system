package entity;

import java.util.Date;

public class Pembayaran {
    private int id;
    private int pendaftaranId;
    private Date tanggalBayar;
    private double totalBayar;
    private String metodeBayar;

    public Pembayaran() {
    }

    public Pembayaran(int pendaftaranId, double totalBayar, String metodeBayar) {
        this.pendaftaranId = pendaftaranId;
        this.totalBayar = totalBayar;
        this.metodeBayar = metodeBayar;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPendaftaranId() { return pendaftaranId; }
    public void setPendaftaranId(int pendaftaranId) { this.pendaftaranId = pendaftaranId; }

    public Date getTanggalBayar() { return tanggalBayar; }
    public void setTanggalBayar(Date tanggalBayar) { this.tanggalBayar = tanggalBayar; }

    public double getTotalBayar() { return totalBayar; }
    public void setTotalBayar(double totalBayar) { this.totalBayar = totalBayar; }

    public String getMetodeBayar() { return metodeBayar; }
    public void setMetodeBayar(String metodeBayar) { this.metodeBayar = metodeBayar; }

    @Override
    public String toString() {
        return "Pembayaran{id=" + id + ", pendaftaranId=" + pendaftaranId +
               ", tanggalBayar=" + tanggalBayar + ", totalBayar=" + totalBayar +
               ", metodeBayar='" + metodeBayar + "'}";
    }
}