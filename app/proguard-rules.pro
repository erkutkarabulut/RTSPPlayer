# ExoPlayer ProGuard rules
-keep class com.google.android.exoplayer2.** { *; }
-dontwarn com.google.android.exoplayer2.**

# AndroidX ProGuard rules
-keep class androidx.** { *; }
-dontwarn androidx.**

# Main application rules
-keep class com.example.rtspplayer.** { *; }
-dontwarn com.example.rtspplayer.**

# Keep activity names
-keep public class com.example.rtspplayer.MainActivity { public *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
