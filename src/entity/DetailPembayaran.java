package entity;

public class DetailPembayaran {
    private int id;
    private int pembayaranId;
    private String deskripsi;
    private double jumlahBiaya;

    public DetailPembayaran() {
    }

    public DetailPembayaran(int pembayaranId, String deskripsi, double jumlahBiaya) {
        this.pembayaranId = pembayaranId;
        this.deskripsi = deskripsi;
        this.jumlahBiaya = jumlahBiaya;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPembayaranId() { return pembayaranId; }
    public void setPembayaranId(int pembayaranId) { this.pembayaranId = pembayaranId; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public double getJumlahBiaya() { return jumlahBiaya; }
    public void setJumlahBiaya(double jumlahBiaya) { this.jumlahBiaya = jumlahBiaya; }

    @Override
    public String toString() {
        return "DetailPembayaran{id=" + id + ", pembayaranId=" + pembayaranId +
               ", deskripsi='" + deskripsi + "', jumlahBiaya=" + jumlahBiaya + "}";
    }
}