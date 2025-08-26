
/* [/]
 >> Project:    TPX-4
 >> File:       tpx.h
 >> Author:     Candy
[/] */

#ifndef TPX_H
#define TPX_H

void imprimirArreglo(int *,int);

int obtenerPosicion(int,int *,int);

int obtenerPosicion(int,int,int *,int *,int);

void intercambiarValor(int,int,int *);

void intercambiarValor(int &,int &);

void ordenar_1C_Asc(int *,int);

void ordenar_1C_Desc(int *,int);

void ordenar_2C(int *,int *,int);

void ordenar_3C(int *,int *,int *,int);

int obtenerPosicion_Bin_Asc(int,int *,int);

int obtenerPosicion_Bin_Desc(int,int *,int);

void eliminarElemento(int,int *,int &);

void insertarElemento(int,int,int *,int &);

void insertarOrdenado_Asc(int,int *,int &);

void insertarOrdenado_Asc_V2(int,int *,int &);

void insertarOrdenado_Desc(int,int *,int &);

void insertarOrdenado_Desc_V2(int,int *,int &);

#endif /* TPX_H */
