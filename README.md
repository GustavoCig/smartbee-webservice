# smartbee-webservice
Webservice under development for the Smartbee project.

Its function is to provide the applications developed for the project (Mobile application, site, etc ...), an option to make available as information collected by the sensors located in the hives.

### Important links 

[Github principal do projeto](https://github.com/antoniorafaelbraga/smartbee)

[Github da aplicação móvel](https://github.com/alissonlimasilva/MonitorTCCapp)

### Features

The webservice provides the following URIs:

+ <b>/colmeia:</b> Lists all monitored hives.

+ <b>/colmeia/{ID DA COLMEIA}:</b> Returns the last data collected by the sensors in the hive indicated by the parameter.

+ <b>/colmeia/{ID DA COLMEIA}/info:</b> Returns important information about the monitored hive in focus.

+ <b>/apiarios:</b> Returns a list of all apiaries registered in the system.
