
/* [/]
 >> Project:    PuntosMeseros
 >> File:       funciones.cpp
 >> Author:     
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"
#define MAXTEXTO 50
#define MEDLINEA 137

void cargarDatos_Cuentas(const char *nombArchCuentas,struct Nodo *&listaDeCuentas) {
    ifstream archCuentas;
    abrirArchivo_IFS(archCuentas,nombArchCuentas);
    int cliID;
    double saldoInicial;
    char *cliNomb;
    
    while(1) {
        archCuentas>>cliID;
        if(archCuentas.eof()) break;
        archCuentas.get();
        cliNomb = leerCadenaExacta(archCuentas,',');
        archCuentas>>saldoInicial;
        struct Cuenta cuenta;
        cuenta.codigo = cliID;
        cuenta.fechaSobregiro = 0;
        cuenta.nombre = cliNomb;
        cuenta.saldo = saldoInicial;
        cuenta.sobregiro = false;
        insertarOrdenado(listaDeCuentas,cuenta);
    }
}

void cargarDatos_Movimientos(const char *nombArchMovimientos,struct Nodo *listaDeCuentas) {
    ifstream archMovimientos;
    abrirArchivo_IFS(archMovimientos,nombArchMovimientos);
    int fechaAtencion,cliID_Atendido;
    double montoEnMovimiento;
    
    while(1) {
        fechaAtencion = leerValorTemporal(archMovimientos,'F');
        if(fechaAtencion == -1) break;
        while(1) {
            if(archMovimientos.get() == '\n') break;
            archMovimientos>>cliID_Atendido;
            archMovimientos.get();
            archMovimientos>>montoEnMovimiento;
            actualizarCuenta(listaDeCuentas,fechaAtencion,cliID_Atendido,montoEnMovimiento);
        }
    }
}

void actualizarCuenta(struct Nodo *listaDeCuentas,int fechaAtencion,int cliID_Atendido,double montoEnMovimiento) {
    struct Nodo *nAux = listaDeCuentas;
    while(nAux != nullptr) {
        if(nAux->cuenta.codigo == cliID_Atendido) {
            nAux->cuenta.saldo += montoEnMovimiento;
            if(nAux->cuenta.saldo < 0) {
                nAux->cuenta.sobregiro = true;
                if(fechaAtencion > nAux->cuenta.fechaSobregiro) nAux->cuenta.fechaSobregiro = fechaAtencion;
            }
        }
        nAux = nAux->siguiente;
    }
}

void emitirReporte(const char *nombArchRep,struct Nodo *listaDeCuentas) {
    ofstream archRep;
    abrirArchivo_OFS(archRep,nombArchRep);
    int posCuenta = 1;
    struct Nodo *nAux = listaDeCuentas;
    archRep<<fixed<<setprecision(2);
    imprimirEncabezado(archRep);
    while(nAux != nullptr) {
        archRep<<setw(4)<<' '<<setfill('0')<<setw(2)<<posCuenta<<setfill(' ')<<')';
        archRep<<setw(12)<<nAux->cuenta.codigo<<setw(4)<<' '<<left;
        archRep<<setw(MAXTEXTO)<<nAux->cuenta.nombre<<right;
        archRep<<setw(12)<<nAux->cuenta.saldo<<setw(12)<<' ';
        if(nAux->cuenta.sobregiro) {
            archRep<<"SI"<<setw(16)<<' ';
            imprimirValorTemporal(archRep,nAux->cuenta.fechaSobregiro,'F');
        } else archRep<<"NO"<<setw(26)<<"--/--/----";
        archRep<<endl;
        nAux = nAux->siguiente;
        posCuenta++;
    }
    imprimirLinea(archRep,'=',MEDLINEA,true);
}

void eliminarCuentasDeSaldosPositivos(struct Nodo *&listaDeCuentas) {
    struct Nodo *nAux = listaDeCuentas,*nAnt = nullptr;
    while(nAux != nullptr) {
        if(nAux->cuenta.saldo > 0) {
            if(nAnt == nullptr) {
                listaDeCuentas = nAux->siguiente;
                delete(nAux);
                nAux = listaDeCuentas;
            } else {
                nAnt->siguiente = nAux->siguiente;
                delete(nAux);
                nAux = nAnt->siguiente;
            }
        } else {
            nAnt = nAux;
            nAux = nAux->siguiente;
        }
    }
}

void imprimirEncabezado(ofstream &archSalida) {
    archSalida<<setw((MEDLINEA + 24)/2)<<"BANCO De LOS CLIENTES Tp"<<endl;
    archSalida<<setw((MEDLINEA + 18)/2)<<"LISTADO DE LAS CUENTA DE AHORROS DE";
    archSalida<<"NUESTROS CLIENTES"<<endl;
    imprimirLinea(archSalida,'=',MEDLINEA,true);
    archSalida<<setw(7)<<"No."<<setw(11)<<"CUENTA"<<setw(5)<<" ";
    archSalida<<left<<setw(MAXTEXTO)<<"CLIENTE"<<right<<setw(11)<<"SALDO";
    archSalida<<setw(19)<<"SOBREGIRADA"<<setw(30)<<"FECHA DEL ULTIMO SOBREGIRO"<<endl;
}

struct Nodo *crearNodo(struct Cuenta cuenta,struct Nodo *siguiente) {
    struct Nodo *ptNodo = new struct Nodo;
    ptNodo->cuenta = cuenta;
    ptNodo->siguiente = siguiente;
    return ptNodo;
}

void insertarOrdenado(struct Nodo *&listaDeCuentas,struct Cuenta cuenta) {
    struct Nodo *newNodo = crearNodo(cuenta,nullptr);
    struct Nodo *nAux = listaDeCuentas,*nAnt = nullptr;
    if(nAux != nullptr) {
        while(nAux != nullptr) {
            if(newNodo->cuenta.codigo < nAux->cuenta.codigo) {
                if(nAnt == nullptr) {
                    newNodo->siguiente = listaDeCuentas;
                    listaDeCuentas = newNodo;
                }
                break;
            }
            nAnt = nAux;
            nAux = nAux->siguiente;
        }
        if(nAnt != nullptr) {
            newNodo->siguiente = nAux;
            nAnt->siguiente = newNodo;
        }
    } else listaDeCuentas = newNodo;
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
    archEntrada>>dd_hor;
    if(archEntrada.eof()) return -1;
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
char *obtenerDinamicoExacto(const char *buffer) {
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
// Modulo de Busqueda SECUENCIAL de Posicion de Palaba en Cadena
int obtenerPosisionDePalabraEnCadena(const char *palabra,const char *cadena) {
    int posBuff = 0,posPalabra = 0,posResultado = -1;
    while(cadena[posBuff]) {
        if(cadena[posBuff] == palabra[posPalabra]) {
            if(posPalabra == 0) posResultado = posBuff;
            posPalabra++;
            if(palabra[posPalabra] == 0) break;
        } else if(posPalabra > 0) {
            posBuff--;
            posPalabra = 0;
            posResultado = -1;
        }
        posBuff++;
    }
    return posResultado;
}
