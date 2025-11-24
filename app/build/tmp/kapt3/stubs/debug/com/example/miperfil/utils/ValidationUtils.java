package com.example.miperfil.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u0012\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\bJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0012\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0013\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0014\u001a\u00020\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/miperfil/utils/ValidationUtils;", "", "()V", "EMAIL_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "PHONE_PATTERN", "getEmailError", "", "email", "getNameError", "name", "getPasswordError", "password", "getPhoneError", "phone", "isValidEmail", "", "isValidName", "isValidPassword", "isValidPhone", "app_debug"})
public final class ValidationUtils {
    private static final java.util.regex.Pattern EMAIL_PATTERN = null;
    private static final java.util.regex.Pattern PHONE_PATTERN = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.miperfil.utils.ValidationUtils INSTANCE = null;
    
    private ValidationUtils() {
        super();
    }
    
    public final boolean isValidEmail(@org.jetbrains.annotations.Nullable()
    java.lang.String email) {
        return false;
    }
    
    public final boolean isValidPassword(@org.jetbrains.annotations.Nullable()
    java.lang.String password) {
        return false;
    }
    
    public final boolean isValidName(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return false;
    }
    
    public final boolean isValidPhone(@org.jetbrains.annotations.Nullable()
    java.lang.String phone) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEmailError(@org.jetbrains.annotations.Nullable()
    java.lang.String email) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPasswordError(@org.jetbrains.annotations.Nullable()
    java.lang.String password) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNameError(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPhoneError(@org.jetbrains.annotations.Nullable()
    java.lang.String phone) {
        return null;
    }
}