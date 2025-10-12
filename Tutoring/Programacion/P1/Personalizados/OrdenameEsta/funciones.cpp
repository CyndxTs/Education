#include <fenv.h>
#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
#include  "funciones.h"
#define NO_ENCONTRADO -1
#define DIMLINEA 120

void cargarDatos_Medico(const char *nombArchMedicos,int *arrMedId,double *arrMedTarifa,int &cantMedicos) {
    ifstream archMedicos;
    abrirAchivo_IFS(archMedicos,nombArchMedicos);
    int medId;
    double medTarifa;
    while (1) {
        archMedicos>>medId;
        if (archMedicos.eof()) break;
        archMedicos>>ws;
        archMedicos.get();
        while(archMedicos.get() != '/');
        archMedicos>>ws;
        while(archMedicos.get() != ' ');
        archMedicos>>medTarifa;
        arrMedId[cantMedicos] = medId;
        arrMedTarifa[cantMedicos] = medTarifa;
        cantMedicos++;
    }
}

void cargarDatos_Medicina(const char *nombArchMedicinas,int *arrMedicinaId,double *arrMedicinaPrecio,int &cantMedicinas) {
    ifstream archMedicinas;
    abrirAchivo_IFS(archMedicinas,nombArchMedicinas);
    int medicinaId;
    double medicinaPrecio;
    while (1) {
        archMedicinas>>medicinaId;
        if (archMedicinas.eof()) break;
        archMedicinas>>ws;
        while(archMedicinas.get() != ' ');
        archMedicinas>>medicinaPrecio;
        arrMedicinaId[cantMedicinas] = medicinaId;
        arrMedicinaPrecio[cantMedicinas] = medicinaPrecio;
        cantMedicinas++;
    }
}

void procesarCitas(const char *nombArchCitas,int *arrMedId,double *arrMedTarifa,double *arrMedIngresosPorCitas,double *arrMedIngresosPorMedicamentos,
                  double *arrMedTotalIngresado,int *arrMedCantAtendidosConMedicinas,int *arrMedCantAtendidosSinMedicinas,int *arrMedTotalPacientes,int &cantMedicos,
                  int *arrMedicinaId,double *arrMedicinaPrecio,int *arrMedicinaCantUnidadesVendidas,double *arrMedicinaIngresosSinDescuentos,double *arrMedicinaDescuentosPorSeguros,
                  double *arrMedicinaTotalIngresado,int &cantMedicinas) {
    double porcentajeDeDescuentoPorSeguro;
    ifstream archCitas;
    abrirAchivo_IFS(archCitas,nombArchCitas);
    int fechaCita,h_ini,h_fin,pacienteId,medId,medicinaId,cantRecetada,posMedico,posMedicina,cantMedicinasRecetadas;
    double duracionHoras,montoPorCita,montoPorMedicina,montoDescuentoPorMedicina;
    while(1) {
        fechaCita = leerDatoTemporal(archCitas,'F');
        if (fechaCita == -1) break;
        archCitas>>pacienteId>>porcentajeDeDescuentoPorSeguro;
        h_ini = leerDatoTemporal(archCitas,'H');
        h_fin = leerDatoTemporal(archCitas,'H');
        duracionHoras = (h_fin - h_ini)/3600.0;
        archCitas>>medId;
        posMedico = obtenerPosicion(medId,arrMedId,cantMedicos);
        if (posMedico != NO_ENCONTRADO) {
            montoPorCita = arrMedTarifa[posMedico]*duracionHoras;
            arrMedIngresosPorCitas[posMedico] += montoPorCita;
            arrMedTotalIngresado[posMedico] += montoPorCita;
            cantMedicinasRecetadas = 0;
            while (1) {
                if (archCitas.get() == '\n') break; // de primero para casos en los que no se reseta nada
                archCitas>>medicinaId>>cantRecetada;
                posMedicina = obtenerPosicion(medicinaId,arrMedicinaId,cantMedicinas);
                if (posMedicina != NO_ENCONTRADO) {
                    cantMedicinasRecetadas++;
                    montoPorMedicina = arrMedicinaPrecio[posMedicina]*cantRecetada;
                    montoDescuentoPorMedicina = montoPorMedicina*porcentajeDeDescuentoPorSeguro*0.01;
                    arrMedIngresosPorMedicamentos[posMedico] += montoPorMedicina - montoDescuentoPorMedicina;
                    arrMedTotalIngresado[posMedico] += montoPorMedicina - montoDescuentoPorMedicina;
                    arrMedicinaCantUnidadesVendidas[posMedicina] += cantRecetada;
                    arrMedicinaIngresosSinDescuentos[posMedicina] += montoPorMedicina;
                    arrMedicinaDescuentosPorSeguros[posMedicina] += montoDescuentoPorMedicina;
                    arrMedicinaTotalIngresado[posMedicina] += montoPorMedicina - montoDescuentoPorMedicina;
                }
            }
            if (cantMedicinasRecetadas == 0) arrMedCantAtendidosSinMedicinas[posMedico]++;
            else arrMedCantAtendidosConMedicinas[posMedico]++;
            arrMedTotalPacientes[posMedico]++;
        } else while (archCitas.get() != '\n'); // descarte por medico inexistente
    }
}



void emitirReporteDeMedicos(ofstream &archRep,int *arrMedId,double *arrMedTarifa,double *arrMedIngresosPorCitas,double *arrMedIngresosPorMedicamentos,
                  double *arrMedTotalIngresado,int *arrMedCantAtendidosConMedicinas,int *arrMedCantAtendidosSinMedicinas,int *arrMedTotalPacientes,int &cantMedicos) {
    imprimirEncabezado(archRep,'A');
    for(int i = 0;i < cantMedicos;i++) {
        archRep<<setw(20)<<arrMedId[i];
        archRep<<setw(20)<<arrMedTarifa[i];
        archRep<<setw(20)<<arrMedIngresosPorCitas[i];
        archRep<<setw(20)<<arrMedIngresosPorMedicamentos[i];
        archRep<<setw(20)<<arrMedTotalIngresado[i];
        archRep<<setw(20)<<arrMedCantAtendidosConMedicinas[i];
        archRep<<setw(20)<<arrMedCantAtendidosSinMedicinas[i];
        archRep<<setw(20)<<arrMedTotalPacientes[i];
        archRep<<endl;
    }
}

void emitirReporteDeIngresos(const char *nombArchRep,int *arrMedId,double *arrMedTarifa,double *arrMedIngresosPorCitas,double *arrMedIngresosPorMedicamentos,
                  double *arrMedTotalIngresado,int *arrMedCantAtendidosConMedicinas,int *arrMedCantAtendidosSinMedicinas,int *arrMedTotalPacientes,int cantMedicos,
                  int *arrMedicinaId,double *arrMedicinaPrecio,int *arrMedicinaCantUnidadesVendidas,double *arrMedicinaIngresosSinDescuentos,double *arrMedicinaDescuentosPorSeguros,
                  double *arrMedicinaTotalIngresado,int cantMedicinas) {
    ofstream archRep;
    abrirAchivo_OFS(archRep,nombArchRep);
    archRep<<fixed;
    archRep.precision(2);
    emitirReporteDeMedicos(archRep,arrMedId,arrMedTarifa,arrMedIngresosPorCitas,arrMedIngresosPorMedicamentos,
                  arrMedTotalIngresado,arrMedCantAtendidosConMedicinas,arrMedCantAtendidosSinMedicinas,arrMedTotalPacientes,cantMedicos);
    emitirReporteDeMedicinas(archRep,arrMedicinaId,arrMedicinaPrecio,arrMedicinaCantUnidadesVendidas,arrMedicinaIngresosSinDescuentos,arrMedicinaDescuentosPorSeguros,
                  arrMedicinaTotalIngresado,cantMedicinas);
}

void emitirReporteDeMedicinas(ofstream &archRep,int *arrMedicinaId,double *arrMedicinaPrecio,int *arrMedicinaCantUnidadesVendidas,double *arrMedicinaIngresosSinDescuentos,double *arrMedicinaDescuentosPorSeguros,
                  double *arrMedicinaTotalIngresado,int &cantMedicinas) {
    imprimirEncabezado(archRep,'B');
    for (int i = 0;i < cantMedicinas;i++) {
        archRep<<setw(20)<<arrMedicinaId[i];
        archRep<<setw(20)<<arrMedicinaPrecio[i];
        archRep<<setw(20)<<arrMedicinaCantUnidadesVendidas[i];
        archRep<<setw(20)<<arrMedicinaIngresosSinDescuentos[i];
        archRep<<setw(20)<<arrMedicinaDescuentosPorSeguros[i];
        archRep<<setw(20)<<arrMedicinaTotalIngresado[i];
        archRep<<endl;
    }
}

void abrirAchivo_IFS(ifstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::in);
    if (arch.is_open()) return;
    cout<<"ERROR: No se encontro el archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}

void abrirAchivo_OFS(ofstream &arch,const char *nombArch) {
    arch.open(nombArch,ios::out);
    if (arch.is_open()) return;
    cout<<"ERROR: No se encontro el archivo '"<<nombArch<<"'."<<endl;
    exit(1);
}

//
void imprimirEncabezado(ofstream &archSaldia, char tipo) {
    if (tipo == 'A') {
        archSaldia<<setw((19+DIMLINEA)/2)<<"REGISTRO POR MEDICO"<<endl;
        archSaldia<<endl;
    } else {
        archSaldia<<setw((19+DIMLINEA)/2)<<"REGISTRO POR VUELO"<<endl;
        archSaldia<<endl;
    }
}
//
int compactarDatoTemporal(int dd_hor,int mm_min,int aa_seg,char tipo) {
    if (tipo =='H') return 3600*dd_hor + 60*mm_min + aa_seg;
    return dd_hor + 100*mm_min + 10000*aa_seg;
}
//
int leerDatoTemporal(ifstream &archEntrada,char tipo) {
    int dd_hor,mm_min,aa_seg;
    char c;
    archEntrada>>dd_hor;
    if (archEntrada.eof()) return -1;
    archEntrada>>c>>mm_min>>c>>aa_seg;
    return compactarDatoTemporal(dd_hor,mm_min,aa_seg,tipo);
}

int obtenerPosicion(int id,int *arr,int medida) {
    for (int i = 0; i < medida; i++) if (arr[i] == id) return i;
    return NO_ENCONTRADO;
}

/*
int obtenerPosicion_ConRepeticion(int id,int *arr,int medida,int &ult_pos) {
    for (int i = ult_pos; i < medida; i++) {
        if (arr[i] == id) {
            ult_pos = i + 1;
            return i;
        }
    }
    return NO_ENCONTRADO;
}

/*  ultPos = 0;
 *  while(1){
 *    posicion = obtenerPOsicionConRepeticion()
 *    if(poscion != NO_ENCONTRADO) {
 *      cositas
 *      } else break;
 *
 */