package com.example.exafina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerView;
    RevistaAdapter adapter;
    ArrayList<Revista> listaRevistas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear el adapter y manejar clic
        adapter = new RevistaAdapter(listaRevistas, revistaSeleccionada -> {
            String idRevista = revistaSeleccionada.getId();

            if (idRevista != null && !idRevista.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, VolumenActivity.class);
                intent.putExtra("journal_id", idRevista);
                startActivity(intent);
            } else {
                Toast.makeText(this, "ID de revista no disponible", Toast.LENGTH_SHORT).show();
            }

        });

        recyclerView.setAdapter(adapter);

        // Llamar al WebService
        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService(
                "https://revistas.uteq.edu.ec/ws/journals.php",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject revista = jsonArray.getJSONObject(i);
            String id = revista.getString("journal_id");
            String nombre = revista.getString("name");
            String descripcion = revista.getString("description");
            String abbreviation = revista.getString("abbreviation");

            String portada = revista.has("portada")
                    ? revista.getString("portada")
                    : "https://revistas.uteq.edu.ec/public/site/images/adminuteq/logoUTEQ.png";

            listaRevistas.add(new Revista(id, nombre, descripcion, portada, abbreviation));
        }

        adapter.notifyDataSetChanged();
    }
}
