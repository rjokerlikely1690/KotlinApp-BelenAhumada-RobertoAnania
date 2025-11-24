package com.example.miperfil.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.miperfil.data.model.Pet;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PetDao_Impl implements PetDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Pet> __insertionAdapterOfPet;

  private final EntityDeletionOrUpdateAdapter<Pet> __deletionAdapterOfPet;

  private final EntityDeletionOrUpdateAdapter<Pet> __updateAdapterOfPet;

  public PetDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPet = new EntityInsertionAdapter<Pet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `pets` (`id`,`ownerEmail`,`name`,`breed`,`age`,`weight`,`lastVisit`,`photoUri`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pet entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOwnerEmail() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOwnerEmail());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getBreed() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBreed());
        }
        statement.bindLong(5, entity.getAge());
        statement.bindDouble(6, entity.getWeight());
        if (entity.getLastVisit() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getLastVisit());
        }
        if (entity.getPhotoUri() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPhotoUri());
        }
      }
    };
    this.__deletionAdapterOfPet = new EntityDeletionOrUpdateAdapter<Pet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `pets` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pet entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPet = new EntityDeletionOrUpdateAdapter<Pet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `pets` SET `id` = ?,`ownerEmail` = ?,`name` = ?,`breed` = ?,`age` = ?,`weight` = ?,`lastVisit` = ?,`photoUri` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pet entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOwnerEmail() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOwnerEmail());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getBreed() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBreed());
        }
        statement.bindLong(5, entity.getAge());
        statement.bindDouble(6, entity.getWeight());
        if (entity.getLastVisit() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getLastVisit());
        }
        if (entity.getPhotoUri() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPhotoUri());
        }
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertPet(final Pet pet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPet.insert(pet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePet(final Pet pet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPet.handle(pet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePet(final Pet pet, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPet.handle(pet);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Pet>> getPetsForOwner(final String ownerEmail) {
    final String _sql = "SELECT * FROM pets WHERE ownerEmail = ? ORDER BY name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ownerEmail == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ownerEmail);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pets"}, new Callable<List<Pet>>() {
      @Override
      @NonNull
      public List<Pet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwnerEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerEmail");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBreed = CursorUtil.getColumnIndexOrThrow(_cursor, "breed");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfLastVisit = CursorUtil.getColumnIndexOrThrow(_cursor, "lastVisit");
          final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photoUri");
          final List<Pet> _result = new ArrayList<Pet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Pet _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwnerEmail;
            if (_cursor.isNull(_cursorIndexOfOwnerEmail)) {
              _tmpOwnerEmail = null;
            } else {
              _tmpOwnerEmail = _cursor.getString(_cursorIndexOfOwnerEmail);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBreed;
            if (_cursor.isNull(_cursorIndexOfBreed)) {
              _tmpBreed = null;
            } else {
              _tmpBreed = _cursor.getString(_cursorIndexOfBreed);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final double _tmpWeight;
            _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            final String _tmpLastVisit;
            if (_cursor.isNull(_cursorIndexOfLastVisit)) {
              _tmpLastVisit = null;
            } else {
              _tmpLastVisit = _cursor.getString(_cursorIndexOfLastVisit);
            }
            final String _tmpPhotoUri;
            if (_cursor.isNull(_cursorIndexOfPhotoUri)) {
              _tmpPhotoUri = null;
            } else {
              _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
            }
            _item = new Pet(_tmpId,_tmpOwnerEmail,_tmpName,_tmpBreed,_tmpAge,_tmpWeight,_tmpLastVisit,_tmpPhotoUri);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
