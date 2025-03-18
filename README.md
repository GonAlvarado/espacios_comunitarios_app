# 📱 Espacios Comunitarios App

Espacios Comunitarios App es una aplicación móvil desarrollada en Kotlin diseñada para la gestión y administración de Espacios Comunitarios que brindan asistencia a la población en situación de vulnerabilidad social. La aplicación permite a los usuarios autenticados acceder a información relevante sobre los espacios comunitarios, sus referentes y la asistencia que reciben, con la posibilidad de visualizar sus ubicaciones en un mapa interactivo y obtener la ruta hacia ellos.

---

## 🌟 Características
- 🔐 **Autenticación de Usuarios:** Manejo seguro de autenticación utilizando tokens JWT.
- 🌐 **Consumo de API con Retrofit:** Interacción con la API REST para obtener y enviar información.
- 📌 **Integración con Google Maps:** Visualización de ubicaciones y rutas hacia los Espacios Comunitarios.
- 🧩 **Inyección de dependencias con Dagger Hilt:** Mejora de la modularidad y escalabilidad del proyecto.
- 💾 **Almacenamiento Local:** Uso de DataStore Preferences para manejo eficiente de configuraciones locales.
- 📱 **Arquitectura MVVM:** Separación clara entre UI, lógica de negocio y datos.

---

## 📂 Estructura del Proyecto
- `app/` - Código principal de la aplicación móvil.
- `data/` - Gestión de datos, incluyendo modelos y acceso a la API.
- `ui/` - Vistas y componentes de UI utilizando la arquitectura MVVM.
- `domain/` - Lógica de negocio.

---

## 🚀 Tecnologías Usadas
- **Lenguaje:** Kotlin
- **Librerías principales:**
  - Retrofit
  - Dagger Hilt
  - Google Maps
  - DataStore Preferences
- **Arquitectura:** MVVM (Model-View-ViewModel)

---

## 🔑 Instalación y Ejecución
1. Clona este repositorio:
```bash
 git clone https://github.com/GonAlvarado/espacios_comunitarios_app.git
```
2. Navega al directorio del proyecto:
```bash
cd EspaciosComunitariosApp
```
3. Abre el proyecto en Android Studio.
4. Configura tu archivo `secret.properties` con tu API Key de Google Maps:
```
MAPS_API_KEY=tu_api_key
```
5. Ejecuta la aplicación en un dispositivo o emulador.

---

## 📌 Uso
- Ingresa tus credenciales para acceder a la aplicación.
- Visualiza los Espacios Comunitarios en un mapa interactivo.
- Accede a información detallada de cada espacio y contacta a sus referentes.

---

## 📜 Licencia
Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo `LICENSE` para más información.

---

## 🤝 Contribuciones
¡Las contribuciones son bienvenidas! Si deseas colaborar, por favor realiza un fork del proyecto, crea una rama y envía un pull request.

---

## 📧 Contacto
Para consultas o sugerencias, puedes contactarme a través de mi perfil de GitHub.

