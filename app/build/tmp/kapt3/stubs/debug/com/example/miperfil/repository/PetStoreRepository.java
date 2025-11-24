package com.example.miperfil.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e0\u001d2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001e0\u001dJ\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u001e0\u001dJ\u001c\u0010#\u001a\u00020\f2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u001eH\u0086@\u00a2\u0006\u0002\u0010%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/example/miperfil/repository/PetStoreRepository;", "", "petDao", "Lcom/example/miperfil/data/local/PetDao;", "productDao", "Lcom/example/miperfil/data/local/ProductDao;", "reservationDao", "Lcom/example/miperfil/data/local/ReservationDao;", "vetServiceDao", "Lcom/example/miperfil/data/local/VetServiceDao;", "(Lcom/example/miperfil/data/local/PetDao;Lcom/example/miperfil/data/local/ProductDao;Lcom/example/miperfil/data/local/ReservationDao;Lcom/example/miperfil/data/local/VetServiceDao;)V", "addPet", "", "pet", "Lcom/example/miperfil/data/model/Pet;", "(Lcom/example/miperfil/data/model/Pet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addProduct", "product", "Lcom/example/miperfil/data/model/Product;", "(Lcom/example/miperfil/data/model/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addReservation", "reservation", "Lcom/example/miperfil/data/model/Reservation;", "(Lcom/example/miperfil/data/model/Reservation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureSeedData", "ownerEmail", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPets", "Lkotlinx/coroutines/flow/Flow;", "", "getProducts", "getReservations", "getServices", "Lcom/example/miperfil/data/model/VetService;", "saveServices", "services", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PetStoreRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.miperfil.data.local.PetDao petDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.miperfil.data.local.ProductDao productDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.miperfil.data.local.ReservationDao reservationDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.miperfil.data.local.VetServiceDao vetServiceDao = null;
    
    public PetStoreRepository(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.local.PetDao petDao, @org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.local.ProductDao productDao, @org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.local.ReservationDao reservationDao, @org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.local.VetServiceDao vetServiceDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.Pet>> getPets(@org.jetbrains.annotations.NotNull()
    java.lang.String ownerEmail) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addPet(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Pet pet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.Product>> getProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addProduct(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.Reservation>> getReservations(@org.jetbrains.annotations.NotNull()
    java.lang.String ownerEmail) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addReservation(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Reservation reservation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.VetService>> getServices() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveServices(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.miperfil.data.model.VetService> services, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureSeedData(@org.jetbrains.annotations.NotNull()
    java.lang.String ownerEmail, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}