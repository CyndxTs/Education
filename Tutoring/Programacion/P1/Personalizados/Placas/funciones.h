
/* [/]
 >> Project:    Placas
 >> File:       funciones.h
 >> Author:     Candy
[/] */

#ifndef FUNCIONES_H
#define FUNCIONES_H
#include "stEmpresa.h"

void cargarDatos_Empresa(const char *nombArchEmp,struct Empresa *arrEmpresa,int &cantEmpresas);
void cargarDatos_PlacasRegistradas(const char *nombArchPlac,struct Empresa *arrEmp,int cantEmp);
void cargarDatos_Infraccion(const char *nombArchInf,struct Infraccion *arrInfraccion,int &cantInfracciones);
void cargarDatos_InfraccionesDeEmpresa(const char *nombArchInfCom,struct Empresa *arrEmpresa,
                                    struct Infraccion *arrInfraccion,int cantEmpresas,int cantInfracciones);
int obtenerPosicion(int representanteID,struct Empresa *arrEmp,int cantEmp);
int obtenerPosicionPorPlaca(char *id,struct Empresa *arrEmp,int cantEmp);
void emitirReporte(const char *nombArchRep,struct Empresa *arrEmp,int cantEmp);

int obtenerPosicion(char *id,struct Infraccion *arrInfraccion,int cantInfracciones);
char *leerCadenaExacta(ifstream &archEntrada,int medida,char delimitador);
char *obtenerDinamicoExacto(char *buff);
// Modulo de Lectura de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int leerValorTemporal(ifstream &archEntrada,char tipo);
// Modulo de Compactacion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
int compactarValorTemporal(int dd_hor,int mm_min,int aa_seg,char tipo);
// Modulo de Impresion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
void imprimirValorTemporal(ofstream &archSalida,int valor,char tipo);
// Modulo de Descompactacion de Valor Temporal [Ideado para: 'F' = Fecha {dd/mm/aaaa} || 'H' = Hora {hh:mm:ss}]
void descompactarValorTemporal(int valor,int &dd_hor,int &mm_min,int &aa_seg,char tipo);

void imprimirLinea(ofstream &archSalida,int medida, char simbolo);
void imprimirEncabezado(ofstream &archSalida);


#endif /* FUNCIONES_H */
