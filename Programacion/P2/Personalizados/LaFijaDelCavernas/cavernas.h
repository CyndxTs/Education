
/*/ 
 * Projecto:  LaFijaDelCavernas
 * Nombre del Archivo:  cavernas.h
 * Autor: El Cavernas
/*/

#ifndef CAVERNAS_H
#define CAVERNAS_H

                /*  - / > [ Declaracion de Funciones ] < / -  */

void cargarLibros(void *&);

void *obtenerRegistrosDeLibro(ifstream &);

void cargarClientes(void *&);

void *obtenerRegistrosDeCliente(ifstream &);

void cargarReservasPorPedidos(void *&,void *,void *);

void procesarDatosDeReserva(void *,void *,void *,int);

void insertarDatosDeLibroPedido(void *&,char *,char *);

void compactarSubRegistros_Resv(void **);

void emitirReporte(void *);

void imprimirRegistrosDeReserva(ofstream &,int,void *);

void imprimirLibrosDePedido(ofstream &,void *);

ifstream abrirArchivo_IFS(const char *);

ofstream abrirArchivo_OFS(const char *);

char *obtenerCadenaExacta(ifstream &,char);

void almacenarRegistrosExactos(void *&,void **,int);

void compactarSubRegistrosDeRegistro(void *&);

int obtenerPosLib(char *,void **);

bool validarLibro(char *,void *);

int obtenerPosCli(int,void **);

bool validarClientte(int,void *);

int obtenerPosPed(int,void **);

bool validarPedido(int,void *);

void imprimirEncabezados(ofstream &,int,char);

void imprimirLinea(ofstream &,int,char);

#endif /* CAVERNAS_H */
