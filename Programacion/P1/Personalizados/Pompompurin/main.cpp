
/*]
 >> Project:    Pompompurin
 >> Autor:      Candy
 >> File:       main.cpp
[*/

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include  "Bibliotecas/funciones.h"
// Modulo principal
int main() {
    // Declaración de parametros de reporte
    int f_ini = 20220909,f_fin = 20230101;
    // Emisión de reporte parametrizado
    emitirReporte("ArchivosDeReporte/Reporte.txt","ArchivosDeDatos/twitchdataTP.txt",f_ini,f_fin);

    return 0;
}
