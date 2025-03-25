package com.example.exafina;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class VolumenActivity extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerView;
    VolumenAdapter adapter;
    ArrayList<Volumen> listaVolumenes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);

        recyclerView = findViewById(R.id.recyclerViewVolumenes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new VolumenAdapter(listaVolumenes, volumenSeleccionado -> {
            Toast.makeText(this, "Seleccionado: " + volumenSeleccionado.getTitulo(), Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);

        String journalId = getIntent().getStringExtra("journal_id");

        if (journalId != null) {
            Map<String, String> datos = new HashMap<>();
            String url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + journalId;
            WebService ws = new WebService(url, datos, this, this);
            ws.execute("GET");
        } else {
            Toast.makeText(this, "No se recibió el ID de la revista", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void processFinish(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                Volumen v = new Volumen(
                        obj.getString("issue_id"),
                        obj.getString("volume"),
                        obj.getString("number"),
                        obj.getString("year"),
                        obj.getString("date_published"),
                        obj.getString("title"),
                        obj.getString("doi"),
                        obj.getString("cover")
                );

                listaVolumenes.add(v);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
