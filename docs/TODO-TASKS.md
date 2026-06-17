# TODO-TASKS.md — Pembagian Tugas Tim
## Sistem Manajemen Klinik — Tugas UAS PBO

---

## Tim Pengembang

| Nama | Role | Kontribusi Utama |
|------|------|-----------------|
| **Winata** | Team Leader & Service Layer | `KlinikService.java` — logika bisnis, koordinasi tim, review kode |
| **Rahman** | Entity Layer | 9 class entity (POJO) + constructor + getter/setter |
| **Ariq** | DAO Layer | 9 Interface DAO + 9 DAOImpl JDBC |
| **Androni** | MainApp & Config | `DatabaseConfig.java`, `MainApp.java`, `dbklinik.sql`, setup proyek |

---

## Package & File yang Harus Dibuat

```
src/
├── config/
│   └── DatabaseConfig.java               ← ANDRONI
├── entity/
│   ├── Pasien.java                       ← RAHMAN
│   ├── Dokter.java                       ← RAHMAN
│   ├── Obat.java                         ← RAHMAN
│   ├── Pendaftaran.java                  ← RAHMAN
│   ├── RekamMedis.java                   ← RAHMAN
│   ├── Resep.java                        ← RAHMAN
│   ├── ResepObat.java                    ← RAHMAN
│   ├── Pembayaran.java                   ← RAHMAN
│   └── DetailPembayaran.java             ← RAHMAN
├── dao/
│   ├── PasienDAO.java (Interface)        ← ARIQ
│   ├── PasienDAOImpl.java                ← ARIQ
│   ├── DokterDAO.java (Interface)        ← ARIQ
│   ├── DokterDAOImpl.java                ← ARIQ
│   ├── ObatDAO.java (Interface)          ← ARIQ
│   ├── ObatDAOImpl.java                  ← ARIQ
│   ├── PendaftaranDAO.java (Interface)   ← ARIQ
│   ├── PendaftaranDAOImpl.java           ← ARIQ
│   ├── RekamMedisDAO.java (Interface)    ← ARIQ
│   ├── RekamMedisDAOImpl.java            ← ARIQ
│   ├── ResepDAO.java (Interface)         ← ARIQ
│   ├── ResepDAOImpl.java                 ← ARIQ
│   ├── ResepObatDAO.java (Interface)     ← ARIQ
│   ├── ResepObatDAOImpl.java             ← ARIQ
│   ├── PembayaranDAO.java (Interface)    ← ARIQ
│   ├── PembayaranDAOImpl.java            ← ARIQ
│   ├── DetailPembayaranDAO.java (Interface) ← ARIQ
│   └── DetailPembayaranDAOImpl.java      ← ARIQ
├── service/
│   └── KlinikService.java                ← WINATA (TL)
├── MainApp.java                          ← ANDRONI
└── dbklinik.sql                          ← ANDRONI
```

---

## TODO per Anggota

---

### Winata — Service Layer

**File: `src/service/KlinikService.java`**

**Tugas:** Implementasi logika bisnis yang menghubungkan MainApp dengan DAO layer.

```
TODO WINATA-01: Buat class KlinikService dengan constructor injection untuk 9 DAO
TODO WINATA-02: Implementasi method tambahPasien() — validasi nama tidak kosong
TODO WINATA-03: Implementasi method semuaPasien() — delegasi ke pasienDAO.findAll()
TODO WINATA-04: Implementasi method cariPasienById() — return null jika tidak ditemukan
TODO WINATA-05: Implementasi method ubahPasien() — validasi, lalu update
TODO WINATA-06: Implementasi method hapusPasien() — delegasi ke pasienDAO.delete()
TODO WINATA-07: Implementasi method tambahDokter() — validasi
TODO WINATA-08: Implementasi method semuaDokter()
TODO WINATA-09: Implementasi method cariDokterById()
TODO WINATA-10: Implementasi method ubahDokter() & hapusDokter()
TODO WINATA-11: Implementasi method tambahObat() — validasi stok >= 0
TODO WINATA-12: Implementasi method semuaObat() & cariObatById()
TODO WINATA-13: Implementasi method ubahObat() & hapusObat()
TODO WINATA-14: Implementasi method daftarPasien() — buat objek Pendaftaran, insert ke DB
TODO WINATA-15: Implementasi method lihatAntrian() — filter by status
TODO WINATA-16: Implementasi method periksaPasien() — update status pendaftaran → "DIPERIKSA", buat RekamMedis
TODO WINATA-17: Implementasi method lihatRekamMedis() & riwayatPasien()
TODO WINATA-18: Implementasi method buatResep() — buat Resep + looping insert ResepObat + update stok Obat
TODO WINATA-19: Implementasi method prosesPembayaran() — hitung total, insert Pembayaran + DetailPembayaran
TODO WINATA-20: Implementasi method laporanPendapatan() — filter by tanggal
```

**Ketergantungan:** Tunggu DAO dari Ariq selesai. Bisa kerjakan setelah DAO interface selesai (implementasi bisa pakai dummy dulu).

---

### Rahman — Entity Layer

**File: 9 file di folder `src/entity/`**

**Tugas:** Membuat class POJO (Plain Old Java Object) untuk setiap entitas.

```
TODO RAHMAN-01: Buat Pasien.java
  - 7 field private (int id, String nama, String alamat, String noTelepon, String tanggalLahir, String jenisKelamin, Date createdAt)
  - 2 constructor (default + berparameter)
  - Getter & Setter semua field
  - Method toString() untuk debugging

TODO RAHMAN-02: Buat Dokter.java
  - 5 field private (int id, String nama, String spesialisasi, String noTelepon, String jadwalPraktek)
  - 2 constructor (default + berparameter)
  - Getter & Setter

TODO RAHMAN-03: Buat Obat.java
  - 5 field private (int id, String namaObat, String satuan, double hargaSatuan, int stok)
  - 2 constructor
  - Getter & Setter

TODO RAHMAN-04: Buat Pendaftaran.java
  - 6 field private (int id, int pasienId, int dokterId, Date tanggalDaftar, String keluhan, String status)
  - 2 constructor
  - Getter & Setter

TODO RAHMAN-05: Buat RekamMedis.java
  - 8 field private (int id, int pendaftaranId, int dokterId, int pasienId, String diagnosa, String tindakan, String catatanTambahan, Date tanggalPeriksa)
  - 2 constructor
  - Getter & Setter

TODO RAHMAN-06: Buat Resep.java
  - 4 field private (int id, int rekamMedisId, Date tanggalResep, String keterangan)
  - 2 constructor
  - Getter & Setter

TODO RAHMAN-07: Buat ResepObat.java
  - 5 field private (int id, int resepId, int obatId, int jumlah, String aturanPakai)
  - 2 constructor
  - Getter & Setter

TODO RAHMAN-08: Buat Pembayaran.java
  - 5 field private (int id, int pendaftaranId, Date tanggalBayar, double totalBayar, String metodeBayar)
  - 2 constructor
  - Getter & Setter

TODO RAHMAN-09: Buat DetailPembayaran.java
  - 4 field private (int id, int pembayaranId, String deskripsi, double jumlahBiaya)
  - 2 constructor
  - Getter & Setter
```

**Bisa dikerjakan paling awal** karena tidak tergantung anggota lain.

---

### Ariq — DAO Layer

**File: 18 file di folder `src/dao/`**

**Tugas:** Membuat Interface DAO (abstraksi) dan DAOImpl (implementasi JDBC).

```
TODO ARIQ-01: Buat PasienDAO.java (Interface) — method: insert, findAll, findById, update, delete
TODO ARIQ-02: Buat PasienDAOImpl.java (JDBC) — implementasi 5 method dengan PreparedStatement
TODO ARIQ-03: Buat DokterDAO.java (Interface) — method: insert, findAll, findById, update, delete
TODO ARIQ-04: Buat DokterDAOImpl.java (JDBC) — implementasi
TODO ARIQ-05: Buat ObatDAO.java (Interface) — method: insert, findAll, findById, update, delete, updateStok
TODO ARIQ-06: Buat ObatDAOImpl.java (JDBC) — implementasi + method khusus updateStok
TODO ARIQ-07: Buat PendaftaranDAO.java (Interface) — insert, findAll, findById, findByStatus, updateStatus
TODO ARIQ-08: Buat PendaftaranDAOImpl.java (JDBC) — implementasi
TODO ARIQ-09: Buat RekamMedisDAO.java (Interface) — insert, findById, findByPendaftaranId, findByPasienId
TODO ARIQ-10: Buat RekamMedisDAOImpl.java (JDBC) — implementasi
TODO ARIQ-11: Buat ResepDAO.java (Interface) — insert, findById, findByRekamMedisId
TODO ARIQ-12: Buat ResepDAOImpl.java (JDBC) — implementasi
TODO ARIQ-13: Buat ResepObatDAO.java (Interface) — insert, findByResepId
TODO ARIQ-14: Buat ResepObatDAOImpl.java (JDBC) — implementasi
TODO ARIQ-15: Buat PembayaranDAO.java (Interface) — insert, findById, findByPendaftaranId, findByTanggal, findAll
TODO ARIQ-16: Buat PembayaranDAOImpl.java (JDBC) — implementasi
TODO ARIQ-17: Buat DetailPembayaranDAO.java (Interface) — insert, findByPembayaranId
TODO ARIQ-18: Buat DetailPembayaranDAOImpl.java (JDBC) — implementasi
```

**Ketergantungan:** Tunggu entity dari Rahman selesai (agar bisa import class entity dengan benar).

---

### Androni — MainApp & Config

**File: 4 file**

```
TODO ANDRONI-01: Buat .vscode/settings.json — konfigurasi classpath yang mengarah ke lib/
TODO ANDRONI-02: Buat folder lib/ dan download MySQL Connector/J
TODO ANDRONI-03: Buat src/config/DatabaseConfig.java
  - private static final String URL, USER, PASSWORD dari System.getenv()
  - public static Connection getConnection() throws SQLException
  - Gunakan DriverManager.getConnection()

TODO ANDRONI-04: Buat src/dbklinik.sql
  - CREATE DATABASE IF NOT EXISTS dbklinik
  - USE dbklinik
  - 9 tabel CREATE TABLE dengan FOREIGN KEY dan ENGINE=InnoDB
  - INSERT data sample (minimal 3 pasien, 2 dokter, 5 obat)

TODO ANDRONI-05: Buat src/MainApp.java
  - import semua yang diperlukan
  - Di main(): buat Connection via DatabaseConfig
  - Buat semua objek DAOImpl (constructor injection Connection)
  - Buat KlinikService dengan DAO-DAO tersebut
  - Looping menu utama (while) dengan Scanner
  - Menu 1: Manajemen Pasien (sub-menu: tambah, lihat, cari, ubah, hapus)
  - Menu 2: Manajemen Dokter (sub-menu sama)
  - Menu 3: Manajemen Obat (sub-menu sama)
  - Menu 4: Pendaftaran (daftar, lihat antrian)
  - Menu 5: Rekam Medis & Resep (periksa, lihat rekam medis, resep)
  - Menu 6: Pembayaran (proses pembayaran)
  - Menu 7: Laporan (laporan pendapatan)
  - Menu 0: Keluar
  - Error handling: try-catch untuk setiap input user
```

**Ketergantungan:** Tunggu semua layer lain selesai agar MainApp bisa memanggil KlinikService.

---

## Dependency Chain (Urutan Pengerjaan)

```
Fase 1: RAHMAN (Entity)  ← Paling awal, tidak tergantung siapapun
     ↓
Fase 2: ARIQ (DAO)       ← Butuh Entity dari Rahman
     ↓
Fase 3: WINATA (Service) ← Butuh DAO Interface dari Ariq
     ↓
Fase 4: ANDRONI (MainApp)← Butuh Service dari Winata
     ↓
Fase 5: Semua            ← Integrasi & testing bersama
```

---

## Timeline Saran

| Fase | Deadline | PIC | Deliverable |
|------|----------|-----|------------|
| Setup proyek + SQL | H+1 | Androni | dbklinik.sql, settings.json |
| Entity layer | H+2 | Rahman | 9 file entity |
| DAO layer | H+4 | Ariq | 18 file dao (interface + impl) |
| Service layer | H+5 | Winata | KlinikService.java |
| MainApp + integrasi | H+7 | Androni | MainApp.java, testing |
| Review & fix | H+8 | Semua | Bug fixing |
| Presentasi | H+9 | Semua | Demo ke dosen |
