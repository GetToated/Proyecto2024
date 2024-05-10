<h1 align= center>PROYECTO AHORCADO</h1>
<p align = center><img src = https://play-lh.googleusercontent.com/27_vqM7JDATQmp4ZRF_WUdq9jRRtcDq7iB9Ja80FyQSKr4vSX80oif_br8LBN1jJf-c=w240-h480-rw></p>
<h2>Se diseña el juego del Ahorcado en Java orientado a objetos. El juego constará de 2 modos de juego:</h2>

 <h2>Modo Clásico.</h2>
 <ul>
   <li>Tendras 10 intentos para adivinar la palabra</li>
   <li>Habrá una puntuacion que podras conseguir</li>
   <li>Habra Logros ocultos que puedes conseguir</li>
 </ul>

 <h2>Modo Cronometrado.</h2>
 <ul>
   <li>Tendras 60 segundos para adivinar la palabra</li>
   <li>Habrá una puntuacion que podras conseguir</li>
   <li>Habra Logros ocultos que puedes conseguir</li>
 </ul>
<h2>Atributos necesarios:</h2>
<ul>
  <li>Numero de intentos disponibles</li>
  <li>Tiempo límite para el modo cronometrado</li>
  <li>Una palabra oculta que se tenga que adivinar</li>
  <li>Lista de palabras</li>
</ul>

<h2>Clases:</h2>
<ul>
  <li>AhorcadoPrincipal: Será la que maneja la logica del juego incluyendo la interaccion con el usuario</li>
  <li>PalabraEscondida: Se encargara de manejar la palabra oculta y el estado de las letras adivinadas</li>
  <li>ModosDeJuego: Implementará las instrucciones necesarios de cada modo de juego</li>
  <li>Logros: Registrara los Logros de la partida</li>
  <li>Jugador: Gestionara la informacion del jugador</li>
  <li>Puntuacion: Proporcionara metodos para actualizar la puntuacion durante la partida</li>
  <li>TipoDeJugador: Extendera de Jugador. Dependiendo de si el jugador dice que se le da bien jugar o no, se le proporcionara una lista mas dificil o al la inversa</li>
</ul>

<h2>Métodos:</h2>

<ul>
  <li>Se usaran constructores para inicializar los atributos de cada clase</li>
  <li>Se usaran GET y SET</li>
  <li>Metodos para mostrar la informacion del juego</li>
  <li>Metodos para la logica, adivinar letras, estado de la partida etc</li>
  <li>Metodos para el tiempo del modo cronometrado</li>
</ul>

<h3>Una implementacion seria permitir al jugador ingresar su nombre y edad, seleccionar el modo de juego, jugar tantas veces como desee y registrar sus logros. La aplicación mostrará las instrucciones correspondientes a cada modo de juego y permitirá al jugador adivinar la palabra oculta. Una vez que se completa la partida, se mostrará si el jugador ganó o perdió, junto con la palabra oculta, los logros obtenidos y la puntuacion</h3>
