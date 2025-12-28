//
// Created by cesar on 26/09/2025.
//

#ifndef ARREGLAMELAVIDA_FUNCIONES_H
#define ARREGLAMELAVIDA_FUNCIONES_H

void cargarSumas(const char *nombArchNum,const char *nombArchRep,int *,int *,int *);
void leerNumero(ifstream &archNum,int *numero);
void abrirAchivo_IFS(ifstream &arch,const char *nombArch);
void abrirAchivo_OFS(ofstream &arch,const char *nombArch);
void imprimirResultado(ofstream &archReporte,int *numeroGrande, int posSol);
int obtenerMedida(int *numeroGrande);
void copiarNumeroGrande(int *numOrig,int *numDest);
void sumarNumerosGrandes(int *numeroGrande1,int *numeroGrande2,int *totalGrande);

#endif //ARREGLAMELAVIDA_FUNCIONES_H