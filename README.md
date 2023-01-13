# Mauricio Zanon (mbz0505@gmail.com)

[![Generic badge](https://img.shields.io/badge/java-1.8-red.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/Spring%20Boot-2.7.8-green.svg)]

## Modo de uso

Para poder ser ejecutada localmente se debe correr el método 'main' ubicado en el archivo `WeatherApiApplication.java`.

Al terminar de inicializarse se expone un único endpoint en el puerto 8080 el cuál puede consumirse usando el siguiente curl:

```
curl --location --request GET 'http://localhost:8080/condicionActual?nombreCiudad=Buenos aires'
```

En el parámetro `nombreCiudad` debemos ingresar el nombre de la ciudad de la cual queremos ver el clima actual, lo cual nos devolverá un json con la siguiente estructura:

```
{
    "nombreCiudad": string,
    "fechaYHora": string,
    "tiempoUnix": string,
    "clima": string,
    "icono": number,
    "estaLloviendo": boolean,
    "tipoDePrecipitacion": string,
    "esDeDia": boolean,
    "linkMovil": string,
    "link": string
}
```
