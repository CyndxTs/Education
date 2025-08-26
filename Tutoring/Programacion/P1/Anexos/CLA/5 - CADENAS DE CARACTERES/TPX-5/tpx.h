
/* [/]
 >> Project:    TPX-5
 >> File:       tpx.h
 >> Author:     Candy
[/] */

#ifndef TPX_H
#define TPX_H

void imprimirCadena(char *,int);

int longitud(const char *);

void copiar(char *,const char *);

int comparar(const char *,const char *);

void concatenar(char *,const char *);

void concatenar_V2(char *,const char *);

int obtenerPosicion_Bin_Asc(char,char *,int);

int obtenerPosicion_Bin_Desc(char,char *,int);

void eliminarElemento(int,char *,int &);

void insertarElemento(char,int,char *,int &);

void insertarOrdenado_Asc(char,char *,int &);

void insertarOrdenado_Asc_V2(char,char *,int &);

void insertarOrdenado_Desc(char,char *,int &);

void insertarOrdenado_Desc_V2(char,char *,int &);

int obtenerPosision(const char *,const char *);

int obtenerPosision(const char *,const char *,int &);

#endif /* TPX_H */
