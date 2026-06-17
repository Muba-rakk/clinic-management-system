# AGENTS.md — Panduan Kerja Tim
## Sistem Manajemen Klinik — Tugas UAS PBO

---

Dokumen ini berisi aturan main, standar coding, dan workflow yang harus dipatuhi oleh seluruh anggota tim. **Bacalah dokumen ini sebelum mulai coding.**

---

## 1. Struktur Folder Proyek

```
clinic-management-system/
├── src/
│   ├── config/
│   │   └── DatabaseConfig.java           # KONEKSI DATABASE
│   ├── entity/
│   │   ├── Pasien.java
│   │   ├── Dokter.java
│   │   ├── Obat.java
│   │   ├── Pendaftaran.java
│   │   ├── RekamMedis.java
│   │   ├── Resep.java
│   │   ├── ResepObat.java
│   │   ├── Pembayaran.java
│   │   └── DetailPembayaran.java
│   ├── dao/
│   │   ├── PasienDAO.java                # INTERFACE
│   │   ├── PasienDAOImpl.java            # IMPLEMENTASI JDBC
│   │   ├── DokterDAO.java
│   │   ├── DokterDAOImpl.java
│   │   ├── ObatDAO.java
│   │   ├── ObatDAOImpl.java
│   │   ├── PendaftaranDAO.java
│   │   ├── PendaftaranDAOImpl.java
│   │   ├── RekamMedisDAO.java
│   │   ├── RekamMedisDAOImpl.java
│   │   ├── ResepDAO.java
│   │   ├── ResepDAOImpl.java
│   │   ├── ResepObatDAO.java
│   │   ├── ResepObatDAOImpl.java
│   │   ├── PembayaranDAO.java
│   │   ├── PembayaranDAOImpl.java
│   │   ├── DetailPembayaranDAO.java
│   │   └── DetailPembayaranDAOImpl.java
│   ├── service/
│   │   └── KlinikService.java
│   ├── MainApp.java                      # TITIK MASUK APLIKASI
│   └── dbklinik.sql                      # SCRIPT DATABASE
├── lib/
│   └── (letakkan mysql-connector-j-*.jar di sini)
├── docs/
│   ├── PRD.md
│   ├── AGENTS.md
│   ├── STRUCTURE-DESIGN.md
│   ├── TODO-TASKS.md
│   └── TODO-SPECS.md
└── .vscode/
    └── settings.json
```

---

## 2. Standar Penulisan Kode (Code Conventions)

### 2.1 Naming Conventions

| Elemen | Aturan | Contoh |
|--------|--------|--------|
| **Class** | PascalCase, noun | `Pasien`, `KlinikService` |
| **Interface** | PascalCase, diakhiri `DAO` | `PasienDAO`, `ObatDAO` |
| **Method** | camelCase, verb | `findAll()`, `daftarPasien()` |
| **Variable** | camelCase | `namaPasien`, `totalBayar` |
| **Parameter** | camelCase | `int pasienId`, `String keluhan` |
| **Package** | lowercase | `entity`, `dao`, `service`, `config` |
| **Konstanta** | UPPER_SNAKE_CASE | `DB_URL`, `MAX_STOK` |

### 2.2 Access Modifier Rules

| Anggota Class | Modifier | Alasan |
|---------------|----------|--------|
| **Field (attribute)** | `private` | Encapsulation — tidak bisa diakses langsung dari luar |
| **Constructor** | `public` | Bisa dipanggil untuk membuat objek |
| **Getter/Setter** | `public` | Akses terkontrol ke field private |
| **Method DAO Interface** | `public` (implisit) | Kontrak publik untuk service |
| **Method Service** | `public` | Bisa dipanggil dari MainApp |
| **Method helper/utility** | `private` | Hanya dipakai di dalam class |

### 2.3 Aturan Encapsulation

```java
// BENAR - Encapsulation dengan private field + public getter/setter
public class Pasien {
    private int id;
    private String nama;

    public Pasien() {}

    public Pasien(String nama) {
        this.nama = nama;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}

// SALAH - Field public, tidak ada encapsulation
public class Pasien {
    public int id;
    public String nama;
}
```

---

## 3. Aturan SOLID — WAJIB DIPATUHI

### S — Single Responsibility Principle (SRP)

**Aturan:**
- **Entity class** hanya berisi field, constructor, getter, setter. **TIDAK BOLEH** ada kode JDBC atau SQL di dalamnya.
- **DAOImpl** hanya berisi kode SQL dan JDBC. **TIDAK BOLEH** ada logika bisnis (validasi, perhitungan).
- **Service** hanya berisi logika bisnis. **TIDAK BOLEH** ada koneksi database langsung.
- **MainApp** hanya berisi menu UI (cetak teks, baca input). **TIDAK BOLEH** ada SQL.

```java
// BENAR - Entity tidak tahu menahu tentang database
public class Pasien {
    private int id;
    private String nama;
    // getter/setter saja
}

// SALAH - Entity mencampur data dengan kode SQL
public class Pasien {
    private String nama;
    public void simpanKeDatabase() {
        // KODE SQL DI ENTITY = PELANGGARAN SRP!
    }
}
```

### O — Open/Closed Principle

- Kelas harus terbuka untuk ekstensi, tertutup untuk modifikasi.
- Jika ingin menambah fitur baru (misal export ke Excel), buat class baru, jangan modifikasi class yang sudah ada.

### L — Liskov Substitution Principle

- Jika ada subclass dari DAOImpl (misal `PasienDAOImplPostgre`), harus bisa menggantikan `PasienDAOImpl` tanpa mengubah behavior.

### I — Interface Segregation Principle

- Interface DAO hanya berisi method yang relevan untuk entity tersebut.
- Tidak perlu method `findAll()` di `DetailPembayaranDAO` jika tidak pernah digunakan.

### D — Dependency Inversion Principle (DIP)

**WAJIB**: Service harus bergantung pada **Interface DAO**, bukan pada implementasi konkret DAOImpl.

```java
// BENAR - Bergantung pada abstraksi (interface)
public class KlinikService {
    private PasienDAO pasienDAO;  // Interface!

    public KlinikService(PasienDAO pasienDAO) {  // Constructor Injection
        this.pasienDAO = pasienDAO;  // Terima dari luar, bukan new sendiri!
    }
}

// SALAH - Bergantung pada konkret (implementasi)
public class KlinikService {
    private PasienDAOImpl pasienDAO;  // Implementasi konkret!

    public KlinikService() {
        this.pasienDAO = new PasienDAOImpl();  // new sendiri = tight coupling!
    }
}
```

---

## 4. Aturan JDBC & Keamanan Database

### 4.1 WAJIB: PreparedStatement

Semua query SQL yang menerima input **WAJIB** menggunakan `PreparedStatement`. Dilarang menggunakan `Statement` untuk input dinamis.

```java
// BENAR - PreparedStatement (AMAN)
String sql = "INSERT INTO pasien (nama, alamat) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setString(1, pasien.getNama());
    ps.setString(2, pasien.getAlamat());
    ps.executeUpdate();
}

// SALAH - Concatenation string (RAWAN SQL INJECTION)
String sql = "INSERT INTO pasien VALUES ('" + inputNama + "', '" + inputAlamat + "')";
Statement st = conn.createStatement();
st.executeUpdate(sql);  // BERBAHAYA!
```

### 4.2 WAJIB: try-with-resources

Semua operasi JDBC yang membuka `Connection`, `PreparedStatement`, atau `ResultSet` **WAJIB** menggunakan `try-with-resources`.

```java
// BENAR - Resource otomatis ditutup
try (Connection conn = DatabaseConfig.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    // ...
} catch (SQLException e) {
    e.printStackTrace();
}

// SALAH - Resource tidak ditutup otomatis
Connection conn = null;
try {
    conn = DatabaseConfig.getConnection();
    // ...
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    conn.close();  // Rawan lupa, dan jika exception di close, tidak ketangkap
}
```

### 4.3 WAJIB: File .env atau Environment Variable

**DILARANG KERAS** menuliskan username/password database langsung di dalam kode Java.

Kredensial bisa dibaca dari dua sumber (prioritas: file .env dulu, baru System.getenv):

**Opsi A — File `.env` (lebih praktis):**
Copy file `.env.example` ke `.env`, lalu isi kredensial:
```
# .env
DB_URL=jdbc:mysql://localhost:3306/dbklinik
DB_USER=root
DB_PASSWORD=
```

Metode ini otomatis dibaca oleh `DatabaseConfig.java` saat program dijalankan.

**Opsi B — Environment Variable OS (alternatif):**
Set sebelum menjalankan program:
```bash
export DB_URL="jdbc:mysql://localhost:3306/dbklinik"
export DB_USER="root"
export DB_PASSWORD=""
```

```java
// BENAR — Dibaca dari .env atau System.getenv(), tidak hardcode
public class DatabaseConfig {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        bacaFileEnv();  // coba .env dulu
        if (URL == null) URL = System.getenv("DB_URL");
        if (USER == null) USER = System.getenv("DB_USER");
        if (PASSWORD == null) PASSWORD = System.getenv("DB_PASSWORD");
    }
}

// SALAH — Hardcode kredensial di source code
public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/dbklinik";
    private static final String USER = "root";        // BERBAHAYA!
    private static final String PASSWORD = "";         // BERBAHAYA!
}
```

### 4.4 Cara Menjalankan dengan .env

Cukup copy `.env.example` ke `.env`, isi sesuai kredensial MySQL lokal, lalu jalankan program. `DatabaseConfig` akan membaca file `.env` secara otomatis.

```bash
# 1. Copy file contoh
cp .env.example .env

# 2. Edit .env (isi password MySQL kamu)
nano .env

# 3. Compile & run — DatabaseConfig baca otomatis dari .env
javac -d bin -cp "lib/mysql-connector-j-8.0.33.jar" src/**/*.java
java -cp "bin:lib/mysql-connector-j-8.0.33.jar" MainApp
```

---

## 5. Arsitektur Berlapis (Layered Architecture)

```
MainApp.java                      ← Layer 1: PRESENTATION (UI Console)
     ↓  memanggil method
KlinikService.java                ← Layer 2: SERVICE (Business Logic)
     ↓  memanggil method interface
PasienDAO (Interface)             ← Layer 3: DAO (Abstraction)
     ↓  diimplementasi oleh
PasienDAOImpl.java                ← Layer 4: DATA ACCESS (JDBC / SQL)
     ↓  mengirim query
MySQL Database                    ← Layer 5: DATABASE
```

**Aturan Dependensi:**
- MainApp → Service (boleh)
- MainApp → DAO (JANGAN — lewati Service)
- Service → Interface DAO (boleh)
- Service → DAOImpl (JANGAN — harus lewat interface)
- Entity → semua layer (boleh, entity dipakai di mana-mana)

---

## 6. Panduan Penggunaan Git

### 6.1 Inisialisasi Proyek

```bash
git init
git add .
git commit -m "chore: initial project setup with docs and structure"
git remote add origin <url-repo-github>
git branch -M main
git push -u origin main
```

### 6.2 Branch Strategy

| Branch | Kegunaan |
|--------|----------|
| `main` | Kode yang sudah stabil dan sudah direview |
| `dev` | Integrasi kode dari semua anggota |

### 6.3 Workflow Harian

```bash
# 1. Ambil update terbaru
git pull origin main

# 2. Buat branch fitur sendiri
git checkout -b feat/entity-rahman

# 3. Coding... coding... coding...

# 4. Stage file yang sudah selesai
git add src/entity/Pasien.java

# 5. Commit dengan pesan yang jelas
git commit -m "feat: add Pasien entity class with encapsulation"

# 6. Push branch
git push -u origin feat/entity-rahman

# 7. Buat Pull Request ke branch dev atau main
```

### 6.4 Format Commit Message

```
<type>: <deskripsi singkat>

Type:
- feat:   Fitur baru (misal: feat: add Pasien entity)
- fix:    Perbaikan bug (misal: fix: fix SQL syntax in PasienDAOImpl)
- docs:   Perubahan dokumentasi (misal: docs: update PRD with acceptance criteria)
- chore:  Setup project, konfigurasi (misal: chore: add .vscode settings)
- refactor: Perubahan kode tanpa mengubah fungsionalitas
```

---

## 7. Proses Review Antar Anggota

1. **Sebelum commit**, cek sendiri:
   - Apakah ada `System.out.println` yang tertinggal? (Kecuali untuk debugging)
   - Apakah PreparedStatement sudah dipakai?
   - Apakah ada field yang tidak sengaja public?
   - Apakah kode bisa di-compile?

2. **Setelah selesai per layer**, kumpulkan dan review bersama:
   - Winata (TL) review semua kode
   - Pastikan method di DAO cocok dengan yang dipanggil di Service
   - Pastikan nama method dan parameter konsisten

---

## 8. Checklist Sebelum Push ke main

- [ ] Semua file bisa di-compile tanpa error
- [ ] Tidak ada hardcode kredensial database
- [ ] Semua query menggunakan PreparedStatement
- [ ] Semua resource JDBC menggunakan try-with-resources
- [ ] Semua field entity menggunakan `private`
- [ ] Service menggunakan Interface DAO (bukan implementasi)
- [ ] Tidak ada SQL di Entity class
- [ ] Tidak ada code yang di-comment (kecuali memang sengaja)
- [ ] Pesan commit sudah sesuai format
