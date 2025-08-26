
/* [/]
 >> Project:    Priorizando
 >> File:       main.cpp
 >> Author:     Candy
[/] */

#include <iomanip>
#include <iostream>
#include <fstream>
using namespace std;
#include "ColaPrioritaria.h"

int main(int argc, char** argv) {
    //
    struct ColaPrioritaria colaPrioritaria;
    struct Dato datos[20] = {{8,"Pepe"},{3,"Ramiro"},{5,"Jorge"},
                             {6,"Manuel"},{3,"Valeriano"},{1,"Zaid"},
                             {3,"Pablo"},{6,"Martin"},{2,"Juan"},
                             {1,"Esmeralda"},{2,"Roberto"},{8,"Sofia"},
                             {5,"Liz"},{7,"Zoe"},{3,"Valentina"}};
    int numDat = 15;
    //
    construir(colaPrioritaria);
    //
    for(int i = 0;i < numDat;i++) insertarPriorizando(colaPrioritaria,datos[i]);
    //
    imprimir(colaPrioritaria);

    return 0;
}
