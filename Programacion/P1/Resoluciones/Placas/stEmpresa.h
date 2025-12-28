
/* [/]
 >> Project:    Placas
 >> File:       stEmpresa.h
 >> Author:     Candy
[/] */

#ifndef STEMPRESA_H
#define STEMPRESA_H
#include "stPlaca.h"

struct Empresa {
    int representanteID;
    char *representanteNomb;
    char distrito[40];
    struct Placa placasRegistradas[10];
    int cantPlacasRegistradas;
    int fechaInfraccionMasAntigua;
    int fechaPagoMasReciente;
    double totalPagadoLeves;
    double totalPagadoGraves;
    double totalPagadoMuyGraves;
    int totalInfracciones;
};


#endif /* STEMPRESA_H */
