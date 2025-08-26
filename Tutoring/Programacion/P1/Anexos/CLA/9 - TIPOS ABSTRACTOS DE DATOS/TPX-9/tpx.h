
/* [/]
 >> Project:    TPX-9
 >> File:       tpx.h
 >> Author:     Candy
[/] */

#ifndef TPX_H
#define TPX_H

struct Dato {
    int valor;
};

struct NodoSimple {
    struct Dato dato;
    struct NodoSimple *prox;
};

struct NodoSimple *obtenerNodo(struct Dato, struct NodoSimple *);

struct NodoDoble {
    struct Dato dato;
    struct NodoDoble *prev;
    struct NodoDoble *prox;
};

struct NodoDoble *obtenerNodo(struct Dato, struct NodoDoble *, struct NodoDoble *);

struct LSE {
    int longitud;
    struct NodoSimple *inicial;
};

void construir(struct LSE &);

struct LC {
    int longitud;
    struct NodoSimple *inicial;
};

void construir(struct LC &);

struct LDE {
    int longitud;
    struct NodoDoble *inicial;
};

void construir(struct LDE &);


struct LDEC {
    int longitud;
    struct NodoDoble *inicial;
};

void construir(struct LDEC &);

struct Pila {
    int medida;
    struct NodoSimple *cima;
};

void construir(struct Pila &);

struct Cola {
    int medida;
    struct NodoSimple *cabeza;
    struct NodoSimple *cola;
};

void construir(struct Cola &);

#endif /* TPX_H */
