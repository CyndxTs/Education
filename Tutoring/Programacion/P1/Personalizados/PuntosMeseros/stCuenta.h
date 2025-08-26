
/* [/]
 >> Project:    PuntosMeseros
 >> File:       stCuenta.h
 >> Author:     
[/] */

#ifndef STCUENTA_H
#define STCUENTA_H

struct Cuenta {
    int codigo;
    char *nombre;
    double saldo;
    int fechaSobregiro;
    bool sobregiro;
};


#endif /* STCUENTA_H */
