package entity;

import java.util.Date;

public class RekamMedis {
    private int id;
    private int pendaftaranId;
    private int dokterId;
    private int pasienId;
    private String diagnosa;
    private String tindakan;
    private String catatanTambahan;
    private Date tanggalPeriksa;

    public RekamMedis() {
    }

    public RekamMedis(int pendaftaranId, int dokterId, int pasienId, String diagnosa, String tindakan) {
        this.pendaftaranId = pendaftaranId;
        this.dokterId = dokterId;
        this.pasienId = pasienId;
        this.diagnosa = diagnosa;
        this.tindakan = tindakan;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPendaftaranId() { return pendaftaranId; }
    public void setPendaftaranId(int pendaftaranId) { this.pendaftaranId = pendaftaranId; }

    public int getDokterId() { return dokterId; }
    public void setDokterId(int dokterId) { this.dokterId = dokterId; }

    public int getPasienId() { return pasienId; }
    public void setPasienId(int pasienId) { this.pasienId = pasienId; }

    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { this.diagnosa = diagnosa; }

    public String getTindakan() { return tindakan; }
    public void setTindakan(String tindakan) { this.tindakan = tindakan; }

    public String getCatatanTambahan() { return catatanTambahan; }
    public void setCatatanTambahan(String catatanTambahan) { this.catatanTambahan = catatanTambahan; }

    public Date getTanggalPeriksa() { return tanggalPeriksa; }
    public void setTanggalPeriksa(Date tanggalPeriksa) { this.tanggalPeriksa = tanggalPeriksa; }

    @Override
    public String toString() {
        return "RekamMedis{id=" + id + ", pendaftaranId=" + pendaftaranId + ", dokterId=" + dokterId +
               ", pasienId=" + pasienId + ", diagnosa='" + diagnosa + "', tindakan='" + tindakan +
               "', catatanTambahan='" + catatanTambahan + "'}";
    }
}