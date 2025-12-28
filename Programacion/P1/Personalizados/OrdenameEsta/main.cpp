#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include  "funciones.h"
#define MAXMEDICO 100
#define MAXMEDICINA 300

int main() {
    int cantMedicos = 0;
    int arrMedId[MAXMEDICO],arrMedCantAtendidosConMedicinas[MAXMEDICO]{},arrMedCantAtendidosSinMedicinas[MAXMEDICO]{};
    int arrMedTotalPacientes[MAXMEDICO]{};
    double arrMedTarifa[MAXMEDICO],arrMedIngresosPorCitas[MAXMEDICO]{},arrMedIngresosPorMedicamentos[MAXMEDICO]{};
    double arrMedTotalIngresado[MAXMEDICO]{};
    int cantMedicinas = 0;
    int arrMedicinaId[MAXMEDICINA],arrMedicinaCantUnidadesVendidas[MAXMEDICINA]{};
    double arrMedicinaPrecio[MAXMEDICINA],arrMedicinaIngresosSinDescuentos[MAXMEDICINA]{},arrMedicinaDescuentosPorSeguros[MAXMEDICINA]{};
    double arrMedicinaTotalIngresado[MAXMEDICINA]{};
    // Carga de Datos de Medico
    cargarDatos_Medico("ArchivosDeDatos/Medicos.txt",arrMedId,arrMedTarifa,cantMedicos);
    // Carga de Datos de Medicina
    cargarDatos_Medicina("ArchivosDeDatos/Medicinas.txt",arrMedicinaId,arrMedicinaPrecio,cantMedicinas);
    // Procesar Citas
    procesarCitas("ArchivosDeDatos/CitasMedicas.txt",arrMedId,arrMedTarifa,arrMedIngresosPorCitas,arrMedIngresosPorMedicamentos,
                  arrMedTotalIngresado,arrMedCantAtendidosConMedicinas,arrMedCantAtendidosSinMedicinas,arrMedTotalPacientes,cantMedicos,
                  arrMedicinaId,arrMedicinaPrecio,arrMedicinaCantUnidadesVendidas,arrMedicinaIngresosSinDescuentos,arrMedicinaDescuentosPorSeguros,
                  arrMedicinaTotalIngresado,cantMedicinas);
    // Emitir Reporte de Ingresos
    emitirReporteDeIngresos("ArchivosDeReporte/ReporteDeIngresos.txt",arrMedId,arrMedTarifa,arrMedIngresosPorCitas,arrMedIngresosPorMedicamentos,
                  arrMedTotalIngresado,arrMedCantAtendidosConMedicinas,arrMedCantAtendidosSinMedicinas,arrMedTotalPacientes,cantMedicos,
                  arrMedicinaId,arrMedicinaPrecio,arrMedicinaCantUnidadesVendidas,arrMedicinaIngresosSinDescuentos,arrMedicinaDescuentosPorSeguros,
                  arrMedicinaTotalIngresado,cantMedicinas);
    // Emitir Reporte de Mejores Medicos
    // emitirReporteDeMejoresMedicos();
    return 0;
}
