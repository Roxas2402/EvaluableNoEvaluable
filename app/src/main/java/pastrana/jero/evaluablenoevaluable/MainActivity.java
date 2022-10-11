package pastrana.jero.evaluablenoevaluable;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.ArrayList;

import pastrana.jero.evaluablenoevaluable.activities.activity_add;
import pastrana.jero.evaluablenoevaluable.databinding.ActivityMainBinding;
import pastrana.jero.evaluablenoevaluable.models.PisoModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> addPisoLauncher;

    private ArrayList<PisoModel> pisoModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        pisoModelArrayList = new ArrayList<>();
        inicializaLauncher();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPisoLauncher.launch(new Intent(MainActivity.this, activity_add.class));
            }
        });
    }

    private void inicializaLauncher(){
        addPisoLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null && result.getData().getExtras() != null){
                        PisoModel pisoModel = (PisoModel) result.getData().getExtras().getSerializable("PISO");
                        if (pisoModel != null) {
                            pisoModelArrayList.add(pisoModel);
                            //mostrarPisos();
                        }
                    }

                }
            }
        });
    }
}