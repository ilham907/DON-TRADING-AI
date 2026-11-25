package com.dontradingai.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private static final int PICK_IMAGE = 101;
    private ImageView preview;
    private TextView fileStatus, result;
    private Uri selectedImage;
    private Spinner timeframe;
    private final int bg = Color.rgb(9,10,13), panel = Color.rgb(22,24,29), gold = Color.rgb(214,174,85), white = Color.WHITE, muted = Color.rgb(170,174,184);

    @Override public void onCreate(Bundle saved) {
        super.onCreate(saved);
        ScrollView scroll = new ScrollView(this); scroll.setBackgroundColor(bg);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(20), dp(28), dp(20), dp(28));
        scroll.addView(root);

        TextView brand = new TextView(this); brand.setText("DON TRADING AI"); brand.setTextColor(gold); brand.setTextSize(25); brand.setTypeface(null, Typeface.BOLD); root.addView(brand);
        TextView sub = new TextView(this); sub.setText("CHART ANALYSIS  •  EDUCATION MODE"); sub.setTextColor(muted); sub.setTextSize(11); sub.setPadding(0,dp(5),0,dp(20)); root.addView(sub);

        TextView heading = new TextView(this); heading.setText("Analisis Grafik MT5"); heading.setTextColor(white); heading.setTextSize(21); heading.setTypeface(null,Typeface.BOLD); root.addView(heading);
        TextView desc = new TextView(this); desc.setText("Unggah screenshot grafik untuk menyiapkan analisis teknikal."); desc.setTextColor(muted); desc.setTextSize(14); desc.setPadding(0,dp(7),0,dp(18)); root.addView(desc);

        LinearLayout card = new LinearLayout(this); card.setOrientation(LinearLayout.VERTICAL); card.setPadding(dp(14),dp(14),dp(14),dp(14)); card.setBackground(round(panel,18)); root.addView(card);
        preview = new ImageView(this); preview.setAdjustViewBounds(true); preview.setScaleType(ImageView.ScaleType.FIT_CENTER); preview.setBackground(round(Color.rgb(13,15,19),12)); preview.setMinimumHeight(dp(170)); card.addView(preview,new LinearLayout.LayoutParams(-1,dp(190)));
        fileStatus = new TextView(this); fileStatus.setText("Belum ada screenshot dipilih"); fileStatus.setTextColor(muted); fileStatus.setTextSize(12); fileStatus.setPadding(0,dp(10),0,dp(10)); card.addView(fileStatus);
        Button upload = button("PILIH SCREENSHOT MT5"); card.addView(upload); upload.setOnClickListener(v -> pickImage());

        TextView tfLabel = label("TIMEFRAME GRAFIK"); root.addView(tfLabel);
        timeframe = new Spinner(this); String[] frames = {"M1", "M5", "M15", "M30", "H1", "H4", "D1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, frames) {
            @Override public View getView(int p, View c, android.view.ViewGroup parent) { TextView t=(TextView)super.getView(p,c,parent); t.setTextColor(white); return t; }
        }; timeframe.setAdapter(adapter); timeframe.setBackground(round(panel,12)); root.addView(timeframe,new LinearLayout.LayoutParams(-1,dp(50)));

        Button analyze = button("SIAPKAN ANALISIS"); LinearLayout.LayoutParams ap = new LinearLayout.LayoutParams(-1,dp(52)); ap.topMargin=dp(18); root.addView(analyze,ap);
        result = new TextView(this); result.setText("HASIL ANALISIS\n\nPilih screenshot MT5 terlebih dahulu. Analisis AI belum aktif sampai server AI dihubungkan."); result.setTextColor(white); result.setTextSize(14); result.setPadding(dp(16),dp(16),dp(16),dp(16)); result.setBackground(round(panel,16)); LinearLayout.LayoutParams rp=new LinearLayout.LayoutParams(-1,-2); rp.topMargin=dp(18); root.addView(result,rp);
        analyze.setOnClickListener(v -> {
            if (selectedImage == null) { Toast.makeText(this,"Pilih screenshot MT5 dulu.",Toast.LENGTH_SHORT).show(); return; }
            result.setText("SCREENSHOT SIAP DIPROSES\n\nTimeframe: "+timeframe.getSelectedItem()+"\n\nTahap tampilan aplikasi berhasil. Mesin AI belum terhubung; diperlukan backend aman dan API AI agar sistem dapat membaca tren, support/resistance, candlestick, dan struktur pasar.\n\nJangan gunakan keluaran aplikasi sebagai kepastian arah pasar atau perintah transaksi. Gunakan untuk belajar dan latihan akun demo.");
        });
        TextView foot = new TextView(this); foot.setText("DON TRADING AI  •  Versi prototipe 1.0\nAnalisis bersifat edukasi, bukan saran investasi."); foot.setTextColor(muted); foot.setTextSize(11); foot.setGravity(Gravity.CENTER); foot.setPadding(0,dp(22),0,0); root.addView(foot);
        setContentView(scroll);
    }
    private void pickImage(){ Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT); i.addCategory(Intent.CATEGORY_OPENABLE); i.setType("image/*"); startActivityForResult(i,PICK_IMAGE); }
    @Override protected void onActivityResult(int req,int code,Intent data){ super.onActivityResult(req,code,data); if(req==PICK_IMAGE && code==RESULT_OK && data!=null){ selectedImage=data.getData(); try { getContentResolver().takePersistableUriPermission(selectedImage,Intent.FLAG_GRANT_READ_URI_PERMISSION); } catch(Exception ignored){} preview.setImageURI(selectedImage); fileStatus.setText("Screenshot berhasil dipilih"); } }
    private TextView label(String s){ TextView t=new TextView(this); t.setText(s); t.setTextColor(gold); t.setTextSize(12); t.setTypeface(null,Typeface.BOLD); t.setPadding(2,dp(20),0,dp(8)); return t; }
    private Button button(String s){ Button b=new Button(this); b.setText(s); b.setTextColor(Color.rgb(15,15,15)); b.setTextSize(13); b.setTypeface(null,Typeface.BOLD); b.setAllCaps(false); b.setBackground(round(gold,12)); return b; }
    private android.graphics.drawable.GradientDrawable round(int color,int radius){ android.graphics.drawable.GradientDrawable d=new android.graphics.drawable.GradientDrawable(); d.setColor(color); d.setCornerRadius(dp(radius)); return d; }
    private int dp(int v){ return (int)(v*getResources().getDisplayMetrics().density+0.5f); }
}
