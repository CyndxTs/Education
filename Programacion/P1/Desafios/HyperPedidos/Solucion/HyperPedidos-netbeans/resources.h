
/* [/]
 >> Project:    HyperPedidos
 >> File:       resources.h
 >> Author:     Candy
[/] */

#ifndef RESOURCES_H
#define RESOURCES_H

void pasarMayuscula(char &);

void pasarMinuscula(char &);

void abrirArchivo_IFS(ifstream &,const char *);

void abrirArchivo_OFS(ofstream &,const char *);

void imprimirLinea(ofstream &,char,int,bool);

int leerValorTemporal(ifstream &,char);

int compactarValorTemporal(int,int,int,char);

void imprimirValorTemporal(ofstream &,int,char);

void descompactarValorTemporal(int,int &,int &,int &,char);

int obtenerPosicion_Bin_Asc(int,int *,int);

void insertarElemento(int,int,int *,int &);

void insertarOrdenado_Asc_V2(int,int *,int &);

void copiar(char *,const char *);

void concatenar_V2(char *,const char *);

void normalizar(char *);

int obtenerPosisionDePalabraEnCadena(const char *,const char *);

#endif /* RESOURCES_H */
