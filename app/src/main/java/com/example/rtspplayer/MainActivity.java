package com.example.rtspplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.util.MimeTypes;

public class MainActivity extends AppCompatActivity {

    private StyledPlayerView playerView1;
    private StyledPlayerView playerView2;
    private ExoPlayer player1;
    private ExoPlayer player2;
    private TextView statusText;

    // RTSP URLs
    private static final String RTSP_URL_1 = "rtsp://user:onk!user26R@10.21.14.53:554/live/1/1";
    private static final String RTSP_URL_2 = "rtsp://user:onk!user26R@10.21.11.35:554/live/1/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elemanlarını bul
        playerView1 = findViewById(R.id.player_view_1);
        playerView2 = findViewById(R.id.player_view_2);
        statusText = findViewById(R.id.status_text);
        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);

        // Buton dinleyicileri
        startButton.setOnClickListener(v -> startStreaming());
        stopButton.setOnClickListener(v -> stopStreaming());

        // Oynatıcıları hazırla
        initPlayers();
    }

    private void initPlayers() {
        try {
            // İlk oynatıcı
            player1 = new ExoPlayer.Builder(this).build();
            playerView1.setPlayer(player1);

            // İkinci oynatıcı
            player2 = new ExoPlayer.Builder(this).build();
            playerView2.setPlayer(player2);

            statusText.setText("Oynatıcılar hazırlandı. 'Başlat' butonuna basın.");
        } catch (Exception e) {
            statusText.setText("Hata: " + e.getMessage());
        }
    }

    private void startStreaming() {
        try {
            statusText.setText("Bağlanılıyor...");

            // İlk kamerayı oynat
            MediaItem mediaItem1 = MediaItem.fromUri(Uri.parse(RTSP_URL_1));
            player1.setMediaItem(mediaItem1);
            player1.prepare();
            player1.play();

            // İkinci kamerayı oynat
            MediaItem mediaItem2 = MediaItem.fromUri(Uri.parse(RTSP_URL_2));
            player2.setMediaItem(mediaItem2);
            player2.prepare();
            player2.play();

            statusText.setText("Yayınlar oynatılıyor...");
        } catch (Exception e) {
            statusText.setText("Hata: " + e.getMessage());
        }
    }

    private void stopStreaming() {
        try {
            if (player1 != null) {
                player1.stop();
            }
            if (player2 != null) {
                player2.stop();
            }
            statusText.setText("Yayınlar durduruldu.");
        } catch (Exception e) {
            statusText.setText("Hata: " + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer(player1);
        releasePlayer(player2);
    }

    private void releasePlayer(ExoPlayer player) {
        if (player != null) {
            player.release();
        }
    }
}
