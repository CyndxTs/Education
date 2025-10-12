//
// Created by cesar on 26/09/2025.
//

#ifndef ORDENAMEESTA_FUNCIONES_H
#define ORDENAMEESTA_FUNCIONES_H


void cargarDatos_Medico(const char *nombArchMedicos,int *arrMedId,double *arrMedTarifa,int &cantMedicos);

void cargarDatos_Medicina(const char *nombArchMedicinas,int *arrMedicinaId,double *arrMedicinaPrecio,int &cantMedicinas);
void procesarCitas(const char *nombArchCitas,int *arrMedId,double *arrMedTarifa,double *arrMedIngresosPorCitas,double *arrMedIngresosPorMedicamentos,
                  double *arrMedTotalIngresado,int *arrMedCantAtendidosConMedicinas,int *arrMedCantAtendidosSinMedicinas,int *arrMedTotalPacientes,int &cantMedicos,
                  int *arrMedicinaId,double *arrMedicinaPrecio,int *arrMedicinaCantUnidadesVendidas,double *arrMedicinaIngresosSinDescuentos,double *arrMedicinaDescuentosPorSeguros,
                  double *arrMedicinaTotalIngresado,int &cantMedicinas);


void emitirReporteDeMedicos(ofstream &archRep,int *arrMedId,double *arrMedTarifa,double *arrMedIngresosPorCitas,double *arrMedIngresosPorMedicamentos,
                  double *arrMedTotalIngresado,int *arrMedCantAtendidosConMedicinas,int *arrMedCantAtendidosSinMedicinas,int *arrMedTotalPacientes,int &cantMedicos);
void emitirReporteDeIngresos(const char *nombArchRep,int *arrMedId,double *arrMedTarifa,double *arrMedIngresosPorCitas,double *arrMedIngresosPorMedicamentos,
                  double *arrMedTotalIngresado,int *arrMedCantAtendidosConMedicinas,int *arrMedCantAtendidosSinMedicinas,int *arrMedTotalPacientes,int cantMedicos,
                  int *arrMedicinaId,double *arrMedicinaPrecio,int *arrMedicinaCantUnidadesVendidas,double *arrMedicinaIngresosSinDescuentos,double *arrMedicinaDescuentosPorSeguros,
                  double *arrMedicinaTotalIngresado,int cantMedicinas);
void emitirReporteDeMedicinas(ofstream &archRep,int *arrMedicinaId,double *arrMedicinaPrecio,int *arrMedicinaCantUnidadesVendidas,double *arrMedicinaIngresosSinDescuentos,double *arrMedicinaDescuentosPorSeguros,
                  double *arrMedicinaTotalIngresado,int &cantMedicinas);
void abrirAchivo_IFS(ifstream &arch,const char *nombArch);
void abrirAchivo_OFS(ofstream &arch,const char *nombArch);
void imprimirEncabezado(ofstream &archSaldia, char tipo);
int compactarDatoTemporal(int dd_hor,int mm_min,int aa_seg,char tipo);
int leerDatoTemporal(ifstream &archEntrada,char tipo);
int obtenerPosicion(int id,int *arr,int medida);

#endif //ORDENAMEESTA_FUNCIONES_H