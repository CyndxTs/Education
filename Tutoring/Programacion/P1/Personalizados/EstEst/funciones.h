
/* [/]
 >> Project:    EstEst
 >> File:       funciones.h
 >> Author:     Candy
[/] */

#ifndef FUNCIONES_H
#define FUNCIONES_H
#include "stMedico.h"

void cargarMedicos(const char *nombArchMed,struct Medico *arrMed,int &cantMed);
void imprimirMedicos(const char *nombArchMed,struct Medico *arrMed,int cantMed);
char *leerCadenaExacta(ifstream &archEntrada,int medida,char delimitador);
char *leerCadenaExacta(ifstream &archEntrada,char delimitador);
char *obtenerDinamicoExacto(char *buff);


#endif /* FUNCIONES_H */
