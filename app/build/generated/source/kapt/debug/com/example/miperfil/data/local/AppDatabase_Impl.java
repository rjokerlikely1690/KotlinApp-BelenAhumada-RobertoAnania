package com.example.miperfil.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile PetDao _petDao;

  private volatile ProductDao _productDao;

  private volatile VetServiceDao _vetServiceDao;

  private volatile ReservationDao _reservationDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`email` TEXT NOT NULL, `name` TEXT NOT NULL, `password` TEXT NOT NULL, `phone` TEXT NOT NULL, `profileImageUri` TEXT, PRIMARY KEY(`email`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `pets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ownerEmail` TEXT NOT NULL, `name` TEXT NOT NULL, `breed` TEXT NOT NULL, `age` INTEGER NOT NULL, `weight` REAL NOT NULL, `lastVisit` TEXT NOT NULL, `photoUri` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `products` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `category` TEXT NOT NULL, `description` TEXT NOT NULL, `price` REAL NOT NULL, `stock` INTEGER NOT NULL, `imageUrl` TEXT, `featured` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vet_services` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `durationMinutes` INTEGER NOT NULL, `price` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reservations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ownerEmail` TEXT NOT NULL, `petName` TEXT NOT NULL, `serviceName` TEXT NOT NULL, `appointmentDate` INTEGER NOT NULL, `status` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '60f72a4e1b868556e478aa901eb6f158')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `pets`");
        db.execSQL("DROP TABLE IF EXISTS `products`");
        db.execSQL("DROP TABLE IF EXISTS `vet_services`");
        db.execSQL("DROP TABLE IF EXISTS `reservations`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(5);
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("profileImageUri", new TableInfo.Column("profileImageUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.example.miperfil.data.model.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsPets = new HashMap<String, TableInfo.Column>(8);
        _columnsPets.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("ownerEmail", new TableInfo.Column("ownerEmail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("breed", new TableInfo.Column("breed", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("age", new TableInfo.Column("age", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("lastVisit", new TableInfo.Column("lastVisit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPets.put("photoUri", new TableInfo.Column("photoUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPets = new TableInfo("pets", _columnsPets, _foreignKeysPets, _indicesPets);
        final TableInfo _existingPets = TableInfo.read(db, "pets");
        if (!_infoPets.equals(_existingPets)) {
          return new RoomOpenHelper.ValidationResult(false, "pets(com.example.miperfil.data.model.Pet).\n"
                  + " Expected:\n" + _infoPets + "\n"
                  + " Found:\n" + _existingPets);
        }
        final HashMap<String, TableInfo.Column> _columnsProducts = new HashMap<String, TableInfo.Column>(8);
        _columnsProducts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("stock", new TableInfo.Column("stock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("featured", new TableInfo.Column("featured", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProducts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProducts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProducts = new TableInfo("products", _columnsProducts, _foreignKeysProducts, _indicesProducts);
        final TableInfo _existingProducts = TableInfo.read(db, "products");
        if (!_infoProducts.equals(_existingProducts)) {
          return new RoomOpenHelper.ValidationResult(false, "products(com.example.miperfil.data.model.Product).\n"
                  + " Expected:\n" + _infoProducts + "\n"
                  + " Found:\n" + _existingProducts);
        }
        final HashMap<String, TableInfo.Column> _columnsVetServices = new HashMap<String, TableInfo.Column>(5);
        _columnsVetServices.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVetServices.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVetServices.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVetServices.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVetServices.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVetServices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVetServices = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVetServices = new TableInfo("vet_services", _columnsVetServices, _foreignKeysVetServices, _indicesVetServices);
        final TableInfo _existingVetServices = TableInfo.read(db, "vet_services");
        if (!_infoVetServices.equals(_existingVetServices)) {
          return new RoomOpenHelper.ValidationResult(false, "vet_services(com.example.miperfil.data.model.VetService).\n"
                  + " Expected:\n" + _infoVetServices + "\n"
                  + " Found:\n" + _existingVetServices);
        }
        final HashMap<String, TableInfo.Column> _columnsReservations = new HashMap<String, TableInfo.Column>(6);
        _columnsReservations.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("ownerEmail", new TableInfo.Column("ownerEmail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("petName", new TableInfo.Column("petName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("serviceName", new TableInfo.Column("serviceName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("appointmentDate", new TableInfo.Column("appointmentDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReservations.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReservations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReservations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReservations = new TableInfo("reservations", _columnsReservations, _foreignKeysReservations, _indicesReservations);
        final TableInfo _existingReservations = TableInfo.read(db, "reservations");
        if (!_infoReservations.equals(_existingReservations)) {
          return new RoomOpenHelper.ValidationResult(false, "reservations(com.example.miperfil.data.model.Reservation).\n"
                  + " Expected:\n" + _infoReservations + "\n"
                  + " Found:\n" + _existingReservations);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "60f72a4e1b868556e478aa901eb6f158", "26271cd7ab81e5b90f9c76232458808f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","pets","products","vet_services","reservations");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `pets`");
      _db.execSQL("DELETE FROM `products`");
      _db.execSQL("DELETE FROM `vet_services`");
      _db.execSQL("DELETE FROM `reservations`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PetDao.class, PetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductDao.class, ProductDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VetServiceDao.class, VetServiceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReservationDao.class, ReservationDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public PetDao petDao() {
    if (_petDao != null) {
      return _petDao;
    } else {
      synchronized(this) {
        if(_petDao == null) {
          _petDao = new PetDao_Impl(this);
        }
        return _petDao;
      }
    }
  }

  @Override
  public ProductDao productDao() {
    if (_productDao != null) {
      return _productDao;
    } else {
      synchronized(this) {
        if(_productDao == null) {
          _productDao = new ProductDao_Impl(this);
        }
        return _productDao;
      }
    }
  }

  @Override
  public VetServiceDao vetServiceDao() {
    if (_vetServiceDao != null) {
      return _vetServiceDao;
    } else {
      synchronized(this) {
        if(_vetServiceDao == null) {
          _vetServiceDao = new VetServiceDao_Impl(this);
        }
        return _vetServiceDao;
      }
    }
  }

  @Override
  public ReservationDao reservationDao() {
    if (_reservationDao != null) {
      return _reservationDao;
    } else {
      synchronized(this) {
        if(_reservationDao == null) {
          _reservationDao = new ReservationDao_Impl(this);
        }
        return _reservationDao;
      }
    }
  }
}
