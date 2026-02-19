# Tarea 3 – Programación III  
### Maven + Librería Local + Aplicación Consumidora + Pila Manual + Validación de Expresiones + Ofuscación Reproducible + Ingeniería Inversa

---

## 1️⃣ Estructura del Repositorio

El repositorio contiene **dos proyectos Maven independientes** dentro del mismo repositorio, cada uno con su propio `pom.xml`:

- `umg.edu.gt.data-structure.stack/` → **Librería**
  - Implementación manual de la estructura de datos tipo pila (lista enlazada).
- `stackHandler/` → **Aplicación cliente**
  - Consume la librería anterior para validar expresiones algebraicas.
- `evidencias/`
  - Capturas de compilación, ejecución, generación de JAR, ofuscación y decompilación.

No se utilizó un proyecto multi-módulo; ambos proyectos se gestionan de forma separada dentro del mismo repositorio.

---

## 2️⃣ Requisitos del Entorno

El proyecto fue desarrollado y probado con:

- **JDK:** 11  
- **Maven:** 3.9.x  
- **Herramienta de decompilación:** JD-GUI (u otra similar)

Verificación del entorno:

```bash
java -version
mvn -v
```

---

## 3️⃣ Parte A – Gestión de Dependencias con Maven

### 3.1 Instalación de la librería local

Desde la carpeta de la librería:

```bash
cd umg.edu.gt.data-structure.stack
mvn clean install
```

Este comando:

- Limpia compilaciones anteriores.
- Genera el archivo JAR.
- Instala la librería en el repositorio local de Maven (`.m2`).

A partir de este momento, el proyecto `stackHandler` puede declarar la dependencia en su `pom.xml`.

---

### 3.2 Compilación del proyecto consumidor

```bash
cd ../stackHandler
mvn clean package
```

Esto genera el JAR ejecutable en:

```
stackHandler/target/stackHandler-1.0.0.jar
```

---

## 4️⃣ Parte B – Implementación Funcional

### 4.1 Librería: Pila Enlazada Manual

La estructura de datos fue implementada **sin utilizar `java.util.Stack`**.

Se desarrolló una pila basada en lista enlazada con nodos personalizados (`Node`), incluyendo las siguientes operaciones:

- `push()` → Inserta un elemento en la cima.
- `pop()` → Elimina y retorna el elemento superior.
- `peek()` → Retorna el elemento superior sin eliminarlo.
- `isEmpty()` → Indica si la pila está vacía.
- `getCount()` / `getSize()` / `length()` → Devuelve la cantidad de elementos almacenados.
- `getNodeInit()` → Retorna el nodo base (fondo) de la pila.

La implementación cumple con el comportamiento LIFO (Last In, First Out).

---

### 4.2 Aplicación: Validador de Expresiones

El proyecto `stackHandler` incluye una clase `SymbolValidator` que utiliza la pila implementada en la librería.

El algoritmo:

1. Recorre la expresión carácter por carácter.
2. Inserta símbolos de apertura (`(`, `[`, `{`) en la pila.
3. Al encontrar un símbolo de cierre:
   - Verifica que la pila no esté vacía.
   - Comprueba que coincida con el último símbolo insertado.
4. Al finalizar, si la pila está vacía, la expresión es válida.

Casos probados:

- `(a+b) * [c-d]` → **true**
- `([)]` → **false**

---

## 5️⃣ Ejecución desde Consola (JAR Normal)

Ejemplo de ejecución:

```bash
cd stackHandler
java -jar target/stackHandler-1.0.0.jar "(a+b) * [c-d]"
java -jar target/stackHandler-1.0.0.jar "([)]"
```

El resultado indica si la expresión es válida o inválida según el balance de símbolos.

---

## 6️⃣ Parte C – Ofuscación Reproducible con ProGuard

### 6.1 Configuración

La ofuscación fue configurada dentro del `pom.xml` de `stackHandler` mediante un perfil Maven:

```xml
<id>obfuscate</id>
```

Se utiliza un archivo de reglas:

```
stackHandler/proguard.pro
```

Esto permite que el proceso sea reproducible únicamente mediante comandos Maven.

---

### 6.2 Generación del JAR Ofuscado

```bash
cd stackHandler
mvn clean package -Pobfuscate
```

Archivos generados en `target/`:

- `stackHandler-1.0.0.jar` → JAR normal
- `stackHandler-1.0.0-obfuscated.jar` → JAR ofuscado

---

## 7️⃣ Prueba de Regresión (JAR Ofuscado)

```bash
cd stackHandler
java -jar target/stackHandler-1.0.0-obfuscated.jar "(a+b) * [c-d]"
java -jar target/stackHandler-1.0.0-obfuscated.jar "([)]"
```

Resultado:

El comportamiento es **idéntico** al JAR original.  
La funcionalidad no se ve afectada por la ofuscación.

---

## 8️⃣ Parte D – Ingeniería Inversa

Se utilizó una herramienta de decompilación (por ejemplo JD-GUI) para analizar:

- `stackHandler-1.0.0.jar`
- `stackHandler-1.0.0-obfuscated.jar`

### Observaciones:

- En el JAR normal, los nombres de clases y métodos son claros y legibles.
- En el JAR ofuscado, los nombres son reemplazados por identificadores genéricos (ej. `a`, `b`, `c`).
- La estructura del programa sigue siendo reconocible.
- La lógica puede inferirse, pero la lectura es considerablemente más difícil.

Esto demuestra que la ofuscación incrementa la dificultad del análisis sin alterar el funcionamiento.

---

## 9️⃣ Evidencias

La carpeta `evidencias/` incluye:

- Compilación exitosa (`BUILD SUCCESS`)
- Listado de JAR normal y ofuscado
- Ejecución por consola antes y después de ofuscar
- Capturas de decompilación mostrando los cambios en nombres

---

## 🔟 Conclusión

Se desarrolló correctamente una pila manual como librería Maven instalada localmente, la cual fue consumida desde un segundo proyecto para validar expresiones algebraicas.

Se generaron JARs ejecutables desde consola y se configuró un proceso de ofuscación reproducible mediante perfil Maven con ProGuard.  

La ingeniería inversa confirma que la ofuscación reduce la legibilidad del código, pero mantiene intacto el comportamiento funcional del sistema.
