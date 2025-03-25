<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Galería de Imágenes</title>
  <style>
    .galeria {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
      justify-content: center;
    }
    .galeria img {
      width: 250px;
      height: 250px;
      object-fit: cover;
      border: 2px solid #ccc;
      border-radius: 10px;
    }
  </style>
</head>
<body>
  <h2 style="text-align:center;">Imágenes del Proyecto</h2>
  <div class="galeria">
    <img src="https://github.com/user-attachments/assets/52d84c78-3ae2-4cb7-a58d-2fd7a4b8e2cc" alt="Imagen 1">
    <img src="https://github.com/user-attachments/assets/947ba953-70ae-4bb8-945f-bc794448c2b7" alt="Imagen 2">
    <img src="https://github.com/user-attachments/assets/cd0c819e-70aa-4ef7-88e3-2f570b6fd736" alt="Imagen 3">
    <img src="https://github.com/user-attachments/assets/b28f8dd5-794a-445d-ac96-a9edfdfd6d29" alt="Imagen 4">
  </div>
</body>
</html>
