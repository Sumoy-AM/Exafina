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

public class ArticuloActivity extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerViewArticulos;
    ArticuloAdapter adapter;
    ArrayList<Articulo> listaArticulos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        recyclerViewArticulos = findViewById(R.id.recyclerViewArticulos);
        recyclerViewArticulos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArticuloAdapter(listaArticulos);
        recyclerViewArticulos.setAdapter(adapter);

        String issueId = getIntent().getStringExtra("issue_id");

        if (issueId != null) {
            String url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + issueId;
            Map<String, String> datos = new HashMap<>();
            WebService ws = new WebService(url, datos, this, this);
            ws.execute("GET");
        } else {
            Toast.makeText(this, "No se recibió el ID de la edición", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject art = jsonArray.getJSONObject(i);

            String titulo = art.getString("title");
            String resumen = art.getString("abstract");
            String doi = art.getString("doi");

            JSONArray galeys = art.getJSONArray("galeys");
            String pdfUrl = "", htmlUrl = "";
            for (int j = 0; j < galeys.length(); j++) {
                JSONObject g = galeys.getJSONObject(j);
                String label = g.getString("label").toUpperCase();
                if (label.equals("PDF")) {
                    pdfUrl = g.getString("UrlViewGalley");
                } else if (label.equals("HTML")) {
                    htmlUrl = g.getString("UrlViewGalley");
                }
            }

            JSONArray autoresJson = art.getJSONArray("authors");
            ArrayList<String> autores = new ArrayList<>();
            for (int j = 0; j < autoresJson.length(); j++) {
                JSONObject autor = autoresJson.getJSONObject(j);
                autores.add(autor.getString("nombres"));
            }

            listaArticulos.add(new Articulo(titulo, resumen, doi, pdfUrl, htmlUrl, autores));
        }

        adapter.notifyDataSetChanged();
    }
}
