package entity;

import java.sql.Date;

public class Pasien {
    private int id;
    private String nama;
    private String alamat;
    private String noTelepon;
    private String tanggalLahir;
    private String jenisKelamin;
    private Date createdAt;

    // Default Constructor
    public Pasien() {
    }

    // Parameterized Constructor (sesuai Structure Design)
    public Pasien(String nama, String alamat, String noTelepon, String tanggalLahir, String jenisKelamin) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Pasien{id=" + id + ", nama='" + nama + "', alamat='" + alamat +
               "', noTelepon='" + noTelepon + "', tanggalLahir='" + tanggalLahir +
               "', jenisKelamin='" + jenisKelamin + "'}";
    }
}