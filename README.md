# Juego de Serpientes y Escaleras en java
El juego es un ejemplo de OPP (pragramación orientado a objetos en java). El juego funciona por la consola de java, por lo cual no tiene interfaz grafica.

## Especificaciones que se piden
Usted ha sido contratado por el Departamento de Sistemas y Procesos para automatizar el juego de mesa llamado Serpientes y Escaleras. Para dicho juego se podrá contar con dos o más jugadores, quienes comienzan con una ficha y se turnan para lanzar un dado que les indicará la cantidad de casillas que deben avanzar. Las fichas se mueven según la numeración del tablero, en sentido ascendente. Si al finalizar un movimiento un jugador cae en una casilla donde comienza una escalera, sube por ella hasta la casilla donde ésta termina. Si, por el contrario, cae en una en donde comienza la cabeza de una serpiente, desciende por ésta hasta la casilla donde finaliza su cola.
### Características del juego
El camino está representado por un arreglo bidireccional de 8x8, en el cual pueden encontrar serpientes (cabezas --> $) y escaleras (inicio --> #), además dicho camino estará enumerado ascendentemente.

Los competidores comenzarán en la misma posición cada uno y se moverán según el valor arrojado por el dado. Si un jugador obtiene un 6 podrá mover y tirar nuevamente el dado. Si un jugador obtiene tres 6 consecutivos, deberá regresar a la casilla inicial y no podrá mover su ficha hasta obtener nuevamente un 6. El jugador que logra llegar a la casilla final es el ganador, sin embargo una de las modalidades se debe llegar a la casilla final con el puntaje justo, de no sacarlo pasara su turno a otro jugador.

Para el juego se manejara un archivo de texto donde se le dará valores a las posiciones correspondientes de los implementos usados en el juego (una posición inicial y una final).

El juego deberá contar con un menú que me permita crear un tablero para iniciar a jugar y guardarlo en un archivo de texto, cargar un tablero ya creado desde un archivo de texto, mostrar las reglas del juego y un vector de los 3 mejores jugadores con la cantidad de juegos ganados.
La información en los archivos de texto verá estructurada de la siguiente manera:

- En la primera línea las posiciones de casilla inicial y casilla final
- En la segunda línea la posición de las serpientes (cabeza y cola)
- En la tercera las posiciones de las escaleras (inicio y fin)

Cuando a una jugador le toque su turno el sistema deberá indicarlo, y
posteriormente mostrar que valor saco al lanzar el dado, seguido de a que numero de casilla
se moverá su ficha. También me deberá mostrar el nombre de aquel jugador que gane la
partida.

Sintaxis del archivo de entrada

![alt tag](https://github.com/Ricardo96r/SerpientesYEscaleras/blob/master/especificaciones/datos.png)

Por ejemplo: Imagine el siguiente camino:

![alt tag](https://github.com/Ricardo96r/SerpientesYEscaleras/blob/master/especificaciones/tablero.png)

Como notará en el ejemplo, en el tablero no se refleja la posición final de la escalera, ni la cola de la serpiente, por ende deberá indicar si el jugador cayó en una cabeza de serpiente a que cola (casilla) se mueve y si cayo en la inicial de una escalera a que final (casilla) se mueve.

## Diagrama de clase (UML)
### App
![alt tag](https://github.com/Ricardo96r/SerpientesYEscaleras/blob/master/UML%20Diagrama%20de%20clase/app.png)

### Datos
![alt tag](https://github.com/Ricardo96r/SerpientesYEscaleras/blob/master/UML%20Diagrama%20de%20clase/datos.png)

### Fichas
![alt tag](https://github.com/Ricardo96r/SerpientesYEscaleras/blob/master/UML%20Diagrama%20de%20clase/fichas.png)

### Tablero
![alt tag](https://github.com/Ricardo96r/SerpientesYEscaleras/blob/master/UML%20Diagrama%20de%20clase/tablero.png)
