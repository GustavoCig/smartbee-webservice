# smartbee-webservice
Webservice under development for the Smartbee project.

Its function is to provide the applications developed for the project (Mobile application, site, etc ...), an option to make available as information collected by the sensors located in the hives.

### Important links 

[Github principal do projeto](https://github.com/antoniorafaelbraga/smartbee)

[Github da aplicação móvel](https://github.com/alissonlimasilva/MonitorTCCapp)

### Features

The webservice provides the following URIs:

+ <b>/colmeia:</b> lista todas as colmeias monitoradas.

+ <b>/colmeia/{ID DA COLMEIA}:</b> Devolve os últimos dados coletados pelos sensores na colmeia indicada através do parâmetro.

+ <b>/colmeia/{ID DA COLMEIA}/info:</b> Responde informações importantes sobre a colmeia monitorada em foco

+ <b>/apiarios:</b> Retorna uma lista com todos os apiários cadastrados no sistema.
