
/*/ 
 * Projecto:  Pregunta01PunterosMultiples
 * Nombre del Archivo:  FuncionesExamen01Pregunta01.h
 * Autor: MultiPunta
/*/

#ifndef FUNCIONESEXAMEN01PREGUNTA01_H
#define FUNCIONESEXAMEN01PREGUNTA01_H

                /*  - / > [ Declaracion de Funciones ] < / -  */

void CargarProductosPedidos(int **&,char **&,int **&,const char *);

void leerInformacionDePedido(ifstream &,int &,int &,int &,double &,double &,
                             double &,double &,char *&);

void insertarDatosDeProducto(int *&,char *&,int,double,double,double,char *);

void insertarDatosDePedido(int *&,int,int,double,int);

void PruebaProductosPedidos(int **,char **,int **,const char *);

void imprimirRegistrosDeProducto(ofstream &,int *,char *);

void imprimirRegistrosDePedido(ofstream &,int *);

void ordenarPedidos(int **);

void Qsort(int **,int,int);

void intercambiar(int *&,int *&);

bool compararRegistros(int *,int *);

void asignarPedidos(int ***,int **,int **);

void extraerDatosDePedido(int *,int &,int &,int &,int &);

bool validarStock(int *,int);

void insertarDatosDePedido_Cli(int **,int,int,int,int &,int &);

void incrementarCapacidad_ListaDePedidos(int *&,int &,int &);

ifstream abrirArchivo_IFS(const char *);

ofstream abrirArchivo_OFS(const char *);

void almacenarRegistrosExactos(int **&,int **,int);

void almacenarRegistrosExactos(char **&,char **,int);

char *obtenerCadenaExacta(ifstream &,char);

int obtenerFecha(ifstream &);

int obtenerPosProd(int,int **);

bool validarProducto(int,int *);

int obtenerPosCli(int,int ***);

bool validarCliente(int,int **);

void imprimirFecha(ofstream &,int);

void imprimirEncabezado(ofstream &,int,char);

void imprimirLinea(ofstream &,int,char);

#endif /* FUNCIONESEXAMEN01PREGUNTA01_H */
