package com.example.miperfil.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/miperfil/utils/PermissionHelper;", "", "()V", "CAMERA_PERMISSION_REQUEST_CODE", "", "STORAGE_PERMISSION_REQUEST_CODE", "hasCameraPermission", "", "activity", "Landroid/app/Activity;", "hasStoragePermission", "app_debug"})
public final class PermissionHelper {
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    public static final int STORAGE_PERMISSION_REQUEST_CODE = 101;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.miperfil.utils.PermissionHelper INSTANCE = null;
    
    private PermissionHelper() {
        super();
    }
    
    public final boolean hasCameraPermission(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
    
    public final boolean hasStoragePermission(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
}