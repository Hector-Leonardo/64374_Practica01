===============================================================================
                           GESTOR DE CONTACTOS
===============================================================================
CARACTERÍSTICAS COMUNES
===============================================================================

- Agregar contactos con nombre y teléfono
- Mostrar todos los contactos registrados
- Búsqueda inteligente por nombre completo o parcial
- Modificación de contactos existentes con selección múltiple
- Eliminación del archivo de contactos
- Confirmación visual de operaciones (datos agregados/modificados)

===============================================================================
INSTALACIÓN Y EJECUCIÓN
===============================================================================

VERSIÓN BUFFERS:
------------------------------
1. javac GestorContactosBuffers.java
2. java GestorContactosBuffers

VERSIÓN FILES:
------------------------------
1. javac GestorContactosFiles.java  
2. java GestorContactosFiles

IMPORTANTE: Solo ejecutar UNA versión a la vez, ambas usan el mismo archivo
"contactos.txt" para almacenar los datos.

===============================================================================
GUÍA DE USO (AMBAS VERSIONES)
===============================================================================

MENÚ PRINCIPAL:
--------------
=== GESTOR DE CONTACTOS ===
1. Agregar contacto
2. Mostrar todos los contactos
3. Buscar contacto por nombre
4. Modificar contacto
5. Eliminar archivo de contactos
6. Salir

-------------------------------------------------------------------------------
1. AGREGAR CONTACTO
-------------------------------------------------------------------------------

- Solicita NOMBRE del contacto
- Solicita TELÉFONO del contacto
- Guarda automáticamente en contactos.txt
- MUESTRA confirmación con los datos agregados

EJEMPLO:
-------------
--- AGREGAR CONTACTO ---
Ingrese el nombre: Ana García
Ingrese el teléfono: 555-9876

--- CONTACTO AGREGADO CORRECTAMENTE ---
Nombre: Ana García
Teléfono: 555-9876

-------------------------------------------------------------------------------
2. MOSTRAR TODOS LOS CONTACTOS
-------------------------------------------------------------------------------

- Lista TODOS los contactos registrados
- Formato: "Nombre: [nombre], Teléfono: [teléfono]"
- Contador total de contactos
- Mensaje informativo si no hay contactos

EJEMPLO:
--------
--- LISTA DE CONTACTOS ---
Nombre: Ana García, Teléfono: 555-9876
Nombre: Carlos Ruiz, Teléfono: 555-1122
Nombre: María López, Teléfono: 555-3344

Total de contactos: 3

-------------------------------------------------------------------------------
3. BUSCAR CONTACTO POR NOMBRE
-------------------------------------------------------------------------------

CARACTERÍSTICAS DE BÚSQUEDA:
- BÚSQUEDA PARCIAL: No necesitas el nombre completo 
- MÚLTIPLES RESULTADOS: Muestra todas las coincidencias
- NUMERACIÓN: Lista numerada de resultados

EJEMPLOS:
-------------
Buscar "ana" encuentra:
- Ana García
- Juana Pérez  
- Susana Martín

Buscar "GAR" encuentra:
- Ana García
- Edgar Morales

RESULTADO:
-------------
--- COINCIDENCIAS ENCONTRADAS ---
1. Nombre: Ana García, Teléfono: 555-9876
2. Nombre: Edgar Morales, Teléfono: 555-4455

Total de coincidencias: 2

-------------------------------------------------------------------------------
4. MODIFICAR CONTACTO
-------------------------------------------------------------------------------

PROCESO COMPLETO:
1. BÚSQUEDA PARCIAL del contacto
2. SELECCIÓN si hay múltiples coincidencias
3. MODIFICACIÓN de nombre y/o teléfono
4. CONFIRMACIÓN con datos actualizados

EJEMPLO COMPLETO:
-----------------
--- MODIFICAR CONTACTO ---
Ingrese el nombre o parte del nombre del contacto a modificar: Ana

--- CONTACTOS ENCONTRADOS ---
1. Nombre: Ana García, Teléfono: 555-9876
2. Nombre: Juana Pérez, Teléfono: 555-1111

Seleccione el número del contacto a modificar (1-2): 1

Contacto seleccionado: Ana García - 555-9876
Ingrese el nuevo nombre (presione Enter para mantener "Ana García"): Ana García Silva
Ingrese el nuevo teléfono (presione Enter para mantener "555-9876"): 555-0000

--- CONTACTO MODIFICADO CORRECTAMENTE ---
Nombre actualizado: Ana García Silva
Teléfono actualizado: 555-0000

-------------------------------------------------------------------------------
5. ELIMINAR ARCHIVO DE CONTACTOS
-------------------------------------------------------------------------------

- Elimina TODO el archivo contactos.txt
- TODOS los contactos se perderán PERMANENTEMENTE
- Acción IRREVERSIBLE

-------------------------------------------------------------------------------
6. SALIR
-------------------------------------------------------------------------------

- Cierre seguro del programa
- Datos automáticamente guardados
- Scanner cerrado correctamente

===============================================================================