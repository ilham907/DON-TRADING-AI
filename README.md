# DON TRADING AI — Android prototype

Tema: hitam elegan dengan aksen emas. Bahasa: Indonesia.

## Yang sudah ada
- Tampilan aplikasi Android native.
- Pemilih screenshot grafik MT5 dari galeri/penyimpanan.
- Pratinjau gambar.
- Pilihan timeframe M1, M5, M15, M30, H1, H4, D1.
- Layar status hasil analisis dan penjelasan bahwa backend AI belum terhubung.

## Yang belum ada
Mesin AI sungguhan belum dihubungkan. Untuk analisis gambar otomatis dibutuhkan backend HTTPS yang menerima gambar, memanggil penyedia AI, lalu mengembalikan ringkasan. Jangan menaruh API key rahasia di dalam APK. Backend dan API key harus disiapkan terpisah.

## Cara membuat APK
1. Buka folder proyek ini di Android Studio versi terbaru.
2. Tunggu Gradle Sync selesai (butuh koneksi internet untuk mengunduh Android Gradle Plugin/SDK bila belum ada).
3. Pilih **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
4. APK debug akan muncul di `app/build/outputs/apk/debug/app-debug.apk`.

Minimum Android: 6.0 (API 23). Compile/target SDK: 35.

## Catatan penggunaan
Prototipe ini belum menghasilkan sinyal trading. Hasil analisis teknikal kelak hanya untuk edukasi dan latihan akun demo, bukan jaminan pergerakan harga atau saran transaksi.
