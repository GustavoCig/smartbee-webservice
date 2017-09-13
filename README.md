# smartbee-webservice
Webservice under development for the Smartbee project.

Its function is to provide the applications developed for the project (Mobile application, site, etc ...), an option to make available as information collected by the sensors located in the hives.

### Important links 

[Github principal do projeto](https://github.com/antoniorafaelbraga/smartbee)

[Github da aplicação móvel](https://github.com/alissonlimasilva/MonitorTCCapp)


### End Points
The webservice provides the following URIs:

#### URIs GET
+ <b>/colmeia:</b> 
Lists all monitored hives and returns an ArrayJson like the one shown below.

```json 
[  
  {  
    "idColmeia":"string",
    "latitude":"string",
    "longitude":"string",
    "datacriacao":"string",
    "numerocoletas":int
  },
  {  
    "idColmeia":"string",
    "latitude":"string",
    "longitude":"string",
    "datacriacao":"string",
    "numerocoletas":int
  }
]
```

+ <b>   /colmeia/{ID COLMEIA}/info:</b> Returns importants information about the monitored hive in focus. Get hive ID through pathParam(ID COLMEIA) and returns the json below.

```json 
{  
  "idColmeia":"string",
  "latitude":"string",
  "longitude":"string",
  "datacriacao":"string",
  "numerocoletas":int
}
```

+ <b>/apiarios:</b> Returns to ArrayJson with list of all apiaries registered in the system.


```json
[  
   {  
      "idApiario":int,
      "descricao":"string",
      "latitude":"string",
      "longitude":"string"
   },
   {  
      "idApiario":int,
      "descricao":"string",
      "latitude":"string",
      "longitude":"string"
   }
]
```


+ <b>/colmeia/{ID COLMEIA}:</b> Returns a JSON with the last data collected by the sensors in the hive indicated by the parameter.

```json
{  
   "amostra":int,
   "idColmeia":"string",
   "datahora":{  
      "year":2017,
      "month":7,
      "dayOfMonth":20,
      "hourOfDay":21,
      "minute":29,
      "second":40
   },
   "som":double,
   "temperatura":double,
   "umidade":double,
   "dioxido":double,
   "tensaocolmeia":double,
   "tensaorepetidor":double,
   "tensaogateway":double
}
```

OBS: The datetime attribute is of type java.util.Calendar. 

+ <b>/colmeia/{id da colmeia}/{medida}</b>
Receives the hive ID and the metric per parameter by PathParam and returns a JSON containing average, current value, lowest value and highest value collected from metric received.

```json
{  
   "media":valor,
   "minimo":valor,
   "maximo":valor,
   "atual":valor
}
```

+ <b> /colmeias/{id da colmeia}/historico/{tempo em horas}/hour </br>
Or
<br> /colmeias/{id da colmeia}/historico/{tempo em minutos 1 a 59}/min </b>

The server receives the PathParam hive ID and time, which can be in hours or minutes depending on the URI. After processing is returned an ArrayJson containing all the collections in the time interval.

```json
[  
  {  
    "amostra":int,
    "idColmeia":"string",
    "datahora":{  
      "year":2017,
      "month":7,
      "dayOfMonth":22,
      "hourOfDay":11,
      "minute":32,
      "second":34
    },
    "som":double,
    "temperatura":double,
    "umidade":double,
    "dioxido":double,
    "tensaocolmeia":double,
    "tensaorepetidor":double
  }
```
OBS: The datetime attribute is of type java.util.Calendar.

#### URIs POST
+ <b> /add/coleta </b>: Adds a new collection. Expect to receive the Json shown below and return Status Code 200 if the store was successfully held or Status Code 500 for error.

If the datahora key is not included in Json, the server stores the time of receipt.

```json
{  
  "idColmeia":"string",
  "datahora":{  
    "year":valor,
    "month":valor,
    "dayOfMonth":valor,
    "hourOfDay":valor,
    "minute":valor,
    "second":valor
  },
  "som":double,
  "temperatura":double,
  "umidade":double,
  "dioxido":double,
  "tensaocolmeia":double,
  "tensaorepetidor":double,
  "tensaogateway":double
}
]
```

+ <b>/cadastro</b>
The server receives a serialized object in Json as shown below and saved to the database. The system uses the SHA-256 algorithm to generate a password hash code.

This end point returns 3 possible Status Code's as a response:

>> Successful registration: Status Code 200 + Json object with the data sent to the server.


>> Email already used by another user: Status Code 409 (Conflict).


>> Internal server error: Status Code 500
 
```json
{  
  "login":"string",
  "senha":"string",
  "nome":"string"
}
```

+ <b>/login</b>


This end point receives a Json containing login and password as shown below.

```json
{  
  "login":"string",
  "senha":"string"
}
```

After processing returns Status codes indicating the error in authentication or a Json containing the token needed to access the URIs of the server.

>> Login does not exist = Status code 400

>> User Profile is not activated = Status code 403

>> Incorrect password = Status code 401

>> If login occurs successfully returns Status code 200 and Json shown below with login, password and token generated.

```json
{  
  "login":"string",
  "senha":"string",
  "token":"string",
}
```

### Features

#### Authentication

To authenticate to the system it is necessary to have a token that the system makes available, it is possible to obtain this token following the steps indicated in the previous section. After purchased token, each request that is made to the system is required to send a header with Authentication key and <b> "Bearer + <value of token>" </ b> value. Below is an example of how the request should be made.

```html
Header 
    key = Authentication 
    value = Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1MDUzMjI0ODEsImlzcyI6Im
```
