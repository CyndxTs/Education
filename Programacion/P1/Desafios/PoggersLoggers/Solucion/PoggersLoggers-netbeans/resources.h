
/* [/]
 >> Project:    PoggersLoggers
 >> File:       resources.h
 >> Author:     Candy
[/] */

#ifndef RECURSOS_H
#define RECURSOS_H

void pasarMayuscula(char &);

void pasarMinuscula(char &);

void abrirArchivo_IFS(ifstream &,const char *);

void abrirArchivo_OFS(ofstream &,const char *);

void imprimirLinea(ofstream &,char,int,bool);

int leerValorTemporal(ifstream &,char);

int compactarValorTemporal(int,int,int,char);

void imprimirValorTemporal(ofstream &,int,char);

void descompactarValorTemporal(int,int &,int &,int &,char);

void intercambiarValor(int,int,int *);

void intercambiarValor(int,int,char *);

int obtenerPosicion_Bin_Asc(int,int *,int);

#endif /* RECURSOS_H */
