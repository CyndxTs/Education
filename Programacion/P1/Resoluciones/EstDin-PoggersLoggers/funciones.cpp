
/* [/]
 >> Project:    EstDin
 >> File:       funciones.cpp
 >> Author:     
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"
#define MEDLINEA 56
#define XD -1
#define MAXTEXTO 100
#define MAXSESIONESPORUSARIO 20
#define MAXVALORACIONESDURANTESESION 10

void cargarDatos_Usuario(const char *nombArchUsuarios,struct Usuario *arrUsuario,int &cantUsuarios) {
    ifstream archUsuarios;
    abrirArchivo_IFS(archUsuarios,nombArchUsuarios);
    int id;
    char *username;
    while(1) {
        archUsuarios>>id;
        if(archUsuarios.eof()) break;
        arrUsuario[cantUsuarios].id = id;
        arrUsuario[cantUsuarios].username = leerCadenaExacta(archUsuarios,' ');
        archUsuarios>>ws;
        if(archUsuarios.get() == 'V') arrUsuario[cantUsuarios].esPerfilPublico = true;
        else arrUsuario[cantUsuarios].esPerfilPublico = false;
        arrUsuario[cantUsuarios].fechaNacimiento = leerValorTemporal(archUsuarios,'F');
        arrUsuario[cantUsuarios].cantSesiones = 0;
        cantUsuarios++;
    }
}

void cargarDatos_Sesion(const char *nombArchSesiones,struct Usuario *arrUsuario,int cantUsuarios) {
    ifstream archSesiones;
    abrirArchivo_IFS(archSesiones,nombArchSesiones);
    int usu_id,ref_ini,fecha,posUsuario,posSesion;
    while(1) {
        archSesiones>>usu_id;
        if(archSesiones.eof()) break;
        ref_ini = leerValorTemporal(archSesiones,'H');
        fecha = leerValorTemporal(archSesiones,'F');
        posUsuario = obtenerPosicion(usu_id,arrUsuario,cantUsuarios);
        if(posUsuario != XD) {
            posSesion = arrUsuario[posUsuario].cantSesiones;
            if(posSesion == 0) arrUsuario[posUsuario].sesionesDeUsuario = new struct Sesion[MAXSESIONESPORUSARIO];
            arrUsuario[posUsuario].sesionesDeUsuario[posSesion].fecha = fecha;
            arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_ini = ref_ini;
            arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_fin = 0;
            arrUsuario[posUsuario].sesionesDeUsuario[posSesion].cantValoracionesDuranteSesion = 0;
            arrUsuario[posUsuario].cantSesiones++;
        }
    }
}

void cargarDatos_Valoracion(const char *nombArchValoraciones,struct Usuario *arrUsuario,int cantUsuarios) {
    ifstream archValoraciones;
    abrirArchivo_IFS(archValoraciones,nombArchValoraciones);
    int usu_id,ref_ini,fecha,valoracion,numLikes,posUsuario,posSesion,posValoracion;
    char *comentario;
    while(1) {
        archValoraciones>>usu_id;
        if(archValoraciones.eof()) break;
        ref_ini = leerValorTemporal(archValoraciones,'H');
        fecha = leerValorTemporal(archValoraciones,'F');
        comentario = leerCadenaExacta(archValoraciones,' ');
        archValoraciones>>valoracion>>numLikes;
        posUsuario = obtenerPosicion(usu_id,arrUsuario,cantUsuarios);
        if(posUsuario != XD) {
            posSesion = obtenerPosicion(ref_ini,fecha,arrUsuario[posUsuario].sesionesDeUsuario,arrUsuario[posUsuario].cantSesiones);
            if(posSesion != XD) {
                posValoracion = arrUsuario[posUsuario].sesionesDeUsuario[posSesion].cantValoracionesDuranteSesion;
                if(posValoracion == 0) arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion = new struct Valoracion[MAXVALORACIONESDURANTESESION];
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_ini = ref_ini;
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_fin = 0;
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].numLikes = numLikes;
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].comentario = comentario;
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].valoracion = valoracion;
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].cantValoracionesDuranteSesion++;
            }
        }
    }
}

int obtenerPosicion(int id,struct Usuario *arrUsuario,int cantUsuarios) {
    for(int i = 0;i < cantUsuarios;i++) if(arrUsuario[i].id == id) return i;
    return XD;
}
int obtenerPosicion(int ref_ini,int fecha,struct Sesion *arrSesiones,int cantSesiones) {
    int posResultado = XD;
    for(int i = 0;i < cantSesiones;i++) {
        if(arrSesiones[i].fecha == fecha) {
            if(ref_ini > arrSesiones[i].referenciaGeneral.ref_ini) {
                posResultado = i;
            }
        }
    }
    return posResultado;
}

void actualizarReferencias(struct Usuario *arrUsuario,int cantUsuarios) {
    for(int posUsuario = 0,cantSesiones;posUsuario < cantUsuarios;posUsuario++) {
        cantSesiones = arrUsuario[posUsuario].cantSesiones;
        for(int posSesion = 0,cantValoraciones;posSesion < cantSesiones;posSesion++) {
            cantValoraciones = arrUsuario[posUsuario].sesionesDeUsuario[posSesion].cantValoracionesDuranteSesion;
            if(cantValoraciones > 0) {
                if(cantValoraciones > 1) {
                    for(int posValoracion = 0;posValoracion < cantValoraciones - 1;posValoracion++) {
                        arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_fin = arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion + 1].referencia.ref_ini;
                    }
                }
                arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_fin = arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[cantValoraciones - 1].referencia.ref_ini;
            }
        }
    }
}

void imprimirReporte(const char *nombArchReporte,struct Usuario *arrUsuario,int cantUsuarios) {
    ofstream archReporte;
    abrirArchivo_OFS(archReporte,nombArchReporte);
    archReporte<<setw((MEDLINEA + 33)/2)<<"LOGS DE USUARIOS CON VALORACIONES"<<endl;
    for(int posUsuario = 0,cantSesiones;posUsuario < cantUsuarios;posUsuario++) {
        imprimirLinea(archReporte,'=',MEDLINEA,true);
        archReporte<<"[#] USUARIO :: ["<<arrUsuario[posUsuario].id<<"] - ";
        archReporte<<((arrUsuario[posUsuario].esPerfilPublico)?arrUsuario[posUsuario].username:"PERFIL PRIVADO")<<endl;
        imprimirLinea(archReporte,'-',MEDLINEA,true);
        cantSesiones = arrUsuario[posUsuario].cantSesiones;
        for(int posSesion = 0,ultimaFechaSesion = 0,cantValoraciones;posSesion < cantSesiones;posSesion++) {
            cantValoraciones = arrUsuario[posUsuario].sesionesDeUsuario[posSesion].cantValoracionesDuranteSesion;
            if(cantValoraciones != 0) {
                if(ultimaFechaSesion < arrUsuario[posUsuario].sesionesDeUsuario[posSesion].fecha) {
                    if(ultimaFechaSesion != 0) imprimirLinea(archReporte,'*',MEDLINEA,true);
                    ultimaFechaSesion = arrUsuario[posUsuario].sesionesDeUsuario[posSesion].fecha;
                    archReporte<<setw((MEDLINEA - 1)/2)<<"LOGS DEL '";
                    imprimirValorTemporal(archReporte,ultimaFechaSesion,'F');
                    archReporte<<"'"<<endl;
                    imprimirLinea(archReporte,'-',MEDLINEA,true);
                    archReporte<<setw(20)<<"REF. INI"<<setw(14)<<"REF. FIN";
                    archReporte<<setw(16)<<"TRANSCURRIDO"<<endl;
                }
                archReporte<<setw(12)<<"[+] ";
                imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_ini,'H');
                archReporte<<"  >>  ";
                if(cantValoraciones > 1) {
                    imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[0].referencia.ref_ini,'H');
                    archReporte<<"  ::  ";
                    imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[0].referencia.ref_ini - arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_ini,'H');
                    archReporte<<endl;
                    for(int posValoracion = 0;posValoracion < cantValoraciones - 1;posValoracion++) {
                        archReporte<<setw(12)<<" ~  ";
                        imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_ini,'H');
                        archReporte<<"  >>  ";
                        imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_fin,'H');
                        archReporte<<"  ::  ";
                        imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_fin - arrUsuario[posUsuario].sesionesDeUsuario[posSesion].valoracionesDuranteSesion[posValoracion].referencia.ref_ini,'H');
                        archReporte<<endl;
                    }
                } else {
                    imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_fin,'H');
                    archReporte<<"  ::  ";
                    imprimirValorTemporal(archReporte,arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_fin - arrUsuario[posUsuario].sesionesDeUsuario[posSesion].referenciaGeneral.ref_ini,'H');
                    archReporte<<endl;
                }
            }
        }
    }
    imprimirLinea(archReporte,'=',MEDLINEA,true);
}


// Modulo de Apertura de Archivos IFSTREAM
void abrirArchivo_IFS(ifstream &arch, const char *nombArch) {
    arch.open(nombArch,ios::in);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
// Modulo de Apertura de Archivos OFSTREAM
void abrirArchivo_OFS(ofstream &arch, const char *nombArch) {
    arch.open(nombArch,ios::out);
    if(arch.is_open()) return;
    cout<<"ERROR DE APERTURA: Archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}
// Modulo de Impresion de Simbolos en Linea
void imprimirLinea(ofstream &archSalida, char simbolo, int medida, bool enter) {
    for(int i = 0;i < medida;i++) archSalida.put(simbolo);
    if(enter) archSalida<<endl;
}
// Modulo de Lectura de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int leerValorTemporal(ifstream &archEntrada,char tipo) {
    int dd_hor,mm_min,aa_seg;
    char c;
    archEntrada>>dd_hor>>c>>mm_min>>c>>aa_seg;
    return compactarValorTemporal(dd_hor,mm_min,aa_seg,tipo);
}
// Modulo de Compactacion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int compactarValorTemporal(int dd_hor,int mm_min,int aa_seg,char tipo) {
    if(tipo == 'F') return aa_seg*10000 + mm_min * 100 + dd_hor;
    else return dd_hor * 3600 + mm_min * 60 + aa_seg;
}
// Modulo de Impresion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
void imprimirValorTemporal(ofstream &archSalida,int valor,char tipo) {
    int dd_hor,mm_min,aa_seg,n = ((tipo == 'F')? 4:2);
    char simbolo = ((tipo == 'F')? '/' : ':');
    descompactarValorTemporal(valor,dd_hor,mm_min,aa_seg,tipo);
    archSalida.fill('0');
    archSalida<<setw(2)<<dd_hor<<simbolo<<setw(2)<<mm_min<<simbolo<<setw(n)<<aa_seg;
    archSalida.fill(' ');
}
// Modulo de Descompactacion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
void descompactarValorTemporal(int valor,int &dd_hor,int &mm_min,int &aa_seg,char tipo) {
    if(tipo == 'F') {
        aa_seg = valor/10000;
        mm_min = (valor - aa_seg*10000)/100;
        dd_hor = valor - aa_seg*10000 - mm_min*100;
    } else {
        dd_hor = valor/3600;
        mm_min = (valor - dd_hor*3600)/60;
        aa_seg = valor - dd_hor*3600 - mm_min*60;
    }
}
//
char *obtenerDinamicoExacto(char *buffer) {
    char *dinamico = new char[strlen(buffer) + 1];
    strcpy(dinamico,buffer);
    return dinamico;
}
//
char *leerCadenaExacta(ifstream &archEntrada,char delimitador) {
    char buffer[MAXTEXTO];
    if(delimitador == ' ') archEntrada>>buffer;
    else archEntrada.getline(buffer,MAXTEXTO,delimitador);
    return obtenerDinamicoExacto(buffer);
}
