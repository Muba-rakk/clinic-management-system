package entity;

import java.util.Date;

public class Pendaftaran {
    private int id;
    private int pasienId;
    private int dokterId;
    private Date tanggalDaftar;
    private String keluhan;
    private String status;

    public Pendaftaran() {
    }

    public Pendaftaran(int pasienId, int dokterId, String keluhan) {
        this.pasienId = pasienId;
        this.dokterId = dokterId;
        this.keluhan = keluhan;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPasienId() { return pasienId; }
    public void setPasienId(int pasienId) { this.pasienId = pasienId; }

    public int getDokterId() { return dokterId; }
    public void setDokterId(int dokterId) { this.dokterId = dokterId; }

    public Date getTanggalDaftar() { return tanggalDaftar; }
    public void setTanggalDaftar(Date tanggalDaftar) { this.tanggalDaftar = tanggalDaftar; }

    public String getKeluhan() { return keluhan; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Pendaftaran{id=" + id + ", pasienId=" + pasienId + ", dokterId=" + dokterId +
               ", tanggalDaftar=" + tanggalDaftar + ", keluhan='" + keluhan + "', status='" + status + "'}";
    }
}