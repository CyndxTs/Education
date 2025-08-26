
/* [/]
 >> Project:    Aegleseeker
 >> File:       Partida.cpp
 >> Author:     CyndxTs o.0?!
[/] */

#include "Partida.h"

//
Partida::Partida() {
    numJugadas = 0;
    enJuego = true;
    mapa_muestra = nullptr;
    mapa_valores = nullptr;
}
//
Partida::~Partida() {
    if(mapa_muestra) delete mapa_muestra;
    if(mapa_valores) delete mapa_valores;
}
//
int Partida::obtenerChancePorDificultad(char diff){
    switch(diff){
        case 'E':   // Chance de Aparición de Mina : 10% - 15%
            return (10 + rand() % 6);
        case 'N':   // Chance de Aparición de Mina : 15% - 20%
            return (15 + rand() % 6);
        case 'H':   // Chance de Aparición de Mina : 20% - 25%
            return (20 + rand() % 6);
        default:    // Chance de Aparición de Mina : 10% - 25%
            return (10 + rand() % 16);
    }
}
//
int Partida::obtenerDimensionPorDificultad(char diff){
    switch(diff){
        case 'E':   // Entre 25% - 50% de 'dimLinea'
            return (1 + ((dimLinea - 3)/16) + rand() % ((dimLinea - 3)/16));
        case 'N':   // Entre 50% - 75% de 'dimLinea'
            return (1 + ((dimLinea - 3)/8) + rand() % ((dimLinea - 3)/16));
        case 'H':   // Entre 75% - 100% de 'dimLinea'
            return (1 + (3*(dimLinea - 3)/16) + rand() % ((dimLinea - 3)/16));
        default:    // Entre 25% - 100% de 'dimLinea'
            return (1 + ((dimLinea - 3)/16) + rand() % (3*(dimLinea - 3)/16));
    }
}
//
void Partida::iniciarNuevaPartida(){
    imprimirTitulos('A');
    inicializarElementos();
    while(1){
        mostrar();
        while(not realizarJugada());
        if(validarVictoria()){
            mostrar();
            imprimirTitulos('W');
            break;
        } else if(not enJuego){
            imprimirTitulos('L');
            break;
        }
    }
}
//
void Partida::inicializarElementos(){
    srand(time(NULL));
    cout<<endl<<setw((dimLinea - 58)/2)<<' ';
    cout<<"[E] : Easy | [N] : Normal | [H] : Hard | [Otro] : Random"<<endl;
    cout<<endl<<">> Selecciona la dificultad de la partida: ";
    cin>>dificultad;
    dificultad -= (dificultad >= 'a' and dificultad <= 'z')? 'a' - 'A' : 0;
    imprimirLinea(dimLinea,'=');
    cout<<"Generando tablero.."<<endl;
    int dX = obtenerDimensionPorDificultad(dificultad);
    int dY = obtenerDimensionPorDificultad(dificultad);
    mapa_valores = new class TableroDeValores;
    mapa_valores->inicializar(dX,dY,obtenerChancePorDificultad(dificultad));
    mapa_muestra = new class TableroDeMuestra;
    mapa_muestra->inicializar(dX,dY,mapa_valores->getNumMinas());
}
//
void Partida::mostrar(){
    imprimirLinea(dimLinea,'-');
    cout<<setw((dimLinea - 24)/2)<<"Give up?: Inserta una letra.";
    cout<<" | Jugadas realizadas: "<<setw(2)<<numJugadas;
    mapa_muestra->mostrar();
}
//
void Partida::mostrar(int cX,int cY){
    imprimirLinea(dimLinea,'-');
    cout<<setw((dimLinea - 24)/2)<<"Give up?: Inserta una letra.";
    cout<<" | Jugadas realizadas: "<<setw(2)<<numJugadas;
    mapa_muestra->mostrar(cX,cY);
}
//
bool Partida::realizarJugada(){
    int cX,cY,accion;
    cout<<endl<<">> Inserte la posicion de la casilla en la que desee realizar";
    cout<<" una accion: ";
    cin>>cX>>cY;
    if(validarRendicion()) return true;
    if(not mapa_valores->validarPosicionEnDominio(cX,cY)){
        cout<<endl<<"La posicion insertada no es valida. Intente de nuevo.";
        cout<<endl;
        return false;
    }
    mostrar(cX,cY);
    while(true){
        cout<<endl<<setw((dimLinea - 22)/2)<<"[1] : Minar | [2] : Marcar ";
        cout<<"| [3] : Desmarcar | [Otro Numero] : Reseleccionar"<<endl<<endl;
        cout<<">> Inserte la accion a realizar en ("<<cX<<','<<cY<<"): ";
        cin>>accion;
        if(validarRendicion()) return true;
        if(accion > 0 and accion < 4){
            if((accion == 1 and minarCasilla(cX,cY)) or
               (accion == 2 and marcarCasilla(cX,cY)) or
               (accion == 3 and desmarcarCasilla(cX,cY))){
                numJugadas++;
                return true;
            } else cout<<endl<<"Jugada invalida. Intente nuevamente."<<endl;
        } else break;
    }
    return false;
}
//
bool Partida::minarCasilla(int cX,int cY){
    if(mapa_muestra->obtenerCasilla(cX,cY) != '?') return false;
    char valor = mapa_valores->obtenerCasilla(cX,cY);
    if(valor != '*'){
        mapa_muestra->actualizarCasilla(cX,cY,valor);
        revelarArea(cX,cY);
    } else {
        mapa_muestra->actualizarCasilla(cX,cY,mapa_valores->obtenerCasilla(cX,cY));
        mostrar(cX,cY);
        enJuego = false;
    }
    return true;
}
//
void Partida::revelarArea(int mX,int mY){
    if(mapa_valores->obtenerCasilla(mX,mY) != '0') return;
    for(int k = 0,nX,nY;k < 8;k++){
            nX = mX + movsAdy[k][0];
            nY = mY + movsAdy[k][1];
            if(mapa_valores->validarPosicionEnDominio(nX,nY) and
               mapa_valores->obtenerCasilla(nX,nY) != '*' and
               mapa_muestra->obtenerCasilla(nX,nY) == '?'){
                mapa_muestra->actualizarCasilla(nX,nY,mapa_valores->obtenerCasilla(nX,nY));
                revelarArea(nX,nY);
            }
    }
}
//
bool Partida::marcarCasilla(int cX,int cY){
    if(mapa_muestra->getNumMarcadores() == 0 or
       mapa_muestra->obtenerCasilla(cX,cY) != '?') return false;
    mapa_muestra->actualizarCasilla(cX,cY,'X');
    mapa_muestra->setNumMarcadores(mapa_muestra->getNumMarcadores()-1);
    return true;
}
//
bool Partida::desmarcarCasilla(int cX,int cY){
    if(mapa_muestra->obtenerCasilla(cX,cY) != 'X') return false;
    mapa_muestra->actualizarCasilla(cX,cY,'?');
    mapa_muestra->setNumMarcadores(mapa_muestra->getNumMarcadores()+1);
    return true;
}
//
bool Partida::validarRendicion(){
    if(not cin.fail()) return false;
    enJuego = false;
    return true;
}
//
bool Partida::validarVictoria(){
    if(mapa_muestra->getNumMarcadores() > 0) return false;
    for(int y = 0;y < mapa_muestra->getDimY();y++){
        for(int x = 0;x < mapa_muestra->getDimX();x++){
            if(mapa_muestra->obtenerCasilla(x,y) == '?') return false;
        }
    }
    return true;
}
//
void Partida::imprimirLinea(int medida,char simbolo){
    for(int i = 0;i < medida;i++) cout.put(simbolo);
    cout<<endl;
}
//
void Partida::imprimirTitulos(char seleccion){
    switch(seleccion){
        case 'A':
            cout<<endl<<setw((29+dimLinea)/2)<<"> ' A E G L E S E E K E R ' <";
            cout<<endl;
            break;
        case 'W':
            cout<<endl<<frases_victoria[rand() % 7]<<endl<<endl;
            cout<<setw((17+dimLinea)/2)<<"> Y O U   W I N <"<<endl;
            break;
        case 'L':
            cout<<endl<<frases_derrota[rand() % 7]<<endl<<endl;
            cout<<setw((21+dimLinea)/2)<<"> G A M E   O V E R <"<<endl;
            break;
    }
}
