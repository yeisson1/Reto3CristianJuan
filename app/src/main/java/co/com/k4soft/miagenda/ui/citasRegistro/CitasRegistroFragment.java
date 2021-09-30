package co.com.k4soft.miagenda.ui.citasRegistro;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import co.com.k4soft.miagenda.R;
import co.com.k4soft.miagenda.databinding.FragmentRegistroBinding;
import co.com.k4soft.miagenda.entitiy.Cita;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import co.com.k4soft.miagenda.persistencia.room.DataBaseHelper;

public class CitasRegistroFragment extends  Fragment{

    private CitaRegistroViewModel citaRegistroViewModel;
    private @NonNull FragmentRegistroBinding binding;
    private Cita cita;

    private EditText cidPersona;
    private EditText fecha;
    private EditText estado;
    private EditText observacion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       citaRegistroViewModel =
                new ViewModelProvider(this).get(CitaRegistroViewModel.class);

        binding = FragmentRegistroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        cidPersona = binding.txtcidPersona;
        fecha = binding.txtfecha;
        estado = binding.txtestado;
        observacion= binding.txtxobservacion;

        final Button btnAceptar = binding.btnAceptarr;
        cita = new Cita();

        citaRegistroViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        btnAceptar.setOnClickListener( v-> {
            cita.setIdPersona(cidPersona.getText().toString());
            cita.setFecha(fecha.getText().toString());
            cita.setEstado(estado.getText().toString());
            cita.setObservacion(observacion.getText().toString());
            Cita citaConsultada = DataBaseHelper.getDBMainThread(getContext()).getCitaDAO().findByEstado(cita.getEstado());
            if(citaConsultada == null){
                insertarInformacion();
                Toast.makeText(getContext(),getText(R.string.informacion_exitosa),Toast.LENGTH_LONG).show();
                borrarInformacion();
            }else {
                Toast.makeText(getContext(),getText(R.string.ya_existe),Toast.LENGTH_LONG).show();
            }

            if(estado.getText().toString().equals("Activo")){

            }
        });

        return root;
    }


    private void borrarInformacion() {
        cita = new Cita();
        cidPersona.setText("");
        fecha.setText("");
        estado.setText("");
        observacion.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void insertarInformacion() {
        Observable.fromCallable(()-> {
            DataBaseHelper.getSimpleDB(getContext()).getCitaDAO().create(cita);
            return cita;
        }).subscribeOn(Schedulers.computation()).subscribe();
    }


}
