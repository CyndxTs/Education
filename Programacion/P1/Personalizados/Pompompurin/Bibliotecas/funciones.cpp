
/*]
 >> Project:    Pompompurin
 >> Autor:      Candy
 >> File:       funciones.cpp
[*/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include "funciones.h"
#define DIMLINEA 92
#define MAXTEXTO 20
#define ING_POR_REPRO 0.00325

                  /*  - / > [ Funciones en main() ] < / -  */

// Modulo de Emision de Reporte Parametrizado
void emitirReporte(const char *archReporteDIR,const char *archTwitchDIR,int &f_ini,int &f_fin) {
    // Declaracion de variables
    int f_creacion,codNum,numSeguidores,numCanal = 0,numStreams_General = 0,t_duracionTotal_General = 0,maxIng_codNum = 0;
    double ingreso_General = 0,maxIngreso = 0;
    char codLetra,maxIng_codLetra = 0;
    // Apertura de archivos
    ifstream archTwitch;
    abrirArchivo_IFS(archTwitch,archTwitchDIR);
    ofstream archReporte;
    abrirArchivo_OFS(archReporte,archReporteDIR);
    // Inicializacion de reporte
    archReporte<<fixed;
    archReporte.precision(2);
    imprimirTitulo(archReporte,f_ini,f_fin);
    // Iterativa del proceso de impresion
    while (1) {
        f_creacion = leerValorTemporal(archTwitch,'F');
        if (f_creacion == -1) break;  // Validacion de salida
        if (f_creacion >= f_ini && f_creacion <= f_fin) {  // Validacion por fecha de creacion
            numCanal++;
            archTwitch>>codLetra>>codNum>>ws;
            archReporte<<setw(11)<<"CANAL No."<<numCanal<<endl;
            imprimirEncabezado(archReporte,'B');
            archReporte<<setw(8)<<" ";
            procesarNombreDeCanal(archReporte,archTwitch);
            archTwitch>>numSeguidores;
            archReporte<<setw(9)<<codLetra<<codNum<<setw(8)<<" ";
            imprimirValorTemporal(archReporte,f_creacion,'F');
            archReporte<<setw(22)<<numSeguidores<<endl;
            procesarUltimasReproduccionesDeCanal(archReporte,archTwitch,codLetra,codNum,numStreams_General,t_duracionTotal_General,ingreso_General,
                                                 maxIngreso,maxIng_codLetra,maxIng_codNum);
            imprimirLinea(archReporte,'=',DIMLINEA);
        } else while (archTwitch.get() != '\n');  // Descarte de linea
    }
    procesarResumenFinal(archReporte,numStreams_General,t_duracionTotal_General,ingreso_General,maxIngreso,maxIng_codLetra,maxIng_codNum);
}

              /*  - / > [ Funciones de Orden Principal ] < / -  */

// Modulo de impresion de titulo de reporte parametrizado
void imprimirTitulo(ofstream &archReporte,int f_ini,int f_fin) {
    imprimirEncabezado(archReporte,'A');
    archReporte<<setw((DIMLINEA+2)/2)<<"FECHAS DE CREACION ENTRE EL ";  // +28tit  -10f_ini   -6tit  - 10f_fin  == +2
    imprimirValorTemporal(archReporte,f_ini,'F');
    archReporte<<" Y EL ";
    imprimirValorTemporal(archReporte,f_fin,'F');
    archReporte<<endl;
    imprimirLinea(archReporte,'=',DIMLINEA);
}
// Modulo de procesamiento de nombre de canal
void procesarNombreDeCanal(ofstream &archReporte,ifstream &archTwitch) {
    // Declaracion de variables
    int numCar = 0;
    char letra;
    // Procesamiento de nombre
    while(1) {
        letra = archTwitch.get();
        if (letra == ' ') break;  // Validacion de salida
        pasarMayuscula(letra);
        archReporte.put(letra);
        numCar++;
    }
    // Impresion de espaciado restante
    archReporte<<setw(MAXTEXTO - numCar)<<" ";
}
// Modulo de procesamiento de ultimas reproducciones de canal
void procesarUltimasReproduccionesDeCanal(ofstream &archReporte,ifstream &archTwitch,int codLetra,int codNum,int &numStreams_General,
                                          int &t_duracionTotal_General,double &ingreso_General,double &maxIngreso,char &maxIng_codLetra,
                                          int &maxIng_codNum) {
    // Declaracion de variables
    int f_publicacion,t_duracion,numReproducciones,f_ultPublicacion_Canal = 0,t_duracionTotal_Canal = 0,totalReproducciones_Canal = 0;
    // Procesamiento de ultimas reproducciones
    imprimirEncabezado(archReporte,'C');
    while (1) {
        if (archTwitch.get() == '\n') break;  // Validacion de salida
        numStreams_General++; // actualizacion de acumulador global de cantidad de streams
        f_publicacion = leerValorTemporal(archTwitch,'F');
        t_duracion = leerValorTemporal(archTwitch,'H');
        archTwitch>>numReproducciones;
        archReporte<<setw(12)<<" ";
        imprimirValorTemporal(archReporte,f_publicacion,'F');
        archReporte<<setw(23)<<" ";
        imprimirValorTemporal(archReporte,t_duracion,'H');
        archReporte<<setw(28)<<numReproducciones;
        archReporte<<endl;
        // Actualizacion de acumuladores locales
        t_duracionTotal_Canal += t_duracion;
        f_ultPublicacion_Canal = f_publicacion;
        totalReproducciones_Canal += numReproducciones;
    }
    imprimirLinea(archReporte,'-',DIMLINEA);
    procesarResumenDeCanal(archReporte,t_duracionTotal_Canal,f_ultPublicacion_Canal,totalReproducciones_Canal,codLetra,codNum,
                           t_duracionTotal_General,ingreso_General,maxIngreso,maxIng_codLetra,maxIng_codNum);
}
// Modulo de procesamiento de resumen final
void procesarResumenFinal(ofstream &archReporte,int &numStreams_General,int &t_duracionTotal_General,double &ingreso_General,
                          double &maxIngreso,char &maxIng_codLetra,int &maxIng_codNum) {
    archReporte<<setw(20)<<"RESUMEN DEL FINAL:"<<endl;
    archReporte<<setw(54)<<"CANTIDAD TOTAL DE STREAMS COLOCADOS POR LOS CANALES:"<<setw(15)<<numStreams_General<<endl;
    archReporte<<setw(43)<<"DURACION TOTAL DE LOS STREAMS PUBLICADOS:"<<setw(17)<<" ";
    imprimirValorTemporal(archReporte,t_duracionTotal_General,'H');
    archReporte<<endl<<setw(34)<<"INGRESOS TOTALES POR PUBLICIDAD:"<<setw(26)<<"$"<<setw(9)<<ingreso_General<<endl;
    archReporte<<setw(44)<<"CANAL CON MAYORES INGRESOS POR PUBLICIDAD:"<<setw(5)<<maxIng_codLetra<<maxIng_codNum<<" CON  $"<<setw(9)<<maxIngreso<<endl;
    imprimirLinea(archReporte,'=',DIMLINEA);
}

             /*  - / > [ Funciones de Orden Secundario ] < / -  */

// Modulo de procesamiento de resumen de canal
void procesarResumenDeCanal(ofstream &archReporte,int t_duracionTotal_Canal,int f_ultPublicacion_Canal,int totalReproducciones_Canal,
                            int codLetra,int codNum,int &t_duracionTotal_General,double &ingreso_General,double &maxIngreso,
                            char &maxIng_codLetra,int &maxIng_codNum) {
    // Declaracion de variables
    double ingreso_Canal = ING_POR_REPRO*totalReproducciones_Canal;
    // Actualizacion de acumuladores globales
    t_duracionTotal_General += t_duracionTotal_Canal;
    ingreso_General += ingreso_Canal;
    if (ingreso_Canal > maxIngreso) {
        maxIngreso = ingreso_Canal;
        maxIng_codLetra = codLetra;
        maxIng_codNum = codNum;
    }
    // Impresion de resumen
    archReporte<<setw(24)<<"RESUMEN DEL CANAL:"<<endl;
    archReporte<<setw(43)<<"DURACION TOTAL DE LAS REPRODUCCIONES:"<<setw(2)<<" ";
    imprimirValorTemporal(archReporte,t_duracionTotal_Canal,'H');
    archReporte<<endl<<setw(25)<<"ULTIMA PUBLICACION:"<<setw(20)<<" ";
    imprimirValorTemporal(archReporte,f_ultPublicacion_Canal,'F');
    archReporte<<endl<<setw(30)<<"TOTAL DE REPRODUCCIONES:"<<setw(20)<<totalReproducciones_Canal<<endl;
    archReporte<<setw(30)<<"INGRESOS POR PUBLICIDAD:"<<setw(17)<<"$ "<<ingreso_Canal<<endl;
}

                  /*  - / > [ Funciones Auxiliares ] < / -  */

// Modulo de Apertura de Archivos IFSTREAM
void abrirArchivo_IFS(ifstream &arch,const char *archDIR) {
    arch.open(archDIR,ios::in);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<archDIR<<"'."<<endl;
    exit(1);
}
// Modulo de Apertura de Archivos OFSTREAM
void abrirArchivo_OFS(ofstream &arch,const char *archDIR) {
    arch.open(archDIR,ios::out);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<archDIR<<"'."<<endl;
    exit(1);
}
// Modulo de impresión de datos en linea
void imprimirLinea(ofstream &archSalida,char simbolo,int medida) {
    for(int i=0;i<medida;i++) archSalida.put(simbolo);
    archSalida<<endl;
}
// Modulo de impresión de encabezados de reporte
void imprimirEncabezado(ofstream &archSalida,char tipo) {
    if(tipo == 'A') {
        archSalida<<setw((DIMLINEA+20)/2)<<"PLATAFORMA TP_Twitch"<<endl;
        archSalida<<setw((DIMLINEA+33)/2)<<"REGISTRO DE LOS CANALES AFILIADOS"<<endl;
    } else if(tipo == 'B') {
        archSalida<<setw(8)<<" "<<left<<setw(MAXTEXTO)<<"NOMBRE"<<right<<setw(14)<<"CODIGO";
        archSalida<<setw(16)<<"CREADO EL"<<setw(30)<<"NUMERO DE SEGUIDORES";
        archSalida<<endl;
    } else {
        imprimirLinea(archSalida,'-',DIMLINEA);
        archSalida<<setw(28)<<"ULTIMAS REPRODUCCIONES"<<endl;
        archSalida<<setw(32)<<"FECHA DE PUBLICACION";
        archSalida<<setw(26)<<"TIEMPO DE DURACION";
        archSalida<<setw(32)<<"NUMERO DE REPRODUCCIONES";
        archSalida<<endl;
    }
}
// Modulo de compactacion de valor temporal
int compactarValorTemporal(int dd_hor,int mm_min,int aa_seg,char tipo) {
    if (tipo == 'F') return dd_hor + 100*mm_min + 10000*aa_seg;
    return 3600*dd_hor + 60*mm_min + aa_seg;
}
// Modulo de lectura de valor temporal
int leerValorTemporal(ifstream &archEntrada,char tipo) {
    int dd_hor,mm_min,aa_seg;
    char c;
    archEntrada>>dd_hor;
    if (archEntrada.eof()) return -1; // Validacion por fin de archivo
    archEntrada>>c>>mm_min>>c>>aa_seg;
    return compactarValorTemporal(dd_hor,mm_min,aa_seg,tipo);
}
// Modulo de descompactacion de valor temporal
void descompactarValorTemporal(int datoTemporal,int &dd_hor,int &mm_min,int &aa_seg,char tipo) {
    if (tipo == 'F') {
        aa_seg = datoTemporal/10000;
        mm_min = (datoTemporal - aa_seg*10000)/100;
        dd_hor = datoTemporal - aa_seg*10000 - mm_min*100;
    } else {
        dd_hor = datoTemporal/3600;
        mm_min = (datoTemporal - dd_hor*3600)/60;
        aa_seg = datoTemporal - dd_hor*3600 - mm_min*60;
    }
}
// Modulo de impresion de valor temporal
void imprimirValorTemporal(ofstream &archSalida,int datoTemporal,char tipo) {
    int dd_hor,mm_min,aa_seg,aux = (tipo == 'F')? 4:2;
    char simbolo = (tipo == 'F') ? '/' : ':';
    descompactarValorTemporal(datoTemporal,dd_hor,mm_min,aa_seg,tipo);
    archSalida.fill('0');
    archSalida<<setw(2)<<dd_hor<<simbolo<<setw(2)<<mm_min<<simbolo<<setw(aux)<<aa_seg;
    archSalida.fill(' ');
}
// Modulo de traspaso de caracter a mayuscula
void pasarMayuscula(char &caracter) {
    if (caracter < 'a' or caracter > 'z') return;  // De esta manera se pueden ignorar caracteres que no son "letras"
    caracter -= 'a' - 'A';
}
