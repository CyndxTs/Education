
/* 
 * File:   Arbol.cpp
 * Author: Cesar Augusto Napuri de la Cruz
 * Codigo: 20211692
 */

#include "Arbol.h"

Arbol::Arbol() {
    raiz = nullptr;
}

Arbol::~Arbol() {
}

void Arbol::cargarDatos(ifstream &archEst,ifstream &archLib){
    int id;
    double capacidad;
    while(1){
        archEst>>id;
        if(archEst.eof()) break;
        archEst.get();
        archEst>>capacidad;
        NEstante *nEst = new NEstante;
        nEst->SetId(id);
        nEst->SetCapacidad(capacidad);
        nEst->SetDisponible(capacidad);
        nEst->cargarLibros(archLib);
        nEst->actualizarDisponibilidad();
        insertarRecursivo(nEst,raiz);
    }
}

void Arbol::mostrarDatos(ofstream &archRep){
    mostrarEnOrdenRecursivo(archRep,raiz);
}

void Arbol::mostrarEnOrdenRecursivo(ofstream &archRep,class NEstante *nAux){
    if(nAux){
        mostrarEnOrdenRecursivo(archRep,nAux->izq);
        nAux->mostrar(archRep);
        mostrarEnOrdenRecursivo(archRep,nAux->der);
    }
}

void Arbol::insertarRecursivo(class NEstante *nInsertar,class NEstante *&nAux){
    if(!nAux) nAux = nInsertar;
    else if(*nInsertar < *nAux) insertarRecursivo(nInsertar,nAux->izq);
    else insertarRecursivo(nInsertar,nAux->der);
}

void Arbol::evaluarPeso(double pesoEval){
    bool encontrado = false;
    ofstream archAuxRep("Prueba.txt",ios::out);
    if(!archAuxRep.is_open()){
       exit(1); 
    }
    buscarValidadoRecursivo(archAuxRep,pesoEval,raiz,encontrado);
    if(!encontrado) cout<<"No es posible colocar el libro"<<endl;
}

void Arbol::buscarValidadoRecursivo(ofstream &arch,double pesoEval,class NEstante *nAux,
                            bool &encontrado){
    if(!nAux) return;
    buscarValidadoRecursivo(arch,pesoEval,nAux->izq,encontrado);
    buscarValidadoRecursivo(arch,pesoEval,nAux->der,encontrado);
    if(nAux->disponible >= pesoEval){
        encontrado = true;
        nAux->mostrar(arch);
        return;
    }
}
