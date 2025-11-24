package com.example.miperfil.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/example/miperfil/data/local/ReservationDao;", "", "getReservationsForOwner", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/miperfil/data/model/Reservation;", "ownerEmail", "", "insertReservation", "", "reservation", "(Lcom/example/miperfil/data/model/Reservation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReservations", "reservations", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ReservationDao {
    
    @androidx.room.Query(value = "SELECT * FROM reservations WHERE ownerEmail = :ownerEmail ORDER BY appointmentDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.Reservation>> getReservationsForOwner(@org.jetbrains.annotations.NotNull()
    java.lang.String ownerEmail);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReservation(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Reservation reservation, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReservations(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.miperfil.data.model.Reservation> reservations, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}