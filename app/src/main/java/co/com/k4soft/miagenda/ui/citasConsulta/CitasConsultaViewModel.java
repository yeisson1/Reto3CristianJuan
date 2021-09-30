package co.com.k4soft.miagenda.ui.citasConsulta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitasConsultaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CitasConsultaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is consulta cita fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
