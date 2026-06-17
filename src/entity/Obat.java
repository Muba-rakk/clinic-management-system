package entity;

public class Obat {
    private int id;
    private String namaObat;
    private String satuan;
    private double hargaSatuan;
    private int stok;

    public Obat() {
    }

    public Obat(String namaObat, String satuan, double hargaSatuan, int stok) {
        this.namaObat = namaObat;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.stok = stok;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaObat() { return namaObat; }
    public void setNamaObat(String namaObat) { this.namaObat = namaObat; }

    public String getSatuan() { return satuan; }
    public void setSatuan(String satuan) { this.satuan = satuan; }

    public double getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(double hargaSatuan) { this.hargaSatuan = hargaSatuan; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    @Override
    public String toString() {
        return "Obat{id=" + id + ", namaObat='" + namaObat + "', satuan='" + satuan +
               "', hargaSatuan=" + hargaSatuan + ", stok=" + stok + "}";
    }
}