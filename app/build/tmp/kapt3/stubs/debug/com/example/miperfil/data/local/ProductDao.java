package com.example.miperfil.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\'J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/example/miperfil/data/local/ProductDao;", "", "getProductCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/miperfil/data/model/Product;", "insertProduct", "", "product", "(Lcom/example/miperfil/data/model/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertProducts", "products", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProduct", "app_debug"})
@androidx.room.Dao()
public abstract interface ProductDao {
    
    @androidx.room.Query(value = "SELECT * FROM products ORDER BY featured DESC, title")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.miperfil.data.model.Product>> getProductsFlow();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertProducts(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.miperfil.data.model.Product> products, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertProduct(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProduct(@org.jetbrains.annotations.NotNull()
    com.example.miperfil.data.model.Product product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProductCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}