
/* [/]
 >> Project:    PoggersLoggers
 >> File:       solution.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "solution.h"
#define XD -1
#define MAXTEXTO 20

                      /*  - / > [ - TAREA 1 - ] < / -  */

// Modulo de Carga de Datos de Usuario
void cargarUsuarios(ifstream &archUsuarios,int *arrUsu_ID,char *arrUsu_Priv,
                    int *arrUsu_FechaNac,int &cantUsu) {
    // Declaracion de Variables
    int id,fechaNacimiento;
    char privacidad;
    // Proceso de Carga
    while(1) {
        archUsuarios>>id;
        if(archUsuarios.eof()) break;                                           // Validacion de Fin de Archivo
        archUsuarios>>ws;
        while(archUsuarios.get() != ' ');                                       // Descarte de Nombre
        archUsuarios>>privacidad;
        fechaNacimiento = leerValorTemporal(archUsuarios,'F');
        // Insercion Ordenada de Datos de Usuario
        insertarOrdenado_Usuario(id,privacidad,fechaNacimiento,arrUsu_ID,arrUsu_Priv,
                                 arrUsu_FechaNac,cantUsu);
    }
}
// Modulo de Inserción Ordenada de Datos de Usuario
void insertarOrdenado_Usuario(int id, char privacidad, int fechaNacimiento,
                              int *arrUsu_ID,char *arrUsu_Priv,int *arrUsu_FechaNac,
                              int &cantUsu){
    // Declaracion de Variables
    int i = 0;
    // Proceso de Insercion Ordenada
    while(i <= cantUsu) {
        if(id < arrUsu_ID[i] or i == cantUsu) {                                 // Validacion de Insercion
            for(int j = cantUsu;j > i;j--) {                                    // Desplazamiento para Insercion
                arrUsu_ID[j] = arrUsu_ID[j-1];
                arrUsu_Priv[j] = arrUsu_Priv[j-1];
                arrUsu_FechaNac[j] = arrUsu_FechaNac[j-1];
            }
            // Insercion de Datos de Usuario
            arrUsu_ID[i] = id;
            arrUsu_Priv[i] = privacidad;
            arrUsu_FechaNac[i] = fechaNacimiento;
            cantUsu++;
            break;
        }
        i++;
    }
}

                      /*  - / > [ - TAREA 2 - ] < / -  */

// Modulo de Carga de Datos de Log
void cargarLogs(ifstream &archLogs,int *arrLog_UsuID,int *arrLog_InstanteLogin,
                int *arrLog_Fecha, int &cantLog) {
    // Declaracion de Variables
    int id,instanteLogin,fecha;
    // Proceso de Carga
    while(1) {
        archLogs>>id;
        if(archLogs.eof()) break;                                               // Validacion de Fin de Archivo
        instanteLogin = leerValorTemporal(archLogs,'H');
        fecha = leerValorTemporal(archLogs,'F');
        arrLog_UsuID[cantLog] = id;
        arrLog_InstanteLogin[cantLog] = instanteLogin;
        arrLog_Fecha[cantLog] = fecha;
        cantLog++;
    }
}

                      /*  - / > [ - TAREA 3 - ] < / -  */

// Modulo de Actualizacion de Reigstros por Valoraciones Realizadas
void cargarValoraciones(ifstream &archValoraciones,int *arrUsu_ID,int *arrUsu_FechaNac,
                        int *arrUsu_CantValoraciones,int *arrUsu_MaxLikes,int *arrUsu_SumaValoraciones,
                        int cantUsu,int *arrLog_UsuID,int *arrLog_InstanteLogin,int *arrLog_Fecha,
                        int *arrLog_InstanteValoracion,int *arrLog_TiempoTranscurrido, int &cantLog) {
    // Declaracion de Variables
    int usuID,fecha,instanteValoracion,posLog,posUsu,valoracion,numLikes;
    // Proceso de Actualizacion de Registros
    while(1) {
        archValoraciones>>usuID;
        if(archValoraciones.eof()) break;                                       // Validacion de Fin de Archivo
        posUsu = obtenerPosicion_Bin_Asc(usuID,arrUsu_ID,cantUsu);
        if(posUsu != XD) {                                                      // Validacion de Existencia de Usuario
            instanteValoracion = leerValorTemporal(archValoraciones,'H');
            fecha = leerValorTemporal(archValoraciones,'F');
            posLog = obtenerPosicion_UltimoLog(usuID,instanteValoracion,fecha,arrLog_UsuID,
                                               arrLog_InstanteLogin,arrLog_Fecha,cantLog);
            if(posLog != XD) {                                                  // Validacion de Existencia de Log
                archValoraciones>>ws;
                while(archValoraciones.get() != ' ');                           // Descarte de Comentario
                archValoraciones>>valoracion>>numLikes;
                if(numLikes > arrUsu_MaxLikes[posUsu]) arrUsu_MaxLikes[posUsu] = numLikes;   // Validacion por Nuevo Maximo de Likes
                arrUsu_CantValoraciones[posUsu]++;
                arrUsu_SumaValoraciones[posUsu] += valoracion;
                if(arrLog_InstanteValoracion[posLog] != 0) {                    // Validacion de Existencia de una Valoracion
                    insertarElemento_Log(usuID,arrLog_InstanteLogin[posLog],fecha,instanteValoracion,
                                         instanteValoracion - arrLog_InstanteValoracion[posLog],
                                         posLog + 1,arrLog_UsuID,arrLog_InstanteLogin,arrLog_Fecha,
                                         arrLog_InstanteValoracion,arrLog_TiempoTranscurrido,cantLog);
                } else {
                    arrLog_InstanteValoracion[posLog] = instanteValoracion;
                    arrLog_TiempoTranscurrido[posLog] = instanteValoracion - arrLog_InstanteLogin[posLog];
                }
            } else while(archValoraciones.get() != '\n');                       // Descarte de Linea
        } else while(archValoraciones.get() != '\n');                           // Descarte de Linea
    }
}
// Modulo de Busqueda de Posicion de Ultimo Log de Usuario a partir de Ultimo Login en Fecha
int obtenerPosicion_UltimoLog(int usuID,int instanteValoracion,int fecha,
                              int *arrLog_UsuID,int *arrLog_InstanteLogin,int *arrLog_Fecha,
                              int cantLog) {
    // Declaracion de Variables
    int ultimoLogin = -1, posLog = -1;
    // Proceso de Busqueda de Ultimo Login
    for(int i = 0;i < cantLog;i++) {
        if(usuID == arrLog_UsuID[i] and fecha == arrLog_Fecha[i] and
           arrLog_InstanteLogin[i] < instanteValoracion and
           arrLog_InstanteLogin[i] >= ultimoLogin) {                            // Validacion de Condicion de Busqueda
            ultimoLogin = arrLog_InstanteLogin[i];
            posLog = i;
        }
    }
    // Validacion de Retorno por Resultado de Busqueda
    if(posLog == -1) return XD;
    return posLog;
}
// Modulo de Insercion de Datos de Log [Por Valoracion Adicional]
void insertarElemento_Log(int usuID,int instanteLogin,int fecha,int instanteValoracion,
                          int tiempoTranscurrido,int posInsertar,int *arrLog_UsuID,
                          int *arrLog_InstanteLogin,int *arrLog_Fecha,int *arrLog_InstanteValoracion,
                          int *arrLog_TiempoTranscurrido,int &cantLog) {
    // Desplazamiento para Insercion
    for(int i = cantLog;i > posInsertar;i--) {
        arrLog_UsuID[i] = arrLog_UsuID[i-1];
        arrLog_InstanteLogin[i] = arrLog_InstanteLogin[i-1];
        arrLog_Fecha[i] = arrLog_Fecha[i-1];
        arrLog_InstanteValoracion[i] = arrLog_InstanteValoracion[i-1];
        arrLog_TiempoTranscurrido[i] = arrLog_TiempoTranscurrido[i-1];
    }
    // Insercion de Datos de Log
    arrLog_UsuID[posInsertar] = usuID;
    arrLog_InstanteLogin[posInsertar] = instanteLogin;
    arrLog_Fecha[posInsertar] = fecha;
    arrLog_InstanteValoracion[posInsertar] = instanteValoracion;
    arrLog_TiempoTranscurrido[posInsertar] = tiempoTranscurrido;
    cantLog++;
}

                      /*  - / > [ - TAREA 4 - ] < / -  */

// Modulo de Eliminacion de Datos de Registros con Usuarios sin Valoraciones
void eliminarUsuariosSinValoraciones(int *arrUsu_ID,char *arrUsu_Priv,int *arrUsu_FechaNac,
                                     int *arrUsu_CantValoraciones,int *arrUsu_MaxLikes,int *arrUsu_SumaValoraciones,
                                     int &cantUsu,int *arrLog_UsuID,int *arrLog_InstanteLogin,
                                     int *arrLog_Fecha,int *arrLog_InstanteValoracion,int *arrLog_TiempoTranscurrido,
                                     int &cantLog) {
    // Proceso de Eliminacion de Registros de Usuario en Arreglos 'Usu'
    for(int i = 0;i < cantUsu;i++) {
        if(arrUsu_CantValoraciones[i] == 0) {                                   // Validacion de Condicion de Eliminacion
            eliminarElemento_Usuario(i,arrUsu_ID,arrUsu_Priv,arrUsu_FechaNac,arrUsu_CantValoraciones,
                                     arrUsu_MaxLikes,arrUsu_SumaValoraciones,cantUsu);
            i--;
        }
    }
    // Proceso de Eliminacion de Registros de Usuario en Arreglos 'Log'
    for(int i = 0;i < cantLog;i++) {
        if(arrLog_InstanteValoracion[i] == 0) {                                 // Validacion de Condicion de Eliminacion
            eliminarElemento_Log(i,arrLog_UsuID,arrLog_InstanteLogin,arrLog_Fecha,
                                 arrLog_InstanteValoracion,arrLog_TiempoTranscurrido,
                                 cantLog);
            i--;
        }
    }
}
// Proceso de Eliminacion de Datos de Usuario
void eliminarElemento_Usuario(int posEliminar,int *arrUsu_ID,char *arrUsu_Priv,int *arrUsu_FechaNac,
                              int *arrUsu_CantValoraciones,int *arrUsu_MaxLikes,int *arrUsu_SumaValoraciones,
                              int &cantUsu) {
    for(int i = posEliminar;i < cantUsu;i++) {
        arrUsu_ID[i] = arrUsu_ID[i+1];
        arrUsu_Priv[i] = arrUsu_Priv[i+1];
        arrUsu_FechaNac[i] = arrUsu_FechaNac[i+1];
        arrUsu_CantValoraciones[i] = arrUsu_CantValoraciones[i+1];
        arrUsu_MaxLikes[i] = arrUsu_MaxLikes[i+1];
        arrUsu_SumaValoraciones[i] = arrUsu_SumaValoraciones[i+1];
    }
    cantUsu--;
}
// Proceso de Eliminacion de Datos de Log
void eliminarElemento_Log(int posEliminar,int *arrLog_UsuID,int *arrLog_InstanteLogin,
                          int *arrLog_Fecha,int *arrLog_InstanteValoracion,int *arrLog_TiempoTranscurrido,
                          int &cantLog) {
    for(int i = posEliminar;i < cantLog;i++) {
        arrLog_UsuID[i] = arrLog_UsuID[i+1];
        arrLog_InstanteLogin[i] = arrLog_InstanteLogin[i+1];
        arrLog_Fecha[i] = arrLog_Fecha[i+1];
        arrLog_InstanteValoracion[i] = arrLog_InstanteValoracion[i+1];
        arrLog_TiempoTranscurrido[i] = arrLog_TiempoTranscurrido[i+1];
    }
    cantLog--;
}

                      /*  - / > [ - TAREA 5 - ] < / -  */

// Modulo de Ordenamiento de Arreglos de Datos de Usuario
void ordenarArreglos_Usuarios(int *arrUsu_ID,char *arrUsu_Priv,int *arrUsu_FechaNac,
                              int *arrUsu_CantValoraciones,int *arrUsu_MaxLikes,
                              int *arrUsu_SumaValoraciones,int cantUsu) {
    for(int i = 0;i < cantUsu - 1;i++){
        for(int j = i + 1;j < cantUsu;j++){
            if(arrUsu_MaxLikes[i] < arrUsu_MaxLikes[j] or
                ((arrUsu_MaxLikes[i] == arrUsu_MaxLikes[j]) and (arrUsu_Priv[i] < arrUsu_Priv[j]))){
                intercambiarValor(i,j,arrUsu_ID);
                intercambiarValor(i,j,arrUsu_Priv);
                intercambiarValor(i,j,arrUsu_FechaNac);
                intercambiarValor(i,j,arrUsu_CantValoraciones);
                intercambiarValor(i,j,arrUsu_MaxLikes);
                intercambiarValor(i,j,arrUsu_SumaValoraciones);
            }
        }
    }
}

                      /*  - / > [ - TAREA 6 - ] < / -  */

// Modulo de Emision de Reporte de Valoraciones de Usuarios
void emitirReporteUsuarios(ofstream &archRep,ifstream &archUsu,int *arrUsu_ID,char *arrUsu_Priv,
                           int *arrUsu_FechaNac,int *arrUsu_CantValoraciones,int *arrUsu_MaxLikes,
                           int *arrUsu_SumaValoraciones,int cantUsu) {
    // Declaracion de Variables & Inicializacion de Atributos de Reporte
    int tamLinea = 109;
    archRep<<fixed;
    archRep.precision(2);
    // Proceso de Emision de Reporte
    imprimirEncabezado_RepUsuarios(archRep,'A',tamLinea);
    for(int i = 0;i < cantUsu;i++) {
        archRep<<setw(10)<<arrUsu_ID[i]<<setw(5)<<" ";
        if(arrUsu_Priv[i] == 'F') {                                             // Validacion por Privacidad
            imprimirLinea(archRep,'>',13,false);
            archRep<<" HIDDEN ";
            imprimirLinea(archRep,'<',13,false);
        } else {
            imprimirNickname(archRep,archUsu,i);
            archRep<<setw(4)<<" ";
            imprimirValorTemporal(archRep,arrUsu_FechaNac[i],'F');
        }
        archRep<<setw(14)<<arrUsu_CantValoraciones[i]<<setw(19)<<arrUsu_MaxLikes[i];
        archRep<<setw(17)<<((double)arrUsu_SumaValoraciones[i]/arrUsu_CantValoraciones[i])<<endl;
    }
    imprimirLinea(archRep,'=',tamLinea,true);
}
// Modulo Auxiliar de Impresion de Encabezados de Reporte
void imprimirEncabezado_RepUsuarios(ofstream &archRep, char tipo,int tamLinea) {
    switch(tipo) {
        case 'A':
            archRep<<setw((tamLinea+35)/2)<<"RESUMEN DE VALORACIONES POR USUARIO"<<endl;
            imprimirLinea(archRep,'=',tamLinea,true);
            archRep<<setw(11)<<"USUARIO"<<setw(4)<<" "<<left<<setw(20)<<"NICKNAME";
            archRep<<right<<setw(14)<<"FECHA NAC."<<setw(22)<<"CANT. VALORACIONES";
            archRep<<setw(14)<<"MAX. LIKES"<<setw(20)<<"PROM. VALORACION"<<endl;
            imprimirLinea(archRep,'-',tamLinea,true);
            break;
    }
}
// Modulo de Impresion de Nombre de Usuario [Formato: @minus]
void imprimirNickname(ofstream &archRep,ifstream &archUsuarios,int posUsu) {
    // Declaracion de Variables
    int medNombre = 0,posLinea = 0;
    char letra;
    // Inicializacion de Archivo
    archUsuarios.clear();
    archUsuarios.seekg(0,ios::beg);
    // Posicionamiento en Linea Respectiva
    while(posLinea < posUsu) {
        while(archUsuarios.get() != '\n');                                           // Descarte de Linea
        posLinea++;
    }
    archUsuarios>>ws;
    while(archUsuarios.get() != ' ');                                                // Descarte de ID
    archUsuarios>>ws;
    // Proceso de Impresion de Nombre de Usuario
    archRep<<"@";
    medNombre++;
    while(1) {
        letra = archUsuarios.get();
        if(letra == ' ') break;                                                 // Validacion de Fin de Nickname
        pasarMinuscula(letra);
        archRep.put(letra);
        medNombre++;
    }
    archRep<<setw(MAXTEXTO - medNombre)<<" ";
}

                      /*  - / > [ - TAREA 7 - ] < / -  */

// Modulo de Ordenamiento de Arreglos de Datos de Log
void ordenarArreglos_Logs(int *arrLog_UsuID,int *arrLog_InstanteLogin,int *arrLog_Fecha,
                          int *arrLog_InstanteValoracion,int *arrLog_TiempoTranscurrido, int cantLog) {
    for(int i = 0;i < cantLog - 1;i++){
        for(int j = i + 1;j < cantLog;j++){
            if(arrLog_Fecha[i] > arrLog_Fecha[j] or
               ((arrLog_Fecha[i] == arrLog_Fecha[j]) and ((arrLog_UsuID[i] > arrLog_UsuID[j]) or
                    ((arrLog_UsuID[i] == arrLog_UsuID[j]) and (arrLog_InstanteValoracion[i] > arrLog_InstanteValoracion[j]))))){
                intercambiarValor(i,j,arrLog_UsuID);
                intercambiarValor(i,j,arrLog_InstanteLogin);
                intercambiarValor(i,j,arrLog_Fecha);
                intercambiarValor(i,j,arrLog_InstanteValoracion);
                intercambiarValor(i,j,arrLog_TiempoTranscurrido);
            }
        }
    }
}

                      /*  - / > [ - TAREA 8 - ] < / -  */

// Modulo de Emision de Reporte de Logs de Valoraciones de Usuario por Fecha
void emitirReporte_Logs(ofstream &archRep,int *arrLog_UsuID,int *arrLog_InstanteLogin,
                        int *arrLog_Fecha,int *arrLog_InstanteValoracion,int *arrLog_TiempoTranscurrido,
                        int cantLog) {
    // Declaracion de Variables
    int tamLinea = 62;
    // Proceso de Emision de Reporte
    imprimirEncabezado_RepLogs(archRep,'A',tamLinea);
    for(int i = 0,ult_Fecha = -1,ult_Usu,ult_Login,referenciaInicial;i < cantLog;i++) {
        if(ult_Fecha != arrLog_Fecha[i]) {                                      // Validacion por Nueva Fecha
            ult_Fecha = arrLog_Fecha[i];
            ult_Usu = -1;
            imprimirLinea(archRep,'=',tamLinea,true);
            archRep<<setw(17)<<">> LOGS DEL '";
            imprimirValorTemporal(archRep,ult_Fecha,'F');
            archRep<<"'"<<endl;
            imprimirLinea(archRep,'-',tamLinea,true);
        }
        if(ult_Usu != arrLog_UsuID[i]) {                                        // Validacion por Nuevo Usuario
            ult_Usu = arrLog_UsuID[i];
            ult_Login = -1;
            archRep<<setw(20)<<"[#] USUARIO :: '"<<ult_Usu<<"'"<<endl;
            imprimirEncabezado_RepLogs(archRep,'B',tamLinea);
            
        }
        if(ult_Login != arrLog_InstanteLogin[i]) {                              // Validacion por Nuevo Login
            ult_Login = arrLog_InstanteLogin[i];
            referenciaInicial = ult_Login;
            archRep<<setw(8)<<"[+] ";
        } else {
            referenciaInicial = arrLog_InstanteValoracion[i - 1];
            archRep<<setw(8)<<" ~  ";
        }
        imprimirValorTemporal(archRep,referenciaInicial,'H');
        archRep<<" --> ";
        imprimirValorTemporal(archRep,arrLog_InstanteValoracion[i],'H');
        archRep<<"  ::  ";
        imprimirValorTemporal(archRep,arrLog_TiempoTranscurrido[i],'H');
        archRep<<endl;        
    }
    imprimirLinea(archRep,'=',tamLinea,true);
}
// Modulo Auxiliar de Impresion de Encabezados de Reporte
void imprimirEncabezado_RepLogs(ofstream &archRep, char tipo,int tamLinea) {
    switch(tipo) {
        case 'A':
            archRep<<setw((tamLinea + 32 - 22)/2)<<"REPORTE DE LOGS DE USUARIOS CON ";
            archRep<<"VALORACIONES POR FECHA"<<endl;
            break;
        default:
            archRep<<setw(16)<<"REF. INI"<<setw(13)<<"REF. FIN";
            archRep<<setw(16)<<"TRANSCURRIDO"<<endl;
            break;
    }
}
