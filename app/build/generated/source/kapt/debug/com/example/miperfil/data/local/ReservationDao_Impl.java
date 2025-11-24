package com.example.miperfil.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.miperfil.data.model.Reservation;
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
public final class ReservationDao_Impl implements ReservationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Reservation> __insertionAdapterOfReservation;

  public ReservationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReservation = new EntityInsertionAdapter<Reservation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `reservations` (`id`,`ownerEmail`,`petName`,`serviceName`,`appointmentDate`,`status`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Reservation entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getOwnerEmail() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getOwnerEmail());
        }
        if (entity.getPetName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPetName());
        }
        if (entity.getServiceName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getServiceName());
        }
        statement.bindLong(5, entity.getAppointmentDate());
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
      }
    };
  }

  @Override
  public Object insertReservation(final Reservation reservation,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReservation.insert(reservation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertReservations(final List<Reservation> reservations,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReservation.insert(reservations);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Reservation>> getReservationsForOwner(final String ownerEmail) {
    final String _sql = "SELECT * FROM reservations WHERE ownerEmail = ? ORDER BY appointmentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ownerEmail == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ownerEmail);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reservations"}, new Callable<List<Reservation>>() {
      @Override
      @NonNull
      public List<Reservation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOwnerEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerEmail");
          final int _cursorIndexOfPetName = CursorUtil.getColumnIndexOrThrow(_cursor, "petName");
          final int _cursorIndexOfServiceName = CursorUtil.getColumnIndexOrThrow(_cursor, "serviceName");
          final int _cursorIndexOfAppointmentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "appointmentDate");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<Reservation> _result = new ArrayList<Reservation>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Reservation _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOwnerEmail;
            if (_cursor.isNull(_cursorIndexOfOwnerEmail)) {
              _tmpOwnerEmail = null;
            } else {
              _tmpOwnerEmail = _cursor.getString(_cursorIndexOfOwnerEmail);
            }
            final String _tmpPetName;
            if (_cursor.isNull(_cursorIndexOfPetName)) {
              _tmpPetName = null;
            } else {
              _tmpPetName = _cursor.getString(_cursorIndexOfPetName);
            }
            final String _tmpServiceName;
            if (_cursor.isNull(_cursorIndexOfServiceName)) {
              _tmpServiceName = null;
            } else {
              _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
            }
            final long _tmpAppointmentDate;
            _tmpAppointmentDate = _cursor.getLong(_cursorIndexOfAppointmentDate);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new Reservation(_tmpId,_tmpOwnerEmail,_tmpPetName,_tmpServiceName,_tmpAppointmentDate,_tmpStatus);
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
