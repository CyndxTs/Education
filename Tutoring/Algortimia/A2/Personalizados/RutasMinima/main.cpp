/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: cueva
 *
 * Created on 3 de mayo de 2024, 05:51 PM
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <ctime>
#define alfa 0.3
#define MAX 8
#define ITERACIONES 1000000

using namespace std;

struct nodo{
    int punto;
    int distancia;
    
};

bool compara(nodo a, nodo b){
    return a.distancia < b.distancia;
    
}
int verifica(vector <nodo>ciudades,int maxrcl){
    int cont=0;
    for(int i=0;i<ciudades.size();i++) 
        if(maxrcl>=ciudades[i].distancia)
            cont++;
    return cont;    
}

int minruta(int ini,int fin,int mapa[][MAX]){
    int beta,tau,maxrcl,ciudad,indmax,inda;
    int total,min=999999;
    
   
    for(int k=0;k<ITERACIONES;k++){
        ciudad=ini;
        total = 0;

        while(1){

            vector<nodo>vecinos;
            nodo paso;
            for(int i=0;i<MAX;i++){
                if(mapa[ciudad][i]>0){
                    paso.distancia=mapa[ciudad][i];
                    paso.punto=i;
                    vecinos.push_back(paso);
                }
            }    
            if(!vecinos.empty()){
                sort(vecinos.begin(),vecinos.end(),compara);
                beta = vecinos[0].distancia;
                tau = vecinos[vecinos.size()-1].distancia;
                maxrcl = round(beta + alfa*(tau-beta));
                indmax = verifica(vecinos,maxrcl);
                srand(time(NULL));
                inda = rand()%indmax;
                ciudad = vecinos[inda].punto;
                total+=vecinos[inda].distancia;
            }
            if(ciudad==fin) break;
            if(vecinos.empty()){
                cout <<"No se encontro solución" <<endl;
                total=0;
                break;
            }
        }    
        cout << total << endl;
        if(total<min){
            min=total;
        } 
    }
    return min;
}

int main(int argc, char** argv) {
    int mapa[][MAX]={   {0,4,10,6,0,0,0,0},
                        {0,0,0,0,2,0,0,0},
                        {0,0,0,0,0,0,0,3},
                        {0,0,0,0,0,3,0,0},
                        {0,0,0,0,0,0,10,0},
                        {0,0,0,0,0,0,2,0},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0}};
    
    cout << minruta(0,6,mapa);

    return 0;
}

