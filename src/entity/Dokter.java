package entity;

public class Dokter {
    private int id;
    private String nama;
    private String spesialisasi;
    private String noTelepon;
    private String jadwalPraktek;

    public Dokter() {
    }

    public Dokter(String nama, String spesialisasi, String noTelepon, String jadwalPraktek) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.noTelepon = noTelepon;
        this.jadwalPraktek = jadwalPraktek;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getSpesialisasi() { return spesialisasi; }
    public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getJadwalPraktek() { return jadwalPraktek; }
    public void setJadwalPraktek(String jadwalPraktek) { this.jadwalPraktek = jadwalPraktek; }

    @Override
    public String toString() {
        return "Dokter{id=" + id + ", nama='" + nama + "', spesialisasi='" + spesialisasi +
               "', noTelepon='" + noTelepon + "', jadwalPraktek='" + jadwalPraktek + "'}";
    }
}