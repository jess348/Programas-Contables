# Sistema de Gestión Administrativa UNI (DGI & RRHH) 🏢💻

Este proyecto es una solución integral desarrollada en **Java Swing** para la automatización de procesos administrativos críticos. Integra un módulo de declaración de impuestos para la **Dirección General de Ingresos (DGI)** y un sistema de gestión de reclutamiento para **Recursos Humanos**.

El software destaca por su robustez técnica, implementando arquitecturas de diseño avanzadas y motores de validación en tiempo real.

## 🚀 Características Principales

### 📑 Gestión de Ventanas (MDI)
* **MDI (Multiple Document Interface):** Arquitectura centralizada que permite la ejecución de múltiples formularios internos (`JInternalFrame`) sobre un escritorio virtual (`JDesktopPane`).
* **VentManager:** Controlador de flujo que audita el estado de las ventanas para prevenir duplicidad de procesos y optimizar el consumo de memoria RAM.

### ⚖️ Módulo Tributario (DGI)
* **Motor de Cálculo:** Automatización del IR (30% sobre utilidad en Régimen General o 3% sobre ingresos en Cuota Fija).
* **Pasarela de Pago Interceptora:** Implementación de un diálogo de tipo `APPLICATION_MODAL` que detiene el flujo de guardado hasta confirmar la transacción bancaria.
* **Generación de Folios:** Identificadores únicos basados en marcas de tiempo del sistema.

### 👥 Módulo de Recursos Humanos (Solicitud de Empleo)
* **Estructura Multicapa:** Organización de datos en 8 pestañas lógicas (Datos Personales, Escolaridad, Salud, Experiencia, etc.).
* **Validación de Integridad:** Uso de **Expresiones Regulares (Regex)** para validar formatos legales nicaragüenses (RUC, Cédula, NSS, Email).
* **Tablas Dinámicas:** Gestión de historial académico y laboral mediante `JTable`.

### ⚡ Experiencia de Usuario (UX)
* **Navegación por Recursividad:** Algoritmo recursivo que permite navegar por todos los campos del formulario (`transferFocus`) utilizando únicamente la tecla **Enter**.
* **Diseño Moderno:** Implementación de la librería **FlatLaf** para una interfaz profesional y escalable.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 8 o superior).
* **GUI:** Java Swing & AWT.

## 📂 Estructura del Proyecto
* `Main.java`: Punto de entrada y configuración del entorno global.
* `SolicitudFormularioFrm.java`: Clase principal del módulo DGI.
* `SolicituDEmpleo.java`: Clase principal del módulo RRHH.
* `BoletaPagoDialog.java`: Ventana modal de pasarela bancaria.
* `Contribuyente.java` / `Domicilio.java` / `Familiar.java`: Modelos de datos encapsulados (POO).

## ⚙️ Instalación y Ejecución
1.  Clona el repositorio:
    ```bash
    git clone [https://github.com/tu-usuario/gestion-administrativa-uni.git](https://github.com/tu-usuario/gestion-administrativa-uni.git)
    ```
2.  Importa el proyecto en tu IDE favorito (VS Code, IntelliJ, NetBeans).
3.  Asegúrate de incluir las librerías `.jar` (`FlatLaf` e `iText`) en las dependencias del proyecto.
4.  Ejecuta la clase `FormularioDGI.Main`.

## ✍️ Autor
* **Jeslyng David Maldonado Vivas** - Estudiante de Ingeniería de Sistemas - **Universidad Nacional de Ingeniería (UNI)**.

---
*Este proyecto fue desarrollado como parte de la asignatura de Programación II.*
