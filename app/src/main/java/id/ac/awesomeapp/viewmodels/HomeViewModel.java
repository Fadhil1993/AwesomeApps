package id.ac.awesomeapp.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.awesomeapp.model.ImageModel;

public class HomeViewModel extends ViewModel {
    public String Title = "Awesome App";
    List<ImageModel> imageModels;
    public boolean listView = true;
    public boolean gridView = false;

    public void ChangeGrid(){
        listView = !listView;
        gridView = !gridView;
    }
}
