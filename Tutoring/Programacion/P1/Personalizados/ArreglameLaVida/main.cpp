#include<iomanip>
#include <iostream>
#include <fstream>
using namespace  std;
#include  "funciones.h"
#define MAXGRANDE 69

int main() {
    int numeroGrande1[MAXGRANDE],numeroGrande2[MAXGRANDE],totalGrande[MAXGRANDE];
    cargarSumas("ArchivoDeDatos/NumerosASumar.txt","ArchivoDeReporte/Sumitas.txt",numeroGrande1,numeroGrande2,totalGrande);

    return 0;
}