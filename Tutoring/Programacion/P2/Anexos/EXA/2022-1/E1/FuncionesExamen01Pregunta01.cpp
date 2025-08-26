
/*/ 
 * Projecto:  Pregunta01PunterosMultiples
 * Nombre del Archivo:  FuncionesExamen01Pregunta01.cpp
 * Autor: MultiPunta
/*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstring>
using namespace std;
#include "FuncionesExamen01Pregunta01.h"
#define XD -1
#define MEDPED 3
#define INCREMENTO 5
#define MAXBUFF 60
#define MAXPED_CLI 100
#define MAXPROD 300
#define MAXPED 600

                     /*  - / > [ - Parte 'A' - ] < / -  */

// Modulo de Carga de Datos de 'Productos' & 'Pedidos'
void CargarProductosPedidos(int **&pro_Informacion,char **&pro_Descripcion,
                            int **&ped_Todo,const char *nombArchPed){
    // Declaracion de Variables && Apertura de Archivos
    int *arrProdRegs[MAXPROD]{},*arrPedRegs[MAXPED]{},numProd = 0,numPed = 0;
    int pedProdID,pedCliID,pedFecha;
    double prodDescuento,pedCantSol,prodPrecioUnit,prodStock;
    char *arrProdDesc[MAXPROD]{},*prodDesc;
    ifstream archPed = abrirArchivo_IFS(nombArchPed);
    // Iterativa del Proceso de Lectura & Carga de Datos
    while(1){
        // Lectura de Datos
        leerInformacionDePedido(archPed,pedProdID,pedCliID,pedFecha,
                                prodDescuento,pedCantSol,prodPrecioUnit,
                                prodStock,prodDesc);
        if(archPed.eof()) break;                                                // Validacion de Fin de Archivo
        // Busqueda & Validacion por Insercion de Nuevo Registro de 'Producto'
        if(obtenerPosProd(pedProdID,arrProdRegs) ==  XD){
            insertarDatosDeProducto(arrProdRegs[numProd],arrProdDesc[numProd],
                                    pedProdID,prodDescuento,prodPrecioUnit,
                                    prodStock,prodDesc);
            numProd++;
        }
        // Insercion de Nuevo Registro de 'Pedido'
        insertarDatosDePedido(arrPedRegs[numPed],pedCliID,pedFecha,pedCantSol,
                              pedProdID);
        numPed++;
    }
    // Almacenamiento Exacto de Registros Cargados
    almacenarRegistrosExactos(pro_Informacion,arrProdRegs,numProd);
    almacenarRegistrosExactos(pro_Descripcion,arrProdDesc,numProd);
    almacenarRegistrosExactos(ped_Todo,arrPedRegs,numPed);
}
// Modulo de Lectura de Datos de 'Producto' & 'Pedido'
void leerInformacionDePedido(ifstream &archPed,int &prodID,int &cliID,
                             int &fecha,double &descuento,double &cantSol,
                             double &precioUnit,double &stock,char *&prodDesc){
    // Proceso de Lectura
    archPed>>prodID;
    if(archPed.eof()) return;                                                   // Validacion de Fin de Archivo
    archPed.get();
    prodDesc = obtenerCadenaExacta(archPed,',');
    archPed>>descuento;
    // Validacion de Existencia de Descuento
    if(archPed.get() == '%'){
        archPed.get();
        archPed>>cantSol;
        archPed.get();
    } else{
        cantSol = descuento;
        descuento = 0;
    }
    archPed>>precioUnit;
    archPed.get();
    archPed>>stock;
    archPed.get();
    archPed>>cliID;
    archPed.get();
    fecha = obtenerFecha(archPed);
}
// Modulo de Insercion de Datos de 'Producto'
void insertarDatosDeProducto(int *&prodRegs,char *&prodDesc,int id,
                             double descuento,double precioUnit,double stock,
                             char *descripcion){
    prodRegs = new int[4]{};
    prodRegs[0] = id;
    prodRegs[1] = (int)(100*descuento);
    prodRegs[2] = (int)(100*precioUnit);
    prodRegs[3] = (int)(100*stock);
    prodDesc = new char[strlen(descripcion) + 1];
    strcpy(prodDesc,descripcion);
}
// Modulo de Insercion de Datos de 'Pedido'
void insertarDatosDePedido(int *&pedRegs,int cliID,int fecha,double cantSol,
                           int prodID){
    pedRegs = new int[4]{};
    pedRegs[0] = cliID;
    pedRegs[1] = fecha;
    pedRegs[2] = (int)(100*cantSol);
    pedRegs[3] = prodID;
}
// Modulo de Impresion de Prueba de Carga de Datos de 'Productos' & 'Pedidos'
void PruebaProductosPedidos(int **pro_Informacion,char **pro_Descripcion,
                            int **ped_Todo,const char *nombArchRep){
    // Apertura de Archivos && Declaracion de Variables && Inicializacion de Elementos
    ofstream archRep = abrirArchivo_OFS(nombArchRep);
    int medLinea_A = 130,medLinea_B = 62;
    archRep<<fixed<<setprecision(2);
    // Proceso de Impresion
    imprimirEncabezado(archRep,medLinea_A,'A');
    for(int i = 0;pro_Informacion[i];i++){
        imprimirRegistrosDeProducto(archRep,pro_Informacion[i],
                                    pro_Descripcion[i]);
    }
    imprimirLinea(archRep,medLinea_A,'=');
    imprimirEncabezado(archRep,medLinea_B,'B');
    for(int i = 0;ped_Todo[i];i++)
        imprimirRegistrosDePedido(archRep,ped_Todo[i]);
    imprimirLinea(archRep,medLinea_B,'=');
}
// Modulo de Impresion de Registros de 'Producto'
void imprimirRegistrosDeProducto(ofstream &archRep,int *prodRegs,
                                 char *prodDesc){
    archRep<<setw(10)<<prodRegs[0]<<left<<setw(4)<<' ';
    archRep<<setw(MAXBUFF)<<prodDesc<<right;
    archRep<<setw(17)<<(((double)prodRegs[1])/100)<<'%';
    archRep<<setw(17)<<(((double)prodRegs[2])/100);
    archRep<<setw(17)<<(((double)prodRegs[3])/100)<<endl;
}
// Modulo de Impresion de Registros de 'Pedido'
void imprimirRegistrosDePedido(ofstream &archRep,int *pedRegs){
    archRep<<setw(12)<<pedRegs[0]<<setw(8)<<' ';
    imprimirFecha(archRep,pedRegs[1]);
    archRep<<setw(13)<<(((double)pedRegs[2])/100);
    archRep<<setw(14)<<pedRegs[3]<<endl;
}

                     /*  - / > [ - Parte 'B' - ] < / -  */

// Modulo de Ordenamiento de Pedidos [ASC: PedFecha & CliID]
void ordenarPedidos(int **ped_Todo){
    // Declaracion de Variables
    int numPed = 0;
    // Calculo de Numero de Pedidos Registrados
    while(ped_Todo[numPed]) numPed++;
    // Ordenamiento 'Qsort' Adaptado
    Qsort(ped_Todo,0,numPed - 1);
}
// Modulo de Ordenamiento 'Qsort' Adaptado
void Qsort(int **arrRegs,int izq,int der){
    // Declaracion de Variables
    int limite = izq;
    if(izq >= der) return;                                                      // Validacion de Retorno
    // Proceso de Ordenamiento
    intercambiar(arrRegs[izq],arrRegs[(izq + der)/2]);
    for(int i = izq + 1;i <= der;i++){
        // Validacion De Criterio de Ordenamiento
        if(compararRegistros(arrRegs[izq],arrRegs[i])){
            limite++;
            intercambiar(arrRegs[limite],arrRegs[i]);
        }
    }
    intercambiar(arrRegs[izq],arrRegs[limite]);
    Qsort(arrRegs,izq,limite - 1);
    Qsort(arrRegs,limite + 1,der);
}
// Modulo de Intercambio de Valores de Datos ['int *']
void intercambiar(int *&regs_a,int *&regs_b){
    int *aux = regs_a;
    regs_a = regs_b;
    regs_b = aux;
}
// Modulo de Comparacion de Registros por Criterios de Ordenamiento
bool compararRegistros(int *regs_a,int *regs_b){
    int diff_fecha = regs_a[1] - regs_b[1];
    int diff_ID = regs_a[0] - regs_b[0];
    return (diff_fecha > 0 or (diff_fecha == 0 and diff_ID > 0));
}
// Modulo de Asignacion de Pedidos a Clientes
void asignarPedidos(int ***cli_DniTelPed,int **pro_Informacion,int **ped_Todo){
    // Declaracion de Variables
    int arrNumPed_Cli[MAXPED_CLI]{},arrCapPed_Cli[MAXPED_CLI]{};
    int pedCliID,pedFecha,pedCantSol,pedProdID,posCli,posProd;
    // Iterativa del Proceso de Asignacion
    for(int i = 0;ped_Todo[i];i++){
        extraerDatosDePedido(ped_Todo[i],pedCliID,pedFecha,pedCantSol,
                             pedProdID);
        // Validacion de Existencia de Cliente & Producto
        posCli = obtenerPosCli(pedCliID,cli_DniTelPed);
        posProd = obtenerPosProd(pedProdID,pro_Informacion);
        if(posCli != XD and posProd != XD){
            // Validacion de Existencia de Stock
            if(validarStock(pro_Informacion[posProd],pedCantSol)){
                // Insercion de Datos de Pedido en Lista de Cliente
                insertarDatosDePedido_Cli(cli_DniTelPed[posCli],pedFecha,
                                          pedCantSol,pedProdID,
                                          arrNumPed_Cli[posCli],
                                          arrCapPed_Cli[posCli]);
            }
        }
    }
}
// Modulo de Extraccion de Datos de Pedido
void extraerDatosDePedido(int *pedRegs,int &pedCliID,int &pedFecha,
                          int &pedCantSol,int &pedProdID){
    pedCliID = pedRegs[0];
    pedFecha = pedRegs[1];
    pedCantSol = pedRegs[2];
    pedProdID = pedRegs[3];
}
// Modulo de Validacion de Existencia de Stock && Reduccion de Stock
bool validarStock(int *prodRegs,int cantSol){
    if(prodRegs[3] - cantSol > 0){
        prodRegs[3] -= cantSol;
        return true;
    } else return false;
}
// Modulo de Insercion de Datos de Pedido Realizado por Cliente en su Lista
void insertarDatosDePedido_Cli(int **cliRegs,int pedFecha,int pedCantSol,
                               int pedProdID,int &numPed,int &capPed){
    // Declaracion de Variables
    int *cliReg_Peds,posInsertar;
    // Validacion de Incremento de Capacidad de Lista
    if(numPed == capPed){
        incrementarCapacidad_ListaDePedidos(cliRegs[2],numPed,capPed);
    }
    // Proceso de Insercion
    posInsertar = MEDPED*(numPed - 1);
    cliReg_Peds = cliRegs[2];
    cliReg_Peds[posInsertar] = pedFecha;
    cliReg_Peds[posInsertar + 1] = pedProdID;
    cliReg_Peds[posInsertar + 2] = pedCantSol;
    numPed++;
}
// Modulo de Incremento de Capacidad de Lista de Pedidos
void incrementarCapacidad_ListaDePedidos(int *&cliReg_Peds,int &numPed,
                                         int &capPed){
    // Incremento de Capacidad
    capPed += INCREMENTO;
    // Declaracion & Inicializacion de Variables
    int *auxCliReg_Peds = new int[capPed*MEDPED]{},numDat = numPed*MEDPED;
    // Validacion de Existencia de Datos Almacenados
    if(cliReg_Peds){
        for(int i = 0;i < numDat;i++) auxCliReg_Peds[i] = cliReg_Peds[i];
        delete cliReg_Peds;
    } else numPed = 1;
    // Redireccion Hacia Datos Expandidos
    cliReg_Peds = auxCliReg_Peds;
}

                  /*  - / > [ Funciones Auxiliares ] < / -  */

// Modulo Auxiliar de Apertura de Archivos IFSTREAM
ifstream abrirArchivo_IFS(const char *nombArch){
    ifstream arch(nombArch,ios::in);
    if(arch.is_open()) return arch;
    cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
    exit(1);
}
// Modulo Auxiliar de Apertura de Archivos OFSTREAM
ofstream abrirArchivo_OFS(const char *nombArch){
    ofstream arch(nombArch,ios::out);
    if(arch.is_open()) return arch;
    cout<<"ERROR DE APERTURA: Archivo "<<nombArch<<endl;
    exit(1);
}
// Modulo Auxiliar de Almacenamiento de Registros Exactos [int **]
void almacenarRegistrosExactos(int **&regsExactos,int **regs,int numDat){
    int **auxRegs = new int *[numDat + 1]{};
    for(int i = 0;i < numDat;i++) auxRegs[i] = regs[i];
    regsExactos = auxRegs;
}
// Modulo Auxiliar de Almacenamiento de Registros Exactos [char **]
void almacenarRegistrosExactos(char **&regsExactos,char **regs,int numDat){
    char **auxRegs = new char *[numDat + 1]{};
    for(int i = 0;i < numDat;i++) auxRegs[i] = regs[i];
    regsExactos = auxRegs;
}
// Modulo Auxiliar de Lectura de Cadena Exacta
char *obtenerCadenaExacta(ifstream &archEntrada,char delimitador){
    char buff[MAXBUFF]{},*cadExacta;
    if(delimitador == ' ') archEntrada>>buff;
    else archEntrada.getline(buff,MAXBUFF,delimitador);
    cadExacta = new char[strlen(buff) + 1]{};
    strcpy(cadExacta,buff);
    return cadExacta;
}
// Modulo Auxiliar de Lectura de Fecha Compactada
int obtenerFecha(ifstream &archEntrada){
    int dd,mm,aa;
    char simbolo;
    archEntrada>>dd>>simbolo>>mm>>simbolo>>aa;
    return dd+100*mm+10000*aa;
}
// Modulo Auxiliar de Busqueda de Posicion de Registros 'Producto'
int obtenerPosProd(int prodID,int **arrProdRegs){
    for(int i = 0;arrProdRegs[i];i++)
        if(validarProducto(prodID,arrProdRegs[i])) return i;
    return XD;
}
// Modulo Auxiliar de Validacion de Identificador de 'Producto'
bool validarProducto(int prodID,int *prodRegs){
    return prodID == prodRegs[0];
}
// Modulo Auxiliar de Busqueda de Posicion de Registros 'Cliente'
int obtenerPosCli(int cliID,int ***arrCliRegs){
    for(int i = 0;arrCliRegs[i];i++)
        if(validarCliente(cliID,arrCliRegs[i])) return i;
    return XD;
}
// Modulo Auxiliar de Validacion de Identificador de 'Cliente'
bool validarCliente(int cliID,int **cliRegs){
    int *ptrCliID = cliRegs[0];
    return cliID == *ptrCliID;
}
// Modulo Auxiliar de Impresion de Fecha
void imprimirFecha(ofstream &archRep,int fecha){
    int aa = fecha/10000;
    int mm = (fecha - aa*10000)/100;
    int dd = fecha - aa*10000 - mm*100;
    archRep.fill('0');
    archRep<<setw(2)<<dd<<'/'<<setw(2)<<mm<<'/'<<setw(4)<<aa;
    archRep.fill(' ');
}
// Modulo Auxiliar de Impresion de Encabezados
void imprimirEncabezado(ofstream &archSalida,int dimLinea,char seleccion){
    if(seleccion == 'A'){
        archSalida<<setw((dimLinea + 30)/2)<<"REPORTE DE ESTADO DE PRODUCTOS";
        archSalida<<endl;
        imprimirLinea(archSalida,dimLinea,'=');
        archSalida<<setw(10)<<"CODIGO"<<setw(4)<<' '<<left;
        archSalida<<setw(MAXBUFF)<<"DESCRIPCION"<<right;
        archSalida<<setw(20)<<"DESCUENTO"<<setw(20)<<"PRECIO UNITARIO";
        archSalida<<setw(12)<<"STOCK"<<endl;
        imprimirLinea(archSalida,dimLinea,'-');
    } else {
        archSalida<<setw((dimLinea + 28)/2)<<"REPORTE DE ESTADO DE PEDIDOS";
        archSalida<<endl;
        imprimirLinea(archSalida,dimLinea,'=');
        archSalida<<setw(11)<<"CLIENTE"<<setw(16)<<"FECHA";
        archSalida<<setw(18)<<"CANTIDAD"<<setw(13)<<"PRODUCTO"<<endl;
        imprimirLinea(archSalida,dimLinea,'-');
    }
}
// Modulo Auxiliar de Impresion de Simbolos en Linea
void imprimirLinea(ofstream &archSalida,int medida,char simbolo){
    for(int i = 0;i < medida;i++) archSalida.put(simbolo);
    archSalida<<endl;
}
