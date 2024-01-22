# Image Viewer Project
Este proyecto implementa un visor de imágenes en Java con interfaz gráfica de usuario (GUI). Permite visualizar imágenes y navegar entre ellas mediante clics y arrastres en la ventana ó botones en la parte inferior que aparecen con el cursor encima.

### Estructura del Proyecto

El proyecto está organizado en paquetes que representan diferentes capas de la aplicación:

- **software.ulpgc.imageviewer:** Contiene las clases principales de la lógica de la aplicación.
  - **Image:** Interfaz que define la estructura de una imagen y métodos para navegar entre ellas.
  - **ImageDisplay:** Interfaz que define la visualización de imágenes y métodos de interacción.
  - **ImageLoader:** Interfaz para cargar imágenes.
  - **ImagePresenter:** Clase que gestiona la presentación de las imágenes.
- **software.ulpgc.imageviewer.mocks:** Contiene implementaciones simuladas para pruebas y desarrollo.
  - **MockImageLoader:** Implementación de ImageLoader que carga imágenes simuladas.
- **software.ulpgc.imageviewer.swing:** Contiene la implementación de la interfaz gráfica utilizando Swing.
  - **MainFrame:** Clase principal que representa la ventana principal de la aplicación.
  - **SwingImageDisplay:** Implementación de ImageDisplay utilizando componentes Swing.
