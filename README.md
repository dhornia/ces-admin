
# CesAdmin

CesAdmin es una aplicación desarrollada en Java como parte del curso "Introducción a la programación para testers" del CES, orientada a la gestión de usuarios administradores.

## Funcionalidades

#### 🔐 Login de usuario
* **Descripción:** Permitir a un usuario administrador autenticarse mediante correo y contraseña, validar que las credenciales sean correctas y que no se introduzcan campos vacíos.
* **Datos requeridos:** `correo`, `contraseña`

#### 📝 Registro de usuario
* **Descripción:** Crear nuevos usuarios administradores, mediante el ingreso de los datos solicitados en el formulario, validar que no se introduzcan campos vacíos, que el correo no exista para no duplicar el usuario y verificar que las contraseñas coincidan.
* **Datos requeridos:** `nombre`, `apellido`, `correo`, `contraseña`, `país de nacimiento`

#### 🔄 Reinicio de contraseña
* **Descripción:** Cambiar la contraseña a un usuario administrador, validar que el usuario exista, que no hayan campos vacíos y que las contraseñas introducidas coincidan.
* **Datos requeridos:** `correo`, `contraseña`

#### 👥 Listado de usuarios
* **Descripción:** Muestra la lista de los usuarios administradores registrados en el sistema.
* **Datos requeridos:** `correo`, `contraseña`

## Tecnologías utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

## Diagrama de clases UML

![Diagrama de clases UML](./uml.svg)
