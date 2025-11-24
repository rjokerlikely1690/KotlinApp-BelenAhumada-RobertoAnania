# Add project specific ProGuard rules here.
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**
-keep class com.example.miperfil.data.model.** { *; }
