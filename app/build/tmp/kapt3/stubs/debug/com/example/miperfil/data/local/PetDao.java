package com.example.miperfil.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/example/miperfil/data/local/PetDao;", "", "deletePet", "", "pet", "Lcom/example/miperfil/data/model/Pet;", "(Lcom/example/miperfil/data/model/Pet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPetsForOwner", "Lkotlinx/coroutines/flow/Flow;", "", "ownerEmail", "", "insertPet", "updatePet", "app_debug"})
@androidx.room.Dao()
public abstract interface PetDao {
    
    @androidx.room.Query(value = "SELECT * FROM pets WHERE ownerEmail = :ownerEmail ORDER BY name")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.Pet>> getPetsForOwner(@org.jetbrains.annotations.NotNull()
    java.lang.String ownerEmail);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertPet(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Pet pet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePet(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Pet pet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePet(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Pet pet, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}