
/* [/]
 >> Project:    EstDin
 >> File:       main.cpp
 >> Author:     
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;
#include "funciones.h"


int main(int argc, char** argv) {

    struct Usuario *arrUsuario;
    arrUsuario = new struct Usuario[20];
    int cantUsuarios = 0;
    
    cargarDatos_Usuario("Usuarios.txt",arrUsuario,cantUsuarios);
    cargarDatos_Sesion("Logs.txt",arrUsuario,cantUsuarios);
    cargarDatos_Valoracion("Valoraciones.txt",arrUsuario,cantUsuarios);
    actualizarReferencias(arrUsuario,cantUsuarios);
    imprimirReporte("Reporte.txt",arrUsuario,cantUsuarios);

    return 0;
}
