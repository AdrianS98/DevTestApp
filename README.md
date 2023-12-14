DevTestApp


El proyecto sigue la arquitectura CLEAN y patron de arquitectura MVVM.                     
app
src/main/java  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**com.example.devtestapp**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**data:** Contiene las fuentes de datos, como las llamadas
REST con Retrofit con lógica de cacheado para no hacer llamadas constantemente cada vez que entra a la pantalla.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**di:** Contiene los módulos de Dagger para la inyección de dependencias con Hilt.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**domain:** Contiene la lógica de dominio y los casos de uso de la aplicación.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**presentation:** Contiene actividades y fragmentos de la interfaz de usuario, así como los ViewModels, corrutinas...  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**utils:** Clases de utilidad ( comprobación de conectividad, interceptores.  

**Frameworks Utilizados**  
**Hilt**  
He implementado Hilt para facilitar la inyección de dependencias en toda la aplicación, asegurando una gestión eficiente de los componentes.

**Retrofit**  
Para realizar las llamadas REST he utilizado Retrofit, y he implementado un sistema de caché en la capa de datos para no hacer llamadas constantemente cada vez que se entra en la pantalla y en caso de que no tengamos conexión mostrar los ultimos resultados.

**Glide**  
He utilizado Glide para cargar imágenes de manera eficiente desde URL, brindando una experiencia de usuario fluida.

**ViewModel y Coroutines**  
Se ha adoptado la arquitectura de ViewModel junto con corrutinas para gestionar de manera eficiente la lógica asíncrona y el ciclo de vida de los componentes de la interfaz de usuario.

**Navigation Component y SafeArgs**  
La navegación entre pantallas se gestiona mediante Navigation Component, proporcionando una estructura clara. Además, SafeArgs se utiliza para pasar datos de manera segura entre pantallas, evitando posibles errores en tiempo de ejecución.

**PROBLEMAS ENCONTRADOS**   
He encontrado dificultades para realizar el diseño del toolbar colapsado en la pantalla del detalle pero a nivel de lógica he intentado hacer lo mejor para priorizar la escalabilidad del código.

