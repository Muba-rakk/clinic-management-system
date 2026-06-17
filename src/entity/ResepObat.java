package entity;

public class ResepObat {
    private int id;
    private int resepId;
    private int obatId;
    private int jumlah;
    private String aturanPakai;

    public ResepObat() {
    }

    public ResepObat(int resepId, int obatId, int jumlah, String aturanPakai) {
        this.resepId = resepId;
        this.obatId = obatId;
        this.jumlah = jumlah;
        this.aturanPakai = aturanPakai;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getResepId() { return resepId; }
    public void setResepId(int resepId) { this.resepId = resepId; }

    public int getObatId() { return obatId; }
    public void setObatId(int obatId) { this.obatId = obatId; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public String getAturanPakai() { return aturanPakai; }
    public void setAturanPakai(String aturanPakai) { this.aturanPakai = aturanPakai; }
}