
/*/ 
 * Projecto:  LaFijaDelCavernas
 * Nombre del Archivo:  cavernas.cpp
 * Autor: El Cavernas
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;
#include "cavernas.h"
#define XD -1
#define MAXBUFF 60
#define MAXCLI 100
#define MAXLIB 300
#define MAXPED 500
#define MAXLIB_PED 100

                      /*  - / > [ - Parte 1 - ] < / -  */

//
void cargarLibros(void *&libros){
    //
    ifstream archLib = abrirArchivo_IFS("Libros.csv");
    void *libRegs,*arrLibRegs[MAXLIB]{}; int numLib = 0;
    //
    while(1){
        libRegs = obtenerRegistrosDeLibro(archLib);
        if(libRegs == nullptr) break;
        arrLibRegs[numLib] = libRegs;
        numLib++;
    }
    //
    almacenarRegistrosExactos(libros,arrLibRegs,numLib);
}
//
void *obtenerRegistrosDeLibro(ifstream &archLib){
    //
    void **arrLibReg; char *libID,*libNomb,*libAutor;
    //
    libID = obtenerCadenaExacta(archLib,',');
    if(libID == nullptr) return nullptr;
    libNomb = obtenerCadenaExacta(archLib,',');
    libAutor = obtenerCadenaExacta(archLib,',');
    while(archLib.get() != '\n');
    //
    arrLibReg = new void *[3]{};
    arrLibReg[0] = libID;
    arrLibReg[1] = libNomb;
    arrLibReg[2] = libAutor;
    return arrLibReg;
}
//
void cargarClientes(void *&clientes){
    //
    ifstream archCli = abrirArchivo_IFS("Clientes.txt");
    void *cliRegs,*arrCliRegs[MAXCLI]{}; int numCli = 0;
    //
    while(1){
        cliRegs = obtenerRegistrosDeCliente(archCli);
        if(cliRegs == nullptr) break;
        arrCliRegs[numCli] = cliRegs;
        numCli++;
    }
    //
    almacenarRegistrosExactos(clientes,arrCliRegs,numCli);
}
//
void *obtenerRegistrosDeCliente(ifstream &archCli){
    //
    void **arrCliReg; int idAux,*cliID; char *cliNomb;
    //
    archCli>>idAux;
    if(archCli.eof()) return nullptr;
    cliID = new int;
    *cliID = idAux;
    archCli.get();
    cliNomb = obtenerCadenaExacta(archCli,',');
    while(archCli.get() != '\n');
    //
    arrCliReg = new void *[2]{};
    arrCliReg[0] = cliID;
    arrCliReg[1] = cliNomb;
    return arrCliReg;
}

                      /*  - / > [ - Parte 2 - ] < / -  */

//
void cargarReservasPorPedidos(void *&reserva,void *libros,void *clientes){
    //
    ifstream archCli = abrirArchivo_IFS("Pedidos.txt");
    void **arrLibRegs = (void **)libros,**arrCliRegs = (void **)clientes;
    void *arrResvRegs[MAXPED]{}; char *pedLibID;
    int pedCliID,pedID,numPed = 0,posCli,posLib,posPed;
    //
    while(1){
        archCli>>pedCliID;
        if(archCli.eof()) break;
        archCli.get();
        pedLibID = obtenerCadenaExacta(archCli,',');
        archCli>>pedID;
        posLib = obtenerPosLib(pedLibID,arrLibRegs);
        posCli = obtenerPosCli(pedCliID,arrCliRegs);
        posPed = obtenerPosPed(pedID,arrResvRegs);
        //
        if(posPed == XD){
            posPed = numPed;
            arrResvRegs[posPed] = new void *[5]{};
            numPed++;
        }
        //
        if(posLib != XD and posCli != XD){
            procesarDatosDeReserva(arrResvRegs[posPed],arrLibRegs[posLib],
                                   arrCliRegs[posCli],pedID);
        }
    }
    //
    compactarSubRegistros_Resv(arrResvRegs);
    //
    almacenarRegistrosExactos(reserva,arrResvRegs,numPed);
}
//
void procesarDatosDeReserva(void *resvRegs,void *libRegs,void *cliRegs,
                            int pedID){
    //
    void **arrResvReg = (void **)resvRegs,**arrLibReg = (void **)libRegs;
    void **arrCliReg = (void **)cliRegs;
    int *pedCliID = (int *)arrCliReg[0],*pedCantLib;
    char *pedCliNomb = (char *)arrCliReg[1],*pedLibID = (char *)arrLibReg[0];
    char *pedLibNomb = (char *)arrLibReg[1];
    if(!arrResvReg[0]){
        arrResvReg[0] = new int{pedID};
        arrResvReg[4] = new int{0};
    }
    pedCantLib = (int *)arrResvReg[4];
    (*pedCantLib)++;
    arrResvReg[1] = pedCliID;
    arrResvReg[2] = pedCliNomb;
    insertarDatosDeLibroPedido(arrResvReg[3],pedLibID,pedLibNomb);
}
//
void insertarDatosDeLibroPedido(void *&resvReg_Libs,char *pedLibID,
                                char *pedLibNomb){
    void **arrLibResvRegs = (void **)resvReg_Libs,**arrLibResvReg;
    int numLibPed = 0;
    if(!resvReg_Libs){
        arrLibResvRegs = new void *[MAXLIB_PED]{};
        resvReg_Libs = arrLibResvRegs;
    }
    while(arrLibResvRegs[numLibPed]) numLibPed++;
    arrLibResvReg = new void *[2]{};
    arrLibResvReg[0] = pedLibID;
    arrLibResvReg[1] = pedLibNomb;
    arrLibResvRegs[numLibPed] = arrLibResvReg;
}
//
void compactarSubRegistros_Resv(void **arrResvRegs){
    for(int i = 0;arrResvRegs[i];i++){
        void **arrResvReg = (void **)arrResvRegs[i];
        compactarSubRegistrosDeRegistro(arrResvReg[3]);
    }
}

                      /*  - / > [ - Parte 3 - ] < / -  */

//
void emitirReporte(void *reserva){
    //
    ofstream archRep = abrirArchivo_OFS("ReporteDeReservasRealizadas.txt");
    int medLinea = 114; void **arrResvRegs = (void **)reserva;
    imprimirEncabezados(archRep,medLinea,'A');
    //
    for(int i = 0;arrResvRegs[i];i++){
        imprimirRegistrosDeReserva(archRep,medLinea,arrResvRegs[i]);
        imprimirLinea(archRep,medLinea,'=');
    }
}
//
void imprimirRegistrosDeReserva(ofstream &archRep,int medLinea,void *resvRegs){
    //
    void **arrResvReg = (void **)resvRegs;
    void **arrLibResvRegs = (void **)arrResvReg[3];
    int *pedID = (int *)arrResvReg[0],*pedCliID = (int *)arrResvReg[1];
    int *pedCantLib = (int *)arrResvReg[4];
    char *pedCliNomb = (char *)arrResvReg[2];
    //
    imprimirEncabezados(archRep,medLinea,'B');
    archRep<<setw(4)<<' '<<setfill('0')<<setw(6)<<*pedID<<setfill(' ');
    archRep<<setw(16)<<*pedCliID<<setw(8)<<' '<<left<<setw(MAXBUFF)<<pedCliNomb;
    archRep<<right<<setw(10)<<*pedCantLib<<endl;
    imprimirEncabezados(archRep,medLinea,'C');
    //
    if(*pedCantLib > 0){
        for(int i = 0;arrLibResvRegs[i];i++){
            imprimirLibrosDePedido(archRep,arrLibResvRegs[i]);
        }
    } else {
        archRep<<endl;
        archRep<<setw((medLinea + 33)/2)<<"> [ NO SE CONCRETARON PEDIDOS ] <";
        archRep<<endl<<endl;
    }
}
//
void imprimirLibrosDePedido(ofstream &archRep,void *libResvRegs){
    //
    void **arrLibResvReg = (void **)libResvRegs;
    char *pedProdID = (char *)arrLibResvReg[0];
    char *pedProdNomb = (char *)arrLibResvReg[1];
    //
    archRep<<setw(15)<<pedProdID<<setw(8)<<' '<<pedProdNomb<<endl;
}

                  /*  - / > [ Funciones Auxiliares ] < / -  */

//
ifstream abrirArchivo_IFS(const char *nombArch){
    ifstream arch(nombArch,ios::in);
    if(arch.is_open()) return arch;
    cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
    exit(1);
}
//
ofstream abrirArchivo_OFS(const char *nombArch){
    ofstream arch(nombArch,ios::out);
    if(arch.is_open()) return arch;
    cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
    exit(1);
}
//
char *obtenerCadenaExacta(ifstream &archEntrada,char delimitador){
    char buff[MAXBUFF]{},*cadExacta;
    if(delimitador == ' ') archEntrada>>buff;
    else archEntrada.getline(buff,MAXBUFF,delimitador);
    if(archEntrada.eof()) return nullptr;
    cadExacta = new char[strlen(buff) + 1];
    strcpy(cadExacta,buff);
    return cadExacta;
}
//
void almacenarRegistrosExactos(void *&regsExactos,void **arrRegs,int numReg){
    void **auxArrRegs = new void *[numReg + 1]{};
    for(int i = 0;i < numReg;i++) auxArrRegs[i] = arrRegs[i];
    delete arrRegs;
    regsExactos = auxArrRegs;
}
//
void compactarSubRegistrosDeRegistro(void *&reg){
    void **arrSubRegs = (void **)reg,**auxArrSubRegs; int numDat = 0;
    if(reg) while(arrSubRegs[numDat]) numDat++;
    auxArrSubRegs = new void *[numDat + 1]{};
    for(int i = 0;i < numDat;i++) auxArrSubRegs[i] = arrSubRegs[i];
    delete arrSubRegs;
    reg = auxArrSubRegs;
}
//
int obtenerPosLib(char *pedLibID,void **arrLibRegs){
    for(int i = 0;arrLibRegs[i];i++)
        if(validarLibro(pedLibID,arrLibRegs[i])) return i;
    return XD;
}
//
bool validarLibro(char *pedLibID,void *libRegs){
    void **arrLibReg = (void **)libRegs;
    char *libID = (char *)arrLibReg[0];
    return (strcmp(pedLibID,libID) == 0);
}
//
int obtenerPosCli(int pedCliID,void **arrCliRegs){
    for(int i = 0;arrCliRegs[i];i++)
        if(validarClientte(pedCliID,arrCliRegs[i])) return i;
    return XD;
}
//
bool validarClientte(int pedCliID,void *cliRegs){
    void **arrCliReg = (void **)cliRegs;
    int *ptrCliID = (int *)arrCliReg[0];
    return (pedCliID == *ptrCliID);
}
//
int obtenerPosPed(int pedID,void **arrResvRegs){
    for(int i = 0;arrResvRegs[i];i++)
        if(validarPedido(pedID,arrResvRegs[i])) return i;
    return XD;
}
//
bool validarPedido(int pedID,void *resvRegs){
    void **arrResvReg = (void **)resvRegs;
    int *ptrPedID = (int *)arrResvReg[0];
    return (pedID == *ptrPedID);
}
//
void imprimirEncabezados(ofstream &archSalida,int dimLinea,char seleccion){
    if(seleccion == 'A'){
        archSalida<<setw((dimLinea + 31)/2)<<"REPORTE DE RESERVAS POR CLIENTE";
        archSalida<<endl;
        imprimirLinea(archSalida,dimLinea,'=');
    } else if(seleccion == 'B'){
        archSalida<<setw(10)<<"PEDIDO"<<setw(11)<<"DNI"<<setw(13)<<' '<<left;
        archSalida<<setw(MAXBUFF)<<"NOMBRE"<<right<<setw(16)<<"CANT. RESERVAS";
        archSalida<<endl;
    } else{
        imprimirLinea(archSalida,dimLinea,'-');
        archSalida<<setw((dimLinea + 28)/2)<<"LISTA DE RESERVAS REALIZADAS";
        archSalida<<endl;
        imprimirLinea(archSalida,dimLinea,'-');
        archSalida<<setw(13)<<"LIBRO"<<setw(16)<<"TITULO"<<endl;
        imprimirLinea(archSalida,dimLinea,'-');
    }
}
//
void imprimirLinea(ofstream &archSalida,int medLinea,char simbolo){
    for(int i = 0;i < medLinea;i++) archSalida.put(simbolo);
    archSalida<<endl;
}
