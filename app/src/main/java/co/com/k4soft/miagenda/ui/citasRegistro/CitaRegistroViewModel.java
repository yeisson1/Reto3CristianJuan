package co.com.k4soft.miagenda.ui.citasRegistro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CitaRegistroViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CitaRegistroViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cita registro fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}