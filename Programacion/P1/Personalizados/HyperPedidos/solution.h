
/* [/]
 >> Project:    HyperPedidos
 >> File:       solution.h
 >> Author:     Candy
[/] */

#ifndef FUNCIONES_H
#define FUNCIONES_H
#include "resources.h"

void cargarClientes(ifstream &,int *,int &);

void procesarReporte(ofstream &,ifstream &,int *,int *,int);

void imprimirEncabezado(ofstream &,char);

void procesarDatosDeCliente(ofstream &,ifstream &,int,bool);

void procesarNombreCompletoDeCliente(char *,char *,char *);

void procesarPedidoDeCliente(ofstream &,ifstream &,int &);

#endif /* FUNCIONES_H */
