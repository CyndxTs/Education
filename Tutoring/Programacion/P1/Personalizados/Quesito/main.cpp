


#include <iostream>  // cout (ostream) cin (istream)
#include <iomanip>
#include <fstream>   //  ofstream ifstream
using namespace std;


int main() {

    ifstream archQueso("../Queso.txt",ios::in);
    ofstream archSalida("../Paria.txt",ios::out);
    if (!archQueso.is_open()) {
        cout<<"No esta mano pipipi"<<endl;
        exit(1);
    }
    char letra;
    while (1) {
        letra = archQueso.get();
        if (letra == ' ') break;
        archSalida<<letra;
    }
    archSalida<<" PRO"<<endl;


    return 0;
}

