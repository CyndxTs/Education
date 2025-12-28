
/* [/]
 >> Project:    PoggersLoggers
 >> File:       main.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "solution.h"
#define MAXUSU 100
#define MAXLOG 500

// Modulo Principal
int main(int argc, char** argv) {
    // Apertura de Archivos
    ifstream archUsu,archLog,archValoraciones;
    ofstream archRepUsu,archRepLog;
    abrirArchivo_IFS(archUsu,"Usuarios.txt");
    abrirArchivo_IFS(archLog,"Logs.txt");
    abrirArchivo_IFS(archValoraciones,"Valoraciones.txt");
    abrirArchivo_OFS(archRepUsu,"ReporteUsuarios.txt");
    abrirArchivo_OFS(archRepLog,"ReporteLogs.txt");
    // Declaracion de Variables
    char arrUsu_Priv[MAXUSU]{};
    int arrUsu_ID[MAXUSU]{},arrUsu_FechaNac[MAXUSU]{},arrUsu_CantValoraciones[MAXUSU]{};
    int arrUsu_MaxLikes[MAXUSU]{},arrUsu_SumaValoraciones[MAXUSU]{},cantUsu = 0;
    int arrLog_UsuID[MAXLOG]{},arrLog_InstanteLogin[MAXLOG]{},arrLog_Fecha[MAXLOG]{};
    int arrLog_InstanteValoracion[MAXLOG]{},arrLog_TiempoTranscurrido[MAXLOG]{},cantLog = 0;
    //  TAREA 1 ||  Carga de Datos desde Usuarios.txt
    cargarUsuarios(archUsu,arrUsu_ID,arrUsu_Priv,arrUsu_FechaNac,cantUsu);
    //  TAREA 2 ||  Carga de Datos desde Logs.txt
    cargarLogs(archLog,arrLog_UsuID,arrLog_InstanteLogin,arrLog_Fecha,cantLog);
    //  TAREA 3 ||  Carga de Datos desde Valoraciones.txt
    cargarValoraciones(archValoraciones,arrUsu_ID,arrUsu_FechaNac,arrUsu_CantValoraciones,
                       arrUsu_MaxLikes,arrUsu_SumaValoraciones,cantUsu,arrLog_UsuID,
                       arrLog_InstanteLogin,arrLog_Fecha,arrLog_InstanteValoracion,
                       arrLog_TiempoTranscurrido,cantLog);
    //  TAREA 4 ||  Eliminacion de Registros de Usuarios Sin Valoraciones
    eliminarUsuariosSinValoraciones(arrUsu_ID,arrUsu_Priv,arrUsu_FechaNac,arrUsu_CantValoraciones,
                                    arrUsu_MaxLikes,arrUsu_SumaValoraciones,cantUsu,
                                    arrLog_UsuID,arrLog_InstanteLogin,arrLog_Fecha,
                                    arrLog_InstanteValoracion,arrLog_TiempoTranscurrido,cantLog);
    //  TAREA 5 ||  Ordenamiento de Arreglos 'Usu' (Para Primer Reporte)
    ordenarArreglos_Usuarios(arrUsu_ID,arrUsu_Priv,arrUsu_FechaNac,arrUsu_CantValoraciones,
                             arrUsu_MaxLikes,arrUsu_SumaValoraciones,cantUsu);
    //  TAREA 6 ||  Emision de Reporte de Valoraciones de Usuarios
    emitirReporteUsuarios(archRepUsu,archUsu,arrUsu_ID,
                           arrUsu_Priv,arrUsu_FechaNac,
                           arrUsu_CantValoraciones,arrUsu_MaxLikes,
                           arrUsu_SumaValoraciones,cantUsu);
    //  TAREA 7 ||  Ordenamiento de Arreglos 'Log' (Para Segundo Reporte)
    ordenarArreglos_Logs(arrLog_UsuID,arrLog_InstanteLogin,arrLog_Fecha,arrLog_InstanteValoracion,
                          arrLog_TiempoTranscurrido,cantLog);
    //  TAREA 8 ||  Emision de Reporte de Logs Con Valoraciones por Usuario por Fecha
    emitirReporte_Logs(archRepLog,arrLog_UsuID,arrLog_InstanteLogin,arrLog_Fecha,
                        arrLog_InstanteValoracion,arrLog_TiempoTranscurrido,cantLog);

    return 0;
}
