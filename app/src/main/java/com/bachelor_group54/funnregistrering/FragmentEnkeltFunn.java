package com.bachelor_group54.funnregistrering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//This fragment displays one selected find at the time. The find can also be edited here.
public class FragmentEnkeltFunn extends Fragment {
    private View view;
    private Funn funn;
    private int position; //The finds position in the saved list

    //Simple constructor for getting the find that the fragment should display
    public FragmentEnkeltFunn(Funn funn, int position) {
        this.funn = funn;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enkelt_funn, container, false); //Loads the page from the XML file
        //Add setup code here later
        loadFunn();
        return view;
    }

    public void loadFunn(){
        String tomtFelt = "ikke fylt ut";
        ImageView imageView = view.findViewById(R.id.fragment_enkelt_funn_bilde); //Finds the image view
        imageView.setImageBitmap(ImageSaver.loadImage(getContext(), funn.getBildeID())); //Sets the image view to the finds image

        //TODO finne ut hvilke felter brukeren skal kunne endre selv

        EditText coordinates = view.findViewById(R.id.fragment_enkelt_funn_et_koordinater); //Finds the coordinates textView
        String coords = "" + funn.getLongitude() + " " + funn.getLatitude();
        //Nether latitude nor longitude can have 200 as a value, but these are the default values. This tells us that they have not been set jet
        if(funn.getLatitude() == 200 && funn.getLongitude() == 200){
            coords = tomtFelt;
            coordinates.setHint(coords); //Hint is preview text, makes it easier to edit.
        }else {
            coordinates.setText(coords);
        }

        EditText depth = view.findViewById(R.id.fragment_enkelt_funn_et_funndybde);
        if(funn.getFunndybde() == -1){//-1 is the default value, FIXME should not be able to set negative value for depth
            depth.setHint(tomtFelt);
        }else{
            depth.setText("" + funn.getFunndybde());
        }

        EditText title = view.findViewById(R.id.fragment_enkelt_funn_et_tittel); //Finds the textView of the title
        setText(funn.getTittel(), title); //Checks and sets the title

        //The same for all the other textViews, finding, checking and setting the text.
        EditText date = view.findViewById(R.id.fragment_enkelt_funn_et_dato);
        setText(funn.getDato(), date);

        EditText location = view.findViewById(R.id.fragment_enkelt_funn_et_sted);
        setText(funn.getFunnsted(), location);

        EditText owner = view.findViewById(R.id.fragment_enkelt_funn_et_grunneier);
        setText(funn.getGrunneierNavn(), owner);

        //TODO legge til status
        TextView status = view.findViewById(R.id.fragment_enkelt_funn_tv_status);
        status.setText("Status: vi har ikke noe status");

        EditText description = view.findViewById(R.id.fragment_enkelt_funn_et_beskrivelse);
        setText(funn.getBeskrivelse(), description);

        EditText item = view.findViewById(R.id.fragment_enkelt_funn_et_gjenstand);
        setText(funn.getGjenstand(), item);

        EditText itemMarking = view.findViewById(R.id.fragment_enkelt_funn_et_gjenstand_merke);
        setText(funn.getGjenstandMerking(), itemMarking);

        EditText age = view.findViewById(R.id.fragment_enkelt_funn_et_datum);
        setText(funn.getDatum(), age);

        EditText areaType = view.findViewById(R.id.fragment_enkelt_funn_et_arealtype);
        setText(funn.getArealType(), areaType);

        EditText moreInfo = view.findViewById(R.id.fragment_enkelt_funn_et_annet);
        setText(funn.getOpplysninger(), moreInfo);

        //FIXME legge til gårdnr, bruksnr, kommune og fylke
    }

    //Checks if strings are filled put or not
    public String checkString(String string){
        if(string == null || string.equals("")){ //If null or empty string return not filled message
            return "ikke fylt ut";
        }
        return string; //Returns the input string by default
    }

    public void setText(String text, EditText editText){
        text = checkString(text);
        if (!text.equals("ikke fylt ut")) {
            editText.setText(text);
        }
    }

    public void saveFind(){
        updateFind();
        //If the a picture has been added save it

        /*if(picture != null) {
            savePicture();
        }*/

        ObjektLagrer objektLagrer = new ObjektLagrer(getContext(), "funn"); //Initialises the class that saves the finds
        ArrayList<Object> arrayList = objektLagrer.loadData(); //Gets the already saved ArrayList with all the previous finds
        arrayList.set(position, funn); //Overwrites the previous finds

        objektLagrer.saveData(arrayList); //Saves the new list, overwriting the old list
    }

    //This method is used for updating the find before saving it
    public void updateFind(){
        //TODO endre bilde
        EditText coordinates = view.findViewById(R.id.fragment_enkelt_funn_et_koordinater); //Finds the coordinates textView
        String coords = "" + funn.getLongitude() + " " + funn.getLatitude();
        if(!coordinates.getText().toString().equals("")) {
            //Finds the coordinates, splits them in lat and long (" " in between) and parses them to double
            funn.setLatitude(Double.parseDouble((coordinates.getText().toString()).split(" ")[0]));
            funn.setLongitude(Double.parseDouble((coordinates.getText().toString()).split(" ")[1]));
        }

        EditText depth = view.findViewById(R.id.fragment_enkelt_funn_et_funndybde); //Finds the text field
        if(!depth.getText().toString().equals("")) {
            funn.setFunndybde(Integer.parseInt(depth.getText().toString())); //Changes the info inn the find
        }

        //Just the same all the way, find and update the text fields
        EditText title = view.findViewById(R.id.fragment_enkelt_funn_et_tittel);
        funn.setTittel(title.getText().toString());

        EditText date = view.findViewById(R.id.fragment_enkelt_funn_et_dato);
        funn.setDato(date.getText().toString());

        EditText location = view.findViewById(R.id.fragment_enkelt_funn_et_sted);
        funn.setFunnsted(location.getText().toString());

        EditText owner = view.findViewById(R.id.fragment_enkelt_funn_et_grunneier);
        funn.setGrunneierNavn(owner.getText().toString());

        //TODO legge til status
        TextView status = view.findViewById(R.id.fragment_enkelt_funn_tv_status);
        status.setText("Status: vi har ikke noe status");

        EditText description = view.findViewById(R.id.fragment_enkelt_funn_et_beskrivelse);
        funn.setBeskrivelse(description.getText().toString());

        EditText item = view.findViewById(R.id.fragment_enkelt_funn_et_gjenstand);
        funn.setGjenstand(item.getText().toString());

        EditText itemMarking = view.findViewById(R.id.fragment_enkelt_funn_et_gjenstand_merke);
        funn.setGjenstandMerking(itemMarking.getText().toString());

        EditText age = view.findViewById(R.id.fragment_enkelt_funn_et_datum);
        funn.setDatum(age.getText().toString());

        EditText areaType = view.findViewById(R.id.fragment_enkelt_funn_et_arealtype);
        funn.setArealType(areaType.getText().toString());

        EditText moreInfo = view.findViewById(R.id.fragment_enkelt_funn_et_annet);
        funn.setOpplysninger(moreInfo.getText().toString());

        //TODO legg til resten av feltene
    }

   /*
    public void savePicture(){
        //Gets the current picture ID for shared preferences (locally saved)
        int pictureID = funn.getBilde();

        //Saves the image and saves the ID of the picture to the find
        ImageSaver.saveImage(picture, getContext(), pictureID);
        funn.setBilde(pictureID);
    } */
}
