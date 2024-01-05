### PROYECTO FINAL SISTEMAS DISTRIBUIDOS


El presente proyecto equivale a dos proyectos (5 y 6) por su complejidad, y debe programarse en 
lenguaje Java además de utilizar el protocolo HTTP para la comunicación.
Para el presente proyecto se programará un sistema distribuido en la nube que realice 
procesamiento para minería de texto e incorporará también un sistema de monitoreo para realizar 
un seguimiento del estado y rendimiento de las estancias en la nube. 
En el siguiente video se explican tres propuestas de solución para la minería de texto, donde la 
primera es la más sencilla, la segunda un poco más difícil y la tercera la más difícil de programar. 
El alumno elige cual opción usar y la cantidad de puntos depende de la elección.
https://youtu.be/6cZsXVngxik
Después de seleccionar la propuesta, tendrá que programarla en un sistema distribuido con las 
consideraciones que se explican a continuación.
El sistema debe contar con una interfaz web, y con tres servidores de procesamiento adicionales al 
servidor web en la nube


En la interfaz web el usuario debe poder introducir una frase, y al dar click para enviar, el sistema 
distribuido deberá hacer la minería de texto para posteriormente enviar en orden de relevancia
una lista con todos los textos contenidos en la base de datos.
Para distribuir el trabajo existen dos soluciones principalmente, la primera es distribuir los textos 
entre los tres servidores, y la segunda es que todos los servidores tengan todos los textos, y 
asignar a cada servidor los textos en los que hará la búsqueda. Se utilizará la segunda opción 
debido a su escalabilidad. 
Los textos donde se realizarán las búsquedas se podrán descargar desde la siguiente liga:
https://drive.google.com/file/d/1BEdEo-yzpTQcbm7_t1am7QKVKdyWYtXD/view?usp=sharing
La distribución del trabajo debe realizarse de manera equitativa de tal manera que todos los 
servidores de procesamiento se encuentren trabajando durante el periodo que dure la consulta.
Sobre el sistema de monitoreo debe existir una interfaz gráfica (en java o en un cliente web) para 
que un usuario que sea el administrador del sistema distribuido pueda visualizar el historial del
porcentaje de uso de CPU y de RAM para cada una de las cuatro instancias en la nube (servidor 
web y los cuatro servidores de procesamiento) de manera simultánea. 
Asimismo, se debe visualizar un costo por el cómputo realizado, el cual que se calcula con el área
bajo la curva del historial de uso de CPU multiplicado por un factor f1 (propuesto por el 
programador) más el área bajo la curva del historial de uso de memoria física multiplicado por un 
factor f2