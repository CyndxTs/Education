
/*]
 >> Project:    Pompompurin
 >> Autor:      Candy
 >> File:       funciones.h
[*/

#ifndef POMPOMPURIN_FUNCIONES_H
#define POMPOMPURIN_FUNCIONES_H

                  /*  - / > [ Funciones en main() ] < / -  */

void emitirReporte(const char *,const char *,int &,int &);

              /*  - / > [ Funciones de Orden Principal ] < / -  */

void imprimirTitulo(ofstream &,int,int);
void procesarNombreDeCanal(ofstream &,ifstream &);
void procesarUltimasReproduccionesDeCanal(ofstream &,ifstream &,int,int,int &,int &,double &,double &,char &,int &);
void procesarResumenFinal(ofstream &,int &,int &,double &,double &,char &,int &);

             /*  - / > [ Funciones de Orden Secundario ] < / -  */

void procesarResumenDeCanal(ofstream &,int,int,int,int,int,int &,double &,double &,char &,int &);

                  /*  - / > [ Funciones Auxiliares ] < / -  */

void abrirArchivo_IFS(ifstream &,const char *);
void abrirArchivo_OFS(ofstream &,const char *);
void imprimirLinea(ofstream &,char,int);
void imprimirEncabezado(ofstream &,char);
int compactarValorTemporal(int,int,int,char);
int leerValorTemporal(ifstream &,char);
void descompactarValorTemporal(int,int &,int &,int &,char);
void imprimirValorTemporal(ofstream &,int,char);
void pasarMayuscula(char &);

#endif //POMPOMPURIN_FUNCIONES_H
