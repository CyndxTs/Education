/* 
 * File:   main.cpp
 * Author: cueva.r
 *
 * Created on 7 de mayo de 2024, 03:04 PM
 */

#include <iostream>
#include <algorithm>
#include <vector>
#include <ctime>
#define alfa 0.3
#define iteraciones 100


using namespace std;

int verifica(vector <int>paquetes,int minrcl){
    int cont=0;
    for(int i=0;i<paquetes.size();i++) 
        if(minrcl<=paquetes[i])cont++;
    return cont;
}

bool compara(int a,int b){
    return a>b;
}
int cargamochila(int *paq,int peso,int n){
    int residual,beta,tau,minrcl,indmin,inda;
    int mejor = 999999;
    vector <int> maxsol;
    sort(paq,paq+n,compara);
    for(int k=0;k<iteraciones;k++){ //la cantidad de iteraciones
        //buscando una solucion al poblema
        vector <int> solucion;        
        vector <int > paquetes;
        paquetes.insert(paquetes.begin(),paq,paq+n);
        residual=peso;
        while(!paquetes.empty()){
            srand(time(NULL));
            beta = paquetes[0];
            tau = paquetes[paquetes.size()-1];
            minrcl = beta - alfa*(beta-tau);
            indmin = verifica(paquetes,minrcl);
            inda = rand()%indmin;
        
            if(residual-paquetes[inda]>=0){
                residual -= paquetes[inda];
                solucion.push_back(paquetes[inda]);
            }
            paquetes.erase(paquetes.begin()+inda);
        }
        //cout << residual << endl;
        if(residual<mejor){
            mejor=residual;
            maxsol.erase(maxsol.begin(),maxsol.begin()+maxsol.size());
            maxsol.insert(maxsol.begin(),solucion.begin(),solucion.begin()+solucion.size());            
        }
        
    }
    cout << "Solucion:" <<endl;
    for(int i=0;i<maxsol.size();i++)
        cout << maxsol[i] << " ";
    return mejor;    
}


int main(int argc, char** argv) {
    int paquetes[]={2,3,5,12,2};
    int peso=16;
    int n=sizeof(paquetes)/sizeof(paquetes[0]);
    cout <<endl<<"Sobra: " << cargamochila(paquetes,peso,n);
    return 0;
}
