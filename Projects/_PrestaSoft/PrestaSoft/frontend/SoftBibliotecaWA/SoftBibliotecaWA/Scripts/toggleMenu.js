function toggleMenu() {
    const barra = document.getElementById('barraDerecha');
    const contenido = document.getElementById('contenido');

    if (barra && contenido) {
        barra.classList.toggle('visible');

        if (barra.classList.contains('visible')) {
            contenido.classList.add('contenidoAdaptado');
        } else {
            contenido.classList.remove('contenidoAdaptado');
        }
    } else {
        console.error('No se encontró barra o contenido');
    }
}
document.addEventListener('DOMContentLoaded', function () {
});