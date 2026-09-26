#!/bin/bash

echo "=========================================="
echo "RTSP Player APK Oluşturuluyor..."
echo "=========================================="

# Dizine git
cd "$(dirname "$0")"

# Gradle wrapper indir (ilk kez)
if [ ! -f "gradlew" ]; then
    echo "📥 Gradle wrapper indiriliyor..."
    chmod +x gradle/wrapper/gradle-wrapper.sh
fi

# SDK path'i ayarla (Android Studio kurulu ise)
export ANDROID_HOME=$HOME/Android/Sdk

# Check if ANDROID_HOME exists
if [ ! -d "$ANDROID_HOME" ]; then
    echo "❌ HATA: Android SDK bulunamadı!"
    echo "Lütfen Android Studio'yu kurun: https://developer.android.com/studio"
    exit 1
fi

echo "✅ Android SDK bulundu: $ANDROID_HOME"

# Build yapılandırmasını kontrol et
echo "🔧 Proje kontrol ediliyor..."

# Debug APK oluştur
echo "🏗️  APK oluşturuluyor (Debug)..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo "=========================================="
    echo "✅ APK BAŞARIYLA OLUŞTURULDU!"
    echo "=========================================="
    echo "Konum: app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "Cihaza yüklemek için:"
    echo "adb install -r app/build/outputs/apk/debug/app-debug.apk"
else
    echo "❌ APK oluşturulamadı!"
    exit 1
fi
