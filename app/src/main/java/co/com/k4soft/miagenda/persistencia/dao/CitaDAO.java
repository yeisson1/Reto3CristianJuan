package co.com.k4soft.miagenda.persistencia.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.entitiy.Persona;

@Dao
public interface CitaDAO {

    @Insert
    void create(Cita cita);

    @Query("SELECT * FROM cita c")
    LiveData<List<Cita>> findAll();
    @Query("SELECT * FROM cita where estado =:estado")
    Cita findByEstado(String estado);
    @Query("UPDATE cita SET estado='Cerrada' where estado ='Activo'")
    Cita findUpateEstado(String estado);

}
