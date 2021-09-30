package co.com.k4soft.miagenda.ui.citasConsulta;

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
import co.com.k4soft.miagenda.databinding.FragmentConsultaBinding;
import co.com.k4soft.miagenda.databinding.FragmentConsultaBinding;
import co.com.k4soft.miagenda.entitiy.Cita;
import co.com.k4soft.miagenda.persistencia.room.DataBaseHelper;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CitasConsultaFragment extends Fragment{

    private CitasConsultaViewModel citaConsultaViewModel;
    private @NonNull FragmentConsultaBinding binding;
    private Cita cita;

    private EditText estado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        citaConsultaViewModel =
                new ViewModelProvider(this).get(CitasConsultaViewModel.class);

        binding = FragmentConsultaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        estado = binding.txtestado;

        final Button btnConsultar = binding.btnConsultar;
        cita = new Cita();

        citaConsultaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        btnConsultar.setOnClickListener( v-> {

            cita.setEstado(estado.getText().toString());

            if(estado.getText().toString().equals("Activa")){
                Cita citaConsultada = DataBaseHelper.getDBMainThread(getContext()).getCitaDAO().findUpateEstado(cita.getEstado());
                Toast.makeText(getContext(),getText(R.string.consulta_exitosa),Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getContext(),getText(R.string.error),Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
