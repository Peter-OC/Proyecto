# Proyecto Real: Aplicación Sector Hostelería "PIZZERÍA"
El objetivo que perseguimos con el desarrollo de esta aplicación es que los alumnos puedan aplicar lo aprendido en el curso desde el primer momento a un Proyecto real que irá creciendo según avance la formación y sus conocimientos vayan siendo más amplios. Además de la parte técnica, en el Proyecto se trabajarán otra serie de habilidades importantes en su perfil como el trabajo en equipo, gestión del tiempo y comunicación.
El proyecto consistirá en el desarrollo de una aplicación web para el sector hostelero (pizzería). La aplicación tendrá un backend con microservicios desarrollados con JAVA, MAVEN y SPRING; haciendo uso de bases de datos relacionales (MySQL) y No-SQL (MongoDB). Estos servicios serán consumidos desde el frontend web desarrollado en Angular. El proyecto deberá cumplir con las reglas de buenas prácticas del Clean Code y una cobertura de código adecuada.
## Contexto:
Una pizzería tradicional de amplia tradición, dentro del proceso de actualización de su modelo de negocio con la transformación digital y a la tendencia de los clientes a la comida a domicilio, ha solicitado un sistema web que permita a sus clientes realizar sus pedidos a domicilio y a sus empleados gestionarlos.
Los clientes podrán consultar la carta con las pizzas disponibles y realizar los pedidos, así como hacer el seguimiento de los mismos. Siguiendo la tendencia de estos tiempos, están muy interesados en la retroalimentación con sus clientes a través de me gusta (likes), valoraciones, comentarios y otros aspectos de redes sociales. Así mismo ha insistido mucho que sea “muy bonito”, “con muchas fotos y colores” y “que se vea bien en los móviles”.

Los empleados cuentan con tres perfiles claramente diferenciados:
- Los empleados de tienda gestionan la recepción y elaboración de los pedidos, registrando en la aplicación sus acciones para permitir el seguimiento de los pedidos por parte de los clientes.
- Los repartidores servirán los pedidos, registrando a través de sus móviles el proceso y así permitir a los clientes continuar con el seguimiento de su pedido hasta la entrega.
- El gerente se encarga de las acciones comerciales elaborando la carta y será el encargado de las autorizaciones a los usuarios asignando los diferentes perfiles.
## Product Backlog
Analizadas las historias de usuario, el Product Owner ha incorporado las siguientes temáticas y tareas priorizadas al backlog:
- Area de clientes
    - Consultar carta de pizzas.
    - Ver los detalles de cada pizza.
    - Pedir una pizza.
    - Hacer el seguimiento de su pedido pendiente.
    - Cancelar el pedido si no ha empezado a elaborarse.
    - Marcar si le ha gustado o no la pizza.
    - Valorar o escribir sobre la pizza.
- Tienda
    - Consulta los pedidos solicitados.
    - Pasar un pedido de solicitado a elaborándose o cancelado.
    - Pasar un pedido de elaborándose a preparado (pendiente de enviar).
- Repartidores:
    - Consulta los pedidos preparados.
    - Marcar el pedido que recoge dejándolo como enviado.
    - Marcar el pedido que entrega dejándolo como recibido.
- Gerente:
    - Mantenimiento de los ingredientes de las pizzas.
    - Mantenimiento de las pizzas y su composición.
    - Asignación de los roles de empleados a los usuarios.
- Usuarios:
    - Auto registro de clientes (como usuarios autenticados).
    - Auto registro de empleados (como usuarios autenticados).
    - Consulta y mantenimiento de los datos propios de usuario.
    - Cambio de contraseña.
- Infraestructura
    - Creación del repositorio GIT
    - Creación de las bases de datos
    - Creación de proyecto del microservicio de tienda.
    - Creación de proyecto del microservicio del servidor de autenticación.
    - Creación de proyecto del frontend.
## Especificaciones:
### Modelo de datos:
![Diagrama de datos](https://raw.githubusercontent.com/Peter-OC/Proyecto/master/modelo_de_datos.png)

- Ingrediente: nombre, tipo (base, salsa y otros) y precio.
- Pizza: nombre, descripción, foto, base (ingrediente), salsa(ingrediente), precio, me gusta, no me gusta.
- Ingredientes por Pizza: pizza, ingrediente, cantidad.
- Comentario: texto, puntuación, fecha, usuario y pizza.
- Usuario: email (identificador), nombre, apellidos, dirección y contraseña.
- Función: usuario, rol (usuario, tienda, repartidor, gerente).
- Pedido: número de pedido, usuario, fecha y hora del pedido, dirección de entrega, preparado por, fecha y hora de entrega, entregada por, importe, estado.
- Pizzas por Pedido: pedido, pizza, cantidad, precio
### Estado del pedido:
![Diagrama de datos](https://raw.githubusercontent.com/Peter-OC/Proyecto/master/estado_del_pedido.png)
### Restricciones:
El precio de la pizza se calcula sumando el coste de los ingredientes, incrementados en un 50% en concepto de gastos de elaboración y envío.

## Arquitectura:
### Back End
- Microservicio: Tienda
    - Servicio: Pizzas (y Comentarios)
        - No autenticados: Solo GET
        - Autenticados: POST (Solo Comentarios)
        - Gerente: GET, POS, PUT, DELETE
    - Servicio: Ingredientes
        - No autenticados: Solo GET
        - Gerente: GET, POS, PUT, DELETE
    - Servicio: Pedidos
        - Autenticados: GET, POS, PUT (solo si solicitada), DELETE (solo si solicitada)
        - Empleado: PATCH: Cambios de estado:
- Microservicio: Autentication
    - Servicio: Login
        - No autenticados: GET, POST
    - Servicio: Usuarios
        - No autenticados: Solo POST
        - Autenticados: GET y PUT propio
        - Gerente: GET, POS, PUT, DELETE
### Restricciones:
:one: - Los usuarios deben poder registrarse en la aplicación por medio de un formulario y hacer log-in.

:two: - Debe dar soporte a la autentificación OAuth con JWT.

:three: - Debe permitir CORS.

:four: - Debe seguir un enfoque API First.

:five: - Debe contar con:
- Validaciones
- Batería de pruebas
- Documentación con OpenApi (opcional)
- Despliegue con Docker (opcional)
### Front End
Aplicación Angular: Pizza Web
- Anónimos: Consultar la carta
- Autenticados: Consultar la carta, dejar comentarios, hacer pedidos, modificar o cancelar pedidos, consultar situación del pedido, ver historial de pedidos.
- Personal (Autenticados): Consultar pedidos, cambiar estado pedido.
- Gerente (Autenticados): Mantener pizzas e ingredientes, mantener usuarios.
### Restricciones:
:one: - Debe ser accesible.

:two: - Debe seguir un enfoque Mobile First.

:three: - Debe tener un estilo propio: Diseño adaptable, BEM, ...

:four: - Debe contar con:
- Validaciones
- Enrutamientos
- Batería de pruebas
