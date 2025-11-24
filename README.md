# PetsOnline

## Resumen ejecutivo
PetsOnline es una aplicación Android desarrollada en Kotlin cuyo objetivo es centralizar la gestión del perfil de un tutor de mascotas, incluyendo autenticación, seguimiento de mascotas registradas, reservas de servicios y productos recomendados. El proyecto forma parte de la Evaluación Parcial 2 del curso y demuestra dominio de diseño Material 3, validación de formularios, persistencia local, gestión de estado y uso controlado de recursos nativos.

## Funcionalidades principales
- **Autenticación segura:** formulario de inicio de sesión con validaciones en tiempo real y mensajes de error contextualizados mediante `ValidationUtils`.
- **Dashboard integral:** tarjetas con métricas clave (mascotas activas, reservas), chips de filtrado, búsqueda de servicios y accesos directos a acciones frecuentes.
- **Gestión de mascotas y reservas:** formularios modales y adaptadores reutilizables (`PetAdapter`, `ReservationAdapter`) para visualizar y actualizar la información.
- **Validaciones reactivas:** `TextWatcher` y `TextInputLayout` aseguran feedback inmediato en login, registro y edición de perfil.
- **Persistencia local:** implementación con Room (`AppDatabase`, DAOs y `UserRepository`) para garantizar operación sin conexión y consistencia de datos.
- **Recursos nativos:** `ProfileActivity` permite capturar o elegir fotografías desde la cámara/galería, administrando permisos mediante `PermissionHelper`.

## Arquitectura y stack tecnológico
- **Patrón:** MVVM con `ViewModel`, `LiveData` y repositorios que abstraen el acceso a datos.
- **Persistencia:** Room Database + DAO para usuarios, mascotas, productos, reservas y servicios veterinarios.
- **UI:** Android Views con Material Components, `ConstraintLayout`, tarjetas y chips personalizados.
- **Utilidades:** `ValidationUtils` para reglas de negocio, `AnimatedButton` como componente reutilizable con animaciones de entrada/feedback.
- **Dependencias clave:** Kotlin, AndroidX, Material Components, Room, Glide.

## Estructura del proyecto
```
app/
 └── src/main/
     ├── java/com/example/miperfil/
     │   ├── data/           # Modelos y DAOs de Room
     │   ├── repository/     # Lógica de acceso a datos
     │   ├── ui/             # Activities, adapters y componentes
     │   ├── utils/          # Validaciones y permisos
     │   └── viewmodel/      # ViewModels y factories
     └── res/                # Layouts, drawables, animaciones y strings
```

## Requisitos previos
- Android Studio Giraffe o superior.
- JDK 17 (configurado por Android Studio).
- Dispositivo físico o emulador con Android 8.0 (API 26) o superior.

## Configuración y ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/rjokerlikely1690/KotlinApp-BelenAhumada-RobertoAnania.git
   cd KotlinApp-BelenAhumada-RobertoAnania
   ```
2. Abrir la carpeta `kotlinandroidapp` en Android Studio.
3. Sincronizar dependencias Gradle y esperar a que finalice la compilación inicial.
4. Configurar un emulador o conectar un dispositivo físico con depuración USB habilitada.
5. Ejecutar **Run > Run 'app'** para desplegar la aplicación.

## Prácticas de calidad implementadas
- Validación exhaustiva de formularios con control de estados y mensajes localizados.
- Observadores de estado (`isLoading`, `currentUser`, `errorMessage`) para mantener una UI reactiva sin bloqueos.
- Componentes modulares reutilizables (p. ej. `AnimatedButton`) que encapsulan animaciones y consistencia visual.
- Manejo explícito de permisos y URIs seguras mediante `FileProvider`.
- Separación clara de responsabilidades entre capas de datos, lógica y presentación para facilitar el mantenimiento.

## Próximos pasos sugeridos
1. Añadir pruebas instrumentadas para flujos críticos (login, registro, carga de imágenes).
2. Incorporar sincronización remota y almacenamiento en la nube para respaldos.
3. Documentar endpoints o mock services si se integra una API externa.
4. Publicar releases etiquetados en GitHub y automatizar builds con GitHub Actions.

---
Para cualquier consulta o seguimiento del proyecto, utilice los issues del repositorio o contacte al equipo responsable. Este documento se mantendrá actualizado con cada entrega relevante.

