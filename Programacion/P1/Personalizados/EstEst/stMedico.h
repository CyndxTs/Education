
/* [/]
 >> Project:    EstEst
 >> File:       stMedico.h
 >> Author:     Candy
[/] */

#ifndef STMEDICO_H
#define STMEDICO_H

struct Medico {
    char *id;
    char *nombre;
    char especialidad[40];
    double tarifa;
    int sumaTiempo;
    int cantAtenciones;
    // struct CitaMedica citas[10];  // En el ejercicio veremos como tener una estructura dentro de otra..
};


#endif /* STMEDICO_H */
