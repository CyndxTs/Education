
/* [/]
 >> Project:    EstDin
 >> File:       funciones.h
 >> Author:     
[/] */

#ifndef FUNCIONES_H
#define FUNCIONES_H

// EJEMPLO
struct ReferenciaTemporal {
    int ref_ini;
    int ref_fin;
};

struct Valoracion {
    struct ReferenciaTemporal referencia;
    char *comentario;
    int valoracion;
    int numLikes;
};

struct Sesion {
    int fecha;
    struct ReferenciaTemporal referenciaGeneral;
    struct Valoracion *valoracionesDuranteSesion;
    int cantValoracionesDuranteSesion;
};

struct Usuario {
    int id;
    char *username;
    bool esPerfilPublico;
    int fechaNacimiento;
    struct Sesion *sesionesDeUsuario;
    int cantSesiones;
};

void cargarDatos_Usuario(const char *,struct Usuario *,int &);

void cargarDatos_Sesion(const char *,struct Usuario *,int);

void cargarDatos_Valoracion(const char *,struct Usuario *,int);

int obtenerPosicion(int,struct Usuario *,int);

int obtenerPosicion(int,int,struct Sesion *,int);

void actualizarReferencias(struct Usuario *,int);

void imprimirReporte(const char *,struct Usuario *,int);

void abrirArchivo_IFS(ifstream &,const char *);

void abrirArchivo_OFS(ofstream &,const char *);

void imprimirLinea(ofstream &,char,int,bool);

int leerValorTemporal(ifstream &,char);

int compactarValorTemporal(int,int,int,char);

void imprimirValorTemporal(ofstream &,int,char);

void descompactarValorTemporal(int,int &,int &,int &,char);

char *obtenerDinamicoExacto(char *);

char *leerCadenaExacta(ifstream &,char);

#endif /* FUNCIONES_H */
