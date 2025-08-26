
/* [/]
 >> Project:    TPX-2
 >> File:       tpx.h
 >> Author:     Candy
[/] */

#ifndef TPX_H
#define TPX_H

void abrirArchivo_IFS(ifstream &,const char *);

void abrirArchivo_OFS(ofstream &,const char *);

void imprimirLinea(ofstream &,char,int,bool);

int leerValorTemporal(ifstream &,char);

int compactarValorTemporal(int,int,int,char);

void imprimirValorTemporal(ofstream &,int,char);

void descompactarValorTemporal(int,int &,int &,int &,char);

#endif /* TPX_H */
