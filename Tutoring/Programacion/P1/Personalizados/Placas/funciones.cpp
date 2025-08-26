
/* [/]
 >> Project:    Placas
 >> File:       funciones.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"
#define MEDLINEA 336
#define MAXTEXTO 50
#define XD -1

void cargarDatos_Empresa(const char *nombArchEmp,struct Empresa *arrEmpresa,int &cantEmpresas) {
    ifstream archEmp(nombArchEmp,ios::in);
    int representanteID;
    char *representanteNombre,distrito[MAXTEXTO];
    while(1){
        archEmp>>representanteID;
        if(archEmp.eof()) break;
        archEmp.get();
        representanteNombre = leerCadenaExacta(archEmp,MAXTEXTO,',');
        archEmp.getline(distrito,MAXTEXTO,'\n');
        arrEmpresa[cantEmpresas].representanteID = representanteID;
        arrEmpresa[cantEmpresas].representanteNomb = representanteNombre;
        strcpy(arrEmpresa[cantEmpresas].distrito,distrito);
        arrEmpresa[cantEmpresas].cantPlacasRegistradas = 0;
        arrEmpresa[cantEmpresas].fechaInfraccionMasAntigua = 40000000;
        arrEmpresa[cantEmpresas].fechaPagoMasReciente = 0;
        arrEmpresa[cantEmpresas].totalInfracciones = 0;
        arrEmpresa[cantEmpresas].totalPagadoGraves = 0;
        arrEmpresa[cantEmpresas].totalPagadoLeves = 0;
        arrEmpresa[cantEmpresas].totalPagadoMuyGraves = 0;
        cantEmpresas++;
    }
}

void cargarDatos_PlacasRegistradas(const char *nombArchPlac,struct Empresa *arrEmp,int cantEmp) {
    ifstream archPlac(nombArchPlac,ios::in);
    int representanteID,posEmp,cantPlacas;
    char placaID[20];
    while(1){
        archPlac>>representanteID;
        if(archPlac.eof()) break;
        posEmp = obtenerPosicion(representanteID,arrEmp,cantEmp);
        if(posEmp != XD) {
            archPlac>>placaID;
            cantPlacas = arrEmp[posEmp].cantPlacasRegistradas;
            strcpy(arrEmp[posEmp].placasRegistradas[cantPlacas].id,placaID);
            arrEmp[posEmp].cantPlacasRegistradas++;
        } else while(archPlac.get() != '\n');
    }
}

void cargarDatos_Infraccion(const char *nombArchInf,struct Infraccion *arrInfraccion,int &cantInfracciones) {
    ifstream archInf(nombArchInf,ios::in);
    double valor;
    char *id;
    while(1) {
        id = leerCadenaExacta(archInf,MAXTEXTO,',');
        if(id == nullptr) break;
        archInf>>valor;
        while(archInf.get() != '\n');
        arrInfraccion[cantInfracciones].id = id;
        arrInfraccion[cantInfracciones].valor = valor;
        cantInfracciones++;
    }
}

void cargarDatos_InfraccionesDeEmpresa(const char *nombArchInfCom,struct Empresa *arrEmpresa,struct Infraccion *arrInfraccion,int cantEmpresas,int cantInfracciones) {
    ifstream archInfCom(nombArchInfCom,ios::in);
    int fechaImposicion,fechaPago,posEmp,posInfraccion;
    char placaID[40],infraccionID[10],estadoPago;
    while(1) {
        fechaImposicion = leerValorTemporal(archInfCom,'F');
        if(fechaImposicion == XD) break;
        archInfCom.get();
        archInfCom.getline(placaID,40,',');
        archInfCom.getline(infraccionID,10,',');
        estadoPago = archInfCom.get();
        archInfCom.get();
        if(estadoPago == 'P') fechaPago = leerValorTemporal(archInfCom,'F');
        else fechaPago = XD;
        posInfraccion = obtenerPosicion(infraccionID,arrInfraccion,cantInfracciones);
        if(posInfraccion != XD) {
            posEmp = obtenerPosicionPorPlaca(placaID,arrEmpresa,cantEmpresas);
            if(posEmp != XD) {
                if(fechaImposicion < arrEmpresa[posEmp].fechaInfraccionMasAntigua) {
                    arrEmpresa[posEmp].fechaInfraccionMasAntigua = fechaImposicion;
                }
                if(fechaPago > arrEmpresa[posEmp].fechaPagoMasReciente) {
                    arrEmpresa[posEmp].fechaPagoMasReciente = fechaPago;
                }
                arrEmpresa[posEmp].totalInfracciones++;
                switch(infraccionID[0]){
                    case 'G':
                        arrEmpresa[posEmp].totalPagadoGraves += arrInfraccion[posInfraccion].valor;
                        break;
                    case 'M':
                        arrEmpresa[posEmp].totalPagadoMuyGraves += arrInfraccion[posInfraccion].valor;
                        break;
                    case 'L':
                        arrEmpresa[posEmp].totalPagadoLeves += arrInfraccion[posInfraccion].valor;
                        break;
                }
            }
        }
    }
}

int obtenerPosicion(int representanteID,struct Empresa *arrEmp,int cantEmp) {
    for(int i = 0;i < cantEmp;i++) if(arrEmp[i].representanteID == representanteID) return i;
    return XD;
}
int obtenerPosicionPorPlaca(char *id,struct Empresa *arrEmp,int cantEmp) {
    for(int posEmp = 0,cantPlacas;posEmp < cantEmp;posEmp++) {
        cantPlacas = arrEmp[posEmp].cantPlacasRegistradas;
        for(int posPlaca = 0;posPlaca < cantPlacas;posPlaca++) {
            if(strcmp(arrEmp[posEmp].placasRegistradas[posPlaca].id,id) == 0) {
                return posEmp;
            }
        }
    }
    return XD;
}

void emitirReporte(const char *nombArchRep,struct Empresa *arrEmp,int cantEmp) {
    ofstream archRep(nombArchRep,ios::out);
    archRep<<fixed;
    archRep.precision(2);
    imprimirEncabezado(archRep);
    for(int posEmp = 0,cantPlacas;posEmp < cantEmp;posEmp++) {
        cantPlacas = arrEmp[posEmp].cantPlacasRegistradas;
        archRep<<setw(6)<<posEmp+1<<')';
        archRep<<setw(16)<<arrEmp[posEmp].representanteID;
        archRep<<setw(8)<<" "<<left<<setw(MAXTEXTO)<<arrEmp[posEmp].representanteNomb<<right;
        archRep<<setw(8)<<" "<<left<<setw(MAXTEXTO)<<arrEmp[posEmp].distrito<<right<<setw(8)<<" ";
        for(int posPlaca = 0;posPlaca < cantPlacas;posPlaca++) {
            archRep<<arrEmp[posEmp].placasRegistradas[posPlaca].id;
            if(posPlaca != cantPlacas - 1) archRep<<'/';
        }
        archRep<<setw(89 - 9*(cantPlacas - 1))<<" ";
        imprimirValorTemporal(archRep,arrEmp[posEmp].fechaInfraccionMasAntigua,'F');
        archRep<<setw(8)<<" ";
        imprimirValorTemporal(archRep,arrEmp[posEmp].fechaPagoMasReciente,'F');
        archRep<<setw(14)<<arrEmp[posEmp].totalPagadoLeves;
        archRep<<setw(14)<<arrEmp[posEmp].totalPagadoGraves;
        archRep<<setw(14)<<arrEmp[posEmp].totalPagadoMuyGraves;
        archRep<<setw(14)<<arrEmp[posEmp].totalInfracciones<<endl;
    }
    imprimirLinea(archRep,MEDLINEA,'=');
}

int obtenerPosicion(char *id,struct Infraccion *arrInfraccion,int cantInfracciones) {
    for(int i = 0;i < cantInfracciones;i++) if(strcmp(arrInfraccion[i].id,id) == 0) return i;
    return XD;
}

char *leerCadenaExacta(ifstream &archEntrada,int medida,char delimitador) {
    char buff[MAXTEXTO];
    if(delimitador == ' ') archEntrada>>buff;
    else archEntrada.getline(buff,medida,delimitador);
    if(archEntrada.eof()) return nullptr;
    return obtenerDinamicoExacto(buff);
}

char *obtenerDinamicoExacto(char *buff) {
    char *dinamicoExacto = new char[strlen(buff) + 1];
    strcpy(dinamicoExacto,buff);
    return dinamicoExacto;
}
// Modulo de Lectura de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int leerValorTemporal(ifstream &archEntrada,char tipo) {
    int dd_hor,mm_min,aa_seg;
    char c;
    archEntrada>>dd_hor;
    if(archEntrada.eof()) return XD;
    archEntrada>>c>>mm_min>>c>>aa_seg;
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
void imprimirLinea(ofstream &archSalida,int medida, char simbolo) {
    for(int i = 0;i < medida;i++) archSalida.put(simbolo);
    archSalida<<endl;
}
//
void imprimirEncabezado(ofstream &archSalida) {
    archSalida<<setw((MEDLINEA + 25)/2)<<"MINISTERIO DE TRANSPORTES"<<endl;
    archSalida<<setw((MEDLINEA + 13)/2)<<"LISTADO DE INFRACCIONES ";
    archSalida<<"POR EMPRESA"<<endl;
    imprimirLinea(archSalida,MEDLINEA,'=');
    archSalida<<setw((2*MAXTEXTO+151)/2)<<"EMPRESA";
    archSalida<<setw((2*MAXTEXTO+161)/2)<<"FECHA INF.";
    archSalida<<setw(17)<<"FECHA PAGO"<<setw(31)<<"TOTAL PAGADO";
    archSalida<<setw(28)<<"CANTIDAD"<<endl;
    archSalida<<setw(7)<<"No."<<setw(13)<<"DNI"<<setw(11)<<" ";
    archSalida<<left<<setw(MAXTEXTO)<<"NOMBRE"<<setw(8)<<" ";
    archSalida<<setw(MAXTEXTO)<<"DISTRITO"<<setw(8)<<" ";
    archSalida<<setw(89)<<"PLACAS"<<right<<setw(19)<<"MAS ANTIGUA";
    archSalida<<setw(18)<<"MAS RECIENTE"<<setw(13)<<"LEVES"<<setw(14)<<"GRAVES";
    archSalida<<setw(15)<<"MUY GRAVES"<<setw(17)<<"DE FALTAS"<<endl;
    imprimirLinea(archSalida,MEDLINEA,'-');
}
